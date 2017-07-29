package com.svarttand.ludumdare39.objects;

public enum PlanetEnum {
	PLANET("BigPlanet", 30000, 128, false);
	
	private String path;
	private float mass;
	private float size;
	private boolean landable;
	
	PlanetEnum(String path, float mass, float size, boolean landable){
		this.path = path;
		this.mass = mass;
		this.size = size;
		this.landable = landable;
	}

	public String getPath() {
		return path;
	}

	public float getMass() {
		return mass;
	}

	public float getSize() {
		return size;
	}

	public boolean isLandable() {
		return landable;
	}
	

}
