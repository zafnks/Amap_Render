package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 高德点位信息解密
 * 从高德的JS中总结出来
 * @author 刘双源
 *
 */
public class ConvertUtils {

	//解密Key
	private final static String key = "ASDFGHJKLQWERTYUIO!sdfghjkl";
	
	
	
	/**
	 * 高德点位信息解密
	 * @param data
	 * @return
	 */
	public static List<Integer> getData(String data){
		
		char[] chars = data.toCharArray();
		List<Integer> result = new ArrayList<Integer>();
		Integer flag = null;
		for(char c:chars){
			int k = key.indexOf(c);
			if(flag==null){
				flag = 27*k;
			}else{
				result.add(flag+k-333);
				flag = null;
			}
		}
		return result;
	}
	
	/**
	 * 高德瓦片偏转加密算法
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public static Integer[] getOffsetTile(Integer x, Integer y, Integer z) {
		int prefix, suffix;
		prefix = ((Double) Math.floor(z / 2)).intValue();
		suffix = z - prefix;
		prefix = (1 << prefix) - 1 << suffix;
		suffix = (1 << suffix) - 1;
		return new Integer[] { x & prefix | y & suffix, y & prefix | x & suffix };
	}
	
	
}
