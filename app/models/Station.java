package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Station extends Model {
    public String name;
    public float latitude;
    public float longitude;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<>();
    @Transient
    public DetailedReading latestReading;
    // TODO: Move to StationAnalytics class?
    @Transient
    public float maxWindSpeed;
    @Transient
    public float minWindSpeed;
    @Transient
    public float maxTemperature;
    @Transient
    public float minTemperature;
    @Transient
    public float maxPressure;
    @Transient
    public float minPressure;
    @Transient
    public String temperatureTrend;
    @Transient
    public String windSpeedTrend;
    @Transient
    public String pressureTrend;

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

    public void calculateMaxWindSpeed() {
        if (!readings.isEmpty()) {
            float currentMaxWindSpeed = readings.get(0).windSpeed;
            for (Reading reading : readings) {
                if (reading.windSpeed > currentMaxWindSpeed) {
                    currentMaxWindSpeed = reading.windSpeed;
                }
            }
            maxWindSpeed = currentMaxWindSpeed;
        }
    }

    public void calculateMinWindSpeed() {
        if (!readings.isEmpty()) {
            float currentMinWindSpeed = readings.get(0).windSpeed;
            for (Reading reading : readings) {
                if (reading.windSpeed < currentMinWindSpeed) {
                    currentMinWindSpeed = reading.windSpeed;
                }
            }
            minWindSpeed = currentMinWindSpeed;
        }
    }

    public void calculateMaxTemperature() {
        if (!readings.isEmpty()) {
            float currentMaxTemperature = readings.get(0).temperature;
            for (Reading reading : readings) {
                if (reading.temperature > currentMaxTemperature) {
                    currentMaxTemperature = reading.temperature;
                }
            }
            maxTemperature = currentMaxTemperature;
        }
    }

    public void calculateMinTemperature() {
        if (!readings.isEmpty()) {
            float currentMinTemperature = readings.get(0).temperature;
            for (Reading reading : readings) {
                if (reading.temperature < currentMinTemperature) {
                    currentMinTemperature = reading.temperature;
                }
            }
            minTemperature = currentMinTemperature;
        }
    }

    public void calculateMaxPressure() {
        if (!readings.isEmpty()) {
            float currentMaxPressure = readings.get(0).pressure;
            for (Reading reading : readings) {
                if (reading.pressure > currentMaxPressure) {
                    currentMaxPressure = reading.pressure;
                }
            }
            maxPressure = currentMaxPressure;
        }
    }

    public void calculateMinPressure() {
        if (!readings.isEmpty()) {
            float currentMinPressure = readings.get(0).pressure;
            for (Reading reading : readings) {
                if (reading.pressure < currentMinPressure) {
                    currentMinPressure = reading.pressure;
                }
            }
            minPressure = currentMinPressure;
        }
    }

//    public void calculateTemperatureTrend() {
//        if (readings.size() >= 3) {
//            int rs = readings.size();
//            if (readings.get(rs - 1).temperature > readings.get(rs - 2).temperature
//                    && readings.get(rs - 2).temperature > readings.get(rs - 3).temperature) {
//                temperatureTrend = "up arrow";
//            } else if (readings.get(rs - 1).temperature < readings.get(rs - 2).temperature
//                    && readings.get(rs - 2).temperature < readings.get(rs - 3).temperature) {
//                temperatureTrend = "down arrow";
//            } else {
//                temperatureTrend = "";
//            }
//        }
//    }

//    public void calculateWindSpeedTrend() {
//        if (readings.size() >= 3) {
//            int rs = readings.size();
//            if (readings.get(rs - 1).windSpeed > readings.get(rs - 2).windSpeed
//                    && readings.get(rs - 2).windSpeed > readings.get(rs - 3).windSpeed) {
//                windSpeedTrend = "up arrow";
//            } else if (readings.get(rs - 1).windSpeed < readings.get(rs - 2).windSpeed
//                    && readings.get(rs - 2).windSpeed < readings.get(rs - 3).windSpeed) {
//                windSpeedTrend = "down arrow";
//            } else {
//                windSpeedTrend = "";
//            }
//        }
//    }

    public void calculateTrends() {
        if (readings.size() >= 3) {
            int len = readings.size();
            temperatureTrend = getTrendIcon(readings.get(len - 3).temperature,
                                            readings.get(len - 2).temperature,
                                            readings.get(len - 1).temperature);
            windSpeedTrend = getTrendIcon(readings.get(len - 3).windSpeed,
                                          readings.get(len - 2).windSpeed,
                                          readings.get(len - 1).windSpeed);
            pressureTrend = getTrendIcon(readings.get(len - 3).pressure,
                                         readings.get(len - 2).pressure,
                                         readings.get(len - 1).pressure);
        } else {
            temperatureTrend = "";
            windSpeedTrend = "";
            pressureTrend = "";
        }
    }

    public String getTrendIcon(float val1, float val2, float val3) {
        // If trend is upwards
        if (val1 < val2 && val2 < val3) {
            return "up arrow";
        // if trend is downwards
        } else if (val1 > val2 && val2 > val3) {
            return "down arrow";
        } else {
            return "";
        }
    }
}