package com.example.androidthingsclient.view.mainScreen.core.fragments.charts_fragment.core.stackedchart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.db.chart.animation.Animation;
import com.db.chart.model.BarSet;
import com.db.chart.view.HorizontalStackBarChartView;
import com.example.androidthingsclient.R;
import com.example.androidthingsclient.view.mainScreen.core.fragments.charts_fragment.core.CardController;


public class StackedCardThree extends CardController {


    private final HorizontalStackBarChartView chart;

    private final String[] labels = {"", "", "", ""};

    private final float[][] values =
            {{30f, 60f, 50f, 80f}, {-70f, -40f, -50f, -20f}, {50f, 70f, 10f, 30f},
                    {-40f, -70f, -60f, -50f}};


    public StackedCardThree(CardView card, Context context) {

        super(card);

        chart = (HorizontalStackBarChartView) card.findViewById(R.id.chart);
        ((TextView) card.findViewById(R.id.electric_text)).setTypeface(
                Typeface.createFromAsset(context.getAssets(), "Ponsi-Regular.otf"));
        ((TextView) card.findViewById(R.id.fuel_text)).setTypeface(
                Typeface.createFromAsset(context.getAssets(), "Ponsi-Regular.otf"));
    }


    @Override
    public void show(Runnable action) {

        super.show(action);

        BarSet dataset = new BarSet(labels, values[0]);
        dataset.setColor(Color.parseColor("#687E8E"));
        chart.addData(dataset);

        dataset = new BarSet(labels, values[1]);
        dataset.setColor(Color.parseColor("#FF5C8E67"));
        chart.addData(dataset);

        chart.setAxisBorderValues(-80, 80, 10)
                .show(new Animation().setInterpolator(new DecelerateInterpolator())
                        .withEndAction(action));
    }


    @Override
    public void update() {

        super.update();

        if (firstStage) {
            chart.updateValues(0, values[2]);
            chart.updateValues(1, values[3]);
        } else {
            chart.updateValues(0, values[0]);
            chart.updateValues(1, values[1]);
        }
        chart.notifyDataUpdate();
    }


    @Override
    public void dismiss(Runnable action) {

        super.dismiss(action);

        chart.dismiss(chart.getChartAnimation().setInterpolator(new AccelerateInterpolator()).withEndAction(action));
    }

}
