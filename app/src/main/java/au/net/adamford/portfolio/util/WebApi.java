package au.net.adamford.portfolio.util;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Adam on 17/11/2015.
 */
public class WebApi {
    public static WebApiService getWebApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.adamford.net.au:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(WebApiService.class);
    }
}
