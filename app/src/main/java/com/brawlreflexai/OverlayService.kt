
package com.brawlreflexai

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.View
import android.view.WindowManager

class OverlayService : Service() {
    private var overlayView: View? = null
    override fun onBind(intent: Intent?) = null
    override fun onCreate() {
        super.onCreate()
        val wm = getSystemService(WINDOW_SERVICE) as WindowManager
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            PixelFormat.TRANSLUCENT
        )
        overlayView = View(this)
        overlayView?.alpha = 0.01f
        wm.addView(overlayView, params)
    }
    override fun onDestroy() {
        super.onDestroy()
        (getSystemService(WINDOW_SERVICE) as WindowManager).removeView(overlayView)
    }
}
        