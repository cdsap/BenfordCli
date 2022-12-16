package org.gradle.eoy.hackathon

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.file

fun main(args: Array<String>) {
    BenfordCli().main(args)
}

class BenfordCli : CliktCommand(
    """
    According to Benford;s law, the frequency of the first digit in a reasonably large dataset
    containing 'naturally occurring' numbers is not uniform, but logarithmic: 1 will be the leading digit in a genuine
    data set of numbers 30.1% of the time; the numeral 2 will be the leading digit 17.6% of the time; and each
    subsequent numeral, 3 through 9, will be the leading digit with decreasing frequency.

""".trimIndent()
) {

    private val file by option().file().required().help("File containing DataSet. Dataset should contain at least 100 numbers, but 500 or more numbers are preferable.")
    private val experimentName by option().help("Name of the experiment").required()

    override fun run() {
        val a = BenfordCalculator().processFile(file)

        ConsoleOutput(a, experimentName).print()
        ImageOutput(a, experimentName).generateImage()
    }
}
