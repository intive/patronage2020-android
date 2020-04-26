package com.intive.patronage.smarthome.feature.home.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.intive.patronage.smarthome.R
import com.intive.patronage.smarthome.feature.home.viewmodel.SensorDialogViewModel

import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val dialogViewModel: SensorDialogViewModel by viewModel()
    private var image : HomeLayoutView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myView = inflater.inflate(R.layout.home_fragment, container, false)
        image = myView.findViewById(R.id.home)
        image?.create(dialogViewModel.items, fragmentManager!!)
        return myView
    }
    override fun onDestroyView() {
        image = null
        super.onDestroyView()
    }

}
