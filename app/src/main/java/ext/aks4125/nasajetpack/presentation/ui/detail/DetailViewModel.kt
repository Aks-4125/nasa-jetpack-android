package ext.aks4125.nasajetpack.presentation.ui.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ext.aks4125.nasajetpack.data.NasaRepository
import ext.aks4125.nasajetpack.presentation.util.NasaDestinationsArgs
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: NasaRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val nasaId: String? = savedStateHandle[NasaDestinationsArgs.ID]

    var uiState by mutableStateOf(DetailUiState())
        private set

    init {
        if (nasaId != null) {
            viewModelScope.launch {
                uiState = uiState.copy(item = repository.getPlanetDetail(nasaId))
            }
        }
    }

    fun formatDate(date: String): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        return if (date.isEmpty()) {
            ""
        } else {
            parser.parse(date)?.let { formatter.format(it) } ?: ""
        }
    }
}
