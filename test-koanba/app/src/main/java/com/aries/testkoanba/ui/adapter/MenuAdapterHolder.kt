package com.aries.testkoanba.ui.adapter

import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.aries.testkoanba.R

class MenuAdapterHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

    var cardView: CardView
    var txtView : TextView
    var imageView : ImageView
    private var listener : AdapterView.OnItemClickListener? = null

    override fun onClick(v: View) {
//        listener?.onItemClick(v , adapterPosition)
    }

    init{
        cardView = itemView.findViewById(R.id.cardview)
        txtView = itemView.findViewById(R.id.tv_menu_title)
        imageView = itemView.findViewById(R.id.iv_menu_icon)
        itemView.setOnClickListener(this)
    }
}