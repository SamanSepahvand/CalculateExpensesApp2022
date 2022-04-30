package com.samansepahvand.calculateexpensesapp2022.bussines.repository;

import static com.samansepahvand.calculateexpensesapp2022.bussines.domain.Constants.BuyPriceTypeItem;

import android.content.Context;
import android.util.SparseArray;

import com.activeandroid.query.Select;
import com.samansepahvand.calculateexpensesapp2022.bussines.domain.Constants;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.OperationResult;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.PriceTypeHeader;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.ResultMessage;
import com.samansepahvand.calculateexpensesapp2022.db.PriceType;

import java.util.List;

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

    public OperationResult<PriceType> GetLastIdPriceType(String tempPriceTypeIdHeader) {

        try {
            PriceType lastPriceType = new Select().from(PriceType.class)
                    .where("priceTypeId =?", Integer.parseInt(tempPriceTypeIdHeader)) //PriceType.PRICE_TYPE_ID
                    .orderBy("id desc")
                    .executeSingle();

            if (lastPriceType==null)  return  new OperationResult<>(ResultMessage.ErrorListMessage,false,null);

            return  new OperationResult<>(ResultMessage.SuccessMessage,true,null,lastPriceType,null);

        }catch (Exception e){

            return  new OperationResult<>(ResultMessage.ErrorListMessage,false,e.getMessage());
        }


    }

    public OperationResult<PriceType> AddCustomPriceType(PriceType priceType) {

        try {

            if (priceType==null) {
                return new OperationResult<>(ResultMessage.ErrorListMessage, false, null);
            } else {
                priceType.save();
                return new OperationResult<>(ResultMessage.SuccessMessage, true, null);
            }
        }catch (Exception e){

            return  new OperationResult<>(ResultMessage.ErrorListMessage,false,e.getMessage());
        }

    }

    public OperationResult<SparseArray<PriceTypeHeader>> GetPriceTypeCategory() {

        try {

            SparseArray<PriceTypeHeader> priceTypeHeaderList=new SparseArray<>();


            List<PriceType> list=new Select().from(PriceType.class).execute();

            if (list==null) return  new OperationResult<>(ResultMessage.ErrorListMessage,false,null);

            for (int j=0;j< Constants.PriceTypeHeader.length;j++){

                PriceTypeHeader group=new PriceTypeHeader(j,Constants.PriceTypeHeader[j],Constants.PriceTypeHeaderPicture[j]);
                for (PriceType item:list) {
                    if (item.getPriceTypeId()==j){
                        group.priceTypeList.add(item);
                        priceTypeHeaderList.append(j,group);
                    }
                }
            }
            return  new OperationResult<>(ResultMessage.SuccessMessage,true,null,priceTypeHeaderList,null);




        }catch (Exception e){

            return  new OperationResult<>(ResultMessage.ErrorListMessage,false,e.getMessage());
        }

    }


    public OperationResult AddDefaultPriceType(){


        try{
            PriceType priceType=new Select().from(PriceType.class).executeSingle();
            if (priceType!=null)     return  new OperationResult<>(ResultMessage.ErrorMessage,false,null);


            for (int i=0;i<Constants.PriceTypeHeader.length;i++){
                switch (i){

                    case 0: // خرید
                        fillPriceType(Constants.BuyPriceTypeItem,i);
                        break;
                    case 1: // اجاره
                        fillPriceType(Constants.RentPriceTypeItem,i);
                        break;
                    case 2: // ماشین
                        fillPriceType(Constants.CarPriceTypeItem,i);
                        break;
                    case 3: // خوراک
                        fillPriceType(Constants.FoodPriceTypeItem,i);
                        break;
                    case 4: // رفت و آمد
                        fillPriceType(Constants.CommutingPriceTypeItem,i);
                        break;
                    case 5: // سرگرمی
                        fillPriceType(Constants.EntertainmentPriceTypeItem,i);
                        break;
                    case 6: // قبوص
                        fillPriceType(Constants.BillsPriceTypeItem,i);
                        break;
                }
            }


            return  new OperationResult<>(ResultMessage.SuccessMessage,true,null);


        }catch (Exception e){

            return  new OperationResult<>(ResultMessage.ErrorListMessage,false,e.getMessage());
        }

    }

    private void fillPriceType(String[] priceTypeItem, int i) { // 0 خرید

        for (int j=0;j<priceTypeItem.length;j++){

            PriceType priceType=new PriceType();


            priceType.setPriceTypeId(i);
            priceType.setPriceTypeIdName(Constants.PriceTypeHeader[i]);


            priceType.setPriceTypeItemId(j);
            priceType.setPriceTypeItemIdName(priceTypeItem[j]);

            priceType.save();


        }

    }


}
