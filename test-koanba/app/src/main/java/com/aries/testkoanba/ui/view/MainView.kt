package com.aries.testkoanba.ui.view

import com.aries.testkoanba.network.response.Movie
import com.aries.testkoanba.network.response.MovieResponse

interface MainView {

    interface View : BaseView {
        fun onSuccess(result: MovieResponse, isAppend: Boolean)
        fun onAppend(result: ArrayList<Movie>)
        fun onError(message: String)
    }

    interface Presenter : BasePresenter {
        fun loadData(lenguange: String ,page: String)
        fun loadMore(lenguange: String ,page: String)
    }
}