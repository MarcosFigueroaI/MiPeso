package com.mf.mipeso.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mf.mipeso.R
import com.mf.mipeso.model.Peso
import kotlinx.android.synthetic.main.custom_row.view.*
import java.util.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var pesoList = emptyList<Peso>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = pesoList[position]
        holder.itemView.fecha_txt.text = currentItem.dia + " " + currentItem.fecha
        holder.itemView.peso_txt.text = "Peso: ${currentItem.peso} Kg"

        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return pesoList.size
    }

    fun setData(peso: List<Peso>) {
        this.pesoList = peso
        notifyDataSetChanged()
    }
}