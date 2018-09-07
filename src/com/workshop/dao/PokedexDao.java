package com.workshop.dao;

import java.util.List;

import com.workshop.model.Pokedex;
import com.workshop.model.User;

public interface PokedexDao {
	public void insertData(Pokedex pokedex);
	public void updateData(Pokedex pokedex);
	public Pokedex getPokedex(int userId, int pokemonId);
	public List<Pokedex> getPokedexList();
}
