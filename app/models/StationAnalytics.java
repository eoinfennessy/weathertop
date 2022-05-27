package models;

import play.db.jpa.Model;

import java.util.List;

public class StationAnalytics extends Model {
    public float maxWindSpeed;
    public float minWindSpeed;
    public float maxTemperature;
    public float minTemperature;
    public float maxPressure;
    public float minPressure;
    public String temperatureTrendIcon;
    public String windSpeedTrendIcon;
    public String pressureTrendIcon;

    public StationAnalytics(List<Reading> readings) {
        maxWindSpeed = returnMaxWindSpeed(readings);
        minWindSpeed = returnMinWindSpeed(readings);
        minTemperature = returnMinTemperature(readings);
        maxTemperature = returnMaxTemperature(readings);
        minPressure = returnMinPressure(readings);
        maxPressure = returnMaxPressure(readings);
        updateTrendIcons(readings);
    }

    public static float returnMaxWindSpeed(List<Reading> readings) {
        float currentMaxWindSpeed = readings.get(0).windSpeed;
        for (Reading reading : readings) {
            if (reading.windSpeed > currentMaxWindSpeed) {
                currentMaxWindSpeed = reading.windSpeed;
            }
        }
        return currentMaxWindSpeed;
    }

    public static float returnMinWindSpeed(List<Reading> readings) {
        float currentMinWindSpeed = readings.get(0).windSpeed;
        for (Reading reading : readings) {
            if (reading.windSpeed < currentMinWindSpeed) {
                currentMinWindSpeed = reading.windSpeed;
            }
        }
        return currentMinWindSpeed;
    }

    public static float returnMaxTemperature(List<Reading> readings) {
        float currentMaxTemperature = readings.get(0).temperature;
        for (Reading reading : readings) {
            if (reading.temperature > currentMaxTemperature) {
                currentMaxTemperature = reading.temperature;
            }
        }
        return currentMaxTemperature;
    }

    public static float returnMinTemperature(List<Reading> readings) {
        float currentMinTemperature = readings.get(0).temperature;
        for (Reading reading : readings) {
            if (reading.temperature < currentMinTemperature) {
                currentMinTemperature = reading.temperature;
            }
        }
        return currentMinTemperature;
    }

    public static float returnMaxPressure(List<Reading> readings) {
        float currentMaxPressure = readings.get(0).pressure;
        for (Reading reading : readings) {
            if (reading.pressure > currentMaxPressure) {
                currentMaxPressure = reading.pressure;
            }
        }
        return currentMaxPressure;
    }

    public static float returnMinPressure(List<Reading> readings) {
        float currentMinPressure = readings.get(0).pressure;
        for (Reading reading : readings) {
            if (reading.pressure < currentMinPressure) {
                currentMinPressure = reading.pressure;
            }
        }
        return currentMinPressure;
    }

    private void updateTrendIcons(List<Reading> readings) {
        if (readings.size() >= 3) {
            // Most recent reading is at index zero
            temperatureTrendIcon = returnTrendIcon(readings.get(2).temperature,
                    readings.get(1).temperature,
                    readings.get(0).temperature);
            windSpeedTrendIcon = returnTrendIcon(readings.get(2).windSpeed,
                    readings.get(1).windSpeed,
                    readings.get(0).windSpeed);
            pressureTrendIcon = returnTrendIcon(readings.get(2).pressure,
                    readings.get(1).pressure,
                    readings.get(0).pressure);
        } else {
            temperatureTrendIcon = "";
            windSpeedTrendIcon = "";
            pressureTrendIcon = "";
        }
    }

    public String returnTrendIcon(float val1, float val2, float val3) {
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