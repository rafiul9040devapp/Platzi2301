package com.rafiul.platzi2301.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.rafiul.platzi2301.R
import com.rafiul.platzi2301.databinding.ItemProductBinding
import com.rafiul.platzi2301.models.product.ResponseProduct

class ProductAdapter(private var listener: Listener) :
    ListAdapter<ResponseProduct, ProductAdapter.ProductViewHolder>(COMPARATOR) {

    interface Listener {
        fun productClick(productID: Int)
    }

    class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)


    companion object {

        val COMPARATOR = object : DiffUtil.ItemCallback<ResponseProduct>() {
            override fun areItemsTheSame(
                oldItem: ResponseProduct,
                newItem: ResponseProduct
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ResponseProduct,
                newItem: ResponseProduct
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position).let {
            holder.binding.titleTextView.text = it.title
            holder.binding.priceTextView.text = "Price: $${it.price}"
            holder.binding.descriptionTextView.text = it.description



//            it.images?.let {
//                holder.binding.apply {
//                    it[0]?.apply { productImageView1.load(this) }
//                    it[1]?.apply { productImageView2.load(this) }
//                    it[2]?.apply { productImageView3.load(this) }
//                }
//            }

            it.images?.get(0)?.let { image1 ->
                holder.binding.productImageView1.load(image1) {
                    placeholder(R.drawable.placeholder)
                    error(R.drawable.errorplaceholder)
                    crossfade(true)
                }
            }

            it.images?.get(1)?.let { image1 ->
                holder.binding.productImageView2.load(image1) {
                    placeholder(R.drawable.placeholder)
                    error(R.drawable.errorplaceholder)
                    crossfade(true)
                }
            }

            it.images?.get(2)?.let { image1 ->
                holder.binding.productImageView3.load(image1) {
                    placeholder(R.drawable.placeholder)
                    error(R.drawable.errorplaceholder)
                    crossfade(true)
                }
            }

            it.category?.let { category ->
                holder.binding.categoryImageView.load(category.image) {
                    placeholder(R.drawable.placeholder)
                    error(R.drawable.errorplaceholder)
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
                holder.binding.categoryTextView.text = category.name
            }


            holder.itemView.setOnClickListener { _ -> it.id?.let { it1 -> listener.productClick(it1) } }

        }
    }
}