package com.examen.zssnapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.examen.zssnapp.Model.Response.SobrevivientesResponse
import com.examen.zssnapp.R
import com.examen.zssnapp.Repository.SobrevivientesRepository
import com.examen.zssnapp.adapter.SobrevivientesAdapter
import com.examen.zssnapp.databinding.MainFragmentBinding

/**
 * A fragment representing a list of Items.
 */
class MainFragment : Fragment() {

    private var columnCount = 4
    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        iniciar(view)

        return view
    }



    fun iniciar(view: View) {
        setViewModel()
        observables(view)
    }


    private fun onListItemClick(item: SobrevivientesResponse) {
        Toast.makeText(context, item.idSobreviviente, Toast.LENGTH_SHORT).show()
    }

    private fun observables(view: View) {
        try{
            viewModel.getSobrevieintes().observe(viewLifecycleOwner){
                if(!it.isNullOrEmpty()){
                    setReclycler(it,view)
                }else{
                    Toast.makeText(context,"Error al conseguir los sobrevivientes", Toast.LENGTH_LONG).show()
                }
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    private fun setReclycler(list: List<SobrevivientesResponse>, view: View) {
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = SobrevivientesAdapter(list) { it ->
                    onListItemClick(it)
                }
            }
        }
    }

    private fun setViewModel() {
        binding = MainFragmentBinding.inflate(layoutInflater)
        val repository = SobrevivientesRepository()
        val mainFactory = MainFactory(repository)
        viewModel = ViewModelProvider(this,mainFactory).get(MainViewModel::class.java)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}