package `in`.bitcode.webservices2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.bitcode.webservices2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var usersAdapter: UsersAdapter

    private val users = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()

        WebThread(
            UsersHandler()
        ).execute()
    }

    private fun initAdapter() {
        usersAdapter = UsersAdapter(users)
        binding.recyclerUsers.adapter = usersAdapter
        binding.recyclerUsers.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private inner class UsersHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if(msg.obj == null) {
                mt("Unable to fetch data!")
                return
            }

            val users = msg.obj as ArrayList<User>

            this@MainActivity.users.addAll(users)
            usersAdapter.notifyDataSetChanged()

            for(user in users) {
                Log.v("tag", user.toString())
            }
        }
    }

    private fun mt(text : String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}