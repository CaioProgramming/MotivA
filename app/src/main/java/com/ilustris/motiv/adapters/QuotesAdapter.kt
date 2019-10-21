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

class QuotesAdapter(internal var quotesArrayList: ArrayList<Quotes>, internal var activity: Activity) : RecyclerView.Adapter<QuotesAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val mInflater = LayoutInflater.from(activity)
        val view = mInflater.inflate(R.layout.quotes_card, parent, false)


        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val quote = quotesArrayList[holder.adapterPosition]
        holder.author.text = quote.author
        holder.quote.text = quote.quote
        holder.author.setTextColor(quote.textcolor)
        holder.quote.setTextColor(quote.textcolor)
        holder.background.setCardBackgroundColor(quote.backgroundcolor)
        holder.dia.text = quote.data
        holder.username.text = quote.username
        Glide.with(activity).load(quote.userphoto).into(holder.userpic)
        if (quote.backgroundcolor == 0) {
            holder.background.setCardBackgroundColor(Color.WHITE)
        }
        if (quote.textcolor == 0) {
            holder.quote.setTextColor(Color.BLACK)
            holder.author.setTextColor(Color.BLACK)
        }


        holder.delete.setOnClickListener {
            val quotesdb = Quotesdb(activity)
            quotesdb.Delete(quote)
        }
        holder.restore.setOnClickListener {
            quote.isReport = false
            val quotesdb = Quotesdb(activity)
            quotesdb.Restore(quote)
        }
    }

    override fun getItemCount(): Int {
        return quotesArrayList.size
    }

    inner class MyViewHolder(convertView: View) : RecyclerView.ViewHolder(convertView) {
        internal var card: SwipeLayout
        internal var quotedata: LinearLayout
        internal var userpic: CircleImageView
        internal var username: TextView
        internal var dia: TextView
        internal var background: CardView
        internal var quote: TextView
        internal var author: TextView
        internal var restore: TextView
        internal var delete: TextView


        init {
            card = convertView.findViewById(R.id.card)
            quotedata = convertView.findViewById(R.id.quotedata)
            userpic = convertView.findViewById(R.id.userpic)
            username = convertView.findViewById(R.id.username)
            dia = convertView.findViewById(R.id.dia)
            background = convertView.findViewById(R.id.background)
            quote = convertView.findViewById(R.id.quote)
            author = convertView.findViewById(R.id.author)
            restore = convertView.findViewById(R.id.rightview)
            delete = convertView.findViewById(R.id.leftview)
        }
    }
}

