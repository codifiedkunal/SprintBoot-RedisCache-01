package com.kworld.bootredis.entity;

public enum SEX {
	MALE("M"), FEMALE("F"), OTHER("O"), NA(null);
	SEX(String value){
		this.value = value;
	}
	private String value;
	public String getValue() {
		return value;
	}
}
