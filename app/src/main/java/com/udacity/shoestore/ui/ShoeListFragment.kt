package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodel.ShoesViewModel

class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    private lateinit var shoeItemBinding: ShoeItemBinding
    private val viewModel by activityViewModels<ShoesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        shoeItemBinding = DataBindingUtil.inflate(inflater, R.layout.shoe_item, container, false)
        setHasOptionsMenu(true)

        // Handle click go to List shoes detail
        binding.listFloatingBtn.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        viewModel._shoeData.observe(viewLifecycleOwner, ::addAndDisplayShoesList)

        return binding.root
    }

    private fun addAndDisplayShoesList(shoe: Shoe) {
        // Add view : logic handle: https://stackoverflow.com/questions/2395769/how-to-programmatically-add-views-to-views
        shoeItemBinding.itemNameShoe.text = shoe.name
        shoeItemBinding.itemCompanyShoe.text = shoe.company
        shoeItemBinding.itemSizeShoe.text = shoe.size
        shoeItemBinding.itemDescriptionShoe.text = shoe.description
        binding.shoeListFrame.addView(shoeItemBinding.root)
    }


    // Override function
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shoelist_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }
}
