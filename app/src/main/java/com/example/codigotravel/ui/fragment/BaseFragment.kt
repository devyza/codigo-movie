package com.example.codigotravel.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.codigotravel.R
import com.example.codigotravel.data.repository.MainRepository
import com.example.codigotravel.ui.component.ProgressDialog
import com.example.codigotravel.ui.viewmodel.MainViewModel
import com.example.codigotravel.ui.viewmodel.ViewModelFactory

open class BaseFragment<VB: ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
): Fragment() {

    internal val mainViewModel: MainViewModel by activityViewModels {  MainViewModel.Factory }

    private var _binding: VB? = null
    internal val binding get() = _binding!!

    internal lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        progressDialog = ProgressDialog(requireContext())
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(MainRepository())
    }

    open fun setUpObservers() {
        mainViewModel.token.observe(viewLifecycleOwner) { token ->
            findNavController().apply {
                if (token != null) {
                    val startDes = R.id.homeFragment
                    graph.setStartDestination(startDes)
                    navigate(startDes)
                }
            }
        }
    }
}