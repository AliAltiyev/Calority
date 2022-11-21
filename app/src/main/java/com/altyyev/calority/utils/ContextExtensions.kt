package com.altyyev.calority.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context?.showToast(@StringRes res: Int) {
    Toast.makeText(this, res, Toast.LENGTH_LONG).show()
}