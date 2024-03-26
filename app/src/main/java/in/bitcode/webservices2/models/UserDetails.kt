package `in`.bitcode.webservices2.models

import com.google.gson.annotations.SerializedName

data class UserDetails(
    val id : Int,
    val email : String,
    @SerializedName("first_name")
    val firstName : String,
    @SerializedName("last_name")
    val lastName : String,
    val avatar : String
)