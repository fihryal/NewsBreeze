package com.faqiy.newsbreeze

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.faqiy.newsbreeze.adapter.SectionPagerAdapter
import com.faqiy.newsbreeze.data.NewsResponse
import com.faqiy.newsbreeze.data.network.ApiClient
import com.faqiy.newsbreeze.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var _binding:ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)

        // mengatur adapter ViewPager2 menggunakan class SectionPagerAdapter
        binding.vpContainer.adapter = SectionPagerAdapter(this)
        // array untuk mengatur tab item pada TabLayout
        val listFragment = arrayOf("Common", "Entertainment","Sport","Warning")

        // mengatur TabLayout dan ViewPager2 jadi bisa menyatu dengan yang lain
        TabLayoutMediator(binding.tabLayout, binding.vpContainer){tab,position ->
            tab.text =listFragment[position]
        }.attach()



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        val searchView = menu?.findItem(R.id.option_search)?.actionView as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId){
//            R.id.option_search -> onSearchRequested()
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}