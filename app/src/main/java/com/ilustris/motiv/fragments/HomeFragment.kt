package com.ilustris.motiv.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

import com.google.android.material.appbar.AppBarLayout
import com.ilustris.motiv.R
import com.ilustris.motiv.model.Quotesdb

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {


    private var appbar: AppBarLayout? = null
    private var reportcount: TextView? = null
    private var recyclerview: RecyclerView? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        initView(v)
        return v
    }

    private fun initView(v: View) {
        val empty = v.findViewById<TextView>(R.id.empty)
        appbar = v.findViewById(R.id.appbar)
        reportcount = v.findViewById(R.id.reportcount)
        recyclerview = v.findViewById(R.id.recyclerview)
        val quotesdb = Quotesdb(activity!!)
        quotesdb.Carregar(recyclerview!!)
        if (recyclerview!!.childCount == 0) {
            empty.visibility = View.VISIBLE
        }

    }
}// Required empty public constructor
