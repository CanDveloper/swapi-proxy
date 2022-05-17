package com.tech.proxyapi.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tech.proxyapi.dtos.PersonDto;
import com.tech.proxyapi.services.ProxyApiService;


@RestController
@RequestMapping("/swapi-proxy")
public class ProxyApiRestController {
	
	@Autowired
	ProxyApiService proxyApiService;
	
	@GetMapping("/person-info")
	public PersonDto getPersonInfo(@RequestParam String name ) throws IOException {
	
		
		return proxyApiService.extractingPersonInfo(name);
	}
	
	

}
