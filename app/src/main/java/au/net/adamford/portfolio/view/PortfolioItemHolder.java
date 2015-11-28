package au.net.adamford.portfolio.view;

import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import au.net.adamford.portfolio.R;
import au.net.adamford.portfolio.model.PortfolioItem;
import au.net.adamford.portfolio.util.OnHolderClickListener;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Adam on 17/11/2015.
 */
public class PortfolioItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @Bind(R.id.text_view)
    TextView textView;
    @Bind(R.id.image)
    ImageView image;

    PortfolioItem mPortfolioItem;
    OnHolderClickListener<PortfolioItem> mOnHolderClickListener;

    public static String TAG = "PortfolioItemHolder";

    public PortfolioItemHolder(View itemView, OnHolderClickListener onClickListener) {
        super(itemView);
        itemView.setOnClickListener(this);
        mOnHolderClickListener = onClickListener;
        ButterKnife.bind(this, itemView);
    }

    public void bindResult(PortfolioItem portfolioItem) {
        this.mPortfolioItem = portfolioItem;
        textView.setText(mPortfolioItem.title);
        switch(mPortfolioItem.type) {
            case 1:{
                image.setImageResource(R.drawable.android);
                break;
            }
            case 2: {
                image.setImageResource(R.drawable.mac);
                break;
            }
            case 0: {
                image.setImageResource(R.drawable.html);
                break;

            }
        }
    }

    public PortfolioItem getPortfolioItem() {
        return mPortfolioItem;
    }

    public void onClick(View v) {
        if(mOnHolderClickListener!=null) {
            mOnHolderClickListener.onClick(mPortfolioItem, itemView);
        }
        else {
            Log.i(TAG, "Please set onHolderClickListener");
        }
    }

}
