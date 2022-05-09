package controllers;

import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller {
    public static void index() {
        Logger.info("Rendering dashboard");
        Member member = Accounts.getLoggedInMember();
        List<Station> stations = member.stations;
        render ("dashboard.html", stations);
    }

    public static void addStation(String name) {
        Logger.info("Adding station: " + name);
        Member member = Accounts.getLoggedInMember();
        Station station = new Station(name);
        member.stations.add(station);
        member.save();
        redirect("/dashboard");
    }
}
