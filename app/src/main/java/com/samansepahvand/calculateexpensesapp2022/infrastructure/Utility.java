package com.samansepahvand.calculateexpensesapp2022.infrastructure;

import android.animation.ObjectAnimator;
import android.widget.ImageView;

public   class Utility {



    public static void   RotateImage(ImageView imageView){

        ObjectAnimator animator=ObjectAnimator.ofFloat(imageView,"rotation",0f,90f);
        animator.setDuration(200);
        animator.start();
    }


}
