package com.example.aruuke_hw2_6m.data.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.aruuke_hw2_6m.utils.Resource
import com.example.aruuke_hw2_6m.utils.showToast

abstract class BaseFragment<binding : ViewBinding> : Fragment() {

    private var _binding: binding? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        setupObservers()
    }

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): binding

    abstract fun setupRecycler()

    abstract fun setupObservers()

    protected fun <T> resourceHandler(
        resource: Resource<T>,
        success: (T?) -> Unit
    ) {
        when (resource) {
            is Resource.Error -> showToast(resource.message)
            is Resource.Success -> success(resource.data)
            is Resource.Loading -> {}
        }
    }
}