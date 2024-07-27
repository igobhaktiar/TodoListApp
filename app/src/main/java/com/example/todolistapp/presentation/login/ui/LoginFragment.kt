package com.example.todolistapp.presentation.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolistapp.R
import com.example.todolistapp.databinding.FragmentLoginBinding
import com.example.todolistapp.presentation.login.viewmodel.LoginState
import com.example.todolistapp.presentation.login.viewmodel.LoginViewModel
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    // Inflate the layout for this fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize the action
        initAction()
    }

    // Function to initialize the action
    private fun initAction() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            loginAction(email, password)
        }
    }

    private fun loginAction(email: String, password: String) {
        viewModel.login(email, password)
        viewModel.loginState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoginState.Loading -> {
                    showProgressBar()
                }

                is LoginState.Success -> {
                    navigateToHomeScreen()
                }

                is LoginState.Error -> {
                    hideProgressBar()
                }
            }
        }
    }

    // Function to navigate to the home screen
    private fun navigateToHomeScreen() {
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }

    // progress bar visibility
    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

}