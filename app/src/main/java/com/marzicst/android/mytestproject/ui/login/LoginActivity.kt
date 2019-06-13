package com.marzicst.android.mytestproject.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.marzicst.android.mytestproject.R
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.marzicst.android.mytestproject.MainActivity

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private var mAuth: FirebaseAuth? = null
    private var firebaseUser: FirebaseUser? = null
    private var callbackManager: CallbackManager? = null

    var presenter: LoginPresenter? = LoginPresenter()

    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var emailInput: TextInputLayout
    private lateinit var googleSignInButton: SignInButton
    private lateinit var signOut: Button
    private lateinit var facebookLoginButton: LoginButton
    private lateinit var loginButton: Button

    private lateinit var passwordInput: TextInputLayout
    private var emailText: String? = null
    private var passwordText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter?.onCreate(LoginModel(), this)
        initViews()
        mAuth = FirebaseAuth.getInstance()

        googleSignInButton.setOnClickListener {

            presenter?.onGoogleSignInButtonClicked(this)

        }
        signOut.setOnClickListener { FirebaseAuth.getInstance().signOut() }

        email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                emailInput.isErrorEnabled = false
                emailText = email.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                passwordInput.isErrorEnabled = false
                passwordText = password.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                passwordText = password.text.toString()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        loginButton.setOnClickListener { presenter?.onButtonLoginClicked(emailText, passwordText) }

        callbackManager = CallbackManager.Factory.create()
        facebookLoginButton.setReadPermissions("email")

//        facebookLoginButton.setOnClickListener { presenter?.onFacebookSignInButtonClicked() }

        facebookLoginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                Toast.makeText(baseContext, "Login Facebook Success", Toast.LENGTH_LONG).show()
                handleFacebookAccessToken(result.accessToken)
            }

            override fun onCancel() {
                Toast.makeText(baseContext, "Login Facebook Cancel", Toast.LENGTH_LONG).show()
            }

            override fun onError(error: FacebookException?) {
                Toast.makeText(baseContext, "Login Facebook Error", Toast.LENGTH_LONG).show()
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 101) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    presenter?.authWithGoogle(account, this)
                }
            } catch (e: ApiException) {
                Log.w("LOG", "Google sign in failed", e)
            }
        }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("TAG", "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        mAuth?.signInWithCredential(credential)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "signInWithCredential:success")
//                    updateUI(user)
                    openMainActivity()
                } else {
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    override fun showEmailError(text: String) {
        emailInput.isErrorEnabled = true
        emailInput.error = text
    }

    override fun showPasswordError(text: String) {
        passwordInput.isErrorEnabled = true
        passwordInput.error = text
    }

    override fun validationSuccessful() {
        emailInput.error = null
        passwordInput.error = null
        Toast.makeText(baseContext, "Login Successful", Toast.LENGTH_SHORT).show()
    }

    override fun hideKeyboard() {
        val view = currentFocus
        if (view != null) {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                view.windowToken,
                HIDE_NOT_ALWAYS
            )
        }
    }

    override fun openMainActivity() {
        val intent = MainActivity.newIntent(baseContext)
        startActivity(intent)
    }

    private fun initViews() {
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        emailInput = findViewById(R.id.activity_login_email)
        passwordInput = findViewById(R.id.activity_login_password)
        googleSignInButton = findViewById(R.id.activity_login_btn_google_login)
        signOut = findViewById(R.id.activity_login_btn_sign_out)
        facebookLoginButton = findViewById(R.id.activity_login_facebook_login_button)
        loginButton = findViewById(R.id.activity_login_btn_login)
    }

    override fun getViewContext(): LoginContract.View {
        return this
    }

}