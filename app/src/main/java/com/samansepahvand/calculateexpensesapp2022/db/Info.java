package com.samansepahvand.calculateexpensesapp2022.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name=Info.TABLE_NAME)
public class Info  extends Model {

public static final String  TABLE_NAME="Info";

    public static final String TITLE="title";
    public static final String PRICE="price";
    public static final String PRICE_TYPE_ID="priceTypeId";
    public static final String PRICE_TYPE_ITEM_ID="priceTypeItemId";
    public static final String ACTION_DATE="actionDate"; //20220212
    public static final String FARSI_DATE="farsiDate";
    public static final String ENG_DATE="endDate";




    @Column(name = ENG_DATE)
    private String engDate;

    @Column(name = FARSI_DATE)
    private String farsiDate;

    @Column(name = ACTION_DATE)
    private int actionDate;

    @Column(name = PRICE_TYPE_ITEM_ID)
    private int priceTypeItemId;

    @Column(name = PRICE_TYPE_ID)
    private int priceTypeId;


    @Column(name = PRICE)
    private int price;


    @Column(name = TITLE)
    private String title;


    public String getEngDate() {
        return engDate;
    }

    public void setEngDate(String engDate) {
        this.engDate = engDate;
    }

    public String getFarsiDate() {
        return farsiDate;
    }

    public void setFarsiDate(String farsiDate) {
        this.farsiDate = farsiDate;
    }

    public int getActionDate() {
        return actionDate;
    }

    public void setActionDate(int actionDate) {
        this.actionDate = actionDate;
    }

    public int getPriceTypeItemId() {
        return priceTypeItemId;
    }

    public void setPriceTypeItemId(int priceTypeItemId) {
        this.priceTypeItemId = priceTypeItemId;
    }

    public int getPriceTypeId() {
        return priceTypeId;
    }

    public void setPriceTypeId(int priceTypeId) {
        this.priceTypeId = priceTypeId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
