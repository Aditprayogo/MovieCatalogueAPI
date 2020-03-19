package com.aditprayogo.moviecatalogueapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aditprayogo.moviecatalogueapi.Movie.Movie
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.activity_detail_movie.progressBar
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.item_row_movie.*

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val  EXTRA_OVERVIEW = "extra_overview"
        const val EXTRA_RATING = "extra_rating"
        const val EXTRA_IMAGE = "extra_image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        showLoading(true)

        if (intent.getStringExtra(EXTRA_TITLE).isNotEmpty()) {
            showLoading(false)
            Glide.with(this).load(intent.getStringExtra(EXTRA_IMAGE)).into(iv_image)
            tv_title.setText(intent.getStringExtra(EXTRA_TITLE))
            tv_overview.setText(intent.getStringExtra(EXTRA_OVERVIEW))
            tv_rating.text = intent.getFloatExtra(EXTRA_RATING, 0F).toString()
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar!!.visibility = View.VISIBLE
        } else {
            progressBar!!.visibility = View.GONE
        }
    }
}
