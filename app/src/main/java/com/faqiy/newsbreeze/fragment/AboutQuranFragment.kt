package com.faqiy.newsbreeze.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.faqiy.newsbreeze.NewsViewModel
import com.faqiy.newsbreeze.R
import com.faqiy.newsbreeze.adapter.NewsAdapter
import com.faqiy.newsbreeze.databinding.FragmentAboutQuranBinding
import com.faqiy.newsbreeze.databinding.FragmentCommonBinding
import com.faqiy.newsbreeze.databinding.ItemNewsBinding

class AboutQuranFragment : Fragment() {
    private var _binding : FragmentAboutQuranBinding? = null
    private val binding get() = _binding as FragmentAboutQuranBinding

    private var _viewModel : NewsViewModel? = null
    private val viewModel get() = _viewModel as NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAboutQuranBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadingView.root.visibility = View.VISIBLE
        _viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        viewModel.getEntertainmentNews()
        viewModel.AboutQuranNews.observe(viewLifecycleOwner) {
            val data = it.articles
            binding.rvAboutQuran.apply {
                val mAdapter = NewsAdapter()
                mAdapter.setData(data)
                adapter = mAdapter
                layoutManager = LinearLayoutManager(context)
            }
            binding.loadingView.root.visibility = View.GONE
        }
    }


}