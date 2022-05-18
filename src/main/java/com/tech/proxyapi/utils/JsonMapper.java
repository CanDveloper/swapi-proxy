package com.tech.proxyapi.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.tech.proxyapi.dtos.FilmDto;
import com.tech.proxyapi.dtos.PersonDto;
import com.tech.proxyapi.exceptions.EmptySearchException;

public class JsonMapper {

	public JsonMapper() {
	}

	public PersonDto JsonToPersonDtoMapper(String jsonString) throws IOException, EmptySearchException {

		ArrayList<Integer> filmIdList = new ArrayList<Integer>();
		ArrayList<FilmDto> personFilmList = new ArrayList<FilmDto>();

		ArrayList<Integer> vehicleIdList = new ArrayList<Integer>();
		ArrayList<Integer> starshipIdList = new ArrayList<Integer>();

		PersonDto personDto = new PersonDto();
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectReader reader = objectMapper.readerFor(new TypeReference<ArrayList<String>>() {
		});

		JsonNode jsonTreePersonObject = objectMapper.readTree(jsonString);

		JsonNode resultsNode = jsonTreePersonObject.path("results").get(0);

		if (resultsNode == null) {

			throw new EmptySearchException("No existe el personaje indicado");
		} else {

			personDto.setName(resultsNode.path("name").asText());
			personDto.setBirth_year(resultsNode.path("birth_year").asText());
			personDto.setGender(resultsNode.path("gender").asText());

			Integer planetId = GetIdentifier(resultsNode.path("homeworld").asText());
			personDto.setPlanet_name(GetPlanetName(planetId));

			JsonNode jsonFilmsNode = resultsNode.path("films");

			ArrayList<String> filmListNode = reader.readValue(jsonFilmsNode);
			filmIdList = GetFilmIdList(filmListNode);
			personFilmList = GetFilmograpy(filmIdList);
			personDto.setFilms(personFilmList);

			JsonNode jsonVehiclesNode = resultsNode.path("vehicles");
			JsonNode jsonStarshipsNode = resultsNode.path("starships");

			ArrayList<String> vehicleListNode = reader.readValue(jsonVehiclesNode);
			vehicleIdList = GetVehicleIdList(vehicleListNode);

			ArrayList<String> starshipListNode = reader.readValue(jsonStarshipsNode);
			starshipIdList = GetStarshipIdList(starshipListNode);

			if (vehicleIdList.contains(0) && starshipIdList.contains(0)) {

				personDto.setFastest_vehicle_driven("none");

			} else if (vehicleIdList.contains(0) && !starshipIdList.contains(0)) {

				Starship fastestStarship = GetFastestStarship(starshipIdList);
				personDto.setFastest_vehicle_driven(fastestStarship.getName());

			} else if (!vehicleIdList.contains(0) && starshipIdList.contains(0)) {

				Vehicle fastestVehicle = GetFastestVehicle(vehicleIdList);
				personDto.setFastest_vehicle_driven(fastestVehicle.getName());
			} else {

				Vehicle fastestVehicle = GetFastestVehicle(vehicleIdList);
				Starship fastestStarship = GetFastestStarship(starshipIdList);
				String fastestVehicleName = CheckFastest(fastestVehicle, fastestStarship);
				personDto.setFastest_vehicle_driven(fastestVehicleName);

			}

			return personDto;

		}

	}

	private Integer GetIdentifier(String str) {

		String identifier = "";

		if (str != null) {

			identifier = str.replaceAll("[^0-9]", "");
		}

		if (identifier != "") {

			return Integer.parseInt(identifier);

		} else
			return 0;
	}

	private String CheckFastest(Vehicle vehicle, Starship starship) {

		if (vehicle.getMax_atmosphering_speed() > starship.getMax_atmosphering_speed()) {

			return vehicle.getName();
		} else
			return starship.getName();
	}

	private String GetPlanetName(Integer planetId) throws JsonMappingException, JsonProcessingException {

		ProxyHandler proxyHandler = new ProxyHandler();
		String jsonPlanetResult = proxyHandler.SwapiPlanetHandler(planetId);

		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonTreeObject = objectMapper.readTree(jsonPlanetResult);
		String planetName = jsonTreeObject.path("name").asText();

		return planetName;
	}

