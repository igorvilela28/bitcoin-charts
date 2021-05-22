package com.igorvd.bitcoincharts.features.charts.presentation.chart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.igorvd.bitcoincharts.core.domain.service.datetime.DateTimeService
import com.igorvd.bitcoincharts.core.presentation.extensions.collect
import com.igorvd.bitcoincharts.core.presentation.extensions.extra
import com.igorvd.bitcoincharts.core.presentation.extensions.launch
import com.igorvd.bitcoincharts.core.presentation.extensions.viewBinding
import com.igorvd.bitcoincharts.features.charts.databinding.ActivityChartBinding
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.features.charts.presentation.chart.view.linechart.formatter.YAxisFormatterFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject

@AndroidEntryPoint
class BitcoinChartActivity : AppCompatActivity() {

    val chartType: ChartType by extra(EXTRA_CHART_TYPE)
    private val viewBinding: ActivityChartBinding by viewBinding(ActivityChartBinding::inflate)
    val viewModel: BitcoinChartViewModel by viewModels()

    @Inject
    protected lateinit var dateTimeService: DateTimeService

    @Inject
    protected lateinit var yAxisFormatterFactory: YAxisFormatterFactory
    private val layoutContainer by lazy {
        BitcoinChartLayoutContainer(this, viewBinding, dateTimeService, yAxisFormatterFactory)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        layoutContainer.setup()
        setupObservers()
        viewModel.launch { getChartData(chartType) }
    }

    private fun setupObservers() = viewModel.apply {
        collect(chartScreenStateFlow.filterNotNull()) {
            layoutContainer.setScreenContent(it)
        }
    }

    companion object {
        private const val EXTRA_CHART_TYPE = "chart_type"
        fun newIntent(context: Context, chartType: ChartType): Intent {
            return Intent(context, BitcoinChartActivity::class.java).apply {
                putExtra(EXTRA_CHART_TYPE, chartType)
            }
        }
    }
}