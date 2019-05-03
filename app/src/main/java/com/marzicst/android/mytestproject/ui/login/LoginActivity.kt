package com.marzicst.android.mytestproject.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.marzicst.android.mytestproject.R
import java.nio.file.WatchKey
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private var mAuth: FirebaseAuth? = null
    private var googleSignInClient: GoogleSignInClient? = null

    var presenter: LoginPresenter? = LoginPresenter()
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var emailInput: TextInputLayout
    lateinit var signButton: SignInButton
    lateinit var signOut: Button
    private lateinit var passwordInput: TextInputLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter?.onCreate(LoginModel(), this)

        val btn: Button = findViewById(R.id.activity_login_btn_login)
        btn.setOnClickListener { presenter?.onButtonClicked() }


        mAuth = FirebaseAuth.getInstance()


        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        emailInput = findViewById(R.id.activity_login_email)
        passwordInput = findViewById(R.id.activity_login_password)
        signButton = findViewById(R.id.activity_login_btn_google_login)
        signOut = findViewById(R.id.activity_login_btn_sign_out)

        signButton.setOnClickListener { signIn() }
        signOut.setOnClickListener { FirebaseAuth.getInstance().signOut() }

        email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (email.text.toString().isEmpty()) {
                    emailInput.isErrorEnabled = true
                    emailInput.error = "email can`t be empty"
                    return
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
                    emailInput.isErrorEnabled = true
                    emailInput.error = "incorrect email"
                } else {
                    emailInput.isErrorEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


            }
        })

        password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (password.text.toString().isEmpty()) {
                    passwordInput.isErrorEnabled = true
                    passwordInput.error = "password can`t be empty"
                    return
                }
                if (password.length() < 4) {
                    passwordInput.isErrorEnabled = true
                    passwordInput.error = " min password 4 symbol"
                    return
                }
                if (password.length() > 10) {
                    passwordInput.isErrorEnabled = true
                    passwordInput.error = " max password 10 symbol"
                } else {
                    passwordInput.isErrorEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun signIn() {
        val signInIntent = googleSignInClient?.signInIntent
        startActivityForResult(signInIntent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 101) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("LOG", "Google sign in failed", e)
                // ...
            }
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        Log.d("LOG", "firebaseAuthWithGoogle:" + account.id!!)

        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        mAuth?.signInWithCredential(credential)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("LOG", "signInWithCredential:success")
                    Toast.makeText(baseContext, "Login in Google success", Toast.LENGTH_LONG).show()
//                    val user = mAuth?.currentUser
//                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "signInWithCredential:failure", task.exception)
//                    Snackbar.make(main_layout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
//                    updateUI(null)
                }

                // ...
            }
    }


    @SuppressLint("ShowToast")
    override fun showMassage() {
        Toast.makeText(baseContext, "Hello", Toast.LENGTH_LONG).show()
    }

    fun isEmailValid(email: String) {
        if (email.isEmpty()) {
            emailInput.isErrorEnabled = true
            emailInput.error = "field can`t be empty"
        } else {
            emailInput.isErrorEnabled = false
        }
    }

    fun isPasswordValid(pasword: String) {
        if (password.length() < 4) {
            passwordInput.isErrorEnabled = true
            passwordInput.error = "short password"
        } else {
            passwordInput.isErrorEnabled = false
        }
    }


}