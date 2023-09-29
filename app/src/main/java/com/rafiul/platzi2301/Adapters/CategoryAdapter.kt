package com.rafiul.platzi2301.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rafiul.platzi2301.R
import com.rafiul.platzi2301.databinding.ItemCategoryBinding
import com.rafiul.platzi2301.models.category.ResponseCategory
import java.util.Objects

class CategoryAdapter(private var listener: Listener) :
    ListAdapter<ResponseCategory, CategoryAdapter.CategoryViewHolder>(COMPARATOR) {

    interface Listener {
        fun categoryClick(categoryID: Int)
    }

    class CategoryViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<ResponseCategory>() {
            override fun areItemsTheSame(
                oldItem: ResponseCategory,
                newItem: ResponseCategory
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ResponseCategory,
                newItem: ResponseCategory
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        //setting up the value of the view holder
        getItem(position).let { response ->
            holder.binding.apply {
                tvCategoryId.text = response.id.toString()
                tvCategoryName.text = response.name
                tvCategoryCreated.text = "Created At: ${response.creationAt}"
                tvCategoryUpdated.text = "Updated At: ${response.updatedAt}"

                imvCategoryImage.load(response.image) {
                    placeholder(R.drawable.placeholder)
                    error(R.drawable.errorplaceholder)
                    crossfade(true)
                }
            }
        }

        //now making the itemView clickable

        holder.itemView.setOnClickListener { view ->
            view.id.let { listener.categoryClick(it) }
        }


    }
}