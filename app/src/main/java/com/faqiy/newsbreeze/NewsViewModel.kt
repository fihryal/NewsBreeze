package com.faqiy.newsbreeze

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faqiy.newsbreeze.data.NewsResponse
import com.faqiy.newsbreeze.data.network.ApiClient
import com.faqiy.newsbreeze.fragment.AboutQuranFragment
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel: ViewModel() {
    // Handle UI
    private var _CommonNews = MutableLiveData<NewsResponse>()
    val CommonNews get() = _CommonNews as LiveData<NewsResponse>

    private var _AboutQuranNews = MutableLiveData<NewsResponse>()
    val AboutQuranNews get() = _AboutQuranNews as LiveData<NewsResponse>

    private var _SportNews = MutableLiveData<NewsResponse>()
    val SportNews get() = _SportNews as LiveData<NewsResponse>

    private var _WarningNews = MutableLiveData<NewsResponse>()
    val WarningNews get() = _WarningNews as LiveData<NewsResponse>



    private var _searchNews = MutableLiveData<NewsResponse>()
    val searchNews get() = _searchNews as LiveData<NewsResponse>

    // Handle nussines logic
    fun getCommonNews (){

        ApiClient.getApiService().getCommonNews().enqueue(object : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful){
                    Log.i("ViewModel","onResponse: ${response.body()}")
                    _CommonNews.postValue(response.body())
                }else Log.e("ViewModel",
                    "onFailedCall: Call Error with Http Status Code : "+response.code())
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("ViewModel", "onFailure: " + t.localizedMessage)
            }

        })
    }

    fun getEntertainmentNews (){

        ApiClient.getApiService().getEntertainmentNews().enqueue(object : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful){
                    Log.i("ViewModel","onResponse: ${response.body()}")
                    _AboutQuranNews.postValue(response.body())
                }else Log.e("ViewModel",
                    "onFailedCall: Call Error with Http Status Code : "+response.code())
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("ViewModel", "onFailure: " + t.localizedMessage)
            }

        })
    }

    fun getSportNews (){

        ApiClient.getApiService().getSportNews().enqueue(object : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful){
                    Log.i("ViewModel","onResponse: ${response.body()}")
                    _SportNews.postValue(response.body())
                }else Log.e("ViewModel",
                    "onFailedCall: Call Error with Http Status Code : "+response.code())
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("ViewModel", "onFailure: " + t.localizedMessage)
            }

        })
    }

    fun getWarningNews (){

        ApiClient.getApiService().getWarningNews().enqueue(object : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful){
                    Log.i("ViewModel","onResponse: ${response.body()}")
                    _WarningNews.postValue(response.body())
                }else Log.e("ViewModel",
                    "onFailedCall: Call Error with Http Status Code : "+response.code())
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("ViewModel", "onFailure: " + t.localizedMessage)
            }

        })
    }



    fun searchNews(query: String){
        ApiClient.getApiService().searchNews(query).enqueue(object : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>,response: Response<NewsResponse>){
                if (response.isSuccessful){
                    _searchNews.value = response.body()
                    _searchNews.postValue(response.body())
                }else Log.e("NewsViewModel","onResponse : ${response.message()}")
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("NewsViewModel","onFailure : ${t.localizedMessage}")
            }

        })

    }

}