
package com.brawlreflexai

import org.opencv.core.Point

class DodgeEngine {
    fun estimateProjectileSpeed(projectileHistory: List<Point>): Double {
        if (projectileHistory.size < 2) return 350.0
        val last = projectileHistory.last()
        val prev = projectileHistory[projectileHistory.size - 2]
        val dx = last.x - prev.x
        val dy = last.y - prev.y
        val dist = Math.sqrt(dx * dx + dy * dy)
        return dist * 30
    }
    fun shouldDodge(
        projectile: Point?, playerPos: Point, projectileHistory: List<Point>
    ): Boolean {
        if (projectile == null) return false
        val pxPerSec = estimateProjectileSpeed(projectileHistory)
        val dx = projectile.x - playerPos.x
        val dy = projectile.y - playerPos.y
        val dist = Math.sqrt(dx * dx + dy * dy)
        val timeToImpact = dist / pxPerSec * 1000
        return dist < 220 && timeToImpact < 160
    }
}
        