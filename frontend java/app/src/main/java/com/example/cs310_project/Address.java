package com.example.cs310_project;



import android.os.Parcel;
import android.os.Parcelable;

public class Address implements Parcelable {
    private String id;
    private String city;
    private String district;
    private String neighbourhood;
    private String street;
    private String buildingNumber;

    public Address() {
        // Boş yapıcı metot
    }

    public Address(String id, String city, String district, String neighbourhood, String street, String buildingNumber) {
        this.id = id;
        this.city = city;
        this.district = district;
        this.neighbourhood = neighbourhood;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    protected Address(Parcel in) {
        id = in.readString();
        city = in.readString();
        district = in.readString();
        neighbourhood = in.readString();
        street = in.readString();
        buildingNumber = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(city);
        dest.writeString(district);
        dest.writeString(neighbourhood);
        dest.writeString(street);
        dest.writeString(buildingNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

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
