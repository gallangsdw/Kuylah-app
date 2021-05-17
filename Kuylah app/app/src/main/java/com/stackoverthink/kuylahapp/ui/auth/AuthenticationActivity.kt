package com.stackoverthink.kuylahapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.stackoverthink.kuylahapp.R
import com.stackoverthink.kuylahapp.databinding.ActivityAuthenticationBinding
import com.stackoverthink.kuylahapp.ui.main.MainActivity
import com.stackoverthink.kuylahapp.ui.models.User
import com.stackoverthink.kuylahapp.ui.signup.SignUpActivity

class AuthenticationActivity : AppCompatActivity() {

    companion object {
        private const val RC_SIGN_IN = 120
        private const val TAG = "SignIn"
    }

    private lateinit var binding: ActivityAuthenticationBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        mAuth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            signIn()
            Toast.makeText(this,"sebentar ya",Toast.LENGTH_LONG).show()
        }

//        binding.btnSignUp.setOnClickListener {
//            val intent = Intent(this, SignUpActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if (task.isSuccessful) {
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d("AuthenticationActivity", "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w("AuthenticationActivity", "Google sign in failed", e)
                }
            } else {
                Log.w("AuthenticationActivity", exception.toString())
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        val profile = MutableLiveData<User>()
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("AuthenticationActivity", "signInWithCredential:success")
                        Toast.makeText(this,"Selamat datang di kuylah!",Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        val currentUser = mAuth.currentUser
                        profile.value = User(uid = currentUser.uid, email = currentUser.email, name = currentUser.displayName, photoUrl = currentUser.photoUrl.toString())
                        addUserToDatabase(profile.value)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("AuthenticationActivity", "signInWithCredential:failure", task.exception)
                    }
                }
    }

    private fun addUserToDatabase(user: User?){
        val db = Firebase.firestore
        db.collection("users").document(user?.uid!!)
            .set(user)
            .addOnSuccessListener {
                Log.d(TAG, "User added : ${user.toString()}")
            }
            .addOnFailureListener {
                Log.w(TAG, "Error adding document", it)
            }
    }
}