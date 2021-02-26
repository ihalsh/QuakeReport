package com.example.android.quakereport;

public class Earthquake {

    private Double magnitude;
    private String cityLocation;
    private Long time;
    private String url;

    public Earthquake(Double magnitude, String cityLocation, Long time, String url) {
        this.magnitude = magnitude;
        this.cityLocation = cityLocation;
        this.time = time;
        this.url = url;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public String getCityLocation() {
        return cityLocation;
    }

    public Long getTime() {
        return time;
    }

    public String getUrl() {
        return url;
    }
}




