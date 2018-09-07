package com.workshop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.workshop.model.HuntTransaction;

public class HuntTransactionExtractor implements ResultSetExtractor<HuntTransaction> {

	public HuntTransaction extractData(ResultSet resultSet) throws SQLException, DataAccessException {

		HuntTransaction huntTransaction = new HuntTransaction();

		huntTransaction.setId(resultSet.getInt(1));
		huntTransaction.setUserId(resultSet.getInt(2));
		huntTransaction.setPokemonId(resultSet.getInt(3));
		huntTransaction.setDateTime(resultSet.getString(4));
		huntTransaction.setEarnExp(resultSet.getInt(5));
		huntTransaction.setPlayerWinCount(resultSet.getInt(6));
		huntTransaction.setPokemonWinCount(resultSet.getInt(7));
		huntTransaction.setPokemonName(resultSet.getString(9));

		return huntTransaction;
	}
}
