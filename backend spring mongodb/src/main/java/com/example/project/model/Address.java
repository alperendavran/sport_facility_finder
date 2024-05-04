package com.example.project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Address {
	
	
	@Id private String id;
	private String city;
	private String district;
	private String neighbourhood;
	private String street;
	private String buildingNumber;
	
	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(String id, String city, String district, String neighbourhood, String street, String buildingNumber) {
		super();
		this.id = id;
		this.city = city;
		this.district = district;
		this.neighbourhood = neighbourhood;
		this.street = street;
		this.buildingNumber = buildingNumber;
	}
		
	public Address(String city, String district, String neighbourhood, String street, String buildingNumber) {
		super();
		this.city = city;
		this.district = district;
		this.neighbourhood = neighbourhood;
		this.street = street;
		this.buildingNumber = buildingNumber;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", city=" + city + ", district=" + district + ", neighbourhood=" + neighbourhood
				+ ", street=" + street + ", buildingNumber=" + buildingNumber + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}
	
	
}
