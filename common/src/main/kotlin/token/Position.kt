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

