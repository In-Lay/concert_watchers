package com.inlay.login.presentation.viewModel.profile

import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import coil.load
import coil.transform.CircleCropTransformation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AppProfileViewModel : ProfileViewModel() {
    private val _email = MutableLiveData<String>()
    override var email: LiveData<String> = _email

    private val _auth = MutableLiveData<FirebaseAuth>()
    override val auth: LiveData<FirebaseAuth> = _auth

    private val _currentUser = MutableLiveData<FirebaseUser?>()
    override val currentUser: LiveData<FirebaseUser?> = _currentUser

    private val _userStateFlag = MutableLiveData<Boolean>()
    override val userProfileStateFlag: LiveData<Boolean> = _userStateFlag

    private val _profileImageStateFlag = MutableLiveData<Boolean>()
    override val profileImageStateFlag: LiveData<Boolean> = _profileImageStateFlag

    private val _profileImageUri = MutableLiveData<Uri?>()
    override val profileImageUri: LiveData<Uri?> = _profileImageUri

    override fun setAuth(newAuth: FirebaseAuth) {
        _auth.value = newAuth
    }

    override fun setEmail(newEmail: String) {
        _email.value = newEmail
    }

    override fun setCurrentUser(firebaseUser: FirebaseUser?) {
        _currentUser.value = firebaseUser
    }

    override fun setProfileImageUri(imageUri: Uri?) {
        _profileImageUri.value = imageUri
    }

    override fun changeProfileImageStateFlagToFalse() {
        _profileImageStateFlag.value = false
    }

    override fun onImageChangeClicked() {
        _profileImageStateFlag.value = true
    }

    override fun onLogoutClicked() {
        _auth.value?.signOut()
        _userStateFlag.value = false
    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageSource")
        fun loadImage(view: ImageView, image: Uri?) {
            Log.d(
                "LoginTag", "imageLoaded with: $image"
            )
            if (image == null) {
                view.load(
                    "https://www.personality-insights.com/wp-content/uploads/2017/12/default-profile-pic-150x150.jpg"
                ) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
            } else {
                Log.d(
                    "LoginTag", "image != null"
                )
                view.load(image) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
            }
        }
    }
}