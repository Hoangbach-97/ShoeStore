package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesViewModel : ViewModel() {

    var shoeName: String? = null
    var shoeCompany: String? = null
    var shoeSize: String? = null
    var shoeDescription: String? = null
    private val listUpdate = ArrayList<Shoe>()

    private var shoeData: MutableLiveData<MutableList<Shoe>> = MutableLiveData()
    val _shoeData: LiveData<MutableList<Shoe>>
        get() = shoeData

    // Make sure all items not nullable
//    init {
//        shoeName = "Nike"
//        shoeCompany = "nike"
//        shoeSize = "42"
//        shoeDescription = "Bright your foot"
//    }

    fun addShoeItem(shoe: Shoe) {
        listUpdate.add(shoe)
        shoeData.postValue(listUpdate)
    }

    override fun onCleared() {
        super.onCleared()
    }
}
