package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpImgUtils {

	// 把从服务器获得图片的输入流InputStream写到本地磁盘
	public void saveImageToDisk(String url, String path) throws Exception {

		InputStream inputStream = getInputStream(url);
		byte[] data = new byte[1024];
		int len = 0;
		FileOutputStream fileOutputStream = null;
		fileOutputStream = new FileOutputStream(path);
		while ((len = inputStream.read(data)) != -1) {
			fileOutputStream.write(data, 0, len);
		}

		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (fileOutputStream != null) {
			try {
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// 从服务器获得一个输入流(本例是指从服务器获得一个image输入流)
	public InputStream getInputStream(String _url) throws Exception {
		InputStream inputStream = null;
		HttpURLConnection httpURLConnection = null;

		URL url = new URL(_url);
		httpURLConnection = (HttpURLConnection) url.openConnection();
		// 设置网络连接超时时间
		httpURLConnection.setConnectTimeout(3000);
		// 设置应用程序要从网络连接读取数据
		httpURLConnection.setDoInput(true);

		httpURLConnection.setRequestMethod("GET");
		int responseCode = httpURLConnection.getResponseCode();
		if (responseCode == 200) {
			// 从服务器返回一个输入流
			inputStream = httpURLConnection.getInputStream();

		}

		return inputStream;

	}

	public static void saveImageFromSteam(InputStream inputStream, String path) {
		byte[] data = new byte[1024];
		int len = 0;
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(path);
			while ((len = inputStream.read(data)) != -1) {
				fileOutputStream.write(data, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

}
