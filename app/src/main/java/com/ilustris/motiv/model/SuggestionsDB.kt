package com.ilustris.motiv.model

import android.app.Activity
import com.ilustris.motiv.beans.Quotes
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import android.R.string
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ilustris.motiv.adapters.SuggestionsAdapter


class SuggestionsDB(private val activity: Activity){

    var recyclerView: RecyclerView? = null

    fun carregar(){
        val thread = Thread(Runnable {
            try {
                //Your code goes here
                val client = OkHttpClient()
                val request = Request.Builder()
                        .url("https://favqs.com/api/qotd")
                        .get()
                        .build()

                val response = client.newCall(request).execute()
                val quoteslist = ArrayList<Quotes>()

                val jsonData = response.body()?.string()
                val Jobject = JSONObject(jsonData)
                val jquote = JSONObject(Jobject.getString("quote"))
                Log.d("Sugest",Jobject.toString(2))
                val quotes = Quotes()
                quotes.quote = jquote.getString("body")
                quotes.author = jquote.getString("author")
                 quoteslist.add(quotes)
                val suggestionsAdapter = SuggestionsAdapter(quoteslist,activity)
                val llm = GridLayoutManager(activity,2,GridLayoutManager.VERTICAL,false)
                recyclerView!!.adapter = suggestionsAdapter
                recyclerView?.layoutManager = llm




            } catch (e: Exception) {
                e.printStackTrace()
            }
        })

        thread.start()




    }



}