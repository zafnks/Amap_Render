package utils;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.GlyphVector;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/**
 * 渲染Utils类
 * @author 刘双源
 * @date   2016.9.24	
 *
 */
public class PrintUtils {
	
	private BufferedImage image = null;
	
	private Integer rotate = 0;
	
	/**
	 * 设置底图
	 * @param image
	 */
	public PrintUtils(BufferedImage image){
		this.image = image;
	}

	/**
	 * 渲染文字
	 * @param value 		文字
	 * @param size  		字号
	 * @param color			文字颜色
	 * @param boundColor	文字描边颜色
	 * @param x				X坐标
	 * @param y				Y坐标
	 * @param rotate		旋转角度
	 */
	public void printFont(String value,Integer size,String color,String boundColor,Integer x,Integer y,Integer rotate){
		 Graphics2D g = (Graphics2D) image.createGraphics(); 
         Font f = new Font("微软雅黑",1,size); 
         //去除文字锯齿
         g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);        
         g.setFont(f);
         int r = rotate-this.rotate;
         r = (r<0)?360+r:r;
         g.rotate(Math.toRadians(360-rotate),x,y); 
         this.rotate = rotate;
         //使用shape填充方式打印文字
         GlyphVector v = f.createGlyphVector(g.getFontMetrics(f).getFontRenderContext(), value);
         Shape shape = v.getOutline();     
         g.translate(x,y);
         g.setColor(Color.decode(color));
         g.fill(shape);
         //字体描边
         if(boundColor!=null){
        	 g.setColor(Color.decode(boundColor));
             g.setStroke(new BasicStroke(0.7f));
         }         
         g.draw(shape);
         g.dispose(); 
	}
	
	public void drawLine(Integer[] x, Integer[] y, String color, float width) {
		Graphics2D g = (Graphics2D) image.getGraphics();
		Stroke stroke = g.getStroke(); // 得到当前的画刷
		g.setColor(Color.decode(color));
		g.setStroke(new BasicStroke(width, BasicStroke.CAP_ROUND , BasicStroke.JOIN_ROUND)); // 设置新的画刷
		int _x = x[0],_y = y[0];
		for(int i=1; i<x.length; i++){
			g.draw(new Line2D.Float(_x, _y, x[i], y[i]));
			_x = x[i];
			_y = y[i];
		}
		g.setStroke(stroke); // 将画刷复原
		g.dispose(); 
	}
	
	/**
	 * 绘制虚线
	 * @param x
	 * @param y
	 * @param color
	 * @param width
	 */
	public void drawDash(Integer[] x, Integer[] y, String color, float width, float[] param) {
		Graphics2D g = (Graphics2D) image.getGraphics();
		Stroke stroke = g.getStroke(); // 得到当前的画刷
		g.setColor(Color.decode(color));
		Stroke dash = new BasicStroke(width, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 3.5f, param, 0f);
		g.setStroke(dash); // 设置新的画刷
		int _x = x[0],_y = y[0];
		for(int i=1; i<x.length; i++){
			g.draw(new Line2D.Float(_x, _y, x[i], y[i]));
			_x = x[i];
			_y = y[i];
		}
		g.setStroke(stroke); // 将画刷复原
		g.dispose(); 
	}
	
	/**
	 * 铺满背景色
	 * @param color
	 * @param width
	 * @param height
	 */
	public void fillBackGround(Color color, int width, int height) {
		Graphics graphics = image.getGraphics();
		// 填充背景色
		graphics.setColor(color);
		graphics.fillRect(1, 1, width - 1, height - 1);
		graphics.dispose();
	}
	
	/**
	 * 图片叠加
	 * @param newimage	图片buffered
	 * @param x			图片X坐标
	 * @param y			图片Y坐标
	 */
	public void printImage(BufferedImage newimage,Integer x,Integer y){
		Graphics2D g=(Graphics2D) image.getGraphics();
		ImageIcon imageIcon = new ImageIcon(image);
        g.drawImage(newimage,x,y,imageIcon.getImageObserver());
        g.dispose(); 
	}
	
	/**
	 * 绘制色块区域
	 * @param x				X轴坐标数组
	 * @param y				Y轴坐标数组
	 * @param edgeColor		边框线条颜色(#ffffff)
	 * @param fillColor		填充颜色(#ffffff)
	 * @param alpha			透明度
	 */
	public void printPolygon(int[] x,int[] y,String edgeColor,String fillColor,float alpha){		
		Graphics2D g = (Graphics2D) image.createGraphics();
		//设置透明度
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
    	g.setComposite(ac);
		Stroke stroke=new BasicStroke(2.0f);		
		if(edgeColor!=null){
			g.setStroke(stroke);			
			g.setColor(Color.decode(edgeColor));
			//瓦片边缘不绘制线条
			for(int i=0;i<x.length;i++){
				if(i!=x.length-1){
					if((y[i]==512 && y[i+1]==512) || (y[i]==0 && y[i+1]==0) || (x[i]==512 && x[i+1]==512) || (x[i]==0 && x[i+1]==0)){
						continue;
					}
					g.drawLine(x[i], y[i], x[i+1], y[i+1]);
				}else{
					if((y[x.length-1]==512 && y[0]==512) || (y[x.length-1]==0 && y[0]==0) || (x[x.length-1]==512 && x[0]==512) || (x[x.length-1]==0 && x[0]==0)){
						continue;
					}
					g.drawLine(x[x.length-1], y[x.length-1], x[0], y[0]);
				}
			}
			
		}		
		g.setColor(Color.decode(fillColor));
		g.fillPolygon(x, y, x.length-1);
		g.dispose();
	}
	
	public void printPolygon(Integer[] x,Integer[] y,String edgeColor,String fillColor,float alpha){
		int[] nx = new int[x.length];
		int[] ny = new int[y.length];
		for(int i=0;i<x.length;i++){
			nx[i] = x[i];
		}
		for(int i=0;i<x.length;i++){
			ny[i] = y[i];
		}
		this.printPolygon(nx, ny,edgeColor,fillColor,alpha);
	}
	
}
