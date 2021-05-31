package com.aries.testkoanba.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.aries.testkoanba.R
import com.aries.testkoanba.listener.onItemClickListener
import com.aries.testkoanba.network.response.MenuModel
import com.aries.testkoanba.ui.adapter.MenuAdapter
import com.aries.testkoanba.utils.ConvertUtil
import com.aries.testkoanba.utils.GridSpacingItemDecoration
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_main.*

private var menuAdapter : MenuAdapter? = null

class MainActivity : AppCompatActivity() , onItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menuAdapter = MenuAdapter()
        menuAdapter?.listener = this

        initMenu()
        initView()
    }

    private fun initView(){
        rv_menu.setHasFixedSize(true)
        rv_menu.layoutManager = GridLayoutManager(applicationContext , 3)
        rv_menu.addItemDecoration(GridSpacingItemDecoration(3 , ConvertUtil.dpToPx(baseContext , 4), true))
        rv_menu.itemAnimator = DefaultItemAnimator()
        rv_menu.adapter = menuAdapter

        val image = intArrayOf(R.drawable.gambar_movie_1 , R.drawable.gambar_movie_2 , R.drawable.gambar_movie_3)
        carouselView.pageCount = image.size
        carouselView.setImageListener(ImageListener{ position, imageView ->  imageView.setImageResource(image[position]) })
    }

    private fun initMenu(){
        val items :ArrayList<MenuModel> = ArrayList<MenuModel>()
        items.add(MenuModel(R.drawable.ic_movie , "Now Playing"))
        items.add(MenuModel(R.drawable.ic_popular , "Popular"))
        items.add(MenuModel(R.drawable.ic_top_rated , "Top Rate"))
        items.add(MenuModel(R.drawable.ic_upcoming , "Upcoming"))

        menuAdapter?.addAllItem(items)
    }

    override fun onItemClick(v: View?, position: Int?){
        if(position == 0){
            val intent = Intent(this@MainActivity , MovieActivity::class.java)
            intent.putExtra("title" , "Now Playing")
            intent.putExtra("titleString" , "now_playing")
            startActivity(intent)
        }else if(position == 1){
            val intent = Intent(this@MainActivity , MovieActivity::class.java)
            intent.putExtra("title" , "Popular Movie")
            intent.putExtra("titleString" , "popular")
            startActivity(intent)
        }else if(position == 2){
            val intent = Intent(this@MainActivity , MovieActivity::class.java)
            intent.putExtra("title" , "Top Rated")
            intent.putExtra("titleString" , "top_rated")
            startActivity(intent)
        }else if(position == 3){
            val intent = Intent(this@MainActivity , MovieActivity::class.java)
            intent.putExtra("title" , "Upcoming")
            intent.putExtra("titleString" , "upcoming")
            startActivity(intent)
        }
    }
}