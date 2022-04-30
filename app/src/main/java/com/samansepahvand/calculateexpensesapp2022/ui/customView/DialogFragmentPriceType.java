package com.samansepahvand.calculateexpensesapp2022.ui.customView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.samansepahvand.calculateexpensesapp2022.R;
import com.samansepahvand.calculateexpensesapp2022.bussines.domain.Constants;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.OperationResult;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.PriceTypeHeader;
import com.samansepahvand.calculateexpensesapp2022.bussines.repository.PriceTypeRepository;
import com.samansepahvand.calculateexpensesapp2022.db.PriceType;
import com.samansepahvand.calculateexpensesapp2022.ui.MainApplication;
import com.samansepahvand.calculateexpensesapp2022.ui.adapter.MyExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

public class DialogFragmentPriceType  extends DialogFragment implements View.OnClickListener, MyExpandableListAdapter.IGetPriceType {


    private Context context= getContext();
    private Button btnConfirm,btnCancel;
    private ImageView imgCloseDialog;
    private ConstraintLayout clPriceTypeFilterShow;
    private View view;
    private List<PriceType> priceTypeList=new ArrayList<>();
    private ExpandableListView expandableListView;


    private IPriceTypeDialogFragment iPriceTypeDialogFragment;
    private MyExpandableListAdapter.IGetPriceType iGetPriceType;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.dialog_price_type,container,false);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initView();
        return view;

    }

    private void initView() {


        expandableListView=view.findViewById(R.id.listView);

        imgCloseDialog=view.findViewById(R.id.img_dialog_close);
        clPriceTypeFilterShow=view.findViewById(R.id.cl_pricetype_filter_show);
        iGetPriceType=(MyExpandableListAdapter.IGetPriceType)this;



        OpenDialogChosePriceType();

        imgCloseDialog.setAnimation(MainApplication.SetAnimation("Rotate"));

        imgCloseDialog.setOnClickListener(this);
        clPriceTypeFilterShow.setOnClickListener(this);



    }

    private void OpenDialogChosePriceType() {


        OperationResult<SparseArray<PriceTypeHeader>> result=
                PriceTypeRepository.getInstance().GetPriceTypeCategory();

        if (result.IsSuccess){
            MyExpandableListAdapter adapter=new MyExpandableListAdapter(getActivity(),result.Item,iGetPriceType);
            expandableListView.setAdapter(adapter);

        }
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_dialog_close:
                animOpenCloseDialog();
                break;
            case R.id.cl_pricetype_filter_show:

                DialogAddPriceType dialogAddPriceType =new DialogAddPriceType(getContext(),true,true);
                dialogAddPriceType.setCancelable(true);
                dialogAddPriceType.show();
                dismiss();

                break;


        }
        dismiss();
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
    public void GetPriceType(PriceType priceType) {
        iPriceTypeDialogFragment.GetPrice(priceType);
        dismiss();

    }

    public interface IPriceTypeDialogFragment{
        void GetPrice (PriceType priceType);
    }


    @Override
    public void onAttach(@NonNull Context context) {
     try{
         iPriceTypeDialogFragment=(IPriceTypeDialogFragment) getTargetFragment();
     }catch (ClassCastException e){
         e.printStackTrace();
     }
     super.onAttach(context);
    }
}
