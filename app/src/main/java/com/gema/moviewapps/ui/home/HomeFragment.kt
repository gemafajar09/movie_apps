package com.gema.moviewapps.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gema.moviewapps.BuildConfig
import com.gema.moviewapps.data.Resouce
import com.gema.moviewapps.databinding.FragmentHomeBinding
import com.gema.moviewapps.util.Constan
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding

    private lateinit var homeAdapter: HomeAdapter

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupList()
        getDataHome()
        setupRecyclerView()
    }

    private fun getDataHome() {
        viewModel.getHome(Constan.getToken).observe(viewLifecycleOwner) { result ->
            when(result){
                is Resouce.Loading -> {
                    //looading
                }
                is Resouce.Success -> {
                    val dataMovie = result.data
                    homeAdapter.submitList(dataMovie.results)
                    setupRecyclerView()
                }
                is Resouce.Error -> {
                    //looading
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding?.rvMovie?.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
        }
    }

    private fun setupList() {
        homeAdapter = HomeAdapter {

        }

    }

}