package com.marzicst.android.mytestproject.data

import com.google.gson.annotations.SerializedName

class LoginRequest {
    @SerializedName("email")
    var login: String? = null
    var password: String? = null

    constructor(login: String?, password: String?){
        this.login = login
        this.password = password
    }
}