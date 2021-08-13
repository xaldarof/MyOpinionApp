package com.example.myopinion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myopinion.databinding.FragmentSignInBinding
import com.example.myopinion.viewmodel.SignInFragmentViewModel
import com.example.myopinion.viewmodel.SignInViewModelFactory
import com.google.firebase.auth.FirebaseAuth

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

        binding.signInBtn.setOnClickListener {
            login = binding.loginInputText.text.toString()
            password = binding.passwordInputText.text.toString()
            name = binding.nameEditText.text.toString()
            surname = binding.surnameEditText.text.toString()

            createUser(login, password,name,surname)

        }
        viewModel = ViewModelProvider(this, SignInViewModelFactory(requireActivity()))
            .get(SignInFragmentViewModel::class.java)

        return binding.root
    }


    private fun createUser(login: String, password: String,name:String,surname:String) {
        viewModel.createUser(login, password,name,surname)
    }
}