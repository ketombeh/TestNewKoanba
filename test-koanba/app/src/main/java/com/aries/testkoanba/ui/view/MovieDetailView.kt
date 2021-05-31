package gookios.com.movie_search.view

import com.aries.testkoanba.network.response.MovieDetailResponse
import com.aries.testkoanba.ui.view.BasePresenter
import com.aries.testkoanba.ui.view.BaseView


interface MovieDetailView {

    interface View: BaseView {
        fun onSuccess(result: MovieDetailResponse)
        fun onError(message: String)
    }

    interface Presenter: BasePresenter {
        fun getMovieDetail(movieId: String , lenguange: String)
    }
}