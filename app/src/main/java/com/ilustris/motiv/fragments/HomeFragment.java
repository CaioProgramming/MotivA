package com.ilustris.motiv.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.ilustris.motiv.R;
import com.ilustris.motiv.db.Picsdb;
import com.ilustris.motiv.db.Quotesdb;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private AppBarLayout appbar;
    private TextView reportcount;
    private RecyclerView recyclerview;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        appbar = (AppBarLayout) v.findViewById(R.id.appbar);
        reportcount = (TextView) v.findViewById(R.id.reportcount);
        recyclerview = (RecyclerView) v.findViewById(R.id.recyclerview);
        Quotesdb quotesdb = new Quotesdb(getActivity());
        quotesdb.Carregar(recyclerview);

    }
}
