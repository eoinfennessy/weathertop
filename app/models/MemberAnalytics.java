package models;

import play.db.jpa.Model;

import java.util.Date;

public class MemberAnalytics extends Model {
  public int numberOfStations;
  public int numberOfReadings;
  float daysActive;
  float avgReadingsPerDay;

  public MemberAnalytics(Member member) {
    numberOfStations = member.stations.size();
    for (Station station : member.stations) {
      numberOfReadings += station.readings.size();
    }
    long timeActiveMS = new Date().getTime() - member.joinDate.getTime();
    daysActive = timeActiveMS / (1000 * 60 * 60 * 24);
    avgReadingsPerDay = numberOfReadings / daysActive;
  }
}
