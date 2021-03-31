package com.mf.mipeso.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mf.mipeso.R
import com.mf.mipeso.model.Peso
import com.mf.mipeso.viewmodel.PesoViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var mPesoViewModel: PesoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // PesoViewModel
        mPesoViewModel = ViewModelProvider(this).get(PesoViewModel::class.java)
        mPesoViewModel.getPeso.observe(viewLifecycleOwner, Observer { peso ->
            adapter.setData(peso)
        })

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // Add menu deleteAll
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllPesos()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllPesos() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Si") { _, _ ->
            mPesoViewModel.deleteAllPesos()
            Toast.makeText(requireContext(), "Se ha borrado todo exitosamente!", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Borrar todo?")
        builder.setMessage("Estas seguro de borrar todo?")
        builder.create().show()
    }

}