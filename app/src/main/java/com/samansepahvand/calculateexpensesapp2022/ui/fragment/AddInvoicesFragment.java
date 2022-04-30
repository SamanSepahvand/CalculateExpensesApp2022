package com.samansepahvand.calculateexpensesapp2022.ui.fragment;

import static android.content.ContentValues.TAG;
import static com.samansepahvand.calculateexpensesapp2022.infrastructure.Utility.ChangeIconAddExpenses;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.samansepahvand.calculateexpensesapp2022.R;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.OperationResult;
import com.samansepahvand.calculateexpensesapp2022.bussines.repository.InfoRepository;
import com.samansepahvand.calculateexpensesapp2022.db.Info;
import com.samansepahvand.calculateexpensesapp2022.db.PriceType;
import com.samansepahvand.calculateexpensesapp2022.infrastructure.Utility;
import com.samansepahvand.calculateexpensesapp2022.ui.customView.DialogFragmentPriceType;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.api.PersianPickerDate;
import ir.hamsaa.persiandatepicker.api.PersianPickerListener;
import ir.hamsaa.persiandatepicker.util.PersianCalendarUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddInvoicesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddInvoicesFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener , DialogFragmentPriceType.IPriceTypeDialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /// component and variable


    private EditText edtPrice, edtTitle;
    private TextView txtChooseDate, txtShowInvoice, txtPriceType, txtPriceTypeResult;
    private NavController mNavController;
    private StringBuilder spPrice = new StringBuilder();
    private String titleLength, priceLength;
    private PriceType priceType = new PriceType();
    private int actionDate = 0; // 202221015
    private ImageView imgInsertBack;
    private String current = "";
    private String invoicePrice, invoiceTitle, engDate, farsiDate, chooseDate = null;
    private PriceType globalPriceType;

    //edit


    public AddInvoicesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddInvoicesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddInvoicesFragment newInstance(String param1, String param2) {
        AddInvoicesFragment fragment = new AddInvoicesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_invoices, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intView(view);
    }

    private void intView(View view) {

        mNavController = Navigation.findNavController(view);


        imgInsertBack = view.findViewById(R.id.img_back);
        edtPrice = view.findViewById(R.id.edt_price);
        edtTitle = view.findViewById(R.id.edt_name);


        txtPriceType = view.findViewById(R.id.txt_price_type);
        txtPriceTypeResult = view.findViewById(R.id.txt_price_type_result);
        txtShowInvoice = view.findViewById(R.id.txt_invoice_show);
        txtChooseDate = view.findViewById(R.id.txt_date_chosse);

        edtOnline();


        imgInsertBack.setOnClickListener(this);
        txtChooseDate.setOnClickListener(this);
        txtPriceType.setOnClickListener(this);
        txtPriceTypeResult.setOnLongClickListener(this);


    }

    private void edtOnline() {


        edtPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                OnlineInvoices();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                titleLength = editable.toString();
                StateLive(titleLength, priceLength);
                try {

                    if (!editable.toString().equals(current)) {

                        edtPrice.removeTextChangedListener(this);
                        String cleanString = editable.toString().replaceAll("[$,.]", "");
                        double parsed = Double.parseDouble(cleanString.equals("") ? "0" : cleanString);
                        String formatted = NumberFormat.getNumberInstance().format(parsed);
                        current = formatted;
                        edtPrice.setText(formatted);
                        edtPrice.setSelection(formatted.length());
                        edtPrice.addTextChangedListener(this);
                        invoicePrice = formatted;


                    }
                } catch (Exception e) {

                }
            }
        });

        edtTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                priceLength = editable.toString();
                StateLive(titleLength, priceLength);
                invoiceTitle = editable.toString();

            }
        });


    }


    private void StateLive(String titleLength, String priceLength) {

        if (priceLength == null || priceLength.equals("") || titleLength == null || titleLength.equals("")) {
            imgInsertBack.setImageResource(ChangeIconAddExpenses(0)); // no invoice
        } else imgInsertBack.setImageResource(ChangeIconAddExpenses(1));  // have invoice
    }


    private void OnlineInvoices() {
        Spanned strHtml = Html.fromHtml(" شما در تاریخ  " + "<font color='red'>"
                + engDate + "</font>" + " مبلغ  " + "<font color='red'>" + invoicePrice
                + "</font>" + " بابت هزینه  " + "<font color='red'>" + invoiceTitle + "</font>" + " پرداخت کرده اید. ");

        txtShowInvoice.setText(strHtml);



    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.img_back:
                String tempTitle = edtTitle.getText().toString();
                String tempPrice = edtPrice.getText().toString();

                if (validation(tempTitle, tempPrice)) {
                    AddNewPrice();
                } else {
                    Toast.makeText(getContext(), "Error Null !", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.txt_date_chosse:
                PersianDataPicker();
                break;
            case R.id.txt_price_type:
           OpenPriceTypeDialog();
                break;
        }

    }

    private void AddNewPrice() {


        Info info=new Info();


        info.setTitle(edtTitle.getText().toString());
        info.setPrice(Utility.GetPrice(edtPrice.getText().toString()));

        info.setEngDate(GetEngDate());
        info.setFarsiDate(GetFarsiDate());
        info.setActionDate(GetActionDate());

        if (globalPriceType==null){
            Toast.makeText(getContext(), "لطفا یک دسته بندی را انتخاب کنید.", Toast.LENGTH_SHORT).show();
        }else{
            info.setPriceTypeId(globalPriceType.getPriceTypeId());
            info.setPriceTypeItemId(globalPriceType.getPriceTypeItemId());
        }
        OperationResult result= InfoRepository.getInstance().AddPrice(info);
        Toast.makeText(getContext(), result.Message, Toast.LENGTH_SHORT).show();


      //  mNavController.navigate();
    }

    private void OpenPriceTypeDialog() {


        DialogFragmentPriceType dialog=new DialogFragmentPriceType();
        dialog.setTargetFragment(AddInvoicesFragment.this,1);
        dialog.show(getFragmentManager(),getString(R.string.app_name));

    }

    private void PersianDataPicker() {


        String toDay = Utility.getIranianDateInt();

        String[] setToday = toDay.split("/");


        PersianDatePickerDialog picker = new PersianDatePickerDialog(getActivity())
                .setPositiveButtonString("باشه")
                .setNegativeButton("بیخیال")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1300)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setMaxMonth(PersianDatePickerDialog.THIS_MONTH)
                .setMaxDay(PersianDatePickerDialog.THIS_DAY)
                .setInitDate(Integer.parseInt(setToday[0]),
                        Integer.parseInt(setToday[1]),
                        Integer.parseInt(setToday[2]))
                .setActionTextColor(Color.GRAY)
                //.setTypeFace(typeface)
                .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
                .setShowInBottomSheet(true)
                .setListener(new PersianPickerListener() {
                    @Override
                    public void onDateSelected(@NotNull PersianPickerDate persianPickerDate) {
                        Log.d(TAG, "onDateSelected: " + persianPickerDate.getTimestamp());//675930448000
                        Log.d(TAG, "onDateSelected: " + persianPickerDate.getGregorianDate());//Mon Jun 03 10:57:28 GMT+04:30 1991
                        Log.d(TAG, "onDateSelected: " + persianPickerDate.getPersianLongDate());// دوشنبه  13  خرداد  1370
                        Log.d(TAG, "onDateSelected: " + persianPickerDate.getPersianMonthName());//خرداد
                        Log.d(TAG, "onDateSelected: " + PersianCalendarUtils.isPersianLeapYear(persianPickerDate.getPersianYear()));//true


                        Date selectedDate = persianPickerDate.getGregorianDate();
                        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                        String strDate = dateFormat.format(selectedDate);


                        DateFormat dateFormatDate = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(selectedDate);

                        chooseDate = Utility.getIranianDateCustom(calendar);
                        actionDate = Integer.parseInt(strDate);//20220105
                        txtChooseDate.setText(persianPickerDate.getPersianLongDate());
                        engDate = dateFormatDate.format(selectedDate);
                        OnlineInvoices();

                    }

                    @Override
                    public void onDismissed() {

                    }
                });

        picker.show();
    }

    private boolean validation(String tempTitle, String tempPrice) {
        if ((tempPrice == null || tempPrice.equals("")) || (tempTitle.equals("") || tempTitle == null)) {
            return false;
        }
        return true;
    }


    @Override
    public boolean onLongClick(View view) {

        switch (view.getId()) {
            case R.id.txt_price_type_result:
                removePriceType();
                break;
        }
        return false;
    }

    private void removePriceType() {
        txtPriceTypeResult.setVisibility(View.GONE);
        txtPriceTypeResult.setText("");
    }


    private String GetFarsiDate(){
        if (chooseDate==null) return Utility.getIranianDate();
        else return chooseDate;
    }

    private String GetEngDate(){

        Date date=new Date();
        if (engDate==null){
            return  DateFormat.getDateTimeInstance(DateFormat.DEFAULT,DateFormat.SHORT).format(date);

        }else  return engDate;
    }



    private int GetActionDate(){
        if (actionDate==0) return Utility.GetActionDate();
        else return actionDate;
    }


    @Override
    public void GetPrice(PriceType priceType) {

        Log.e(TAG, "GetPrice: "+priceType );


        if (priceType!=null){

            globalPriceType=new PriceType();
            globalPriceType=priceType;
            fillPriceType(globalPriceType);

        }


    }

    private void fillPriceType(PriceType globalPriceType) {
        txtPriceTypeResult.setVisibility(View.VISIBLE);
   txtPriceTypeResult.setText(globalPriceType.getPriceTypeItemIdName());
    }
}