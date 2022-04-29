package com.samansepahvand.calculateexpensesapp2022.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


@Table(name=PriceType.TABLE_NAME)
public class PriceType extends Model {

    public static final String  TABLE_NAME="PriceType";


    public static final String PRICE_TYPE_ID="priceTypeId";
    public static final String PRICE_TYPE_NAME="priceTypeIdName";


    public static final String PRICE_TYPE_ITEM_ID="priceTypeItemId";
    public static final String PRICE_TYPE_ITEM_ID_NAME="priceTypeItemIdName";

    @Column(name = PRICE_TYPE_ID)
    private int priceTypeId;

    @Column(name = PRICE_TYPE_NAME)
    private String priceTypeIdName;

    @Column(name = PRICE_TYPE_ITEM_ID)
    private int priceTypeItemId;

    @Column(name = PRICE_TYPE_ITEM_ID_NAME)
    private String priceTypeItemIdName;

    //alt+insert


    public int getPriceTypeId() {
        return priceTypeId;
    }

    public void setPriceTypeId(int priceTypeId) {
        this.priceTypeId = priceTypeId;
    }

    public String getPriceTypeIdName() {
        return priceTypeIdName;
    }

    public void setPriceTypeIdName(String priceTypeIdName) {
        this.priceTypeIdName = priceTypeIdName;
    }

    public int getPriceTypeItemId() {
        return priceTypeItemId;
    }

    public void setPriceTypeItemId(int priceTypeItemId) {
        this.priceTypeItemId = priceTypeItemId;
    }

    public String getPriceTypeItemIdName() {
        return priceTypeItemIdName;
    }

    public void setPriceTypeItemIdName(String priceTypeItemIdName) {
        this.priceTypeItemIdName = priceTypeItemIdName;
    }
}
