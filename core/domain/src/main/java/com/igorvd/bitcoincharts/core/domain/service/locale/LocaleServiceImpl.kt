package com.igorvd.bitcoincharts.core.domain.service.locale

import java.util.Locale
import javax.inject.Inject

class LocaleServiceImpl @Inject constructor() : LocaleService {
    override fun getLocale(): Locale {
        return Locale.ENGLISH
    }
}