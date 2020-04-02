package com.student.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.student.R
import com.student.activity.StudentDetail
import com.student.model.StudentResult
import java.util.*

class sAdapter(var list: List<StudentResult>,var context : Context) : RecyclerView.Adapter<sAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element_student,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val updateCurrent: StudentResult = list[position]
        holder.name.text = updateCurrent.name
        holder.email.text = updateCurrent.email
        holder.first.text = updateCurrent.name[0].toString().toUpperCase(Locale.getDefault())
        val random = Random()
        val color : Int = Color.argb(255,random.nextInt(256),random.nextInt(256),random.nextInt(256))
        holder.card.setCardBackgroundColor(color)

        holder.layout.setOnClickListener {
            val intent = Intent(context,StudentDetail::class.java)
            intent.putExtra("GSON", Gson().toJson(updateCurrent))
            intent.putExtra("colour",color)
            context.startActivity(intent)
        }

    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val name : TextView = itemView.findViewById(R.id.element_student_name)
        val email : TextView = itemView.findViewById(R.id.element_student_email)
        val first : TextView = itemView.findViewById(R.id.element_student_first)
        val card : CardView = itemView.findViewById(R.id.element_student_card)
        val layout : ConstraintLayout = itemView.findViewById(R.id.element_student_layout)
    }
}