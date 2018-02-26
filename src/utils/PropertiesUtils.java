package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
	
	public static Double begin_lon;
	public static Double begin_lat;
	public static Double end_lon;
	public static Double end_lat;
	public static Integer thread;
	public static String _path;
	public static Integer _maxZoom; 
	public static Integer _minZoom; 
	
	public static Integer _wp_type; 
	public static String defultBGColor;
	
	public static void loadProperties(String path) throws IOException{
		
		Properties prop = new Properties();   
		File file = new File(path+"config.properties");
		InputStream in = new FileInputStream(file);
        prop.load(in);   
        begin_lon = Double.valueOf(prop.getProperty("begin_lon").trim());   
        begin_lat = Double.valueOf(prop.getProperty("begin_lat").trim());
        end_lon = Double.valueOf(prop.getProperty("end_lon").trim());   
        end_lat = Double.valueOf(prop.getProperty("end_lat").trim());
        thread = Integer.valueOf(prop.getProperty("requestThread").trim());
        _maxZoom = Integer.valueOf(prop.getProperty("maxZoom").trim());
        _minZoom = Integer.valueOf(prop.getProperty("minZoom").trim());
        _path = prop.getProperty("save_path").trim();     
        _wp_type = Integer.valueOf(prop.getProperty("wp_type").trim());
        defultBGColor = prop.getProperty("defultBGColor").trim();
	}
	
	public static Double getBegin_lon() {
		return begin_lon;
	}
	public static Double getBegin_lat() {
		return begin_lat;
	}
	public static Double getEnd_lon() {
		return end_lon;
	}
	public static Double getEnd_lat() {
		return end_lat;
	}
	public static Integer getThread() {
		return thread;
	}
	public static String getPath() {
		return _path;
	}

	public static Integer getMaxZoom() {
		return _maxZoom;
	}

	public static Integer getMinZoom() {
		return _minZoom;
	}

	public static Integer get_wp_type() {
		return _wp_type;
	}

	public static String getDefultBGColor() {
		return defultBGColor;
	}
	
	
}
