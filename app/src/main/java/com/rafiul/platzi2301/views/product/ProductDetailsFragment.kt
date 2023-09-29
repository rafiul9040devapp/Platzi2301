package com.rafiul.platzi2301.views.product

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import coil.transform.CircleCropTransformation
import com.rafiul.platzi2301.R
import com.rafiul.platzi2301.databinding.FragmentProductDetailsBinding
import com.rafiul.platzi2301.models.product.ResponseProduct
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private val viewModel: ProductViewModel by activityViewModels()

    lateinit var binding: FragmentProductDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.productID.observe(viewLifecycleOwner) {
            viewModel.getProductByIdFromVM(it)
        }

        viewModel.productByIdResponse.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                it.body()?.let { item ->
                    setProduct(item)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setProduct(body: ResponseProduct) {
        binding.titleTextView.text = body.title
        binding.priceTextView.text = "Price: $${body.price}"
        binding.descriptionTextView.text = body.description

        body.images?.get(0)?.let { image1 ->
            binding.productImageView1.load(image1) {
                placeholder(R.drawable.placeholder)
                error(R.drawable.errorplaceholder)
                crossfade(true)
            }
        }
        body.images?.get(1)?.let { image1 ->
            binding.productImageView2.load(image1) {
                placeholder(R.drawable.placeholder)
                error(R.drawable.errorplaceholder)
                crossfade(true)
            }
        }
        body.images?.get(2)?.let { image1 ->
            binding.productImageView3.load(image1) {
                placeholder(R.drawable.placeholder)
                error(R.drawable.errorplaceholder)
                crossfade(true)
            }
        }

        body.category?.let {
            binding.categoryImageView.load(it.image) {
                placeholder(R.drawable.placeholder)
                error(R.drawable.errorplaceholder)
                crossfade(true)
                transformations(CircleCropTransformation())
            }

            binding.categoryTextView.text = it.name
        }
    }

}