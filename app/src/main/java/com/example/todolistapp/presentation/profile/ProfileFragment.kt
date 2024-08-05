package com.example.todolistapp.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolistapp.R
import com.example.todolistapp.base.BaseFragment
import com.example.todolistapp.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private val viewModel: ProfileViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false)

    override fun initUI() {
        // Check if the user is logged in
        if (!viewModel.isLoggedIn()) {
            // If the user is not logged in, navigate to login screen
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }
        // Get user data
        val user = viewModel.getUserData()
        // Set user data to the UI
        binding.tvEmail.text = user.email
        binding.tvName.text = user.username
    }

    override fun initAction() {
        binding.btnLogout.setOnClickListener {
            // Handle log out button click
            viewModel.logOut()
            // Navigate to login screen
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }
    }

    override fun initProcess() {
    }

    override fun observeData() {
    }

}