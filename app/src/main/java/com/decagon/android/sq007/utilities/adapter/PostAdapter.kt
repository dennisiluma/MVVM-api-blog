package com.decagon.android.sq007.utilities.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.decagon.android.sq007.R
import com.decagon.android.sq007.data.Post
import com.decagon.android.sq007.ui.views.PostDetailActivity

class PostAdapter:RecyclerView.Adapter<PostAdapter.MyViewHolder>(){
    private var mylist = emptyList<Post>()


    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
//        val userId = itemView.findViewById<TextView>(R.id.tvUserId)
//        val postId = itemView.findViewById<TextView>(R.id.tvPostId)
        val title = itemView.findViewById<TextView>(R.id.tvPostTitle)
//        val body = itemView.findViewById<TextView>(R.id.tvPostBody)

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, PostDetailActivity::class.java).apply {
                    putExtra("USER_id", mylist[super.getLayoutPosition()].userId.toString())
                    putExtra("POST_ID", mylist[super.getLayoutPosition()].id.toString())
                    putExtra("TITLE", mylist[super.getLayoutPosition()].title)
                    putExtra("BODY", mylist[layoutPosition].body)
                }
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_post_item, parent, false))

    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.userId.text = mylist[position].userId.toString()
//        holder.postId.text = mylist[position].id.toString()
        holder.title.text = mylist[position].title
//        holder.body.text = mylist[position].body
    }
    //this function colects api response data when it is called in the mainActivity.
    // it then stores the data to the myList decleared above for use by the recycler adapter
    fun setData(newList:List<Post>){
        mylist = newList
        notifyDataSetChanged()
    }
}