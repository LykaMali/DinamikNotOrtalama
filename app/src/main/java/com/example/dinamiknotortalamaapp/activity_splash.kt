package com.example.dinamiknotortalamaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash.*

class activity_splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        var asagidanGelenButton = AnimationUtils.loadAnimation(this, R.anim.asagidangelenbutton)
        var yukaridanGelenBalon = AnimationUtils.loadAnimation(this, R.anim.yukaridangelenbalon)

        var asagiyaGeriDonenButon = AnimationUtils.loadAnimation(this, R.anim.asagigidenbuton)
        var yukariGeriDonenBalon = AnimationUtils.loadAnimation(this,R.anim.yukariyagidenbalon)

        ortHesaplaMain.animation = asagidanGelenButton
        gelenBalonAnimation.animation = yukaridanGelenBalon

        ortHesaplaMain.setOnClickListener {
            ortHesaplaMain.startAnimation(asagiyaGeriDonenButon)
            gelenBalonAnimation.startAnimation(yukariGeriDonenBalon)

            object : CountDownTimer(1000,1000){
                override fun onFinish() {
                    var intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }

                override fun onTick(millisUntilFinished: Long) {

                }

            }.start()

        }
    }
}