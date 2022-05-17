package com.tech.proxyapi.dtos;

public class FilmDto {
	
	private String name;
	private String release_date;
	
	public FilmDto() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	} 
	
}
