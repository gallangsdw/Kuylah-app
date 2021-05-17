package com.stackoverthink.kuylahapp.ui.main.setting

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.stackoverthink.kuylahapp.R
import com.stackoverthink.kuylahapp.databinding.FragmentSettingBinding
import com.stackoverthink.kuylahapp.ui.auth.AuthenticationActivity

class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        requireActivity().run { googleSignInClient = GoogleSignIn.getClient(this,gso) }

        binding.tvName.text = currentUser?.displayName
        binding.tvEmail.text = currentUser?.email
//        binding.tvPhoneNumber.text = currentUser?.phoneNumber
        binding.tvPhoneNumber.text = currentUser?.uid

        Glide.with(this)
            .load(currentUser?.photoUrl)
            .transform(RoundedCorners(16))
            .into(binding.imgProfile)

        binding.button.setOnClickListener {
            requireActivity().run {
            val eBuilder = AlertDialog.Builder(this)
                eBuilder.setTitle("Keluar")
                eBuilder.setMessage("Yakin kamu ingin keluar?")
                eBuilder.setPositiveButton("Iya") {
                    _, _ ->
                    mAuth.signOut()
                    googleSignInClient.signOut().addOnCompleteListener {
                        startActivity(Intent(this, AuthenticationActivity::class.java))
                        finish()
                    }
                }
                eBuilder.setNegativeButton("No") {
                    _, _ ->
                    Toast.makeText(this,"yuhu gajadi keluar",Toast.LENGTH_SHORT).show()
                }
                val createBuild = eBuilder.create()
                createBuild.show()
            }
        }
    }
}