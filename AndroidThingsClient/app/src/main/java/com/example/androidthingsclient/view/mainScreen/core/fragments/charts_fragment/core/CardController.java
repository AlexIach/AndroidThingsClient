package com.example.androidthingsclient.view.mainScreen.core.fragments.charts_fragment.core;

/**
 * Created by aiachimov on 6/30/17.
 */

import android.os.Handler;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.androidthingsclient.R;


public class CardController {


    private final ImageButton playBtn;

    private final ImageButton updateBtn;

    private final Runnable unlockAction = new Runnable() {
        @Override
        public void run() {

            new Handler().postDelayed(new Runnable() {
                public void run() {

                    unlock();
                }
            }, 500);
        }
    };

    protected boolean firstStage;

    private final Runnable showAction = new Runnable() {
        @Override
        public void run() {

            new Handler().postDelayed(new Runnable() {
                public void run() {

                    show(unlockAction);
                }
            }, 500);
        }
    };


    protected CardController(CardView card) {

        super();

        RelativeLayout toolbar = (RelativeLayout) card.findViewById(R.id.chart_toolbar);
        playBtn = (ImageButton) toolbar.findViewById(R.id.play);
        updateBtn = (ImageButton) toolbar.findViewById(R.id.update);

        playBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dismiss(showAction);
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                update();
            }
        });
    }


    public void init() {

        show(unlockAction);
    }


    protected void show(Runnable action) {

        lock();
        firstStage = false;
    }


    protected void update() {

        lock();
        firstStage = !firstStage;
    }


    protected void dismiss(Runnable action) {

        lock();
    }


    private void lock() {

        playBtn.setEnabled(false);
        updateBtn.setEnabled(false);
    }


    private void unlock() {

        playBtn.setEnabled(true);
        updateBtn.setEnabled(true);
    }
}
