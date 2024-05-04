package com.example.project.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Command {
	
	@Id private String id;
	private String text;
	private String username;
	private LocalDateTime date;
	@DBRef private SportPlace sportPlace;
	
	public Command() {
		// TODO Auto-generated constructor stub
	}

	public Command(String id, String text, LocalDateTime date, String username, SportPlace sportPlace) {
		super();
		this.id = id;
		this.text = text;
		this.date = date;
		this.username = username;
		this.sportPlace = sportPlace;
	}

	public Command(String text, LocalDateTime date, String username, SportPlace sportPlace) {
		super();
		this.text = text;
		this.date = date;
		this.username = username;
		this.sportPlace = sportPlace;
	}

	@Override
	public String toString() {
		return "Command [id=" + id + ", text=" + text + ", date=" + date + ", username=" + username + ", sportPlace="
				+ sportPlace + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public SportPlace getSportPlace() {
		return sportPlace;
	}

	public void setSportPlace(SportPlace sportPlace) {
		this.sportPlace = sportPlace;
	}
	
	
}
