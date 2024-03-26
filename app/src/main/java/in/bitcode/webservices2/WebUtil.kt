package `in`.bitcode.webservices2

import android.util.Log
import com.google.gson.Gson
import `in`.bitcode.webservices2.models.Response
import `in`.bitcode.webservices2.models.User
import `in`.bitcode.webservices2.models.UserDetails
import org.json.JSONObject
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object WebUtil {

    fun fetchUsers() : ArrayList<User>? {
        val url = URL("https://reqres.in/api/users?page=1")
        val httpURLConnection = url.openConnection() as HttpURLConnection

        httpURLConnection.connect()

        if(httpURLConnection.responseCode != 200) {
            return null;
        }

        /*
        val responseBuffer = StringBuffer()
        val data = ByteArray(1024 * 2)
        var count = 0

        val inStream = httpURLConnection.getInputStream()
        count = inStream.read(data)
        while (count != -1) {
            responseBuffer.append(String(data, 0, count))
            count = inStream.read(data)
        }
        inStream.close()

        val gson = Gson()
        val response =
            gson.fromJson<Response>(responseBuffer.toString(), Response::class.java )*/

        val gson = Gson()
        val response =
            gson.fromJson<Response>(
                InputStreamReader(httpURLConnection.inputStream),
                Response::class.java
            )

        Log.e("tag", response.toString() )

        return response.users
    }

    fun fetchUserDetails(userId : Int) : UserDetails? {
        val url = URL("https://reqres.in/api/users/$userId")
        val httpURLConnection = url.openConnection() as HttpURLConnection

        httpURLConnection.connect()
        if(httpURLConnection.responseCode != 200) {
            return null
        }

        val stringBuffer = StringBuffer()
        val data = ByteArray(1024)
        var count = 0

        count = httpURLConnection.inputStream.read(data)
        while(count != -1) {
            stringBuffer.append(
                String(data, 0, count)
            )
            count = httpURLConnection.inputStream.read(data)
        }
        httpURLConnection.inputStream.close()

        Log.e("tag", stringBuffer.toString() )

        val jResponse = JSONObject(stringBuffer.toString())
        val jUser = jResponse.getJSONObject("data")

        return UserDetails(
            jUser.getInt("id"),
            jUser.getString("email"),
            jUser.getString("first_name"),
            jUser.getString("last_name"),
            jUser.getString("avatar")
        )
    }

    fun addUser(username : String, job : String) : Boolean {

        val url = URL("https://reqres.in/api/users")
        val httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.doOutput = true

        val inputJson = "{ name : $username, job : $job}"
        httpURLConnection.outputStream.write(inputJson.toByteArray())

        httpURLConnection.connect()

        if(httpURLConnection.responseCode != 201) {
            return false
        }

        val stringBuffer = StringBuffer()
        val data = ByteArray(1024)
        var count = 0

        count = httpURLConnection.inputStream.read(data)
        while(count != -1) {
            stringBuffer.append(
                String(data, 0, count)
            )
            count = httpURLConnection.inputStream.read(data)
        }
        httpURLConnection.inputStream.close()
        Log.e("tag", stringBuffer.toString())

        return true
    }
}