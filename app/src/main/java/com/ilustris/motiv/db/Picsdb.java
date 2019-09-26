package com.ilustris.motiv.db;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ilustris.motiv.adapters.PicsAdapter;
import com.ilustris.motiv.beans.Pics;

import java.util.ArrayList;

public class Picsdb extends Quotesdb {
    private Activity activity;


    public Picsdb(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    public void Carregar(final RecyclerView recyclerView) {
        DatabaseReference picsdb = FirebaseDatabase.getInstance().getReference();
        picsdb.keepSynced(false);
        picsdb = FirebaseDatabase.getInstance().getReference().child("images");
        picsdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    ArrayList<Pics> picsArrayList = new ArrayList<>();
                    for (DataSnapshot d: dataSnapshot.getChildren()) {
                        picsArrayList.add(d.getValue(Pics.class));

                    }
                    GridLayoutManager llm = new GridLayoutManager(activity,3, LinearLayoutManager.VERTICAL,false);
                    PicsAdapter picsAdapter = new PicsAdapter(activity,picsArrayList);
                    recyclerView.setAdapter(picsAdapter);
                    recyclerView.setLayoutManager(llm);
                    Log.println(Log.INFO,"Pics","There are " + picsArrayList.size() + " icons");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
