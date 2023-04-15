
package ies;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * demonstrates the use of the IPMA API for weather forecast
 */
public class WeatherStarter {


    private static Logger logger = LogManager.getLogger(WeatherStarter.class);
    public static void  main(String[] args ) {
        int CITY_ID = Integer.parseInt(args[0]);
        // get a retrofit instance, loaded with the GSon lib to convert JSON into objects
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // create a typed interface to use the remote API (a client)
        IpmaService service = retrofit.create(IpmaService.class);
        // prepare the call to remote endpoint
        Call<IpmaCityForecast> callSync = service.getForecastForACity(CITY_ID);

        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();

            if (forecast != null) {
                logger.info("Info log message");
                logger.error("Error log message");
                var firstDay = forecast.getData().listIterator().next();
                System.out.printf("Forecast for city with ID=%d%nPrecipitation Probability: %4.1f%nMinimum Temperature: %4.1f%n", 
                CITY_ID, Double.parseDouble(firstDay.getPrecipitaProb()), Double.parseDouble(firstDay.getTMin()));
            } else {
                System.out.println( "No results for this request!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
