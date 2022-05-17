package com.tech.proxyapi.services;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.tech.proxyapi.dtos.PersonDto;
import com.tech.proxyapi.utils.JsonMapper;
import com.tech.proxyapi.utils.ProxyHandler;



@Service
public class ProxyApiServiceImpl implements ProxyApiService{

	@Override
	public PersonDto extractingPersonInfo(String name) throws IOException {
		
		ProxyHandler proxyHandler = new ProxyHandler();
		JsonMapper jsonMapper = new JsonMapper();
		
		String jsonResult = proxyHandler.SwapiPersonHandler(name);
		
		
		
		return jsonMapper.JsonToPersonDtoMapper(jsonResult);
	}

}
                                               