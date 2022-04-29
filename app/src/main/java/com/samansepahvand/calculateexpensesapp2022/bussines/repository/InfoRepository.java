package com.samansepahvand.calculateexpensesapp2022.bussines.repository;

import android.content.Context;

import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.OperationResult;
import com.samansepahvand.calculateexpensesapp2022.db.Info;

public class InfoRepository {

    private static final String TAG = "InfoRepository";

    public static InfoRepository infoRepository=null;

    private Context context;


    //alt+insert


    public InfoRepository() {
    }


    public static InfoRepository getInstance(){
        if (infoRepository==null){
            synchronized (InfoRepository.class){
                if (infoRepository==null){
                    infoRepository=new InfoRepository();
                }
            }
        }
        return infoRepository;
    }



    //add-price


    //show-list-invoices



    //update.delete.read.create  //crud

}
