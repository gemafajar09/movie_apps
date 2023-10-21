package com.gema.moviewapps.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.gema.moviewapps.data.Resouce
import com.gema.moviewapps.databinding.ActivityHomeBinding
import com.gema.moviewapps.util.Constan
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeAdapter : HomeAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupList()
        getDataHome()

        binding.rfSwipe.setOnRefreshListener {
            getDataHome()
            binding.rfSwipe.isRefreshing = false
        }
    }

    private fun getDataHome() {
        viewModel.getHome(Constan.getToken).observe(this@HomeActivity) { result ->
            when(result){
                is Resouce.Loading -> {
                    binding.apply {
                        shimmerLoading.visibility = View.VISIBLE
                        rvMovie.visibility = View.GONE
                    }
                }

                is Resouce.Success -> {
                    val dataMovie = result.data

                    homeAdapter.submitList(dataMovie.results)
                    setupRecyclerView()

                    binding.shimmerLoading.visibility = View.GONE
                    binding.rvMovie.visibility = View.VISIBLE
                }

                is Resouce.Error -> {}
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvMovie?.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(this@HomeActivity)
            binding!!.rvMovie.layoutManager = GridLayoutManager(this@HomeActivity, 2)
            setHasFixedSize(true)
        }
    }

    private fun setupList() {
        homeAdapter = HomeAdapter {

        }
    }
}