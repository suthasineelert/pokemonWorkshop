package com.workshop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.workshop.model.Pokedex;

public class PokedexExtractor implements ResultSetExtractor<Pokedex> {
	public Pokedex extractData(ResultSet resultSet) throws SQLException, DataAccessException {

		Pokedex pokedex = new Pokedex();

		pokedex.setUserId(resultSet.getInt(1));
		pokedex.setPokemonId(resultSet.getInt(2));
		pokedex.setHuntCount(resultSet.getInt(3));
		
		return pokedex;
	}
}
