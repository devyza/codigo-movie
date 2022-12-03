package com.example.codigotravel.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.codigotravel.R
import com.example.codigotravel.databinding.FragmentLoginBinding

class LoginFragment: BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findNavController().apply {
            binding.btnCreateAccount.setOnClickListener {
                navigate(R.id.action_loginFragment_to_signUpFragment)
            }
            binding.btnEmailLogin.setOnClickListener {
                navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
    }
}