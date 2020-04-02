package com.student.rest

import com.student.response.*
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("parent/otp")
    fun sendOTP(
        @Field("phone") phone : String
    ) : Call<OtpResponse>

    @GET("parent/{phone}/{page}")
    fun getStudent(
        @Path("phone") phone : String,
        @Path("page") page : String
    ) : Call<studentResponse>

    @GET("result/list/{student_id}/{page}")
    fun getQuizList(
        @Path("student_id") studentId : String,
        @Path("page") page : String
    ) : Call<ResultResponse>

    @GET("result/solutions/{id}")
    fun getSolutions(
        @Path("id") id: String
    ): Call<SolutionsResponse>

    @GET("result/rank/{student_id}/{quiz_id}")
    fun getRank(
        @Path("student_id") studentId : String,
        @Path("quiz_id") quizId : String
    ): Call<RankResponse>

}