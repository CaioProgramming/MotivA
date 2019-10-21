package com.ilustris.motiv.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import com.ilustris.motiv.fragments.HomeFragment
import com.ilustris.motiv.fragments.PicsFragment
import com.ilustris.motiv.fragments.SuggestionsFragment

class HomePager(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> PicsFragment()
            else -> SuggestionsFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }
}
