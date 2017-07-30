package com.svarttand.ludumdare39.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.ludumdare39.Application;
import com.svarttand.ludumdare39.hud.GameOverHud;
import com.svarttand.ludumdare39.level.Dificuly;

public class GameOverState extends State{

	private Viewport viewport;
	private GameOverHud hud;
	private TextureRegion backGround;
	
	public GameOverState(GameStateManager gsm, TextureAtlas atlas, float distance, Dificuly dificuly) {
		super(gsm);
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT, cam);
        cam.position.set(Application.V_WIDTH*0.5f, Application.V_HEIGHT*0.5f,0);
        cam.update();
        viewport.apply();
        hud = new GameOverHud(viewport, cam, atlas, gsm, distance, gsm.assetManager.get("Sound/10.wav", Sound.class), dificuly);
		backGround = atlas.findRegion("MainBackGround");
	}
	

	@Override
	protected void handleInput(float delta) {
		
		
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch batch) {
		cam.update();
        batch.setProjectionMatrix(cam.combined);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(backGround, 0, 0);
        batch.end();
        hud.getStage().draw();
		
	}

	@Override
	public void dispose() {
		hud.dispose();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		hud.resize(width, height);
		
	}
}
