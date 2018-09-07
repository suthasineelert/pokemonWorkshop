package com.workshop.model;

public class Pokedex {
	private int userId;
	private int pokemonId;
	private int huntCount;
	
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
	public int getHuntCount() {
		return huntCount;
	}
	public void setHuntCount(int huntCount) {
		this.huntCount = huntCount;
	}
}
