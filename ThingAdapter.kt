package com.example.recycler_view2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycler_view2.databinding.ItemThingsBinding

class ThingAdapter() :
    ListAdapter<Thing, ThingAdapter.ThingHolder>(DiffCallBack()) {

    var onItemClick: (thing: Thing) -> Unit = { _ -> }
    var onDeleteClick: (thing: Thing) -> Unit = { _ ->}

    inner class ThingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemThingsBinding.bind(itemView)

        fun bind(thing: Thing) = with(binding) {
            nameThing.text = thing.name
            tasteThing.text = thing.taste
            fortressThing.text = thing.fortress
            costThing.text = thing.cost

            Glide.with(itemView.context)
                .load(thing.photo)
                .centerCrop()
                .error(R.drawable.error)
                .placeholder(R.drawable.error)
                .into(picture)

            imageDelete.setOnClickListener {
                onDeleteClick(thing)
            }

            imageLike.setOnClickListener {
                onItemClick(thing)
                imageLike.setImageResource(R.drawable.favorite_2)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThingHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_things, parent, false)
        return ThingHolder(itemView)
    }

    override fun onBindViewHolder(holder: ThingHolder, position: Int) {
        holder.bind(getItem(position))

    }

}

private class DiffCallBack : DiffUtil.ItemCallback<Thing>() {
    override fun areItemsTheSame(oldItem: Thing, newItem: Thing): Boolean {
        return oldItem.photo == newItem.photo
    }

    override fun areContentsTheSame(oldItem: Thing, newItem: Thing): Boolean {
        return oldItem == newItem
    }

}