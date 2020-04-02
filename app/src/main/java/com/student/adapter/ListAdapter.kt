package com.student.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.student.R
import com.student.activity.Solutions
import com.student.model.QuizId
import com.student.model.QuizList

class ListAdapter(var context: Context): PagedListAdapter<QuizList, ListAdapter.ViewHolder>(DIFF_CALLBACK){

    companion object {

        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<QuizList>() {
            override fun areItemsTheSame(oldItem: QuizList, newItem: QuizList):Boolean {
                return oldItem.id.toString() === newItem.id.toString()
            }
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: QuizList, newItem: QuizList):Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element_quiz_list,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current : QuizList = getItem(position)!!
        val updateCurrent: QuizId? = getItem(position)!!.quizId
        if(updateCurrent != null){
            holder.name.text = updateCurrent.quizName
            val duration = updateCurrent.duration.toInt()
            if (duration > 60){
                holder.duration.text = "Duration : ${duration/60} hours ${duration%60} min"
            }else{
                holder.duration.text = "Duration : ${updateCurrent.duration} min"
            }

            holder.card.setOnClickListener {
                val intent = Intent(context,Solutions::class.java)
                intent.putExtra("GSON",Gson().toJson(current))
                context.startActivity(intent)
            }
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val name : TextView = itemView.findViewById(R.id.quiz_name)
        val duration : TextView = itemView.findViewById(R.id.quiz_duration)
        val card : LinearLayout = itemView.findViewById(R.id.quiz_card)
    }
}