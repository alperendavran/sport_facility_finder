package com.example.project.model;

import java.time.LocalDateTime;


public class CommandPayload {
	
	private String text;
	private String username;
	private LocalDateTime date;
	private String sportPlaceId;
	
	public CommandPayload() {
		// TODO Auto-generated constructor stub
	}

	public CommandPayload(String text, String username, LocalDateTime date, String sportPlaceId) {
		super();
		this.text = text;
		this.username = username;
		this.date = date;
		this.sportPlaceId = sportPlaceId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getSportPlaceId() {
		return sportPlaceId;
	}

	public void setSportPlaceId(String sportPlaceId) {
		this.sportPlaceId = sportPlaceId;
	}
	
}
