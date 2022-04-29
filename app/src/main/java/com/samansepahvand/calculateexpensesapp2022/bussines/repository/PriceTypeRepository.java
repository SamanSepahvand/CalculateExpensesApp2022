package com.samansepahvand.calculateexpensesapp2022.bussines.repository;

import android.content.Context;

public class PriceTypeRepository {

    private static final String TAG = "PriceTypeRepository";

    public static PriceTypeRepository priceTypeRepository=null;

    private Context context;


    //alt+insert


    public PriceTypeRepository() {
    }


    public static PriceTypeRepository getInstance(){
        if (priceTypeRepository==null){
            synchronized (PriceTypeRepository.class){
                if (priceTypeRepository==null){
                    priceTypeRepository=new PriceTypeRepository();
                }
            }
        }
        return priceTypeRepository;
    }



// add price



//show all price


}
