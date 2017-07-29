package com.svarttand.ludumdare39.states;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.ludumdare39.Application;
import com.svarttand.ludumdare39.level.Ground;
import com.svarttand.ludumdare39.objects.Obstacle;
import com.svarttand.ludumdare39.objects.ObstacleEnum;
import com.svarttand.ludumdare39.objects.Player;

public class PlayState extends State{
	
	
	private static final int GROUND_SIZE = 32;
	private static final int OBSTACLE_GAP = 400;
	private Viewport viewport;
	private TextureAtlas atlas;
	
	private Player player;
	
	private ArrayList<Ground> groundList;
	private ArrayList<Obstacle> obstacleList;
	
	private TextureRegion backGround1;
	private TextureRegion backGround2;
	private float bg1Pos;
	private float bg2Pos;

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
        
        backGround1 = atlas.findRegion("CityBG");
        bg1Pos = 0;
        backGround2 = atlas.findRegion("CityBG");
        bg2Pos = backGround2.getRegionWidth();
        
        
        groundList = new ArrayList<Ground>();
        obstacleList = new ArrayList<Obstacle>();
        
        for (int i = 0; i < 3; i++) {
			obstacleList.add(new Obstacle(new Vector2(i  * OBSTACLE_GAP, player.GROUND), ObstacleEnum.CAR));
		}
        
        for (int i = 0; i < (GROUND_SIZE*2 + Application.V_WIDTH)/GROUND_SIZE; i++) {
			groundList.add(new Ground(new Vector2(i*GROUND_SIZE, 50-GROUND_SIZE), "RoadCity"));
		}
        resize(600, 400);
        
	}

	@Override
	protected void handleInput(float delta) {
		
		
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			player.upPressed(delta);
			
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
		//cam.position.y = player.getPosition().y;
		for (int i = 0; i < groundList.size(); i++) {
			if (cam.position.x - (cam.viewportWidth/2) > groundList.get(i).getPosition().x + GROUND_SIZE){
				groundList.get(i).updateX(groundList.get(i).getPosition().x + groundList.size() * GROUND_SIZE);
				System.out.println("It changed" + groundList.size());
			}
		}
		for (int i = 0; i < obstacleList.size(); i++) {
			if (cam.position.x - (cam.viewportWidth/2) > obstacleList.get(i).getPosition().x + obstacleList.get(i).getBounds().width){
				obstacleList.get(i).setPosition(obstacleList.get(i).getPosition().x + obstacleList.size() * OBSTACLE_GAP);
				System.out.println("It changed" + obstacleList.size());
			}
		}
		if (cam.position.x - (cam.viewportWidth/2) > bg1Pos + backGround1.getRegionWidth()){
			bg1Pos += backGround1.getRegionWidth()*2;
		}
		if (cam.position.x - (cam.viewportWidth/2) > bg2Pos + backGround1.getRegionWidth()){
			bg2Pos += backGround1.getRegionWidth()*2;
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
