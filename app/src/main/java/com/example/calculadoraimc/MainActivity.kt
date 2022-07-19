package com.example.calculadoraimc

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import com.example.calculadoraimc.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    private var altura = 150
    private var peso = 75
    private val MIN_VALUE = 0.0
    private val MAX_VALUE = 100.0

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
                when {
                    b.tvResult.text.toString().toDouble() < 16.00 ->
                        Snackbar.make(b.llPrincipal, R.string.severa, Snackbar.LENGTH_LONG).show()
                    b.tvResult.text.toString().toDouble() <16.99 ->
                        Snackbar.make(b.llPrincipal, R.string.leve1, Snackbar.LENGTH_LONG).show()
                    b.tvResult.text.toString().toDouble() < 16.00 ->
                        Snackbar.make(b.llPrincipal, R.string.leve1, Snackbar.LENGTH_LONG).show()
                    b.tvResult.text.toString().toDouble() < 16.00 ->
                        Snackbar.make(b.llPrincipal, R.string.leve1, Snackbar.LENGTH_LONG).show()
                    b.tvResult.text.toString().toDouble() < 16.00 ->
                        Snackbar.make(b.llPrincipal, R.string.leve1, Snackbar.LENGTH_LONG).show()
                    b.tvResult.text.toString().toDouble() < 16.00 ->
                        Snackbar.make(b.llPrincipal, R.string.leve1, Snackbar.LENGTH_LONG).show()


                }
                val resultado =  b.tvResult.text.toString().toDouble()
                when (resultado){
                     in MIN_VALUE..16.00 ->
                        Snackbar.make(b.llPrincipal, R.string.severa, Snackbar.LENGTH_LONG).show()
                    in 16.00..16.99 ->
                        Snackbar.make(b.llPrincipal, R.string.moderada, Snackbar.LENGTH_LONG).show()
                    in 17.00..18.49 ->
                        Snackbar.make(b.llPrincipal, R.string.leve1, Snackbar.LENGTH_LONG).show()
                    in 18.50..24.99 ->
                        Snackbar.make(b.llPrincipal, R.string.normal, Snackbar.LENGTH_LONG).show()
                    in 25.00..29.99 ->
                        Snackbar.make(b.llPrincipal, R.string.pre, Snackbar.LENGTH_LONG).show()
                    in 30.00..34.99 ->
                        Snackbar.make(b.llPrincipal, R.string.leve, Snackbar.LENGTH_LONG).show()
                    in 35.00..39.99 ->
                        Snackbar.make(b.llPrincipal, R.string.media, Snackbar.LENGTH_LONG).show()
                    in 40.00..MAX_VALUE->
                        Snackbar.make(b.llPrincipal, R.string.morbida, Snackbar.LENGTH_LONG).show()


                }

            }
        })
    }
    private fun imc(): String {
        val altura2 = altura.toDouble() / 100
        val total = "%.2f".format(peso / (altura2 * altura2))
        return total.toString()
    }
//    private fun imc(p: Double, a: Double): String {
//        return ("%.2f".format(p/ (a.pow(2))))
//    }

}