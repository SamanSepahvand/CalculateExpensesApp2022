package com.samansepahvand.calculateexpensesapp2022.ui.adapter;

import android.app.Activity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.samansepahvand.calculateexpensesapp2022.R;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.PriceTypeHeader;
import com.samansepahvand.calculateexpensesapp2022.db.PriceType;

public class MyExpandableListAdapter  extends BaseExpandableListAdapter {



    public LayoutInflater inflater;
    public SparseArray<PriceTypeHeader> groups;
    public Activity activity;



    public interface IGetPriceType{
        void GetPriceType(PriceType priceType);

    }



    private IGetPriceType _iGetPriceType;


    public MyExpandableListAdapter( Activity activity,SparseArray<PriceTypeHeader> groups,IGetPriceType iGetPriceType) {
        this.inflater = activity.getLayoutInflater();
        this.groups = groups;
        this.activity = activity;
        this._iGetPriceType=iGetPriceType;

    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return groups.get(i).priceTypeList.size();
    }

    @Override
    public Object getGroup(int i) {
        return groups.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return groups.get(i).priceTypeList.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean isExpanded, View convertView, ViewGroup viewGroup) {


        if (convertView==null)convertView=inflater.inflate(R.layout.list_row_group,null);

        PriceTypeHeader group=(PriceTypeHeader) getGroup(i);

        ((CheckedTextView) convertView).setText(group.priceTypeName);
        ((CheckedTextView) convertView).setCompoundDrawablesWithIntrinsicBounds(0,0,group.pic,0);

        ((CheckedTextView) convertView).setChecked(isExpanded);


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View convertView, ViewGroup viewGroup) {

        PriceType children=(PriceType) getChild(groupPosition,childPosition);

        TextView txt=null;
        if (convertView==null)convertView=inflater.inflate(R.layout.list_row_child,null);
        txt=convertView.findViewById(R.id.txt_child_name);
        txt.setText(children.getPriceTypeItemIdName());

        //onclick


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _iGetPriceType.GetPriceType(children);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
