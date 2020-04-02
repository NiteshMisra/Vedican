package com.student.model

import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

class QuizList {

    @SerializedName("id")
    var id : Int = 0

    @SerializedName("student_name")
    lateinit var studentName : String

    @SerializedName("student_id")
    lateinit var studentId : String

    @Nullable
    @SerializedName("quiz_id")
    var quizId: QuizId? = null

    @SerializedName("total_questions")
    lateinit var totalQuestions : String

    @SerializedName("total_attempt")
    lateinit var totalAttempt : String

    @SerializedName("total_correct")
    lateinit var totalCorrect : String

    @SerializedName("total_incorrect")
    lateinit var totalIncorrect : String

    @SerializedName("total_marks")
    lateinit var totalMarks : String

    @SerializedName("final_result")
    lateinit var finalResult : String

    @NotNull
    @SerializedName("ip")
    lateinit var ip : String

    @SerializedName("total_answers")
    lateinit var totalAnswers : String

    @SerializedName("status")
    lateinit var status : String

    @SerializedName("date")
    lateinit var date : String

}