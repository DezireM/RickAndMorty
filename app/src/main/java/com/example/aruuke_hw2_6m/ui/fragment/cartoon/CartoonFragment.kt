package com.example.aruuke_hw2_6m.ui.fragment.cartoon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aruuke_hw2_6m.databinding.FragmentCartoonBinding
import com.example.aruuke_hw2_6m.mvvm.CartoonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartoonFragment : Fragment() {

    private val binding by lazy {
        FragmentCartoonBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[CartoonViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCartoon.layoutManager = LinearLayoutManager(context)

        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            binding.rvCartoon.adapter = CartoonAdapter(characters)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}