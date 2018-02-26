package entity;


public class PoiLabel {

	private String[] value = new String[3];
	
	private Integer x;
	
	private Integer y;
	
	private String type;
	
	private Integer fontSize;
	
	private String color;
	
	private String boundColor;
	
	private Integer[] lable = new Integer[12];
	
	private boolean icon = true;
	
	private boolean isLable = true;

	public String[] getValue() {
		return value;
	}

	public void setValue(String[] value) {
		this.value = value;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public String getType() {
		return type;
	}

	public void setType(String sub) {
		this.type = sub;
	}

	public Integer getFontSize() {
		return fontSize;
	}

	public void setFontSize(Integer fontSize) {
		this.fontSize = fontSize;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer[] getLable() {
		return lable;
	}

	public void setLable(Integer[] lable) {
		this.lable = lable;
	}

	public boolean isIcon() {
		return icon;
	}

	public void setIcon(boolean icon) {
		this.icon = icon;
	}

	public boolean isLable() {
		return isLable;
	}

	public void setLable(boolean isLable) {
		this.isLable = isLable;
	}

	public String getBoundColor() {
		return boundColor;
	}

	public void setBoundColor(String boundColor) {
		this.boundColor = boundColor;
	}
	
	
}
