package thread;

import java.text.DecimalFormat;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import entity.Poi;
import entity.TileData;
import utils.PropertiesUtils;

public class Run {

	public static void main(String[] args) {
		
		int threadNum = PropertiesUtils.getThread();
		
		BlockingQueue<Poi> poiQueue = new LinkedBlockingQueue<Poi>(100);
		
		BlockingQueue<TileData> unHandleTileQueue = new LinkedBlockingQueue<TileData>(100);
		
		BlockingQueue<TileData> handleTileQueue = new LinkedBlockingQueue<TileData>(100);
		
		AtomicInteger total = new AtomicInteger(0);
		
		AtomicInteger handleNum = new AtomicInteger(0);
		
		TileDistributedThread step1 = new TileDistributedThread(poiQueue);
		step1.setTotal(total);
		new Thread(step1).start();
		
		for(int i=0;i<threadNum;i++){
			DataAnalysisThread step2 = new DataAnalysisThread(poiQueue, unHandleTileQueue);
			new Thread(step2).start();
		}
		
		for(int i=0;i<2;i++){
			RenderThread step3 = new RenderThread(unHandleTileQueue, handleTileQueue);
			new Thread(step3).start();
		}
				
		
		ImageOutputThread step4 = new ImageOutputThread(handleTileQueue);
		step4.setHandleNum(handleNum);
		new Thread(step4).start();
				
		int t = 0;
		DecimalFormat df = new DecimalFormat("0.00");//格式化小数  
		while(true){
			if (total.get() != 0 && t != handleNum.get()) {
				t = handleNum.get();
				/*System.out.println("poiQueue:" + poiQueue.size());
				System.out.println("unHandleTileQueue:" + unHandleTileQueue.size());
				System.out.println("handleTileQueue:" + handleTileQueue.size());*/
				System.out.println(handleNum.get() + "/" + total.get() + "   " + df.format((double)handleNum.get()/total.get()*100)+"%");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
