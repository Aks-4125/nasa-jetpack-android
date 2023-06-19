package ext.aks4125.nasajetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import ext.aks4125.nasajetpack.ui.theme.NasaJetpackTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NasaJetpackTheme {
                NasaNavGraph { finish() }
            }
        }
    }
}