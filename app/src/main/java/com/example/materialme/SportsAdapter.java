package com.example.materialme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.nfc.TagLostException;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.ViewHolder> {
    private ArrayList<Sport> mSportsData;
    private Context mContext;
    private GradientDrawable mGradientDrawable;


    SportsAdapter(Context context, ArrayList<Sport> sportsData) {
        this.mSportsData = mSportsData;
        this.mContext = context;

        mGradientDrawable =new GradientDrawable();
        mGradientDrawable.setColor(Color.GRAY);

        Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.img_badminton);
        if (drawable != null) {
            mGradientDrawable.setSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
    }

    @Override
    public SportsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mContext, LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false), mGradientDrawable);
    }

    @Override
    public void onBindViewHolder(SportsAdapter.ViewHolder holder, int position) {
        Sport currentSport = mSportsData.get(position);
        holder.bindTo(currentSport);
    }

    @Override
    public int getItemCount() {
        return mSportsData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mSportsImage;
        private Context mContext;
        private Sport mCurrentSport;
        private GradientDrawable mGradientDrawable;


        ViewHolder(Context context, View itemView, GradientDrawable gradientDrawable) {
            super(itemView);

            mTitleText = (TextView) itemView.findViewById(R.id.newsTitle);
            mInfoText = (TextView) itemView.findViewById(R.id.subtitle);
            mSportsImage = (ImageView) itemView.findViewById(R.id.sportsImage);

            itemView.setOnClickListener(this);
        }


        void bindTo(Sport currentSport) {
            mTitleText.setText(currentSport.getTitle());
            mInfoText.setText(currentSport.getInfo());

            mCurrentSport = currentSport;

            Glide.with(mContext).load(currentSport.getImageResource()).placeholder(mGradientDrawable).into(mSportsImage);
        }

        @Override
        public void onClick(View view) {
            Intent detailIntent = Sport.starter(mContext, mCurrentSport.getTitle(),
                    mCurrentSport.getImageResource());

            mContext.startActivity(detailIntent);
        }
    }
}