package org.gradle.eoy.hackathon

import java.io.File

class BenfordCalculator {

    @Throws(Exception::class)
    fun processFile(file: File): BenfordSeries {
        val benfordSeries = BenfordSeries()
        file.readLines().forEach {
            val digit = it[0].toString().toInt()
            if (digit > 0) {
                benfordSeries.digitCounters[digit - 1]++
                benfordSeries.totalDigits++
            }
        }
        return benfordSeries
    }
}

data class BenfordSeries(
    val digitCounters: IntArray = IntArray(9),
    val digitPercentages: DoubleArray = DoubleArray(9),
    var totalDigits: Int = 0,
    val BENFORD_PERCENTAGES: DoubleArray = doubleArrayOf(30.10, 17.61, 12.49, 9.69, 7.92, 6.69, 5.80, 5.12, 4.58)
)
