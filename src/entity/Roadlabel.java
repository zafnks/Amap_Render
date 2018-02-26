package entity;

import java.util.ArrayList;
import java.util.List;

public class Roadlabel {

	private List<String> value = new ArrayList<String>();
	
	private List<Integer> x = new ArrayList<Integer>();
	
	private List<Integer> y = new ArrayList<Integer>();
	
	private List<Integer> angle = new ArrayList<Integer>();
	
	private Integer fontSize;
	
	private String color;
	
	private String boundColor;
	
	private Integer num = 0;
	
	public void addNum(){
		this.num = this.num + 1;
	}

	public List<String> getValue() {
		return value;
	}

	public void setValue(List<String> value) {
		this.value = value;
	}

	public List<Integer> getX() {
		return x;
	}

	public void setX(List<Integer> x) {
		this.x = x;
	}

	public List<Integer> getY() {
		return y;
	}

	public void setY(List<Integer> y) {
		this.y = y;
	}

	public List<Integer> getAngle() {
		return angle;
	}

	public void setAngle(List<Integer> angle) {
		this.angle = angle;
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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getBoundColor() {
		return boundColor;
	}

	public void setBoundColor(String boundColor) {
		this.boundColor = boundColor;
	}
	
	
}
