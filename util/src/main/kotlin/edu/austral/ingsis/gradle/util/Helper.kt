package util.edu.austral.ingsis.gradle.util

import common.token.Position

fun calculatePosition(code: String, index: Int): Position {
    var row = 1
    var column = 1
    for (i in 0 until index) {
        if (code[i] == '\n') {
            row++
            column = 1
        } else {
            column++
        }
    }
    return Position(row, column)
}