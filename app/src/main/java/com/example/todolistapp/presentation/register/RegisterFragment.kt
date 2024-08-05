package com.example.todolistapp.presentation.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolistapp.R
import com.example.todolistapp.base.BaseFragment
import com.example.todolistapp.databinding.FragmentRegisterBinding
import com.example.todolistapp.presentation.utils.ProgressBarHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    private val viewModel: RegisterViewModel by viewModels()


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater, container, false)

    override fun initUI() {}

    override fun initAction() {
        binding.btnRegister.setOnClickListener {
            ProgressBarHandler(binding.progressBar).showProgressBar()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val username = binding.etUsername.text.toString()

            registerAction(email, password, username)
        }

        binding.tvLogin.setOnClickListener {
            ProgressBarHandler(binding.progressBar).hideProgressBar()
            navigateToLogin()
        }
    }

    override fun initProcess() {}

    override fun observeData() {
        viewModel.isSuccess.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess == true) {
                ProgressBarHandler(binding.progressBar).hideProgressBar()
                navigateToLogin()
            } else {
                ProgressBarHandler(binding.progressBar).hideProgressBar()
                binding.tvError.visibility = View.VISIBLE
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            binding.tvError.text = errorMessage
        }
    }

    private fun registerAction(email: String, password: String, username: String) {
        viewModel.register(email, password, username)
    }

    private fun navigateToLogin() {
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }

}