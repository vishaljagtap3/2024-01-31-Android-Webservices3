package `in`.bitcode.webservices2.threads

import android.os.AsyncTask
import android.os.Handler
import android.os.Message
import `in`.bitcode.webservices2.WebUtil
import `in`.bitcode.webservices2.models.User

class WebThread(
    private val handler: Handler
) : AsyncTask<Any, Any, ArrayList<User>>(){

    override fun doInBackground(vararg params: Any?): ArrayList<User>? {
        return WebUtil.fetchUsers()
    }

    override fun onPostExecute(result: ArrayList<User>?) {
        super.onPostExecute(result)

        val message = Message()
        message.obj = result

        handler.sendMessage(message)

    }
}