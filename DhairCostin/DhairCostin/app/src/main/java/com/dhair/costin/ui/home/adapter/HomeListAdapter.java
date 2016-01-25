package com.dhair.costin.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dhair.costin.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Creator: dengshengjin on 16/1/25 12:11
 * Email: deng.shengjin@zuimeia.com
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.RecycleViewHolder> {
    private Context mContext;
    public LayoutInflater mInflater;

    public HomeListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.fragment_home_list_item, parent, false);
        RecycleViewHolder mViewHolder = new RecycleViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        holder.mTitleView.setText("Item " + position);
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    static final class RecycleViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.title_text)
        TextView mTitleView;

        public RecycleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
