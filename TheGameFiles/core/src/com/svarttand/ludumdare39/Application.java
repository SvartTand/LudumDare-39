package com.svarttand.ludumdare39;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.svarttand.ludumdare39.states.GameStateManager;
import com.svarttand.ludumdare39.states.LoadingState;

public class Application extends ApplicationAdapter {
	public static final int V_WIDTH = 300;
	public static final int V_HEIGHT = 200;
	private SpriteBatch batch;
	private GameStateManager gsm;
	private AssetManager assetManager;
	
	@Override
	public void create () {
		assetManager = new AssetManager();
		batch = new SpriteBatch();
		gsm = new GameStateManager(assetManager);
		LoadingState loadingState = new LoadingState(gsm);
		gsm.push(loadingState);
	}

	@Override
	public void render () {
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose () {
		gsm.dispose();
	}

	@Override
	public void resize(int width, int height){
		gsm.resize(width, height);

	}
}
