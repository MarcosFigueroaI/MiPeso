package com.mf.mipeso.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mf.mipeso.R
import com.mf.mipeso.model.Peso
import com.mf.mipeso.viewmodel.PesoViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mPesoViewModel: PesoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mPesoViewModel = ViewModelProvider(this).get(PesoViewModel::class.java)

        view.txtModificarPeso.setText(args.currentPeso.peso.toString())

        view.btnModificar.setOnClickListener {
            updatePeso()
        }

        // Add menu delete
        setHasOptionsMenu(true)

        return view
    }

    private fun updatePeso() {
        val pesoKg = txtModificarPeso.text

        if (inputCheck(pesoKg)) {
            // Create Peso Object
            val updatePeso = Peso(args.currentPeso.id, args.currentPeso.dia, args.currentPeso.fecha, pesoKg.toString().toFloat(), null)
            // Update Current Peso
            mPesoViewModel.updatePeso(updatePeso)
            Toast.makeText(requireContext(), "Modificaste exitosamente!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Por favor llena todos los campos.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(peso: Editable): Boolean {
        return !(peso.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deletePeso()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deletePeso() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Si") { _, _ ->
            mPesoViewModel.deletePeso(args.currentPeso)
            Toast.makeText(requireContext(), "Borrado exitosamente: ${args.currentPeso.peso}", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Borrar ${args.currentPeso.peso}?")
        builder.setMessage("Estas seguro de borrar ${args.currentPeso.peso}?")
        builder.create().show()
    }

}