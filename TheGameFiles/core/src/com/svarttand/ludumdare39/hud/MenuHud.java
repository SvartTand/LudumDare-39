package com.svarttand.ludumdare39.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.ludumdare39.Application;

public class MenuHud {
	
	private OrthographicCamera camera;
	private Stage stage;
	private Viewport viewport;
	private TextureAtlas atlas;
	
	 private TextButton.TextButtonStyle style;
	 private Skin skin;
	 private BitmapFont font;

	 private Button playButton;
	 private Button exitButton;
	 
	 private int pressed;
	
	public MenuHud(Viewport viewport, OrthographicCamera cam, TextureAtlas atlas){
		this.viewport = viewport;
		this.camera = cam;
		this.atlas = atlas;
		stage = new Stage(viewport);
		
		 cam.update();
	     viewport.apply();
	     
	     pressed = 0;
	     
	     font = new BitmapFont();
	     skin = new Skin(atlas);
	     style = new TextButton.TextButtonStyle();
	     style.font = font;
	     style.up = skin.getDrawable("Button");
	     style.down = skin.getDrawable("Button");


	     playButton = new TextButton("PLAY", style);
	     playButton.sizeBy(64,32);
	     playButton.setPosition((Application.V_WIDTH*0.5f-playButton.getWidth()*0.5f), Application.V_HEIGHT*0.5f-playButton.getHeight());
	     playButton.addListener( new ClickListener() {
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	             System.out.println("PLAY!!");
	             pressed = 1;
	            }
	        });
	     
	     stage.addActor(playButton);
	     Gdx.input.setInputProcessor(stage);
		
	}
	
	public Stage getStage() {
		return stage;
	}
	

	 public int getPressed() {
	     return pressed;
	 }

	 public void dispose() {
	     stage.dispose();
	     skin.dispose();
	 }

	 public void resize(int width, int height) {
	     viewport.update(width,height);
	 }

}
