package `in`.bitcode.webservices2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.bitcode.webservices2.adapters.UsersAdapter
import `in`.bitcode.webservices2.databinding.ActivityMainBinding
import `in`.bitcode.webservices2.fragments.AddUserFragment
import `in`.bitcode.webservices2.fragments.UserDetailsFragment2
import `in`.bitcode.webservices2.models.User
import `in`.bitcode.webservices2.threads.WebThread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var usersAdapter: UsersAdapter

    private val users = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initListeners()

        WebThread(
            UsersHandler()
        ).execute()
    }

    private fun initListeners() {
        binding.btnAddUser.setOnClickListener {
            val addUserFragment = AddUserFragment()

            supportFragmentManager.beginTransaction()
                .add(R.id.mainContainer, addUserFragment, null)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun initAdapter() {
        usersAdapter = UsersAdapter(users)
        binding.recyclerUsers.adapter = usersAdapter
        binding.recyclerUsers.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        usersAdapter.onUserClickListener = MyOnUserClickListener()

    }

    private inner class MyOnUserClickListener : UsersAdapter.OnUserClickListener {
        override fun onUserClick(position: Int, user: User) {
            //Add the fragment to show the user details
            //way1
            /*val input = Bundle()
            input.putSerializable("user", user)

            val userDetailsFragment1 = UserDetailsFragment1()
            userDetailsFragment1.arguments = input

            supportFragmentManager.beginTransaction()
                .add(R.id.mainContainer, userDetailsFragment1, null)
                .addToBackStack(null)
                .commit()*/

            val input = Bundle()
            input.putInt("user_id", user.id)

            val userDetailsFragment2 = UserDetailsFragment2()
            userDetailsFragment2.arguments = input

            supportFragmentManager.beginTransaction()
                .add(R.id.mainContainer, userDetailsFragment2, null)
                .addToBackStack(null)
                .commit()

        }
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