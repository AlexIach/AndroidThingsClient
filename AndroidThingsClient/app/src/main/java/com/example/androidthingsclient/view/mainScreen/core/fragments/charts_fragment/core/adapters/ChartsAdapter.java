package com.example.androidthingsclient.view.mainScreen.core.fragments.charts_fragment.core.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidthingsclient.R;
import com.example.androidthingsclient.view.mainScreen.core.fragments.charts_fragment.core.barchart.BarCardOne;
import com.example.androidthingsclient.view.mainScreen.core.fragments.charts_fragment.core.linechart.LineCardOne;
import com.example.androidthingsclient.view.mainScreen.core.fragments.charts_fragment.core.linechart.LineCardThree;
import com.example.androidthingsclient.view.mainScreen.core.fragments.charts_fragment.core.stackedchart.StackedCardOne;

import javax.inject.Inject;

/**
 * Created by aiachimov on 6/30/17.
 */

public class ChartsAdapter extends RecyclerView.Adapter<ChartsAdapter.ViewHolder> {


    private final static int NUM_CHARTS = 4;
    private final Context context;

    @Inject
    public ChartsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ChartsAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        View v;
        switch (viewType) {
            case 0:
                v = LayoutInflater.from(context).inflate(R.layout.chart1, parent, false);
                break;
            case 1:
                v = LayoutInflater.from(context).inflate(R.layout.chart2, parent, false);
                break;
            case 2:
                v = LayoutInflater.from(context).inflate(R.layout.chart3, parent, false);
                break;
            case 4:
                v = LayoutInflater.from(context).inflate(R.layout.chart5, parent, false);
                break;
            default:
                v = LayoutInflater.from(context).inflate(R.layout.chart5, parent, false);
                break;
        }
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        switch (position) {
            case 0:
                (new LineCardOne(holder.cardView, context)).init();
                break;
            case 1:
                (new LineCardThree(holder.cardView, context)).init();
                break;
            case 2:
                (new BarCardOne(holder.cardView, context)).init();
                break;
            case 4:
                (new StackedCardOne(holder.cardView)).init();
                break;
            default:
                (new StackedCardOne(holder.cardView)).init();
                break;
        }
    }

    @Override
    public int getItemCount() {

        return NUM_CHARTS;
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        final CardView cardView;

        ViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.chart_card);
        }
    }
}
