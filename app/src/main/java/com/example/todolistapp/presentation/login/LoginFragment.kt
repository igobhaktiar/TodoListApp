package com.example.todolistapp.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolistapp.R
import com.example.todolistapp.databinding.FragmentLoginBinding
import com.example.todolistapp.presentation.utils.GenericState
import com.example.todolistapp.presentation.utils.ProgressBarHandler
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

        viewModel.loginState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is GenericState.Loading -> {
                    ProgressBarHandler(binding.progressBar).showProgressBar()
                }

                is GenericState.Success -> {
                    ProgressBarHandler(binding.progressBar).hideProgressBar()
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }

                is GenericState.Error -> {
                    ProgressBarHandler(binding.progressBar).hideProgressBar()
                    binding.tvFailedLogin.visibility = View.VISIBLE
                }
            }
        }
    }

    // Function to initialize the action
    private fun initAction() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            loginAction(email, password)
        }

        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun loginAction(email: String, password: String) {
        viewModel.login(email, password)
    }

}