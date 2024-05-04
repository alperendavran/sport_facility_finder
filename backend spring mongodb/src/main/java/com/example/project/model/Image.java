package com.example.project.model;
public class Image {
	
	
    private String id; // Unique ID
    private byte[] data; // Resim verisi
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}

    // Constructor, getter ve setter metodları...
}