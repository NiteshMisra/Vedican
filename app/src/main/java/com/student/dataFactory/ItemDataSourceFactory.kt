package com.student.dataFactory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.student.datasource.ItemDataSource
import com.student.extras.HomeInterface
import com.student.model.StudentResult

class ItemDataSourceFactory(var phone : String, var homeInterface: HomeInterface) : DataSource.Factory<Int,StudentResult>() {

    private val itemLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, StudentResult>>()

    override fun create(): DataSource<Int, StudentResult> {
        val itemDataSource = ItemDataSource(phone,homeInterface)
        itemLiveDataSource.postValue(itemDataSource)
        return itemDataSource
    }

    fun getItemLiveDataSource() : MutableLiveData<PageKeyedDataSource<Int, StudentResult>> {
        return itemLiveDataSource
    }

}
