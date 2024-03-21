package `in`.bitcode.webservices2

import android.util.Log
import org.json.JSONObject
import java.net.URL

object WebUtil {

    fun fetchUsers() {
        val url = URL("https://reqres.in/api/users?page=1")
        val httpURLConnection = url.openConnection()

        httpURLConnection.connect()

        val responseBuffer = StringBuffer()
        val data = ByteArray(1024*2)
        var count = 0

        val inStream = httpURLConnection.getInputStream()
        count = inStream.read(data)
        while(count != -1) {
            responseBuffer.append( String(data, 0, count))
            count = inStream.read(data)
        }
        inStream.close()

        Log.e("tag", responseBuffer.toString() )

        val jRoot = JSONObject(responseBuffer.toString());
        val page = jRoot.getInt("page")
        val perPage = jRoot.getInt("per_page")
        val total = jRoot.getInt("total")
        val totalPages = jRoot.getInt("total_pages")

        Log.e("tag", "page: $page per page = $perPage total = $total total pages = $totalPages", )
    }
}