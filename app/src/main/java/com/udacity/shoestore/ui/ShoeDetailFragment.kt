package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodel.ShoesViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [ShoeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeDetailFragment : Fragment() {
    private val viewModel by activityViewModels<ShoesViewModel>()
    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            FragmentShoeDetailBinding.inflate(inflater, container, false)
        binding.shoreStoreDetailViewModel = viewModel
        binding.detailCancelBtn.setOnClickListener { view ->
            Navigation.findNavController(view)
                .navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }
        // All field not nullable
        with(binding) {
            detailSaveBtn.setOnClickListener { view ->
                if (detailShoeNameEt.text.isBlank()
                    || detailShoeCompanyEt.text.isBlank()
                    || detailShoeSizeEt.text.isBlank()
                    || detailShoeDescriptionEt.text.isBlank()
                ) {
                    Toast.makeText(requireContext(), "Please fill out all!", Toast.LENGTH_SHORT).show()
                } else {
                    val shoe = Shoe(
                        name = ("Name: " + viewModel.shoeName?.trim()?.uppercase()),
                        company = ("Company: " + viewModel.shoeCompany?.trim()?.uppercase()),
                        size = ("Size: " + viewModel.shoeSize?.trim()),
                        description = ("Description: " + viewModel.shoeDescription?.trim())
                    )
                    viewModel.addShoeItem(shoe)
                    Navigation.findNavController(view)
                        .navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
                }
            }
        }

        return binding.root
    }

}
