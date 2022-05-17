package com.tech.proxyapi.services;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.tech.proxyapi.dtos.PersonDto;

@Service
public interface ProxyApiService {
	
	public String extractingPersonInfo(String name) throws JsonMappingException, JsonProcessingException, IOException;
}
