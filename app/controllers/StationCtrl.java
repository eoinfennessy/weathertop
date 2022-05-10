package controllers;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class StationCtrl extends Controller {
    public void index(long id) {
        Station station = Station.findById(id);
        Logger.info("Rendering station: " + station.name);
        station.calculateLatestReading();
        station.calculateMaxTemperature();
        station.calculateMinTemperature();
        station.calculateMaxWindSpeed();
        station.calculateMinWindSpeed();
        station.calculateMaxPressure();
        station.calculateMinPressure();
        render("station.html", station);
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
