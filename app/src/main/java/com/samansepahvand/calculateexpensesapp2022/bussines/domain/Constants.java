package com.samansepahvand.calculateexpensesapp2022.bussines.domain;

import android.graphics.Typeface;

import com.samansepahvand.calculateexpensesapp2022.R;
import com.samansepahvand.calculateexpensesapp2022.ui.MainApplication;

public class Constants {


    public static final int DelayTimeSplash = 2500;
    public static final int DelayFast = 500;
    public static final int DelayMoreFast = 300;
    public static final int DelayTime = 1500;
    public static final int DelayTimeDialogAnimation = 500;


    public static Typeface CustomStyleElement() {
        Typeface fontIranSans = Typeface.createFromAsset(MainApplication.getAppMainContext().getAssets(),
                "fonts/iran_sans.ttf");
        return fontIranSans;
    }



    public static int[] TypeImageAlertDialog = new int[]{
            R.drawable.ic_baseline_info_black_24,   //0
            R.drawable.ic_baseline_info_yellow_24,  //1 info
            R.drawable.ic_baseline_warning_24,      //2 error
            R.drawable.ic_baseline_check_circle_24, //3 success
    };


    public static int[] TypeButtonStyleAlertDialog = new int[]{
            R.drawable.shape_background_button_dialog_cancel,  //0
            R.drawable.shape_background_button_dialog_yellow,  //1
            R.drawable.shape_background_button_dialog_red,     //2 error
            R.drawable.shape_background_button_dialog_confirm, //3 success
            R.drawable.shape_background_button_dialog_confirm_light, //4 success

    };






    public static String[] PriceTypeHeader = new String[]{
            "خرید", //0
            "اجاره",
            "ماشین",
            "خوراک",
            "رفت و آمد",
            "سرگرمی",
            "قبوض" //6
    };


    public static int[] PriceTypeHeaderPicture = new int[]{


            R.drawable.icons8_shopee_64,
            R.drawable.icons8_rent_64,
            R.drawable.icons8_car_64,
            R.drawable.icons8_ingredients_64,
            R.drawable.icons8_traveler_64,
            R.drawable.icons8_entertainment_64,
            R.drawable.icons8_cash_receipt_64,


    };




    public static String[] CarPriceTypeItem = new String[]{
            "بنزین",
            "بیمه",
            "جریمه",
            "تعمیرگاه"
    };



    public static String[] BuyPriceTypeItem = new String[]{
            "لباس",
            "هدیه",
            "کارت شارژ",
            "اینترنت",
            "دارو",
            "کتاب",
            "کالا"
    };



    public static String[] RentPriceTypeItem = new String[]{
            "خانه",
            "شرکت",
            "مغازه"
    };




    public static String[] FoodPriceTypeItem = new String[]{
            "رستوران",
            "کافی شاپ",
            "آنلاین شاپینگ"
    };


    public static String[] CommutingPriceTypeItem = new String[]{
            "اتوبوس",
            "تاکسی",
            "مترو"
    };

    public static String[] EntertainmentPriceTypeItem = new String[]{
            "شهربازی",
            "سینما",
            "تئاتر",
            "کنسرت"
    };

    public static String[] BillsPriceTypeItem = new String[]{
            "موبایل",
            "تلفن",
            "برق",
            "آب"
    };



}
