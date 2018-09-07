package com.workshop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.workshop.jdbc.PurchaseTransactionRowMapper;
import com.workshop.model.PurchaseTransaction;

public class PurchaseTransactionDaoImpl implements PurchaseTransactionDao {

	@Autowired
	DataSource dataSource;

	@Override
	public void insertData(PurchaseTransaction purchaseTransaction) {

		String sql = "INSERT INTO WS_PURCHASE_TRANSACTION (USER_ID,ITEM_ID,PURC_DATETIME,TRANSACTION_ID)"
				+ " VALUES (?,?,?,?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql, new Object[] { purchaseTransaction.getUserId(),
				purchaseTransaction.getItemId(), purchaseTransaction.getDateTime(),purchaseTransaction.getTransactionId() });
	}

	@Override
	public void updateData(PurchaseTransaction purchaseTransaction) {
		String sql = "UPDATE WS_PURCHASE_TRANSACTION SET USER_ID=?,ITEM_ID=?,PURC_DATETIME=?,TRANSACTION_ID=?"
				+ " WHERE PURC_TXN_ID=?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql, new Object[] { purchaseTransaction.getUserId(), purchaseTransaction.getItemId(),
				purchaseTransaction.getDateTime(),purchaseTransaction.getTransactionId(), purchaseTransaction.getId() });

	}

	@Override
	public PurchaseTransaction getPurchaseTransaction(int id) {
		List<PurchaseTransaction> purchaseTransactionList = new ArrayList<PurchaseTransaction>();
		String sql = "SELECT * FROM WS_PURCHASE_TRANSACTION WHERE PURC_TXN_ID=" + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		purchaseTransactionList = jdbcTemplate.query(sql, new PurchaseTransactionRowMapper());
		if(purchaseTransactionList.size()>0)
			return purchaseTransactionList.get(0);
		return null;
	}

	@Override
	public List<PurchaseTransaction> getPurchaseTransactionList() {
		List<PurchaseTransaction> purchaseTransactionList = new ArrayList<PurchaseTransaction>();
		String sql = "SELECT * FROM WS_PURCHASE_TRANSACTION";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		purchaseTransactionList = jdbcTemplate.query(sql, new PurchaseTransactionRowMapper());
		return purchaseTransactionList;
	}
	
	@Override
	public List<PurchaseTransaction> getPurchaseTransactionListByUserId(int userId) {
		List<PurchaseTransaction> purchaseTransactionList = new ArrayList<PurchaseTransaction>();
		String sql = "SELECT * FROM WS_PURCHASE_TRANSACTION WHERE USER_ID = "+userId+
				" ORDER BY PURC_DATETIME DESC";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		purchaseTransactionList = jdbcTemplate.query(sql, new PurchaseTransactionRowMapper());
		return purchaseTransactionList;
	}

}
