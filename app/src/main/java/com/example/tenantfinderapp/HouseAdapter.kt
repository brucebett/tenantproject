package com.example.tenantfinderapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tenantfifnderapp.R

class HouseAdapter(var mlist:List<Houses>) :
    RecyclerView.Adapter<HouseAdapter.HousesViewholder>(){
    var onItemclick : ((Houses) -> Unit)? = null
     class HousesViewholder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val image:ImageView = itemView.findViewById(R.id.imghouse)
        val titletv:TextView = itemView.findViewById(R.id.title)

    }

    fun setFilteredList(mlist: List<Houses>){
        this.mlist = mlist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HousesViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_items,parent,false)
        return HousesViewholder(view)
    }

    override fun getItemCount(): Int {
        return mlist.size
    }

    override fun onBindViewHolder(holder: HousesViewholder, position: Int) {
        val HousesData = mlist[position]
        holder.image.setImageResource(mlist[position].image)
        holder.titletv.text = mlist[position].title
        holder.itemView.setOnClickListener {
            onItemclick?.invoke(HousesData)
        }
    }
}




