package com.tech.proxyapi.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tech.proxyapi.dtos.ErrorsDto;
import com.tech.proxyapi.dtos.PersonDto;
import com.tech.proxyapi.exceptions.EmptySearchException;
import com.tech.proxyapi.services.ProxyApiService;

@RestController
@RequestMapping("/swapi-proxy")
public class ProxyApiRestController {

	@Autowired
	ProxyApiService proxyApiService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/person-info")
	public PersonDto getPersonInfo(@RequestParam String name) throws IOException, EmptySearchException {

		return proxyApiService.extractingPersonInfo(name);
	}

	@ExceptionHandler(EmptySearchException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorsDto handleEmptySearchException(EmptySearchException exception) {

		String errorMessage = "No existe el personaje indicado";

		return new ErrorsDto(errorMessage);
	}

}