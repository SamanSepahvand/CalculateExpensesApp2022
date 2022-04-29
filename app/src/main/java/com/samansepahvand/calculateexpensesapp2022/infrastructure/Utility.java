package com.samansepahvand.calculateexpensesapp2022.infrastructure;

import android.animation.ObjectAnimator;
import android.widget.ImageView;

import com.samansepahvand.calculateexpensesapp2022.R;
import com.samansepahvand.calculateexpensesapp2022.helper.CalendarTool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public   class Utility {



    public static void   RotateImage(ImageView imageView){

        ObjectAnimator animator=ObjectAnimator.ofFloat(imageView,"rotation",0f,90f);
        animator.setDuration(200);
        animator.start();
    }


    public static int ChangeIconAddExpenses(int count) {
        if (count == 1) return R.drawable.ic_baseline_check_24;
        return R.drawable.ic_baseline_arrow_back_ios_24;

    }



    public static String getIranianDateInt() {
        CalendarTool tool = new CalendarTool();

        String date = String.format(Locale.US, "%d/%02d/%02d",
                tool.getIranianYear(), tool.getIranianMonth(),
                tool.getIranianDay());
        return date;
    }


    public static String getIranianDateCustom(Calendar calendar) {
        Calendar cNew=Calendar.getInstance();
        CalendarTool tool = new CalendarTool(calendar);
        String date = String.format(Locale.US, "%d/%02d/%02d %02d:%02d:%02d",
                tool.getIranianYear(), tool.getIranianMonth(),
                tool.getIranianDay(), cNew.get(Calendar.HOUR_OF_DAY),
                cNew.get(Calendar.MINUTE), cNew.get(Calendar.SECOND));
        return date;
    }

    public static String getIranianDate() {

        CalendarTool tool = new CalendarTool();
        Calendar cal = Calendar.getInstance();

        String date = String.format(Locale.US, "%d/%02d/%02d %02d:%02d:%02d",
                tool.getIranianYear(), tool.getIranianMonth(),
                tool.getIranianDay(), cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
        return date;
    }


    public static int GetActionDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar mDate = Calendar.getInstance();
        return Integer.parseInt(dateFormat.format(mDate.getTime()));
    }





}
