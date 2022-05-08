package controllers;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;

import static utils.readingConversions.*;

public class StationCtrl extends Controller {
    public void index(long id) {
        Station station = Station.findById(id);
        Logger.info("Rendering station: " + station.name);

        // Get most recent reading (last element of station.readings)
        Reading latestReading = null;
        String weatherCondition = null;
        float tempInFahrenheit = 0;
        int beaufortForce = 0;
        String cardinalWindDirection = null;
        float windChill = 0;
        if (!station.readings.isEmpty()) {
            latestReading = station.readings.get(station.readings.size() -1);
            weatherCondition = convertWeatherCodeToWeatherCondition(latestReading.code);
            tempInFahrenheit = convertCelsiusToFahrenheit(latestReading.temperature);
            beaufortForce = convertKilometresPerHourToBeaufortForce(latestReading.windSpeed);
            cardinalWindDirection = convertDegreesToCardinalDirection(latestReading.windDirection);
            windChill = calculateWindChill(latestReading.temperature, latestReading.windSpeed);
        }
        render("station.html", station, latestReading, weatherCondition, tempInFahrenheit,
                beaufortForce, cardinalWindDirection, windChill);
    }

    public void addReading(long id, int code, float temperature, float windSpeed, float windDirection, float pressure) {
        Station station = Station.findById(id);
        Logger.info("Adding reading to station: " + station.name);
        Reading reading = new Reading(code, temperature, windSpeed, windDirection, pressure);
        reading.save();
        station.readings.add(reading);
        station.save();
        redirect("/stations/" + id);
    }
}
