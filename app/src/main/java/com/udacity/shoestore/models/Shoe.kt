package com.udacity.shoestore.models

data class Shoe(
    var name: String?,
    var size: String?, // Changed from Double into String cause it's easier to handle
    var company: String?,
    var description: String?
)