package com.inlay.login.presentation.viewModel.profile

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

abstract class ProfileViewModel : ViewModel() {
    abstract val email: LiveData<String>
    abstract val profileImageUri: LiveData<Uri?>

    abstract val auth: LiveData<FirebaseAuth>

    abstract val currentUser: LiveData<FirebaseUser?>

    abstract val userProfileStateFlag: LiveData<Boolean>
    abstract val profileImageStateFlag: LiveData<Boolean>

    abstract fun setAuth(newAuth: FirebaseAuth)

    abstract fun setCurrentUser(firebaseUser: FirebaseUser?)
    abstract fun setEmail(newEmail: String)

    abstract fun setProfileImageUri(imageUri: Uri?)

    abstract fun changeProfileImageStateFlagToFalse()

    abstract fun onLogoutClicked()

    abstract fun onImageChangeClicked()
}