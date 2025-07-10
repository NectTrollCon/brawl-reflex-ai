
package com.brawlreflexai

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.view.accessibility.AccessibilityEvent

class MyAccessibilityService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}
    override fun onInterrupt() {}
    fun dodgeLeft() {
        val path = Path()
        path.moveTo(1400f, 800f)
        path.lineTo(900f, 800f)
        val gesture = GestureDescription.Builder()
            .addStroke(GestureDescription.StrokeDescription(path, 0, 55))
            .build()
        dispatchGesture(gesture, null, null)
    }
    fun dodgeRight() {
        val path = Path()
        path.moveTo(900f, 800f)
        path.lineTo(1400f, 800f)
        val gesture = GestureDescription.Builder()
            .addStroke(GestureDescription.StrokeDescription(path, 0, 55))
            .build()
        dispatchGesture(gesture, null, null)
    }
    fun shootWithCorrection(targetX: Float, targetY: Float) {
        val path = Path()
        path.moveTo(1120f, 800f)
        path.lineTo(targetX, targetY)
        val gesture = GestureDescription.Builder()
            .addStroke(GestureDescription.StrokeDescription(path, 0, 90))
            .build()
        dispatchGesture(gesture, null, null)
    }
}
        