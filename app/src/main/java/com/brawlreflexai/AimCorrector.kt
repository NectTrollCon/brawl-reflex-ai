
package com.brawlreflexai

import org.opencv.core.Point
import kotlin.math.*

class AimCorrector {
    fun estimateEnemyMotion(enemyHistory: List<Point>): Triple<Double, Double, Double> {
        if (enemyHistory.size < 3) return Triple(0.0, 0.0, 0.0)
        val last = enemyHistory[enemyHistory.size - 1]
        val mid = enemyHistory[enemyHistory.size - 2]
        val prev = enemyHistory[enemyHistory.size - 3]
        val vx1 = mid.x - prev.x
        val vy1 = mid.y - prev.y
        val vx2 = last.x - mid.x
        val vy2 = last.y - mid.y
        val speed1 = sqrt(vx1 * vx1 + vy1 * vy1)
        val speed2 = sqrt(vx2 * vx2 + vy2 * vy2)
        val speed = (speed1 + speed2) / 2 * 30
        val angle1 = atan2(vy1, vx1)
        val angle2 = atan2(vy2, vx2)
        val avgAngle = (angle1 + angle2) / 2
        val dAngle = angle2 - angle1
        val accel = (speed2 - speed1) * 30
        return Triple(speed, avgAngle + dAngle, accel)
    }
    fun predictTarget(
        enemyHistory: List<Point>,
        playerPos: Point = Point(1120.0, 800.0),
        projectileSpeed: Double = 350.0,
        playerMove: Point? = null
    ): Point {
        if (enemyHistory.isEmpty()) return playerPos
        if (enemyHistory.size < 3) return enemyHistory.last()
        val (v, angle, a) = estimateEnemyMotion(enemyHistory)
        val last = enemyHistory.last()
        val relDx = last.x - playerPos.x
        val relDy = last.y - playerPos.y
        val dist = sqrt(relDx * relDx + relDy * relDy)
        val timeToTarget = dist / projectileSpeed
        val predX = last.x + v * cos(angle) * timeToTarget + 0.5 * a * cos(angle) * timeToTarget * timeToTarget
        val predY = last.y + v * sin(angle) * timeToTarget + 0.5 * a * sin(angle) * timeToTarget * timeToTarget
        return Point(predX, predY)
    }
}
        