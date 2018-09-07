package com.workshop.model;

public class User {
	private int id;
	private String username;
	private String password;
	private String gender;
	private String playerName;
	private int teamId;
	private int currentLevel;
	private int currentExp;
	private int currentPocketSlot;
	private int maxExp;
	private int maxPocketSlot;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public int getCurrentLevel() {
		return currentLevel;
	}
	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}
	public int getCurrentExp() {
		return currentExp;
	}
	public void setCurrentExp(int currentExp) {
		this.currentExp = currentExp;
	}
	public int getCurrentPocketSlot() {
		return currentPocketSlot;
	}
	public void setCurrentPocketSlot(int currentPocketSlot) {
		this.currentPocketSlot = currentPocketSlot;
	}
	public int getMaxExp() {
		return maxExp;
	}
	public void setMaxExp(int maxExp) {
		this.maxExp = maxExp;
	}
	public int getMaxPocketSlot() {
		return maxPocketSlot;
	}
	public void setMaxPocketSlot(int maxPocketSlot) {
		this.maxPocketSlot = maxPocketSlot;
	}

}
