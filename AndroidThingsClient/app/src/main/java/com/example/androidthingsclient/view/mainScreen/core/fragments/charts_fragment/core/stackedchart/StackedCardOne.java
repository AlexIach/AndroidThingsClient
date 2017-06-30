package com.example.androidthingsclient.view.mainScreen.core.fragments.charts_fragment.core.stackedchart;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.widget.TextView;

import com.db.chart.animation.Animation;
import com.db.chart.listener.OnEntryClickListener;
import com.db.chart.model.BarSet;
import com.db.chart.renderer.XRenderer;
import com.db.chart.renderer.YRenderer;
import com.db.chart.util.Tools;
import com.db.chart.view.StackBarChartView;
import com.example.androidthingsclient.R;
import com.example.androidthingsclient.view.mainScreen.core.fragments.charts_fragment.core.CardController;


public class StackedCardOne extends CardController {


    private final TextView legendOneRed;

    private final TextView legendOneYellow;

    private final TextView legendOneGreen;


    private final StackBarChartView chart;

    private final String[] labels =
            {"JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ"};

    private final float[][] valuesOne =
            {{30f, 40f, 25f, 25f, 40f, 25f, 25f, 30f, 30f, 25f, 40f, 25f},
                    {30f, 30f, 25f, 40f, 25f, 30f, 40f, 30f, 30f, 25f, 25f, 25f},
                    {30f, 30f, 25f, 25f, 25f, 25f, 25f, 30f, 40f, 25, 25, 40f}};


    public StackedCardOne(CardView card) {

        super(card);

        chart = (StackBarChartView) card.findViewById(R.id.chart);
        legendOneRed = (TextView) card.findViewById(R.id.state_one);
        legendOneYellow = (TextView) card.findViewById(R.id.state_two);
        legendOneGreen = (TextView) card.findViewById(R.id.state_three);
    }


    @Override
    public void show(Runnable action) {

        super.show(action);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            chart.setOnEntryClickListener(new OnEntryClickListener() {
                @SuppressLint("NewApi")
                @Override
                public void onClick(int setIndex, int entryIndex, Rect rect) {

                    if (setIndex == 2) legendOneRed.animate()
                            .scaleY(1.3f)
                            .scaleX(1.3f)
                            .setDuration(100)
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {

                                    legendOneRed.animate().scaleY(1.0f).scaleX(1.0f).setDuration(100);
                                }
                            });
                    else if (setIndex == 1) {
                        legendOneYellow.animate()
                                .scaleY(1.3f)
                                .scaleX(1.3f)
                                .setDuration(100)
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {

                                        legendOneYellow.animate()
                                                .scaleY(1.0f)
                                                .scaleX(1.0f)
                                                .setDuration(100);
                                    }
                                });
                    } else {
                        legendOneGreen.animate()
                                .scaleY(1.3f)
                                .scaleX(1.3f)
                                .setDuration(100)
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {

                                        legendOneGreen.animate()
                                                .scaleY(1.0f)
                                                .scaleX(1.0f)
                                                .setDuration(100);
                                    }
                                });
                    }
                }
            });

        Paint thresPaint = new Paint();
        thresPaint.setColor(Color.parseColor("#dad8d6"));
        thresPaint.setPathEffect(new DashPathEffect(new float[]{10, 20}, 0));
        thresPaint.setStyle(Paint.Style.STROKE);
        thresPaint.setAntiAlias(true);
        thresPaint.setStrokeWidth(Tools.fromDpToPx(.75f));

        BarSet stackBarSet = new BarSet(labels, valuesOne[0]);
        stackBarSet.setColor(Color.parseColor("#a1d949"));
        chart.addData(stackBarSet);

        stackBarSet = new BarSet(labels, valuesOne[1]);
        stackBarSet.setColor(Color.parseColor("#ffcc6a"));
        chart.addData(stackBarSet);

        stackBarSet = new BarSet(labels, valuesOne[2]);
        stackBarSet.setColor(Color.parseColor("#ff7a57"));
        chart.addData(stackBarSet);

        int[] order = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        chart.setXLabels(XRenderer.LabelPosition.OUTSIDE)
                .setYLabels(YRenderer.LabelPosition.NONE)
                .setValueThreshold(89.f, 89.f, thresPaint)
                .show(new Animation().inSequence(.5f, order).withEndAction(action));
    }


    @Override
    public void update() {

        super.update();

        if (firstStage) {
            chart.updateValues(0, valuesOne[2]);
            chart.updateValues(1, valuesOne[0]);
            chart.updateValues(2, valuesOne[1]);
        } else {
            chart.updateValues(0, valuesOne[0]);
            chart.updateValues(1, valuesOne[1]);
            chart.updateValues(2, valuesOne[2]);
        }
        chart.notifyDataUpdate();
    }


    @Override
    public void dismiss(Runnable action) {

        super.dismiss(action);

        int[] order = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        chart.dismiss(new Animation().inSequence(.5f, order).withEndAction(action));
    }

}
