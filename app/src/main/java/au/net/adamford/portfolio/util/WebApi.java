package au.net.adamford.portfolio.util;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.logging.Level;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Adam on 17/11/2015.
 */
public class WebApi {
    public static WebApiService getWebApiService() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient();
        // add your other interceptors …

        // add logging as last interceptor
        httpClient.interceptors().add(logging);  // <-- this is the important line!
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.adamford.net.au:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        return retrofit.create(WebApiService.class);
    }
}
