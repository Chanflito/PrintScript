package edu.austral.ingsis.gradle.cli.util

fun delay(milliseconds: Long) {
    try {
        Thread.sleep(milliseconds)
    } catch (e: InterruptedException) {
        Thread.currentThread().interrupt()
    }
}
