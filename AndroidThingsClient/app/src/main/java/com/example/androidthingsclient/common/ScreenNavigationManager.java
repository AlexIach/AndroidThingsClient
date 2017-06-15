package com.example.androidthingsclient.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

/**
 * Created by aiachimov on 4/28/17.
 */

public class ScreenNavigationManager {
    private FragmentManager fragmentManager;

    @Inject
    public ScreenNavigationManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void switchActivity(AppCompatActivity appCompatActivity, Class<? extends AppCompatActivity> extendsBaseActivity) {
        switchActivity(appCompatActivity, extendsBaseActivity, null);
    }

    public void switchActivity(AppCompatActivity appCompatActivity, Class<? extends AppCompatActivity> extendsBaseActivity, Bundle bundle) {
        appCompatActivity.startActivity(getIntent(appCompatActivity, extendsBaseActivity), bundle);

    }

    public void switchFragment(Fragment fragment, int containerId, boolean shouldAddToBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, fragment);

        if (shouldAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    public void switchFragment(Fragment fragment, int containerId) {
        switchFragment(fragment, containerId, true);
    }

    private Intent getIntent(AppCompatActivity AppCompatActivity, Class<? extends AppCompatActivity> extendsBaseActivityClass) {
        return new Intent(AppCompatActivity, extendsBaseActivityClass);
    }

    public void switchActivityWithTransition(AppCompatActivity appCompatActivity, Intent i, ActivityOptionsCompat optionsCompat) {
        ActivityCompat.startActivity(appCompatActivity, i, optionsCompat.toBundle());
    }

    public void switchActivityWithBundleIntent(Context context, Intent intent) {
        context.startActivity(intent);
    }
}
