package com.example.aruuke_hw2_6m.ui.fragment.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.aruuke_hw2_6m.R
import com.example.aruuke_hw2_6m.data.model.Character

class DetailAdapter(private var list: List<Character>) :
    RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_currentName)
        val location: TextView = itemView.findViewById(R.id.tv_currentLocation)
        val linearLayout: LinearLayout = itemView.findViewById(R.id.expandableContainer)
        val cardView: CardView = itemView.findViewById(R.id.cardView)

        fun expandOrCollapse() {
            val isExpanded = list[adapterPosition].isExpandable
            name.visibility = if (isExpanded) View.VISIBLE else View.GONE
            location.visibility = if (isExpanded) View.VISIBLE else View.GONE
            linearLayout.visibility = if (isExpanded) View.VISIBLE else View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail, parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val character = list[position]
        holder.name.text = character.name
        holder.location.text = character.location.name

        holder.expandOrCollapse()

        holder.cardView.setOnClickListener {
            character.isExpandable = !character.isExpandable
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}