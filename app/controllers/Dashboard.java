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
    member.sortStationsByName();
    List<Station> stations = member.stations;
    for (Station station : stations) {
      station.updateLatestReading();
      station.updateStationAnalytics();
    }
    render ("dashboard.html", stations);
  }

  public static void addStation(String name, float latitude, float longitude) {
    Member member = Accounts.getLoggedInMember();
    if (member == null) {
      redirect("/login");
    } else {
      Logger.info("Adding station: " + name);
      Station station = new Station(name, latitude, longitude);
      member.stations.add(station);
      member.save();
      redirect("/dashboard");
    }
  }

  public void deleteStation(long id) {
    Member member = Accounts.getLoggedInMember();
    if (member == null) {
      redirect("/login");
    } else {
      Station station = Station.findById(id);
      if (member.stations.contains(station)) {
        Logger.info("Deleting station: " + station.name);
        member.stations.remove(station);
        member.save();
        station.delete();
      } else {
        Logger.info("Logged in user does not have permission to delete this station");
      }
      redirect("/dashboard");
    }
  }
}