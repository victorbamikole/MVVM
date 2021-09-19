package com.example.architecturetask9.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturetask9.R
import com.example.architecturetask9.adapter.RecyclerViewAdapter
import com.example.architecturetask9.adapter.RecyclerViewAdapter2
import com.example.architecturetask9.adapter.onItemClickListener
import com.example.architecturetask9.model.CommentsItem
import com.example.architecturetask9.model.PostListsItem
import com.example.architecturetask9.viewmodel.DetailPostViewModel
import com.example.architecturetask9.viewmodel.MainActivityViewModel

class DetailsActivity : AppCompatActivity(), RecyclerViewAdapter2.onBtnClickListener {
    private lateinit var recyclerAdapter: RecyclerViewAdapter2
    lateinit var recyclerView2: RecyclerView
    lateinit var replyBtn: Button
    lateinit var postBody: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        val intent: Intent = getIntent()
        var postItems: PostListsItem? = intent.getParcelableExtra("postlists")
        Log.d("mainActivity", "onCreate: $postItems")
        var position = intent.getIntExtra("pos", 1)
        val id = position + 1
        postBody = findViewById(R.id.postBody)

        postBody.text  = postItems?.body


        recyclerView2 = findViewById(R.id.recyclerView2)
        recyclerView2.layoutManager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView2.addItemDecoration(decoration)
//        replyBtn = findViewById(R.id.reply)!!

//        replyBtn.setOnClickListener {
//            val i = Intent(this, CreateComment::class.java)
//            startActivity(i)
//        }

        initViewModel2(postItems?.id)

    }

    fun bindDetails(it: List<CommentsItem>) {

    }

    private fun initViewModel(id: Int?): DetailPostViewModel {

        val viewModel = ViewModelProvider(this).get(DetailPostViewModel::class.java)
        viewModel.commentsLiveData.observe(this, {
            if (it != null) {
                bindDetails(it)
            } else {
                Toast.makeText(this, "error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.callComments(id!!)
        return viewModel

    }

    private fun initViewModel2(id: Int?): DetailPostViewModel {
        val viewModel = ViewModelProvider(this).get(DetailPostViewModel::class.java)
        viewModel.getRecyclerListObserver2().observe(this, {
            if (it != null) {
                bindDetails(it)
                recyclerAdapter = RecyclerViewAdapter2(this)
                recyclerAdapter.comment = (it as MutableList<CommentsItem>)
                recyclerAdapter.notifyDataSetChanged()
                recyclerView2.adapter = recyclerAdapter

            } else {
                Toast.makeText(this, "error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.callComments(id!!)
        return viewModel
    }

    override fun onBtnClick(position: Int) {
        val intent: Intent = Intent(this, CreateComment::class.java)
//        intent.putExtra("postlists", objectData)
//        intent.putExtra("pos", position)
        startActivity(intent)
    }

}