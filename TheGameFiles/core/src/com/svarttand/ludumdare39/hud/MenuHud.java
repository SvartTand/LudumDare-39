package com.svarttand.ludumdare39.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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
import com.svarttand.ludumdare39.level.Dificuly;
import com.svarttand.ludumdare39.states.GameStateManager;
import com.svarttand.ludumdare39.states.PlayState;

public class MenuHud {
	
	private OrthographicCamera camera;
	private Stage stage;
	private Viewport viewport;

	
	 private TextButton.TextButtonStyle style;
	 private Skin skin;
	 private BitmapFont font;

	 private Button playButton;
	 private Button exitButton;
	 
	 private Button easyButton;
	 private Button mediumButton;
	 private Button hardButton;
	 private Button insaneButton;
	 
	 private Dificuly selected;
	
	public MenuHud(Viewport viewport, OrthographicCamera cam, final TextureAtlas atlas, final GameStateManager gsm, final Sound clickSound){
		this.viewport = viewport;
		this.camera = cam;

		stage = new Stage(viewport);
		
		 cam.update();
	     viewport.apply();
	     
	     selected = Dificuly.EASY;
	     
	     font = new BitmapFont();
	     skin = new Skin(atlas);
	     style = new TextButton.TextButtonStyle();
	     style.font = font;
	     style.up = skin.getDrawable("ButtonOrange");
	     style.down = skin.getDrawable("ButtonOrangePressed");
	     style.checked = skin.getDrawable("ButtonOrangePressed");


	     playButton = new TextButton("PLAY", style);
	     playButton.setPosition((Application.V_WIDTH*0.76f-playButton.getWidth()*0.5f), Application.V_HEIGHT*0.92f-playButton.getHeight());
	     playButton.addListener( new ClickListener() {
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	             clickSound.play();
	             gsm.set(new PlayState(gsm, atlas, selected));

	            }
	        });
	     
	     exitButton = new TextButton("EXIT", style);
	     exitButton.setPosition((Application.V_WIDTH*0.76f-exitButton.getWidth()*0.5f), Application.V_HEIGHT*0.82f-exitButton.getHeight());
	     exitButton.addListener( new ClickListener() {
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	             clickSound.play();
	             Gdx.app.exit();

	            }
	        });
	     
	     easyButton = new TextButton("EASY", style);
	     easyButton.setPosition((Application.V_WIDTH*0.76f-easyButton.getWidth()*0.5f), Application.V_HEIGHT*0.6f-easyButton.getHeight());
	     easyButton.addListener( new ClickListener() {
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	             clickSound.play();
	             selected = Dificuly.EASY;
	             easyButton.setChecked(true);
	             mediumButton.setChecked(false);
	             hardButton.setChecked(false);
	             insaneButton.setChecked(false);
	             
	            }
	        });
	     
	     mediumButton = new TextButton("MEDIUM", style);
	     mediumButton.setPosition((Application.V_WIDTH*0.76f-mediumButton.getWidth()*0.5f), Application.V_HEIGHT*0.5f-mediumButton.getHeight());
	     mediumButton.addListener( new ClickListener() {
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	             clickSound.play();
	             selected = Dificuly.MEDIUM;
	             easyButton.setChecked(false);
	             mediumButton.setChecked(true);
	             hardButton.setChecked(false);
	             insaneButton.setChecked(false);

	            }
	        });
	     
	     hardButton = new TextButton("HARD", style);
	     hardButton.setPosition((Application.V_WIDTH*0.76f-hardButton.getWidth()*0.5f), Application.V_HEIGHT*0.4f-hardButton.getHeight());
	     hardButton.addListener( new ClickListener() {
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	             clickSound.play();
	             selected = Dificuly.HARD;
	             easyButton.setChecked(false);
	             mediumButton.setChecked(false);
	             hardButton.setChecked(true);
	             insaneButton.setChecked(false);

	            }
	        });
	     
	     insaneButton = new TextButton("INSANE!", style);
	     insaneButton.setPosition((Application.V_WIDTH*0.76f-insaneButton.getWidth()*0.5f), Application.V_HEIGHT*0.3f-insaneButton.getHeight());
	     insaneButton.addListener( new ClickListener() {
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	             clickSound.play();
	             
	             selected = Dificuly.INSANE;
	             easyButton.setChecked(false);
	             mediumButton.setChecked(false);
	             hardButton.setChecked(false);
	             insaneButton.setChecked(true);

	            }
	        });
	     easyButton.setChecked(true);
	     stage.addActor(easyButton);
	     stage.addActor(mediumButton);
	     stage.addActor(hardButton);
	     stage.addActor(insaneButton);
	     
	     stage.addActor(exitButton);
	     stage.addActor(playButton);
	     Gdx.input.setInputProcessor(stage);
		
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
