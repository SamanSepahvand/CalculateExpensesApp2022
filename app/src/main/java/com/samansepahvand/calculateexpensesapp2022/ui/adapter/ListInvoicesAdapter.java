package com.samansepahvand.calculateexpensesapp2022.ui.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samansepahvand.calculateexpensesapp2022.R;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.InfoMetaModel;
import com.samansepahvand.calculateexpensesapp2022.infrastructure.Utility;

import java.util.List;

public class ListInvoicesAdapter extends RecyclerView.Adapter<ListInvoicesAdapter.MyViewHolder> {


    private Context context;
    private List<InfoMetaModel> mDataList;


    public ListInvoicesAdapter(Context context, List<InfoMetaModel> mDataList) {
        this.context = context;
        this.mDataList = mDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_list_invoices,parent,false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        InfoMetaModel item=mDataList.get(position);

        holder.txtRow.setText((position+1)+"");

        holder.txtTitle.setText(item.getTitle());

        holder.txtDate.setText("تاریخ ثبت:"+item.getFarsiDate());

        holder.txtPriceType.setText(item.getPriceTypeItemName());

        String str = "<font color=red><b>" +
                Utility.splitDigits(item.getPrice()) +
                "</b></font>"
                + "  ریال ";
        Spanned strHtml = Html.fromHtml(str);

        holder.txtPrice.setText(strHtml);



    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    // update  list





    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTitle, txtPrice, txtDate, txtRow,txtPriceType;
        private ImageView imgShowInvoices,imgEdit,imgDelete;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            initView(itemView);
        }

        private void initView(View itemView) {

            imgEdit = itemView.findViewById(R.id.img_edit);
            imgDelete = itemView.findViewById(R.id.img_delete);
            imgShowInvoices=itemView.findViewById(R.id.img_show_invoices);

            txtDate = itemView.findViewById(R.id.item_txt_date);
            txtTitle = itemView.findViewById(R.id.item_txt_title);
            txtPrice = itemView.findViewById(R.id.item_txt_price);
            txtRow = itemView.findViewById(R.id.item_txt_row);
            txtPriceType =itemView.findViewById(R.id.txt_price_type);

        }
    }


}
