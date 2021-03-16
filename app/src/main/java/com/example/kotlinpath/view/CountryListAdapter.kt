package com.example.kotlinpath.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpath.R
import com.example.kotlinpath.model.Country
import com.example.kotlinpath.util.getProgressDrawable
import com.example.kotlinpath.util.loadImage

class CountryListAdapter(var countries: ArrayList<Country>) : RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= CountryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country,parent,false)
    )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount()=countries.size

    class CountryViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val countryName: TextView = view.findViewById(R.id.name)
        private val imageView: ImageView=view.findViewById(R.id.imageView)
        private val countryCapital:TextView=view.findViewById(R.id.capital)
        private val progressDrawable= getProgressDrawable(view.context)

        fun bind(country: Country){
            countryName.text=country.countryName
            countryCapital.text=country.capital
            imageView.loadImage(country.flag,progressDrawable)

        }
    }
    fun updateCountries(newCountries: List<Country>){
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }
}