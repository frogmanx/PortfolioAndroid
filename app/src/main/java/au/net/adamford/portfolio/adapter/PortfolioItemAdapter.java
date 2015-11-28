package au.net.adamford.portfolio.adapter;

import android.location.Address;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import au.net.adamford.portfolio.R;
import au.net.adamford.portfolio.model.PortfolioItem;
import au.net.adamford.portfolio.util.OnHolderClickListener;
import au.net.adamford.portfolio.view.PortfolioItemHolder;

/**
 * Created by Adam on 17/11/2015.
 */
public class PortfolioItemAdapter extends RecyclerView.Adapter<PortfolioItemHolder> {
    ArrayList<PortfolioItem> mPortfolioItems;
    OnHolderClickListener mOnClickListener;
    public static String TAG = "AddressAdapter";
    public void setList(ArrayList<PortfolioItem> portfolioItems) {
        this.mPortfolioItems = portfolioItems;
        this.notifyDataSetChanged();
    }

    public void setOnClickListener(OnHolderClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
        this.notifyDataSetChanged();
    }

    public PortfolioItemAdapter(ArrayList<PortfolioItem> portfolioItems){
        this.mPortfolioItems = portfolioItems;
    }

    public PortfolioItemAdapter(ArrayList<PortfolioItem> portfolioItems, OnHolderClickListener onClickListener){
        this.mPortfolioItems = portfolioItems;
        this.mOnClickListener = onClickListener;
    }


    @Override
    public PortfolioItemHolder onCreateViewHolder(ViewGroup parent, int pos) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item, parent, false);
        return new PortfolioItemHolder(view, mOnClickListener);
    }

    @Override
    public void onBindViewHolder(PortfolioItemHolder holder, int pos) {
        PortfolioItem portfolioItem = mPortfolioItems.get(pos);
        holder.bindResult(portfolioItem);
    }

    @Override
    public int getItemCount() {
        return mPortfolioItems.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
