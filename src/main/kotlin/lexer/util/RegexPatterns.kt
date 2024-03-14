package lexer.util

import common.token.TokenType

class RegexPatterns {
    companion object {
        // Regex pattern to match strings with simple quotes or double quotes. Example: "Hello World", 'Hello World'
        val QUOTES_REGEX = Regex("""(?<!\\)["']([^"\\]*(\\.[^"\\]*)*)["']""")

        // Regex pattern to match operators. Example: "+", "-", "*", "/", "=", "(", ")", ";"
        val OPERATOR_REGEX = Regex("""[+\-*/=():;]""")

        // Regex pattern to match identifiers. Example: "a", "x", "variable1"
        val IDENTIFIER_REGEX = Regex("""[a-zA-Z_][a-zA-Z0-9_]*""")

        // Regex pattern to match just numbers. Includes decimal and integer numbers
        val NUMBER_REGEX = Regex("""\b-?\d+(\.\d+)?\b""")

        // Regex pattern to match keywords. Example: "let", "println"
        val KEYWORD_REGEX: (Map<String, TokenType>) -> Regex = { tokens ->
            Regex("""\b(${tokens.keys.joinToString("|")})\b""")
        }
        // Regex pattern to match types. Example: "string", "number"
        val TYPE_REGEX: (Map<String, TokenType>) -> Regex = { tokens ->
            Regex("""\b(${tokens.keys.joinToString("|")})\b""")
        }
    }
}