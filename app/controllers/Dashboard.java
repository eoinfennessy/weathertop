package controllers;

import models.Station;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller {
    public static void index() {
        Logger.info("Rendering dashboard");
        List<Station> stations = Station.findAll();
        render ("dashboard.html", stations);
    }

    public static void addStation(String name) {
        Logger.info("Adding station: " + name);
        Station station = new Station(name);
        station.save();
        redirect("/dashboard");
    }
}
