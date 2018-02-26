package thread;

import java.io.File;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import entity.Poi;
import utils.BaseRatioUtils;
import utils.PropertiesUtils;

/**
 * 根据配置分发瓦片任务
 * @author liushuangyuan
 * @version 1.1
 */
public class TileDistributedThread implements Runnable {
	
	//使用堵塞队列
	private BlockingQueue<Poi> exportQueue;
	
	private AtomicInteger total;
	
	public TileDistributedThread(BlockingQueue<Poi> exportQueue){
		this.exportQueue = exportQueue;
	}
	
	public void setExportQueue(BlockingQueue<Poi> exportQueue) {
		this.exportQueue = exportQueue;
	}	

	public void setTotal(AtomicInteger total) {
		this.total = total;
	}

	@Override
	public void run() {
		
		String path = PropertiesUtils.getPath();
		
		Integer maxZoom = PropertiesUtils.getMaxZoom();
		Integer minZoom = PropertiesUtils.getMinZoom();
		Double X1 = PropertiesUtils.getBegin_lon();
		Double X2 = PropertiesUtils.getEnd_lon();
		Double Y1 = PropertiesUtils.getBegin_lat();
		Double Y2 = PropertiesUtils.getEnd_lat();
		//计算总数
		for(int i=maxZoom;i>=minZoom;i--){
			Integer maxTileX = BaseRatioUtils.getAmapTileX(X1, i);
			Integer maxTileY = BaseRatioUtils.getAmapTileY(Y1, i);
			Integer minTileX = BaseRatioUtils.getAmapTileX(X2, i);
			Integer minTileY = BaseRatioUtils.getAmapTileY(Y2, i);
			if(maxTileX<minTileX){
				Integer t = maxTileX;
				maxTileX = minTileX;
				minTileX = t;
			}
			if(maxTileY<minTileY){
				Integer t = maxTileY;
				maxTileY = minTileY;
				minTileY = t;
			}
			total.addAndGet((maxTileX-minTileX+1)*(maxTileY-minTileY+1));
		}
		for(int i=maxZoom;i>=minZoom;i--){
			File dir = Paths.get(path,String.valueOf(i)).toFile();
			if(!dir.exists()){
				dir.mkdir();
			}
			Integer maxTileX = BaseRatioUtils.getAmapTileX(X1, i);
			Integer maxTileY = BaseRatioUtils.getAmapTileY(Y1, i);
			Integer minTileX = BaseRatioUtils.getAmapTileX(X2, i);
			Integer minTileY = BaseRatioUtils.getAmapTileY(Y2, i);
			
			if(maxTileX<minTileX){
				Integer t = maxTileX;
				maxTileX = minTileX;
				minTileX = t;
			}
			if(maxTileY<minTileY){
				Integer t = maxTileY;
				maxTileY = minTileY;
				minTileY = t;
			}
			
			for(int j = minTileX;j<=maxTileX;j++){
				dir = Paths.get(path,String.valueOf(i),String.valueOf(j)).toFile();
				if(!dir.exists()){
					dir.mkdir();
				}
				for(int k = minTileY;k<=maxTileY;k++){					
					Poi p = new Poi(j,k,i);
					try {
						exportQueue.put(p);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}		
		}
		
	}
	

}
