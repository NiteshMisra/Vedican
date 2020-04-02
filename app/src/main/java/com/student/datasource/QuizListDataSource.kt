package com.student.datasource

import androidx.paging.PageKeyedDataSource
import com.student.extras.DetailInterface
import com.student.extras.HomeInterface
import com.student.model.QuizList
import com.student.response.ResultResponse
import com.student.rest.Api
import com.student.rest.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuizListDataSource(var studentId : String,var detailInterface: DetailInterface) : PageKeyedDataSource<Int,QuizList>() {

    private var pageLoaded : Int = 0
    private var isNextLoading : Boolean = true

    override fun loadInitial( params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, QuizList>) {

        detailInterface.showProgress()
        RetrofitClient.buildRetrofit(Api::class.java).getQuizList(studentId,pageLoaded.toString()).enqueue(object : Callback<ResultResponse>{
            override fun onFailure(call: Call<ResultResponse>, t: Throwable) {
                detailInterface.hideProgress()
            }

            override fun onResponse(
                call: Call<ResultResponse>,
                response: Response<ResultResponse>
            ) {
                detailInterface.hideProgress()
                if (response.isSuccessful){
                    val result = response.body()!!.QuizList
                    pageLoaded++
                    if (result.size < 10){
                        isNextLoading = false
                    }
                    callback.onResult(result,null,pageLoaded)
                }
            }

        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, QuizList>) {
        if (isNextLoading){
            RetrofitClient.buildRetrofit(Api::class.java).getQuizList(studentId,pageLoaded.toString()).enqueue(object : Callback<ResultResponse>{
                override fun onFailure(call: Call<ResultResponse>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<ResultResponse>,
                    response: Response<ResultResponse>
                ) {
                    if (response.isSuccessful){
                        val result = response.body()!!.QuizList
                        pageLoaded++
                        if (result.size < 10){
                            isNextLoading = false
                        }
                        callback.onResult(result,pageLoaded)
                    }
                }

            })
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, QuizList>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}