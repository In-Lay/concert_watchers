package com.inlay.login.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.inlay.login.R
import com.inlay.login.databinding.FragmentLoginBinding
import com.inlay.login.di.getOrCreateLoginScope
import com.inlay.login.presentation.viewModel.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    private val loginViewModel: LoginViewModel = getOrCreateLoginScope().get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_login, container, false)
        binding.viewModel = loginViewModel

        binding.lifecycleOwner = this@LoginFragment
        binding.executePendingBindings()

        loginViewModel.setAuth(Firebase.auth)
        loginViewModel.setCurrentUser(Firebase.auth.currentUser)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginViewModel.warningText.observe(viewLifecycleOwner) {
            binding.textWarning.text = it
        }

        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it).setTitle(R.string.dialog_title)
        }
        builder?.setPositiveButton(R.string.dissmiss) { dialog, _ ->
            dialog.dismiss()
        }
        loginViewModel.error.observe(viewLifecycleOwner) {
            Log.d("LoginTag", "error block: $it")
            if (it != null) {
                builder?.setMessage(it)
                builder?.create()?.show()
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }
}