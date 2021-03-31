package com.mf.mipeso.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mf.mipeso.R
import com.mf.mipeso.model.Peso
import com.mf.mipeso.viewmodel.PesoViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class AddFragment : Fragment() {

    private lateinit var mPesoViewModel: PesoViewModel
    var nombreDia: String = ""
    var fecha: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mPesoViewModel = ViewModelProvider(this).get(PesoViewModel::class.java)

        // Date Picker
        val today = Calendar.getInstance()
        val day = today.get(Calendar.DAY_OF_WEEK)
        val dia = today.get(Calendar.DAY_OF_MONTH)
        val mes = today.get(Calendar.MONTH).plus(1)
        val año = today.get(Calendar.YEAR)
        fecha = "$dia/$mes/$año"
        //println(fecha)

        // Nombre Dia
        when (day) {
            1 -> nombreDia = "Domingo"
            2 -> nombreDia = "Lunes"
            3 -> nombreDia = "Martes"
            4 -> nombreDia = "Miercoles"
            5 -> nombreDia = "Jueves"
            6 -> nombreDia = "Viernes"
            7 -> nombreDia = "Sabado"
        }
        //println(nombreDia)

        // Seleccionar la fecha
        view.datePicker.init(
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ) { view, year, month, day ->
            val month = month + 1
            fecha = "$day/$month/$year"
            val msg = "Seleccionaste: $day/$month/$year"
            Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
            println(msg)

            // Obtener nombre del dia
            val dayOfWeekFormatter: DateTimeFormatter =
                DateTimeFormatter.ofPattern("EEEE", Locale.US)
            val date: LocalDate = LocalDate.of(
                year, month, day
            )
            when (date.format(dayOfWeekFormatter)) {
                "Sunday" -> nombreDia = "Domingo"
                "Monday" -> nombreDia = "Lunes"
                "Tuesday" -> nombreDia = "Martes"
                "Wednesday" -> nombreDia = "Miercoles"
                "Thursday" -> nombreDia = "Jueves"
                "Friday" -> nombreDia = "Viernes"
                "Saturday" -> nombreDia = "Sabado"
            }
        }

        view.btnAgregar.setOnClickListener {
            agregarDatos()
        }

        return view
    }

    private fun agregarDatos() {
        val pesoKg = txtPeso.text

        if (inputCheck(fecha, pesoKg)) {
            // Create Peso Object
            val peso = Peso(0, nombreDia, fecha, pesoKg.toString().toFloat())
            // Add Data to Database
            mPesoViewModel.addPeso(peso)
            Toast.makeText(requireContext(), "Agregado exitosamente!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Favor de llenar todos los campos", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(fecha: String, peso: Editable): Boolean {
        return !(TextUtils.isEmpty(fecha) && peso.isEmpty())
    }

}