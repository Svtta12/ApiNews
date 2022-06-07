package science.example.apinews.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import science.example.apinews.R
import science.example.apinews.databinding.ItemMainBinding
import science.example.apinews.model.Article

class NewsAdapter(): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var newsList : List<Article> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMainBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val new = newsList[position]
        with(holder.binding) {
            holder.itemView.tag = new

            textTitle.text = new.title
            textContent.text = new.content
            textAuthor.text = new.author
            textPublishedAt.text = new.publishedAt
            Picasso.get()
                .load(new.urlToImage)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(imageNews)
        }
    }

    override fun getItemCount(): Int = newsList.size

    inner class NewsViewHolder (val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun seNewsListItems(list: List<Article>) {
        this.newsList = list
        notifyDataSetChanged()
    }
}