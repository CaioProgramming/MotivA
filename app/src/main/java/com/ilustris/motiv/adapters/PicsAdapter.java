package com.ilustris.motiv.adapters;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.mmin18.widget.RealtimeBlurView;
import com.ilustris.motiv.R;
import com.ilustris.motiv.beans.Pics;
import com.ilustris.motiv.db.Picsdb;

import java.util.ArrayList;

public class PicsAdapter extends RecyclerView.Adapter<PicsAdapter.MyViewHolder> {
    private Activity activity;
    private ArrayList<Pics> picsArrayList;

    public PicsAdapter(Activity mactivity, ArrayList<Pics> picsArrayList) {
        this.activity = mactivity;
        this.picsArrayList = picsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(activity);
        View view = mInflater.inflate(R.layout.pics,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Pics pic = picsArrayList.get(holder.getAdapterPosition());
        final Animation in = AnimationUtils.loadAnimation(activity, R.anim.pop_in);


        Glide.with(activity).load(pic.getUri()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.pic.setImageDrawable(activity.getDrawable(R.drawable.ic_bubble_chart_black_24dp));
                holder.pic.startAnimation(in);
                Log.println(Log.ERROR,"Pics","Erro ao encontrar foto " + pic.getUri());
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {


                if (resource != null) {
                    Animation out = AnimationUtils.loadAnimation(activity, R.anim.fade_out);
                    holder.loading.startAnimation(out);
                    holder.loading.setVisibility(View.GONE);
                    holder.pic.setImageDrawable(resource);
                    holder.pic.startAnimation(in);

                    return true;
                } else {
                    holder.loading.setVisibility(View.VISIBLE);
                }

                return false;
            }
        }).into(holder.pic);
        holder.pic.startAnimation(in);
        holder.blur.setVisibility(View.GONE);
        holder.remove.setVisibility(View.GONE);
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picsdb picsdb = new Picsdb(activity);
                picsdb.Remover(pic);
             }
        });

    }

    @Override
    public int getItemCount() {
        return picsArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView pic;
        private ProgressBar loading;
         private RealtimeBlurView blur;
        private Button remove;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.pic);
            loading = itemView.findViewById(R.id.loading);
            blur = itemView.findViewById(R.id.defocus);
            remove = itemView.findViewById(R.id.removebtn);
            pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    blur.setVisibility(View.VISIBLE);
                    remove.setVisibility(View.VISIBLE);
                }
            });
        }
    }
}
