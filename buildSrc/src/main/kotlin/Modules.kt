object Modules {
    const val app = ":app"
    const val coreData = ":core:data"
    const val coreDomain = ":core:domain"
    const val corePresentation = ":core:presentation"
    const val charts = ":features:charts"

    fun toList() = listOf(
        app,
        coreData,
        coreDomain,
        corePresentation,
        charts
    )
}