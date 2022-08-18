package ru.aasmc.sunflowerclone.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.aasmc.sunflowerclone.core.data.repository.GardenPlantingRepository
import ru.aasmc.sunflowerclone.core.database.model.PlantAndGardenPlantings
import javax.inject.Inject

@HiltViewModel
class GardenPlantingListViewModel @Inject constructor(
    gardenPlantingRepository: GardenPlantingRepository
) : ViewModel() {
    val plantAndGardenPlantings: LiveData<List<PlantAndGardenPlantings>> =
        gardenPlantingRepository.getPlantedGardens().asLiveData()
}