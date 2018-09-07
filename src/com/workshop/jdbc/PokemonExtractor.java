package com.workshop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.workshop.model.Pokemon;

public class PokemonExtractor implements ResultSetExtractor<Pokemon> {

	public Pokemon extractData(ResultSet resultSet) throws SQLException, DataAccessException {

		Pokemon pokemon = new Pokemon();

		pokemon.setId(resultSet.getInt(1));
		pokemon.setName(resultSet.getString(2));
		pokemon.setType(resultSet.getString(3));
		pokemon.setImage(resultSet.getString(4));
		pokemon.setLevel(resultSet.getString(5));
		pokemon.setMinCP(resultSet.getInt(6));
		pokemon.setMaxCP(resultSet.getInt(7));

		return pokemon;
	}
}
