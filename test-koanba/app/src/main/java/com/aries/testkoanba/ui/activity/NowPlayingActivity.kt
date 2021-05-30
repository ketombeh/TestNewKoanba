package com.aries.testkoanba.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.aries.testkoanba.R
import com.aries.testkoanba.interactor.NowPlayingPresenterImpl
import com.aries.testkoanba.network.NetworkConnection
import com.aries.testkoanba.network.response.Movie
import com.aries.testkoanba.network.response.MovieResponse
import com.aries.testkoanba.ui.adapter.NowPlayingAdapter
import com.aries.testkoanba.ui.view.MainView
import kotlinx.android.synthetic.main.activity_now_playing.*

class NowPlayingActivity : AppCompatActivity() , MainView.View, SwipeRefreshLayout.OnRefreshListener{
    private var presenterImpl: NowPlayingPresenterImpl? = null
    private var adapter: NowPlayingAdapter? = null
    private lateinit var intenString: String

    private var page: Int = 1
    private var lenguange: String = "en-US"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_now_playing)
        presenterImpl = NowPlayingPresenterImpl(this)
        adapter = NowPlayingAdapter(this )
        setSupportActionBar(findViewById(R.id.toolbar))
        initView()

        if(intent.extras != null){
            val bundle = intent.extras
            intenString = bundle?.getString("title").toString()
            presenterImpl!!.loadData(lenguange , page++.toString())
        }

        toolbar.title = intenString
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun initView() {
        swipeRefresh.setOnRefreshListener(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = this.adapter

        adapter?.setListener(object : NowPlayingAdapter.OnItemClickListener {
            override fun onItemClick(v: View, position: Int) {
                val item = adapter!!.getItem(position)
                var intent = Intent(applicationContext , MovieDetailActivity::class.java)
                intent.putExtra("id" , item.id)
                startActivity(intent)
            }

            override fun onLoadMore() {
                presenterImpl!!.loadMore(lenguange ,page++.toString())
            }
        })
    }

    override fun onSuccess(result: MovieResponse, isAppend: Boolean) {
        if (result.results.size > 0) {
            adapter!!.addItems(result.results, isAppend)
        }
    }

    override fun onAppend(result: ArrayList<Movie>) {
        TODO("Not yet implemented")
    }

    override fun onError(message: String) {

    }

    override fun showProgressBar() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideProgressBar() {
        swipeRefresh.isRefreshing = false
    }

    override fun isConnected(): Boolean {
        return NetworkConnection.isNetworkConnection(applicationContext)
    }

    override fun onRefresh() {
        try {
            if (adapter!!.itemCount > 0) {
                hideProgressBar()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}