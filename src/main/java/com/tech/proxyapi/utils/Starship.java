package com.tech.proxyapi.utils;

import java.util.Comparator;

public class Starship implements Comparator<Starship>, Comparable<Starship>{
	
	private String name;
	private Integer max_atmosphering_speed;
	
	public Starship(){}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMax_atmosphering_speed() {
		return max_atmosphering_speed;
	}
	public void setMax_atmosphering_speed(Integer max_atmosphering_speed) {
		this.max_atmosphering_speed = max_atmosphering_speed;
	}

	@Override
	public int compareTo(Starship o) {
		return (this.name).compareTo(o.name);
	}

	@Override
	public int compare(Starship o1, Starship o2) {
		return o1.max_atmosphering_speed - o2.max_atmosphering_speed;
	}


}
