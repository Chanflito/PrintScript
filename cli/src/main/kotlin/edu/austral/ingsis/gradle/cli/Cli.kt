package edu.austral.ingsis.gradle.cli

fun main() {
    welcome()
    do {
        print("> ")
        when (val command = readln()) {
            "exit" -> break
            "help" -> help()
            else -> println(command)
        }
    } while (true)
}

private fun welcome() {
    println(
        "██████╗ ██████╗ ██╗███╗   ██╗████████╗███████╗ ██████╗██████╗ ██╗██████╗ ████████╗\n" +
            "██╔══██╗██╔══██╗██║████╗  ██║╚══██╔══╝██╔════╝██╔════╝██╔══██╗██║██╔══██╗╚══██╔══╝\n" +
            "██████╔╝██████╔╝██║██╔██╗ ██║   ██║   ███████╗██║     ██████╔╝██║██████╔╝   ██║   \n" +
            "██╔═══╝ ██╔══██╗██║██║╚██╗██║   ██║   ╚════██║██║     ██╔══██╗██║██╔═══╝    ██║   \n" +
            "██║     ██║  ██║██║██║ ╚████║   ██║   ███████║╚██████╗██║  ██║██║██║        ██║   \n" +
            "╚═╝     ╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝   ╚═╝   ╚══════╝ ╚═════╝╚═╝  ╚═╝╚═╝╚═╝        ╚═╝   \n",
    )

    println("Welcome to printScript! Type 'help' to see more info")
}

private fun help() {
    println("\nPrintScript Help:")
    println("PrintScript is a TypeScript-based programming language.")
    println("It has only one function:")
    println(" - println(): Prints anything you pass as a parameter.")
}
