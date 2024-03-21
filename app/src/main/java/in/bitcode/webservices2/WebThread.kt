package `in`.bitcode.webservices2

import android.os.AsyncTask

class WebThread : AsyncTask<Any, Any, Any>(){

    override fun doInBackground(vararg params: Any?): Any? {
        WebUtil.fetchUsers()
        return null
    }
}