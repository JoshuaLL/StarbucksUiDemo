package com.joshua.starbucksui

import android.app.Activity
import android.graphics.Color
import android.view.View


@Suppress("DEPRECATION")
fun Activity.setTransparentStatusBar() {
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    window.statusBarColor = Color.TRANSPARENT
}