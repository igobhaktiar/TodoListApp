package com.example.todolistapp.presentation.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolistapp.R
import com.example.todolistapp.databinding.FragmentRegisterBinding
import com.example.todolistapp.presentation.utils.GenericState
import com.example.todolistapp.presentation.utils.ProgressBarHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
    }

    private fun initAction() {
        binding.btnRegister.setOnClickListener {
            ProgressBarHandler(binding.progressBar).showProgressBar()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val username = binding.etUsername.text.toString()

            registerAction(email, password, username)
        }

        binding.tvLogin.setOnClickListener {
            ProgressBarHandler(binding.progressBar).hideProgressBar()
            navigateToLoginFragment()
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            binding.tvError.text = errorMessage
        }
    }

    private fun registerAction(email: String, password: String, username: String) {
        viewModel.register(email, password, username)
        viewModel.isSuccess.observe(viewLifecycleOwner) { isSuccess ->
            println("isSuccess: $isSuccess")
            if (isSuccess == true) {
                ProgressBarHandler(binding.progressBar).hideProgressBar()
                navigateToHomeFragment()
            } else {
                ProgressBarHandler(binding.progressBar).hideProgressBar()
                binding.tvError.visibility = View.VISIBLE
            }
        }
    }

    // navigate to login fragment
    private fun navigateToLoginFragment() {
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }

    // navigate to home fragment
    private fun navigateToHomeFragment() {
        findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
    }

}