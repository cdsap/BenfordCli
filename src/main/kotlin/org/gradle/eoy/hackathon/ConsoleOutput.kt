package org.gradle.eoy.hackathon

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation

class ConsoleOutput(val benfordSeries: BenfordSeries, val experimentName: String) {

    fun print() {
        println()
        println("------- $experimentName----------------------")
        println("Total numbers = ${benfordSeries.totalDigits}")
        println()
        for (i in 0..8) {
            benfordSeries.digitPercentages[i] = 100.0 * benfordSeries.digitCounters[i] / benfordSeries.totalDigits
            println(
                String.format(
                    "%d: %10d -> %5.2f (%5.2f)  %s", i + 1, benfordSeries.digitCounters[i],
                    benfordSeries.digitPercentages[i], benfordSeries.BENFORD_PERCENTAGES[i],
                    "#".repeat(Math.round(benfordSeries.digitPercentages[i]).toInt())
                )
            )
        }
        val corr: Double =
            PearsonsCorrelation().correlation(benfordSeries.digitPercentages, benfordSeries.BENFORD_PERCENTAGES)
        println("\n--- Correlation = $corr")
    }
}
