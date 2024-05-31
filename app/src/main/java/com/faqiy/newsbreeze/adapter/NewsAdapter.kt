package com.faqiy.newsbreeze.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.faqiy.newsbreeze.DetailActivity
import com.faqiy.newsbreeze.data.ArticlesItem
import com.faqiy.newsbreeze.databinding.ItemNewsBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NewsAdapter:RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {
    private var listNews = ArrayList<ArticlesItem>()

    fun setData(list: List<ArticlesItem>?){
        if (list == null)return
        listNews.clear()
        listNews.addAll(list)

    }
    inner class MyViewHolder( val binding: ItemNewsBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder (
        ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )

    override fun getItemCount() = listNews.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listNews[position]

        val date = data.publishedAt?.take(10)
        val dateArray = date?.split("-")?.toTypedArray()

        val calender = Calendar.getInstance()
        dateArray?.let {
            calender.set(Calendar.YEAR, it[0].toInt())
            calender.set(Calendar.MONTH, it[1].toInt() - 1)
            calender.set(Calendar.DATE, it[2].toInt())
        }


        val dateFormat = SimpleDateFormat("MMM,dd,''yy", Locale.getDefault()).format(calender.time)
        val timeFormat = SimpleDateFormat("h:mm a", Locale.getDefault()).format(calender.time)

        val publishedResult = "$dateFormat at $timeFormat"

        holder.binding.apply{
            itemTitle.text = data.title
            itemDate.text = publishedResult
            itemAuthor.text = data.author
            Picasso.get().load(data.urlToImage).into(itemImage)
        }
            holder.itemView.setOnClickListener{
                val intent = Intent(it.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA,data)
                it.context.startActivity(intent)
            }
    }
}