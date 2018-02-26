package thread;

import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import javax.imageio.ImageIO;

import entity.TileData;
import utils.PropertiesUtils;

public class ImageOutputThread implements Runnable{
	
	private BlockingQueue<TileData> importQueue;
	
	private AtomicInteger handleNum;	
	
	public void setHandleNum(AtomicInteger handleNum) {
		this.handleNum = handleNum;
	}

	public ImageOutputThread(BlockingQueue<TileData> importQueue){
		this.importQueue = importQueue;
	}

	public BlockingQueue<TileData> getImportQueue() {
		return importQueue;
	}

	public void setImportQueue(BlockingQueue<TileData> importQueue) {
		this.importQueue = importQueue;
	}

	@Override
	public void run() {
		
		String path = PropertiesUtils.getPath();	
		while(true){
			try {
				TileData tileData = importQueue.take();
				ImageIO.write(tileData.getBufferedImage(), "PNG", Paths.get(path,String.valueOf(tileData.getZ()),
						String.valueOf(String.valueOf(tileData.getX())),String.valueOf(tileData.getY())+".png").toFile());
				handleNum.incrementAndGet();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	

}
