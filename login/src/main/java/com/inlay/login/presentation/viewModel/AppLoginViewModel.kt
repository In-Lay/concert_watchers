package com.inlay.login.presentation.viewModel

import android.text.Editable
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AppLoginViewModel : LoginViewModel() {
    private val _email = MutableLiveData<String>()
    override var email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    override var password: LiveData<String> = _password

    private val _warningText = MutableLiveData<String>()
    override val warningText: LiveData<String> = _warningText

    private val _auth = MutableLiveData<FirebaseAuth>()
    override val auth: LiveData<FirebaseAuth> = _auth

    private val _currentUser = MutableLiveData<FirebaseUser?>()
    override val currentUser: LiveData<FirebaseUser?> = _currentUser

    private val _userStateFlag = MutableLiveData<Boolean>()
    override val userStateFlag: LiveData<Boolean> = _userStateFlag

    private val _error = MutableLiveData<String?>()
    override val error: LiveData<String?> = _error

    override fun updateEmail(email: Editable) {
        _email.value = email.toString()
    }

    override fun updatePassword(password: Editable) {
        _password.value = password.toString()
    }

    override fun setAuth(newAuth: FirebaseAuth) {
        _auth.value = newAuth
    }

    override fun setCurrentUser(firebaseUser: FirebaseUser?) {
        _currentUser.value = firebaseUser
    }

    override fun onLoginClicked() {
        if (_email.value?.let { isEmailValid(it) } == true && _password.value != null) {
            _warningText.value = ""

            _auth.value?.signInWithEmailAndPassword(_email.value!!, _password.value!!)
                ?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        _currentUser.value = _auth.value!!.currentUser
                        _userStateFlag.value = true
                    }
                }?.addOnFailureListener {
                    _error.value = it.message
                }
        } else {
            _warningText.value = "Incorrect Email or Password"
        }
    }

    override fun onRegisterClicked() {
        if (_email.value?.let { isEmailValid(it) } == true && _password.value != null) {

            _warningText.value = ""
            _auth.value?.createUserWithEmailAndPassword(_email.value!!, _password.value!!)
                ?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        _currentUser.value = _auth.value!!.currentUser
                        Firebase.database.reference.child(_currentUser.value?.uid!!)
                    }
                }?.addOnFailureListener {
                    _error.value = it.message
                }
        } else {
            _warningText.value = "Incorrect Email or Password"
        }
    }


    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}