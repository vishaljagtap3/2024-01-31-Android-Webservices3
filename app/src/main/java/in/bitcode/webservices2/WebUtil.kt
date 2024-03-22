package `in`.bitcode.webservices2

import android.util.Log
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object WebUtil {

    fun fetchUsers() : ArrayList<User>? {
        val url = URL("https://reqres.in/api/users?page=1")
        val httpURLConnection = url.openConnection() as HttpURLConnection

        httpURLConnection.requestMethod = "GET"
        httpURLConnection.connect()

        if(httpURLConnection.responseCode != 200) {
            return null;
        }

        Log.e("tag", "Response code: ${httpURLConnection.responseCode}")
        Log.e("tag", "Response message: ${httpURLConnection.responseMessage}")
        Log.e("tag", "Response type: ${httpURLConnection.contentType}")
        Log.e("tag", "Response length: ${httpURLConnection.contentLength}")

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

        Log.e("tag", responseBuffer.toString())

        val jRoot = JSONObject(responseBuffer.toString());
        val page = jRoot.getInt("page")
        val perPage = jRoot.getInt("per_page")
        val total = jRoot.getInt("total")
        val totalPages = jRoot.getInt("total_pages")

        val jUsers = jRoot.getJSONArray("data")
        val users = ArrayList<User>()

        for (i in 0..jUsers.length() - 1) {
            val jUser = jUsers.getJSONObject(i)
            val id = jUser.getInt("id")
            val email = jUser.getString("email")
            val firstName = jUser.getString("first_name")
            val lastName = jUser.getString("last_name")
            val avatar = jUser.getString("avatar")

            users.add(
                User(
                    id,
                    email,
                    firstName,
                    lastName,
                    avatar
                )
            )
            Log.e("tag", "user: $id $email $firstName $lastName $avatar")
        }

        Log.e("tag", "page: $page per page = $perPage total = $total total pages = $totalPages")

        return users
    }
}