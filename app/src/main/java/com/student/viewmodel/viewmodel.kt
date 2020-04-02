package com.student.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.student.extras.DetailInterface
import com.student.extras.HomeInterface
import com.student.extras.LoginInterface
import com.student.extras.SolutionInterface
import com.student.model.QuizList
import com.student.model.StudentResult
import com.student.repository.Repository
import com.student.response.OtpResponse
import com.student.response.RankResponse
import com.student.response.SolutionsResponse

class viewmodel: ViewModel() {

    private var repository : Repository = Repository()

    fun sendOTP(phone : String,loginInterface: LoginInterface): LiveData<OtpResponse>{
        return repository.sendOTP(phone,loginInterface)
    }

    fun fetchStudentList(phone : String, homeInterface: HomeInterface): LiveData<PagedList<StudentResult>> {
        return repository.getStudentList(phone,homeInterface)
    }

    fun fetchQuizList(studentId : String,detailInterface: DetailInterface) : LiveData<PagedList<QuizList>>{
        return repository.getQuizList(studentId,detailInterface)
    }

    fun fetchSolutions(id : String, solutionInterface: SolutionInterface) : LiveData<SolutionsResponse>{
        return repository.getSolutions(id,solutionInterface)
    }

    fun getRank(id : String,quizId : String, solutionInterface: SolutionInterface) : LiveData<RankResponse> {
        return repository.getRank(id,quizId,solutionInterface)
    }

}