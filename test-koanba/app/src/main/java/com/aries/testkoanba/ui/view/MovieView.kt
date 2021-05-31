package com.aries.testkoanba.ui.view

import com.aries.testkoanba.network.response.Movie
import com.aries.testkoanba.network.response.MovieResponse

interface MovieView {

    interface View : BaseView {
        fun onSuccess(result: MovieResponse, isAppend: Boolean)
        fun onAppend(result: ArrayList<Movie>)
        fun onError(message: String)
    }

    interface Presenter : BasePresenter {
        fun loadData(type: String ,lenguange: String ,page: String)
        fun loadMore(type: String ,lenguange: String ,page: String)
    }
}