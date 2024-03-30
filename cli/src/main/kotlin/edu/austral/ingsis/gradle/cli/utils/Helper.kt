package edu.austral.ingsis.gradle.cli.utils

fun clearConsole() {
    // Clearing the console based on the operating system
    val os = System.getProperty("os.name")
    when {
        os.contains("Windows") -> Runtime.getRuntime().exec("cmd /c cls")
        os.contains("Linux") || os.contains("Mac") -> Runtime.getRuntime().exec("clear")
        else -> println("Unsupported operating system: $os")
    }
}

fun delay(milliseconds: Long) {
    try {
        Thread.sleep(milliseconds)
    } catch (e: InterruptedException) {
        Thread.currentThread().interrupt()
    }
}
