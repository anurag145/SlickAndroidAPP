package com.github.anurag145.slick;

import android.app.Application;

import com.estimote.sdk.EstimoteSDK;

/**
 * Created by anura on 3/13/2018.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EstimoteSDK.initialize(getApplicationContext(), "anurag145-gmail-com-s-your-g5p", "b67d1f0b7dda12ad9737b09b6186671a");

    }
}
