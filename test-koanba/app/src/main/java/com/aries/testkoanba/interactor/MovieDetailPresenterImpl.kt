package com.aries.testkoanba.interactor

import com.aries.testkoanba.Application
import com.aries.testkoanba.network.ApiConstant
import com.aries.testkoanba.network.ApiInterface
import gookios.com.movie_search.view.MovieDetailView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieDetailPresenterImpl : MovieDetailView.Presenter {
    var view: MovieDetailView.View
    var service: ApiInterface

    @NonNull
    var disposable: Disposable? = null

    constructor(view: MovieDetailView.View) {
        this.view = view
        this.service = Application.instance.apiService
    }

    override fun getMovieDetail(id: String , lenguange: String) {
        view.showProgressBar()
        if (view.isConnected()) {
            disposable = service.getDetailMovies(id , ApiConstant.API_KEY, lenguange)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.hideProgressBar()
                    if (it.isSuccessful) {
                        view.onSuccess(it.body()!!)
                    } else {
                        view.onError("Oops..\nSomething when wrong, please refresh page..")
                    }
                }, {
                    view.hideProgressBar()
                    view.onError("Oops..\nSomething when wrong, please refresh page..")
                })
        }
    }

    override fun onDestroy() {
        if (disposable != null) {
            disposable!!.dispose()
        }
    }
}