package com.igorvd.bitcoincharts.features.charts.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.igorvd.bitcoincharts.features.charts.databinding.ItemStatsCategoryBinding
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.features.charts.domain.model.StatsCategory

class StatsCategoryAdapter(
    private val items: List<StatsCategory>,
    private val onClick: (ChartType) -> Unit
) : RecyclerView.Adapter<StatsCategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemStatsCategoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(
        holder: StatsCategoryAdapter.ViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.count()

    inner class ViewHolder constructor(
        private val viewBinding: ItemStatsCategoryBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(item: StatsCategory) = viewBinding.apply {
            tvTitle.text = item.title
            rvCategoryMetrics.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = StatsMetricAdapter(item.metrics, onClick)
            }
        }
    }
}