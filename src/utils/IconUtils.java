package utils;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * poi点位图片类 请求过的icon图片按种类存入内存 不需要重复请求因为点位量巨大
 * 
 * @author 刘双源
 *
 */
public class IconUtils {

	/**
	 * 点位图片Buffered存储Map
	 */
	private static Map<String, BufferedImage> iconMap = new HashMap<String, BufferedImage>();
	private static HttpImgUtils imgUtils = new HttpImgUtils();

	/**
	 * 根据种类获取缓存 如果没有则向高德服务器请求
	 * 
	 * @param iconName
	 * @return
	 * @throws Exception
	 */
	public static BufferedImage getIcon(String iconName) throws Exception {

		return iconMap.get(iconName);

	}

	private static void getIconMap() throws Exception {
		InputStream poiIconStream = imgUtils.getInputStream(PathConstants.ICONPATH);
		BufferedImage bi = ImageIO.read(poiIconStream);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 17; j++) {
				BufferedImage icon = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
				for (int x = 0; x < 40; x++) {
					for (int y = 0; y < 40; y++) {
						icon.setRGB(x, y, bi.getRGB(i * 40 + x, j * 40 + y));
					}
				}
				iconMap.put((j * 10 + (i + 1)) + "", icon);
			}
		}
		poiIconStream.close();
		
		poiIconStream = imgUtils.getInputStream(PathConstants.ICONPATH2);
		bi = ImageIO.read(poiIconStream);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 17; j++) {
				BufferedImage icon = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
				for (int x = 0; x < 40; x++) {
					for (int y = 0; y < 40; y++) {
						icon.setRGB(x, y, bi.getRGB(i * 40 + x, j * 40 + y));
					}
				}
				iconMap.put("10" + (j * 10 + (i + 1)), icon);
			}
		}
		poiIconStream.close();
	}

	static {
		try {
			getIconMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
