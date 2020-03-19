package com.aditprayogo.moviecatalogueapi.TvShow

import org.json.JSONObject

class TvShow internal constructor(`object`: JSONObject) {
    var photo: String? = null
    var title: String? = null
    var overview: String? = null
    var rating = 0f

    init {
        try {
            val title = `object`.getString("name")
            val rating = `object`.getDouble("vote_average")
            val overview = `object`.getString("overview")
            val poster = "https://image.tmdb.org/t/p/w154" + `object`.getString("poster_path")
            this.photo = poster
            this.title = title
            this.overview = overview
            this.rating = rating.toFloat()

        }catch (e: Exception) {
            e.printStackTrace()
        }
    }
}