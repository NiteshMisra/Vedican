@file:Suppress("DEPRECATION")

package com.student.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.student.R
import com.student.databinding.ActivityLoginBinding
import com.student.extras.LoginInterface
import com.student.extras.PrefData
import com.student.response.OtpResponse
import com.student.viewmodel.viewmodel
import dmax.dialog.SpotsDialog

class LoginActivity : AppCompatActivity(),LoginInterface {

    private lateinit var binding: ActivityLoginBinding
    private var countDownTimer: CountDownTimer? = null
    private var isSendEnabled: Boolean = true
    private var isOTPSent: Boolean = false
    private var otp: Int = 0
    private lateinit var viewModel: viewmodel
    private lateinit var mobileNo : String
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProviders.of(this@LoginActivity).get(viewmodel::class.java)
        dialog = SpotsDialog.Builder().setContext(this).build()

        binding.sendOtpButton.setOnClickListener {
            if (isSendEnabled) {
                mobileNo = binding.mobile.text.toString()

                if (mobileNo.isEmpty() || mobileNo.length != 10) {
                    Toast.makeText(this, "Enter correct mobile no.", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                viewModel.sendOTP(mobileNo,this).observe(this,
                    Observer<OtpResponse> {
                        if (it != null) {

                            otp = it.result
                            isOTPSent = true
                            startTimer()
                            Toast.makeText( this@LoginActivity, "OTP sent successfully", Toast.LENGTH_LONG ).show()
                        } else {
                            Toast.makeText(this@LoginActivity, "Some error occurred, Try again later", Toast.LENGTH_LONG ).show()
                            isOTPSent = false
                        }
                    })
            }
        }

        binding.login.setOnClickListener {
            val userOtp = binding.otp.text.toString()
            if (isOTPSent && userOtp.isNotEmpty()) {
                if (userOtp.toInt() == otp) {
                    PrefData.session(true)
                    PrefData.phone(mobileNo)
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, "OTP not matched", Toast.LENGTH_LONG).show()
                    PrefData.session(false)
                }
            }
        }
    }

    private fun startTimer() {

        countDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onFinish() {
                binding.sendOtpButton.text = "Send OTP"
                isSendEnabled = true
            }

            override fun onTick(millisUntilFinished: Long) {

                val seconds = millisUntilFinished / 1000
                val minutes = seconds / 60

                binding.sendOtpButton.text = "Resend OTP in ${String.format("%02d:%02d", minutes, seconds)}"
                isSendEnabled = false

            }

        }.start()
    }

    override fun dismissDialog(error : String?) {
        dialog.dismiss()
        if (error != null){
            Toast.makeText(this@LoginActivity,error,Toast.LENGTH_LONG).show()
        }
    }

    override fun showDialog() {
        dialog.setTitle("Please wait...")
        dialog.setCanceledOnTouchOutside(false)
        dialog.setMessage("Sending OTP...")
        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(countDownTimer != null){
            countDownTimer!!.cancel()
        }
    }
}
