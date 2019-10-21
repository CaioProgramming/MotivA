package com.ilustris.motiv.model

import android.app.Activity
import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.ilustris.motiv.R
import com.ilustris.motiv.adapters.PicsAdapter
import com.ilustris.motiv.beans.Pics
import com.ilustris.motiv.tools.Alert

import java.io.ByteArrayOutputStream
import java.util.ArrayList

import de.hdodenhof.circleimageview.CircleImageView

class Picsdb(private val activity: Activity){

      fun Carregar(recyclerView: RecyclerView) {
        var picsdb = FirebaseDatabase.getInstance().reference
        picsdb.keepSynced(false)
        picsdb = FirebaseDatabase.getInstance().reference.child("images")
        picsdb.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val picsArrayList = ArrayList<Pics>()
                    for (d in dataSnapshot.children) {
                        val p = d.getValue(Pics::class.java)
                        p!!.id = d.key
                        picsArrayList.add(p)

                    }
                    val llm = GridLayoutManager(activity, 3, LinearLayoutManager.VERTICAL, false)
                    val picsAdapter = PicsAdapter(activity, picsArrayList)
                    recyclerView.adapter = picsAdapter
                    recyclerView.layoutManager = llm
                    Log.println(Log.INFO, "Pics", "There are " + picsArrayList.size + " icons")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

    }

    fun Remover(pic: Pics) {
        val mFirebaseStorage = FirebaseStorage.getInstance()
        val photoRef = mFirebaseStorage.getReferenceFromUrl(pic.uri!!)

        photoRef.delete().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val reference = FirebaseDatabase.getInstance().getReference("images")
                reference.child(pic.id!!).removeValue { databaseError, databaseReference ->
                    val alert = Alert(activity)
                    alert.MessageAlert(activity.getDrawable(R.drawable.ic_success), "Ícone removido com sucesso")
                }
            } else {
                val alert = Alert(activity)
                alert.MessageAlert(activity.getDrawable(R.drawable.ic_cancel), "Erro ao remover ícone " + task.exception!!.message)
            }
        }


    }

    fun AddIcon(name: String, imageView: CircleImageView, dialog: Dialog) {
        // Get the data from an ImageView as bytes
        imageView.isDrawingCacheEnabled = true
        imageView.buildDrawingCache()
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        val storage = FirebaseStorage.getInstance()
        // Create a storage reference from our app
        val storageRef = storage.reference
        // Create a reference to 'images/mountains.jpg'
        val iconimageref = storageRef.child(String.format("icons/%s.png", name))
        val alert = Alert(activity)

        val uploadTask = iconimageref.putBytes(data)


        val urlTask = uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                throw task.getException()!!
            }

            // Continue with the task to get the download URL
            iconimageref.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                dialog.dismiss()
                val pics = Pics()
                pics.name = name
                pics.uri = task.result.toString()
                val iconsref = FirebaseDatabase.getInstance().getReference("images")
                iconsref.push().setValue(pics)
                alert.MessageAlert(activity.getDrawable(R.drawable.ic_success), "Ícone adicionado com sucesso!")
            } else {
                alert.MessageAlert(activity.getDrawable(R.drawable.ic_cancel), "Erro ao retornar url de download " + task.exception!!.message)

            }
        }


    }
}
