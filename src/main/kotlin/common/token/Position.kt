package common.token

data class Position(val row: Int, val column: Int) : Comparable<Position> {
    override fun compareTo(other: Position): Int {
        return if (this.row == other.row) {
            this.column.compareTo(other.column)
        } else {
            this.row.compareTo(other.row)
        }
    }
}

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