	private ArrayList<Integer> GetFilmIdList(ArrayList<String> filmJsonStringNode) {

		ArrayList<Integer> filmIdList = new ArrayList<Integer>();

		for (String filmNode : filmJsonStringNode) {

			Integer filmId = GetIdentifier(filmNode);
			filmIdList.add(filmId);
		}
		return filmIdList;
	}

	private ArrayList<Integer> GetVehicleIdList(ArrayList<String> vehicleJsonStringNode) {

		ArrayList<Integer> vehicleIdList = new ArrayList<Integer>();

		for (String vehicleNode : vehicleJsonStringNode) {

			Integer vehicleId = GetIdentifier(vehicleNode);
			vehicleIdList.add(vehicleId);
		}

		if (vehicleIdList.isEmpty()) {
			vehicleIdList.add(0);
			return vehicleIdList;
		} else
			return vehicleIdList;
	}

	private ArrayList<Integer> GetStarshipIdList(ArrayList<String> starshipJsonStringNode) {

		ArrayList<Integer> starshipIdList = new ArrayList<Integer>();

		for (String starshipNode : starshipJsonStringNode) {

			Integer starshipId = GetIdentifier(starshipNode);
			starshipIdList.add(starshipId);
		}

		if (starshipIdList.isEmpty()) {
			starshipIdList.add(0);
			return starshipIdList;
		} else
			return starshipIdList;
	}

	private ArrayList<FilmDto> GetFilmograpy(ArrayList<Integer> filmIdList)
			throws JsonMappingException, JsonProcessingException {

		ProxyHandler proxyHandler = new ProxyHandler();
		ObjectMapper objectMapper = new ObjectMapper();
		ArrayList<FilmDto> filmography = new ArrayList<FilmDto>();

		for (Integer filmId : filmIdList) {

			String jsonFilmResult = proxyHandler.SwapiFilmHandler(filmId);
			JsonNode jsonTreeFilmObject = objectMapper.readTree(jsonFilmResult);

			FilmDto film = new FilmDto();
			film.setName(jsonTreeFilmObject.path("title").asText());
			film.setRelease_date(jsonTreeFilmObject.path("release_date").asText());

			filmography.add(film);
		}

		return filmography;
	}

	private Vehicle GetFastestVehicle(ArrayList<Integer> vehicleIdList)
			throws JsonMappingException, JsonProcessingException {

		ProxyHandler proxyHandler = new ProxyHandler();
		ObjectMapper objectMapper = new ObjectMapper();
		ArrayList<Vehicle> vehiclesDriven = new ArrayList<Vehicle>();

		for (Integer vehicleId : vehicleIdList) {

			String jsonVehicleResult = proxyHandler.SwapiVehicleHandler(vehicleId);
			JsonNode jsonTreeVehicleObject = objectMapper.readTree(jsonVehicleResult);

			Vehicle vehicle = new Vehicle();
			vehicle.setName(jsonTreeVehicleObject.path("name").asText());
			vehicle.setMax_atmosphering_speed(
					GetIdentifier(jsonTreeVehicleObject.path("max_atmosphering_speed").asText()));

			vehiclesDriven.add(vehicle);
		}

		Collections.sort(vehiclesDriven, new Vehicle());
		return vehiclesDriven.get(vehiclesDriven.size() - 1);

	}

	private Starship GetFastestStarship(ArrayList<Integer> starshipIdList)
			throws JsonMappingException, JsonProcessingException {

		ProxyHandler proxyHandler = new ProxyHandler();
		ObjectMapper objectMapper = new ObjectMapper();
		ArrayList<Starship> starshipsDriven = new ArrayList<Starship>();

		for (Integer starshipId : starshipIdList) {

			String jsonStarshipResult = proxyHandler.SwapiStarshipHandler(starshipId);
			JsonNode jsonTreeStarshipObject = objectMapper.readTree(jsonStarshipResult);

			Starship starship = new Starship();
			starship.setName(jsonTreeStarshipObject.path("name").asText());
			starship.setMax_atmosphering_speed(
					GetIdentifier(jsonTreeStarshipObject.path("max_atmosphering_speed").asText()));

			starshipsDriven.add(starship);
		}

		Collections.sort(starshipsDriven, new Starship());
		return starshipsDriven.get(starshipsDriven.size() - 1);

	}

}
