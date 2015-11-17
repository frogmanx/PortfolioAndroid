package au.net.adamford.portfolio.util;

import java.util.ArrayList;
import java.util.List;

import au.net.adamford.portfolio.model.PortfolioItem;
import retrofit.Call;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Adam on 17/11/2015.
 */
public interface WebApiService {
    @GET("/api")
    Call<List<PortfolioItem>> getAll();
    @GET("/api/{id}")
    Call<List<PortfolioItem>> getPortfolioItem(@Path("id") int id);
}
