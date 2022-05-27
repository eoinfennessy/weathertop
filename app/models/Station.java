package models;

import play.db.jpa.Model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
public class Station extends Model {
    public String name;
    public float latitude;
    public float longitude;
    @OneToMany(cascade = CascadeType.ALL)
    @OrderBy("date DESC")
    public List<Reading> readings = new ArrayList<>();
    @Transient
    public DetailedReading latestReading;
    @Transient
    public StationAnalytics stationAnalytics;

    public Station(String name, float latitude, float longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void updateLatestReading() {
        if (!readings.isEmpty()) {
            Reading reading = readings.stream().max(Comparator.comparing(r -> r.date)).get();
            latestReading = new DetailedReading(reading);
        } else {
            latestReading = null;
        }
    }

    public void updateStationAnalytics() {
        if (!readings.isEmpty()) {
            stationAnalytics = new StationAnalytics(readings);
        } else {
            stationAnalytics = null;
        }
    }
}