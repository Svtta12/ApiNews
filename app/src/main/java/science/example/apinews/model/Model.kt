package science.example.apinews.model

data class Model(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
