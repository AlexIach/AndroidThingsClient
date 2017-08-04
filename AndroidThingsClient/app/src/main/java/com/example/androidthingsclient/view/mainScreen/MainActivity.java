package com.example.androidthingsclient.view.mainScreen;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.androidthingsclient.Injector;
import com.example.androidthingsclient.R;
import com.example.androidthingsclient.util.ScreenNavigationManager;
import com.example.androidthingsclient.util.StringUtil;
import com.example.androidthingsclient.view.mainScreen.core.adapters.MainPagerAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {

    @Inject
    ScreenNavigationManager screenNavigationManager;
    @Inject
    MainPagerAdapter mainPagerAdapter;
    @Inject
    StringUtil stringUtil;

    @BindView(R.id.vp_horizontal_ntb)
    ViewPager viewPager;
    @BindView(R.id.ntb_horizontal)
    NavigationTabBar navigationTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Injector.INSTANCE.initMainActivityComponent(this);
        Injector.INSTANCE.getMainActivityComponent().inject(this);

        initNavigationTabBar();

    }

    private void initNavigationTabBar() {
        viewPager.setAdapter(mainPagerAdapter);

        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();

        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.oil_temperature),
                        getResources().getColor(R.color.primary_dark))
                        .selectedIcon(getResources().getDrawable(R.drawable.temperature_celsius))
                        .title(stringUtil.getStringFromRes(this, R.string.temperature))
                        .badgeTitle(stringUtil.getStringFromRes(this, R.string.tap_to_check))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.water),
                        getResources().getColor(R.color.primary_dark))
                        .selectedIcon(getResources().getDrawable(R.drawable.water_percent))
                        .title(stringUtil.getStringFromRes(this, R.string.humidity))
                        .badgeTitle(stringUtil.getStringFromRes(this, R.string.tap_to_check))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.gauge),
                        getResources().getColor(R.color.primary_dark))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_switch))
                        .title(stringUtil.getStringFromRes(this, R.string.pressure))
                        .badgeTitle(stringUtil.getStringFromRes(this, R.string.tap_to_check))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.ic_lightbulb_outline_black_48dp),
                        getResources().getColor(R.color.primary_dark))
                        .selectedIcon(getResources().getDrawable(R.mipmap.ic_highlight_black_48dp))
                        .title(stringUtil.getStringFromRes(this, R.string.lights))
                        .badgeTitle(stringUtil.getStringFromRes(this, R.string.tap_to_check))
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
                //NOT IMPLEMENTED
            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {
                //NOT IMPLEMENTED
            }
        });

        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }
}
