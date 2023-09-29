package com.rafiul.platzi2301.views.login

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
import com.rafiul.platzi2301.databinding.FragmentLoginBinding
import com.rafiul.platzi2301.models.login.RequestLogin
import com.rafiul.platzi2301.utils.Constants
import com.rafiul.platzi2301.utils.PrefManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var prefManager: PrefManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        viewModel.loginResponse.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                binding.progressBar.visibility = View.GONE
                binding.emailEdt.setText("")
                binding.passwordEdt.setText("")
                Log.d("TAG", "DATA: ${it.body()}")
                Toast.makeText(requireContext(), "Login SUCCESSFUL!!!", Toast.LENGTH_LONG).show()

                prefManager.savePreferences(Constants.KEY_ACCESS_TOKEN, it.body()?.accessToken!!)
                prefManager.savePreferences(Constants.KEY_REFRESH_TOKEN, it.body()?.refreshToken!!)

                findNavController().navigate(R.id.action_loginFragment_to_productFragment)

            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInBtn.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            val email = binding.emailEdt.text.toString().trim()
            val password = binding.passwordEdt.text.toString().trim()

            handleSignIn(email, password)
        }

        binding.registrationBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

    }

    private fun handleSignIn(userEmail: String, userPassword: String) {
        val request: RequestLogin = RequestLogin(email = userEmail, password = userPassword)
        viewModel.getLoginFromVM(request)
    }

}