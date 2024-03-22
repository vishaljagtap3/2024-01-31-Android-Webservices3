package `in`.bitcode.webservices2

import android.util.Log
import com.google.gson.Gson
import org.json.JSONObject
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.math.log

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
}