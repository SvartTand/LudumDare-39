package com.svarttand.ludumdare39.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.ludumdare39.Application;
import com.svarttand.ludumdare39.objects.Player;

public class PlayState extends State{
	
	private Viewport viewport;
	private TextureAtlas atlas;
	
	private Player player;
	
	private Label label;
	private BitmapFont font;
	
	private TextureRegion bg;
	

	public PlayState(GameStateManager gsm, TextureAtlas atlas) {
		super(gsm);
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT, cam);
        cam.position.set(Application.V_WIDTH*0.5f, Application.V_HEIGHT*0.5f,0);
        cam.update();
        viewport.apply();
        resize(Application.V_WIDTH, Application.V_HEIGHT);
        this.atlas = atlas;
        player = new Player();
        
        bg = atlas.findRegion("PlanetPlaceholder");
        System.out.println(bg.getRegionHeight() + ", " + bg.getRegionWidth() + ", " + bg.toString() + ", ");
        
        font = new BitmapFont();
        label = new Label("Does it work?", new Label.LabelStyle(font, Color.WHITE));
        label.setPosition(20, 20);
        
	}

	@Override
	protected void handleInput(float delta) {
		
		
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			player.upPressed(delta);
			System.out.println("up");
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			player.downPressed(delta);
			System.out.println("up");
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			player.leftPressed(delta);
			System.out.println("left");
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			player.rightPressed(delta);
			System.out.println("right");
		}
		
	}

	@Override
	public void update(float delta) {
		handleInput(delta);
		player.update(delta);
		
		
	}

	@Override
	public void render(SpriteBatch batch) {
		cam.update();
        batch.setProjectionMatrix(cam.combined);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(atlas.findRegion(player.getTexturePath()),
        	player.getPosition().x, player.getPosition().y,
        	atlas.findRegion(player.getTexturePath()).getRegionWidth()*0.5f,
        	atlas.findRegion(player.getTexturePath()).getRegionHeight()*0.5f,
        	atlas.findRegion(player.getTexturePath()).getRegionWidth(), 
        	atlas.findRegion(player.getTexturePath()).getRegionHeight(), 1, 1, player.getRotation());
        batch.draw(bg, 200, 200);
        label.draw(batch, 1);
        batch.end();
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		
	}

}
