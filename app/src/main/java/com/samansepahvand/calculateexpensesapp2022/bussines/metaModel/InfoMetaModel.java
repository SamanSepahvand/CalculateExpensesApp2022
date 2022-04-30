package com.samansepahvand.calculateexpensesapp2022.bussines.metaModel;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.samansepahvand.calculateexpensesapp2022.db.Info;
import com.samansepahvand.calculateexpensesapp2022.db.PriceType;

import java.io.Serializable;

public class InfoMetaModel extends Model implements Serializable {





    @Column(name = PriceType.PRICE_TYPE_NAME)
    String  priceTypeName;

    @Column(name = PriceType.PRICE_TYPE_ITEM_ID_NAME)
    String  priceTypeItemName;

    @Column(name =  PriceType.PRICE_TYPE_ID)
    int  priceTypeId;

    @Column(name =  PriceType.PRICE_TYPE_ITEM_ID)
    int  priceTypeItemId;



    @Column(name = Info.TITLE)
    String  title;

    @Column(name = Info.PRICE)
    int price;


    @Column(name = Info.FARSI_DATE)
    String  farsiDate;

    @Column(name = Info.ENG_DATE)
    String  englishDate;




    public int getPriceTypeId() {
        return priceTypeId;
    }

    public void setPriceTypeId(int priceTypeId) {
        this.priceTypeId = priceTypeId;
    }

    public int getPriceTypeItemId() {
        return priceTypeItemId;
    }

    public void setPriceTypeItemId(int priceTypeItemId) {
        this.priceTypeItemId = priceTypeItemId;
    }

    public String getPriceTypeName() {
        return priceTypeName;
    }

    public void setPriceTypeName(String priceTypeName) {
        this.priceTypeName = priceTypeName;
    }

    public String getPriceTypeItemName() {
        return priceTypeItemName;
    }

    public void setPriceTypeItemName(String priceTypeItemName) {
        this.priceTypeItemName = priceTypeItemName;
    }


    public String getFarsiDate() {
        return farsiDate;
    }

    public void setFarsiDate(String farsiDate) {
        this.farsiDate = farsiDate;
    }

    public String getEnglishDate() {
        return englishDate;
    }

    public void setEnglishDate(String englishDate) {
        this.englishDate = englishDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



}
