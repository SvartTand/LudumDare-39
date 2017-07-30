package com.svarttand.ludumdare39.objects;

public enum ObstacleEnum {
	CAR("CarObstacle", 32, false),
	STONE ("Rock", 32, true);
	
	
	private String path;
	private float size;
	private boolean gravity;
	
	ObstacleEnum(String path, float size, boolean gravity){
		this.path = path;
		this.size = size;
		this.gravity = gravity;
	}

	public String getPath() {
		return path;
	}


	public float getSize() {
		return size;
	}
	
	public boolean getGravity(){
		return gravity;
	}


	

}
