package com.ilustris.motiv.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.ilustris.motiv.R
import com.ilustris.motiv.model.SuggestionsDB

/**
 * A simple [Fragment] subclass.
 */
class SuggestionsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_suggestions, container, false)

        val suggestionsDB = SuggestionsDB(activity!!)
        suggestionsDB.recyclerView  = v.findViewById(R.id.suggestionsrecycler)
        suggestionsDB.carregar()
        return v

    }


}
