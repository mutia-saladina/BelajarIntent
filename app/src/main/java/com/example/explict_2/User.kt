package com.example.explict_2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val nama: String,
    val email: String,
    val umur: Int
) : Parcelable
