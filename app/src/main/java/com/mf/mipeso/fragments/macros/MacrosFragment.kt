package com.mf.mipeso.fragments.macros

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mf.mipeso.R
import kotlinx.android.synthetic.main.fragment_macros.*
import kotlinx.android.synthetic.main.fragment_macros.view.*

class MacrosFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_macros, container, false)

        // Hide btn
        view.btnGuardarMacros.visibility = View.GONE

        // Button Calcular Macros
        view.btnCalcularMacros.setOnClickListener {
            calcularMacros()
        }

        // Button Guardar Macros
        view.btnGuardarMacros.setOnClickListener {
            guardarMacros()
        }

        return view
    }

    private fun calcularMacros() {
        val peso = pesoMacros_et.text

        if (inputCheck(peso)) {
            // Calcular libras
            val libras = peso.toString().toFloat() * 2.2
            tv_libras.setText("${libras.toInt()} lbs")

            // Calcular calorias
            val calorias = libras * 12
            tv_calorias.setText("${calorias.toInt()} cal")

            // Calcular proteinas
            val proteinas = libras * 0.8
            tv_proteinas.setText("${proteinas.toInt()} gr")

            // Calcular carbohidratos
            val carbohidratos = libras * 1
            tv_carbohidratos.setText("${carbohidratos.toInt()} gr")

            // Calcular grasas
            val p = proteinas * 4
            val c = carbohidratos * 4
            val g = calorias - (p + c)
            val grasas = g / 9
            tv_grasas.setText("${grasas.toInt()} gr")

            Toast.makeText(requireContext(), "Estos son tus macronutrientes!", Toast.LENGTH_LONG).show()

            // Show btn
            btnGuardarMacros.visibility = View.VISIBLE
        } else {
            // Vacio
            Toast.makeText(requireContext(), "Favor de ingresar el peso.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(peso: Editable): Boolean {
        return !(peso.isEmpty())
    }

    private fun guardarMacros() {
        Toast.makeText(requireContext(), "En desarrollo...", Toast.LENGTH_LONG).show()
    }
}