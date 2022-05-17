package com.tech.proxyapi.services;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.tech.proxyapi.dtos.PersonDto;

public interface ProxyApiService {
	
	public PersonDto extractingPersonInfo(String name) throws JsonMappingException, JsonProcessingException, IOException;

}
