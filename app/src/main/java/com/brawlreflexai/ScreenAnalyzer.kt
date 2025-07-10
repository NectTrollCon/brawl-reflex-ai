
package com.brawlreflexai

import android.content.Context
import org.opencv.core.*

class ScreenAnalyzer(val context: Context) {
    var projectileHistory = mutableListOf<Point>()
    var enemyHistory = mutableListOf<Point>()
    var playerMoveHistory = mutableListOf<Point>()
    fun analyzeFrame(mat: Mat): Triple<Point?, Point?, Point?> {
        val enemy = Point(1280.0, 880.0)
        val projectile = Point(1200.0, 800.0)
        val playerMove = Point(18.0, -13.0)
        projectileHistory.add(projectile)
        if (projectileHistory.size > 5) projectileHistory.removeAt(0)
        enemyHistory.add(enemy)
        if (enemyHistory.size > 5) enemyHistory.removeAt(0)
        playerMoveHistory.add(playerMove)
        if (playerMoveHistory.size > 5) playerMoveHistory.removeAt(0)
        return Triple(enemy, projectile, playerMove)
    }
}
        