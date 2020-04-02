package com.student.response

import com.google.gson.annotations.SerializedName
import com.student.model.StudentResult
import org.jetbrains.annotations.Nullable

class studentResponse {

    @SerializedName("error")
    lateinit var error : String

    @Nullable
    @SerializedName("result")
    lateinit var result: List<StudentResult>

}