package com.example.weatherapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemHistoryListBinding
import com.example.weatherapp.model.entities.Weather

class HistoryAdapter(context: Context) : RecyclerView.Adapter<HistoryAdapter.RecyclerItemViewHolder>() {
    private var data: List<Weather> = arrayListOf()
    private val context = context

    fun setData(data: List<Weather>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder =
        RecyclerItemViewHolder(
            ItemHistoryListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            context = context
        )


    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(private val binding: ItemHistoryListBinding, private val context: Context)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Weather) = with(binding) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                recyclerViewItem.text =
                    String.format("%s %d %s", data.city.city, data.temperature, context.resources.getString(Weather.getWeatherCondition(data.condition.toString())))
                root.setOnClickListener {
                    Toast.makeText(
                        itemView.context,
                        "${data.city.city}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}