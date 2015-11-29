package au.net.adamford.portfolio.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import au.net.adamford.portfolio.BuildConfig;
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
    @Bind(R.id.contact1) TextView contact1;
    @Bind(R.id.contact2) TextView contact2;
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
        title.setText(getResources().getString(R.string.title));
        body.setText(getResources().getString(R.string.about_body));
        contact1.setText(getResources().getString(R.string.email) + BuildConfig.EMAIL_ADDRESS);
        contact2.setText(getResources().getString(R.string.phone) + BuildConfig.PHONE_NUMBER);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @OnClick({R.id.fab, R.id.contact1})
    public void onFabClick(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{BuildConfig.EMAIL_ADDRESS});
        intent.putExtra(Intent.EXTRA_SUBJECT, BuildConfig.EMAIL_SUBJECT);
        PackageManager packageManager = getPackageManager();
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intent, getResources().getString(R.string.send_email)));
        }   else  {
            Snackbar.make(view, getResources().getString(R.string.no_email), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    @OnClick(R.id.contact2)
    public void goToPhone() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(getResources().getString(R.string.telephone)+BuildConfig.PHONE_NUMBER));
        startActivity(intent);
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
