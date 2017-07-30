package com.svarttand.ludumdare39.misc;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;


public class Explosion {
	
	private String textureName;
	private Vector2 position;
	private float currentFrameTime;
	private float maxFrameTime;
	private int frame;
	private float frameCount;
	
	public Explosion(Vector2 position, String name, TextureAtlas atlas, float frameCount, float width, float height, boolean bottomPic){
		this.position = new Vector2();
		
		frame = 1;
		textureName = name;
		if (!bottomPic) {
			this.position.set(position.x +width*0.5f, position.y + height*0.5f);
			this.position.x = this.position.x - atlas.findRegion(textureName + frame).getRegionWidth()*0.5f;
			this.position.y = this.position.y - atlas.findRegion(textureName + frame).getRegionHeight()*0.5f;
		}else{
			this.position.set(position.x +width*0.5f, position.y);
			this.position.x = this.position.x - atlas.findRegion(textureName + frame).getRegionWidth()*0.5f;
		}
		
		maxFrameTime = 0.1f;
		currentFrameTime = 0;
		this.frameCount = frameCount;
	}
	
	public boolean update(float delta){
		currentFrameTime += delta;
		if (currentFrameTime > maxFrameTime){
			frame++;
			currentFrameTime = 0;
		}
		if (frame >= frameCount){
			return false;
		}
		return true;
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public String getTextureName(){
		return textureName + frame;
	}

}
