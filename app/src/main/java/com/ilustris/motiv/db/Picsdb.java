package com.ilustris.motiv.db;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ilustris.motiv.R;
import com.ilustris.motiv.adapters.PicsAdapter;
import com.ilustris.motiv.beans.Pics;
import com.ilustris.motiv.tools.Alert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
                        Pics p = d.getValue(Pics.class);
                        p.setId(d.getKey());
                        picsArrayList.add(p);

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

    public void Remover(final Pics pic){
        FirebaseStorage mFirebaseStorage = FirebaseStorage.getInstance();
        StorageReference photoRef = mFirebaseStorage.getReferenceFromUrl(pic.getUri());

        photoRef.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("images");
                    reference.child(pic.getId()).removeValue(new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                            Alert  alert = new Alert(activity);
                            alert.MessageAlert(activity.getDrawable(R.drawable.ic_success),"Ícone removido com sucesso");
                        }
                    });
                }else{
                    Alert  alert = new Alert(activity);
                    alert.MessageAlert(activity.getDrawable(R.drawable.ic_cancel),"Erro ao remover ícone " + task.getException().getMessage());
                }

            }
        });


    }

    public void AddIcon(final String name, Uri path){
        try {
            InputStream stream = new FileInputStream(new File(path.getPath()));
            FirebaseStorage storage = FirebaseStorage.getInstance();
            // Create a storage reference from our app
            StorageReference storageRef = storage.getReference();
            // Create a reference to 'images/mountains.jpg'
            final StorageReference iconimageref = storageRef.child(String.format("icons/%s.png", name));


            final UploadTask uploadTask = iconimageref.putStream(stream);

            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return iconimageref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Pics pics = new Pics();
                        pics.setName(name);
                        pics.setUri(String.valueOf(task.getResult()));
                        DatabaseReference iconsref = FirebaseDatabase.getInstance().getReference("images");
                        iconsref.push().setValue(pics);
                    } else {
                        Alert alert = new Alert(activity);
                        alert.MessageAlert(activity.getDrawable(R.drawable.ic_cancel),"Erro ao retornar url de download "+
                                task.getException().getMessage());

                    }
                }
            });





        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Alert alert = new Alert(activity);
            alert.MessageAlert(activity.getDrawable(R.drawable.ic_cancel),"Erro ao encontrar imagem.");
        }





    }
}
