package com.samansepahvand.calculateexpensesapp2022.bussines.repository;

import android.content.Context;

import com.activeandroid.util.SQLiteUtils;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.DateModel;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.InfoMetaModel;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.OperationResult;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.ResultMessage;
import com.samansepahvand.calculateexpensesapp2022.db.Info;
import com.samansepahvand.calculateexpensesapp2022.infrastructure.Utility;

import java.util.List;

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

    public OperationResult AddPrice(Info info) {



        try{


            info.save();

            return new OperationResult(ResultMessage.SuccessMessage,true,null);
        }catch (Exception e){

            return new OperationResult(ResultMessage.ErrorMessage,false,e.getMessage());
        }
    }

    public OperationResult GetInfo(DateModel dateModel) {

        try{

            String  Query=" SELECT * FROM INFO i " +
                    " left join  priceType p on p.priceTypeItemId=i.priceTypeItemId and p.priceTypeId=i.priceTypeId " +
                    " where i.actionDate>="+dateModel.getFromDate()+" and i.actionDate<="+dateModel.getToDate()+
                    " order by i.id desc ";

    List<InfoMetaModel> metaModelList=
            SQLiteUtils.rawQuery(InfoMetaModel.class,Query,null);


    if (metaModelList==null)
        return new OperationResult(ResultMessage.ErrorMessage,false,null);



    int totalPrice=0;

    for (InfoMetaModel item:metaModelList){
        totalPrice+=item.getPrice();
        item.setFarsiDate(Utility.ShowTimeFarsiMeta(item));

    }

            return new OperationResult(String.valueOf(totalPrice),true,null,null,metaModelList);

        }catch (Exception e){


            return new OperationResult(ResultMessage.ErrorMessage,false,e.getMessage());

        }




    }


    //add-price


    //show-list-invoices



    //update.delete.read.create  //crud

}
