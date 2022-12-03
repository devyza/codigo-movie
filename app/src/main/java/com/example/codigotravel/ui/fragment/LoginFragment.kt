package com.example.codigotravel.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.codigotravel.R
import com.example.codigotravel.databinding.FragmentLoginBinding
import com.example.codigotravel.ui.ext.getString
import com.example.codigotravel.ui.ext.showToast
import com.example.codigotravel.ui.util.InputResult
import com.example.codigotravel.ui.util.InputValidator
import com.example.codigotravel.ui.viewmodel.LoginViewModel

class LoginFragment: BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
) {
    private val loginFragmentViewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()

        findNavController().apply {

            binding.btnCreateAccount.setOnClickListener {
                navigate(R.id.action_loginFragment_to_signUpFragment)
            }

            binding.btnEmailLogin.setOnClickListener {
                if (checkInputs()) {
                    loginFragmentViewModel.login(
                        binding.txtUserName.getString(),
                        binding.txtPassword.getString()
                    )
                }
            }
        }
    }

    override fun setUpObservers() {
        super.setUpObservers()

        loginFragmentViewModel.message.observe(viewLifecycleOwner) {
            it?.let {
                showToast(it)
                loginFragmentViewModel.resetMessage()
            }
        }
        loginFragmentViewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) progressDialog.show()
            else progressDialog.hide()
        }
    }

    private fun checkInputs(): Boolean {

        val usernameResult = InputValidator.checkInput(binding.txtUserName.getString())
        binding.txtUserName.error = when(usernameResult) {
            InputResult.EMPTY -> getString(R.string.error_input_require)
            else -> null
        }

        val passwordResult = InputValidator.checkInput(binding.txtPassword.getString())
        binding.txtPassword.error = when(passwordResult) {
            InputResult.EMPTY -> getString(R.string.error_input_require)
            else -> null
        }

        return InputValidator.isAllValid(usernameResult, passwordResult)
    }
}