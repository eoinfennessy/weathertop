package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Reading extends Model {
    public int code;
    public float temperature;
    public float windSpeed;
    public float windDirection;
    public float pressure;

    public Reading(int code, float temperature, float windSpeed, float windDirection, float pressure) {
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.pressure = pressure;
    }
}
