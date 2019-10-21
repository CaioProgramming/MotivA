package com.ilustris.motiv.adapters

import android.app.Activity
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.mmin18.widget.RealtimeBlurView
import com.ilustris.motiv.R
import com.ilustris.motiv.beans.Pics
import com.ilustris.motiv.model.Picsdb

import java.util.ArrayList

class PicsAdapter(private val activity: Activity, private val picsArrayList: ArrayList<Pics>) : RecyclerView.Adapter<PicsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val mInflater = LayoutInflater.from(activity)
        val view = mInflater.inflate(R.layout.pics, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pic = picsArrayList[holder.adapterPosition]
        val `in` = AnimationUtils.loadAnimation(activity, R.anim.pop_in)


        Glide.with(activity).load(pic.uri).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                holder.pic.setImageDrawable(activity.getDrawable(R.drawable.ic_bubble_chart_black_24dp))
                holder.pic.startAnimation(`in`)
                Log.println(Log.ERROR, "Pics", "Erro ao encontrar foto " + pic.uri!!)
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {


                if (resource != null) {
                    val out = AnimationUtils.loadAnimation(activity, R.anim.fade_out)
                    holder.loading.startAnimation(out)
                    holder.loading.visibility = View.GONE
                    holder.pic.setImageDrawable(resource)
                    holder.pic.startAnimation(`in`)

                    return true
                } else {
                    holder.loading.visibility = View.VISIBLE
                }

                return false
            }
        }).into(holder.pic)
        holder.pic.startAnimation(`in`)
        holder.blur.visibility = View.GONE
        holder.remove.visibility = View.GONE
        holder.remove.setOnClickListener {
            val picsdb = Picsdb(activity)
            picsdb.Remover(pic)
        }


    }

    override fun getItemCount(): Int {
        return picsArrayList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val pic: ImageView
         val loading: ProgressBar
         val blur: RealtimeBlurView
         val card: CardView
         val remove: Button

        init {
            card = itemView.findViewById(R.id.card)
            pic = itemView.findViewById(R.id.pic)
            loading = itemView.findViewById(R.id.loading)
            blur = itemView.findViewById(R.id.defocus)
            remove = itemView.findViewById(R.id.removebtn)
            pic.setOnClickListener {
                blur.visibility = View.VISIBLE
                remove.visibility = View.VISIBLE
                val pulse = AnimationUtils.loadAnimation(activity, R.anim.pulse)
                card.startAnimation(pulse)
            }
        }
    }
}
