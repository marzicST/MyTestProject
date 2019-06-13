package com.marzicst.android.mytestproject.api.response

import com.google.gson.annotations.SerializedName

class TokenResponse {
    @SerializedName("otpRequired")
    var auth: Boolean = false
}