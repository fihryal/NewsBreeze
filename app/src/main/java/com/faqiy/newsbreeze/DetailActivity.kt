package com.faqiy.newsbreeze

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.faqiy.newsbreeze.data.ArticlesItem
import com.faqiy.newsbreeze.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    private var _binding : ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra(EXTRA_DATA,ArticlesItem::class.java)

//        binding.apply {
//            detailTitle.text = data?.title
//            detailAuthor.text = data?.author
//            detailDate.text = data?.publishedAt
//            Picasso.get().load(data?.urlToImage).into(detailImage)
            setWebView(data)
//
//
//        }
    }

    private fun setWebView(data: ArticlesItem?) {
        var loadingFinished = true
        var redirect = false
        binding.wvDetail.webViewClient = object  : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                if (!loadingFinished){
                    redirect = true
                }
                loadingFinished = false
                view?.loadUrl(request?.url.toString())
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                loadingFinished = false
                binding.loadingView.root.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                if (!redirect){
                    loadingFinished = true
                }
                if (loadingFinished && !redirect){
                    binding.loadingView.root.visibility = View.GONE
                }else{
                    redirect = false
                }
            }
        }
        data?.url?.let { binding.wvDetail.loadUrl(it) }
    }

    companion object{
            const val EXTRA_DATA = "data"
        }
}