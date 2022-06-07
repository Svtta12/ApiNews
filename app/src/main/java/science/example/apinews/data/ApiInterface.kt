package science.example.apinews.data

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import science.example.apinews.BuildConfig
import science.example.apinews.model.Article

interface ApiInterface {

    companion object{
        const val API_KEY = BuildConfig.API_KEY
        var BASE_URL = "https://newsapi.org/v2/"
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
     fun getNews(@Query("country") country: String) : Call <List<Article>>

}

//GET https://newsapi.org/v2/top-headlines?country=us&apiKey=64a169538ba04e4fa00624bcaaf09a3c