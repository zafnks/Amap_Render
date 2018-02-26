package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.imageio.ImageIO;

import thread.DataAnalysisThread;
import thread.RenderThread;
import utils.ConvertUtils;
import utils.PropertiesUtils;
import entity.Poi;
import entity.TileData;

public class Test {

	public static void main(String[] args) {
		doping();
		/*int i = 0;
		while (true) {
			if (i > 30) {
				break;
			}
			try {
				InetAddress address = InetAddress.getByName("192.168.1.1");
				boolean bl = address.isReachable(3000);
				System.out.println("=========is connnect:========" + bl);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}*/
	}
	
	public static void doping() {

		String line = null;
		try {
			Process pro = Runtime.getRuntime().exec("ping 182.168.1.100 -t");
			BufferedReader buf = new BufferedReader(new InputStreamReader(pro.getInputStream(), "GBK"));
			while ((line = buf.readLine()) != null)
				System.out.println(line);
			pro.exitValue()
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	public static void main1(String[] args) {
		List<Integer> t = ConvertUtils.getData("IDOG");
		System.out.println();
		// System.out.println(t[1]);
	}

	public static void main2(String[] args) {
		String path = System.getProperty("user.dir") + "\\WebRoot\\";
		System.out.println(path);
		try {
			PropertiesUtils.loadProperties(path);
		} catch (IOException e) {
			System.out.println("读取配置文件出错!!");
			return;
		}

		BlockingQueue<Poi> poiQueue = new LinkedBlockingQueue<Poi>(100);
		BlockingQueue<TileData> unHandleTileQueue = new LinkedBlockingQueue<TileData>(
				100);
		BlockingQueue<TileData> handleTileQueue = new LinkedBlockingQueue<TileData>(
				100);
		for (int j = 0; j < 1; j++) {
			DataAnalysisThread step2 = new DataAnalysisThread(poiQueue,
					unHandleTileQueue);
			new Thread(step2).start();
		}
		RenderThread step3 = new RenderThread(unHandleTileQueue,
				handleTileQueue);
		new Thread(step3).start();
		poiQueue.offer(new Poi(209900, 113963, 18));
		try {
			TileData data = handleTileQueue.take();
			ImageIO.write(data.getBufferedImage(), "PNG", new File(
					"D:\\123.png"));
			System.out.println("ok!");
			System.exit(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * public static void main(String[] args) { String path =
	 * System.getProperty("user.dir")+"\\WebRoot\\"; System.out.println(path);
	 * try { PropertiesUtils.loadProperties(path); } catch (IOException e) {
	 * System.out.println("读取配置文件出错!!"); return; } Run.main(new String[]{}); }
	 */

}
