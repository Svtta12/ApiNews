package science.example.apinews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import science.example.apinews.adapter.NewsAdapter
import science.example.apinews.data.ApiInterface
import science.example.apinews.data.ApiInterface.Companion.API_KEY
import science.example.apinews.databinding.ActivityMainBinding
import science.example.apinews.model.Article

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NewsAdapter
//    val layoutManager = GridLayoutManager(this, 1)
    val layoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NewsAdapter()
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        val apiInterface = ApiInterface.create().getNews("ru")

        apiInterface.enqueue(object : Callback<List<Article>>{
            override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {
                if (response.body() != null)
                    adapter.seNewsListItems(response.body()!!)
            }

            override fun onFailure(call: Call<List<Article>>, t: Throwable) {}

        })
    }
}

