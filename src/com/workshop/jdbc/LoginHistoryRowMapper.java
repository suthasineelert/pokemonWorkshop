package com.workshop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.workshop.model.LoginHistory;

public class LoginHistoryRowMapper implements RowMapper<LoginHistory> {

	@Override  
	 public LoginHistory mapRow(ResultSet resultSet, int line) throws SQLException {  
	  LoginHistoryExtractor loginHistoryExtractor = new LoginHistoryExtractor();  
	  return loginHistoryExtractor.extractData(resultSet);  
	 }  

}
