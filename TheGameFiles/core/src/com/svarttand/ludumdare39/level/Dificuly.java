package com.svarttand.ludumdare39.level;

public enum Dificuly {
	
	EASY(1,1,1),
	MEDIUM(3,1,1),
	HARD(4,2,2),
	INSANE(10,5,5);
	
	private int stones;
	private int red;
	private int blue;
	
	
	Dificuly(int rocks, int redCars, int blueCars){
		stones = rocks;
		red = redCars;
		blue = blueCars;
	}


	public int getStones() {
		return stones;
	}


	public int getRed() {
		return red;
	}


	public int getBlue() {
		return blue;
	}
	
	

}
