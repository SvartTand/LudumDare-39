package com.svarttand.ludumdare39.level;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Ground {
	
	private Vector2 position;
	private String path;
	
	public Ground(Vector2 position, String path){
		this.position = position;
		this.path = path;
		
	}

	public Vector2 getPosition() {
		return position;
	}

	public String getPath() {
		return path;
	}
	
	public void updateX(float x){
		position.x = x;
	}
	

}
