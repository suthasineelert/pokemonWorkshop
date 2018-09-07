package com.workshop.model;

public class HuntTransaction {
	private int id;
	private int userId;
	private int pokemonId;
	private String dateTime;
	private int earnExp;
	private int playerWinCount;
	private int pokemonWinCount;
	private String pokemonName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPokemonId() {
		return pokemonId;
	}
	public void setPokemonId(int pokemonId) {
		this.pokemonId = pokemonId;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public int getEarnExp() {
		return earnExp;
	}
	public void setEarnExp(int earnExp) {
		this.earnExp = earnExp;
	}
	public int getPlayerWinCount() {
		return playerWinCount;
	}
	public void setPlayerWinCount(int playerWinCount) {
		this.playerWinCount = playerWinCount;
	}
	public int getPokemonWinCount() {
		return pokemonWinCount;
	}
	public void setPokemonWinCount(int pokemonWinCount) {
		this.pokemonWinCount = pokemonWinCount;
	}
	public String getPokemonName() {
		return pokemonName;
	}
	public void setPokemonName(String pokemonName) {
		this.pokemonName = pokemonName;
	}
	
}
