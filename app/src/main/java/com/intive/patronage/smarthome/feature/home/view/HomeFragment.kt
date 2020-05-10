package com.intive.patronage.smarthome.feature.home.view

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.intive.patronage.smarthome.R
import com.intive.patronage.smarthome.feature.home.viewmodel.HomeSharedViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {

    private val homeSharedViewModel: HomeSharedViewModel by sharedViewModel()
    lateinit var image: HomeLayoutView
    lateinit var gestureDetector: GestureDetector

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        image = view.findViewById(R.id.home)
        getSensors()
        initGestureDetector()
        image.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }
        observeToastMessage()
        return view
    }

    private fun initGestureDetector() {
        gestureDetector =
            GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onLongPress(e: MotionEvent?) {
                    super.onLongPress(e)
                    val x = e!!.x
                    val y = e.y
                    val sensorDialog = SensorDialog()
                    homeSharedViewModel.setSensorPosition(x, y, image.width, image.height)
                    sensorDialog.show(fragmentManager!!, "SensorList")
                }
            })
    }

    private fun getSensors() {
        homeSharedViewModel.items.observe(this, Observer {
            if (it != null) {
                image.setData(it)
            }
        })
    }

    private fun observeToastMessage(){
        homeSharedViewModel.toastMessage.observe(this, Observer {
            if (it != null) {
                val message = getString(it)
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}
