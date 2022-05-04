package com.samansepahvand.calculateexpensesapp2022.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.samansepahvand.calculateexpensesapp2022.R;
import com.samansepahvand.calculateexpensesapp2022.bussines.domain.Constants;
import com.samansepahvand.calculateexpensesapp2022.bussines.domain.Enumerations;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.InfoMetaModel;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.OperationResult;
import com.samansepahvand.calculateexpensesapp2022.bussines.repository.InfoRepository;
import com.samansepahvand.calculateexpensesapp2022.infrastructure.Utility;
import com.samansepahvand.calculateexpensesapp2022.ui.adapter.ListSameInvoicesAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailInvoicesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailInvoicesFragment extends Fragment implements View.OnClickListener, ListSameInvoicesAdapter.IGetSameInfoMeta {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private TextView txtInvoicePrice, txtInvoiceTitle, txtInvoiceFullPriceType, txtInvoiceDate, txtInvoiceListSame;

    private Button btnShare;
    private ImageView imgBack, imgMainLogoPriceType;

    private RecyclerView recyclerView;
     private ListSameInvoicesAdapter adapter;

    private NavController navController;

    private InfoMetaModel infoMetaModel = new InfoMetaModel();


    private ListSameInvoicesAdapter.IGetSameInfoMeta _iGetSameInfoMeta;

    public DetailInvoicesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailInvoicesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailInvoicesFragment newInstance(String param1, String param2) {
        DetailInvoicesFragment fragment = new DetailInvoicesFragment();
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
        return inflater.inflate(R.layout.fragment_detail_invoices, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
    }

    private void initView(View view) {

        navController = Navigation.findNavController(view);

        txtInvoiceTitle = view.findViewById(R.id.txt_title);
        txtInvoiceFullPriceType = view.findViewById(R.id.txt_price_type_full_name);
        txtInvoicePrice = view.findViewById(R.id.txt_value_price);
        txtInvoiceDate = view.findViewById(R.id.txt_create_date);
        btnShare = view.findViewById(R.id.btn_share);
        imgBack = view.findViewById(R.id.img_back);
        imgMainLogoPriceType = view.findViewById(R.id.img_main_logo_price_type);

        txtInvoiceListSame = view.findViewById(R.id.txt_no_same_invoice);

     _iGetSameInfoMeta=(ListSameInvoicesAdapter.IGetSameInfoMeta)this;

        recyclerView = view.findViewById(R.id.recyclerview_same_product);


        initData();

        initRecyclerView();
        imgBack.setOnClickListener(this);
        btnShare.setOnClickListener(this);


    }

    private void initRecyclerView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);


        OperationResult result=
                InfoRepository.getInstance().GetSameInvoices(infoMetaModel);

        if (result.IsSuccess) {
            recyclerView.setVisibility(View.VISIBLE);
            txtInvoiceListSame.setVisibility(View.GONE);

            adapter=new ListSameInvoicesAdapter(getContext(),result.Items,_iGetSameInfoMeta);
            recyclerView.setAdapter(adapter);
        }else{
            recyclerView.setVisibility(View.GONE);
            txtInvoiceListSame.setVisibility(View.VISIBLE);

        }

    }

    private void initData() {

        Bundle bundle = getArguments();
        if (bundle != null) {

            infoMetaModel = (InfoMetaModel) bundle.getSerializable("InfoMetaModel");

            txtInvoiceTitle.setText(infoMetaModel.getTitle());

            txtInvoiceDate.setText(infoMetaModel.getFarsiDate());

            txtInvoiceFullPriceType.setText(infoMetaModel.getPriceTypeName() + " » " + infoMetaModel.getPriceTypeItemName());

            imgMainLogoPriceType.setImageResource(Constants.PriceTypeHeaderPicture[infoMetaModel.getPriceTypeId()]);


            String str = "<font color=red><b>" +
                    Utility.SplitDigits(infoMetaModel.getPrice()) +
                    "</b></font>"
                    + "  ریال ";
            Spanned strHtml = Html.fromHtml(str);

            txtInvoicePrice.setText(strHtml);


            txtInvoiceListSame.setCompoundDrawablesWithIntrinsicBounds
                    (0, Constants.PriceTypeHeaderPicture[infoMetaModel.getPriceTypeId()], 0, 0);

            Spanned strHtmlPriceType = Html.fromHtml("فاکتور مشابه ای در دسته بندی " +
                    "<font color=red><b>" + infoMetaModel.getPriceTypeName()
                    + "</b></font>" + " وجود ندارد  ");

            txtInvoiceListSame.setText(strHtmlPriceType);

        }


    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.img_back:
                navController.popBackStack();
                break;
            case R.id.btn_share:
                break;
        }
    }

    @Override
    public void GetSameInfoMeta(InfoMetaModel infoMetaModel, int InvoiceShowType) {



        switch (InvoiceShowType){
            case Enumerations.InvoiceShowType.Single:

                DetailInvoicesFragmentDirections.ActionDetailInvoicesFragmentSelf
                        action=DetailInvoicesFragmentDirections.actionDetailInvoicesFragmentSelf(infoMetaModel);
                navController.navigate(action);

                break;
            case Enumerations.InvoiceShowType.All:
                navController.navigate(R.id.action_detailInvoicesFragment_to_listInvoicesFragment);
                break;

        }
    }
}