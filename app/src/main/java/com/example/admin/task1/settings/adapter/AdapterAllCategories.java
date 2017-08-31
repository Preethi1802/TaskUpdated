package com.example.admin.task1.settings.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.task1.R;
import com.example.admin.task1.model.Category;
import com.example.admin.task1.model.Child;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Admin on 8/23/2017.
 */

public class AdapterAllCategories extends BaseExpandableListAdapter {
    private Context mContext;
    private List<Category> categoryList;


    public AdapterAllCategories(Context context, List<Category> categoryList) {
        this.mContext = context;
        this.categoryList = categoryList;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        List<Child> childArrayList = categoryList.get(groupPosition).getChildren();

        return childArrayList.get(childPosititon);

    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        Child child = (Child) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child_elctronic_item, null);
        }

        TextView tv_child = (TextView) convertView.findViewById(R.id.txt_child_list);
        tv_child.setText(child.getName());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<Child> childList = categoryList.get(groupPosition).getChildren();
        return childList.size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return categoryList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return categoryList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Category category = (Category) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.parent_electronic_item, null);
        }

        TextView tv_parent = (TextView) convertView.findViewById(R.id.txt_electronics_list);
        tv_parent.setText(category.getName());

        ImageView iv_category_icon = (ImageView) convertView.findViewById(R.id.category_icon);
        String iconUrl = category.getIconUrl();
        Log.i(TAG, "" + iconUrl);
        Glide.with(mContext).load(iconUrl).into(iv_category_icon);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}