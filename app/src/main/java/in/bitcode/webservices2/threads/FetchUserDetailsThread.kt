package `in`.bitcode.webservices2.threads

import android.os.AsyncTask
import android.os.Handler
import android.os.Message
import `in`.bitcode.webservices2.WebUtil
import `in`.bitcode.webservices2.models.UserDetails

class FetchUserDetailsThread(
    private val handler: Handler
) : AsyncTask<Int, Any, UserDetails?>(){

    override fun doInBackground(vararg params: Int?): UserDetails? {
        return WebUtil.fetchUserDetails( (params as Array<Int>)[0] )
    }

    override fun onPostExecute(result: UserDetails?) {
        val message = Message()
        message.obj = result
        handler.sendMessage(message)
    }
}