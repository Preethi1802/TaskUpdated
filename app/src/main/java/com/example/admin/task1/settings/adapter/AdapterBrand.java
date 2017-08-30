package com.example.admin.task1.settings.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.task1.R;
import com.example.admin.task1.model.Brand;

import java.util.ArrayList;

/**
 * Created by Admin on 8/29/2017.
 */

public class AdapterBrand extends BaseAdapter {
    Context context;
    public ArrayList<Brand> brandList;
    LayoutInflater layoutInflater;

    public AdapterBrand(ArrayList<Brand> brandList) {
        this.context = context;
        this.brandList = brandList;

    }

    @Override
    public int getCount() {
        return brandList.size();
    }

    @Override
    public Object getItem(int position) {
        return brandList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        view = layoutInflater.from(viewGroup.getContext()).inflate(R.layout.brand_items, viewGroup, false);

        Brand brand = brandList.get(position);
        TextView tv_brand = (TextView) view.findViewById(R.id.brand_text);
        tv_brand.setText(brand.getName());

        return view;

    }
}