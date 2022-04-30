package com.samansepahvand.calculateexpensesapp2022.bussines.metaModel;

import com.samansepahvand.calculateexpensesapp2022.db.PriceType;

import java.util.ArrayList;
import java.util.List;

public class PriceTypeHeader {

    public int priceTypeId;
    public String priceTypeName;
    public int pic;
    public List<PriceType> priceTypeList =new ArrayList<>();


    public PriceTypeHeader(int priceTypeId, String priceTypeName, int pic) {
        this.priceTypeId = priceTypeId;
        this.priceTypeName = priceTypeName;
        this.pic = pic;

    }
}
