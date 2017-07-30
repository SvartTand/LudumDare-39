package com.svarttand.ludumdare39.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.ludumdare39.Application;

public class LoadingState extends State {
		
		public static final int AUDIO_AMOUNT = 12;
		public static final String ATLAS_PATH = "ThePack.pack";
	 	private Viewport viewport;
	    private boolean loaded;
	    private int counter;
	    private String[] audioPaths;

	    public LoadingState(GameStateManager gsm) {
	        super(gsm);
	        viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT, cam);
	        counter = 0;
	        loaded = false;
	        makeAudioPaths();
	    }
	    
	    private void makeAudioPaths(){
	    	
	    	audioPaths = new String[AUDIO_AMOUNT];
	        for (int i = 0; i < AUDIO_AMOUNT; i++) {
	            audioPaths[i] = "Sound/"+ i + ".wav";
	        }
	      
	    }
	    

	    private void load(){
	        for (int i = 0; i < audioPaths.length; i++) {
	        	if (i < 9) {
	        		gsm.assetManager.load(audioPaths[i], Music.class);
				}else{
					gsm.assetManager.load(audioPaths[i], Sound.class);
				}
	            
	        }
	        gsm.assetManager.load(ATLAS_PATH, TextureAtlas.class);
	        loaded = true;
	    }

	    @Override
	    protected void handleInput(float delta) {

	    }

	    @Override
	    public void update(float delta) {
	        if (!loaded){
	            load();
	        }
	        System.out.println(counter++);
	        gsm.assetManager.update();
	        if (gsm.assetManager.getProgress() >= 1){
	        	
	            gsm.set(new MenuState(gsm,gsm.assetManager.get(LoadingState.ATLAS_PATH, TextureAtlas.class)));
	            dispose();
	            
	        }
	    }

	    @Override
	    public void render(SpriteBatch batch) {
	        Gdx.gl.glClearColor(1, 0, 0, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        cam.update();
	        batch.setProjectionMatrix(cam.combined);
	        batch.begin();
	        batch.end();

	    }

	    @Override
	    public void dispose() {

	    }

	    @Override
	    public void resize(int width, int height) {
	        viewport.update(width, height);

	    }

}
