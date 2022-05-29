package utils;

import models.Reading;

import java.util.List;

public class StationAnalytics {

  public static Reading getMinTemp(List<Reading> readings) {
    Reading minTemp = null;
    if (readings.size() > 0) {
      minTemp = readings.get(0);
      for (Reading reading : readings) {
        if (reading.temperature < minTemp.temperature){
          minTemp = reading;
        }
      }
    }
    return minTemp;
  }

  public static Reading getMaxTemp(List<Reading> readings) {
    Reading maxTemp = null;
    if (readings.size() > 0) {
      maxTemp = readings.get(0);
      for (Reading reading : readings) {
        if (reading.temperature > maxTemp.temperature){
          maxTemp = reading;
        }
      }
    }
    return maxTemp;
  }

  public static Reading getMinWind(List<Reading> readings) {
    Reading minWind = null;
    if (readings.size() > 0) {
      minWind = readings.get(0);
      for (Reading reading : readings) {
        if (reading.windSpeed < minWind.windSpeed){
          minWind = reading;
        }
      }
    }
    return minWind;
  }

  public static Reading getMaxWind(List<Reading> readings) {
    Reading maxWind = null;
    if (readings.size() > 0) {
      maxWind = readings.get(0);
      for (Reading reading : readings) {
        if (reading.windSpeed > maxWind.windSpeed){
          maxWind = reading;
        }
      }
    }
    return maxWind;
  }

  public static Reading getMinPressure(List<Reading> readings) {
    Reading minPressure = null;
    if (readings.size() > 0) {
      minPressure = readings.get(0);
      for (Reading reading : readings) {
        if (reading.pressure < minPressure.pressure){
          minPressure = reading;
        }
      }
    }
    return minPressure;
  }

  public static Reading getMaxPressure(List<Reading> readings) {
    Reading maxPressure = null;
    if (readings.size() > 0) {
      maxPressure = readings.get(0);
      for (Reading reading : readings) {
        if (reading.pressure > maxPressure.pressure){
          maxPressure = reading;
        }
      }
    }
    return maxPressure;
  }

//  public static double getBeaufort(List<Reading> readings){
//    double windSpeed = readings.get(readings.size()-1).windSpeed;
//    if (windSpeed <= 1)
//      return 0;
//    else if (windSpeed > 1 && windSpeed < 6)
//      return 1;
//    else if (windSpeed >= 6 && windSpeed < 12)
//      return 2;
//    else if (windSpeed >= 12 && windSpeed < 19)
//      return 3;
//    else if (windSpeed >= 19 && windSpeed < 29)
//      return 4;
//    else if (windSpeed >= 30 && windSpeed < 39)
//      return 5;
//    else if (windSpeed >= 39 && windSpeed < 50)
//      return 6;
//    else if (windSpeed >= 50 && windSpeed < 62)
//      return 7;
//    else if (windSpeed >= 62 && windSpeed < 75)
//      return 8;
//    else if (windSpeed >= 75 && windSpeed < 89)
//      return 9;
//    else if (windSpeed >= 89 && windSpeed < 103)
//      return 10;
//    else if (windSpeed >=103 && windSpeed < 117)
//      return 11;
//    else
//      return 0;
//  }





}
