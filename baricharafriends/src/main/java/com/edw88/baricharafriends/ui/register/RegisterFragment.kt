package com.edw88.baricharafriends.ui.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.edw88.baricharafriends.R
import com.edw88.baricharafriends.databinding.RegisterFragmentBinding
import com.edw88.baricharafriends.ui.login.LoginViewModel

class RegisterFragment : Fragment() {

   private lateinit var registerViewModel: RegisterViewModel
   private lateinit var registerBinding: RegisterFragmentBinding
   private lateinit var email: String
   private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        registerBinding = RegisterFragmentBinding.inflate(inflater, container, false)
        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        registerViewModel.onUserCreated.observe(viewLifecycleOwner,{result ->
            onUserCreatedSubscribe(result)

        })

        return registerBinding.root

    }

    private fun onUserCreatedSubscribe(result: Boolean?) {
        result?.let { isRegister->
            if (isRegister) {
                Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                registerViewModel.createUserAccount(email,username)
                //findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
            }else
                Toast.makeText(context, "Error en el regsitro", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(registerBinding){
            registerButton.setOnClickListener{
                email = emailEditText.text.toString()
                username = usernameEditText.text.toString()
                val password = passwordEditText.text.toString()

                registerViewModel.register(email,password)
            }
        }
    }

}