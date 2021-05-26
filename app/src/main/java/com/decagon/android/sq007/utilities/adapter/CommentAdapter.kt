package com.decagon.android.sq007.utilities.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.decagon.android.sq007.R
import com.decagon.android.sq007.data.Comment

class CommentAdapter: RecyclerView.Adapter<CommentAdapter.MyViewHolder>(){

    private var commentList = emptyList<Comment>()

    inner class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        val tvCommentName = itemView.findViewById<TextView>(R.id.tvCommentName)
        val tvCommentEmail = itemView.findViewById<TextView>(R.id.tvCommentEmail)
        val tvCommentBody = itemView.findViewById<TextView>(R.id.tvCommentBody)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_comment_item, parent, false))

    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvCommentName.text = "NAME: ${commentList[position].name}"
        holder.tvCommentEmail.text = "EMAIL: ${commentList[position].email}"
        holder.tvCommentBody.text = "COMMENT BODY: ${commentList[position].body}"
    }

    fun setComment(comment: List<Comment>){
        commentList = comment
        notifyDataSetChanged()
    }

}