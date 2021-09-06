package com.opinion.myopinion.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.opinion.myopinion.R
import com.opinion.myopinion.databinding.FragmentSignInBinding
import com.opinion.myopinion.helpers.TextToSpanableText
import com.opinion.myopinion.viewmodel.SignInFragmentViewModel
import com.opinion.myopinion.viewmodel.SignInViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.opinion.myopinion.tools.TopSnackBarShower
import com.opinion.myopinion.utils.Text

class SignInFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: FragmentSignInBinding
    private lateinit var viewModel: SignInFragmentViewModel
    private var login = ""
    private var password = ""
    private var name = ""
    private var surname = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.signInBtn.isEnabled = false
        val textToSpannableText = TextToSpanableText(requireContext())
        textToSpannableText.init(binding.checkbox, binding.signInBtn, binding.politicsTv)

        binding.signInBtn.setOnClickListener {
            login = binding.loginInputText.text.toString()
            password = binding.passwordInputText.text.toString()
            name = binding.nameEditText.text.toString()
            surname = binding.surnameEditText.text.toString()

            if (login.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty()) {
                if (!login.endsWith("@gmail.com") || password.length < 8) {
                    TopSnackBarShower.show(binding.registerLayout,requireActivity(),Text.error)
                } else {
                    createUser(login, password, name, surname)
                }
            }
        }

        viewModel = ViewModelProvider(this, SignInViewModelFactory(requireActivity()))
            .get(SignInFragmentViewModel::class.java)

        return binding.root
    }

    private fun createUser(login: String, password: String, name: String, surname: String) {
        viewModel.createUser(login, password, name, surname)
    }
}