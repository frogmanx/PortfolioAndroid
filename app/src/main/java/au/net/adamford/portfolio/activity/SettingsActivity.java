package au.net.adamford.portfolio.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.ArrayList;

import au.net.adamford.portfolio.R;
import au.net.adamford.portfolio.model.PortfolioItem;
import au.net.adamford.portfolio.util.PreferenceHelper;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by Adam on 29/11/2015.
 */
public class SettingsActivity extends AppCompatActivity {
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.cached) Switch cached;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Settings");
    }

    public void onResume() {
        super.onResume();
        cached.setChecked(PreferenceHelper.isCachedEnabled(this));
    }

    @OnClick(R.id.clear)
    public void clearCache() {
        PreferenceHelper.storePortfolioItems(new ArrayList<PortfolioItem>(), this);
    }

    @OnCheckedChanged(R.id.cached)
    public void toggleCache(CompoundButton toggle, boolean cache) {
        PreferenceHelper.setCacheEnabled(cache, this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
