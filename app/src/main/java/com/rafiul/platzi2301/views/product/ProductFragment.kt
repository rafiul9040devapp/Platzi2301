package com.rafiul.platzi2301.views.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingDataAdapter
import com.rafiul.platzi2301.Adapters.ProductAdapter
import com.rafiul.platzi2301.Adapters.ProductAdapterPaging
import com.rafiul.platzi2301.R
import com.rafiul.platzi2301.databinding.FragmentProductBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductFragment : Fragment(), ProductAdapterPaging.Listener {

    private val viewModel: ProductViewModel by activityViewModels()

    private lateinit var binding: FragmentProductBinding

    lateinit var adapter: ProductAdapterPaging
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(inflater, container, false)
        viewModel.getAllProductsFromVM()

        adapter = ProductAdapterPaging(this)
        binding.productRecView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//
//        viewModel.productResponse.observe(viewLifecycleOwner) {
//            if (it.isSuccessful) {
//                val adapter = ProductAdapter(this)
//                adapter.submitList(it.body())
//                binding.productRecView.adapter = adapter
//            }
//        }

        viewModel.data.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.submitData(it)

            }
        }

        binding.apply {
            profileBTN.setOnClickListener { findNavController().navigate(R.id.action_productFragment_to_profileFragment) }
            productUploadBTN.setOnClickListener { findNavController().navigate(R.id.action_productFragment_to_productUploadFragment) }
        }

    }

    override fun productClick(productID: Int) {
        viewModel.setClickedProductID(productID)
        findNavController().navigate(R.id.action_productFragment_to_productDetailsFragment)
    }

}