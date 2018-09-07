package com.workshop.model;

public class Pokemon {
	private int id;
	private String name;
	private String type;
	private String image;
	private String level;
	private int minCP;
	private int maxCP;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getMinCP() {
		return minCP;
	}
	public void setMinCP(int minCP) {
		this.minCP = minCP;
	}
	public int getMaxCP() {
		return maxCP;
	}
	public void setMaxCP(int maxCP) {
		this.maxCP = maxCP;
	}
	
}
