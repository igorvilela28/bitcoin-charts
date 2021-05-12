object Modules {
    const val app = ":app"
    const val coreData = ":core:data"
    const val coreDomain = ":core:domain"
    const val corePresentation = ":core:presentation"
    const val featureA = ":features:featureA"

    fun toList() = listOf(
        app,
        coreData,
        coreDomain,
        corePresentation,
        featureA
    )
}