package com.samansepahvand.calculateexpensesapp2022.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.samansepahvand.calculateexpensesapp2022.R;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.DetailMainInfo;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.OperationResult;
import com.samansepahvand.calculateexpensesapp2022.bussines.repository.InfoRepository;
import com.samansepahvand.calculateexpensesapp2022.bussines.repository.PriceTypeRepository;
import com.samansepahvand.calculateexpensesapp2022.infrastructure.Utility;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment  implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private NavController mNavController;

    private CardView cvInvoiceList,cvAddInvoice;
    private ImageView imgInvoiceListMore,imgAddInvoicesMore;



    private TextView txtHeaderTotalPrice,txtHeaderCountInvoices,
    txtRightCurrentDate,txtLeftMaxPrice,txtLeftLastInvoicePrice,txtLeftInvoiceCount;









    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

    }


    private void initView(View view){
        mNavController= Navigation.findNavController(view);

        PriceTypeRepository.getInstance().AddDefaultPriceType();

        imgAddInvoicesMore=view.findViewById(R.id.img_add_invoices_more);
        imgInvoiceListMore=view.findViewById(R.id.img_list_invoices_more);


        cvAddInvoice=view.findViewById(R.id.cv_add_invoices);
        cvInvoiceList=view.findViewById(R.id.cv_show_invoices);


        //header
        txtHeaderCountInvoices =view.findViewById(R.id.txt_header_count);
        txtHeaderTotalPrice= view.findViewById(R.id.txt_header_total_price);


        //left
        txtLeftInvoiceCount = view.findViewById(R.id.txt_invoice_left_count_total);
        txtLeftMaxPrice = view.findViewById(R.id.txt_left_max_invoice_price);
        txtLeftLastInvoicePrice = view.findViewById(R.id.txt_left_last_invoice_pricce);


        //right
        txtRightCurrentDate = view.findViewById(R.id.txt_right_current_date);




        //action Component
        cvInvoiceList.setOnClickListener(this);
        cvAddInvoice.setOnClickListener(this);
        imgAddInvoicesMore.setOnClickListener(this);
        imgInvoiceListMore.setOnClickListener(this);



        setHeaderData();

    }

    private void setHeaderData() {


        OperationResult<DetailMainInfo>
                result= InfoRepository.getInstance().DetailMainInfo();

        if (result.IsSuccess){
            //header
            txtHeaderCountInvoices.setText(result.Item.getHeaderInvoiceCount());
            txtHeaderTotalPrice.setText(result.Item.getHeaderTotalPrice());


            //left
            txtLeftInvoiceCount.setText(result.Item.getLeftInvoiceCount());
            txtLeftMaxPrice.setText(result.Item.getLeftMaxInvoicePrice());
            txtLeftLastInvoicePrice.setText(result.Item.getLeftLastInvoicePrice());


            //right
            txtRightCurrentDate.setText(result.Item.getRightCurrentDate());



        }else{

            //header
            txtHeaderCountInvoices.setText("تعداد:0");
            txtHeaderTotalPrice.setText("0");


            //left
            txtLeftInvoiceCount.setText("");
            txtLeftMaxPrice.setText("");
            txtLeftLastInvoicePrice.setText("");


            //right
            txtRightCurrentDate.setText("");


        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.img_add_invoices_more:

                Utility.RotateImage(imgAddInvoicesMore);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mNavController.navigate(R.id.action_mainFragment_to_addInvoicesFragment);

                    }
                },500);

                break;
            case R.id.img_list_invoices_more:
                Utility.RotateImage(imgInvoiceListMore);
                 new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mNavController.navigate(R.id.action_mainFragment_to_listInvoicesFragment);

                    }
                },500);

                break;

            case R.id.cv_show_invoices:
                mNavController.navigate(R.id.action_mainFragment_to_listInvoicesFragment);
                break;

            case R.id.cv_add_invoices:
                mNavController.navigate(R.id.action_mainFragment_to_addInvoicesFragment);
                break;



        }
    }
}