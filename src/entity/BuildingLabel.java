package entity;


public class BuildingLabel {
	
	private Integer[] x;
	
	private Integer[] y;
	
	private Integer[] shadow_x;
	
	private Integer[] shadow_y;	
	
	private String edgeColor;
	
	private String fillColor;
	
	private float alpha;

	public Integer[] getX() {
		return x;
	}

	public void setX(Integer[] x) {
		this.x = x;
	}

	public Integer[] getY() {
		return y;
	}

	public void setY(Integer[] y) {
		this.y = y;
	}

	public Integer[] getShadow_x() {
		return shadow_x;
	}

	public void setShadow_x(Integer[] shadow_x) {
		this.shadow_x = shadow_x;
	}

	public Integer[] getShadow_y() {
		return shadow_y;
	}

	public void setShadow_y(Integer[] shadow_y) {
		this.shadow_y = shadow_y;
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
