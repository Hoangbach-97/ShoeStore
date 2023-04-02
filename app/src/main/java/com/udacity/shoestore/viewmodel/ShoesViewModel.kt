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


    private var shoeData: MutableLiveData<Shoe> = MutableLiveData()
    val _shoeData: LiveData<Shoe>
        get() = shoeData

    // Make sure all items not nullable
    init {
        shoeName = ""
        shoeCompany = ""
        shoeSize = ""
        shoeDescription = ""
    }

    fun addShoeItem(shoe: Shoe) {
        shoeData.postValue(shoe)
    }

    override fun onCleared() {
        super.onCleared()
    }
}
