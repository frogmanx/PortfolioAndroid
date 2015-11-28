package au.net.adamford.portfolio.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import au.net.adamford.portfolio.R;
import au.net.adamford.portfolio.adapter.PortfolioItemAdapter;
import au.net.adamford.portfolio.model.PortfolioItem;
import au.net.adamford.portfolio.util.WebApi;
import au.net.adamford.portfolio.view.DividerItemDecoration;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;

/**
 * Created by Adam on 17/11/2015.
 */
public class DetailActivity extends AppCompatActivity {
    public static final String ITEM = "item";
    PortfolioItem mPortfolioItem;
    @Bind(R.id.description) TextView description;
    @Bind(R.id.title) TextView title;
    //@Bind(R.id.image)
    //SimpleDraweeView image;
    @Bind(R.id.header)
    SimpleDraweeView header;
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        mPortfolioItem = getIntent().getParcelableExtra(ITEM);
    }

    @Override
    protected void onResume() {
        super.onResume();
        description.setText(mPortfolioItem.description);
        setTitle(mPortfolioItem.title);

        if(mPortfolioItem.imageUrl!=null) {
            Uri imageUri = Uri.parse(mPortfolioItem.imageUrl);
            if(imageUri!=null) {
                header.setImageURI(imageUri);
                //image.setImageURI(imageUri);
            }
        }
    }

    @OnClick(R.id.fab)
    public void onClick() {
        String url = mPortfolioItem.url;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
