package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Station extends Model
{
  public String name;
  public double latitude;
  public double longitude;
  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<Reading>();
//  public double latestTemp = 0;
  public double minTemp;
  public double maxTemp;
  public String weatherOutlook;
  public String weatherIcon;
  public double minWind;
  public double maxWind;
  public double minPressure;
  public double maxPressure;



  public Station(String name, double latitude, double longitude)
  {
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;

  }

  public static  String codeToReport(int code) {

      switch (code) {
        case 100:
          return "Clear";
        case 200:
          return "Partial clouds";
        case 300:
          return "Cloudy";
        case 400:
          return "Light Showers";
        case 500:
          return "Heavy Showers";
        case 600:
          return "Rain";
        case 700:
          return "Snow";
        case 800:
          return "Thunder";
      }
      return null;
  }

  public String weatherIcon (int code) {
    HashMap<Integer, String> weatherIcons = new HashMap<>();
    weatherIcons.put(100, "sun icon");
    weatherIcons.put(200, "cloud sun icon");
    weatherIcons.put(300, "cloud icon");
    weatherIcons.put(400, "cloud rain icon");
    weatherIcons.put(500, "cloud heavy showers icon");
    weatherIcons.put(600, "umbrella icon");
    weatherIcons.put(700, "snowflake icon");
    weatherIcons.put(800, "poo storm icon");

    return weatherIcons.get(code);
  }


//  public static Reading getMinTemp(List<Reading> readings) {
//    Reading minTemp = null;
//    if (readings.size() > 0) {
//      minTemp = readings.get(0);
//      for (Reading reading : readings) {
//        if (reading.temperature < minTemp.temperature){
//          minTemp = reading;
//        }
//      }
//    }
//    return minTemp;
//  }
//
//  public static Reading getMaxTemp(List<Reading> readings) {
//    Reading maxTemp = null;
//    if (readings.size() > 0) {
//      maxTemp = readings.get(0);
//      for (Reading reading : readings) {
//        if (reading.temperature > maxTemp.temperature){
//          maxTemp = reading;
//        }
//      }
//    }
//    return maxTemp;
//  }

  public double latestTemp(){
    if (readings.size() == 0) {
      return 0;
    }
    else{
      double latestTemp = readings.get(readings.size() - 1).temperature;

      return latestTemp;
    }
  }

  public double celciusToFahrenheit(){
    if (readings.size() == 0){
      return 0;
    }
    else{
      double latestTemp = readings.get(readings.size() - 1).temperature;
      double fahrenheit = (((latestTemp*9)/5)+32);

      return fahrenheit;
    }
  }

  public double latestPressure(){
    if (readings.size() == 0) {
      return 0;
    }
    else{
      double latestPressure = readings.get(readings.size() - 1).pressure;

      return latestPressure;
    }
  }


  public String calcBeaufort() {
    if (this.readings.size() > 0) {
      double windSpeed = this.readings.get(this.readings.size() - 1).windSpeed;
      if (windSpeed < 1)
        return "0";
      else if (windSpeed >= 1 && windSpeed < 6)
        return "1";
      else if (windSpeed >= 6 && windSpeed < 12)
        return "2";
      else if (windSpeed >= 12 && windSpeed < 19)
        return "3";
      else if (windSpeed >= 19 && windSpeed < 29)
        return "4";
      else if (windSpeed >= 30 && windSpeed < 39)
        return "5";
      else if (windSpeed >= 39 && windSpeed < 50)
        return "6";
      else if (windSpeed >= 50 && windSpeed < 62)
        return "7";
      else if (windSpeed >= 62 && windSpeed < 75)
        return "8";
      else if (windSpeed >= 75 && windSpeed < 89)
        return "9";
      else if (windSpeed >= 89 && windSpeed < 103)
        return "10";
      else if (windSpeed >= 103 && windSpeed < 117)
        return "11";
      else if (windSpeed >= 117)
        return "12";
      else
        return "0";
    }
    return "No Readings";
  }


  public String compassDirection() {
    if (this.readings.size() > 0) {
      double windDirection = this.readings.get(this.readings.size() - 1).windDirection;
      if ((windDirection >= 0 && windDirection < 11.25) || (windDirection > 348.75 && windDirection < 360))
        return "North";
      else if (windDirection >= 11.25 && windDirection < 33.75)
        return "North North East";
      else if (windDirection >= 33.75 && windDirection < 56.25)
        return "North East";
      else if (windDirection >= 56.25 && windDirection < 78.75)
        return "East North East";
      else if (windDirection >= 78.75 && windDirection < 101.25)
        return "East";
      else if (windDirection >= 101.25 && windDirection < 123.75)
        return "East South East";
      else if (windDirection >= 123.75 && windDirection < 146.25)
        return "South East";
      else if (windDirection >= 146.25 && windDirection < 168.75)
        return "South South East";
      else if (windDirection >= 168.75 && windDirection < 191.25)
        return "South";
      else if (windDirection >= 191.25 && windDirection < 213.75)
        return "South South West";
      else if (windDirection >= 213.75 && windDirection < 236.25)
        return "South West";
      else if (windDirection >= 236.25 && windDirection < 258.75)
        return "West South West";
      else if (windDirection >= 258.75 && windDirection < 281.25)
        return "West";
      else if (windDirection >= 281.25 && windDirection < 303.75)
        return "West North West";
      else if (windDirection >= 303.75 && windDirection < 326.25)
        return "North West";
      else if (windDirection >= 326.25 && windDirection <= 348.75)
        return "North North West";
      else
        return "";
    }
    return "No Readings";
  }

  public double windChill(){
    if (this.readings.size() == 0){
      return 0;
    }
    double wS = this.readings.get(this.readings.size()-1).windSpeed;
    double lT = readings.get(readings.size() - 1).temperature;
    double windChill = 13.12 + 0.6215 * lT - 11.37 * (Math.pow(wS,0.16)) + 0.3965 * lT * (Math.pow(wS,0.16));
return (int) (windChill*100)/100.0;       //truncate to two decimal places - lab09a
  }

}