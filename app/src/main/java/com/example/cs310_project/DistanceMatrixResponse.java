package com.example.cs310_project;

import com.google.gson.annotations.SerializedName;

import java.time.Duration;
import java.util.List;








public class DistanceMatrixResponse {

    @SerializedName("destination_addresses")
    private List<String> destinationAddresses;

    @SerializedName("origin_addresses")
    private List<String> originAddresses;

    @SerializedName("rows")
    private List<DistanceMatrixRow> rows;

    @SerializedName("status")
    private String status;

    public List<String> getDestinationAddresses() {
        return destinationAddresses;
    }

    public List<String> getOriginAddresses() {
        return originAddresses;
    }

    public List<DistanceMatrixRow> getRows() {
        return rows;
    }

    public String getStatus() {
        return status;
    }
}
