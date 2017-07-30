package com.svarttand.ludumdare39.states;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.ludumdare39.Application;
import com.svarttand.ludumdare39.hud.PlayHud;
import com.svarttand.ludumdare39.level.Ground;
import com.svarttand.ludumdare39.misc.SoundLoops;
import com.svarttand.ludumdare39.objects.Obstacle;
import com.svarttand.ludumdare39.objects.ObstacleEnum;
import com.svarttand.ludumdare39.objects.Player;

public class PlayState extends State{
	
	
	public static final int GROUND_SIZE = 32;
	public static final int OBSTACLE_GAP = 500;
	public static final int MIN_OBSTACLE_GAP = Application.V_WIDTH;
	private Viewport viewport;
	private TextureAtlas atlas;
	
	private Player player;
	
	private ArrayList<Ground> groundList;
	private ArrayList<Obstacle> obstacleList;
	
	private TextureRegion backGround1;
	private TextureRegion backGround2;
	
	private TextureRegion city;
	private TextureRegion power;
	private TextureRegion field;
	private float bg1Pos;
	private float bg2Pos;

	private Random rn;
	private PlayHud hud;
	private ArrayList<Sound> audioList;
	private ArrayList<Music> musicList;
	
	private ShapeRenderer renderer;
	
	private SoundLoops soundLoops;
	
	public PlayState(GameStateManager gsm, TextureAtlas atlas) {
		super(gsm);
		
		player = new Player(atlas);
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT, cam);
		cam.position.x = player.getPosition().x;
		cam.position.y = 120;
        cam.update();
        viewport.apply();
        this.atlas = atlas;
        resize(Application.V_WIDTH, Application.V_HEIGHT);
        hud = new PlayHud(viewport, cam, atlas);
        
        renderer = new ShapeRenderer();
        
        city = atlas.findRegion("CityBG");
        field = atlas.findRegion("FieldBG");
        power = atlas.findRegion("PowerBG");
        
        backGround1 = city;
        bg1Pos = 0;
        backGround2 = city;
        bg2Pos = backGround2.getRegionWidth();
        
        audioList = new ArrayList<Sound>();
        musicList = new ArrayList<Music>();
        for (int i = 0; i < LoadingState.AUDIO_AMOUNT; i++) {
        	if (i < 9) {
        		musicList.add(gsm.assetManager.get("Sound/"+ i + ".wav",Music.class));
			}else{
				audioList.add(gsm.assetManager.get("Sound/"+ i + ".wav",Sound.class));
			}
			
		}
        soundLoops = new SoundLoops(audioList, musicList);
        
        groundList = new ArrayList<Ground>();
        obstacleList = new ArrayList<Obstacle>();
    	rn = new Random();
        for (int i = 0; i < 4; i++) {

        	int n = OBSTACLE_GAP - MIN_OBSTACLE_GAP + 1;
        	int j = rn.nextInt() % n;
        	if (i <2) {
        		obstacleList.add(new Obstacle(new Vector2(i  * (MIN_OBSTACLE_GAP + j), player.GROUND + Application.V_HEIGHT), ObstacleEnum.STONE));
			}else{
				obstacleList.add(new Obstacle(new Vector2(i  * (MIN_OBSTACLE_GAP + j), player.GROUND), ObstacleEnum.CAR));
			}
			
		}
        
//        for (int i = 0; i < (GROUND_SIZE*2 + Application.V_WIDTH)/GROUND_SIZE; i++) {
//			groundList.add(new Ground(new Vector2(i*GROUND_SIZE, 50-GROUND_SIZE), "RoadCity"));
//		}
        resize(600, 400);
        
	}

	@Override
	protected void handleInput(float delta) {
		
		
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			player.upPressed(delta, audioList.get(0));
			
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			player.downPressed(delta);
			
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			player.leftPressed(delta);
			
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			player.rightPressed(delta);
			
		}
		
	}

	@Override
	public void update(float delta) {
		handleInput(delta);
		player.update(delta, obstacleList);
		cam.position.x = player.getPosition().x;
		hud.update(((int)player.getPosition().x-300)/10, player.getHp());
		//cam.position.y = player.getPosition().y;
//		for (int i = 0; i < groundList.size(); i++) {
//			if (cam.position.x - (cam.viewportWidth/2) > groundList.get(i).getPosition().x + GROUND_SIZE){
//				groundList.get(i).updateX(groundList.get(i).getPosition().x + groundList.size() * GROUND_SIZE);
//				System.out.println("It changed" + groundList.size());
//			}
//		}
		for (int i = 0; i < obstacleList.size(); i++) {
			obstacleList.get(i).update(delta,player,rn,obstacleList);
			if (cam.position.x - (cam.viewportWidth/2) > obstacleList.get(i).getPosition().x + obstacleList.get(i).getBounds().width){
				obstacleList.get(i).reposition(rn, obstacleList, player);
				System.out.println("It changed" + obstacleList.size());
			}
		}
		System.out.println(bg1Pos + ", " + bg2Pos);
		if (cam.position.x - (cam.viewportWidth/2) > bg1Pos + backGround1.getRegionWidth()){
			
			if (bg1Pos >= 5000) {
				backGround1 = field;
				bg1Pos += backGround1.getRegionWidth() + backGround2.getRegionWidth()-250;
			}else{
				bg1Pos += backGround1.getRegionWidth() + backGround2.getRegionWidth();
			}
			
		}
		if (cam.position.x - (cam.viewportWidth/2) > bg2Pos + backGround2.getRegionWidth()){
			
			if (bg2Pos >= 5000 && bg2Pos <= 6700) {
				backGround2 = power;
			}
			if (bg2Pos > 6000) {
				backGround2 = field;
			}
			bg2Pos += backGround2.getRegionWidth() + backGround1.getRegionWidth();
		}
		
		
		
		if (player.getHp()<= 0) {
			gsm.set(new GameOverState(gsm, atlas, (player.getPosition().x - 300)/10));
			soundLoops.dispose();
		}
		soundLoops.update(player.getPosition(), delta, player.getVelocity());
		
	}

	@Override
	public void render(SpriteBatch batch) {
		cam.update();
        batch.setProjectionMatrix(cam.combined);
        Gdx.gl.glClearColor(0, (float) 0.6, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(backGround1, bg1Pos, 20);
        batch.draw(backGround2, bg2Pos, 20);
        for (int i = 0; i < groundList.size(); i++) {
			batch.draw(atlas.findRegion(groundList.get(i).getPath()), groundList.get(i).getPosition().x, groundList.get(i).getPosition().y);
		}
        for (int i = 0; i < obstacleList.size(); i++) {
			batch.draw(atlas.findRegion(obstacleList.get(i).getPath()), obstacleList.get(i).getPosition().x, obstacleList.get(i).getPosition().y);
		}
        batch.draw(player.getTexturePath(),
        	player.getPosition().x,
        	player.getPosition().y);
        
        batch.end();
        
		hud.render(renderer);
		renderer.end();
		hud.getStage().draw();
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
