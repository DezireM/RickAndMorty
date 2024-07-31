package com.example.aruuke_hw2_6m.ui.fragment.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.aruuke_hw2_6m.R
import com.example.aruuke_hw2_6m.data.base.fragment.BaseFragment
import com.example.aruuke_hw2_6m.data.network.model.Character
import com.example.aruuke_hw2_6m.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val viewModel by viewModel<DetailViewModel>()

    private lateinit var adapter: DetailAdapter

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

    override fun setupRecycler() {
        setupRecyclerView()

        val characterId = arguments?.getInt("characterId") ?: 0
        viewModel.fetchCharacterDetails(characterId)
    }

    override fun setupObservers() {
        viewModel.characterDetails.observe(viewLifecycleOwner) { resource ->
            resourceHandler(resource) { data ->
                data?.let {
                    bind(it)
                    adapter.submitList(listOf(it))
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvSeasons.layoutManager = LinearLayoutManager(context)
        adapter = DetailAdapter()
        binding.rvSeasons.adapter = adapter
    }

    private fun bind(character: Character) = with(binding) {
        tvName.text = character.name
        tvStatus.text = "${character.status} - ${character.species}"
        tvCurrentLocation.text = character.location.name
        tvCurrentGender.text = character.gender

        val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            .format(Date())
        tvCurrentTime.text = currentTime

        Glide.with(root.context)
            .load(character.image)
            .into(binding.imgDetail)

        val circleDrawable = when (character.status) {
            "Alive" -> R.drawable.circle_green
            "Dead" -> R.drawable.circle_red
            else -> R.drawable.circle_grey
        }
        imgCircle.setImageResource(circleDrawable)
    }
}