package com.decagon.android.sq007.ui.views

import android.content.Intent
import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagon.android.sq007.R
import com.decagon.android.sq007.repository.Repository
import com.decagon.android.sq007.ui.viewmodels.MainActivityViewModel
import com.decagon.android.sq007.ui.viewmodels.MainActivityViewModelFactory
import com.decagon.android.sq007.utilities.adapter.PostAdapter
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    lateinit var viewModel: MainActivityViewModel

    private val postAdapter by lazy { PostAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationDrawableBarStuffsController()

        /* Navigation Home drawable starts here */


        /* Navigation Home drawable ends here  */

        val repository = Repository()
        val viewModelFactory = MainActivityViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)
        setUpRecyclerView()


            val option:HashMap<String, String> = HashMap()
//            //first parameter is key 2nd is value
            option.put("_sort", "id")
            option["_order"] = "desc"
        val svSearchByUserId = findViewById<Button>(R.id.btSearchById)

        //fecch data from api and store them in the recycler view list so it can be used by the recycler view.
        // This is more efficient than passing the items as a constructor to the recycler view adapter
        viewModel.getMultiQueryPost(null, option)
        viewModel.myMultiQueryPost.observe(this, Observer { response->
            if (response.isSuccessful) response.body()?.let { postAdapter.setData(it) }

        })
    }


    //map the recycler view adapter xml to the PostAdapter class
    fun setUpRecyclerView(){
        val rvRecyclerView = findViewById<RecyclerView>(R.id.rvRecyclerView)
        rvRecyclerView.adapter = postAdapter
        rvRecyclerView.layoutManager = LinearLayoutManager(this)
    }

        /* Navigation bar activities start here  */
    fun navigationDrawableBarStuffsController(){
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        //control item in vavMenu clickable
            val nav_view = findViewById<NavigationView>(R.id.nav_view)
            nav_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.itemAddPost -> {
                    val intent = Intent(this, MyCustomPostActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }
    //handles opening and closing of toggle
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    /* navigation bar activities ends here */
}