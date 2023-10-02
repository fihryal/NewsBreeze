package com.faqiy.newsbreeze.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.faqiy.newsbreeze.fragment.AboutQuranFragment
import com.faqiy.newsbreeze.fragment.CommonFragment
import com.faqiy.newsbreeze.fragment.SportFragment
import com.faqiy.newsbreeze.fragment.WarningFragment

// SectionPagerAdapter mewarisi FragmentStateAdapter untuk mengganti adapter
class SectionPagerAdapter (fa: FragmentActivity) : FragmentStateAdapter(fa) {
    // getItemCount Menentukan berapa banyak fragment yang akan di tampilkan di adapter
    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
        // mengatur posisi fragment dari kiri ke kanan / 0 .. n
        return when (position){
            0 -> CommonFragment()
            1 -> AboutQuranFragment()
            2 -> SportFragment()
            3 -> WarningFragment()
            else ->CommonFragment()
        }
    }
}