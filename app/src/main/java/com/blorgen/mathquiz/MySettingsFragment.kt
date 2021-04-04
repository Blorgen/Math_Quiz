package com.blorgen.mathquiz

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class MySettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.pref)
    }
}