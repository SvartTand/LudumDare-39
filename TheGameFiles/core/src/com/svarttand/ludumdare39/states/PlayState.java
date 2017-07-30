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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.ludumdare39.Application;
import com.svarttand.ludumdare39.hud.PlayHud;
import com.svarttand.ludumdare39.level.Dificuly;
import com.svarttand.ludumdare39.misc.Explosion;
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
	
	private ArrayList<Obstacle> obstacleList;
	
	private TextureRegion backGround1;
	private TextureRegion backGround2;
	private TextureRegion backGroundExtra;
	
	private TextureRegion city;
	private TextureRegion power;
	private TextureRegion field;
	private float bg1Pos;
	private float bg2Pos;
	private float bgEPos;

	private Random rn;
	private PlayHud hud;
	private ArrayList<Sound> audioList;
	private ArrayList<Music> musicList;
	
	private ShapeRenderer renderer;
	
	private SoundLoops soundLoops;
	
	private ArrayList<Explosion> explosions;
	
	private Dificuly dificuly;
	
	public PlayState(GameStateManager gsm, TextureAtlas atlas, Dificuly dificuly) {
		super(gsm);
		
		this.dificuly = dificuly;
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
        backGroundExtra = atlas.findRegion("Stage");
        bgEPos = bg1Pos - backGroundExtra.getRegionWidth();
        
        explosions = new ArrayList<Explosion>();
        
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
        
        obstacleList = new ArrayList<Obstacle>();
    	rn = new Random();
    	int amount = dificuly.getBlue() + dificuly.getRed() + dificuly.getStones();
        for (int i = 0; i < amount; i++) {

        	int n = OBSTACLE_GAP - MIN_OBSTACLE_GAP + 1;
        	int j = rn.nextInt() % n;
        	if (i <dificuly.getStones()) {
        		obstacleList.add(new Obstacle(new Vector2(i  * (MIN_OBSTACLE_GAP + j), Player.GROUND + Application.V_HEIGHT), ObstacleEnum.STONE, rn, atlas));
			}else if(i<dificuly.getStones() + dificuly.getBlue()){
				obstacleList.add(new Obstacle(new Vector2(i  * (MIN_OBSTACLE_GAP + j), Player.GROUND), ObstacleEnum.MOVING_CAR, rn,atlas));
			}else{
				obstacleList.add(new Obstacle(new Vector2(i  * (MIN_OBSTACLE_GAP + j), Player.GROUND), ObstacleEnum.CAR, rn, atlas));
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
		for (int i = 0; i < explosions.size(); i++) {
			if (!explosions.get(i).update(delta)) {
				explosions.remove(i);
			}
			
		}
		
		handleInput(delta);
		player.update(delta, obstacleList, explosions, atlas);
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
			obstacleList.get(i).update(delta,player,rn,obstacleList, audioList.get(2),explosions, atlas, audioList.get(3));
			if (cam.position.x - (cam.viewportWidth/2) > obstacleList.get(i).getPosition().x + obstacleList.get(i).getBounds().width){
				obstacleList.get(i).reposition(rn, obstacleList, player);
			}
		}
		
		if (cam.position.x - (cam.viewportWidth/2) > bg1Pos + backGround1.getRegionWidth()){
			
			if (bg1Pos >= 3000) {
				bg1Pos += backGround1.getRegionWidth() + backGround2.getRegionWidth();
				backGround1 = field;
				
			}else{
				bg1Pos += backGround1.getRegionWidth() + backGround2.getRegionWidth();
			}
			
			bgEPos = bg2Pos - backGroundExtra.getRegionWidth();
		}
		if (cam.position.x - (cam.viewportWidth/2) > bg2Pos + backGround2.getRegionWidth()){
			
			if (bg2Pos > 3000) {
				backGround2 = field;
				
			}
			if (bg2Pos >= 2500 && bg2Pos <= 3000) {
				backGround2 = power;
				System.out.println("power");
				bg2Pos += city.getRegionWidth() + backGround1.getRegionWidth();
			}else{
				bg2Pos += backGround2.getRegionWidth() + backGround1.getRegionWidth();
			}
			bgEPos = bg1Pos - backGroundExtra.getRegionWidth();
			
			
		}
		
		soundLoops.update(player.getPosition(), delta, player.getVelocity());
		
		if (player.getHp()<= 0) {
			gsm.set(new GameOverState(gsm, atlas, (player.getPosition().x - 300)/10, dificuly));
			soundLoops.dispose();
		}
		
		
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
        batch.draw(backGroundExtra, bgEPos, 20);
        for (int i = 0; i < obstacleList.size(); i++) {
        	if (obstacleList.get(i).getType().getAnimation()) {
        		batch.draw(obstacleList.get(i).getFrame(), obstacleList.get(i).getPosition().x, obstacleList.get(i).getPosition().y);
			}else{
				batch.draw(atlas.findRegion(obstacleList.get(i).getPath()), obstacleList.get(i).getPosition().x, obstacleList.get(i).getPosition().y);
			}
			
		}
        batch.draw(player.getTexturePath(),
        	player.getPosition().x,
        	player.getPosition().y);
        for (int i = 0; i < explosions.size(); i++) {
			batch.draw(atlas.findRegion(explosions.get(i).getTextureName()), explosions.get(i).getPosition().x, explosions.get(i).getPosition().y);
		}
        
        batch.end();
        
		hud.render(renderer, player.isHit());
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
