package ru.aasmc.sunflowerclone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import ru.aasmc.sunflowerclone.R
import ru.aasmc.sunflowerclone.adapters.GardenPlantingAdapter
import ru.aasmc.sunflowerclone.adapters.PLANT_LIST_PAGE_INDEX
import ru.aasmc.sunflowerclone.databinding.FragmentGardenBinding
import ru.aasmc.sunflowerclone.viewmodels.GardenPlantingListViewModel

@AndroidEntryPoint
class GardenFragment : Fragment() {

    private lateinit var binding: FragmentGardenBinding

    private val viewModel: GardenPlantingListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGardenBinding.inflate(inflater, container, false)
        val adapter = GardenPlantingAdapter()
        binding.gardenList.adapter = adapter

        binding.addPlant.setOnClickListener {
            navigateToPlantListPage()
        }
        subscribeUi(adapter, binding)
        return binding.root
    }

    private fun subscribeUi(
        adapter: GardenPlantingAdapter,
        binding: FragmentGardenBinding
    ) {
        viewModel.plantAndGardenPlantings.observe(viewLifecycleOwner) { result ->
            binding.hasPlantings = result.isNotEmpty()
            adapter.submitList(result) {
                // At this point the content should be drawn
                activity?.reportFullyDrawn()
            }
        }
    }

    private fun navigateToPlantListPage() {
        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem =
            PLANT_LIST_PAGE_INDEX
    }
}