package controllers;

import models.Member;
import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class StationCtrl extends Controller {
    public void index(long id) {
        Member member = Accounts.getLoggedInMember();
        if (member == null) {
            redirect("/login");
        } else {
            Station station = Station.findById(id);
            if (member.stations.contains(station)) {
                Logger.info("Rendering station: " + station.name);
                station.updateLatestReading();
                station.updateStationAnalytics();
                render("station.html", station);
            } else {
                Logger.info("Logged in user does not have access to station with ID: " + station.id);
                redirect("/login");
            }
        }
    }

    public void addReading(long id, int code, float temperature,
                           float windSpeed, float windDirection, float pressure) {
        Member member = Accounts.getLoggedInMember();
        if (member == null) {
            redirect("/login");
        } else {
            Station station = Station.findById(id);
            if (member.stations.contains(station)) {
                Logger.info("Adding reading to station: " + station.name);
                Reading reading = new Reading(code, temperature, windSpeed, windDirection, pressure);
                reading.save();
                station.readings.add(reading);
                station.save();
                redirect("/stations/" + id);
            } else {
                Logger.info("Logged in user does not have access to station with ID: " + station.id);
                redirect("/login");
            }
        }
    }

    public void deleteReading(long stationId, long readingId) {
        Member member = Accounts.getLoggedInMember();
        if (member == null) {
            redirect("/login");
        } else {
            Station station = Station.findById(stationId);
            Reading reading = Reading.findById(readingId);
            if (member.stations.contains(station) && station.readings.contains(reading)) {
                Logger.info("Deleting reading with ID " + readingId);
                station.readings.remove(reading);
                station.save();
                reading.delete();
                redirect("/stations/" + stationId);
            } else {
                Logger.info("Logged in user does not have access to reading with ID: " + reading.id);
                redirect("/login");
            }
        }
    }
}
