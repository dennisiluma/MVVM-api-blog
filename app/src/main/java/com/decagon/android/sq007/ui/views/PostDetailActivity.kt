package com.decagon.android.sq007.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagon.android.sq007.R

import androidx.lifecycle.Observer
import com.decagon.android.sq007.data.Comment
import com.decagon.android.sq007.repository.Repository
import com.decagon.android.sq007.ui.viewmodels.PostDetailActivityViewModel
import com.decagon.android.sq007.ui.viewmodels.PostDetailActivityViewModelFactory
import com.decagon.android.sq007.utilities.adapter.CommentAdapter

class PostDetailActivity : AppCompatActivity() {

    lateinit var viewModel: PostDetailActivityViewModel

    private val commentAdapter by lazy { CommentAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        displayCommentRecyclerViewSetUp()


        val repository = Repository()
        val viewModelFactory = PostDetailActivityViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PostDetailActivityViewModel::class.java)

        val title = findViewById<TextView>(R.id.tvDetailPostTitle)
        val body = findViewById<TextView>(R.id.tvDetailPostBody)

        //Collect values intent comming from mainActivity RecyclerView
        val postTitle = intent.getStringExtra("TITLE")
        val postBody = intent.getStringExtra("BODY")
        val postId = intent.getStringExtra("POST_ID")
        val userId = intent.getStringExtra("USER_id")
        Log.d("post ID", "$postId")
        Log.d("USER_id ID", "$userId")
        title.text = postTitle.toString()
        body.text = postBody

        //pass the postId from intent to be used to query comments by postid from the api
        viewModel.getComments(Integer.parseInt(postId))
        viewModel.commentData.observe(this, Observer {response->
            if (response.isSuccessful) response.body()?.let { commentAdapter.setComment(it) }

        })

        //handle sent comments
        val btsendComment = findViewById<Button>(R.id.btSendComment)
        btsendComment.setOnClickListener {
            val etAddCommentName = findViewById<EditText>(R.id.etAddCommentName).text.toString()
            val etAddCommentEmail = findViewById<EditText>(R.id.etAddCommentEmail).text.toString()
            val etAddCommentBody = findViewById<EditText>(R.id.etAddCommentBody).text.toString()
            val myComment = Comment(Integer.parseInt(postId),Integer.parseInt(postId),etAddCommentName,etAddCommentEmail,etAddCommentBody)


            viewModel.pushComment(myComment)

            viewModel.myaddedComment.observe(this, Observer {
                response-> if (response.isSuccessful) Toast.makeText(this,
                "You added: ${response.body()?.name}, ${response.body()?.email}, ${response.body()?.body} to the database", Toast.LENGTH_LONG).show()
            })
        }
    }


    //get comment recyclerview xml and adapyter it to the Comment recyclerView Class
    fun displayCommentRecyclerViewSetUp(){
        val rvDetailRecyclerView = findViewById<RecyclerView>(R.id.rvDetailRecyclerView)
        rvDetailRecyclerView.adapter = commentAdapter
        rvDetailRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}