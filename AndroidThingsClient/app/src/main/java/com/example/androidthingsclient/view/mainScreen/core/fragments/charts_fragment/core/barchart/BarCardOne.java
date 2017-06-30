package com.example.androidthingsclient.view.mainScreen.core.fragments.charts_fragment.core.barchart;

import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.view.View;

import com.db.chart.animation.Animation;
import com.db.chart.model.BarSet;
import com.db.chart.renderer.XRenderer;
import com.db.chart.renderer.YRenderer;
import com.db.chart.tooltip.Tooltip;
import com.db.chart.util.Tools;
import com.db.chart.view.BarChartView;
import com.example.androidthingsclient.R;
import com.example.androidthingsclient.view.mainScreen.core.fragments.charts_fragment.core.CardController;


public class BarCardOne extends CardController {


    private final Context context;

    private final BarChartView chart;

    private final String[] labels = {"A", "B", "C", "D"};

    private final float[][] values = {{6.5f, 8.5f, 2.5f, 10f}, {7.5f, 3.5f, 5.5f, 4f}};


    public BarCardOne(CardView card, Context context) {

        super(card);
        chart = (BarChartView) card.findViewById(R.id.chart);
        this.context = context;
    }


    @Override
    public void show(Runnable action) {

        super.show(action);

        // Data
        BarSet barSet = new BarSet(labels, values[0]);
        barSet.setColor(Color.parseColor("#fc2a53"));
        chart.addData(barSet);

        int[] order = {1, 0, 2, 3};
        final Runnable auxAction = action;
        Runnable chartOneAction = new Runnable() {
            @Override
            public void run() {

                auxAction.run();
                showTooltip();
            }
        };

        chart.setXLabels(XRenderer.LabelPosition.OUTSIDE)
                .setYLabels(YRenderer.LabelPosition.NONE)
                .show(new Animation().inSequence(.5f, order).withEndAction(chartOneAction));
    }


    @Override
    public void update() {

        super.update();

        chart.dismissAllTooltips();
        if (firstStage) chart.updateValues(0, values[1]);
        else chart.updateValues(0, values[0]);
        chart.notifyDataUpdate();
    }


    @Override
    public void dismiss(Runnable action) {

        super.dismiss(action);

        chart.dismissAllTooltips();
        int[] order = {0, 2, 1, 3};
        chart.dismiss(new Animation().inSequence(.5f, order).withEndAction(action));
    }


    private void showTooltip() {

        // Tooltip
        Tooltip tip = new Tooltip(context, R.layout.square_tooltip);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {

            tip.setEnterAnimation(PropertyValuesHolder.ofFloat(View.ALPHA, 1),
                    PropertyValuesHolder.ofFloat(View.SCALE_X, 1f),
                    PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f)).setDuration(200);

            tip.setExitAnimation(PropertyValuesHolder.ofFloat(View.ALPHA, 0),
                    PropertyValuesHolder.ofFloat(View.SCALE_X, 0f),
                    PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f)).setDuration(200);
        }
        tip.setVerticalAlignment(Tooltip.Alignment.BOTTOM_TOP);
        tip.setDimensions((int) Tools.fromDpToPx(25), (int) Tools.fromDpToPx(25));
        tip.setMargins(0, 0, 0, (int) Tools.fromDpToPx(10));
        tip.prepare(chart.getEntriesArea(0).get(2), 0);

        chart.showTooltip(tip, true);
    }

}
