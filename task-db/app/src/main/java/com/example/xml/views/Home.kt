package com.example.xml.views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.xml.CarouselAdapter
import com.example.xml.MainActivity
import com.example.xml.R
import com.example.xml.databinding.HomeBinding
import com.example.xml.utils.AppDatabase
import com.example.xml.utils.MovieAdapter
import com.example.xml.utils.MovieRepository
import com.example.xml.utils.NetworkResult
import com.example.xml.viewmodels.MovieViewModel
import com.example.xml.viewmodels.MovieViewModelFactory
import com.example.xml.viewmodels.NavigationEvent
import kotlinx.coroutines.launch
import kotlin.getValue
import kotlin.math.abs

class Home : Fragment(R.layout.home) {
    private val movieRepository by lazy {
        val database = AppDatabase.getDatabase(requireContext())
        MovieRepository(movieDao = database.movieDao(), apiKey = "baa7033988e4a440b53a833c6af66f3f")
    }
    private val movieViewModel: MovieViewModel by activityViewModels {
        MovieViewModelFactory(movieRepository)
    }

    private lateinit var binding: HomeBinding
    private lateinit var carouselAdapter: CarouselAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HomeBinding.bind(view)
        binding.viewModel = movieViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        carouselAdapter = CarouselAdapter(imageUrls = emptyList())
        binding.viewPager.adapter = carouselAdapter

        val transformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
            addTransformer { page, position -> page.scaleY = 0.85f + (1 - abs(position)) * 0.15f }
        }
        binding.viewPager.setPageTransformer(transformer)

        val movieAdapter = MovieAdapter(movies = emptyList())
        binding.gamesList.adapter = movieAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                movieViewModel.moviesState.collect { result ->
                    when (result) {
                        is NetworkResult.Loading -> {}
                        is NetworkResult.Success -> {
                            movieAdapter.updateMovies(result.data)

                            val topMoviesImages = result.data
                                .take(5)
                                .map { it.posterPath }

                            carouselAdapter.updateImages(topMoviesImages)
                        }
                        is NetworkResult.Error -> {
                            Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                movieViewModel.navigationEvent.collect { event ->
                    when (event) {
                        is NavigationEvent.NavigateToSetting -> {
                            (activity as? MainActivity)?.supportFragmentManager?.beginTransaction()?.apply {
                                replace(R.id.fragmentContainerView, Setting())
                                addToBackStack(null)
                                commit()
                            }
                        }
                        is NavigationEvent.OpenWiki -> {}
                        else -> {}
                    }
                }
            }
        }
    }
}