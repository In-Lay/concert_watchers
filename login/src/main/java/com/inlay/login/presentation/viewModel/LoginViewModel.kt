package com.inlay.login.presentation.viewModel

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

abstract class LoginViewModel : ViewModel() {
    abstract val email: LiveData<String>
    abstract val password: LiveData<String>
    abstract val warningText: LiveData<String>

    abstract val auth: LiveData<FirebaseAuth>
    abstract val currentUser: LiveData<FirebaseUser?>
    abstract val userStateFlag: LiveData<Boolean>

    abstract val error: LiveData<String?>

    abstract fun updateEmail(email: Editable)
    abstract fun updatePassword(password: Editable)

    abstract fun setAuth(newAuth: FirebaseAuth)
    abstract fun setCurrentUser(firebaseUser: FirebaseUser?)

    abstract fun onLoginClicked()
    abstract fun onRegisterClicked()

}