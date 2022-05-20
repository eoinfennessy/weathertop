package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Reading extends Model {
    public Date date;
    public int code;
    public float temperature;
    public float windSpeed;
    public float windDirection;
    public float pressure;

    public Reading(int code, float temperature, float windSpeed, float windDirection, float pressure) {
        this.date = new Date();
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.pressure = pressure;
    }

    public Reading(Reading reading) {
        this.date = reading.date;
        this.code = reading.code;
        this.temperature = reading.temperature;
        this.windSpeed = reading.windSpeed;
        this.windDirection = reading.windDirection;
        this.pressure = reading.pressure;
    }
}
