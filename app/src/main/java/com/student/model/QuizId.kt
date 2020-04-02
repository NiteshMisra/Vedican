package com.student.model

import com.google.gson.annotations.SerializedName

class QuizId {

    @SerializedName("id")
    var id : Int = 0

    @SerializedName("quiz_name")
    lateinit var quizName : String

    @SerializedName("category_id")
    lateinit var categoryId : String

    @SerializedName("subcategory_id")
    lateinit var subCategoryId : String

    @SerializedName("set_id")
    lateinit var setId : String

    @SerializedName("subject_id")
    lateinit var subjectId : String

    @SerializedName("duration")
    lateinit var duration : String

    @SerializedName("status")
    lateinit var status : String

    @SerializedName("ip")
    lateinit var ip : String

    @SerializedName("date")
    lateinit var date : String

}
