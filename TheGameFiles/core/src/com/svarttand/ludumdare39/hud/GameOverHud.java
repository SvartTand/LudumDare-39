package com.svarttand.ludumdare39.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.ludumdare39.Application;
import com.svarttand.ludumdare39.states.GameOverState;
import com.svarttand.ludumdare39.states.GameStateManager;
import com.svarttand.ludumdare39.states.MenuState;
import com.svarttand.ludumdare39.states.PlayState;

public class GameOverHud {
	
	private OrthographicCamera camera;
	private Stage stage;
	private Viewport viewport;

	
	 private TextButton.TextButtonStyle style;
	 private Skin skin;
	 private BitmapFont font;

	 private Button playButton;
	 private Button MenuButton;
	 
	 private int pressed;
	 
	 private Label label;
	
	public GameOverHud(Viewport viewport, OrthographicCamera cam, final TextureAtlas atlas, final GameStateManager gsm, float distance){
		this.viewport = viewport;
		this.camera = cam;

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
	     
	     font = new BitmapFont();
		 label = new Label("GAME OVER!\nScore: \n" + distance, new LabelStyle(font, Color.WHITE));
		 label.setPosition(Application.V_WIDTH*0.5f- label.getWidth()*0.5f, Application.V_HEIGHT*0.7f);
		 stage.addActor(label);


	     MenuButton = new TextButton("Main Menu", style);
	     MenuButton.sizeBy(64,32);
	     MenuButton.setPosition((Application.V_WIDTH*0.5f-MenuButton.getWidth()*0.5f), Application.V_HEIGHT*0.28f-MenuButton.getHeight());
	     MenuButton.addListener( new ClickListener() {
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	             System.out.println("PLAY!!");
	             pressed = 1;
	             gsm.set(new MenuState(gsm, atlas));
	            }
	        });
	     
	     playButton = new TextButton("RETRY", style);
	     playButton.sizeBy(64,32);
	     playButton.setPosition((Application.V_WIDTH*0.5f-playButton.getWidth()*0.5f), Application.V_HEIGHT*0.55f-playButton.getHeight());
	     playButton.addListener( new ClickListener() {
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	             System.out.println("PLAY!!");
	             pressed = 1;
	             gsm.set(new PlayState(gsm, atlas));
	            }
	        });
	     stage.addActor(MenuButton);
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
	     //skin.dispose();
	 }

	 public void resize(int width, int height) {
	     viewport.update(width,height);
	 }

}
