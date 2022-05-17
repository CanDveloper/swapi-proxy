package com.tech.proxyapi.services;

import java.io.IOException;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.tech.proxyapi.dtos.PersonDto;

public class ProxyApiServiceImpl implements ProxyApiService{

	@Override
	public String extractingPersonInfo(String name)
			throws JsonMappingException, JsonProcessingException, IOException {
		
		String uriRequest = "https://swapi.dev/api/" + "people?search=" + name;
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uriRequest, String.class);
		
		return result;
	}

}
