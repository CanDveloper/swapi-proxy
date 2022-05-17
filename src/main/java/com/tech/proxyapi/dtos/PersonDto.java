package com.tech.proxyapi.dtos;

import java.util.ArrayList;

public class PersonDto {
	
	private String name;
	private String birth_year;
	private String gender;
	private String planet_name;
	private String fastest_vehicle_driven;
	private ArrayList<FilmDto> films;
	
	public PersonDto() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth_year() {
		return birth_year;
	}
	public void setBirth_year(String birth_year) {
		this.birth_year = birth_year;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPlanet_name() {
		return planet_name;
	}
	public void setPlanet_name(String planet_name) {
		this.planet_name = planet_name;
	}
	public String getFastest_vehicle_driven() {
		return fastest_vehicle_driven;
	}
	public void setFastest_vehicle_driven(String fastest_vehicle_driven) {
		this.fastest_vehicle_driven = fastest_vehicle_driven;
	}
	public ArrayList<FilmDto> getFilms() {
		return films;
	}
	public void setFilms(ArrayList<FilmDto> films) {
		this.films = films;
	}

}
