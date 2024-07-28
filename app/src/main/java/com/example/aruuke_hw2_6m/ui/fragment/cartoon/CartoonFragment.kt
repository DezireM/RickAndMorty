package com.example.aruuke_hw2_6m.ui.fragment.cartoon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aruuke_hw2_6m.data.model.Character
import com.example.aruuke_hw2_6m.databinding.FragmentCartoonBinding
import com.example.aruuke_hw2_6m.utils.Resource
import com.example.aruuke_hw2_6m.utils.gone
import com.example.aruuke_hw2_6m.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartoonFragment : Fragment() {

    private val binding by lazy {
        FragmentCartoonBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModel<CartoonViewModel>()

    private val cartoonAdapter by lazy {
        CartoonAdapter {
            character -> onClicked(character)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            when (characters) {
                is Resource.Error -> {
                    showToast(characters.message)
                    showProgressBar(false)
                }

                is Resource.Loading -> showProgressBar(true)

                is Resource.Success -> {
                    showProgressBar(false)
                    cartoonAdapter.submitList(characters.data)
                }
            }
        }
    }

    private fun setupRecyclerView() = with(binding.rvCartoon) {
        layoutManager = LinearLayoutManager(context)
        adapter = cartoonAdapter
    }

    private fun showProgressBar(isVisible: Boolean) = with(binding) {
        if (isVisible) progressBar.visible() else progressBar.gone()
    }

    private fun showToast(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun onClicked(character: Character) {
        val action = CartoonFragmentDirections.actionCartoonFragmentToDetailFragment(character.id)
        findNavController().navigate(action)
    }
}