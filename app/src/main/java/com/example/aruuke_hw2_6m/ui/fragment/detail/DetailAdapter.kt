package com.example.aruuke_hw2_6m.ui.fragment.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aruuke_hw2_6m.R
import com.example.aruuke_hw2_6m.data.network.model.Character

class DetailAdapter : ListAdapter<Character, DetailAdapter.DetailViewHolder>(CharacterDiffCallback()) {

    inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_currentName)
        val location: TextView = itemView.findViewById(R.id.tv_currentLocation)
        val linearLayout: LinearLayout = itemView.findViewById(R.id.expandableContainer)
        val cardView: CardView = itemView.findViewById(R.id.cardView)

        fun bind(character: Character) {
            name.text = character.name
            location.text = character.location.name

            val isExpanded = character.isExpandable
            name.visibility = if (isExpanded) View.VISIBLE else View.GONE
            location.visibility = if (isExpanded) View.VISIBLE else View.GONE
            linearLayout.visibility = if (isExpanded) View.VISIBLE else View.GONE

            cardView.setOnClickListener {
                character.isExpandable = !character.isExpandable
                notifyItemChanged(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail, parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }
}