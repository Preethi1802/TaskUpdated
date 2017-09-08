package com.example.admin.task1.brand.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.task1.R;
import com.example.admin.task1.model.Brand;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 8/29/2017.
 */

public class AdapterBrand extends BaseAdapter {
    @BindView(R.id.brand_text)      TextView tv_brand;

    public ArrayList<Brand> brandList;
    LayoutInflater layoutInflater;

    public AdapterBrand(ArrayList<Brand> brandList) {
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

        //inflate items in brand list view
        view = layoutInflater.from(viewGroup.getContext()).inflate(R.layout.brand_items, viewGroup, false);
        ButterKnife.bind(this,view);
        Brand brand = brandList.get(position);
        tv_brand.setText(brand.getName());
        return view;

    }
}