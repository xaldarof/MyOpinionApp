package com.example.myopinion.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.myopinion.R
import com.example.myopinion.databinding.FragmentSignInBinding
import com.example.myopinion.netReq.UserCreator
import com.example.myopinion.netReq.UserCreatorProvider
import com.example.myopinion.viewmodel.SignInFragmentViewModel
import com.example.myopinion.viewmodel.SignInViewModelFactory
import com.example.myopinion.viewmodel.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.log

class SignInFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: FragmentSignInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSignInBinding.inflate(inflater,container,false)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.signInBtn.setOnClickListener {
            val login = binding.loginInputText.text.toString()
            val password = binding.passwordInputText.text.toString()
            createUser(login,password)
        }
        return binding.root
    }

   private fun createUser(login:String,password:String){
        val viewModel = ViewModelProvider(this,SignInViewModelFactory(login,password,requireActivity()))
            .get(SignInFragmentViewModel::class.java)
        viewModel.createUser()
    }
}