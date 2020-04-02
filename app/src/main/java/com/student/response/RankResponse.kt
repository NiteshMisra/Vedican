package com.student.response

import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.Nullable

class RankResponse {

    @SerializedName("error")
    lateinit var error : String

    @Nullable
    @SerializedName("result")
    lateinit var result : Rank

}

class Rank {

    @SerializedName("rank")
    var  rank : Int = 0

    @SerializedName("total")
    var total : Int = 0

}