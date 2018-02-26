package entity;

import java.awt.image.BufferedImage;
import java.util.List;

public class TileData {
	
	private Integer x;
	
	private Integer y;
	
	private Integer z;
	
	private List<Roadlabel> roadlabelArray;
	
	private List<PoiLabel> poiLableArray;
	
	private List<BuildingLabel> buildingLabelArray;
	
	private List<Region> regionArray;
	
	private List<Road> roadArray;
	
	private BufferedImage bufferedImage;

	public TileData(){}
	
	public TileData(Integer x, Integer y, Integer z, List<Roadlabel> roadlabelArray, List<PoiLabel> poiLableArray,
			List<BuildingLabel> buildingLabelArray, List<Region> regionArray, List<Road> roadArray) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.roadlabelArray = roadlabelArray;
		this.poiLableArray = poiLableArray;
		this.buildingLabelArray = buildingLabelArray;
		this.regionArray = regionArray;
		this.roadArray = roadArray;
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

	public Integer getZ() {
		return z;
	}

	public void setZ(Integer z) {
		this.z = z;
	}

	public void setRoadlabelArray(List<Roadlabel> roadlabelArray) {
		this.roadlabelArray = roadlabelArray;
	}

	public void setPoiLableArray(List<PoiLabel> poiLableArray) {
		this.poiLableArray = poiLableArray;
	}

	public void setBuildingLabelArray(List<BuildingLabel> buildingLabelArray) {
		this.buildingLabelArray = buildingLabelArray;
	}

	public void setRegionArray(List<Region> regionArray) {
		this.regionArray = regionArray;
	}

	public void setRoadArray(List<Road> roadArray) {
		this.roadArray = roadArray;
	}

	public List<Roadlabel> getRoadlabelArray() {
		return roadlabelArray;
	}

	public List<PoiLabel> getPoiLableArray() {
		return poiLableArray;
	}

	public List<BuildingLabel> getBuildingLabelArray() {
		return buildingLabelArray;
	}

	public List<Region> getRegionArray() {
		return regionArray;
	}

	public List<Road> getRoadArray() {
		return roadArray;
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
	
	
}
