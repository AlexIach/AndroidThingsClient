package com.example.androidthingsclient.view.mainScreen.core.fragments.lights_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidthingsclient.Injector;
import com.example.androidthingsclient.R;
import com.example.androidthingsclient.view.mainScreen.core.fragments.lights_fragment.core.LightPresenter;
import com.zcw.togglebutton.ToggleButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aiachimov on 6/19/17.
 */

public class LightsFragment extends Fragment implements LightPresenter.LightCallBack {

    @BindView(R.id.imageViewKitchen)
    ImageView imageViewKitchen;
    @BindView(R.id.imageViewBathroom)
    ImageView imageViewBathroom;
    @BindView(R.id.imageViewMainRoom)
    ImageView imageViewMainRoom;
    @BindView(R.id.textViewStatusKitchen)
    TextView textViewKitchen;
    @BindView(R.id.textViewStatusBathRoom)
    TextView textViewBathroom;
    @BindView(R.id.textViewStatusMainRoom)
    TextView textViewMainRoom;
    @BindView(R.id.toggleButtonKitchen)
    ToggleButton toggleButtonKitchen;
    @BindView(R.id.toggleButtonbathRoom)
    ToggleButton toggleButtonBathroom;
    @BindView(R.id.toggleButtonMainRoom)
    ToggleButton toggleButtonMainRoom;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lightPresenter.setUpCallback(this);
        lightPresenter.onLoadLightStatuses();
    }

    @Inject
    LightPresenter lightPresenter;


    public static LightsFragment newInstance() {
        return new LightsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Injector.INSTANCE.getMainActivityComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lights, container, false);
        ButterKnife.bind(this, view);
        toggleButtonKitchen.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                Log.d("TAG", "KITCHEN bulb is enable " + on);
                if (on) {
                    imageViewKitchen.setImageResource(R.drawable.bulb_on);
                } else {
                    imageViewKitchen.setImageResource(R.drawable.bulb_off);
                }
                lightPresenter.uploadKitchenLightStatus(on);
            }
        });

        toggleButtonBathroom.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                Log.d("TAG", "BATHROOM bulb is enable " + on);
                if (on) {
                    imageViewBathroom.setImageResource(R.drawable.bulb_on);
                } else {
                    imageViewBathroom.setImageResource(R.drawable.bulb_off);
                }
                lightPresenter.uploadBathroomLightStatus(on);
            }
        });

        toggleButtonMainRoom.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                Log.d("TAG", "MAIN ROOM bulb is enable " + on);
                if (on) {
                    imageViewMainRoom.setImageResource(R.drawable.bulb_on);
                } else {
                    imageViewMainRoom.setImageResource(R.drawable.bulb_off);
                }
                lightPresenter.uploadMainRoomLightStatus(on);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onLightStatusLoaded(boolean isLightKitchenEnable, boolean isLightBthroomEnable, boolean isLightMainRoomEnable) {
        if (isLightKitchenEnable) {
            toggleButtonKitchen.toggleOn();
        } else {
            toggleButtonKitchen.toggleOff();
        }
        if (isLightBthroomEnable) {
            toggleButtonBathroom.toggleOn();
        } else {
            toggleButtonBathroom.toggleOff();
        }
        if (isLightMainRoomEnable) {
            toggleButtonMainRoom.toggleOn();
        } else {
            toggleButtonMainRoom.toggleOff();
        }
    }

    @Override
    public void onLightStatusLoadFailed(String error) {
        Log.d("TAG", "Error is " + error);
    }
}
