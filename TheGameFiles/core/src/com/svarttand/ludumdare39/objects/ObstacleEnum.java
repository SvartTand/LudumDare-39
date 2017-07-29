package com.svarttand.ludumdare39.objects;

public enum ObstacleEnum {
	CAR("CarObstacle", 32);
	
	private String path;
	private float size;
	
	ObstacleEnum(String path, float size){
		this.path = path;
		this.size = size;
	}

	public String getPath() {
		return path;
	}


	public float getSize() {
		return size;
	}


	

}
