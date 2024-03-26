package `in`.bitcode.webservices2.models

import com.google.gson.annotations.SerializedName
import `in`.bitcode.webservices2.models.User

data class Response(
    val page : Int,
    @SerializedName("per_page")
    val perPage : Int,
    val total : Int,
    @SerializedName("total_pages")
    val totalPages : Int,
    @SerializedName("data")
    val users : ArrayList<User>
)