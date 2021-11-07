package com.chaitupenju.dogsinfo.presentation.dog_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.chaitupenju.dogsinfo.R
import com.chaitupenju.dogsinfo.databinding.FragmentDogListBinding
import com.chaitupenju.dogsinfo.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DogListFragment : Fragment(R.layout.fragment_dog_list) {

    private lateinit var dogListBinding: FragmentDogListBinding
    private val dogsViewModel: DogListViewModel by viewModels()

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
        val dogsAdapter = DogListRecyclerView { dog ->
            Toast.makeText(requireContext(), "Dog Name is ${dog.name}", Toast.LENGTH_SHORT).show()

            (activity as MainActivity).navController.navigate(
                DogListFragmentDirections.actionDogListFragmentToDogInfoFragment(
                    dogImageId = dog.referenceImageId
                )
            )
        }

        dogListBinding.rvDogsList.apply {
            adapter = dogsAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            dogsViewModel.state.collectLatest {
                if (!it.isLoading && it.dogs.isNotEmpty()) {
                    dogsAdapter.submitList(it.dogs)
                }
            }
        }
    }
}