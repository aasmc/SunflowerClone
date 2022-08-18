package ru.aasmc.sunflowerclone.adapters

import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.composethemeadapter.MdcTheme
import ru.aasmc.sunflowerclone.compose.plantlist.PlantListItemView
import ru.aasmc.sunflowerclone.core.model.data.Plant
import ru.aasmc.sunflowerclone.fragments.HomeViewPagerFragmentDirections

class PlantAdapter : ListAdapter<Plant, RecyclerView.ViewHolder>(PlantDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return PlantViewHolder(ComposeView(parent.context))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val plant = getItem(position)
        (holder as PlantViewHolder).bind(plant)
    }

    class PlantViewHolder(
        composeView: ComposeView
    ) : RecyclerView.ViewHolder(composeView) {

        fun bind(plant: Plant) {
            (itemView as ComposeView).setContent {
                MdcTheme {
                    PlantListItemView(plant = plant) {
                        navigateToPlant(plant)
                    }
                }
            }
        }

        private fun navigateToPlant(plant: Plant) {
            val direction =
                HomeViewPagerFragmentDirections.actionViewPagerFragmentToPlantDetailFragment(
                    plant.plantId
                )
            itemView.findNavController().navigate(direction)
        }
    }
}

private class PlantDiffCallback : DiffUtil.ItemCallback<Plant>() {
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.plantId == newItem.plantId
    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }

}