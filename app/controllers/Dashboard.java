package controllers;

import java.util.List;

import models.Station;
import models.Member;
import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;

    for (Station weather : stations) {
      if (weather.readings.size() > 0){
        weather.weatherOutlook = weather.codeToReport(weather.readings.get(weather.readings.size()-1).code);
        weather.weatherIcon = weather.weatherIcon(weather.readings.get(weather.readings.size()-1).code);
        weather.minTemp = StationAnalytics.getMinTemp(weather.readings).temperature;
//        System.out.println("Min Temp: "+ weather.minTemp + " @ " + weather.name);     //Test Output to console
        weather.maxTemp = StationAnalytics.getMaxTemp(weather.readings).temperature;
//        System.out.println("Max Temp: "+ weather.maxTemp + " @ " + weather.name);     //Test Output to console

      }
    }
    render ("dashboard.html",member, stations);
  }

  public static void addStation (String name, double latitude, double longitude)
  {
    Logger.info("Adding a Station");
    Member member = Accounts.getLoggedInMember();
    Station station = new Station(name, latitude, longitude);
    member.stations.add(station);
    member.save();
    redirect ("/dashboard");
  }

  public static void deleteStation(Long id) {

    Logger.info("Deleting Station: " + id);
    Member member = Accounts.getLoggedInMember();
    Station station = Station.findById(id);
    member.stations.remove(station);
    member.save();
    station.delete();
    redirect ("/dashboard");
  }
}
