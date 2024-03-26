package `in`.bitcode.webservices2.fragments

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import `in`.bitcode.webservices2.WebUtil
import `in`.bitcode.webservices2.databinding.AddUserFragmentBinding

class AddUserFragment : Fragment() {

    private lateinit var addUserFragmentBinding: AddUserFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addUserFragmentBinding = AddUserFragmentBinding.inflate(inflater)

        initListeners()

        return addUserFragmentBinding.root
    }

    private fun initListeners() {
        addUserFragmentBinding.btnAddUser.setOnClickListener {
            val addUserThread = object : AsyncTask<Any, Any, Boolean>() {
                override fun doInBackground(vararg params: Any?): Boolean {
                    return WebUtil.addUser(
                        addUserFragmentBinding.edtUsername.text.toString(),
                        addUserFragmentBinding.edtJob.text.toString()
                    );
                }

                override fun onPostExecute(isAdded: Boolean) {
                    if(isAdded) {
                        Toast.makeText(requireContext(), "User added!", Toast.LENGTH_LONG).show()
                        parentFragmentManager.popBackStack()
                    }
                    else {
                        Toast.makeText(requireContext(), "Unable to add user!", Toast.LENGTH_LONG).show()
                    }
                }
            }
            addUserThread.execute()
        }


    }
}