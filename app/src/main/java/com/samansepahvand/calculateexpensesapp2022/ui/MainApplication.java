package com.samansepahvand.calculateexpensesapp2022.ui;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

public class MainApplication  extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        intiActiveAndroid();
    }


    private void intiActiveAndroid(){
        ActiveAndroid.initialize(this);
    }
}
