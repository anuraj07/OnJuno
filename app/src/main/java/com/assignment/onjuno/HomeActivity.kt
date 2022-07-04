package com.assignment.onjuno

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.assignment.onjuno.base.ViewBindingBaseActivity
import com.assignment.onjuno.databinding.ActivityHomeBinding
import com.assignment.onjuno.viewmodels.HomeViewModel
import com.assignment.onjuno.viewmodels.HomeViewModelFactory
import javax.inject.Inject

class HomeActivity : ViewBindingBaseActivity<ActivityHomeBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_home

    enum class TYPE { VALUE_STATE, EMPTY_STATE}

    private var type: TYPE? = null
    lateinit var homeViewModel: HomeViewModel
    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as OnJunoApplication).applicationComponent.inject(this)
        homeViewModel = ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)

        type = intent.getSerializableExtra(TYPE) as TYPE?

        showToast(type.toString())

        if (type == HomeActivity.TYPE.EMPTY_STATE)
            observeEmptyState()
        else if (type == HomeActivity.TYPE.VALUE_STATE)
            observeValueState()
    }

    private fun observeEmptyState() {
        homeViewModel.emptyStateLiveData.observe(this) {
            Log.d("test", it.toString())
        }
    }

    private fun observeValueState() {
        homeViewModel.valueStateLiveData.observe(this) {
            Log.d("test", it.toString())
        }
    }

    companion object {
        private const val TYPE = "type"
        fun start (context: Context, type: TYPE) {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra(TYPE, type)
            context.startActivity(intent)
        }
    }
}