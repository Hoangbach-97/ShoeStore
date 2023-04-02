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
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        binding.shoreStoreDetailViewModel = viewModel

        binding.detailCancelBtn.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }
        binding.detailSaveBtn.setOnClickListener {
            if (binding.detailShoeNameEt.text.isBlank()
                || binding.detailShoeCompanyEt.text.isBlank()
                || binding.detailShoeSizeEt.text.isBlank()
                || binding.detailShoeDescriptionEt.text.isBlank()
            ) {
                Toast.makeText(requireContext(), "Please fill out all!", Toast.LENGTH_SHORT).show()
            } else {
                val shoe = Shoe(
                    name = viewModel.shoeName?.trim() ?: "",
                    company = viewModel.shoeCompany?.trim() ?: "",
                    size = viewModel.shoeSize?.trim() ?: "",
                    description = viewModel.shoeDescription?.trim() ?: ""
                )
                viewModel.addShoeItem(shoe)
                Navigation.findNavController(it)
                    .navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
            }
        }
        return binding.root
    }

}
