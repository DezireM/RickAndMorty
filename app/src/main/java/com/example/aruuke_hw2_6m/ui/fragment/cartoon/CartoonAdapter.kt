package com.example.aruuke_hw2_6m.ui.fragment.cartoon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aruuke_hw2_6m.R
import com.example.aruuke_hw2_6m.data.model.Character
import com.example.aruuke_hw2_6m.databinding.ItemCartoonBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CartoonAdapter(private var cartoonList: List<Character>) :
    RecyclerView.Adapter<CartoonAdapter.CartoonViewHolder>() {

    fun updateData(newCartoonList: List<Character>) {
        cartoonList = newCartoonList
        notifyDataSetChanged()
    }

    inner class CartoonViewHolder(private val binding: ItemCartoonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cartoonCharacter: Character) {
            binding.tvName.text = cartoonCharacter.name
            binding.tvStatus.text = "${cartoonCharacter.status} - ${cartoonCharacter.species}"
            binding.tvCurrentLocation.text = cartoonCharacter.location.name

            val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
                .format(Date())
            binding.tvCurrentTime.text = currentTime

            Glide.with(binding.root.context)
                .load(cartoonCharacter.image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imageView)

            val circleDrawable = when (cartoonCharacter.status) {
                "Alive" -> R.drawable.circle_green
                "Dead" -> R.drawable.circle_red
                else -> R.drawable.circle_grey
            }
            binding.imgCircle.setImageResource(circleDrawable)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartoonViewHolder {
        val binding = ItemCartoonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartoonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartoonViewHolder, position: Int) {
        holder.bind(cartoonList[position])
    }

    override fun getItemCount(): Int = cartoonList.size
}