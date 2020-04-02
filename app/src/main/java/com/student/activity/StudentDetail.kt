@file:Suppress("DEPRECATION")

package com.student.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.student.R
import com.student.adapter.ListAdapter
import com.student.databinding.ActivityStudentDetailBinding
import com.student.extras.DetailInterface
import com.student.extras.HomeInterface
import com.student.model.StudentResult
import com.student.viewmodel.viewmodel
import kotlin.math.roundToInt

class StudentDetail : AppCompatActivity(), DetailInterface {

    private lateinit var studentResult : StudentResult
    private lateinit var binding : ActivityStudentDetailBinding
    private lateinit var viewModel : viewmodel
    private lateinit var listAdapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_student_detail)
        viewModel = ViewModelProviders.of(this).get(viewmodel::class.java)
        val value = intent.getStringExtra("GSON")
        studentResult = Gson().fromJson(value, StudentResult::class.java)

        binding.detailRcv.layoutManager = LinearLayoutManager(this@StudentDetail)
        binding.detailRcv.setHasFixedSize(false)

        binding.quizListBack.setOnClickListener {
            onBackPressed()
        }

        binding.quizListStudentEmail.text = studentResult.email
        binding.quizListStudentName.text = studentResult.name

        fetchList()

        binding.detailRefresh.setOnRefreshListener {
            fetchList()
        }


    }

    private fun fetchList() {

        listAdapter = ListAdapter(this@StudentDetail)
        viewModel.fetchQuizList(studentResult.id.toString(),this).observe(this, Observer {
            listAdapter.submitList(it)
            listAdapter.notifyDataSetChanged()
        })

        binding.detailRcv.adapter = listAdapter

    }

    override fun showProgress() {
        binding.detailRefresh.isRefreshing = true
    }

    override fun hideProgress() {
        binding.detailRefresh.isRefreshing = false
    }

}
