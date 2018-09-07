package com.workshop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.workshop.model.HuntTransaction;

public class HuntTransactionRowMapper implements RowMapper<HuntTransaction> {

	@Override  
	 public HuntTransaction mapRow(ResultSet resultSet, int line) throws SQLException {  
	  HuntTransactionExtractor huntTransactionExtractor = new HuntTransactionExtractor();  
	  return huntTransactionExtractor.extractData(resultSet);  
	 }  

}
