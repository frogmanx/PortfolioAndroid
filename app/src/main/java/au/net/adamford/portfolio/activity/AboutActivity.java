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
        title.setText("Adam Ford");
        body.setText("Mobile Developer from Melbourne");
        contact1.setText("Email: adam.ford.eng@gmail.com");
        contact2.setText("Phone: +61437948814");
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @OnClick({R.id.fab, R.id.contact1})
    public void onFabClick(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"adam.ford.eng@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Hello");
        PackageManager packageManager = getPackageManager();
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intent, "Send Email"));
        }   else  {
            Snackbar.make(view, "Not able to send email from this device.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    @OnClick(R.id.contact2)
    public void goToPhone() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+61437948814"));
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == android.R.id.home){
            // app icon in action bar clicked; goto parent activity.
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
