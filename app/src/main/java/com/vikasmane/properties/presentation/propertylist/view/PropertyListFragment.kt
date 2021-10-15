package com.vikasmane.properties.presentation.propertylist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.vikasmane.properties.R
import com.vikasmane.properties.domain.entities.Item
import com.vikasmane.properties.presentation.propertydetails.PropertyItemClickListener
import com.vikasmane.properties.presentation.propertylist.viewmodel.PropertyListViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class PropertyListFragment : Fragment(), PropertyItemClickListener {
    private val viewModel: PropertyListViewModel by viewModels()
    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_property_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyPropertyRecyclerViewAdapter(emptyList(), this@PropertyListFragment)
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner) {
            if (view is RecyclerView) {
                with(view) {
                    adapter =
                        MyPropertyRecyclerViewAdapter(it.properties, this@PropertyListFragment)
                }
            }
        }
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
    }

    override fun onPropertyClicked(item: Item) {
        Toast.makeText(context, "${item.area} of type ${item.type} clicked", Toast.LENGTH_SHORT)
            .show()
    }
}