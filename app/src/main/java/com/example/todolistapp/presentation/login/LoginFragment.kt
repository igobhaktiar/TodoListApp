package com.example.todolistapp.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolistapp.R
import com.example.todolistapp.base.BaseFragment
import com.example.todolistapp.databinding.FragmentLoginBinding
import com.example.todolistapp.presentation.utils.GenericState
import com.example.todolistapp.presentation.utils.ProgressBarHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)

    override fun initUI() {
    }

    // Function to initialize the action
    override fun initAction() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            loginAction(email, password)
        }

        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    override fun initProcess() {
    }

    override fun observeData() {
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

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            binding.tvFailedLogin.text = errorMessage
            println("Error: $errorMessage")
        }
    }

    private fun loginAction(email: String, password: String) {
        viewModel.login(email, password)
    }

}