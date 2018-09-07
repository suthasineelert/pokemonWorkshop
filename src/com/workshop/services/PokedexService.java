package com.workshop.services;

import java.util.List;

import com.workshop.model.Pokedex;

public interface PokedexService {
	public void insertOrUpdateData(Pokedex pokedex);
	public Pokedex getPokedex(int userId, int pokemonId);
	public List<Pokedex> getPokedexList();
}
