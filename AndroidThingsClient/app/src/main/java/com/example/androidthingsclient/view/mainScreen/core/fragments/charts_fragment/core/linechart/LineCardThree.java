package com.example.androidthingsclient.view.mainScreen.core.fragments.charts_fragment.core.linechart;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;

import com.db.chart.animation.Animation;
import com.db.chart.model.LineSet;
import com.db.chart.view.LineChartView;
import com.example.androidthingsclient.R;
import com.example.androidthingsclient.view.mainScreen.core.fragments.charts_fragment.core.CardController;


public class LineCardThree extends CardController {


    private final LineChartView chart;

    private final String[] labels = {"", "", "", "", "", "", "", "", ""};

    private final float[][] values = {{0f, 2f, 1.4f, 4.f, 3.5f, 4.3f, 2f, 4f, 6.f},
            {1.5f, 2.5f, 1.5f, 5f, 4f, 5f, 4.3f, 2.1f, 1.4f}};


    public LineCardThree(CardView card, Context context) {

        super(card);

        chart = (LineChartView) card.findViewById(R.id.chart);
    }


    @Override
    public void show(Runnable action) {

        super.show(action);

        LineSet dataset = new LineSet(labels, values[0]);
        dataset.setColor(Color.parseColor("#53c1bd"))
                .setFill(Color.parseColor("#3d6c73"))
                .setGradientFill(new int[]{Color.parseColor("#364d5a"), Color.parseColor("#3f7178")},
                        null);
        chart.addData(dataset);

        chart.show(new Animation().withEndAction(action));
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
        chart.dismiss(new Animation().withEndAction(action));
    }
}
