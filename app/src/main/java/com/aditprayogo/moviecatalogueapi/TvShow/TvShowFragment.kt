package com.aditprayogo.moviecatalogueapi.TvShow


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditprayogo.moviecatalogueapi.DetailMovieActivity
import com.aditprayogo.moviecatalogueapi.Movie.Movie
import com.aditprayogo.moviecatalogueapi.Movie.MovieAdapter
import com.aditprayogo.moviecatalogueapi.Movie.MovieViewModel
import com.aditprayogo.moviecatalogueapi.R
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_movie.progressBar
import kotlinx.android.synthetic.main.fragment_tv_show.*

/**
 * A simple [Fragment] subclass.
 */
class TvShowFragment : Fragment() {

    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var adapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading(true)
        rv_tvShow.setHasFixedSize(true)

        adapter = TvShowAdapter()
        adapter.notifyDataSetChanged()

        rv_tvShow.layoutManager = LinearLayoutManager(context!!.applicationContext)
        rv_tvShow.adapter = adapter

        adapter.setOnItemClickCallback(object : TvShowAdapter.OnItemClickCallback{
            override fun onItemClicked(data: TvShow) {

                val intent = Intent(context, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_TITLE, data.title)
                intent.putExtra(DetailMovieActivity.EXTRA_OVERVIEW, data.overview)
                intent.putExtra(DetailMovieActivity.EXTRA_IMAGE, data.photo)
                intent.putExtra(DetailMovieActivity.EXTRA_RATING, data.rating)
                startActivity(intent)
            }

        })

        tvShowViewModel= ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(TvShowViewModel::class.java)

        tvShowViewModel.getTvShow().observe(viewLifecycleOwner, Observer { tvShowItem ->
            if (tvShowItem != null) {
                adapter.setData(tvShowItem)
                showLoading(false)
            }

        })
        tvShowViewModel.setTvShow()


    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar!!.visibility = View.VISIBLE
        } else {
            progressBar!!.visibility = View.GONE
        }
    }


}
