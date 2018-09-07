package com.workshop.dao;

import java.util.List;

import com.workshop.model.Pokemon;

public interface PokemonDao {
	public void insertData(Pokemon pokemon);
	public void updateData(Pokemon pokemon);
	public Pokemon getPokemon(int id);
	public List<Pokemon> getPokemonList();
	public Pokemon getRandomPokemon();
}
