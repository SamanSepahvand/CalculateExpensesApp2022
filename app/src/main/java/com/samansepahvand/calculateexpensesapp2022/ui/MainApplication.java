package com.samansepahvand.calculateexpensesapp2022.ui;

import android.app.Application;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.activeandroid.ActiveAndroid;
import com.samansepahvand.calculateexpensesapp2022.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MainApplication extends Application {

    private static Context context;
    private static Animation animation;


    public MainApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        intiActiveAndroid();
        MainApplication.context = getApplicationContext();
        fontAssign();

    }


    public void fontAssign() {

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/iran_sans.ttf")
                .setFontAttrId(R.attr.fontPath)
                .disableCustomViewInflation()
                .build()
        );
    }


    private void intiActiveAndroid() {
        ActiveAndroid.initialize(this);
    }

    public static Context getAppMainContext() {
        return MainApplication.context;
    }


    public static Animation SetAnimation(String typeAnimation) {

        switch (typeAnimation) {
            case "Rotate":
                animation = AnimationUtils.loadAnimation(context, R.anim.anim_rotate_menu);
                break;
            case "RotateSlow":
                animation = AnimationUtils.loadAnimation(context, R.anim.rotate_anim);
                break;
            case "FadeIn":
                animation = AnimationUtils.loadAnimation(context, R.anim.fade_anim_dialog);
                break;
            case "FadeInLong":
                animation = AnimationUtils.loadAnimation(context, R.anim.fade_anim);
                break;
            case "SlideUp":
                animation = AnimationUtils.loadAnimation(context, R.anim.slide_up);
                break;
            case "SlideUpSlow":
                animation = AnimationUtils.loadAnimation(context, R.anim.slide_up_slow);
                break;
            case "FadeOut":
                animation = AnimationUtils.loadAnimation(context, R.anim.fade_out);
                break;


        }
        return animation;

    }


}
