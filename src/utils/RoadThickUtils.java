package utils;

import java.util.HashMap;
import java.util.Map;

public class RoadThickUtils {
	
	private static final Float _WIDTH = 20F;
	
	private static Map<Integer, Map<String,Float>> thickMap = new HashMap<Integer, Map<String, Float>>();
	
	private static Float Double2Float(Double d){
		return Float.parseFloat(String.valueOf(d));
	}
	
	static{
		//18
		Map<String,Float> z18 = new HashMap<String, Float>();		
		z18.put("levelFourRoad",Double2Float(_WIDTH*0.35));
		z18.put("levelThreeRoad",Double2Float(_WIDTH*0.65));
		z18.put("secondaryRoad",Double2Float(_WIDTH*1.05));
		z18.put("nationalRoad",Double2Float(_WIDTH*1.05));
		z18.put("provincialRoad",Double2Float(_WIDTH*1.0));		
		z18.put("highWay",Double2Float(_WIDTH*1.5));
		z18.put("ringRoad",Double2Float(_WIDTH*0.65));		
		z18.put("roadsBeingBuilt",Double2Float(_WIDTH*0.35));		
		z18.put("water",1F);
		z18.put("borders:provincial",8F);
		z18.put("borders:China",10F);
		z18.put("borders:foreign",10F);
		z18.put("subway",2F);	
		z18.put("railway",Double2Float(_WIDTH*0.2));
		z18.put("highSpeedRailway",Double2Float(_WIDTH*0.2));
		z18.put("subwayBeingBuilt",Double2Float(_WIDTH*0.2));
		z18.put("overPass",Double2Float(_WIDTH*0.65));
		z18.put("other",0.5F);
		z18.put("underPass",30F);		
		thickMap.put(18, z18);
		
		//17
		Map<String,Float> z17 = new HashMap<String, Float>();
		z17.put("levelFourRoad",Double2Float(_WIDTH*0.3));
		z17.put("levelThreeRoad",Double2Float(_WIDTH*0.6));
		z17.put("secondaryRoad",Double2Float(_WIDTH*1.0));
		z17.put("nationalRoad",Double2Float(_WIDTH*1.0));
		z17.put("provincialRoad",Double2Float(_WIDTH*0.95));		
		z17.put("highWay",Double2Float(_WIDTH*1.5));
		z17.put("ringRoad",Double2Float(_WIDTH*0.6));		
		z17.put("roadsBeingBuilt",Double2Float(_WIDTH*0.3));		
		z17.put("water",1F);
		z17.put("borders:provincial",8F);
		z17.put("borders:China",10F);	
		z17.put("borders:foreign",10F);
		z17.put("subway",2F);	
		z17.put("railway",Double2Float(_WIDTH*0.2));
		z17.put("highSpeedRailway",Double2Float(_WIDTH*0.2));
		z17.put("subwayBeingBuilt",Double2Float(_WIDTH*0.2));
		z17.put("overPass",Double2Float(_WIDTH*0.6));
		z17.put("other",0.5F);
		thickMap.put(17, z17);
		
		//16
		Map<String,Float> z16 = new HashMap<String, Float>();
		z16.put("levelFourRoad",Double2Float(_WIDTH*0.2));
		z16.put("levelThreeRoad",Double2Float(_WIDTH*0.4));
		z16.put("secondaryRoad",Double2Float(_WIDTH*0.55));
		z16.put("nationalRoad",Double2Float(_WIDTH*0.55));
		z16.put("provincialRoad",Double2Float(_WIDTH*0.55));		
		z16.put("highWay",Double2Float(_WIDTH*1.0));
		z16.put("ringRoad",Double2Float(_WIDTH*0.65));		
		z16.put("roadsBeingBuilt",Double2Float(_WIDTH*0.2));		
		z16.put("water",1F);
		z16.put("borders:provincial",8F);	
		z16.put("borders:China",10F);
		z16.put("borders:foreign",10F);
		z16.put("subway",2F);	
		z16.put("railway",Double2Float(_WIDTH*0.2));
		z16.put("highSpeedRailway",Double2Float(_WIDTH*0.2));
		z16.put("subwayBeingBuilt",Double2Float(_WIDTH*0.2));
		z16.put("overPass",Double2Float(_WIDTH*0.4));
		z16.put("other",0.5F);
		thickMap.put(16, z16);
		
		//15
		Map<String,Float> z15 = new HashMap<String, Float>();
		z15.put("levelFourRoad",Double2Float(_WIDTH*0.15));
		z15.put("levelThreeRoad",Double2Float(_WIDTH*0.25));
		z15.put("secondaryRoad",Double2Float(_WIDTH*0.5));
		z15.put("nationalRoad",Double2Float(_WIDTH*0.5));
		z15.put("provincialRoad",Double2Float(_WIDTH*0.5));		
		z15.put("highWay",Double2Float(_WIDTH*0.95));
		z15.put("ringRoad",Double2Float(_WIDTH*0.6));		
		z15.put("roadsBeingBuilt",Double2Float(_WIDTH*0.15));		
		z15.put("water",1F);
		z15.put("borders:provincial",8F);	
		z15.put("borders:China",10F);
		z15.put("borders:foreign",10F);
		z15.put("subway",2F);	
		z15.put("railway",Double2Float(_WIDTH*0.2));
		z15.put("highSpeedRailway",Double2Float(_WIDTH*0.2));
		z15.put("subwayBeingBuilt",Double2Float(_WIDTH*0.2));
		z15.put("overPass",Double2Float(_WIDTH*0.25));
		z15.put("other",0.5F);
		thickMap.put(15, z15);
		
		//14
		Map<String,Float> z14 = new HashMap<String, Float>();
		z14.put("levelFourRoad",Double2Float(_WIDTH*0.025));
		z14.put("levelThreeRoad",Double2Float(_WIDTH*0.15));
		z14.put("secondaryRoad",Double2Float(_WIDTH*0.3));
		z14.put("nationalRoad",Double2Float(_WIDTH*0.5));
		z14.put("provincialRoad",Double2Float(_WIDTH*0.5));		
		z14.put("highWay",Double2Float(_WIDTH*0.95));
		z14.put("ringRoad",Double2Float(_WIDTH*0.6));		
		z14.put("roadsBeingBuilt",Double2Float(_WIDTH*0.15));		
		z14.put("water",1F);
		z14.put("borders:provincial",8F);	
		z14.put("borders:China",10F);
		z14.put("borders:foreign",10F);
		z14.put("subway",2F);	
		z14.put("railway",Double2Float(_WIDTH*0.2));
		z14.put("highSpeedRailway",Double2Float(_WIDTH*0.2));
		z14.put("subwayBeingBuilt",Double2Float(_WIDTH*0.2));
		z14.put("overPass",Double2Float(_WIDTH*0.15));
		z14.put("other",0.5F);
		thickMap.put(14, z14);
		
		//13
		Map<String,Float> z13 = new HashMap<String, Float>();
		z13.put("levelFourRoad",Double2Float(_WIDTH*0.025));
		z13.put("levelThreeRoad",Double2Float(_WIDTH*0.15));
		z13.put("secondaryRoad",Double2Float(_WIDTH*0.3));
		z13.put("nationalRoad",Double2Float(_WIDTH*0.4));
		z13.put("provincialRoad",Double2Float(_WIDTH*0.4));		
		z13.put("highWay",Double2Float(_WIDTH*0.95));
		z13.put("ringRoad",Double2Float(_WIDTH*0.5));		
		z13.put("roadsBeingBuilt",Double2Float(_WIDTH*0.15));		
		z13.put("water",1F);
		z13.put("borders:provincial",8F);	
		z13.put("borders:China",10F);
		z13.put("borders:foreign",10F);
		z13.put("subway",2F);
		z13.put("railway",Double2Float(_WIDTH*0.2));
		z13.put("highSpeedRailway",Double2Float(_WIDTH*0.2));
		z13.put("subwayBeingBuilt",Double2Float(_WIDTH*0.2));
		z13.put("overPass",Double2Float(_WIDTH*0.15));
		z13.put("other",0.5F);
		thickMap.put(13, z13);
		
		//12
		Map<String,Float> z12 = new HashMap<String, Float>();
		z12.put("levelFourRoad",Double2Float(_WIDTH*0.02));
		z12.put("levelThreeRoad",Double2Float(_WIDTH*0.10));
		z12.put("secondaryRoad",Double2Float(_WIDTH*0.25));
		z12.put("nationalRoad",Double2Float(_WIDTH*0.35));
		z12.put("provincialRoad",Double2Float(_WIDTH*0.35));		
		z12.put("highWay",Double2Float(_WIDTH*0.7));
		z12.put("ringRoad",Double2Float(_WIDTH*0.4));		
		z12.put("roadsBeingBuilt",Double2Float(_WIDTH*0.1));		
		z12.put("water",1F);
		z12.put("borders:provincial",8F);	
		z12.put("borders:China",10F);
		z12.put("borders:foreign",10F);
		z12.put("subway",2F);		
		z12.put("railway",Double2Float(_WIDTH*0.2));
		z12.put("highSpeedRailway",Double2Float(_WIDTH*0.2));
		z12.put("subwayBeingBuilt",Double2Float(_WIDTH*0.2));
		z12.put("overPass",Double2Float(_WIDTH*0.10));
		z12.put("other",0.5F);
		thickMap.put(12, z12);
		
		//11
		Map<String,Float> z11 = new HashMap<String, Float>();
		z11.put("levelFourRoad",Double2Float(_WIDTH*0.02));
		z11.put("levelThreeRoad",Double2Float(_WIDTH*0.02));
		z11.put("secondaryRoad",Double2Float(_WIDTH*0.25));
		z11.put("nationalRoad",Double2Float(_WIDTH*0.25));
		z11.put("provincialRoad",Double2Float(_WIDTH*0.25));		
		z11.put("highWay",Double2Float(_WIDTH*0.25));
		z11.put("ringRoad",Double2Float(_WIDTH*0.25));		
		z11.put("roadsBeingBuilt",Double2Float(_WIDTH*0.02));		
		z11.put("water",1F);
		z11.put("borders:provincial",8F);	
		z11.put("borders:China",10F);
		z11.put("borders:foreign",10F);
		z11.put("subway",2F);	
		z11.put("railway",Double2Float(_WIDTH*0.2));
		z11.put("highSpeedRailway",Double2Float(_WIDTH*0.2));
		z11.put("subwayBeingBuilt",Double2Float(_WIDTH*0.2));
		z11.put("overPass",Double2Float(_WIDTH*0.02));
		z11.put("other",0.5F);
		thickMap.put(11, z11);
		
		
		//10
		Map<String,Float> z10 = new HashMap<String, Float>();
		z10.put("levelFourRoad",Double2Float(_WIDTH*0.02));
		z10.put("levelThreeRoad",Double2Float(_WIDTH*0.02));
		z10.put("secondaryRoad",Double2Float(_WIDTH*0.02));
		z10.put("nationalRoad",Double2Float(_WIDTH*0.02));
		z10.put("provincialRoad",Double2Float(_WIDTH*0.02));		
		z10.put("highWay",Double2Float(_WIDTH*0.25));
		z10.put("ringRoad",Double2Float(_WIDTH*0.25));		
		z10.put("roadsBeingBuilt",Double2Float(_WIDTH*0.02));		
		z10.put("water",1F);
		z10.put("borders:provincial",8F);	
		z10.put("borders:China",10F);
		z10.put("borders:foreign",10F);
		z10.put("subway",2F);		
		z10.put("railway",Double2Float(_WIDTH*0.2));
		z10.put("highSpeedRailway",Double2Float(_WIDTH*0.2));
		z10.put("subwayBeingBuilt",Double2Float(_WIDTH*0.2));
		z10.put("overPass",Double2Float(_WIDTH*0.02));
		z10.put("other",0.5F);
		thickMap.put(10, z10);
		
		//9
		Map<String,Float> z9 = new HashMap<String, Float>();
		z9.put("levelFourRoad",Double2Float(_WIDTH*0.02));
		z9.put("levelThreeRoad",Double2Float(_WIDTH*0.02));
		z9.put("secondaryRoad",Double2Float(_WIDTH*0.02));
		z9.put("nationalRoad",Double2Float(_WIDTH*0.1));
		z9.put("provincialRoad",Double2Float(_WIDTH*0.1));		
		z9.put("highWay",Double2Float(_WIDTH*0.25));
		z9.put("ringRoad",Double2Float(_WIDTH*0.25));		
		z9.put("roadsBeingBuilt",Double2Float(_WIDTH*0.02));		
		z9.put("water",1F);
		z9.put("borders:provincial",8F);	
		z9.put("borders:China",9F);
		z9.put("borders:foreign",9F);
		z9.put("subway",2F);	
		z9.put("railway",Double2Float(_WIDTH*0.2));
		z9.put("highSpeedRailway",Double2Float(_WIDTH*0.2));
		z9.put("subwayBeingBuilt",Double2Float(_WIDTH*0.2));
		z9.put("overPass",Double2Float(_WIDTH*0.02));
		z9.put("other",0.5F);
		thickMap.put(9, z9);
		
		// 8
		Map<String, Float> z8 = new HashMap<String, Float>();
		z8.put("levelFourRoad", Double2Float(_WIDTH * 0.02));
		z8.put("levelThreeRoad", Double2Float(_WIDTH * 0.02));
		z8.put("secondaryRoad", Double2Float(_WIDTH * 0.02));
		z8.put("nationalRoad", Double2Float(_WIDTH * 0.08));
		z8.put("provincialRoad", Double2Float(_WIDTH * 0.08));
		z8.put("highWay", Double2Float(_WIDTH * 0.15));
		z8.put("ringRoad", Double2Float(_WIDTH * 0.15));
		z8.put("roadsBeingBuilt", Double2Float(_WIDTH * 0.02));
		z8.put("water", 1F);
		z8.put("borders:provincial", 4F);
		z8.put("borders:China", 8F);
		z8.put("borders:foreign", 8F);
		z8.put("subway", 2F);
		z8.put("railway",Double2Float(_WIDTH*0.2));
		z8.put("highSpeedRailway",Double2Float(_WIDTH*0.2));
		z8.put("subwayBeingBuilt",Double2Float(_WIDTH*0.2));
		z8.put("overPass",Double2Float(_WIDTH*0.02));
		z8.put("other",0.5F);
		thickMap.put(8, z8);
		
		// 7
		Map<String, Float> z7 = new HashMap<String, Float>();
		z7.put("levelFourRoad", Double2Float(_WIDTH * 0.02));
		z7.put("levelThreeRoad", Double2Float(_WIDTH * 0.02));
		z7.put("secondaryRoad", Double2Float(_WIDTH * 0.02));
		z7.put("nationalRoad", Double2Float(_WIDTH * 0.07));
		z7.put("provincialRoad", Double2Float(_WIDTH * 0.07));
		z7.put("highWay", Double2Float(_WIDTH * 0.15));
		z7.put("ringRoad", Double2Float(_WIDTH * 0.15));
		z7.put("roadsBeingBuilt", Double2Float(_WIDTH * 0.02));
		z7.put("water", 1F);
		z7.put("borders:provincial", 4F);
		z7.put("borders:China", 7F);
		z7.put("borders:foreign", 7F);
		z7.put("subway", 2F);
		z7.put("railway",Double2Float(_WIDTH*0.2));
		z7.put("highSpeedRailway",Double2Float(_WIDTH*0.2));
		z7.put("subwayBeingBuilt",Double2Float(_WIDTH*0.2));
		z7.put("overPass",Double2Float(_WIDTH*0.02));
		z7.put("other",0.5F);
		thickMap.put(7, z7);
		
		// 6
		Map<String, Float> z6 = new HashMap<String, Float>();
		z6.put("levelFourRoad", Double2Float(_WIDTH * 0.02));
		z6.put("levelThreeRoad", Double2Float(_WIDTH * 0.02));
		z6.put("secondaryRoad", Double2Float(_WIDTH * 0.02));
		z6.put("nationalRoad", Double2Float(_WIDTH * 0.06));
		z6.put("provincialRoad", Double2Float(_WIDTH * 0.06));
		z6.put("highWay", Double2Float(_WIDTH * 0.15));
		z6.put("ringRoad", Double2Float(_WIDTH * 0.15));
		z6.put("roadsBeingBuilt", Double2Float(_WIDTH * 0.02));
		z6.put("water", 1F);
		z6.put("borders:provincial", 2.5F);
		z6.put("borders:China", 6F);
		z6.put("borders:foreign", 6F);
		z6.put("subway", 2F);
		z6.put("railway",Double2Float(_WIDTH*0.2));
		z6.put("highSpeedRailway",Double2Float(_WIDTH*0.2));
		z6.put("subwayBeingBuilt",Double2Float(_WIDTH*0.2));
		z6.put("overPass",Double2Float(_WIDTH*0.02));
		z6.put("other",0.5F);
		thickMap.put(6, z6);
		
		// 5
		Map<String, Float> z5 = new HashMap<String, Float>();
		z5.put("levelFourRoad", Double2Float(_WIDTH * 0.02));
		z5.put("levelThreeRoad", Double2Float(_WIDTH * 0.02));
		z5.put("secondaryRoad", Double2Float(_WIDTH * 0.02));
		z5.put("nationalRoad", Double2Float(_WIDTH * 0.05));
		z5.put("provincialRoad", Double2Float(_WIDTH * 0.05));
		z5.put("highWay", Double2Float(_WIDTH * 0.15));
		z5.put("ringRoad", Double2Float(_WIDTH * 0.15));
		z5.put("roadsBeingBuilt", Double2Float(_WIDTH * 0.02));
		z5.put("water", 1F);
		z5.put("borders:provincial", 2.5F);
		z5.put("borders:China", 5F);
		z5.put("borders:foreign", 5F);
		z5.put("subway", 2F);
		z5.put("railway",Double2Float(_WIDTH*0.2));
		z5.put("highSpeedRailway",Double2Float(_WIDTH*0.2));
		z5.put("subwayBeingBuilt",Double2Float(_WIDTH*0.2));
		z5.put("overPass",Double2Float(_WIDTH*0.25));
		z5.put("other",0.5F);
		thickMap.put(5, z5);
	}
	
	
	public static Float getThick(int z, String roadType){
		Float thick = thickMap.get(z).get(roadType);
		return thick;
	}

}
