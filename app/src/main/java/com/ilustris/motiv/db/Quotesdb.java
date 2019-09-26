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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ilustris.motiv.adapters.QuotesAdapter;
import com.ilustris.motiv.beans.Quotes;

import java.util.ArrayList;

public class Quotesdb {

Activity activity;

    public Quotesdb(Activity activity) {
        this.activity = activity;

    }


    public void Carregar(final RecyclerView recyclerView){
       DatabaseReference quotesdb = FirebaseDatabase.getInstance().getReference();
        quotesdb.keepSynced(false);
        quotesdb = FirebaseDatabase.getInstance().getReference().child("Quotes");
        quotesdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    ArrayList<Quotes> quotesArrayList = new ArrayList<>();
                    recyclerView.removeAllViews();

                    for (DataSnapshot d: dataSnapshot.getChildren()) {
                        Quotes quotes = d.getValue(Quotes.class);
                        if (quotes.isReport()){
                         quotesArrayList.add(quotes);
                        }
                    }
                    QuotesAdapter quotesAdapter = new QuotesAdapter(quotesArrayList,activity);
                    GridLayoutManager llm = new GridLayoutManager(activity,1 ,LinearLayoutManager.VERTICAL,false);
                    recyclerView.setAdapter(quotesAdapter);
                    recyclerView.setLayoutManager(llm);

                    Log.println(Log.INFO,"Quotes ", "there are " + quotesArrayList.size() + " quotes");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
