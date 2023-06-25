package ext.aks4125.nasajetpack.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import ext.aks4125.core.ui.theme.NasaJetpackTheme
import ext.aks4125.nasajetpack.presentation.navigation.NasaNavGraph

@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class)
class NasaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            NasaJetpackTheme {
                NasaNavGraph { finish() }
            }
        }
    }
}
