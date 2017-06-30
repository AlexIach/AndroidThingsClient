package com.example.androidthingsclient.view.mainScreen.core.fragments.charts_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidthingsclient.Injector;
import com.example.androidthingsclient.R;
import com.example.androidthingsclient.view.mainScreen.core.fragments.charts_fragment.core.adapters.ChartsAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aiachimov on 6/19/17.
 */

public class ChartsFragment extends Fragment {

    private final static int FULL_SPAN = 3;
    private final static int MULTI_SPAN = 2;
    private final static int SINGLE_SPAN = 1;

    @Inject
    ChartsAdapter chartAdapter;

    @BindView(R.id.recycler_view_Graphics)
    RecyclerView recyclerViewGraphics;


    public static ChartsFragment newInstance() {
        return new ChartsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Injector.INSTANCE.getMainActivityComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graphiscs, container, false);
        ButterKnife.bind(this, view);


        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), FULL_SPAN);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                switch (chartAdapter.getItemViewType(position)) {
                    case 0:
                        return FULL_SPAN;
                    case 1:
                        return MULTI_SPAN;
                    case 2:
                        return SINGLE_SPAN;
                    case 3:
                        return FULL_SPAN;
                    case 4:
                        return FULL_SPAN;
                    case 5:
                        return SINGLE_SPAN;
                    case 6:
                        return MULTI_SPAN;
                    case 7:
                        return FULL_SPAN;
                    case 8:
                        return FULL_SPAN;
                    default:
                        return SINGLE_SPAN;
                }
            }
        });

        chartAdapter = new ChartsAdapter(getContext());

        recyclerViewGraphics.setLayoutManager(mLayoutManager);
        recyclerViewGraphics.setAdapter(chartAdapter);
        return view;
    }
}
