package com.student.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.student.dataFactory.ItemDataSourceFactory
import com.student.dataFactory.QuizListFactory
import com.student.extras.DetailInterface
import com.student.extras.HomeInterface
import com.student.extras.LoginInterface
import com.student.extras.SolutionInterface
import com.student.model.QuizList
import com.student.model.StudentResult
import com.student.response.OtpResponse
import com.student.response.RankResponse
import com.student.response.SolutionsResponse
import com.student.rest.Api
import com.student.rest.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private lateinit var imagePagedList: LiveData<PagedList<QuizList>>
    private lateinit var imageDataSource: LiveData<PageKeyedDataSource<Int, QuizList>>

    private lateinit var studentPagedList: LiveData<PagedList<StudentResult>>
    private lateinit var studentDataSource: LiveData<PageKeyedDataSource<Int, StudentResult>>

    fun sendOTP(mobileNo : String,loginInterface: LoginInterface): MutableLiveData<OtpResponse>{

        loginInterface.showDialog()
        val liveData = MutableLiveData<OtpResponse>()

        RetrofitClient.buildRetrofit(Api::class.java).sendOTP(mobileNo).enqueue(object :
            Callback<OtpResponse> {
            override fun onFailure(call: Call<OtpResponse>, t: Throwable) {
                liveData.postValue(null)
                loginInterface.dismissDialog(t.message)
            }

            override fun onResponse(call: Call<OtpResponse>, response: Response<OtpResponse>) {
                if (response.isSuccessful){
                    liveData.postValue(response.body())
                    loginInterface.dismissDialog(null)
                }else{
                    liveData.postValue(null)
                    loginInterface.dismissDialog(response.errorBody().toString())
                }
            }

        })
        return liveData
    }

    fun getQuizList(studentId : String,detailInterface: DetailInterface): LiveData<PagedList<QuizList>> {

        val itemDataSourceFactory = QuizListFactory(studentId,detailInterface)
        imageDataSource = itemDataSourceFactory.getItemLiveDataSource()

        val config: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        imagePagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()
        Log.e("image",imagePagedList.value.toString())
        return imagePagedList
    }

    fun getStudentList(phone : String,homeInterface: HomeInterface): LiveData<PagedList<StudentResult>> {

        val itemDataSourceFactory = ItemDataSourceFactory(phone,homeInterface)
        studentDataSource = itemDataSourceFactory.getItemLiveDataSource()

        val config: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        studentPagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()
        return studentPagedList
    }

    fun getSolutions(id : String,solutionInterface: SolutionInterface) : MutableLiveData<SolutionsResponse>{
        solutionInterface.showProgress()
        val data = MutableLiveData<SolutionsResponse>()
        RetrofitClient.buildRetrofit(Api::class.java).getSolutions(id).enqueue(object : Callback<SolutionsResponse>{
            override fun onFailure(call: Call<SolutionsResponse>, t: Throwable) {
                solutionInterface.hideProgress()
                data.postValue(null)
            }

            override fun onResponse(
                call: Call<SolutionsResponse>,
                response: Response<SolutionsResponse>
            ) {
                solutionInterface.hideProgress()
                if (response.isSuccessful){
                    data.postValue(response.body())
                }else{
                    data.postValue(null)
                }
            }

        })
        return data
    }

    fun getRank(id : String,quizId : String, solutionInterface: SolutionInterface): MutableLiveData<RankResponse>{

        solutionInterface.showProgress()
        val rankData = MutableLiveData<RankResponse>()
        RetrofitClient.buildRetrofit(Api::class.java).getRank(id,quizId).enqueue(object : Callback<RankResponse>{
            override fun onFailure(call: Call<RankResponse>, t: Throwable) {
                solutionInterface.hideProgress()
                rankData.postValue(null)
            }

            override fun onResponse(call: Call<RankResponse>, response: Response<RankResponse>) {
                solutionInterface.hideProgress()
                if (response.isSuccessful){
                    rankData.postValue(response.body()!!)
                }else{
                    rankData.postValue(null)
                }
            }

        })
        return rankData
    }

}