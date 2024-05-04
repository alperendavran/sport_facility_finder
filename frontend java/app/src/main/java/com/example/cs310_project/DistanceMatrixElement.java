package com.example.cs310_project;

import com.google.gson.annotations.SerializedName;

import java.time.Duration;

public class DistanceMatrixElement {

    @SerializedName("distance")
    private Distance distance;

    @SerializedName("duration")
    private Duration duration;

    @SerializedName("status")
    private String status;

    public Distance getDistance() {
        return distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public String getStatus() {
        return status;
    }
}