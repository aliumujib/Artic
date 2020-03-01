package com.aliumujib.artic.cache.utils


import android.content.Context
import android.content.SharedPreferences


open class CoreSharedPrefManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        APP_NAME,
        Context.MODE_PRIVATE
    )
    val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()


    protected fun delete(key: String) {
        if (sharedPreferences.contains(key)) {
            sharedPreferencesEditor.remove(key).commit()
        }
    }

    protected fun savePref(key: String, value: Any?) {
        delete(key)

        when {
            value is Boolean -> sharedPreferencesEditor.putBoolean(key, (value as Boolean?)!!)
            value is Int -> sharedPreferencesEditor.putInt(key, (value as Int?)!!)
            value is Float -> sharedPreferencesEditor.putFloat(key, (value as Float?)!!)
            value is Long -> sharedPreferencesEditor.putLong(key, (value as Long?)!!)
            value is String -> sharedPreferencesEditor.putString(key, value as String?)
            value is Enum<*> -> sharedPreferencesEditor.putString(key, value.toString())
            value != null -> throw RuntimeException("Attempting to save non-primitive preference")
        }

        sharedPreferencesEditor.commit()
    }

    protected fun <T> getPref(key: String): T {
        return sharedPreferences.all[key] as T
    }

    protected fun <T> getPref(key: String, defValue: T?): T? {
        val returnValue = sharedPreferences.all[key] as T
        return returnValue ?: defValue
    }

    fun clearAll() {
        sharedPreferencesEditor.clear()
    }


    companion object {
        const val APP_NAME = "EYOWO"
    }
}