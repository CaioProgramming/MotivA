package com.ilustris.motiv.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

import com.google.android.material.appbar.AppBarLayout
import com.ilustris.motiv.R
import com.ilustris.motiv.model.Picsdb
import com.ilustris.motiv.tools.Utils

/**
 * A simple [Fragment] subclass.
 */
class PicsFragment : Fragment() {


    private var appbar: AppBarLayout? = null
    private var addicon: CardView? = null
    private var recyclerview: RecyclerView? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_pics, container, false)
        initView(v)
        return v
    }


    private fun initView(v: View) {
        appbar = v.findViewById(R.id.appbar)
        addicon = v.findViewById(R.id.addpicard)
        val pic = v.findViewById<ImageView>(R.id.pic)
        recyclerview = v.findViewById(R.id.recyclerview)
        val picsdb = Picsdb(activity!!)
        picsdb.Carregar(recyclerview!!)
        addicon!!.setOnClickListener { Utils.pickimage(activity!!) }
        pic.setOnClickListener { Utils.pickimage(activity!!) }
    }
}// Required empty public constructor
