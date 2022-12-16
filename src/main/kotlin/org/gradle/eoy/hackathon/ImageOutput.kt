package org.gradle.eoy.hackathon

import org.jetbrains.letsPlot.export.ggsave
import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.label.labs
import org.jetbrains.letsPlot.letsPlot
import java.io.File

class ImageOutput(private val benfordSeries: BenfordSeries, private val experimentName: String) {

    fun generateImage() {
        val imageName = "$experimentName-${System.currentTimeMillis()}.png"
        println("Generating Image $imageName")
        val data3 = mapOf(
            "aa" to benfordSeries.digitPercentages,
            "bb" to benfordSeries.BENFORD_PERCENTAGES,
            "base" to listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        )

        val fig1 = letsPlot(data3) + ggtitle("$experimentName") + labs(x = "", y ="Benford Percentages") +
            geomPoint(
                color = "red",
                size = 4.0
            ) { x = "base"; y = "bb"; } + geomLine(
            color = "blue",
            size = 4.0,
            alpha = 0.2
        ) { x = "base"; y = "aa"; }

        ggsave(plot = fig1, filename = imageName, path = System.getProperty("user.dir"))
        if(File(imageName).exists()){
            println("Image $imageName created")
        }

    }
}
