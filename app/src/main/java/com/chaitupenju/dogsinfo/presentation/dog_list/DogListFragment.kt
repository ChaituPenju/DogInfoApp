package com.chaitupenju.dogsinfo.presentation.dog_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.chaitupenju.dogsinfo.R
import com.chaitupenju.dogsinfo.databinding.FragmentDogListBinding
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

        val dogsAdapter = DogListRecyclerView()
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