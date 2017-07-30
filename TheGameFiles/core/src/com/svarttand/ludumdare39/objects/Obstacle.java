package com.svarttand.ludumdare39.objects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.svarttand.ludumdare39.Application;
import com.svarttand.ludumdare39.misc.Animation;
import com.svarttand.ludumdare39.misc.Explosion;
import com.svarttand.ludumdare39.states.PlayState;

public class Obstacle {
	
	private Vector2 position;
	private String path;
	private Rectangle bounds;
	private ObstacleEnum type;
	private Vector2 velocity;
	private Animation animation;
	
	public Obstacle(Vector2 position, ObstacleEnum type, Random rn, TextureAtlas atlas){
		this.position = position;
		this.type = type;
		path = type.getPath();
		bounds = new Rectangle(position.x, position.y,type.getSize(), type.getHeight());
		velocity = new Vector2(0,0);
		if (type.getAnimation()) {
			animation = new Animation(path, 4, 0.5f, atlas);
		}
		if (type.getMoving() < -2) {
			int n = (int) (type.getMoving() - (type.getMoving()*0.5f) - 1);
	    	int j = rn.nextInt() % n;
			velocity.x = (type.getMoving()*0.5f)+ j;
		}
	}
	
	public void update(float delta, Player player, Random rn, ArrayList<Obstacle> obstacleList, Sound hurtSound, ArrayList<Explosion> explosions, TextureAtlas atlas, Sound explosion){
		if (type.getAnimation()) {
			animation.update(delta);
		}
		if (type.getGravity() == true && position.y >= Player.GROUND) {
			velocity.y += -2 * delta;
		}else {
			if (type.getGravity()) {
				explosions.add(new Explosion(new Vector2(position.x - ((56-32)*0.5f), position.y - ((56-32)*0.5f)), "StoneExplosion", atlas, 5, 56, 56, false));
				if (position.x - player.getPosition().x < Application.V_WIDTH*0.5f && position.x - player.getPosition().x >  -Application.V_WIDTH*0.5f ) {
					explosion.setVolume(explosion.play(), 0.3f);
				}
				reposition(rn,obstacleList,player);
				
			}
			velocity.y = 0;
			//position.y = Player.GROUND;
		}
		
		position.y = position.y + velocity.y;
		position.x += velocity.x *delta;
		bounds.setPosition(position);
		
		if (player.getBounds().overlaps(bounds)) {
			player.takeDmg(hurtSound);
		}
	}
	
	public void reposition(Random rn, ArrayList<Obstacle> obstacleList, Player player){
		int n = PlayState.OBSTACLE_GAP - PlayState.MIN_OBSTACLE_GAP + 1;
    	int j = rn.nextInt() % n;
    	if (type.getGravity()) {
    		setPosition(player.getPosition().x + PlayState.MIN_OBSTACLE_GAP + j, rn);
		}else{
			if (obstacleList.get(1).equals(this)) {
				setPosition(obstacleList.get(0).getPosition().x + PlayState.MIN_OBSTACLE_GAP + j, rn);
			}else{
				setPosition(obstacleList.get(1).getPosition().x + PlayState.MIN_OBSTACLE_GAP + j, rn);
			}
			
		}
		
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
	
	public void setPosition(float position, Random rn){
		if (type.getGravity()) {
			this.position.y = Application.V_HEIGHT;
			velocity.y = 0;
		}
		if (type.getMoving() < 0) {
			int n = (int) (type.getMoving() - (type.getMoving()*0.5f) - 1);
	    	int j = rn.nextInt() % n;
			velocity.x = (type.getMoving()*0.5f)+ j;
		}
		this.position.x = position;
		bounds.x = position;
	}
	
	public TextureRegion getFrame(){
		return animation.getFrame();
	}

	public ObstacleEnum getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
	

}
