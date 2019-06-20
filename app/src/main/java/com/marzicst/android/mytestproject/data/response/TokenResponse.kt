package com.marzicst.android.mytestproject.data.response

import com.google.gson.annotations.SerializedName

class TokenResponse {
    @SerializedName("otpRequired")
    var auth: Boolean = false
}