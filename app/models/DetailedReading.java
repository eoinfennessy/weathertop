package models;

import static utils.readingConversions.*;
import static utils.readingConversions.weatherIcons;

public class DetailedReading extends Reading {
    String weatherCondition;
    float tempInFahrenheit;
    int beaufortForce;
    String cardinalWindDirection;
    float windChill;
    String weatherIcon;

    public DetailedReading(Reading reading) {
        super(reading);
        weatherCondition = convertWeatherCodeToWeatherCondition(code);
        tempInFahrenheit = convertCelsiusToFahrenheit(temperature);
        beaufortForce = convertKilometresPerHourToBeaufortForce(windSpeed);
        cardinalWindDirection = convertDegreesToCardinalDirection(windDirection);
        windChill = calculateWindChill(temperature, windSpeed);
        weatherIcon = weatherIcons.get(code);
    }
}