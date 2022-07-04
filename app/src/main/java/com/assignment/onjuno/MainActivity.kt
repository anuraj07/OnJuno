package com.assignment.onjuno

import android.os.Bundle
import com.assignment.onjuno.base.ViewBindingBaseActivity
import com.assignment.onjuno.databinding.ActivityMainBinding

class MainActivity : ViewBindingBaseActivity<ActivityMainBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = "Home Page"

        binding.valueState.setOnClickListener {
            HomeActivity.start(this, HomeActivity.TYPE.VALUE_STATE)
        }

        binding.emptyState.setOnClickListener {
            HomeActivity.start(this, HomeActivity.TYPE.EMPTY_STATE)
        }
    }
}