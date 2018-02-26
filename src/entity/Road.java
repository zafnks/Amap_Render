package entity;

import java.util.List;

public class Road {
	
	private List<List<Integer>> x;
	
	private List<List<Integer>> y;
	
	private String edgeColor;
	
	private String fillColor;
	
	private String roadType;
	
	private float[] roadDashParam;
	
	private String edgeType;
	
	private float[] edgeDashParam;
	
	private String roadLevel;
	
	private float roadWidth;
	
	private float edgeWidth;
	


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

	public String getRoadType() {
		return roadType;
	}

	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}

	public String getRoadLevel() {
		return roadLevel;
	}

	public void setRoadLevel(String roadLevel) {
		this.roadLevel = roadLevel;
	}

	public float getRoadWidth() {
		return roadWidth;
	}

	public void setRoadWidth(float roadWidth) {
		this.roadWidth = roadWidth;
	}

	public float getEdgeWidth() {
		return edgeWidth;
	}

	public void setEdgeWidth(float edgeWidth) {
		this.edgeWidth = edgeWidth;
	}

	public float[] getRoadDashParam() {
		return roadDashParam;
	}

	public void setRoadDashParam(float[] roadDashParam) {
		this.roadDashParam = roadDashParam;
	}

	public String getEdgeType() {
		return edgeType;
	}

	public void setEdgeType(String edgeType) {
		this.edgeType = edgeType;
	}

	public float[] getEdgeDashParam() {
		return edgeDashParam;
	}

	public void setEdgeDashParam(float[] edgeDashParam) {
		this.edgeDashParam = edgeDashParam;
	}
	
	
	
}
