package com.example.androidthingsclient.view.mainScreen.core.fragments.charts_fragment.core.linechart;

import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import com.db.chart.animation.Animation;
import com.db.chart.model.LineSet;
import com.db.chart.renderer.AxisRenderer;
import com.db.chart.tooltip.Tooltip;
import com.db.chart.util.Tools;
import com.db.chart.view.LineChartView;
import com.example.androidthingsclient.R;
import com.example.androidthingsclient.view.mainScreen.core.fragments.charts_fragment.core.CardController;


public class LineCardOne extends CardController {


    private final LineChartView chart;


    private final Context context;


    private final String[] labels = {"Day 1", "Day 2", "Day 3", "Day 4", "Day 5", "Day 6", "Day 7"};

    private final float[][] values = {{23f, 13f, 39f, 21f, 55f, 12f, 25f},
            {0f, 0f, 0f, 0f, 0f, 0f, 0f}};

    private Tooltip tip;

    private Runnable baseAction;


    public LineCardOne(CardView card, Context context) {

        super(card);

        this.context = context;
        chart = (LineChartView) card.findViewById(R.id.chart);
    }


    @Override
    public void show(Runnable action) {

        super.show(action);

        // Tooltip
        tip = new Tooltip(context, R.layout.linechart_three_tooltip, R.id.value);

        ((TextView) tip.findViewById(R.id.value)).setTypeface(
                Typeface.createFromAsset(context.getAssets(), "OpenSans-Semibold.ttf"));

        tip.setVerticalAlignment(Tooltip.Alignment.BOTTOM_TOP);
        tip.setDimensions((int) Tools.fromDpToPx(58), (int) Tools.fromDpToPx(25));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {

            tip.setEnterAnimation(PropertyValuesHolder.ofFloat(View.ALPHA, 1),
                    PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f),
                    PropertyValuesHolder.ofFloat(View.SCALE_X, 1f)).setDuration(200);

            tip.setExitAnimation(PropertyValuesHolder.ofFloat(View.ALPHA, 0),
                    PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f),
                    PropertyValuesHolder.ofFloat(View.SCALE_X, 0f)).setDuration(200);

            tip.setPivotX(Tools.fromDpToPx(60) / 2);
            tip.setPivotY(Tools.fromDpToPx(25));
        }

        // Data
        LineSet dataset = new LineSet(labels, values[0]);
        dataset.setColor(Color.parseColor("#758cbb"))
                .setFill(Color.parseColor("#2d374c"))
                .setDotsColor(Color.parseColor("#758cbb"))
                .setThickness(4)
                .setDashed(new float[]{10f, 10f})
                .beginAt(5);
        chart.addData(dataset);

        dataset = new LineSet(labels, values[0]);
        dataset.setColor(Color.parseColor("#b3b5bb"))
                .setFill(Color.parseColor("#2d374c"))
                .setDotsColor(Color.parseColor("#ffc755"))
                .setThickness(4)
                .endAt(6);
        chart.addData(dataset);

        baseAction = action;
        Runnable chartAction = new Runnable() {
            @Override
            public void run() {

                baseAction.run();
                tip.prepare(chart.getEntriesArea(0).get(3), values[0][3]);
                chart.showTooltip(tip, true);
            }
        };

        chart.setAxisBorderValues(0, 20)
                .setYLabels(AxisRenderer.LabelPosition.NONE)
                .setTooltips(tip)
                .show(new Animation().setInterpolator(new BounceInterpolator())
                        .fromAlpha(0)
                        .withEndAction(chartAction));
    }


    @Override
    public void update() {

        super.update();

        chart.dismissAllTooltips();
        if (firstStage) {
            chart.updateValues(0, values[1]);
            chart.updateValues(1, values[1]);
        } else {
            chart.updateValues(0, values[0]);
            chart.updateValues(1, values[0]);
        }
        chart.getChartAnimation().withEndAction(baseAction);
        chart.notifyDataUpdate();
    }


    @Override
    public void dismiss(Runnable action) {

        super.dismiss(action);

        chart.dismissAllTooltips();
        chart.dismiss(new Animation().setInterpolator(new BounceInterpolator()).withEndAction(action));
    }

}
