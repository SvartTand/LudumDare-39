package com.svarttand.ludumdare39.objects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class PlanetHandler {
	
	private static final int[] LIST = {100,300,500,600,300,700,-100,-100, 500,0};
	
	private ArrayList<Planet> planetList;
	
	public PlanetHandler(){
		planetList = new ArrayList<Planet>();
		for (int i = 0; i < LIST.length; i+=2) {
			planetList.add(new Planet(PlanetEnum.PLANET, new Vector2(LIST[i], LIST[i+1])));
		}
	}
	
	public void render(SpriteBatch batch, TextureAtlas atlas){
		for (int i = 0; i < planetList.size(); i++) {
			batch.draw(atlas.findRegion(planetList.get(i).getTexturePath()), planetList.get(i).getPosition().x, planetList.get(i).getPosition().y);
			
		}
	}
	
	public ArrayList<Planet> getPlanetList(){
		return planetList;
	}

}
