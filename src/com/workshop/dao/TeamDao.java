package com.workshop.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.workshop.model.Team;

public interface TeamDao {
   public Team getTeam(int teamId);
	public List<Team> getTeamList();
	List<Map<String, Object>> getPlayerandPokemonCount(int teamId);
}
