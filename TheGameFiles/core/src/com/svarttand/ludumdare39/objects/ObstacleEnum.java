package com.svarttand.ludumdare39.objects;

public enum ObstacleEnum {
	CAR("CarObstacle", 32, false, 32, 0, false),
	STONE ("Rock", 32, true, 32, 0, false),
	MOVING_CAR("CarMoving", 56, false, 32, -160, true);
	
	
	private String path;
	private float width;
	private float height;
	private boolean gravity;
	private float moving;
	private boolean animation;
	
	ObstacleEnum(String path, float width, boolean gravity, float height, float moving, boolean animation){
		this.path = path;
		this.width = width;
		this.gravity = gravity;
		this.height = height;
		this.moving = moving;
		this.animation = animation;
	}

	public String getPath() {
		return path;
	}


	public float getSize() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public boolean getGravity(){
		return gravity;
	}
	
	public boolean getAnimation(){
		return animation;
	}
	
	public float getMoving(){
		return moving;
	}


	

}
