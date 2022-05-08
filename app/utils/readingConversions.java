package utils;

public class readingConversions {
    public static String convertWeatherCodeToWeatherCondition(int weatherCode) {
        switch (weatherCode) {
            case 100: return "Clear";
            case 200: return "Partial Clouds";
            case 300: return "Cloudy";
            case 400: return "Light Showers";
            case 500: return "Heavy Showers";
            case 600: return "Rain";
            case 700: return "Snow";
            case 800: return "Thunder";
            default:  return "Invalid weather condition code provided: " + weatherCode;
        }
    }

    public static float convertCelsiusToFahrenheit(float celsius) {
        return (float) (celsius * 1.8 + 32);
    }

    // Returns -1 if kmph is less than 0
    public static int convertKilometresPerHourToBeaufortForce(float kilometresPerHour) {
        if (kilometresPerHour < 0) {
            return -1;
        } else if (kilometresPerHour < 2) {
            return 0;
        } else if (kilometresPerHour < 6) {
            return 1;
        } else if (kilometresPerHour < 12) {
            return 2;
        } else if (kilometresPerHour < 20) {
            return 3;
        } else if (kilometresPerHour < 29) {
            return 4;
        } else if (kilometresPerHour < 39) {
            return 5;
        } else if (kilometresPerHour < 50) {
            return 6;
        } else if (kilometresPerHour < 62) {
            return 7;
        } else if (kilometresPerHour < 75) {
            return 8;
        } else if (kilometresPerHour < 89) {
            return 9;
        } else if (kilometresPerHour < 103) {
            return 10;
        } else if (kilometresPerHour < 118) {
            return 11;
        } else {
            return 12;
        }
    }

    public static String convertDegreesToCardinalDirection(float degrees) {
        if (degrees < 0 || degrees > 360) {
            return "Invalid entry: " + degrees;
        } else if (degrees >= 348.75 || degrees < 11.25) {
            return "N";
        } else if (degrees < 33.75) {
            return "NNE";
        } else if (degrees < 56.25) {
            return "NE";
        } else if (degrees < 78.75) {
            return "ENE";
        } else if (degrees < 101.25) {
            return "E";
        } else if (degrees < 123.75) {
            return "ESE";
        } else if (degrees < 146.25) {
            return "SE";
        } else if (degrees < 168.75) {
            return "SSE";
        } else if (degrees < 191.25) {
            return "S";
        } else if (degrees < 213.75) {
            return "SSW";
        } else if (degrees < 236.25) {
            return "SW";
        } else if (degrees < 258.75) {
            return "WSW";
        } else if (degrees < 281.25) {
            return "W";
        } else if (degrees < 303.75) {
            return "WNW";
        } else if (degrees < 326.25) {
            return "NW";
        } else {
            return "NNW";
        }
    }


    public static float calculateWindChill(float t, float v) {
        return (float) (13.12 + 0.6215 * t - 11.37 * Math.pow(v, 0.16) + 0.3965 * t * Math.pow(v, 0.16));
    }
}
