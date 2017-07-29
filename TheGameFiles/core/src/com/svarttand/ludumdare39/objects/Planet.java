package com.svarttand.ludumdare39.objects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Planet {
	
	private String texturePath;
	private Vector2 position;
	private Circle bounds;
	private PlanetEnum type;
	
	public Planet(PlanetEnum type, Vector2 position){
		this.type = type;
		this.position = position;
		bounds = new Circle(position, type.getSize());
		texturePath = type.getPath();
	}

	public String getTexturePath() {
		return texturePath;
	}

	public Vector2 getPosition() {
		return position;
	}

	public Circle getBounds() {
		return bounds;
	}

	public PlanetEnum getType() {
		return type;
	}
	
	
	

}
