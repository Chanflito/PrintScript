package lexer.util

class RegexPatterns {
    companion object {
        val QUOTES_REGEX = Regex("""(?<!\\)(["'])(.*?)(?<!\\)\1""")
        val OPERATOR_REGEX = Regex("""[+\-*/=():;]""")
        val IDENTIFIER_REGEX = Regex("""[a-zA-Z_][a-zA-Z0-9_]*""")
        val NUMBER_REGEX = Regex("""\b-?\d+(\.\d+)?\b""")
    }
}