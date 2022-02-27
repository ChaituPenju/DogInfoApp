package com.chaitupenju.dogsinfo.presentation.dog_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.chaitupenju.dogsinfo.R
import com.chaitupenju.dogsinfo.common.hide
import com.chaitupenju.dogsinfo.common.show
import com.chaitupenju.dogsinfo.databinding.FragmentDogListBinding
import com.chaitupenju.dogsinfo.domain.model.Dog
import com.chaitupenju.dogsinfo.presentation.DogActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DogListFragment : Fragment(R.layout.fragment_dog_list) {

    private lateinit var dogListBinding: FragmentDogListBinding
    private val dogsViewModel: DogListViewModel by viewModels()

    private var dogsList: List<Dog> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        dogListBinding = FragmentDogListBinding.inflate(inflater)
        return dogListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDogsListUI()
    }

    private fun setupDogsListUI() {
        val dogsAdapter = DogListAdapter { dog ->
            Toast.makeText(requireContext(), "Dog Name is ${dog.name}", Toast.LENGTH_SHORT).show()

            (activity as DogActivity).navController.navigate(
                DogListFragmentDirections.actionDogListFragmentToDogInfoFragment(
                    dogImageId = dog.referenceImageId
                )
            )
        }

        dogListBinding.rvDogsList.apply {
            adapter = dogsAdapter
        }

        dogListBinding.dogListSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(searchText: String?): Boolean {
                dogsAdapter.submitList(dogsList.filter { dog -> dog.name.contains(searchText.toString()) })
                return false
            }

        })

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            dogsViewModel.state.collectLatest {
                when {
                    it.isLoading -> dogListBinding.dogsListShimmerLayout.show()

                    it.dogs.isNotEmpty() -> {
                        dogListBinding.dogsListShimmerLayout.hide()
                        dogsAdapter.submitList(it.dogs)
                        dogsList = it.dogs
                    }
                }
            }
        }
    }
}