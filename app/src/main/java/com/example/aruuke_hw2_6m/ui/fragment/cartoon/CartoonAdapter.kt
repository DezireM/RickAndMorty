package com.example.aruuke_hw2_6m.ui.fragment.cartoon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aruuke_hw2_6m.R
import com.example.aruuke_hw2_6m.data.model.Character
import com.example.aruuke_hw2_6m.databinding.ItemCartoonBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CartoonAdapter(
    private val onClick: (Character) -> Unit
) : ListAdapter<Character, CartoonAdapter.CartoonViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartoonViewHolder {
        val binding = ItemCartoonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartoonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartoonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CartoonViewHolder(private val binding: ItemCartoonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) = with(binding) {
            tvName.text = character.name
            tvStatus.text = "${character.status} - ${character.species}"
            tvCurrentLocation.text = character.location.name
            tvCurrentCreated.text = character.created

            Glide.with(root.context)
                .load(character.image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imgCartoon)

            val circleDrawable = when (character.status) {
                "Alive" -> R.drawable.circle_green
                "Dead" -> R.drawable.circle_red
                else -> R.drawable.circle_grey
            }
            imgCircle.setImageResource(circleDrawable)

            root.setOnClickListener {
                onClick(character)
            }
        }
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem == newItem
}