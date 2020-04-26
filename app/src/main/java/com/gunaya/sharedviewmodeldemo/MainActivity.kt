package com.gunaya.sharedviewmodeldemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

// Adding the interface that communicates with the fragment
class MainActivity : AppCompatActivity(), DemoFragmentInterface {

    private lateinit var factory: MainViewModelFactory
    // Initializing the viewModel with the factory constructor (just an example, factory is not used in this workshop)
    private val viewModel: MainViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        factory = MainViewModelFactory("hello from factory")
        println("from ACTIVITY = ${viewModel.data}")

        viewModel.showSharedVMToast.observe(this, Observer {
            Toast.makeText(this, "Hello from sharedVM", Toast.LENGTH_SHORT).show()
        })

        viewModel.showInterfaceToast.observe(this, Observer {
            Toast.makeText(this, "Hello from interface", Toast.LENGTH_SHORT).show()
        })

        supportFragmentManager.beginTransaction().add(R.id.demoFragmentContainer, DemoFragment()).commit()
        supportFragmentManager.beginTransaction().add(R.id.workshopFragmentContainer, WorkshopFragment()).commit()
    }

    // Overriding the callbacks performed in the fragment.
    override fun showToastCallback() {
        viewModel.onShowInterfaceToastClick()
    }

    override fun showUserCallback() {
        viewModel.showUserUsingInterface()
    }
}
