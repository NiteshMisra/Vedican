package com.student.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.student.R
import com.student.activity.StudentDetail
import com.student.model.StudentResult
import java.util.*

class StudentAdapter(var context: Context) : PagedListAdapter<StudentResult,StudentAdapter.ViewHolder>(DIFF_CALLBACK){

    companion object {

        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<StudentResult>() {
            override fun areItemsTheSame(oldItem: StudentResult, newItem: StudentResult):Boolean {
                return oldItem.id.toString() === newItem.id.toString()
            }
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: StudentResult, newItem: StudentResult):Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element_student,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val updateCurrent: StudentResult = getItem(position)!!
        holder.name.text = updateCurrent.name
        holder.email.text = updateCurrent.email
        holder.first.text = updateCurrent.name[0].toString().toUpperCase(Locale.getDefault())

        holder.layout.setOnClickListener {
            val intent = Intent(context, StudentDetail::class.java)
            intent.putExtra("GSON", Gson().toJson(updateCurrent))
            context.startActivity(intent)
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val name : TextView = itemView.findViewById(R.id.element_student_name)
        val email : TextView = itemView.findViewById(R.id.element_student_email)
        val first : TextView = itemView.findViewById(R.id.element_student_first)
        val layout : ConstraintLayout = itemView.findViewById(R.id.element_student_layout)
    }
}