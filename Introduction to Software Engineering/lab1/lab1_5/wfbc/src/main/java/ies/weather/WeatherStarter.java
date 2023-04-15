package ies.weather;

import ies.ipma.IpmaApiClient;
import ies.ipma.IpmaCityForecast;
import ies.ipma.CityForecast;

/**
 * demonstrates the use of the IPMA API for weather forecast
 */
public class WeatherStarter {

    public static void  main(String[] args ) {
        int CITY_ID = Integer.parseInt(args[0]);
        IpmaCityForecast forecast = IpmaApiClient.retrieveForecat(CITY_ID);
        if (forecast != null) {
            CityForecast firstDay = forecast.getData().listIterator().next();
            System.out.printf("Forecast for city with ID=%d%nPrecipitation Probability: %4.1f%nMinimum Temperature: %4.1f%n", 
            CITY_ID, Double.parseDouble(firstDay.getPrecipitaProb()), Double.parseDouble(firstDay.getTMin()));
        } else {
            System.out.println( "No results for this request!");
            }

    }
}
