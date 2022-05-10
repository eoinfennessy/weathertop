package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Station extends Model {
    public String name;
    public float latitude;
    public float longitude;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<>();
    public DetailedReading latestReading;

    public Station(String name, float latitude, float longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void calculateLatestReading() {
        if (!readings.isEmpty()) {
            Reading reading = readings.get(readings.size() - 1);
            latestReading = new DetailedReading(reading);
        }
    }
}
