package com.igorvd.bitcoincharts.core.presentation.view.error

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.igorvd.bitcoincharts.core.presentation.databinding.ViewErrorBinding
import com.igorvd.chuckfacts.domain.exceptions.MyIOException

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val viewBinding: ViewErrorBinding

    init {
        viewBinding = ViewErrorBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData(
        data: ErrorViewData,
        onTryAgainClicked: (() -> Unit)? = null
    ) = viewBinding.apply {
        tvErrorMessage.setText(data.messageRes)
        ivErrorIcon.setImageResource(data.drawableRes)
        btTryAgain.apply {
            isVisible = data.showRetryButton
            onTryAgainClicked?.let { listener ->
                setOnClickListener { listener() }
            }
        }
    }

    data class ErrorViewData(
        val messageRes: Int,
        val drawableRes: Int,
        val showRetryButton: Boolean
    )
}