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

public class AllDetailsFragement extends Fragment
{
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static AllDetailsFragement newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        AllDetailsFragement fragment = new AllDetailsFragement();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_detail_title_fragement, container, false);
        TextView tvTitle = (TextView) view.findViewById(R.id.Title);
        tvTitle.setText("Fragment #" + mPage);
        return view;
    }
}
