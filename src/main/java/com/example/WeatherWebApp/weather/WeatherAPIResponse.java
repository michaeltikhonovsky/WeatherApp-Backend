package com.example.WeatherWebApp.weather;

public class WeatherAPIResponse {
    private String name;
    private MainWeatherInfo main;

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MainWeatherInfo getMain() {
        return main;
    }

    public void setMain(MainWeatherInfo main) {
        this.main = main;
    }
}

