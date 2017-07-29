package com.svarttand.ludumdare39.objects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
	
	public static final float MAX_POWER = 100;
	public static final float MAX_HP = 100;
	public static final float MAX_SPEED = 360;
	
	private static final float ACCELERATION = 300;
	private static final float FRICTION = 150;
	private static final float GRAVITY = 400;
	
	private Vector2 velocity;
	private String texturePath;
	private Vector2 position;
	private Vector2 angles;
	private float rotation;
	private float mass;
	
	private float power;
	private float hp;
	
	private Rectangle bounds;
	
	
	
	public Player(){
		velocity = new Vector2(0,0);
		position = new Vector2(50,50);
		angles = new Vector2(0,0);
		texturePath = "Dude";
		rotation = 0;
		power = MAX_POWER;
		hp = MAX_HP;	
		bounds = new Rectangle(50, 50, 16, 32);
		mass = 10;
	}
	
	public String getTexturePath() {
		return texturePath;
	}


	public Vector2 getPosition() {
		return position;
	}


	public float getRotation() {
		return rotation;
	}


	public float getPower() {
		return power;
	}


	public void update(float delta){
		if (velocity.x < 0) {
			velocity.x += FRICTION*delta;
		}
		if (velocity.x > 0) {
			velocity.x -= FRICTION*delta;
		}
		if (position.y > 50) {
			velocity.y -= GRAVITY *delta;
		}
		if (position.y < 50) {
			position.y = 50;
		}
		position.add(velocity.x *delta, velocity.y*delta);
		bounds.setPosition(position);
	}
	
	public void upPressed(float delta){
		if (position.y <= 50) {
			velocity.y = 200;
		}
		
	}
	public void downPressed(float delta){
		
	}
	
	public void leftPressed(float delta){
		if (velocity.x > MAX_SPEED) {
			velocity.x += -ACCELERATION * delta;
		}
		
	}
	
	public void rightPressed(float delta){
		if (velocity.x < MAX_SPEED) {
			velocity.x += ACCELERATION * delta;
		}
		
	}
	
	
	

}
