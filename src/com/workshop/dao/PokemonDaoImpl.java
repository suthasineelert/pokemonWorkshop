package com.workshop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.workshop.jdbc.PokemonRowMapper;
import com.workshop.model.Pokemon;

public class PokemonDaoImpl implements PokemonDao {

	@Autowired
	DataSource dataSource;

	@Override
	public void insertData(Pokemon pokemon) {

		String sql = "INSERT INTO WS_POKEMON (POKEMON_NAME,POKEMON_TYPE,POKEMON_IMAGE,"
				+ "POKEMON_LEVEL,POKEMON_MIN_CP,POKEMON_MAX_CP)"
				+ " VALUES (?,?,?,?,?,?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql,
				new Object[] { pokemon.getName(), pokemon.getType(), pokemon.getImage(), pokemon.getLevel(),
						pokemon.getMinCP(), pokemon.getMaxCP() });
	}

	@Override
	public void updateData(Pokemon pokemon) {
		String sql = "UPDATE WS_POKEMON SET POKEMON_NAME=?,POKEMON_TYPE=?,POKEMON_IMAGE=?,"
				+ "POKEMON_LEVEL=?,POKEMON_MIN_CP=?,POKEMON_MAX_CP=? WHERE POKEMON_ID=?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql,
				new Object[] { pokemon.getName(), pokemon.getType(), pokemon.getImage(), pokemon.getLevel(),
						pokemon.getMinCP(), pokemon.getMaxCP(), pokemon.getId() });

	}

	@Override
	public Pokemon getPokemon(int id) {
		List<Pokemon> pokemonList = new ArrayList<Pokemon>();
		String sql = "SELECT * FROM WS_POKEMON WHERE POKEMON_ID=" + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		pokemonList = jdbcTemplate.query(sql, new PokemonRowMapper());
		return pokemonList.get(0);
	}

	@Override
	public List<Pokemon> getPokemonList() {
		List<Pokemon> pokemonList = new ArrayList<Pokemon>();
		String sql = "SELECT * FROM WS_POKEMON";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		pokemonList = jdbcTemplate.query(sql, new PokemonRowMapper());
		return pokemonList;
	}
	
	@Override
	public Pokemon getRandomPokemon() {
		List<Pokemon> pokemonList = new ArrayList<Pokemon>();
		String sql = "SELECT TOP 1 * FROM WS_POKEMON ORDER BY NEWID()";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		pokemonList = jdbcTemplate.query(sql, new PokemonRowMapper());
		return pokemonList.get(0);
	}

}
