package com.example.admin.task1.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.task1.R;

/**
 * Created by Admin on 8/31/2017.
 */

public class OneFragement extends Fragment
{

    public OneFragement() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.all_detail_title_fragement, container, false);
        TextView tvTitle = (TextView) view.findViewById(R.id.fragement);
        tvTitle.setText("Fragment #bnfcvrfvkb");
        return view;
        // Inflate the layout for this fragment
    //    return inflater.inflate(R.layout.all_detail_title_fragement, container, false);
    }
}
