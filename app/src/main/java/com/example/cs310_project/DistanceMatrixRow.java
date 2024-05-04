package com.example.cs310_project;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DistanceMatrixRow {

    @SerializedName("elements")
    private List<DistanceMatrixElement> elements;

    public List<DistanceMatrixElement> getElements() {
        return elements;
    }
}