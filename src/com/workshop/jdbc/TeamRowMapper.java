package com.workshop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.workshop.model.Team;

public class TeamRowMapper implements RowMapper<Team> {
	 @Override  
	 public Team mapRow(ResultSet resultSet, int line) throws SQLException {  
	  TeamExtractor teamExtractor = new TeamExtractor();  
	  return teamExtractor.extractData(resultSet);  
	 }  
}
