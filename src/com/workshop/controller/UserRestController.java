package com.workshop.controller;
 
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.model.User;
import com.workshop.services.PokeballReloadService;
import com.workshop.services.UserService;

@RequestMapping("/rest/")
@RestController
public class UserRestController {
 
	@Autowired
	UserService userService;
	@Autowired
	PokeballReloadService reload;
	
    @RequestMapping("/userInfo/{id}")
    public User message(@PathVariable String id) {//REST Endpoint.
    	User user = userService.getUser(Integer.parseInt(id));
    	//check pokeball
    	user = reload.checkPokeball(user);
    	return user;
    }
    
    @RequestMapping("/userInfo/username/{username}")
    public String getUserByUsername(@PathVariable String username) {//REST Endpoint.
    	User user = userService.getUser(username);
    	if(user == null) {
    		System.out.println("null");
    		return "ok";
    	}
    	
    	return "not ok";
    }
    
}