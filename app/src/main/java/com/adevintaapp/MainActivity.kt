package com.adevintaapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adevintaapp.databinding.ActivityMainBinding
import com.adevintaapp.ui.BeerVM
import com.adevintaapp.ui.custom.Info
import com.adevintaapp.ui.util.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: BeerVM by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initUI()
        initObservers()
    }

    private fun initUI() {
        viewModel.initLoading()
    }

    private fun initObservers() {
        observe(viewModel.isErrorThrown) { isErrorActive ->
            if (isErrorActive) {
                Toast.makeText(this, "An error occurred while fetching the random beer", Toast.LENGTH_LONG).show()
            }
        }

        observe(viewModel.beer) {
            binding.beerInfo.setDefaults(
                it.name,
                it.tagline,
                it.imageUrl
            )
        }

        observe(viewModel.settings) {
            val allInfo = Info(
                it.nameSize,
                it.nameColor,
                it.taglineSize,
                it.taglineColor,
                it.taglineVisible,
                it.imageHeight,
                it.imageWidth,
                it.imageRoundRadius
            )

            binding.beerInfo.setAllSettings(allInfo)
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.appStopped(binding.beerInfo.getAllSettings())
    }
}