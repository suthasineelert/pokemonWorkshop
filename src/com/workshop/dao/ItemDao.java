package com.workshop.dao;

import java.util.List;

import com.workshop.model.Item;

public interface ItemDao {
   public Item getItem(int id);
	public List<Item> getItemList();
}
