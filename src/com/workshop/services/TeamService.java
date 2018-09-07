package com.workshop.services;

import java.util.List;

import com.workshop.model.Team;

public interface TeamService { 
	 public List<Team> getTeamList();    
	 public Team getTeam(int id);
	 String[][] getPlayerandPokemonCount();    
}
