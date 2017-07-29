package com.svarttand.ludumdare39.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Obstacle {
	
	private Vector2 position;
	private String path;
	private Rectangle bounds;
	private ObstacleEnum type;
	
	public Obstacle(Vector2 position, ObstacleEnum type){
		this.position = position;
		this.type = type;
		path = type.getPath();
		bounds = new Rectangle(position.x, position.y,type.getSize(), type.getSize());
	}

	public Vector2 getPosition() {
		return position;
	}

	public String getPath() {
		return path;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void setPosition(float position){
		this.position.x = position;
		bounds.x = position;
	}
	
	

}
