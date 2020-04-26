package com.gunaya.sharedviewmodeldemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_demo.*

class DemoFragment : Fragment() {

    private var demoInterfaceCallBack: DemoFragmentInterface? = null
    private val sharedVM: MainViewModel by activityViewModels()

    // We don't need Constructor to pass any extra to this fragment because we have access to the data from SharedVM
    // already.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_demo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("from FRAGMENT = ${sharedVM.data}")

        group.visibility = View.VISIBLE
        demoFragmentSendDataButton.visibility = View.VISIBLE

        //Usual way of initializing a callback Interface to communicate with the Activity
        demoInterfaceCallBack = activity as? DemoFragmentInterface

        //We can directly observe the viewModel's data and it's the same as the Activity's instance
        sharedVM.userEntity.observe(viewLifecycleOwner, Observer { user ->
            userName.text = "name: ${user.name}"
            userAge.text = "age: ${user.age}"
            userJob.text = "job: ${user.job}"
        })

        interfaceButton.setOnClickListener {
            demoInterfaceCallBack?.showToastCallback()
        }

        sharedVMButton.setOnClickListener {
            sharedVM.onShowVMToastClick()
        }

        interfaceShowUserButton.setOnClickListener {
            demoInterfaceCallBack?.showUserCallback()
        }

        sharedVMShowUserButton.setOnClickListener {
            sharedVM.showUserUsingSharedVM()
        }

        // TODO send a "hello world!" text to WorkshopFragment using workshopText: MutableLiveData
        demoFragmentSendDataButton.setOnClickListener {

        }
    }
}