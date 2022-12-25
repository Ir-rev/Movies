package com.example.movies.ui.moviesInfoFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movies.databinding.MovieInfoFragmentBinding


class MovieInfoFragment : Fragment() {
    lateinit var viewModel: MovieInfoFragmentViewModel
    lateinit var binding: MovieInfoFragmentBinding
    private val args: MovieInfoFragmentArgs by navArgs()
    val BASE_URL = "https://s3-eu-west-1.amazonaws.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieInfoFragmentViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("checkResult", "onViewCreated: ${args.film}")

    }
}