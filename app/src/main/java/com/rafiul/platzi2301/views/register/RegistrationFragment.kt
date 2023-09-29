package com.rafiul.platzi2301.views.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rafiul.platzi2301.R
import com.rafiul.platzi2301.databinding.FragmentRegistrationBinding
import com.rafiul.platzi2301.models.register.RequestRegister
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private val viewmodel: RegisterViewModel by viewModels()

    private lateinit var binding: FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        viewmodel.registerResponse.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                binding.progressBar.visibility = View.GONE
                binding.nameEdt.setText("")
                binding.emailEdt.setText("")
                binding.passwordEdt.setText("")
                Toast.makeText(requireContext(), "Registration SUCCESSFUL!!!!", Toast.LENGTH_LONG)
                    .show()
                Log.d("TAG", "DATA: ${it.body()}")
            }
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registrationBtn.setOnClickListener {

            binding.progressBar.visibility = View.VISIBLE

            val name = binding.nameEdt.text.toString().trim()
            val email = binding.emailEdt.text.toString().trim()
            val password = binding.passwordEdt.text.toString().trim()

            handleRegistration(name, email, password)
        }

        binding.signInBtn.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }
    }

    private fun handleRegistration(name: String, email: String, password: String) {
        val request: RequestRegister = RequestRegister(
            name = name,
            email = email,
            password = password,
            avatar = "https://dmarkcy.com/images/team/web/mehedi.png"
        )

        viewmodel.getRegisterFromVM(request)
    }

}