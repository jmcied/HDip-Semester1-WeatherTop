package controllers;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;

import java.util.List;

public class StationCtrl extends Controller {
    public static void index(Long id) {
        Logger.info("Rendering Station");
        Station station = Station.findById(id);

        List<Station> stations = Station.findAll();

        for (Station weather : stations) {
            if (weather.readings.size() > 0){
                weather.weatherOutlook  = weather.codeToReport(weather.readings.get(weather.readings.size()-1).code);
                weather.weatherIcon     = weather.weatherIcon(weather.readings.get(weather.readings.size()-1).code);
                weather.minTemp         = StationAnalytics.getMinTemp(weather.readings).temperature;
                weather.maxTemp         = StationAnalytics.getMaxTemp(weather.readings).temperature;
                weather.minWind         = StationAnalytics.getMinWind(weather.readings).windSpeed;
                weather.maxWind         = StationAnalytics.getMaxWind(weather.readings).windSpeed;
                weather.minPressure     = StationAnalytics.getMinPressure(weather.readings).pressure;
                weather.maxPressure     = StationAnalytics.getMaxPressure(weather.readings).pressure;

//      System.out.println("Min Temp: "+ weather.minTemp + " @ " + weather.name);     //Test Output to console
            }
        }
        //Logger.info("Station id = " + id);
        render("station.html", station);
    }


    public static void addReading(Long id, int code, double temperature, double windSpeed, double windDirection, int pressure)
    {
        Reading reading = new Reading(code, temperature, windSpeed, windDirection, pressure);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect ("/stations/" + id);
    }

    public static void deleteReading(Long id, Long readingid) {
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingid);
        Logger.info("Removing " + reading.code);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        redirect ("/stations/" + id);
        render("station.html", station);

    }

}



