package com.example.mvvm_week4_160421085.model

data class Drink(
    val id:String?,
    val name:String?,
    val category:String?,
    val ingredients:List<String>?,
    val extras:ToppingTambahan?,
    val images:String?
)
data class ToppingTambahan(
    val sugar:String?,
    val ice:String?
)
