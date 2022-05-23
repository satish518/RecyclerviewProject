package com.example.recyclerviewproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewproject.Adapter.RetrofitAdapter
import com.example.recyclerviewproject.ViewModel.CatViewModel
import com.sample.lifecycleawarecomponents.RecyclerviewRetrofitMvvm.Model.TestingModelItem
import com.sample.lifecycleawarecomponents.RecyclerviewRetrofitMvvm.Network.Internet

class MainActivity  : AppCompatActivity(), RetrofitAdapter.ItemOnClickListener {

    lateinit var recyclerView: RecyclerView

    lateinit var list: List<TestingModelItem>

    lateinit var retrofitAdapter: RetrofitAdapter

    lateinit var catViewModel: CatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list = listOf()

        recyclerView = findViewById(R.id.recyclerView)
        var linearLayoutManager: LinearLayoutManager = GridLayoutManager(this, 1)
        recyclerView.layoutManager = linearLayoutManager
        retrofitAdapter = RetrofitAdapter(this, list, this)

        recyclerView.adapter = retrofitAdapter

        catViewModel = ViewModelProvider(this).get(CatViewModel::class.java)
        catViewModel.catListObserver.observe(this, Observer {
            if (it != null) {
                list = it
                retrofitAdapter.setCatList(list)
            }
        })

        if (Internet().checkForInternet(this)) {
            catViewModel.catApiCall(this)
        } else {
            Toast.makeText(this, resources.getString(R.string.InternetError), Toast.LENGTH_LONG).show()
        }



    }

    override fun imageClick(item: TestingModelItem?) {
        Toast.makeText(this, item!!.url, Toast.LENGTH_SHORT).show()
    }

}