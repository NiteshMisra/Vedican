package com.student.datasource

import androidx.paging.PageKeyedDataSource
import com.student.extras.HomeInterface
import com.student.model.StudentResult
import com.student.response.studentResponse
import com.student.rest.Api
import com.student.rest.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemDataSource(var phone : String,var homeInterface: HomeInterface) : PageKeyedDataSource<Int,StudentResult>() {

    private var pageLoaded : Int = 0
    private var isNextLoading : Boolean = true

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, StudentResult>) {
        homeInterface.showProgress()
        RetrofitClient.buildRetrofit(Api::class.java)
            .getStudent(phone,pageLoaded.toString()).enqueue(object : Callback<studentResponse>{
            override fun onFailure(call: Call<studentResponse>, t: Throwable) {
                homeInterface.hideProgress(t.message)
            }

            override fun onResponse(call: Call<studentResponse>, response: Response<studentResponse>) {
                if (response.isSuccessful){
                    val result1 = response.body()!!.result

                    if (result1.size < 10){
                        isNextLoading = false
                        if (result1.isEmpty()){
                            homeInterface.hideProgress("Data is not available")
                        }else{
                            homeInterface.hideProgress(null)
                        }
                        pageLoaded += 1
                        callback.onResult(result1,null,pageLoaded)
                    }else{
                        homeInterface.hideProgress(null)
                        pageLoaded += 1
                        callback.onResult(result1,null,pageLoaded)
                    }
                }else{
                    homeInterface.hideProgress(response.errorBody().toString())
                }
            }

        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, StudentResult>) {
        if (isNextLoading){
            RetrofitClient.buildRetrofit(Api::class.java)
                .getStudent(phone,pageLoaded.toString()).enqueue(object : Callback<studentResponse>{
                    override fun onFailure(call: Call<studentResponse>, t: Throwable) {}

                    override fun onResponse(call: Call<studentResponse>, response: Response<studentResponse>) {
                        if (response.isSuccessful){
                            val result1 = response.body()!!.result

                            if (result1.size < 10){
                                isNextLoading = false
                            }

                            pageLoaded += 1
                            callback.onResult(result1,pageLoaded)
                        }
                    }

                })
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, StudentResult>) {

    }


}