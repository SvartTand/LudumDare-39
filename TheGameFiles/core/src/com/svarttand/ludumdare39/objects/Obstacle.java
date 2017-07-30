package com.svarttand.ludumdare39.objects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.svarttand.ludumdare39.Application;
import com.svarttand.ludumdare39.states.PlayState;

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
	
	public void update(float delta, Player player, Random rn, ArrayList<Obstacle> obstacleList){
		if (type.getGravity() == true && position.y >= Player.GROUND) {
			velocity.y += -2 * delta;
		}else {
			if (type.getGravity()) {
				reposition(rn,obstacleList,player);
			}
			velocity.y = 0;
			//position.y = Player.GROUND;
		}
		position.y = position.y + velocity.y;
		bounds.setPosition(position);
		
		if (player.getBounds().overlaps(bounds)) {
			player.takeDmg();
		}
	}
	
	public void reposition(Random rn, ArrayList<Obstacle> obstacleList, Player player){
		int n = PlayState.OBSTACLE_GAP - PlayState.MIN_OBSTACLE_GAP + 1;
    	int j = rn.nextInt() % n;
    	if (type.getGravity()) {
    		setPosition(player.getPosition().x + PlayState.MIN_OBSTACLE_GAP + j);
		}else{
			if (obstacleList.get(1).equals(this)) {
				setPosition(obstacleList.get(0).getPosition().x + PlayState.MIN_OBSTACLE_GAP + j);
			}else{
				setPosition(obstacleList.get(1).getPosition().x + PlayState.MIN_OBSTACLE_GAP + j);
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
	
	public void setPosition(float position){
		if (type.getGravity()) {
			System.out.println("lxdnj");
			this.position.y = Application.V_HEIGHT;
			velocity.y = 0;
		}
		this.position.x = position;
		bounds.x = position;
	}
	
	

}
