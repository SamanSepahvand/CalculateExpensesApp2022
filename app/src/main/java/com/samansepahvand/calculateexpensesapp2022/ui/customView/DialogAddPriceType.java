package com.samansepahvand.calculateexpensesapp2022.ui.customView;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputLayout;
import com.samansepahvand.calculateexpensesapp2022.R;
import com.samansepahvand.calculateexpensesapp2022.bussines.domain.Constants;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.OperationResult;
import com.samansepahvand.calculateexpensesapp2022.bussines.repository.PriceTypeRepository;
import com.samansepahvand.calculateexpensesapp2022.db.PriceType;
import com.samansepahvand.calculateexpensesapp2022.ui.MainApplication;

public class DialogAddPriceType extends Dialog implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    private Context mContext;
    private Button btnConfirm, btnCancel;
    private ImageView imgCloseDialog;
    private Spinner spinner;
    private EditText edtPriceTypeItem;
    private TextInputLayout textInputLayout;
    private String tempPriceTypeIdHeader;


    public DialogAddPriceType(@NonNull Context context, boolean hasConfirm, boolean hasCancel) {
        super(context);
        this.mContext = context;
        setContentView(R.layout.dialog_add_price_type);
        this.getWindow().getAttributes().windowAnimations = R.style.AlertDialogAnimation;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initView(hasConfirm, hasCancel);
        // initValue(alertDialogModel);

    }


    private void initView(final boolean hasConfirm, final boolean hasCancel) {


        btnConfirm = findViewById(R.id.btn_dialog_confirm);
        btnCancel = findViewById(R.id.btn_dialog_cancel);
        imgCloseDialog = findViewById(R.id.img_dialog_close);
        edtPriceTypeItem = findViewById(R.id.edt_price_type_name);

        textInputLayout = findViewById(R.id.filledTextField_edt_price_type_name);

        textInputLayout.setTypeface(Constants.CustomStyleElement());

        spinner = findViewById(R.id.spinner);
        initSpinner();

        edtPriceTypeItem.setVisibility(View.GONE);
        spinner.setVisibility(View.GONE);

        btnConfirm.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                btnConfirm.startAnimation(MainApplication.SetAnimation("FadeIn"));
                edtPriceTypeItem.startAnimation(MainApplication.SetAnimation("FadeIn"));
                spinner.startAnimation(MainApplication.SetAnimation("FadeIn"));


                if (hasCancel) btnCancel.setVisibility(View.VISIBLE);
                if (hasConfirm) btnConfirm.setVisibility(View.VISIBLE);


                edtPriceTypeItem.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);


            }
        }, Constants.DelayMoreFast);

        imgCloseDialog.startAnimation(MainApplication.SetAnimation("Rotate"));

        btnConfirm.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        imgCloseDialog.setOnClickListener(this);

        spinner.setOnItemSelectedListener(this);
    }

    private void initSpinner() {

        ArrayAdapter aa = new ArrayAdapter(mContext, android.R.layout.simple_spinner_item, Constants.PriceTypeHeader);
        // aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.img_dialog_close:
                animOpenCloseDialog();
                break;
            case R.id.btn_dialog_cancel:
                animOpenCloseDialog();
                dismiss();
                break;
            case R.id.btn_dialog_confirm:
//                if (acceptListener != null)
//                    acceptListener.accept();

                String tempPriceType = edtPriceTypeItem.getText().toString();

                if (!tempPriceType.equals("") || tempPriceType != null) {
                    if (!tempPriceTypeIdHeader.equals("") || tempPriceTypeIdHeader != null) {

                        PriceType priceType = new PriceType();

                        priceType.setPriceTypeId(Integer.parseInt(tempPriceTypeIdHeader)); //0
                        priceType.setPriceTypeIdName(Constants.PriceTypeHeader[Integer.parseInt(tempPriceTypeIdHeader)]); //خرید

                        priceType.setPriceTypeItemIdName(tempPriceType);



                        OperationResult<PriceType> result = PriceTypeRepository.getInstance().GetLastIdPriceType(tempPriceTypeIdHeader);

                        if (result.IsSuccess) {
                            priceType.setPriceTypeItemId(result.Item.getPriceTypeItemId() + 1);

                            OperationResult<PriceType> resultAddNewPriceType = PriceTypeRepository.getInstance().AddCustomPriceType(priceType);

                            Toast.makeText(getContext(),resultAddNewPriceType.Message , Toast.LENGTH_SHORT).show();


                            // if (resultAddNewPriceType.IsSuccess) {
                                // DialogSuccess(result.Message, getContext());
                           // }  else {
                               // DialogFailed(result.Message, getContext());
                         //   }
                        } else {
                            Toast.makeText(getContext(), "لطفا عنوان دسته بندی خود را انتخاب کنید", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(getContext(), "لطفا نوع دسته بندی خود را انتخاب کنید", Toast.LENGTH_SHORT).show();

                    }

                }


                dismiss();
                break;


        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void animOpenCloseDialog() {
        imgCloseDialog.startAnimation(MainApplication.SetAnimation("Rotate"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, Constants.DelayTimeDialogAnimation);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        tempPriceTypeIdHeader = position + ""; //0  خرید
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
