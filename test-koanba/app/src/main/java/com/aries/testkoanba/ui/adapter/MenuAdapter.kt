package com.aries.testkoanba.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aries.testkoanba.R
import com.aries.testkoanba.listener.onItemClickListener
import com.aries.testkoanba.network.response.MenuModel

class MenuAdapter : RecyclerView.Adapter<MenuAdapterHolder>() {

    private val items:ArrayList<MenuModel> = ArrayList()
    private var context : Context? = null
    var listener : onItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapterHolder {
        context = parent.context
        val view : View = LayoutInflater.from(context).inflate(R.layout.menu_item ,parent ,  false)
        val holder = MenuAdapterHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return items?.size
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: MenuAdapterHolder, position: Int) {
        val item :MenuModel = items!![position]
        holder.imageView.setImageDrawable(context!!.applicationContext.getDrawable(item.icon))
        holder.txtView.setText(item.title)
        holder.cardView.setOnClickListener{
            listener?.onItemClick(it , position)
        }
    }

    fun addAllItem(item : ArrayList<MenuModel>){
        val count = item.size
        if(count != 0){
            items!!.clear()
            items!!.addAll(item)
            notifyDataSetChanged()
        }
    }

}