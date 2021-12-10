package com.edw88.baricharafriends.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.edw88.baricharafriends.R
import com.edw88.baricharafriends.databinding.LoginFragmentBinding
import com.edw88.baricharafriends.utils.isEmailValid

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginBinding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginBinding = LoginFragmentBinding.inflate(inflater, container, false)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        loginViewModel.onUserLoggedIn.observe(viewLifecycleOwner,{result ->
            onUserLoggedInSubscribe(result)
        })

        return loginBinding.root
    }

    private fun onUserLoggedInSubscribe(result: Boolean?) {
        result?.let { isLoggedIn ->
            if(isLoggedIn){
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToNavigationList())
            }else
                Toast.makeText(context,"Error en el inicio de sesión", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(loginBinding){
            loginButton.setOnClickListener{

                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()

                if (email.isEmpty() || password.isEmpty())
                    Toast.makeText(context, "Debe ingresar el correo y contraseña", Toast.LENGTH_SHORT).show()
                else
                    if(!isEmailValid(email))
                        Toast.makeText(context, "Correo ingresado incorrectamente", Toast.LENGTH_SHORT).show()
                else
                loginViewModel.login(email, password)
            }

            regsiterTextView.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }
    }
}