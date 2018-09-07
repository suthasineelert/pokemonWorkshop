package com.workshop.model;

import java.util.Base64;

public class Team {
	private int id;
	private String name;
	private String color;
	private String image;

	public String getImage() {
		return image;
	}

	public void setImage(String string) {
		this.image = string;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
