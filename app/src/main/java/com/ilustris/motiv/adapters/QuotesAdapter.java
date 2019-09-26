package com.ilustris.motiv.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ilustris.motiv.R;
import com.ilustris.motiv.beans.Quotes;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import mobile.sarproj.com.layout.SwipeLayout;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.MyViewHolder> {

    ArrayList<Quotes> quotesArrayList;
    Activity activity;

    public QuotesAdapter(ArrayList<Quotes> quotesArrayList, Activity activity) {
        this.quotesArrayList = quotesArrayList;
        this.activity = activity;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(activity);
       View view = mInflater.inflate(R.layout.quotes_card,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Quotes quote = quotesArrayList.get(holder.getAdapterPosition());
        holder.author.setText(quote.getAuthor());
        holder.quote.setText(quote.getQuote());
        holder.author.setTextColor(quote.getTextcolor());
        holder.quote.setTextColor(quote.getTextcolor());
        holder.background.setCardBackgroundColor(quote.getBackgroundcolor());
        holder.dia.setText(quote.getData());
        holder.username.setText(quote.getUsername());
        Glide.with(activity).load(quote.getUserphoto()).into(holder.userpic);
        if (quote.getBackgroundcolor() == 0){
            holder.background.setCardBackgroundColor(Color.WHITE); }
        if (quote.getTextcolor() == 0){
            holder.quote.setTextColor(Color.BLACK);
            holder.author.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return quotesArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SwipeLayout card;
        LinearLayout quotedata;
        CircleImageView userpic;
        TextView username;
        TextView dia;
        CardView background;
        TextView quote;
        TextView author;


        public MyViewHolder(@NonNull View convertView) {
            super(convertView);
            card = convertView.findViewById(R.id.card);
            quotedata = convertView.findViewById(R.id.quotedata);
            userpic = convertView.findViewById(R.id.userpic);
            username = convertView.findViewById(R.id.username);
            dia = convertView.findViewById(R.id.dia);
            background = convertView.findViewById(R.id.background);
            quote = convertView.findViewById(R.id.quote);
            author = convertView.findViewById(R.id.author);
        }
    }
}

