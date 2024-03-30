package edu.austral.ingsis.gradle.cli.utils

fun delay(milliseconds: Long) {
    try {
        Thread.sleep(milliseconds)
    } catch (e: InterruptedException) {
        Thread.currentThread().interrupt()
    }
}
