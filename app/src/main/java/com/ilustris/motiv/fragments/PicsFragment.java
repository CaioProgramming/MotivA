package com.ilustris.motiv.fragments;


import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.util.Util;
import com.google.android.material.appbar.AppBarLayout;
import com.ilustris.motiv.R;
import com.ilustris.motiv.db.Picsdb;
import com.ilustris.motiv.tools.Utils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PicsFragment extends Fragment {


    private AppBarLayout appbar;
    private CardView addicon;
    private RecyclerView recyclerview;

    public PicsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pics, container, false);
        initView(v);
        return v;
    }


    private void initView(View v) {
        appbar = v.findViewById(R.id.appbar);
        addicon = v.findViewById(R.id.addpicard);
        ImageView pic = v.findViewById(R.id.pic);
        recyclerview = v.findViewById(R.id.recyclerview);
        Picsdb picsdb = new Picsdb(getActivity());
        picsdb.Carregar(recyclerview);
        addicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.pickimage(getActivity());
            }
        });
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.pickimage(getActivity());
            }
        });
    }
}
