package ies.ipma;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class IpmaApiClient {

    public static IpmaCityForecast retrieveForecat(Integer CITY_ID){
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
            return apiResponse.body();

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
