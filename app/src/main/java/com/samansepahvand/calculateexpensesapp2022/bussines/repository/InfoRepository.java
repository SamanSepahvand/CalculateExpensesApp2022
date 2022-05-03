package com.samansepahvand.calculateexpensesapp2022.bussines.repository;

import android.content.Context;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.util.SQLiteUtils;
import com.samansepahvand.calculateexpensesapp2022.bussines.domain.Enumerations;
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

    public OperationResult AddPrice(Info info,long Id) {



        try{

            if (Id!=0){
                Info infoUpdate=new Select().from(Info.class).where("id=?",Id).executeSingle();
                if (infoUpdate!=null){

                    infoUpdate.setPrice(info.getPrice());
                    infoUpdate.setTitle(info.getTitle());
                    infoUpdate.setEngDate(infoUpdate.getEngDate());
                    infoUpdate.setFarsiDate(infoUpdate.getFarsiDate());

                    infoUpdate.save();

                    return new OperationResult(ResultMessage.SuccessUpdateMessage,true,null);

                }else{
                    return new OperationResult(ResultMessage.ErrorFindInvoices,false,null);

                }


            }else{
                info.save();
                return new OperationResult(ResultMessage.SuccessMessage,true,null);

            }

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

    public OperationResult GetInfoByMeta(InfoMetaModel infoMetaModel, int invoiceActionType) {

        try{

            Info info=new Select().from(Info.class).where("id=?",infoMetaModel.getId()).executeSingle();

            if (info==null)   return new OperationResult(ResultMessage.ErrorFindInvoices,false,null);

            switch (invoiceActionType){
                case  Enumerations.InvoiceActionType.DELETE:
                    boolean isSuccess=DeleteInfo(info);
                 return new OperationResult(ResultMessage.SuccessMessage,isSuccess,null);

                case  Enumerations.InvoiceActionType.SHOW:

                    return new OperationResult(ResultMessage.SuccessMessage,true,null,info,null);

                default:
                 return  new OperationResult(ResultMessage.ErrorMessage,false,null);

            }

    }catch (Exception e){


        return new OperationResult(ResultMessage.ErrorMessage,false,e.getMessage());

    }

}

    private boolean DeleteInfo(Info info) {

        try{

            new Delete().from(Info.class)
                    .where("id=?",info.getId())
                    .execute();
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
