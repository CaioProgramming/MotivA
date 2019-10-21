package com.ilustris.motiv.model

import android.app.Activity
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.ilustris.motiv.R
import com.ilustris.motiv.adapters.QuotesAdapter
import com.ilustris.motiv.beans.Quotes
import com.ilustris.motiv.tools.Alert

import java.util.ArrayList

open class Quotesdb(internal var activity: Activity) {

    internal var quotesdb = FirebaseDatabase.getInstance().getReference("Quotes")


    open fun Carregar(recyclerView: RecyclerView) {

        quotesdb.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val quotesArrayList = ArrayList<Quotes>()
                    recyclerView.removeAllViews()

                    for (d in dataSnapshot.children) {
                        val quotes = d.getValue(Quotes::class.java)
                        if (quotes!!.isReport) {
                            quotesArrayList.add(quotes)
                        }
                    }

                    val quotesAdapter = QuotesAdapter(quotesArrayList, activity)
                    val llm = GridLayoutManager(activity, 1, LinearLayoutManager.VERTICAL, false)
                    recyclerView.adapter = quotesAdapter
                    recyclerView.layoutManager = llm

                    Log.println(Log.INFO, "Quotes ", "there are " + quotesArrayList.size + " quotes")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

    fun Delete(quotes: Quotes) {
        quotesdb.child(quotes.id!!).removeValue { databaseError, databaseReference ->
            val alert = Alert(activity)
            alert.MessageAlert(activity.resources.getDrawable(R.drawable.ic_success), "Frase removida com sucesso!")
        }
    }

    fun Restore(quotes: Quotes) {
        quotesdb.child(quotes.id!!).setValue(quotes).addOnCompleteListener { task ->
            val alert = Alert(activity)
            if (task.isSuccessful) {
                alert.MessageAlert(activity.getDrawable(R.drawable.ic_success), "Frase restaurada")
            } else {
                alert.MessageAlert(activity.getDrawable(R.drawable.ic_cancel), "Erro ao restaurar frase " + task.exception!!.message)

            }
        }
    }
}
