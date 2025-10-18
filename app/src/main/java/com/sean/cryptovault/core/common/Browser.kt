package com.sean.cryptovault.core.common

import android.content.Context
import android.content.Intent
import android.net.Uri

object Browser {
    fun open(context: Context, link: String) {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
    }
}