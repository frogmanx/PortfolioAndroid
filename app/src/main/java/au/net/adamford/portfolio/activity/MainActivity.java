package au.net.adamford.portfolio.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import au.net.adamford.portfolio.R;
import au.net.adamford.portfolio.adapter.PortfolioItemAdapter;
import au.net.adamford.portfolio.model.PortfolioItem;
import au.net.adamford.portfolio.util.OnHolderClickListener;
import au.net.adamford.portfolio.util.PreferenceHelper;
import au.net.adamford.portfolio.util.WebApi;
import au.net.adamford.portfolio.view.DividerItemDecoration;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import uk.co.imallan.jellyrefresh.JellyRefreshLayout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Callback<List<PortfolioItem>>, OnHolderClickListener<PortfolioItem>, JellyRefreshLayout.JellyRefreshListener {

    ArrayList<PortfolioItem> mPortfolioItems;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.drawer_layout) DrawerLayout drawer;
    @Bind(R.id.nav_view) NavigationView navigationView;
    @Bind(R.id.loading) JellyRefreshLayout jellyRefreshLayout;
    PortfolioItemAdapter mAdapter;
    LinearLayoutManager mLinearLayoutManager;
    Call<List<PortfolioItem>> mCall;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if(PreferenceHelper.isCachedEnabled(this)) {
            mPortfolioItems = PreferenceHelper.getPortfolioItems(this);
        }
        else {
            mPortfolioItems = new ArrayList<>();
        }
        mCall = WebApi.getWebApiService().getAll();
        jellyRefreshLayout.setRefreshListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        mAdapter = new PortfolioItemAdapter(mPortfolioItems, this);

        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        Call<List<PortfolioItem>> call = WebApi.getWebApiService().getAll();
        call.enqueue(this);
    }


    @Override
    public void onClick(PortfolioItem portfolioItem, View view) {
        //view.setTransitionName(view.getResources().getString(R.string.transition_shot));
        //view.setBackgroundColor(getResources().getColor(R.color.background_light));
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(DetailActivity.ITEM, portfolioItem);
        ActivityOptions options =
                ActivityOptions.makeSceneTransitionAnimation(this,
                        Pair.create(view, getString(R.string.transition_shot)),
                        Pair.create(view, getString(R.string
                                .transition_shot_background)));
        startActivity(intent, options.toBundle());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.about) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        }
        else if (id == R.id.settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onResponse(Response<List<PortfolioItem>> response, Retrofit retrofit) {
        jellyRefreshLayout.finishRefreshing();
        if(response.isSuccess()&&response.body()!=null) {
            mPortfolioItems = new ArrayList<>(response.body());
            if(PreferenceHelper.isCachedEnabled(this)) {
                PreferenceHelper.storePortfolioItems(mPortfolioItems, this);
            }
            mAdapter.setList(mPortfolioItems);
            Log.i(TAG,  Integer.toString(mPortfolioItems.size()));
        }
    }

    @Override
    public void onFailure(Throwable t) {
        jellyRefreshLayout.finishRefreshing();
    }

    @Override
    public void onRefresh(JellyRefreshLayout jellyRefreshLayout) {
        mCall.cancel();
        mCall = WebApi.getWebApiService().getAll();
        mCall.enqueue(this);
    }
}
