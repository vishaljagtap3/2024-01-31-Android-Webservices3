package `in`.bitcode.webservices2

import com.google.gson.annotations.SerializedName

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