package com.inlay.login.presentation.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.inlay.login.R
import com.inlay.login.databinding.FragmentProfileBinding
import com.inlay.login.di.getOrCreateProfileScope
import com.inlay.login.presentation.viewModel.profile.ProfileViewModel

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val profileViewModel: ProfileViewModel = getOrCreateProfileScope().get()
    private lateinit var fragmentCurrentUser: FirebaseUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        fragmentCurrentUser = Firebase.auth.currentUser!!
        profileViewModel.apply {
            changeProfileImageStateFlagToFalse()
            setAuth(Firebase.auth)
            setCurrentUser(fragmentCurrentUser)
            setEmail(fragmentCurrentUser.email!!)
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        binding.apply {
            viewModel = profileViewModel
            lifecycleOwner = viewLifecycleOwner
            executePendingBindings()
        }
        profileViewModel.setProfileImageUri(fragmentCurrentUser.photoUrl)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        profileViewModel.profileImageStateFlag.observe(viewLifecycleOwner) {
            if (it) {
                resultLauncher.launch(intent)
            }
        }
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                if (data != null) {
                    val reference = data.data?.path
                    val storageRef = Firebase.storage.reference.child(reference!!)

                    val uploadTask = storageRef.putFile(data.data!!)

                    uploadTask.continueWithTask {
                        if (!it.isSuccessful) {
                            it.exception?.let { e ->
                                throw e
                            }
                        }
                        storageRef.downloadUrl
                    }.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val profileUpdate = userProfileChangeRequest {
                                photoUri = task.result
                            }
                            fragmentCurrentUser.updateProfile(profileUpdate)
                                .addOnCompleteListener { updateTask ->
                                    if (updateTask.isSuccessful) {
                                        profileViewModel.setProfileImageUri(task.result)
                                        Toast.makeText(context, "Data changed", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                }
                        }
                    }
                }
            }
        }
}