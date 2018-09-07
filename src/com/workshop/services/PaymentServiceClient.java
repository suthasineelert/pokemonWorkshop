package com.workshop.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.workshop.model.Payment;

public class PaymentServiceClient {

	public HashMap<String,String> inquiryAccount(String accountNumber, String pin, String amount)
	{
	    final String url = "http://192.168.9.154:8080/PaymentGWWeb/PaymentGatewayService/Inquiry";
	    
	    RestTemplate restTemplate = new RestTemplate();
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    Map<String,String> map = new HashMap<>();
	    map.put("accountNumber", accountNumber);
	    map.put("pin", pin);
	    map.put("amount", amount);
	    
	    String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println(json);
	    
	    String response = restTemplate.postForObject( url, json , String.class );
	 
	    System.out.println(response);
	    
	    HashMap<String,String> obj = new Gson().fromJson(response, new TypeToken<HashMap<String, String>>(){}.getType());
	    
	    return obj;
	}
	
	public HashMap<String,String> makePayment(String accountNumber, String pin, String amount,String transactionID)
	{
	    final String url = "http://192.168.9.154:8080/PaymentGWWeb/PaymentGatewayService/Payment";
	    
	    RestTemplate restTemplate = new RestTemplate();
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    Map<String,String> map = new HashMap<>();
	    map.put("accountNumber", accountNumber);
	    map.put("pin", pin);
	    map.put("amount", amount);
	    map.put("transactionID", transactionID);
	    
	    String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println(json);
	    
	    String response = restTemplate.postForObject( url, json , String.class );
	 
	    System.out.println(response);
	    
	    HashMap<String,String> obj = new Gson().fromJson(response, new TypeToken<HashMap<String, String>>(){}.getType());
	    
	    return obj;
	}
	
}
