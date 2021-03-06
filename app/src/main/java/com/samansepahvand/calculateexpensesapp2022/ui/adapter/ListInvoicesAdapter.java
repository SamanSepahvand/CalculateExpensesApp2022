package com.samansepahvand.calculateexpensesapp2022.ui.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samansepahvand.calculateexpensesapp2022.R;
import com.samansepahvand.calculateexpensesapp2022.bussines.domain.Enumerations;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.InfoMetaModel;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.OperationResult;
import com.samansepahvand.calculateexpensesapp2022.bussines.repository.InfoRepository;
import com.samansepahvand.calculateexpensesapp2022.infrastructure.Utility;

import java.util.List;

public class ListInvoicesAdapter extends RecyclerView.Adapter<ListInvoicesAdapter.MyViewHolder> {


    private Context context;
    private List<InfoMetaModel> mDataList;
    private IGetMetaInfo _iGetMetaInfo;


    public ListInvoicesAdapter(Context context, List<InfoMetaModel> mDataList, IGetMetaInfo iGetMetaInfo) {
        this.context = context;
        this.mDataList = mDataList;
        this._iGetMetaInfo = iGetMetaInfo;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_list_invoices, parent, false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        InfoMetaModel item = mDataList.get(position);

        holder.txtRow.setText((position + 1) + "");

        holder.txtTitle.setText(item.getTitle());

        holder.txtDate.setText("تاریخ ثبت:" + item.getFarsiDate());

        holder.txtPriceType.setText(item.getPriceTypeItemName());

        String str = "<font color=red><b>" +
                Utility.SplitDigits(item.getPrice()) +
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
    public void Update(List<InfoMetaModel> newList) {
        setData(newList);
        notifyDataSetChanged();
    }
    private void setData(List<InfoMetaModel> newData){

        mDataList.clear();
        if (newData!=null)mDataList.addAll(newData);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtTitle, txtPrice, txtDate, txtRow, txtPriceType;
        private ImageView imgShowInvoices, imgEdit, imgDelete;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            initView(itemView);
        }

        private void initView(View itemView) {

            imgEdit = itemView.findViewById(R.id.img_edit);
            imgDelete = itemView.findViewById(R.id.img_delete);
            imgShowInvoices = itemView.findViewById(R.id.img_show_invoices);

            txtDate = itemView.findViewById(R.id.item_txt_date);
            txtTitle = itemView.findViewById(R.id.item_txt_title);
            txtPrice = itemView.findViewById(R.id.item_txt_price);
            txtRow = itemView.findViewById(R.id.item_txt_row);
            txtPriceType = itemView.findViewById(R.id.txt_price_type);


            imgDelete.setOnClickListener(this);
            imgEdit.setOnClickListener(this);
            imgShowInvoices.setOnClickListener(this);


        }


        @Override
        public void onClick(View view) {


            switch (view.getId()) {

                case R.id.img_edit:
                    _iGetMetaInfo.GetMetaInfo(mDataList.get(getAdapterPosition()), Enumerations.InvoiceActionType.UPDATE);

                    break;
                case R.id.img_delete:

                    OperationResult result =
                            InfoRepository.getInstance().GetInfoByMeta(mDataList.get(getAdapterPosition()), Enumerations.InvoiceActionType.DELETE);

                    if (result.IsSuccess) {
                        _iGetMetaInfo.GetMetaInfo(null, Enumerations.InvoiceActionType.DELETE);
                        mDataList.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyDataSetChanged();

                    Utility.DialogSuccess(result.Message,context);


                    }else{
                        Utility.DialogFailed(result.Message,context);
                    }




                    break;

                case R.id.img_show_invoices:

                    _iGetMetaInfo.GetMetaInfo(mDataList.get(getAdapterPosition()), Enumerations.InvoiceActionType.SHOW);

                    break;
            }
        }
    }

    public interface IGetMetaInfo {

        void GetMetaInfo(InfoMetaModel metaModel, int invoiceActionType);
    }


}
