package com.example.mvvm_week4_160421085.model

import com.google.gson.annotations.SerializedName

data class Student(
    val id:String?,
    @SerializedName("student_name")
    val name:String?,
    @SerializedName("birth_of_date")
    val bod:String?,
    @SerializedName("phone_number")
    val phone:String?,
    @SerializedName("photo_url")
    val photoUrl:String
)
