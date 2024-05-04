package com.example.cs310_project;


import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

enum SportType {
    Basketball("Basketball", "🏀", android.graphics.Color.YELLOW),
    Tennis("Tennis", "🎾", android.graphics.Color.GREEN),
    Football("Football", "⚽", android.graphics.Color.BLUE),
    Swimming("Swimming", "🏊", android.graphics.Color.CYAN);

    private String name;

    private String emoji;
    private int color;

    SportType(String name, String emoji, int color) {
        this.name = name;
        this.emoji = emoji;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getEmoji() {
        return emoji;
    }

    public int getColor() {
        return color;
    }
}

public class SportPlace implements Parcelable {

    private String distance;
    private String description;

    double  point;
    private String id;
    private String name;
    private Address address;
    private String googleMapsLink;
    String  image;
    private SportType sportType;

    public SportPlace(String id, String name, SportType sportType,double point,Address address,
                      String googleMapsLink, String image, String distance,String description) {
        this.id = id;
        this.name = name;
        this.sportType = sportType;
        this.address = address;
        this.googleMapsLink = googleMapsLink;
        this.image = image;
        this.point = point;
        this.distance=distance;
        this.description=description;
    }

    protected SportPlace(Parcel in) {
        id = in.readString();
        name = in.readString();
        sportType = stringToSportType(in.readString());
        address = in.readParcelable(Address.class.getClassLoader());
        googleMapsLink = in.readString();
        image = in.readString();
        point = in.readDouble();
        distance=in.readString();
        description = in.readString();

    }

    // Utility method to convert string to SportType enum
    private static SportType stringToSportType(String typeString) {
        for (SportType type : SportType.values()) {
            if (type.getName().equals(typeString)) {
                return type;
            }
        }
        return null; // Handle this as needed
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(sportType != null ? sportType.getName() : null);
        dest.writeParcelable(address, flags);
        dest.writeString(googleMapsLink);
        dest.writeString(image);
        dest.writeDouble(point);
        dest.writeString(distance);
        dest.writeString(description);


    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SportPlace> CREATOR = new Creator<SportPlace>() {
        @Override
        public SportPlace createFromParcel(Parcel in) {
            return new SportPlace(in);
        }

        @Override
        public SportPlace[] newArray(int size) {
            return new SportPlace[size];
        }
    };


    @Override
    public String toString() {
        return "SportPlace [id=" + id + ", name=" + name + ", sportType=" + sportType
                + ", address=" + address + ", googleMapsLink=" + googleMapsLink + ", image=" + image + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public String getDistance() {
        return distance;
    }

    public String setDistance(String distance) {
        return this.distance=distance;}

    public String setDescription(String description) {
        return this.description=description;}
    public String  getDescription() {
        return description;
    }


    public void setName(String name) {
        this.name = name;
    }

    public SportType getSportType() {
        return sportType;
    }

    public double getScore() {
        return point;
    }

    public void setScore(double point) {
        this.point = point;
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

    public String getImage() {
        return image;
    }

    public String setImage(String image) {
       return this.image = image;
    }


}
