package com.aries.testkoanba.ui.activity

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.aries.testkoanba.R
import com.aries.testkoanba.interactor.MovieDetailPresenterImpl
import com.aries.testkoanba.network.NetworkConnection
import com.aries.testkoanba.network.response.MovieDetailResponse
import com.bumptech.glide.Glide
import gookios.com.movie_search.view.MovieDetailView
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_movie_detail.ivPoster
import kotlinx.android.synthetic.main.item_movie.*
import kotlinx.android.synthetic.main.layout_movie_detail.*
import kotlinx.android.synthetic.main.layout_movie_detail.tvTitle
import kotlinx.android.synthetic.main.layout_movie_detail.tvYear
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog

class MovieDetailActivity : AppCompatActivity(), MovieDetailView.View {
    private var presenter: MovieDetailPresenterImpl? = null
    private var imdbID: String? = null
    private var progressDialog: ProgressDialog? = null

    private var lenguange: String = "en-US"

    companion object {
        const val IMDB_ID: String = "id"

        fun navigate(activity: Activity, imdbId: String) {
            val intent = Intent(activity, MovieDetailActivity::class.java)
            intent.putExtra(IMDB_ID, imdbId)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        presenter = MovieDetailPresenterImpl(this)
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            imdbID = bundle.getString(IMDB_ID)
        }
        progressDialog = indeterminateProgressDialog("Loading")
        toolbar.setTitleTextColor(resources.getColor(R.color.colorWhite))
        toolbar.title = ""
        setSupportActionBar(toolbar)
        presenter!!.getMovieDetail(imdbID!! , lenguange)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.onDestroy()
    }

    override fun onSuccess(result: MovieDetailResponse) {
        setupView(result)
    }

    override fun onError(message: String) {
        alert("The resource you are looking for might have been removed, had its name changed, or is temporarily unavailable.") {
            title = "Ups.."
            yesButton {
                this.dismiss()
                finish()
            }
        }.show()
    }

    override fun showProgressBar() {
        progressDialog!!.show()
    }

    override fun hideProgressBar() {
        progressDialog!!.dismiss()
    }

    override fun isConnected(): Boolean {
        return NetworkConnection.isNetworkConnection(applicationContext)
    }

    private fun setupView(detail: MovieDetailResponse) {
        try {
            tvTitle.text = detail.title
            tvGenre.text = detail.genres[0].name
            tvDuration.text = detail.runtime + "M"
            tvYear.text = detail.release_date
            tvRating.text = detail.vote_average
            tvStatus.text = detail.status
            tvTagLine.text = detail.tagline
            tvHomePage.text = detail.homepage
            tvPlot.text = detail.overview
            Glide.with(this).load("https://image.tmdb.org/t/p/w185"+detail.poster_path).into(ivPoster)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}