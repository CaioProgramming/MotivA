package com.ilustris.motiv.adapters

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.ilustris.motiv.R
import com.ilustris.motiv.beans.Quotes
import com.ilustris.motiv.model.Quotesdb

import java.util.ArrayList

import de.hdodenhof.circleimageview.CircleImageView
import mobile.sarproj.com.layout.SwipeLayout

class SuggestionsAdapter(internal var quotesArrayList: ArrayList<Quotes>, internal var activity: Activity) : RecyclerView.Adapter<SuggestionsAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val mInflater = LayoutInflater.from(activity)
        val view = mInflater.inflate(R.layout.suggestions_card, parent, false)


        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val quote = quotesArrayList[holder.adapterPosition]
        holder.author.text = quote.author
        holder.quote.text = quote.quote

    }

    override fun getItemCount(): Int {
        return quotesArrayList.size
    }

    inner class MyViewHolder(convertView: View) : RecyclerView.ViewHolder(convertView) {
        internal var quote: TextView
        internal var author: TextView



        init {
            quote = convertView.findViewById(R.id.quote)
            author = convertView.findViewById(R.id.author)

        }
    }
}

