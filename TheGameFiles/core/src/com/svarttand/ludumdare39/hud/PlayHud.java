package com.svarttand.ludumdare39.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.ludumdare39.Application;
import com.svarttand.ludumdare39.objects.Player;

public class PlayHud {
	
	private OrthographicCamera camera;
	private Stage stage;
	private Viewport viewport;
	
	private BitmapFont font;
	private Label distanceLabel;
	
	private float hp;
	
	public PlayHud(Viewport viewport, OrthographicCamera cam, TextureAtlas atlas){
		this.camera = new OrthographicCamera();
		this.viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT,camera);
		stage = new Stage(this.viewport);
		
		camera.update();
	    viewport.apply();	     
	    font = new BitmapFont();
	    distanceLabel = new Label("Distance Traveled: 0", new LabelStyle(font, Color.WHITE));
	    distanceLabel.setPosition(Application.V_WIDTH*0.5f- distanceLabel.getWidth()*0.5f, Application.V_HEIGHT*0.8f);
	    stage.addActor(distanceLabel);
	    
	    
	}
	
	public void update(int distance, float hp){
		distanceLabel.setText("Distance Traveled: " + distance);
		distanceLabel.setPosition(Application.V_WIDTH*0.5f- distanceLabel.getWidth()*0.5f, Application.V_HEIGHT*0.8f);
		this.hp = hp;
	}
	
	public void render(ShapeRenderer renderer){
		renderer.setProjectionMatrix(camera.combined);
		renderer.begin(ShapeType.Filled);
		renderer.setColor(Color.BLACK);
		renderer.rect(6, 6, Player.MAX_HP, 15);
		renderer.setColor(Color.FIREBRICK);
		renderer.rect(6, 6, hp, 15);
		
		
		
	}
	
	public Stage getStage() {
		return stage;
	}

	 public void dispose() {
	     stage.dispose();
	     //skin.dispose();
	 }

	 public void resize(int width, int height) {
	     viewport.update(width,height);
	 }
	

}
