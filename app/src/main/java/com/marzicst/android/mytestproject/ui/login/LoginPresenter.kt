package com.marzicst.android.mytestproject.ui.login

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.marzicst.android.mytestproject.R
import com.marzicst.android.mytestproject.data.request.LoginRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class LoginPresenter : LoginContract.Presenter {

    private var model: LoginContract.Model? = null
    private var view: LoginContract.View? = null
    private var fireBaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var googleSignInClient: GoogleSignInClient? = null

    private var callbackManager: CallbackManager? = null

    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(model: LoginContract.Model, view: LoginContract.View) {
        this.model = model
        this.view = view
    }

    override fun onDestroy() {
        model = null
        view = null
        compositeDisposable.clear()
    }

    override fun onGoogleSignInButtonClicked(loginActivity: LoginActivity) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(loginActivity.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(view?.getViewContext()!!, gso)

        val signInIntent = googleSignInClient?.signInIntent
        loginActivity.startActivityForResult(signInIntent, 101)
    }

    override fun authWithGoogle(account: GoogleSignInAccount, loginActivity: LoginActivity) {
        Log.d("LOG", "authWithGoogle:" + account.id!!)

        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        fireBaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(loginActivity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("LOG", "signInWithCredential:success")
                    Toast.makeText(loginActivity.baseContext, "Login in Google success", Toast.LENGTH_LONG).show()
//                    val user = mAuth?.currentUser
//                    updateUI(user)
                    view?.openMainActivity()
                } else {
                    Toast.makeText(loginActivity.baseContext, "Nooooo", Toast.LENGTH_SHORT).show()

                }
            }
    }

    override fun onButtonLoginClicked(email: String?, password: String?) {

        view?.hideKeyboard()
        if (email != null) {
            if (email.isEmpty()) {
                view?.showEmailError("Field can`t be empty")
                return
            }
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches()) {
            view?.showEmailError("Incorrect email")
            return
        }
        if (password != null) {
            if (password.length < 4) {
                view?.showPasswordError("short password")
                return

            }
        }
        val body = LoginRequest(email, password)
        view?.showProgress()
        val disposable = model?.loginRx(body)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe({view?.openNewsActivity()},{view?.openMainActivity()})


        view?.hideProgress()

        if (disposable != null) {
            compositeDisposable.add(disposable)
        }

//        val body = LoginRequest(email, password)
//        model?.login(body)?.enqueue(object : Callback<TokenResponse>{
//            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
//
//            }
//
//            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
//                Log.d("LOG", response.body().toString())
//                Log.d("LOG", response.isSuccessful.toString())
//            }
//        })
    }
}