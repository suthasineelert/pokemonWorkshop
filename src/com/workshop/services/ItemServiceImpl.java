package com.workshop.services;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.workshop.dao.ItemDao;
import com.workshop.dao.TeamDao;
import com.workshop.model.Item;
import com.workshop.model.Team;

public class ItemServiceImpl implements ItemService {

	@Autowired  
	ItemDao itemdao; 
	
	@Override
	public List<Item> getItemList() {
		return itemdao.getItemList();  
	}

	@Override
	public Item getItem(int id) {
		return itemdao.getItem(id); 
	}

}
