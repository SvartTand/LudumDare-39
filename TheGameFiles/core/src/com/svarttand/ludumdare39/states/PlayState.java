package com.svarttand.ludumdare39.states;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.ludumdare39.Application;
import com.svarttand.ludumdare39.level.Ground;
import com.svarttand.ludumdare39.objects.Planet;
import com.svarttand.ludumdare39.objects.PlanetEnum;
import com.svarttand.ludumdare39.objects.PlanetHandler;
import com.svarttand.ludumdare39.objects.Player;

public class PlayState extends State{
	
	
	private static final int GROUND_SIZE = 32;
	private Viewport viewport;
	private TextureAtlas atlas;
	
	private Player player;
	
	private Label label;
	private BitmapFont font;
	
	private ArrayList<Ground> groundList;
	

	public PlayState(GameStateManager gsm, TextureAtlas atlas) {
		super(gsm);
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT, cam);
        cam.position.set(Application.V_WIDTH, Application.V_HEIGHT,0);
        cam.update();
        viewport.apply();
        resize(Application.V_WIDTH, Application.V_HEIGHT);
        this.atlas = atlas;
        player = new Player();
        cam.position.x = player.getPosition().x;
		cam.position.y = 100;
        
        font = new BitmapFont();
        groundList = new ArrayList<Ground>();
        
        for (int i = 0; i < (GROUND_SIZE + Application.V_WIDTH)/GROUND_SIZE; i++) {
			groundList.add(new Ground(new Vector2(i*GROUND_SIZE, 50-GROUND_SIZE), "Road"));
		}
        
        resize(Application.V_WIDTH, Application.V_HEIGHT);
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
		cam.position.x = player.getPosition().x;
		//cam.position.y = player.getPosition().y;
		for (int i = 0; i < groundList.size(); i++) {
			if (cam.position.x - (cam.viewportWidth/2) > groundList.get(i).getPosition().x + GROUND_SIZE){
				groundList.get(i).updateX(groundList.get(i).getPosition().x + groundList.size() * GROUND_SIZE);
				System.out.println("It changed" + groundList.size());
			}
		}
		
	}

	@Override
	public void render(SpriteBatch batch) {
		cam.update();
        batch.setProjectionMatrix(cam.combined);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        for (int i = 0; i < groundList.size(); i++) {
			batch.draw(atlas.findRegion(groundList.get(i).getPath()), groundList.get(i).getPosition().x, groundList.get(i).getPosition().y);
		}
        batch.draw(atlas.findRegion(player.getTexturePath()),
        	player.getPosition().x - atlas.findRegion(player.getTexturePath()).getRegionWidth()*0.5f,
        	player.getPosition().y - atlas.findRegion(player.getTexturePath()).getRegionHeight()*0.5f);
        
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
