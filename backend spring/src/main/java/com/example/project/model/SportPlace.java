package com.example.project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SportPlace {
    @Id 
    private String id;
    private String description;
    private String name;
    private String sportType;
    private double totalRating;
    private int totalUsers;
    private double averageRating;
    private Address address;
    private String googleMapsLink;
    private String pictureLink;
    private String distance;
    private String image;

    public SportPlace() {
        this.totalRating = 0;
        this.totalUsers = 0;
        this.averageRating = 0;
    }

    // Constructor with parameters
    public SportPlace(String id, String name, String sportType, Address address, 
                      String googleMapsLink, String image, String distance, String description) {
        super();
        this.id = id;
        this.name = name;
        this.sportType = sportType;
        this.address = address;
        this.googleMapsLink = googleMapsLink;
        this.image = image;
        this.distance = distance;
        this.description = description;
    }
    
    public SportPlace (String name, String sportType, Address address, 
            String googleMapsLink, String image, String distance, String description) {
		super();
		this.name = name;
		this.sportType = sportType;
		this.address = address;
		this.googleMapsLink = googleMapsLink;
		this.image = image;
		this.distance = distance;
		this.description = description;
}

    // Add a rating to the sport place
    public void addRating(double rating) {
        this.totalRating += rating;
        this.totalUsers++;
        updateAverageRating();
    }

    // Update the average rating based on total ratings and number of users
    private void updateAverageRating() {
        this.averageRating = (totalUsers > 0) ? (totalRating / totalUsers) : 0;
    }
    
    // Getters and Setters
    public double getAverageRating() {
        return averageRating;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    
	public String getSportType() {
		return sportType;
	}

	public void setSportType(String sportType) {
		this.sportType = sportType;
	}


	public Address getAddress() {
		return address;
	}
	

    
	public void setAddress(Address address) {
		this.address = address;
	}

	public String getGoogleMapsLink() {
		return googleMapsLink;
	}

	public void setGoogleMapsLink(String googleMapsLink) {
		this.googleMapsLink = googleMapsLink;
	}

	public String getPictureLink() {
		return pictureLink;
	}

	public void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
