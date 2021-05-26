package com.decagon.android.sq007.utilities.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.decagon.android.sq007.R
import com.decagon.android.sq007.data.MyPost

class CustomPostAdapter:RecyclerView.Adapter<CustomPostAdapter.MyViewHolder>() {

    private var myCustomPostList = emptyList<MyPost>()

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val tvCustomPostTitle = itemView.findViewById<TextView>(R.id.tvCustomPostTitle)
        val tvCustomPostBody = itemView.findViewById<TextView>(R.id.tvcustomPostBody)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomPostAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_custom_post_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myCustomPostList.size
    }

    override fun onBindViewHolder(holder: CustomPostAdapter.MyViewHolder, position: Int) {
        holder.tvCustomPostTitle.text = myCustomPostList[position].title
        holder.tvCustomPostBody.text = myCustomPostList[position].body
    }

    fun setData(result:List<MyPost> ){
        myCustomPostList = result
        notifyDataSetChanged()

    }
}