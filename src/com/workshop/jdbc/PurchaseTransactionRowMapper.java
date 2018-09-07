package com.workshop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.workshop.model.PurchaseTransaction;

public class PurchaseTransactionRowMapper implements RowMapper<PurchaseTransaction> {

	@Override  
	 public PurchaseTransaction mapRow(ResultSet resultSet, int line) throws SQLException {  
	  PurchaseTransactionExtractor purchaseTransactionExtractor = new PurchaseTransactionExtractor();  
	  return purchaseTransactionExtractor.extractData(resultSet);  
	 }  

}
