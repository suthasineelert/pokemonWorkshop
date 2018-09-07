package com.workshop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.workshop.model.PurchaseTransaction;

public class PurchaseTransactionExtractor implements ResultSetExtractor<PurchaseTransaction> {

	public PurchaseTransaction extractData(ResultSet resultSet) throws SQLException, DataAccessException {

		PurchaseTransaction purchaseTransaction = new PurchaseTransaction();

		purchaseTransaction.setId(resultSet.getInt(1));
		purchaseTransaction.setUserId(resultSet.getInt(2));
		purchaseTransaction.setItemId(resultSet.getInt(3));
		purchaseTransaction.setDateTime(resultSet.getString(4));
		purchaseTransaction.setTransactionId(resultSet.getString(5));

		return purchaseTransaction;
	}
}
