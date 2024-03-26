package `in`.bitcode.webservices2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import `in`.bitcode.webservices2.databinding.UserDetailsFragmentBinding
import `in`.bitcode.webservices2.models.User

class UserDetailsFragment1 : Fragment() {

    private lateinit var user : User
    private lateinit var userDetailsFragmentBinding: UserDetailsFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        user = requireArguments().getSerializable("user") as User

        userDetailsFragmentBinding = UserDetailsFragmentBinding.inflate(inflater)
        userDetailsFragmentBinding.user = user

        return userDetailsFragmentBinding.root
    }

}
