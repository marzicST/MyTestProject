package com.marzicst.android.mytestproject.data.request

import com.google.gson.annotations.SerializedName

class CursorRequest {
    @SerializedName("next_cursor")
    var lastItemDateTimestamp: Long? = null
    @SerializedName("race_type_alias")
    var raceTypeAlias: String? = null
}