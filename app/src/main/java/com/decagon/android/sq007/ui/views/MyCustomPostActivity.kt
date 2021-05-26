package com.decagon.android.sq007.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagon.android.sq007.R
import com.decagon.android.sq007.data.MyPost
import com.decagon.android.sq007.repository.Repository
import com.decagon.android.sq007.ui.viewmodels.MainActivityViewModel
import com.decagon.android.sq007.ui.viewmodels.MainActivityViewModelFactory
import com.decagon.android.sq007.ui.viewmodels.MyCustomPostFactory
import com.decagon.android.sq007.ui.viewmodels.MyCustomPostViewModel
import com.decagon.android.sq007.utilities.adapter.CustomPostAdapter
import com.decagon.android.sq007.utilities.adapter.PostAdapter

class MyCustomPostActivity : AppCompatActivity() {

    lateinit var viewModel: MyCustomPostViewModel

    private val myCustomAdapter by lazy { CustomPostAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_custom_post)

        val btSendPost = findViewById<Button>(R.id.btSendPost)

        //what will happen when the send button fromthis xml activity is clicked
        btSendPost.setOnClickListener {

            //get values from user input
            val title = findViewById<EditText>(R.id.etAddPostTitle).text.toString()
            val body = findViewById<EditText>(R.id.etAddPostBody).text.toString()


            val repository = Repository()
            val viewModelFactory = MyCustomPostFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(MyCustomPostViewModel::class.java)

            val myPost = MyPost(title = title,body = body,id = null, userId = null)

            viewModel.pushMyPost(myPost)

//                if (response.isSuccessful) response.body()?.let { postAdapter.setData(it) }
            //observe the reaponse
            viewModel.myCustomPost.observe(this, Observer {
                myCustomAdapter.setData(listOf(it.body()!!))
                Log.d("Value", "${it.body()}")
                val rvPostRecyclerView = findViewById<RecyclerView>(R.id.rvPostRecyclerView)
                rvPostRecyclerView.adapter = myCustomAdapter
                rvPostRecyclerView.layoutManager = LinearLayoutManager(this)
            })
        }
    }
}