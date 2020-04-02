package com.student.dataFactory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.student.datasource.QuizListDataSource
import com.student.extras.DetailInterface
import com.student.model.QuizList

class QuizListFactory(private var studentId : String, private var detailInterface: DetailInterface) : DataSource.Factory<Int,QuizList>() {

    private val itemLiveDataSource : MutableLiveData<PageKeyedDataSource<Int,QuizList>> = MutableLiveData()

    override fun create(): DataSource<Int, QuizList> {
        val imageDataSource = QuizListDataSource(studentId,detailInterface)
        itemLiveDataSource.postValue(imageDataSource)
        return imageDataSource
    }

    fun getItemLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, QuizList>> {
        return itemLiveDataSource
    }

}