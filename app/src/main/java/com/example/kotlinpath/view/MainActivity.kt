package com.example.kotlinpath.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kotlinpath.R
import com.example.kotlinpath.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ListViewModel
    private val countriesAdapter= CountryListAdapter(arrayListOf())
    lateinit var countriesList: RecyclerView
    lateinit var list_error: TextView
    lateinit var loading_view: ProgressBar
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countriesList= findViewById(R.id.countriesList)
        list_error=findViewById(R.id.list_error)
        loading_view=findViewById(R.id.loading_view)
        swipeRefreshLayout=findViewById(R.id.swiprefreshlayout)
        viewModel= ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        countriesList.apply {
            layoutManager =LinearLayoutManager(context)
            adapter=countriesAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing=false
            viewModel.refresh()
        }
        observeViewModel()

    }
    fun observeViewModel(){
        viewModel.countries.observe(this, Observer { countries ->
           countries?.let{
               countriesList.visibility=View.VISIBLE
               countriesAdapter.updateCountries(it)}
        })
        viewModel.countryLoadError.observe(this, Observer { isError->
            isError?.let{list_error.visibility= if(it) View.VISIBLE else View.GONE}
        })
        viewModel.loading.observe(this, Observer { isLoading->
            isLoading?.let { loading_view.visibility=if(it) View.VISIBLE else View.GONE
            if(it){
                list_error.visibility=View.GONE
                countriesList.visibility=View.GONE
            }
            }
        })
    }

}