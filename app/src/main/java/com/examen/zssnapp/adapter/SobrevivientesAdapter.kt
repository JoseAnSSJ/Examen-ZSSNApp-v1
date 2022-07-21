package com.examen.zssnapp.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.examen.zssnapp.Model.Response.SobrevivientesResponse
import com.examen.zssnapp.R
import com.examen.zssnapp.databinding.FragmentItemBinding


class SobrevivientesAdapter(val listDatos :List<SobrevivientesResponse?>, val listener:(SobrevivientesResponse)->Unit) :
    RecyclerView.Adapter<SobrevivientesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView =
            FragmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listDatos[position],listener)

    }

    override fun getItemCount(): Int {
        return listDatos.size
    }

    inner class ViewHolder(private val it: FragmentItemBinding) :
        RecyclerView.ViewHolder(it.root) {


        fun bind(item : SobrevivientesResponse?, listener:(SobrevivientesResponse)->Unit) {

                it.txtNombre.text = item!!.nombreSobreviviente

            it.root.setOnClickListener {
                listener(item!!)
            }
        }
    }
}