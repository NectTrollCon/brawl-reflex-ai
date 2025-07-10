
package com.brawlreflexai

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$packageName"))
            startActivityForResult(intent, 1)
        } else {
            startService(Intent(this, OverlayService::class.java))
        }
        val accIntent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        startActivity(accIntent)
        finish()
    }
}
        