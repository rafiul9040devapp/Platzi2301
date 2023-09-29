package com.rafiul.platzi2301.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.rafiul.platzi2301.R
import com.rafiul.platzi2301.databinding.ItemProductBinding
import com.rafiul.platzi2301.models.product.ResponseProduct

class ProductAdapterPaging(var listener: Listener) :
    PagingDataAdapter<ResponseProduct, ProductAdapterPaging.ProductViewHolder>(COMPARATOR) {

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
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        getItem(position)?.let {
            holder.binding.apply {
                titleTextView.text = "${it.id} ${it.title}"
                descriptionTextView.text = it.description
                priceTextView.text = "Price: $${it.price}"

                for (i in it.images!!.indices) when (i) {
                    0 -> productImageView1.load(it.images[i]) {
                        placeholder(R.drawable.placeholder)
                        error(R.drawable.errorplaceholder)
                        crossfade(true)
                    }

                    1 -> productImageView2.load(it.images[i]) {
                        placeholder(R.drawable.placeholder)
                        error(R.drawable.errorplaceholder)
                        crossfade(true)
                    }

                    2 -> productImageView3.load(it.images[i]) {
                        placeholder(R.drawable.placeholder)
                        error(R.drawable.errorplaceholder)
                        crossfade(true)
                    }
                }

                it.category?.let { ctg ->
                    categoryTextView.text = ctg.name
                    categoryImageView.load(ctg.image!!) {
                        placeholder(R.drawable.placeholder)
                        error(R.drawable.errorplaceholder)
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }
                }
            }

            holder.itemView.setOnClickListener {
                listener.productClick(it.id)
            }
        }
    }
}