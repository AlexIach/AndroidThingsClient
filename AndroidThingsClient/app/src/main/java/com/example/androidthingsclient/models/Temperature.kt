package com.example.androidthingsclient.models

/**
 * Created by aiachimov on 6/13/17.
 */
data class Temperature(private val qrCodeId: String, private val value: String, private val time: String, private val type: String) {
    //The compiler automatically derives the following equals(), hashCode(), toString(), copy()
}