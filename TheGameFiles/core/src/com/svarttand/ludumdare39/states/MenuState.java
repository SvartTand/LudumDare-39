package com.svarttand.ludumdare39.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.ludumdare39.Application;
import com.svarttand.ludumdare39.hud.MenuHud;

public class MenuState extends State{
	
	private Viewport viewport;
	private TextureAtlas atlas;
	private MenuHud hud;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT, cam);
        cam.position.set(Application.V_WIDTH*0.5f, Application.V_HEIGHT*0.5f,0);
        cam.update();
        viewport.apply();
        atlas = gsm.assetManager.get(LoadingState.ATLAS_PATH, TextureAtlas.class);
        hud = new MenuHud(viewport, cam, atlas, this);
		
	}
	
	public void playPressed(){
		gsm.set(new PlayState(gsm, atlas));
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
