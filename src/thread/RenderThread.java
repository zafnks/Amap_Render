package thread;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import entity.BuildingLabel;
import entity.PoiLabel;
import entity.Region;
import entity.Road;
import entity.Roadlabel;
import entity.TileData;
import utils.IconUtils;
import utils.PrintUtils;
import utils.RoadThickUtils;

public class RenderThread implements Runnable {

	private BlockingQueue<TileData> importQueue;

	private BlockingQueue<TileData> exportQueue;
	
	public RenderThread(BlockingQueue<TileData> importQueue, BlockingQueue<TileData> exportQueue){
		this.importQueue = importQueue;
		this.exportQueue = exportQueue;
	}
	
	public BlockingQueue<TileData> getImportQueue() {
		return importQueue;
	}

	public void setImportQueue(BlockingQueue<TileData> importQueue) {
		this.importQueue = importQueue;
	}

	public BlockingQueue<TileData> getExportQueue() {
		return exportQueue;
	}

	public void setExportQueue(BlockingQueue<TileData> exportQueue) {
		this.exportQueue = exportQueue;
	}



	@Override
	public void run() {
		
		while(true){
			try {
				TileData tileData = importQueue.take();
				Integer z = tileData.getZ();
				List<Roadlabel> roadlabelArray = tileData.getRoadlabelArray();
				List<PoiLabel> poiLabelArray = tileData.getPoiLableArray();
				List<BuildingLabel> buildingLabelArray = tileData.getBuildingLabelArray();
				List<Region> regionArray = tileData.getRegionArray();
				List<Road> roadArray = tileData.getRoadArray();
				
				//创建底图(512px X 512px)
				//不直接使用瓦片当底图，瓦片的色域不一定是标准的
				//不标准的色域会产生严重的色偏
				BufferedImage bi = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB);
				//创建渲染工具
				PrintUtils printUtils = new PrintUtils(bi);
				
				//渲染区域
				for(Region rg : regionArray){
					int lth = rg.getX().size();
					for(int i=0; i<lth; i++){
						List<Integer> xs = rg.getX().get(i);
						List<Integer> ys = rg.getY().get(i);
						printUtils.printPolygon(xs.toArray(new Integer[]{}), ys.toArray(new Integer[]{}), null, rg.getFillColor(),1f);
					}
				}
				//渲染道路边框
				for(Road rd : roadArray){
					int lth = rd.getX().size();
					for(int i=0; i<lth; i++){
						List<Integer> xs = rd.getX().get(i);
						List<Integer> ys = rd.getY().get(i);
						Integer[] xarray = xs.toArray(new Integer[]{});
						Integer[] yarray = ys.toArray(new Integer[]{});
						if(rd.getEdgeColor()!=null){					
							try{
								String type = rd.getEdgeType();
								Float thick = rd.getRoadWidth() + rd.getEdgeWidth();
								if("solid".equals(type)){
									printUtils.drawLine(xarray, yarray, rd.getEdgeColor(), thick);
								}else if("dash".equals(type)){
									printUtils.drawDash(xarray, yarray, rd.getEdgeColor(), thick, rd.getEdgeDashParam());
								}
								
							}catch(Exception e1){
								e1.printStackTrace();
							}
						}
					}
				}
				//渲染道路中心
				for (Road rd : roadArray) {
					int lth = rd.getX().size();
					for (int i = 0; i < lth; i++) {
						List<Integer> xs = rd.getX().get(i);
						List<Integer> ys = rd.getY().get(i);
						Integer[] xarray = xs.toArray(new Integer[] {});
						Integer[] yarray = ys.toArray(new Integer[] {});
						String type = rd.getRoadType();
						if ("solid".equals(type)) {
							printUtils.drawLine(xarray, yarray, rd.getFillColor(), rd.getRoadWidth());
						} else if ("dash".equals(type)) {
							printUtils.drawDash(xarray, yarray, rd.getFillColor(), rd.getRoadWidth(), rd.getRoadDashParam());
						}
					}

				}
				
				/**
				 * 渲染3D建筑
				 * 由底层像上方渲染
				 */
				/*boolean ifRenderNextTile = false;
				for(BuildingLabel buiding :BuildingData){
					//判断是否与y+1瓦片做联合渲染
					ifRenderNextTile = (ifRenderNextTile || ifHasBottomY(buiding.getShadow_y()));
					//渲染阴影
					printUtils.printPolygon(buiding.getShadow_x(), buiding.getShadow_y(), null, buiding.getEdgeColor(),0.37f);
					//渲染立体部分
					for(int i=0;i<buiding.getShadow_x().length-1;i++){
						if(buiding.getShadow_x()[i].equals(buiding.getShadow_x()[i+1])){
							continue;
						}
						if(buiding.getShadow_y()[i].equals(buiding.getShadow_y()[i+1])){
							continue;
						}
						//计算立体区域坐标
						int[] z_x = new int[]{buiding.getShadow_x()[i],buiding.getX()[i],buiding.getX()[i+1],buiding.getShadow_x()[i+1],buiding.getShadow_x()[i]};
						int[] z_y = new int[]{buiding.getShadow_y()[i],buiding.getY()[i],buiding.getY()[i+1],buiding.getShadow_y()[i+1],buiding.getShadow_y()[i]};
						printUtils.printPolygon(z_x, z_y, buiding.getEdgeColor(), buiding.getEdgeColor(),0.37f);
					}
					//渲染顶部
					printUtils.printPolygon(buiding.getX(), buiding.getY(), null, buiding.getFillColor(),0.37f);
				}*/

				/**
				 * 渲染y+1瓦片的3D建筑
				 * 为解决建筑跨瓦片做联合渲染
				 * 由底层像上方渲染
				 */
				/*if(ifRenderNextTile){
					Object[] aftResult = e.getEle(x, y+1, z);
					for(BuildingLabel buiding :(List<BuildingLabel>)aftResult[2]){
						//y坐标从512开始，x坐标不变
						addDiff(buiding.getShadow_y(),+512);
						addDiff(buiding.getY(),+512);
						//渲染阴影
						printUtils.printPolygon(buiding.getShadow_x(), buiding.getShadow_y(), null, buiding.getEdgeColor(),0.37f);
						//渲染顶部
						printUtils.printPolygon(buiding.getX(), buiding.getY(), null, buiding.getFillColor(),0.37f);
					}
				}	*/	
				
				//渲染道路名
				for(Roadlabel road :roadlabelArray){		
					for(int i=0;i < road.getNum();i++){
						printUtils.printFont(road.getValue().get(i), road.getFontSize() * 2-2, road.getColor(),road.getBoundColor(), road.getX().get(i) * 2-9 , road.getY().get(i) * 2+6, road.getAngle().get(i));
					}
				}

				//渲染poi点位
				for(PoiLabel poi :poiLabelArray){
					//判断是否渲染poi图标
					//公寓栋数等不渲染icon
					if(poi.isIcon() && poi.getType()!=null && !"".equals(poi.getType())){
						printUtils.printImage(IconUtils.getIcon(poi.getType()), (poi.getX())*2-15, (poi.getY())*2-15);
					}
					for(int i=0;i<poi.getValue().length;i++){
						if(poi.getValue()[i]==null){
							break;
						}
						//判断是否渲染poi标签
						//公车站、ATM、WC等不进行渲染
						if(poi.isLable()){
							printUtils.printFont(poi.getValue()[i], poi.getFontSize()*2-2, poi.getColor(),poi.getBoundColor(), ((poi.getX()+ poi.getLable()[i*4+0]) * 2 ),((poi.getY() + poi.getLable()[i*4+1]) * 2 )+20, 360);
						}			
					}			
				}
				tileData.setBufferedImage(bi);
				exportQueue.put(tileData);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
/*	private boolean checkSpecialRoad(Road rd){
		String level = rd.getRoadLevel();
		if("railway".equals(level) || "highSpeedRailway".equals(level) || "subwayBeingBuilt".equals(level) || "other".equals(level)){
			return true;
		}
		return false;
	}*/
	
/*	private void printSpecialRoad(PrintUtils printUtils, Road rd, Integer[] xarray, Integer[] yarray, Integer z){
		if("railway".equals(rd.getRoadLevel()) || "highSpeedRailway".equals(rd.getRoadLevel())){
			Float thick = RoadThickUtils.getThick(z, rd.getRoadLevel());
			if (thick <= 0.8F) {
				thick = thick + 1F;
			} else {
				thick = thick + 2F;
			}
			printUtils.drawLine(xarray, yarray, rd.getEdgeColor(), thick);			
			printUtils.drawLine(xarray, yarray, rd.getFillColor(), RoadThickUtils.getThick(z, rd.getRoadLevel()));
			printUtils.drawDash(xarray, yarray, rd.getEdgeColor(), RoadThickUtils.getThick(z, rd.getRoadLevel()));
		}else if("subwayBeingBuilt".equals(rd.getRoadLevel()) || "other".equals(rd.getRoadLevel())){
			printUtils.drawDash(xarray, yarray, rd.getFillColor(), RoadThickUtils.getThick(z, rd.getRoadLevel()));
		}
	}*/
	
	
	private void addDiff(Integer[] arr,Integer diff){
		for(int i=0;i<arr.length;i++){
			arr[i] = arr[i]+diff;
		}
	}
	
	private boolean ifHasBottomY(Integer[] arr){
		for(int i=0;i<arr.length;i++){
			if(arr[i].equals(512)){
				return true;
			}
		}
		return false;
	}

}
