package com.svarttand.ludumdare39.objects;

import java.util.ArrayList;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.svarttand.ludumdare39.misc.Animation;
import com.svarttand.ludumdare39.misc.Explosion;

public class Player {
	
	public static final float MAX_POWER = 100;
	public static final float MAX_HP = 100;
	public static final float MAX_SPEED = 150;
	public static final float GROUND = 60;
	
	
	private static final float ACCELERATION = 300;
	private static final float FRICTION = 150;
	private static final float GRAVITY = 400;
	private static final float TIME = 0.5f;
	
	private Vector2 velocity;
	private Vector2 position;
	private float rotation;
	
	private float power;
	private float hp;
	
	private Rectangle bounds;
	
	private Animation standAnimation;
	private Animation runAnimation;
	private Animation currentAnimation;
	
	private float timeLoop;
	
	private boolean isHit;
	
	public Player(TextureAtlas atlas){
		
		velocity = new Vector2(0,0);
		position = new Vector2(300,GROUND);
		rotation = 0;
		power = MAX_POWER;
		hp = MAX_HP;	
		bounds = new Rectangle(50, 50, 16, 32);
		standAnimation = new Animation("DudeStanding", 4, 0.5f, atlas);
		runAnimation = new Animation("DudeRunning", 5, 0.5f, atlas);
		currentAnimation = standAnimation;
		timeLoop = 0;
		
	}
	
	public void takeDmg(Sound hurtSound){
		if (timeLoop >= TIME) {
			hurtSound.play();
			timeLoop = 0;
			isHit = true;
		}
		hp -= 0.5;
		
	}
	
	public float getHp(){
		return hp;
	}
	
	public TextureRegion getTexturePath() {
		return currentAnimation.getFrame();
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


	public void update(float delta, ArrayList<Obstacle> obstacles, ArrayList<Explosion> explosions, TextureAtlas atlas){
		if (isHit) {
			explosions.add(new Explosion(position, "Blood", atlas, 6, 24, 40, false));
			isHit = false;
		}
		timeLoop += delta;
		currentAnimation.update(delta);
		if (velocity.x <= 10 && velocity.x >= -10) {
			currentAnimation = standAnimation;
		}else {
			currentAnimation = runAnimation;
		}
		if (velocity.x < 0) {
			velocity.x += FRICTION*delta;
		}
		if (velocity.x > 0) {
			velocity.x -= FRICTION*delta;
		}
		if (velocity.x <= 1 && velocity.x >= -1) {
			velocity.x = 0;
		}
		if (position.y > GROUND) {
			velocity.y -= GRAVITY *delta;
		}
		if (position.y < GROUND) {
			position.y = GROUND;
		}
		position.add(velocity.x *delta, velocity.y*delta);
		bounds.setPosition(position);
		
		
			
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public void upPressed(float delta, Sound jump){
		if (position.y <= GROUND) {
			velocity.y = 200;
			jump.play();
		}
		
	}
	public void downPressed(float delta){
		
	}
	
	public void leftPressed(float delta){
		if (velocity.x > -MAX_SPEED) {
			velocity.x += -ACCELERATION * delta;
		}
		
	}
	
	public void rightPressed(float delta){
		if (velocity.x < MAX_SPEED) {
			velocity.x += ACCELERATION * delta;
		}
		
	}

	public float getVelocity() {
		// TODO Auto-generated method stub
		return velocity.x;
	}

	public boolean isHit() {
		// TODO Auto-generated method stub
		return isHit;
	}
	
	
	

}
