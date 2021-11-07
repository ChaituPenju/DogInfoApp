package com.chaitupenju.dogsinfo.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.chaitupenju.dogsinfo.R
import com.chaitupenju.dogsinfo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        navController =
            (supportFragmentManager.findFragmentById(activityMainBinding.containerDogFragments.id) as NavHostFragment)
                .navController
    }
}