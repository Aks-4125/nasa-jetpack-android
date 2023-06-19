package ext.aks4125.nasajetpack.util

import ext.aks4125.nasajetpack.util.NasaDestinationsArgs.ID
import ext.aks4125.nasajetpack.util.NasaScreens.DETAIL_SCREEN
import ext.aks4125.nasajetpack.util.NasaScreens.SEARCH_SCREEN


/**
 * Screens used in [NasaDestinations]
 */
private object NasaScreens {
    const val SEARCH_SCREEN = "search"
    const val DETAIL_SCREEN = "detail"
}
/**
 * Arguments used in [NasaDestinations] routes
 */
object NasaDestinationsArgs {
    const val ID = "ID"
}


object NasaDestinations {
    const val LANDING_PAGE_SEARCH = SEARCH_SCREEN
    const val DETAIL = "$DETAIL_SCREEN/{$ID}"
}