package com.rafiul.platzi2301.utils

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PrefManager @Inject constructor(@ApplicationContext context: Context) {

    private val preferences = context.getSharedPreferences("pref", Context.MODE_PRIVATE)

    fun savePreferences(key: String, value: String) {
        val preferenceEdit: SharedPreferences.Editor = preferences.edit()
        preferenceEdit.putString(key, value)
        preferenceEdit.apply()
    }

    fun getPreferences(key: String): String {

        return preferences.getString(key, "").toString()
    }
}