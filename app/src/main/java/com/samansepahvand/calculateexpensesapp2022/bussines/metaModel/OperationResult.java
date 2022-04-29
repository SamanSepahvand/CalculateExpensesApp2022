package com.samansepahvand.calculateexpensesapp2022.bussines.metaModel;

import java.util.List;

public class OperationResult<T> {

    public String Message;

    public boolean IsSuccess;

    public String Exception;

    public T Item;

    public List<T> Items;


    public OperationResult(String message, boolean isSuccess, String exception) {
        Message = message;
        IsSuccess = isSuccess;
        Exception = exception;
    }


    public OperationResult(String message, boolean isSuccess, String exception, T item, List<T> items) {
        Message = message;
        IsSuccess = isSuccess;
        Exception = exception;
        Item = item;
        Items = items;
    }



}
