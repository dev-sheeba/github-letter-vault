package com.hfad.lettervault.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.lettervault.data.Letter
import com.hfad.lettervault.databinding.RecyclerviewItemBinding

class LetterAdapter : ListAdapter<Letter, LetterAdapter.LetterViewHolder>(ListsComparator()) {

    inner class LetterViewHolder(private val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(letter: Letter) {
            binding.apply {
                title.text = letter.title
                subTitle.text = letter.subTitle
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val binding = RecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LetterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ListsComparator : DiffUtil.ItemCallback<Letter>() {
        override fun areItemsTheSame(oldItem: Letter, newItem: Letter): Boolean =
            oldItem.letterId == newItem.letterId

        override fun areContentsTheSame(oldItem: Letter, newItem: Letter): Boolean =
            oldItem == newItem
    }
}