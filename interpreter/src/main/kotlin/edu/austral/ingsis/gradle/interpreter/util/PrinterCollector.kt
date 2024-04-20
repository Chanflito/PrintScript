package edu.austral.ingsis.gradle.interpreter.util

class PrinterCollector : Printer {
    private val printedValues = mutableListOf<String>()

    override fun print(value: String) {
        printedValues.add(value)
        println(value)
    }

    fun getPrintedValues(): List<String> {
        return printedValues
    }
}
