package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import utils.HttpGetUtils;

public class Out {
	
	public static void main(String[] args) {
		String USER_AGENT = "Mozilla/5.0";
		try {
			for(int i=0; i < 1000; i++){
			URL obj = new URL("http://www.gpsspg.com/apis/maps/geo/?output=json&lat=22.9856780000&lng=108.0290090000&type=5&callback=jQuery110208888189534852657_1503813510958&_=1503813510961");
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);
//			con.setRequestProperty("Accept","text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01");
//			con.setRequestProperty("Accept-Encoding", "gzip, deflate");
			/*con.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			con.setRequestProperty("Connection", "keep-alive");
			con.setRequestProperty("Cookie", "ARRAffinity=eba38b5e5d37ec663e7806a1cf1faf3885142effc9a0b19be7a1b507f5b220db; Hm_lvt_15b1a40a8d25f43208adae1c1e12a514=1503813504; Hm_lpvt_15b1a40a8d25f43208adae1c1e12a514=1503813512; AJSTAT_ok_pages=2; AJSTAT_ok_times=4");
			con.setRequestProperty("Host", "www.gpsspg.com");*/
			con.setRequestProperty("Referer", "http://www.gpsspg.com/iframe/maps/amap_161129.htm?mapi=3");
//			con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			/*con.setRequestProperty("X-Forwarded-For","110.110.12.11");
			con.setRequestProperty("HTTP_X_FORWARDED_FOR","110.110.12.11");
			con.setRequestProperty("HTTP_CLIENT_IP","110.110.12.11");
			con.setRequestProperty("REMOTE_ADDR","110.110.12.11");*/

			con.getResponseCode();
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream(),"UTF-8"));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(i + ":" + response.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main1(String[] args) throws Exception {
		HttpGetUtils utils = new HttpGetUtils();
		for(int i=0; i < 5000; i++){
			System.out.println(i + ":" + utils.sendGet("http://restapi.amap.com/v3/geocode/regeo?key=169d2dd7829fe45690fabec812d05bc3&s=rsv3&location=108.31143,22.839018&callback=jsonp_314379_&platform=JS&logversion=2.0&sdkversion=1.3&appname=http%3A%2F%2Fwww.gpsspg.com%2Fiframe%2Fmaps%2Famap_161128.htm%3Fmapi%3D3&csid=DE528677-9542-4420-B902-55591EDCBB71"));
		}
		
	}

	private static int i = 0;
	
	
	public synchronized static void setI(int x, int y, int c){
		if (i != c) {
			method1("x:" + x + "  y:" + y + "   c:" + c + "\r\n");
			i = c;
		}
	}
	
	
	public static void method1(String conent) {     
        BufferedWriter out = null;     
        try {     
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\123.txt", true)));     
            out.write(conent);     
        } catch (Exception e) {     
            e.printStackTrace();     
        } finally {     
            try {     
                if(out != null){  
                    out.close();     
                }  
            } catch (IOException e) {     
                e.printStackTrace();     
            }     
        }     
    }     
}
