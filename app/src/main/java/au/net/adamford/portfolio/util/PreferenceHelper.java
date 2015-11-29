package au.net.adamford.portfolio.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import au.net.adamford.portfolio.model.PortfolioItem;

/**
 * Created by Adam on 28/11/2015.
 */
public class PreferenceHelper {
    public static String SHARED_PREFERENCES_KEY = "sharedpreferences";
    public static String PORTFOLIO_ITEMS_KEY = "portfolioitems";
    public static String CACHE_ENABLED = "cacheenabled";

    public static void setCacheEnabled(boolean cacheEnabled, Context context) {
        storeObject(cacheEnabled, context, new TypeToken<Boolean>(){}, CACHE_ENABLED);
    }

    public static boolean isCachedEnabled(Context context) {
        Boolean b = (Boolean) getObject(context, new TypeToken<Boolean>(){}, CACHE_ENABLED);
        if(b!=null) {
            return b.booleanValue();
        }
        return true;
    }

    public static void storePortfolioItems(ArrayList<PortfolioItem> portfolioItemArrayList, Context context) {
        storeObject(portfolioItemArrayList, context, new TypeToken<ArrayList<PortfolioItem>>(){}, PORTFOLIO_ITEMS_KEY);
    }

    public static ArrayList<PortfolioItem> getPortfolioItems(Context context) {
        ArrayList<PortfolioItem> portfolioItems =  (ArrayList) getObject(context, new TypeToken<ArrayList<PortfolioItem>>(){}, PORTFOLIO_ITEMS_KEY);
        if(portfolioItems==null) {
            return new ArrayList<>();
        }
        return portfolioItems;
    }

    public static Object getObject(Context context, TypeToken typeToken, String key) {
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString(key, "");
        return gson.fromJson(json, typeToken.getType());
    }

    public static void storeObject(Object object, Context context, TypeToken typeToken, String key) {
        Gson gson = new Gson();
        getSharedPreferences(context).edit().putString(key, gson.toJson(object, typeToken.getType())).apply();
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
    }
}
