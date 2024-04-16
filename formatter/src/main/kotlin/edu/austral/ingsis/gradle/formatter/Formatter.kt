package edu.austral.ingsis.gradle.formatter

interface Formatter<AST> {
    fun format(node: AST): String

    fun canFormat(node: AST): Boolean
}
