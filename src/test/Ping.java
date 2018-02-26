package test;

import java.io.*;
import java.net.*;
import java.nio.channels.*;
import java.util.*;
import java.util.regex.*;

public class Ping {
	static int AYTIME_PORT = 13;
	static int port = 13;

	static class Target {
		InetSocketAddress address;
		SocketChannel channel;
		Exception failure;
		long connectStart;
		long connectFinish = 0;
		boolean shown = false;

		Target(String host) {
			try {
				address = new InetSocketAddress(InetAddress.getByName(host),
						port);
			} catch (IOException x) {
				failure = x;
			}
		}

		void show() {
			String result;
			if (connectFinish != 0)
				result = Long.toString(connectFinish - connectStart) + "ms";
			else if (failure != null)
				result = failure.toString();
			else
				result = "Timed out";
			System.out.println(address + " : " + result);
			shown = true;
		}
	}

	static class Printer extends Thread {
		LinkedList pending = new LinkedList();

		Printer() {
			setName("Printer");
			setDaemon(true);
		}

		void add(Target t) {
			synchronized (pending) {
				pending.add(t);
				pending.notify();
			}
		}

		public void run() {
			try {
				for (;;) {
					Target t = null;
					synchronized (pending) {
						while (pending.size() == 0)
							pending.wait();
						t = (Target) pending.removeFirst();
					}
					t.show();
				}
			} catch (InterruptedException x) {
				return;
			}
		}
	}

	static class Connector extends Thread {
		Selector sel;
		Printer printer;
		LinkedList pending = new LinkedList();

		Connector(Printer pr) throws IOException {
			printer = pr;
			sel = Selector.open();
			setName("Connector");
		}

		void add(Target t) {
			SocketChannel sc = null;
			try {
				sc = SocketChannel.open();
				sc.configureBlocking(false);
				boolean connected = sc.connect(t.address);
				t.channel = sc;
				t.connectStart = System.currentTimeMillis();
				if (connected) {
					t.connectFinish = t.connectStart;
					sc.close();
					printer.add(t);
				} else {
					synchronized (pending) {
						pending.add(t);
					}
					sel.wakeup();
				}
			} catch (IOException x) {
				if (sc != null) {
					try {
						sc.close();
					} catch (IOException xx) {
					}
				}
				t.failure = x;
				printer.add(t);
			}
		}

		void processPendingTargets() throws IOException {
			synchronized (pending) {
				while (pending.size() > 0) {
					Target t = (Target) pending.removeFirst();
					try {
						t.channel.register(sel, SelectionKey.OP_CONNECT, t);
					} catch (IOException x) {
						t.channel.close();
						t.failure = x;
						printer.add(t);
					}
				}
			}
		}

		void processSelectedKeys() throws IOException {
			for (Iterator i = sel.selectedKeys().iterator(); i.hasNext();) {
				SelectionKey sk = (SelectionKey) i.next();
				i.remove();
				Target t = (Target) sk.attachment();
				SocketChannel sc = (SocketChannel) sk.channel();
				try {
					if (sc.finishConnect()) {
						sk.cancel();
						t.connectFinish = System.currentTimeMillis();
						sc.close();
						printer.add(t);
					}
				} catch (IOException x) {
					sc.close();
					t.failure = x;
					printer.add(t);
				}
			}
		}

		volatile boolean shutdown = false;

		void shutdown() {
			shutdown = true;
			sel.wakeup();
		}

		public void run() {
			for (;;) {
				try {
					int n = sel.select();
					if (n > 0)
						processSelectedKeys();
					processPendingTargets();
					if (shutdown) {
						sel.close();
						return;
					}
				} catch (IOException x) {
					x.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException,
			IOException {
		args = new String[] { "80", "192.168.1.1" };
		if (args.length < 1) {
			System.err.println("Usage: java Ping [port] host...");
			return;
		}
		int firstArg = 0;
		if (Pattern.matches("[0-9]+", args[0])) {
			port = Integer.parseInt(args[0]);
			firstArg = 1;
		}
		Printer printer = new Printer();
		printer.start();
		Connector connector = new Connector(printer);
		connector.start();
		LinkedList targets = new LinkedList();
		for (int i = firstArg; i < args.length; i++) {
			Target t = new Target(args[i]);
			targets.add(t);
			connector.add(t);
		}
		Thread.sleep(2000);
		connector.shutdown();
		connector.join();
		for (Iterator i = targets.iterator(); i.hasNext();) {
			Target t = (Target) i.next();
			if (!t.shown)
				t.show();
		}
	}
}