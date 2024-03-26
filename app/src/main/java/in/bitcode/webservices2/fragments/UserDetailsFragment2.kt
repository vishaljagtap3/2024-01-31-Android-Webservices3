package `in`.bitcode.webservices2.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import `in`.bitcode.webservices2.databinding.UserDetailsFragment2Binding
import `in`.bitcode.webservices2.databinding.UserDetailsFragmentBinding
import `in`.bitcode.webservices2.models.UserDetails
import `in`.bitcode.webservices2.threads.FetchUserDetailsThread

class UserDetailsFragment2 : Fragment() {

    private var userId : Int = -1
    private lateinit var userDetailsFragmentBinding2: UserDetailsFragment2Binding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userId = requireArguments().getInt("user_id")

        FetchUserDetailsThread(
            UserDetailsHandler()
        ).execute(userId)

        userDetailsFragmentBinding2 = UserDetailsFragment2Binding.inflate(inflater)
        return userDetailsFragmentBinding2.root
    }

    private inner class UserDetailsHandler : Handler() {
        override fun handleMessage(msg: Message) {
            if(msg.obj == null) {
                Toast.makeText(requireContext(), "Unable to fetch user details!", Toast.LENGTH_LONG)
                    .show()
                return
            }

            userDetailsFragmentBinding2.userDetails = msg.obj as UserDetails
        }
    }

}
