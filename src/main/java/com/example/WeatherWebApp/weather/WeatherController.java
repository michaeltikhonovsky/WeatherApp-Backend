package com.example.WeatherWebApp.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/weather")
@CrossOrigin(origins = "http://localhost:3000")
public class WeatherController {

    @Value("${weather.api.key}")
    private String weatherApiKey;

    @GetMapping
    public ResponseEntity<?> getWeather(@RequestParam("city") String city) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + weatherApiKey;

        try {
            ResponseEntity<Map> responseEntity = restTemplate.getForEntity(apiUrl, Map.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                Map<String, Object> responseBody = responseEntity.getBody();
                if (responseBody != null) {
                    String cityName = (String) responseBody.get("name");
                    System.out.println(cityName);
                    Map<String, Object> mainData = (Map<String, Object>) responseBody.get("main");
                    Map<Double, Object> windData = (Map<Double, Object>) responseBody.get("wind");
                    List<Map<String, Object>> weatherDataList = (List<Map<String, Object>>) responseBody.get("weather");
                    Map<String, Object> weatherData = weatherDataList.get(0);
                    double temperature = (double) mainData.get("temp");
                    int humidity = (int) mainData.get("humidity");
                    String weatherStatus = (String) weatherData.get("main");
                    Double windSpeed = (Double) windData.get("speed");
                    WeatherResponse weatherResponse = new WeatherResponse();
                    weatherResponse.setWind(windSpeed);
                    weatherResponse.setHumidity(humidity);
                    weatherResponse.setCity(cityName);
                    weatherResponse.setTemperature(temperature);
                    weatherResponse.setWeatherStatus(weatherStatus);
                    return ResponseEntity.ok(weatherResponse);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
    }

}
