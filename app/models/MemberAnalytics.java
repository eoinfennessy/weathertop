package models;

import play.db.jpa.Model;

public class MemberAnalytics extends Model {
    public int numberOfStations;
    public int numberOfReadings;

    public MemberAnalytics(Member member) {
        numberOfStations = member.stations.size();
        for (Station station : member.stations) {
            numberOfReadings += station.readings.size();
        }
    }
}
