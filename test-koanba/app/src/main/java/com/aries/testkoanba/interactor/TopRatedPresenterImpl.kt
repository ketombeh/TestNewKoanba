package com.aries.testkoanba.interactor

import com.aries.testkoanba.Application
import com.aries.testkoanba.network.ApiConstant
import com.aries.testkoanba.network.ApiInterface
import com.aries.testkoanba.ui.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.annotations.NotNull

class TopRatedPresenterImpl : MainView.Presenter {
    var mainView: MainView.View
    var service: ApiInterface

    @NotNull
    var disposable: Disposable? = null

    constructor(mainView: MainView.View) {
        this.mainView = mainView
        this.service = Application.instance.apiService
    }

    override fun loadData(lenguange: String, page: String) {
        mainView.showProgressBar()
        if (mainView.isConnected()) {
            disposable = service.getMoviesTopRated(ApiConstant.API_KEY, lenguange , page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mainView.hideProgressBar()
                    if (it.isSuccessful) {
                        mainView.onSuccess(it.body()!!, false)
                    } else {
                        mainView.onError("Oops.. Something when wrong. \n Please try again..")
                    }
                }, {
                    mainView.hideProgressBar()
                    mainView.onError("Oops.. Something when wrong. \n Please try again..")
                })
        } else {
            mainView.hideProgressBar()
            mainView.onError("Please check your internet connection..")
        }
    }

    override fun loadMore(lenguange: String , page: String) {
        if (mainView.isConnected()) {
            disposable = service.getMoviesTopRated(ApiConstant.API_KEY, lenguange, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isSuccessful) {
                        mainView.onSuccess(it.body()!!, true)
                    } else {
                        mainView.onError("Oops.. Something when wrong. \n Please try again..")
                    }
                }, {
                    mainView.onError("Oops.. Something when wrong. \n Please try again..")
                })
        } else {
            mainView.onError("Please check your internet connection..")
        }
    }

    override fun onDestroy() {
        if (disposable != null) {
            disposable!!.dispose()
        }
    }
}