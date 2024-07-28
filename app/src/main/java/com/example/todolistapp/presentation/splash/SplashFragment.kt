package com.example.todolistapp.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolistapp.R
import com.example.todolistapp.base.BaseFragment
import com.example.todolistapp.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    private val viewModel: SplashViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSplashBinding = FragmentSplashBinding.inflate(inflater, container, false)

    override fun initUI() {
        // Function to initialize the UI
    }

    override fun initAction() {
        // Function to initialize the action
    }

    override fun initProcess() {
        // Function to initialize the process
    }

    override fun observeData() {
        // Function to observe the data
        val isLoggedIn = viewModel.isLoggedIn()
        // delay the splash screen for 2 seconds
        val splashScreenDuration = 2000L
        binding.root.postDelayed({
            if (isLoggedIn) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }
        }, splashScreenDuration)

    }
}