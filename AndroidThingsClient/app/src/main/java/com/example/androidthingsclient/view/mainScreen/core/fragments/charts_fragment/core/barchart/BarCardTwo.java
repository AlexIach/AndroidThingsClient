package com.example.androidthingsclient.view.mainScreen.core.fragments.charts_fragment.core.barchart;

import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.db.chart.animation.Animation;
import com.db.chart.listener.OnEntryClickListener;
import com.db.chart.model.Bar;
import com.db.chart.model.BarSet;
import com.db.chart.renderer.XRenderer;
import com.db.chart.tooltip.Tooltip;
import com.db.chart.view.HorizontalBarChartView;
import com.example.androidthingsclient.R;
import com.example.androidthingsclient.view.mainScreen.core.fragments.charts_fragment.core.CardController;

import java.util.Locale;


public class BarCardTwo extends CardController {


    private final Context context;


    private final TextView textViewValue;


    private final HorizontalBarChartView chart;

    private final String[] labels = {"Mon", "Tue", "Wed", "Thu", "Fri"};

    private final float[][] values = {{23f, 34f, 55f, 71f, 98f}, {17f, 26f, 48f, 63f, 94f}};


    public BarCardTwo(CardView card, Context context) {

        super(card);

        this.context = context;
        chart = (HorizontalBarChartView) card.findViewById(R.id.chart);
        textViewValue = (TextView) card.findViewById(R.id.value);

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "OpenSans-Semibold.ttf");
        textViewValue.setTypeface(typeface);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1)
            textViewValue.animate().alpha(0).setDuration(100);
        else textViewValue.setVisibility(View.INVISIBLE);
    }


    @Override
    public void show(Runnable action) {

        super.show(action);

        Tooltip tip = new Tooltip(context);
        tip.setBackgroundColor(Color.parseColor("#f39c12"));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            tip.setEnterAnimation(PropertyValuesHolder.ofFloat(View.ALPHA, 1)).setDuration(150);
            tip.setExitAnimation(PropertyValuesHolder.ofFloat(View.ALPHA, 0)).setDuration(150);
        }

        chart.setTooltips(tip);

        chart.setOnEntryClickListener(new OnEntryClickListener() {
            @Override
            public void onClick(int setIndex, int entryIndex, Rect rect) {

                textViewValue.setText(String.format(Locale.ENGLISH, "%d", (int) values[0][entryIndex]));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1)
                    textViewValue.animate().alpha(1).setDuration(200);
                else textViewValue.setVisibility(View.VISIBLE);
            }
        });

        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1)
                    textViewValue.animate().alpha(0).setDuration(100);
                else textViewValue.setVisibility(View.INVISIBLE);
            }
        });

        BarSet barSet = new BarSet();
        Bar bar;
        for (int i = 0; i < labels.length; i++) {
            bar = new Bar(labels[i], values[0][i]);
            switch (i) {
                case 0:
                    bar.setColor(Color.parseColor("#77c63d"));
                    break;
                case 1:
                    bar.setColor(Color.parseColor("#27ae60"));
                    break;
                case 2:
                    bar.setColor(Color.parseColor("#47bac1"));
                    break;
                case 3:
                    bar.setColor(Color.parseColor("#16a085"));
                    break;
                case 4:
                    bar.setColor(Color.parseColor("#3498db"));
                    break;
                default:
                    break;
            }
            barSet.addBar(bar);
        }
        chart.addData(barSet);

        int[] order = {4, 3, 2, 1, 0};
        chart.setXLabels(XRenderer.LabelPosition.NONE)
                .show(new Animation().inSequence(.5f, order).withEndAction(action));
    }


    @Override
    public void update() {

        super.update();

        chart.dismissAllTooltips();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1)
            textViewValue.animate().alpha(0).setDuration(100);
        else textViewValue.setVisibility(View.INVISIBLE);

        if (firstStage) chart.updateValues(0, values[1]);
        else chart.updateValues(0, values[0]);
        chart.notifyDataUpdate();
    }


    @Override
    public void dismiss(Runnable action) {

        super.dismiss(action);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1)
            textViewValue.animate().alpha(0).setDuration(100);
        else textViewValue.setVisibility(View.INVISIBLE);

        chart.dismissAllTooltips();

        int[] order = {0, 1, 2, 3, 4};
        chart.dismiss(new Animation().inSequence(.5f, order).withEndAction(action));
    }

}
