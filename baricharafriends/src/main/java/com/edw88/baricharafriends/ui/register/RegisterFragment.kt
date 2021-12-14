package com.edw88.baricharafriends.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.edw88.baricharafriends.databinding.RegisterFragmentBinding

class RegisterFragment : Fragment() {

    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var registerBinding: RegisterFragmentBinding
    private lateinit var email: String
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        registerBinding = RegisterFragmentBinding.inflate(inflater, container, false)
        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        return registerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerViewModel.onUserRegistered.observe(viewLifecycleOwner, { result ->
            onUserRegisteredSubscribe(result)
        })

        registerViewModel.onUserCreated.observe(viewLifecycleOwner, { result ->
            onUserCreatedSubscribe(result)

        })

        with(registerBinding) {
            registerButton.setOnClickListener {
                email = emailEditText.text.toString()
                username = usernameEditText.text.toString()
                val password = passwordEditText.text.toString()

                registerViewModel.register(email, password)
            }
        }
    }

    private fun onUserRegisteredSubscribe(result: String?) {
        if (result.equals("Usuario registrado exitosamente"))
            registerViewModel.createUserAccount(email, username)
        //    findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        else
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
    }

    private fun onUserCreatedSubscribe(result: String?) {
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
        if (result.equals("Usuario creado de forma exitosa")) {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }
    }
}
