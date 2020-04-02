package com.student.response

import com.google.gson.annotations.SerializedName
import com.student.model.QuizList

class ResultResponse {

    @SerializedName("error")
    lateinit var error : String

    @SerializedName("result")
    lateinit var QuizList : ArrayList<QuizList>

}