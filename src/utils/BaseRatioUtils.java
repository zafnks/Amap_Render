package utils;

import java.math.BigDecimal;

public class BaseRatioUtils {

	private static Double[] ratio = new Double[18];
	//高德计算基准点
	private final static Double baseAmapLon = 12066128.329448542;
	private final static Double baseAmapLat = 2607939.472702268;	
	private final static Integer[] baseAmapTileX = new Integer[18];
	private final static Integer[] baseAmapTileY = new Integer[18];
	
	/**
	 * 计算4层至18层的比值常数
	 */
	static{
		Double t_ratio = 152.87415079575;
		Integer t_baseAmapTileX = 210000;
		Integer t_baseAmapTileY = 114012;
		ratio[17]=t_ratio;
		baseAmapTileX[17]=t_baseAmapTileX;
		baseAmapTileY[17]=t_baseAmapTileY;
		for(int i = 16;i>3;i--){
			t_ratio *=2.0;
			t_baseAmapTileX /=2;
			t_baseAmapTileY /=2;
			ratio[i]=t_ratio;
			baseAmapTileX[i]=t_baseAmapTileX;
			baseAmapTileY[i]=t_baseAmapTileY;
		}
	}
	
	/**
	 * 根据经度与zoom计算瓦片X值
	 * @param lon
	 * @param zoom
	 * @return
	 */
	public static Integer getAmapTileX(Double lon,Integer zoom){
		return round((Lon_WSC84ToMercator(lon)-baseAmapLon)/ratio[zoom-1]+baseAmapTileX[zoom-1]);
	}
	
	/**
	 * 根据纬度与zoom计算瓦片Y值
	 * @param lon
	 * @param zoom
	 * @return
	 */
	public static Integer getAmapTileY(Double lat,Integer zoom){
		return round((Lat_WSC84ToMercator(lat)-baseAmapLat)/(-1*ratio[zoom-1])+baseAmapTileY[zoom-1]);
	}
	
	
	//四舍五入取整
	private static Integer round(Double num){
		return (new BigDecimal(num).setScale(0, BigDecimal.ROUND_HALF_UP)).intValue();
	}
		
	//经度转墨卡托
	private static Double Lon_WSC84ToMercator(Double x){
		return (x / 180.0) * 20037508.34;
	}
	
	//纬度转墨卡托
	private static Double Lat_WSC84ToMercator(Double y) {
		if (y > 85.05112) {
			y = 85.05112;
		}
		if (y < -85.05112) {
			y = -85.05112;
		}
		y = (Math.PI / 180.0) * y;
		Double tmp = Math.PI / 4.0 + y / 2.0;
		return 20037508.34 * Math.log(Math.tan(tmp)) / Math.PI;
	}
	
}
