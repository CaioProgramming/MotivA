package com.ilustris.motiv.db;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ilustris.motiv.R;
import com.ilustris.motiv.adapters.QuotesAdapter;
import com.ilustris.motiv.beans.Quotes;
import com.ilustris.motiv.tools.Alert;

import java.util.ArrayList;

public class Quotesdb {

    Activity activity;

    public Quotesdb(Activity activity) {
        this.activity = activity;

    }

    DatabaseReference quotesdb = FirebaseDatabase.getInstance().getReference("Quotes");


    public void Carregar(final RecyclerView recyclerView) {

        quotesdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ArrayList<Quotes> quotesArrayList = new ArrayList<>();
                    recyclerView.removeAllViews();

                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        Quotes quotes = d.getValue(Quotes.class);
                        if (quotes.isReport()) {
                            quotesArrayList.add(quotes);
                        }
                    }
                    QuotesAdapter quotesAdapter = new QuotesAdapter(quotesArrayList, activity);
                    GridLayoutManager llm = new GridLayoutManager(activity, 1, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setAdapter(quotesAdapter);
                    recyclerView.setLayoutManager(llm);

                    Log.println(Log.INFO, "Quotes ", "there are " + quotesArrayList.size() + " quotes");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void Delete(Quotes quotes) {
        quotesdb.child(quotes.getId()).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                Alert alert = new Alert(activity);
                alert.MessageAlert(activity.getResources().getDrawable(R.drawable.ic_success), "Frase removida com sucesso!");
            }
        });
    }

    public void Restore(Quotes quotes) {
        quotesdb.child(quotes.getId()).setValue(quotes).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Alert alert = new Alert(activity);
                if (task.isSuccessful()) {
                    alert.MessageAlert(activity.getDrawable(R.drawable.ic_success), "Frase restaurada");
                }else{
                    alert.MessageAlert(activity.getDrawable(R.drawable.ic_cancel), "Erro ao restaurar frase " + task.getException().getMessage());

                }
            }
        });
    }
}
