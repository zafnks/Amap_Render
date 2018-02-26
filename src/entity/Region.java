package entity;

import java.util.List;

public class Region {
	
	private List<List<Integer>> x;
	
	private List<List<Integer>> y;
	
	private String edgeColor;
	
	private String fillColor;
	
	private float alpha;


	public List<List<Integer>> getX() {
		return x;
	}

	public void setX(List<List<Integer>> x) {
		this.x = x;
	}

	public List<List<Integer>> getY() {
		return y;
	}

	public void setY(List<List<Integer>> y) {
		this.y = y;
	}

	public String getEdgeColor() {
		return edgeColor;
	}

	public void setEdgeColor(String edgeColor) {
		this.edgeColor = edgeColor;
	}

	public String getFillColor() {
		return fillColor;
	}

	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	
	
	
}
