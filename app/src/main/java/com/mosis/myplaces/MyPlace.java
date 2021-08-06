package com.mosis.myplaces;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class MyPlace {
    String name;
    String description;
    String longitude;
    String latitude;
    @Exclude
    public String key;
    int ID;

    public MyPlace() {}

    public MyPlace(String name, String desc) {
        this.name = name;
        this.description = desc;
    }

    public MyPlace(String name, String desc, String lon, String lat) {
        this.name = name;
        this.description = desc;
        this.longitude = lon;
        this.latitude = lat;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public MyPlace(String name) {
        this(name, "");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
