package au.net.adamford.portfolio.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import au.net.adamford.portfolio.R;
import au.net.adamford.portfolio.model.PortfolioItem;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Adam on 28/11/2015.
 */
public class AboutActivity extends AppCompatActivity {
    @Bind(R.id.title) TextView title;
    @Bind(R.id.body) TextView body;
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle("About");
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title.setText("Adam Ford");
        body.setText("Blah Blah Blah");
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @OnClick(R.id.fab)
    public void onFabClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
