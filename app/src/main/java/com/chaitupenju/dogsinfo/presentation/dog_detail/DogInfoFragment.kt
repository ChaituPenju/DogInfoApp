package com.chaitupenju.dogsinfo.presentation.dog_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.chaitupenju.dogsinfo.databinding.FragmentDogInfoBinding
import com.chaitupenju.dogsinfo.presentation.DogActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DogInfoFragment : Fragment() {

    private val dogInfoViewModel: DogInfoViewModel by viewModels()
    private val dogInfoArgs: DogInfoFragmentArgs by navArgs()

    private lateinit var dogInfoBinding: FragmentDogInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        dogInfoBinding = FragmentDogInfoBinding.inflate(inflater, container, false)
        return dogInfoBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as DogActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupDogInfoUI()
    }

    private fun setupDogInfoUI() {

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            dogInfoViewModel.state.collectLatest { dogInfoState ->
                if (!dogInfoState.isLoading && dogInfoState.dog != null) {
                    dogInfoBinding.theDogInfo = dogInfoState.dog
                    dogInfoBinding.rvTemperaments.adapter = DogTemperamentListAdapter().also {
                        it.submitList(dogInfoState.dog.temperament)
                    }
                } else {
                    println("THE ERROR IS ${dogInfoState.error}")
                }
            }
        }
    }
}