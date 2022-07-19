package com.example.calculadoraimc

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import com.example.calculadoraimc.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    private var altura = 150
    private var peso = 75

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b=ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)


        b.seekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                altura = progress
                b.tvSeekbarAlt.text = seek.progress.toString().plus("/200")
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                //write custom code for progress is starting

            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                b.tvResult.text = imc()
            }
        })
        b.seekBar2.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                peso = progress
                b.tvSeekbarPeso.text = seek.progress.toString().plus("/150")
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                //write custom code for progress is starting
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                b.tvResult.text = imc()
            }
        })
    }
    private fun imc(): String {
        val altura2 = altura.toDouble() / 100
        val total = peso / (altura2 * altura2)
        return total.toString()
    }
//    private fun imc(p: Double, a: Double): String {
//        return ("%.2f".format(p/ (a.pow(2))))
//    }
}