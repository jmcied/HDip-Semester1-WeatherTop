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
                weather.weatherOutlook = weather.codeToReport(weather.readings.get(weather.readings.size()-1).code);
                weather.weatherIcon = weather.weatherIcon(weather.readings.get(weather.readings.size()-1).code);
                weather.minTemp = StationAnalytics.getMinTemp(weather.readings).temperature;
//                System.out.println("Min Temp: "+ weather.minTemp + " @ " + weather.name);     //Test Output
                weather.maxTemp = StationAnalytics.getMaxTemp(weather.readings).temperature;
//                System.out.println("Max Temp: "+ weather.maxTemp + " @ " + weather.name);     //Test Output
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
        render("station.html", station);
    }

}



