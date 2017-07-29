package com.svarttand.ludumdare39.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Player {
	
	public static final float MAX_POWER = 100;
	public static final float MAX_HP = 100;
	public static final float MAX_ROTATION = 360;
	
	private static final float TURN_SPEED = 100;
	
	private Vector2 velocity;
	private String texturePath;
	private Vector2 position;
	private Vector2 angles;
	private float rotation;
	
	private float power;
	private float hp;
	
	
	
	public Player(){
		velocity = new Vector2(0,0);
		position = new Vector2(50,50);
		angles = new Vector2(0,0);
		texturePath = "Ship";
		rotation = 0;
		power = MAX_POWER;
		hp = MAX_HP;		
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
		
		position.add(delta * velocity.x,delta * velocity.y);
		//System.out.println(position.x + ", " + position.y);
	}
	
	public void upPressed(float delta){
		velocity.add(-100*delta* angles.x, -100*delta*angles.y);
	}
	public void downPressed(float delta){
		velocity.add(100*delta*angles.x, 100*delta*angles.y);
	}
	
	public void leftPressed(float delta){
		rotation -= TURN_SPEED * delta;
		if (rotation < 0) {
			rotation += 360;
		}
		angles.x = (MathUtils.cosDeg(rotation-90));
		angles.y = (MathUtils.sinDeg(rotation-90));
	}
	
	public void rightPressed(float delta){
		rotation += TURN_SPEED * delta;
		if (rotation > MAX_ROTATION) {
			rotation -= MAX_ROTATION;
		}
		angles.x = (MathUtils.cosDeg(rotation-90));
		angles.y = (MathUtils.sinDeg(rotation-90));
	}
	
	
	

}
