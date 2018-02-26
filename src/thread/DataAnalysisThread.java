package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import entity.BuildingLabel;
import entity.Poi;
import entity.PoiLabel;
import entity.Region;
import entity.Road;
import entity.Roadlabel;
import entity.TileData;
import test.Out;
import utils.ConvertUtils;
import utils.HttpGetUtils;
import utils.PathConstants;
import utils.PropertiesUtils;

/**
 * 瓦片数据下载解析线程
 * @author liushuangyuan
 * @version 1.1
 */
public class DataAnalysisThread implements Runnable {
	
	private BlockingQueue<Poi> importQueue;
	
	private BlockingQueue<TileData> exportQueue;
	
	private Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	private String defultBGColor = PropertiesUtils.getDefultBGColor(); 
	
	public DataAnalysisThread(BlockingQueue<Poi> importQueue, BlockingQueue<TileData> exportQueue){
		this.importQueue = importQueue;
		this.exportQueue = exportQueue;
	}
	
	public void setImportQueue(BlockingQueue<Poi> importQueue) {
		this.importQueue = importQueue;
	}

	public void setExportQueue(BlockingQueue<TileData> exportQueue) {
		this.exportQueue = exportQueue;
	}


	@Override
	public void run() {
		HttpGetUtils utils = new HttpGetUtils();
		while(true){			
			try {
				Poi poi = importQueue.take();
				//取得瓦片URL
				Integer[] ratio = ConvertUtils.getOffsetTile(poi.getX(), poi.getY(), poi.getZ());
				if (ratio == null) {
					continue;
				}
				String path = String.format(PathConstants.TILEDATAPATH, poi.getZ(), ratio[0], ratio[1]);
				String path2 = String.format(PathConstants.LIMGDATAPATH, poi.getZ(), ratio[0], ratio[1], poi.getZ());
				//获取瓦片数据
				String result = null;
				String result2 = null;
				while(true){
					try{
						result = utils.sendGet(path);
//						result2 = utils.sendGet(path2);
					}catch(Exception e1){
						System.out.println(poi.getX()+","+poi.getY()+","+poi.getZ()+" 网络拥堵,重试！");
						continue;
					}
					break;
				}					
				//解析response
				String[] r = result.split("\\|");
				TileData tileData = new TileData(
						poi.getX(), poi.getY(), poi.getZ(),
						gatRoadLable(r[1]),
						getPoiLable(r[2]),
						getBuildingLable(r[3]),
						getRegion(r[4]),
						getRoad(r[5]));
				exportQueue.put(tileData);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}		
	}
	
	
	/**
	 * 获取道路数据
	 * @param roadlabelStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Roadlabel> gatRoadLable(String roadlabelStr){
		List<Roadlabel> roadList = new ArrayList<Roadlabel>();
		List<Object> root=g.fromJson(roadlabelStr,new TypeToken<List<Object>>(){}.getType());
		for(int i=1;i<root.size();i++){
			Roadlabel roadlabel = new Roadlabel();
			List<Object> roads = (List<Object>)root.get(i);
			List<Object> road = (List<Object>) ((List<Object>)roads).get(0);
			String[] sub =  ((String)((List<Object>)roads).get(1)).split("&");
			int num = 0;
			for(Object o:road){
				List<Integer> xy = ConvertUtils.getData(((List<String>)o).get(1));
				roadlabel.getX().add(xy.get(0));
				roadlabel.getY().add(xy.get(1));
				Object ag = ((List<Object>)o).get(2);
				if("".equals(ag)){
					roadlabel.getAngle().add(0);
				}else{
					roadlabel.getAngle().add(Double.valueOf(String.valueOf(ag)).intValue());
				}
				roadlabel.getValue().add(((List<String>)o).get(0));
				
				roadlabel.setColor("#"+sub[2].substring(2));
				if(sub.length>3){
					if(!"".equals(sub[3]) && sub[3]!=null){
						roadlabel.setBoundColor("#"+sub[3].substring(2));
					}				
				}				
				roadlabel.setFontSize(Integer.valueOf(sub[1]));
				num++;
			}
			roadlabel.setNum(num);
			roadList.add(roadlabel);
		}
		
		return roadList;
	}
	
	/**
	 * 获取poi数据
	 * @param poilabelStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<PoiLabel> getPoiLable(String poilabelStr){		
		List<PoiLabel> poiList = new ArrayList<PoiLabel>();
		List<Object> root=g.fromJson(poilabelStr,new TypeToken<List<Object>>(){}.getType());
		for(int i=1;i<root.size();i++){
			
			List<Object> pois = (List<Object>)root.get(i);
			List<List<Object>> poi = (List<List<Object>>) pois.get(0);
			String[] sub = ((String)pois.get(1)).split("&");
			for(List<Object> o:poi){
				List<Integer> xy = ConvertUtils.getData((String) o.get(1));
				PoiLabel poiLabel = new PoiLabel();
				if(sub[2].length()>6){
					poiLabel.setColor("#"+sub[2].substring(2));
				}else{
					poiLabel.setColor("#FFFFFF");
				}
				if(sub.length>3){
					if(!"".equals(sub[3]) && sub[3]!=null && sub[3].length()>6){
						poiLabel.setBoundColor("#"+sub[3].substring(2));
					}				
				}			
				poiLabel.setFontSize(Integer.valueOf(sub[1]));
				if("1".equals(sub[5]) && !"".equals(sub[0])){
					poiLabel.setType("10" + sub[0]);
				}else{
					poiLabel.setType(sub[0]);
				}				
				poiLabel.setX(xy.get(0));
				poiLabel.setY(xy.get(1));
				String[] value = ((String) o.get(0)).split("\\^");
				for(int a=0;a<value.length;a++){
					poiLabel.getValue()[a] = value[a];
				}
				if("".equals(o.get(2))){
					poiLabel.setLable(false);
				}else{					
					int c=0;
					for(int b=0;b<value.length;b++){
						List<Double> lableArray = (List<Double>) ((List<Object>) o.get(2)).get(b);
						for(int j=0;j<4;j++){
							poiLabel.getLable()[c] = lableArray.get(j).intValue();
							c++;
						}
					}
					
				}
				if(o.get(3).getClass().toString().contains("String") && "".equals(o.get(3))){
					poiLabel.setIcon(false);
				}
				poiList.add(poiLabel);
			}						
		}	
		return poiList;
	}
	
	/**
	 * 获取3D建筑数据
	 * @param buildinglabelStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<BuildingLabel> getBuildingLable(String buildinglabelStr){
		List<BuildingLabel> Buildinglist = new ArrayList<BuildingLabel>();
		List<Object> root=g.fromJson(buildinglabelStr,new TypeToken<List<Object>>(){}.getType());
		for(int i=1;i<root.size();i++){
			
			List<Object> pois = (List<Object>)root.get(i);
			
			String[] sub = ((String)pois.get(1)).split("&");
			
//			int diff_x = ("".equals((String.valueOf(pois.get(2)))))?0:((Double)pois.get(2)).intValue();
			int diff_y = ("".equals((String.valueOf(pois.get(3)))))?0:((Double)pois.get(3)).intValue();
			int diff_x = diff_y;	
			List<String> buildpois =  (List<String>) pois.get(0);
			for(String bpoi:buildpois){
				List<Integer> xy = ConvertUtils.getData(bpoi);
				Integer[] bx = new Integer[xy.size()/2];
				Integer[] by = new Integer[xy.size()/2];
				Integer[] sx = new Integer[xy.size()/2];
				Integer[] sy = new Integer[xy.size()/2];
				for(int bi=0;bi<xy.size();bi=bi+2){
					sx[bi/2]=xy.get(bi)*2;
					sy[bi/2]=xy.get(bi+1)*2;
				}
							
				for(int bi=0;bi<sx.length;bi++){
					bx[bi] = sx[bi]-diff_x/4;
					by[bi] = sy[bi]-diff_y/4;
				}
				BuildingLabel buildingLabel = new BuildingLabel();
				buildingLabel.setX(bx);
				buildingLabel.setY(by);
				buildingLabel.setShadow_x(sx);
				buildingLabel.setShadow_y(sy);
				buildingLabel.setEdgeColor("#"+sub[2].substring(2));
				buildingLabel.setFillColor("#"+sub[0].substring(2));
				buildingLabel.setAlpha((float)(Integer.parseInt(sub[0].substring(0,2), 16)));
				Buildinglist.add(buildingLabel);	
			}													
		}	
		return Buildinglist;
	}
	
	@SuppressWarnings("unchecked")
	private List<Region> getRegion(String regionStr){
		List<Object> root=g.fromJson(regionStr,new TypeToken<List<Object>>(){}.getType());
		List<Region> regionList = new ArrayList<Region>();
		for(int i=root.size()-1;i>0;i--){
			Region rg = new Region();
			List<Object> areas = (List<Object>)root.get(i);
			List<String> pois = (List<String>) areas.get(0);			
			List<List<Integer>> xs = new ArrayList<List<Integer>>(pois.size());
			List<List<Integer>> ys = new ArrayList<List<Integer>>(pois.size());
			for(String poi : pois){
				List<Integer> xy = ConvertUtils.getData(poi);
				List<Integer> x = new ArrayList<Integer>();
				List<Integer> y = new ArrayList<Integer>();
				for(int pinx=0; pinx<xy.size(); pinx+=2){
					x.add(xy.get(pinx)*2);
					y.add((xy.get(pinx+1))*2);
				}
				x.add(xy.get(0)*2);
				y.add((xy.get(1))*2);
				xs.add(x);
				ys.add(y);
			}
			rg.setX(xs);
			rg.setY(ys);
			
			String[] colors = ((String)areas.get(1)).split("&", -1);
			if(colors.length>1){
				if(colors[1].length()>2){
					rg.setEdgeColor("#"+colors[1].substring(2));
				}				
				rg.setFillColor("#"+colors[0].substring(2));				
			}else{
				rg.setEdgeColor("#"+colors[0].substring(2));
				rg.setFillColor("#"+colors[0].substring(2));
			}
			rg.setAlpha((float)(Integer.parseInt(colors[0].substring(0,2), 16)));
			regionList.add(rg);
		}
		if(!checkBackGround(regionList)){
			Region rg = new Region();
			rg.setFillColor(defultBGColor);
			List<List<Integer>> xs = new ArrayList<List<Integer>>(5);
			List<List<Integer>> ys = new ArrayList<List<Integer>>(5);
			List<Integer> x = new ArrayList<Integer>();
			List<Integer> y = new ArrayList<Integer>();
			x.add(0);x.add(512);x.add(512);x.add(0);x.add(0);
			y.add(0);y.add(0);y.add(512);y.add(512);y.add(0);
			xs.add(x);ys.add(y);
			rg.setX(xs);
			rg.setY(ys);
			regionList.add(0, rg);
		}
		return regionList;
	}
	
	private boolean checkBackGround(List<Region> rglist){
		if(rglist.size()==0){
			return false;
		}
		Region rg = rglist.get(0);
		List<List<Integer>> xs = rg.getX();
		List<List<Integer>> ys = rg.getY();
		for(int i=0; i<xs.size(); i++){
			List<Integer> x = xs.get(i);
			List<Integer> y = ys.get(i);
			if(x.size()!=5 || y.size()!=5){
				continue;
			}
			if(x.get(0)!=0 || x.get(1)!=512 || x.get(2)!=512 || x.get(3)!=0 || x.get(4)!=0){
				continue;
			}
			if(y.get(0)!=0 || y.get(1)!=0 || y.get(2)!=512 || y.get(3)!=512 || y.get(4)!=0){
				continue;
			}
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private List<Road> getRoad(String roadStr){
		List<Object> root=g.fromJson(roadStr,new TypeToken<List<Object>>(){}.getType());
		List<Road> regionList = new ArrayList<Road>();
		for(int i=1;i<root.size();i++){
			Road rd = new Road();
			List<Object> areas = (List<Object>)root.get(i);
			List<String> pois = (List<String>) areas.get(0);			
			List<List<Integer>> xs = new ArrayList<List<Integer>>(pois.size());
			List<List<Integer>> ys = new ArrayList<List<Integer>>(pois.size());
			for(String poi : pois){
				List<Integer> xy = ConvertUtils.getData(poi);
				List<Integer> x = new ArrayList<Integer>();
				List<Integer> y = new ArrayList<Integer>();
				for(int pinx=0; pinx<xy.size(); pinx+=2){
					x.add(xy.get(pinx)*2);
					y.add((xy.get(pinx+1))*2);
				}
				xs.add(x);
				ys.add(y);
			}
			rd.setX(xs);
			rd.setY(ys);
			
			String[] colors = ((String)areas.get(1)).split("&", -1);
			rd.setEdgeWidth(getWidth(colors[3]) * 2);
			rd.setEdgeColor(getColorStr(colors[4]));
			rd.setRoadWidth(getWidth(colors[0]) * 2);
			rd.setFillColor(getColorStr(colors[1]));
			if(colors.length>2){
				String type = colors[2];
				if(type.startsWith("dash")){
					rd.setRoadType("dash");
					type = type.replace("dash(", "").replace(")", "");
					String[] tparm = type.split(",");
					if(tparm.length>1){
						rd.setRoadDashParam(new float[]{Float.valueOf(tparm[0]), (Float.valueOf(tparm[1]) * 2)});
					}
				} else if (type.startsWith("solid")){
					rd.setRoadType("solid");
				} else {
					rd.setRoadType(type);
				}
			}	
			
			if(colors.length>5){
				String type = colors[5];
				if(type.startsWith("dash")){
					rd.setEdgeType("dash");
					type = type.replace("dash(", "").replace(")", "");
					String[] tparm = type.split(",");
					if(tparm.length>1){
						rd.setEdgeDashParam(new float[]{Float.valueOf(tparm[0]), (Float.valueOf(tparm[1]) * 2)});
					}
				} else if(type.startsWith("solid")){
					rd.setEdgeType("solid");
				} else {
					rd.setEdgeType(type);
				}
			}		
			rd.setRoadLevel(areas.get(3).toString().replace("roads:", ""));
			regionList.add(rd);
		}
		return regionList;
	}
	
	private String getColorStr(String color){
		if(StringUtils.isBlank(color)){
			return null;
		}
		if(color.length()==8){
			return "#"+color.substring(2);
		}else{
			return color;
		}
	}
	
	private Float getWidth(String width){
		if(StringUtils.isBlank(width)){
			return 0F;
		}
		return Float.valueOf(width);
	}

}
