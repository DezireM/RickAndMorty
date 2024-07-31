package com.example.aruuke_hw2_6m.ui.fragment.cartoon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aruuke_hw2_6m.data.base.fragment.BaseFragment
import com.example.aruuke_hw2_6m.data.network.model.Character
import com.example.aruuke_hw2_6m.databinding.FragmentCartoonBinding
import com.example.aruuke_hw2_6m.utils.Resource
import com.example.aruuke_hw2_6m.utils.gone
import com.example.aruuke_hw2_6m.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartoonFragment : BaseFragment<FragmentCartoonBinding>() {

    private val viewModel by viewModel<CartoonViewModel>()

    private val cartoonAdapter by lazy {
        CartoonAdapter { character ->
            onClicked(character)
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCartoonBinding {
        return FragmentCartoonBinding.inflate(inflater, container, false)
    }

    override fun setupRecycler() {
        setupRecyclerView()
    }

    override fun setupObservers() {
        viewModel.characters.observe(viewLifecycleOwner) { resource ->
            binding.progressBar.apply { if (resource is Resource.Loading) visible() else gone()
            }

            resourceHandler(resource) { data ->
                cartoonAdapter.submitList(data)
            }
        }
    }

    private fun setupRecyclerView() = with(binding.rvCartoon) {
        layoutManager = LinearLayoutManager(context)
        adapter = cartoonAdapter
    }

    private fun onClicked(character: Character) {
        val action = CartoonFragmentDirections.actionCartoonFragmentToDetailFragment(character.id)
        findNavController().navigate(action)
    }
}