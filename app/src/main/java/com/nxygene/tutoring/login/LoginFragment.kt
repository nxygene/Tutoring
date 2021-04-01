package com.nxygene.tutoring.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.backendless.Backendless
import com.backendless.BackendlessUser
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.nxygene.tutoring.R
import com.nxygene.tutoring.hideKeyboard
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_login.setOnClickListener {
            hideKeyboard()

            //            activity!!.findNavController(R.id.nav_host_fragment).navigate(R.id.action_loginFragment_to_registerFragment)

            Backendless.UserService.login(
                login_username.text.toString(),
                login_password.text.toString(),
                object : AsyncCallback<BackendlessUser> {
                    override fun handleResponse(user: BackendlessUser) {
                        // user has been logged in
                        activity!!.findNavController(R.id.nav_host_fragment)
                            .navigate(R.id.action_loginFragment_to_registerFragment)
                    }

                    override fun handleFault(fault: BackendlessFault) {
                        // login failed, to get the error code call fault.getCode()
                        Toast.makeText(context!!, fault.message, Toast.LENGTH_LONG).show();
                    }
                })

        }

    }

}
