@file:Suppress("DEPRECATION")

package com.student.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig
import com.razerdp.widget.animatedpieview.data.SimplePieInfo
import com.student.R
import com.student.databinding.ActivitySolutionsBinding
import com.student.extras.SolutionInterface
import com.student.model.QuizList
import com.student.response.RankResponse
import com.student.viewmodel.viewmodel

class Solutions : AppCompatActivity(), SolutionInterface {

    private lateinit var binding : ActivitySolutionsBinding
    private lateinit var viewModel : viewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_solutions)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val data : String = intent.getStringExtra("GSON")!!
        val list : QuizList = Gson().fromJson(data,QuizList::class.java)
        viewModel = ViewModelProviders.of(this).get(viewmodel::class.java)


        val config = AnimatedPieViewConfig()
        val notAttempt = list.totalQuestions.toDouble() - (list.totalCorrect.toDouble() + list.totalIncorrect.toDouble())
        config.addData(SimplePieInfo(notAttempt,Color.parseColor("#0000FF"),notAttempt.toInt().toString()))
        config.addData(SimplePieInfo(list.totalCorrect.toDouble(),Color.parseColor("#45AD2B"),list.totalCorrect))
        config.addData(SimplePieInfo(list.totalIncorrect.toDouble(),Color.parseColor("#FF0000"),list.totalIncorrect))
        config.duration = 1000
        config.strokeMode(false)
        config.drawText(false)

        binding.pieChart.applyConfig(config)
        binding.pieChart.start()


        binding.totalMarks.text = list.totalMarks
        binding.obtainedMarks.text = "${list.finalResult}/${list.totalMarks}"
        binding.totalQuestions.text = list.totalQuestions
        binding.attempted.text = "${list.totalAttempt}/${list.totalQuestions}"
        binding.correct.text = "${list.totalCorrect}/${list.totalQuestions}"
        binding.incorrect.text = "${list.totalIncorrect}/${list.totalQuestions}"


        viewModel.getRank(list.studentId,list.quizId!!.id.toString(),this).observe(this,
            Observer<RankResponse> { t ->
                if (t != null){
                    binding.rank.text = "${t.result.rank}/${t.result.total}"
                }else{
                    binding.rank.text = "-"
                }
            })


    }

    override fun showProgress() {
        binding.solutionLayout.visibility = View.GONE
        binding.solutionProgress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.solutionLayout.visibility = View.VISIBLE
        binding.solutionProgress.visibility = View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
