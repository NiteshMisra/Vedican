@file:Suppress("DEPRECATION")

package com.student.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.student.R
import com.student.adapter.StudentAdapter
import com.student.databinding.ActivityMainBinding
import com.student.extras.HomeInterface
import com.student.extras.PrefData
import com.student.viewmodel.viewmodel

class MainActivity : AppCompatActivity(),HomeInterface {

    private lateinit var viewModel: viewmodel
    private lateinit var binding: ActivityMainBinding
    private lateinit var studentAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(viewmodel::class.java)
        binding.homeRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.homeRecyclerView.setHasFixedSize(true)

        binding.homeRecyclerView.visibility = View.GONE
        binding.homeError.visibility = View.GONE

        binding.homeRefresh.setOnRefreshListener {
            fetchList()
        }

        fetchList()
    }

    private fun fetchList() {

        studentAdapter = StudentAdapter(this@MainActivity)

        viewModel.fetchStudentList(PrefData.phone()!!,this).observe(this@MainActivity, Observer {
            if (it != null){
                studentAdapter.submitList(it)
                studentAdapter.notifyDataSetChanged()
            }
        })

        binding.homeRecyclerView.adapter = studentAdapter

    }

    override fun showProgress() {
        binding.homeRefresh.isRefreshing = true
    }

    override fun hideProgress(error: String?) {
        binding.homeRefresh.isRefreshing = false
        if (error == null){
            binding.homeRecyclerView.visibility = View.VISIBLE
            binding.homeError.visibility = View.GONE
        }else{
            binding.homeRecyclerView.visibility = View.GONE
            binding.homeError.visibility = View.VISIBLE
            binding.homeError.text = error
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.nav_logOut){
            PrefData.session(false)
            val intent = Intent(this,LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

}
