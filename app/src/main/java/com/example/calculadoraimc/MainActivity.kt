package com.example.calculadoraimc

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Color.HSVToColor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.calculadoraimc.databinding.ActivityMainBinding
import com.google.android.material.internal.ContextUtils.getActivity
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

//        AlertDialog.Builder(context!!).setView(R.layout.custom_dialog)
//            .setPositiveButton(R.string.aceptar,null)
//            .show()


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
                val resultado =  b.tvResult.text.toString().toDouble()
                when (resultado){
                     in MIN_VALUE..16.00 ->
                        Snackbar.make(b.llPrincipal, R.string.severa, Snackbar.LENGTH_LONG)
                            .withColor(getColor(R.color.darck_blue))
                            .show()
                    in 16.00..16.99 ->
                        Snackbar.make(b.llPrincipal, R.string.moderada, Snackbar.LENGTH_LONG)
                            .withColor(getColor(R.color.blue)).show()
                    in 17.00..18.49 ->
                        Snackbar.make(b.llPrincipal, R.string.leve1, Snackbar.LENGTH_LONG)
                            .withColor(getColor(R.color.turquese)).show()
                    in 18.50..24.99 ->
                        Snackbar.make(b.llPrincipal, R.string.normal, Snackbar.LENGTH_LONG)
                            .withColor(getColor(R.color.green)).show()
                    in 25.00..29.99 ->
                        Snackbar.make(b.llPrincipal, R.string.pre, Snackbar.LENGTH_LONG)
                            .withColor(getColor(R.color.primaryDarkColor)).show()
                    in 30.00..34.99 ->
                        Snackbar.make(b.llPrincipal, R.string.leve, Snackbar.LENGTH_LONG)
                            .withColor(getColor(R.color.yellow)).show()
                    in 35.00..39.99 ->
                        Snackbar.make(b.llPrincipal, R.string.media, Snackbar.LENGTH_LONG)
                            .withColor(getColor(R.color.secondaryColor)).show()
                    in 40.00..MAX_VALUE->
                        Snackbar.make(b.llPrincipal, R.string.morbida, Snackbar.LENGTH_LONG)
                            .withColor(getColor(R.color.red)).setAction("Ver Tabla") {
                                onAlertDialog(b.root)
                            }
                            .show()
                }

            }
        })
    }
    private fun imc(): String {
        val altura2 = altura.toDouble() / 100
        val total = "%.2f".format(peso / (altura2 * altura2))
        return total.toString()
    }

    fun Snackbar.withColor(@ColorInt colorInt: Int): Snackbar{
        this.view.setBackgroundColor(colorInt)
        return this
    }

    // When User cilcks on dialog button, call this method
    fun onAlertDialog(view: View) {
        //Instantiate builder variable
        val builder = AlertDialog.Builder(view.context)

        builder.setView(R.layout.custom_dialog)
        builder.setPositiveButton(R.string.aceptar, null)
        builder.show()
    }
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return activity?.let {
//            val builder = AlertDialog.Builder(it)
//            // Get the layout inflater
//            val inflater = requireActivity().layoutInflater;
//
//            // Inflate and set the layout for the dialog
//            // Pass null as the parent view because its going in the dialog layout
//            builder.setView(inflater.inflate(R.layout.custom_dialog, null))
//                // Add action buttons
//                .setPositiveButton(R.string.aceptar,
//                    DialogInterface.OnClickListener { dialog, id ->
//                        // sign in the user ...
//                    })
//
//            builder.create()
//        } ?: throw IllegalStateException("Activity cannot be null")
//    }
}