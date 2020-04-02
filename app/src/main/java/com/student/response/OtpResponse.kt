package com.student.response

import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.Nullable

class OtpResponse {

    @SerializedName("error")
    lateinit var error : String

    @Nullable
    @SerializedName("result")
    var result : Int = 0
}