package com.tech.proxyapi.utils;

import org.springframework.web.client.RestTemplate;

public class ProxyHandler {

	private static String SwapiBaseUri = "https://swapi.dev/api/";
	
	public ProxyHandler() {}
	

	public static String getSwapiBaseUri() {
		return SwapiBaseUri;
	}

	public String SwapiPersonHandler(String name) {
		
		String uriRequest = getSwapiBaseUri() + "people?search=" + name;
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uriRequest, String.class);
		
		return result;
	}
	
	public String SwapiFilmHandler(Integer filmId) {
		
		String uriRequest = getSwapiBaseUri() + "films/" + filmId;
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uriRequest, String.class);
		
		return result;
	}
	
	public String SwapiVehicleHandler(Integer vehicleId) {
		
		String uriRequest = getSwapiBaseUri() + "vehicles/" + vehicleId;
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uriRequest, String.class);
		
		return result;
	}
	
	public String SwapiStarshipHandler(Integer starshipId) {
		
		String uriRequest = getSwapiBaseUri() + "starships/" + starshipId;
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uriRequest, String.class);
		
		return result;
	}
	
	public String SwapiPlanetHandler(Integer planetId) {
		
		String uriRequest = getSwapiBaseUri() + "planets/" + planetId;
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uriRequest, String.class);
		
		return result;
		
	}
	
}
