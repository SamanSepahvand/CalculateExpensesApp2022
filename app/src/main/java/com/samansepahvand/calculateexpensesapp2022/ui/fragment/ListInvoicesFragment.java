package com.samansepahvand.calculateexpensesapp2022.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.samansepahvand.calculateexpensesapp2022.R;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.DateModel;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.OperationResult;
import com.samansepahvand.calculateexpensesapp2022.bussines.repository.InfoRepository;
import com.samansepahvand.calculateexpensesapp2022.infrastructure.Utility;
import com.samansepahvand.calculateexpensesapp2022.ui.adapter.ListInvoicesAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListInvoicesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListInvoicesFragment extends Fragment implements SearchView.OnQueryTextListener, View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListInvoicesFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private TextView txtInvoiceCount,txtFormDateToDate,txtTotalPrice;
    private SearchView searchView;
    private NavController navController;
    private ImageView imgBack;
    private DateModel dateModel;
private ListInvoicesAdapter adapter;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListInvoicesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListInvoicesFragment newInstance(String param1, String param2) {
        ListInvoicesFragment fragment = new ListInvoicesFragment();
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
        return inflater.inflate(R.layout.fragment_list_invoices, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intiView(view);

    }

    private void intiView(View view) {

        navController= Navigation.findNavController(view);
        imgBack=view.findViewById(R.id.img_back);
        searchView=view.findViewById(R.id.search_view);
        txtFormDateToDate=view.findViewById(R.id.txt_from_date_to_date);
        txtInvoiceCount=view.findViewById(R.id.txt_count);
        txtTotalPrice=view.findViewById(R.id.txt_total_price);


        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        intiData();

        searchView.setOnQueryTextListener(this);
        txtFormDateToDate.setOnClickListener(this);
        imgBack.setOnClickListener(this);





    }

    private void intiData() {


        dateModel= Utility.GetFirstLastDayMonthFarsi();


        OperationResult result= InfoRepository.getInstance().GetInfo(dateModel);

        if (result.IsSuccess){
            txtFormDateToDate.setText(DateModelInfoDesc(dateModel));
            txtInvoiceCount.setText("تعداد : "+(result.Items.size()));
            txtTotalPrice.setText(Utility.SplitDigits(Integer.parseInt(result.Message)));
            adapter=new ListInvoicesAdapter(getContext(),result.Items);
            recyclerView.setAdapter(adapter);

        }else{
            Toast.makeText(getContext(), ""+result.Message, Toast.LENGTH_SHORT).show();
        }


    }

    private String  DateModelInfoDesc(DateModel dataModel) {
        return "فاکتور های " + dataModel.getFromDateFarsi()+"  تا "+dataModel.getToDateFarsi();

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.img_back:
                navController.popBackStack();
                break;

            case R.id.txt_from_date_to_date:

                /// make dialog for choose from date to date list invoices


                break;

        }
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }



}