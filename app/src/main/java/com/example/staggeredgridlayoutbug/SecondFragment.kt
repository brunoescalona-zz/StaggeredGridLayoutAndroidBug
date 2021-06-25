package com.example.staggeredgridlayoutbug

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.staggeredgridlayoutbug.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var ui: FragmentSecondBinding? = null

    private val images = listOf(
        R.drawable.first,
        R.drawable.second,
        R.drawable.second,
        R.drawable.first,
        R.drawable.second,
        R.drawable.first
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ui = FragmentSecondBinding.inflate(inflater, container, false)
        return ui?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ui?.recyclerView?.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = CustomAdapter(images)
        }
    }

    override fun onDestroy() {
        ui = null
        super.onDestroy()
    }
}