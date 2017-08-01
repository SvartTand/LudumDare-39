package com.svarttand.ludumdare39.misc;

import java.util.ArrayList;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.svarttand.ludumdare39.objects.Player;

public class SoundLoops {
	
	private Music music;
	private Music footsteps;
	
	private ArrayList<Music> musics;
	private boolean isPlayingMusic;
	
	public SoundLoops(ArrayList<Music> musics){
		isPlayingMusic = false;
		footsteps = musics.get(0);
		this.musics = musics;
		music = musics.get(1);
		
	}
	
	public void update(Vector2 position, float delta, float velocity){
		if (footsteps.isPlaying()) {
			if (position.y > Player.GROUND || velocity == 0) {
				footsteps.pause();
			}
		}
		if (!footsteps.isPlaying()) {
			if (position.y < Player.GROUND && velocity != 0) {
				footsteps.play();
				footsteps.setVolume(1.4f);
			}
		}
		
		musicUpdate(position.x);
	}
	
	private void musicUpdate(float position){
		if (music.isPlaying()) {
			isPlayingMusic = true;
		}else{
			isPlayingMusic = false;
		}
		
		if (!isPlayingMusic) {
			if (position < 2500) {
				music = musics.get(1);
				music.play();
			}else if (position < 5500) {
				music = musics.get(2);
				music.play();
			}else if (position < 8000) {
				music = musics.get(3);
				music.play();
			}else if (position < 12000) {
				music = musics.get(4);
				music.play();
			}else if (position < 15000) {
				music = musics.get(5);
				music.play();
			}else {
				music = musics.get(6);
				music.play();
			}
			music.setVolume(0.7f);
			
		}
		
	}

	public void dispose() {
		music.stop();
		footsteps.stop();
		
		
	}
	

}
