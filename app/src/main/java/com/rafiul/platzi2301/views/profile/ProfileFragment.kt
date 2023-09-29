package com.rafiul.platzi2301.views.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import coil.transform.CircleCropTransformation
import com.rafiul.platzi2301.R
import com.rafiul.platzi2301.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProfileFromVM()

        viewModel.profileResponse.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                response.body()?.let { profile ->
                    binding.avatarImageView.load(profile.avatar) {
                        placeholder(R.drawable.placeholder)
                        error(R.drawable.errorplaceholder)
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }
                    binding.nameTextView.text = profile.name
                    binding.emailTextView.text = profile.email
                    binding.roleTextView.text = profile.role
                    binding.creationDateTextView.text = "Creation Date: ${profile.creationAt}"
                    binding.updateDateTextView.text = "Last Updated: ${profile.updatedAt}"
                }
            }
        }

    }

}