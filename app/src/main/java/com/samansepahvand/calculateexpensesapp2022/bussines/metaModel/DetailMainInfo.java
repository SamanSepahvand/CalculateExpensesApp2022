package com.samansepahvand.calculateexpensesapp2022.bussines.metaModel;

public class DetailMainInfo {


    private String HeaderTotalPrice;

    private String HeaderInvoiceCount;

    private String LeftMaxInvoicePrice;

    private String LeftInvoiceCount;

    private String LeftLastInvoicePrice;

    private String RightCurrentDate;

    public DetailMainInfo() {
    }


    public String getHeaderTotalPrice() {
        return HeaderTotalPrice;
    }

    public void setHeaderTotalPrice(String headerTotalPrice) {
        HeaderTotalPrice = headerTotalPrice;
    }

    public String getHeaderInvoiceCount() {
        return HeaderInvoiceCount;
    }

    public void setHeaderInvoiceCount(String headerInvoiceCount) {
        HeaderInvoiceCount = headerInvoiceCount;
    }

    public String getLeftMaxInvoicePrice() {
        return LeftMaxInvoicePrice;
    }

    public void setLeftMaxInvoicePrice(String leftMaxInvoicePrice) {
        LeftMaxInvoicePrice = leftMaxInvoicePrice;
    }

    public String getLeftInvoiceCount() {
        return LeftInvoiceCount;
    }

    public void setLeftInvoiceCount(String leftInvoiceCount) {
        LeftInvoiceCount = leftInvoiceCount;
    }

    public String getLeftLastInvoicePrice() {
        return LeftLastInvoicePrice;
    }

    public void setLeftLastInvoicePrice(String leftLastInvoicePrice) {
        LeftLastInvoicePrice = leftLastInvoicePrice;
    }

    public String getRightCurrentDate() {
        return RightCurrentDate;
    }

    public void setRightCurrentDate(String rightCurrentDate) {
        RightCurrentDate = rightCurrentDate;
    }
}
