package ru.aasmc.sunflowerclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import ru.aasmc.sunflowerclone.databinding.ActivityGardenBinding
import androidx.databinding.DataBindingUtil.setContentView

@AndroidEntryPoint
class GardenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Displaying edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContentView<ActivityGardenBinding>(this, R.layout.activity_garden)
    }
}

