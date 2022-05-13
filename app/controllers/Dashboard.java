package controllers;

import models.Member;
import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller {
    public static void index() {
        Logger.info("Rendering dashboard");
        Member member = Accounts.getLoggedInMember();
        member.sortStationsByName();
        List<Station> stations = member.stations;
        for (Station station : stations) {
            station.calculateLatestReading();
            station.calculateMaxTemperature();
            station.calculateMinTemperature();
            station.calculateMaxWindSpeed();
            station.calculateMinWindSpeed();
            station.calculateMaxPressure();
            station.calculateMinPressure();
            station.calculateTrends();
        }
        render ("dashboard.html", stations);
    }

    public static void addStation(String name, float latitude, float longitude) {
        Logger.info("Adding station: " + name);
        Member member = Accounts.getLoggedInMember();
        Station station = new Station(name, latitude, longitude);
        member.stations.add(station);
        member.save();
        redirect("/dashboard");
    }

    public void deleteStation(long id) {
        Station station = Station.findById(id);
        Logger.info("Deleting station: " + station.name);
        Member member = Accounts.getLoggedInMember();
        member.stations.remove(station);
        member.save();
        station.delete();
        redirect("/dashboard");
    }
}
