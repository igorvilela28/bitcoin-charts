package com.igorvd.bitcoincharts.features.charts.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.igorvd.bitcoincharts.features.charts.databinding.ItemStatsCategoryBinding
import com.igorvd.bitcoincharts.features.charts.databinding.ItemStatsMetricBinding
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinMetric
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.features.charts.domain.model.StatsCategory

class StatsMetricAdapter(
    private val items: List<BitcoinMetric>,
    private val onClick: (ChartType) -> Unit
) : RecyclerView.Adapter<StatsMetricAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemStatsMetricBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(
        holder: StatsMetricAdapter.ViewHolder,
        position: Int
    ) {
        holder.bind(items[position], position)
    }

    override fun getItemCount() = items.count()

    inner class ViewHolder constructor(
        private val viewBinding: ItemStatsMetricBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(item: BitcoinMetric, position: Int) = viewBinding.apply {
            tvLabel.text = item.label
            tvValue.text = item.value
            tvViewChart.apply {
                isVisible = item.chartType != null
                item.chartType?.let { chartType ->
                    setOnClickListener { onClick(chartType) }
                }
            }
            viewSeparator.isVisible = position < items.lastIndex
        }
    }
}