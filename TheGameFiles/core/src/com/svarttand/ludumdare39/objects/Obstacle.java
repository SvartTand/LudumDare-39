package com.svarttand.ludumdare39.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Obstacle {
	
	private Vector2 position;
	private String path;
	private Rectangle bounds;
	private ObstacleEnum type;
	private Vector2 velocity;
	
	public Obstacle(Vector2 position, ObstacleEnum type){
		this.position = position;
		this.type = type;
		path = type.getPath();
		bounds = new Rectangle(position.x, position.y,type.getSize(), type.getSize());
		velocity = new Vector2(0,0);
	}
	
	public void update(float delta){
		if (type.getGravity() == true && position.y >= Player.GROUND) {
			velocity.y += -1 * delta;
		}else {
			velocity.y = 0;
			position.y = Player.GROUND;
		}
		position.y = position.y + velocity.y;
		bounds.setPosition(position);
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
		if (type.getGravity()) {
			this.position.y = 150;
		}
		this.position.x = position;
		bounds.x = position;
	}
	
	

}
