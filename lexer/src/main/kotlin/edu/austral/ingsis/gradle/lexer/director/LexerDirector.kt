package edu.austral.ingsis.gradle.lexer.director

import edu.austral.ingsis.gradle.common.token.ConstKeyword
import edu.austral.ingsis.gradle.common.token.ElseKeyword
import edu.austral.ingsis.gradle.common.token.IfKeyword
import edu.austral.ingsis.gradle.common.token.LetKeyword
import edu.austral.ingsis.gradle.common.token.PrintlnKeyword
import edu.austral.ingsis.gradle.common.token.ReadInputKeyword
import edu.austral.ingsis.gradle.common.token.TypeBoolean
import edu.austral.ingsis.gradle.common.token.TypeNumber
import edu.austral.ingsis.gradle.common.token.TypeString
import edu.austral.ingsis.gradle.lexer.Lexer
import edu.austral.ingsis.gradle.lexer.builder.LexerBuilderImp
import edu.austral.ingsis.gradle.lexer.impl.BooleanLexer
import edu.austral.ingsis.gradle.lexer.impl.IdentifierLexer
import edu.austral.ingsis.gradle.lexer.impl.KeywordLexer
import edu.austral.ingsis.gradle.lexer.impl.NumberLexer
import edu.austral.ingsis.gradle.lexer.impl.OperatorLexer
import edu.austral.ingsis.gradle.lexer.impl.StringLexer
import edu.austral.ingsis.gradle.lexer.impl.TypeLexer
import edu.austral.ingsis.gradle.lexer.util.bracketsOperators
import edu.austral.ingsis.gradle.lexer.util.operators

class LexerDirector {
    fun createComposeLexer(version: String): Lexer {
        val builder = LexerBuilderImp(listOf())
        return when (version) {
            "1.0" -> {
                builder
                    .withLexer(KeywordLexer(mapOf("let" to LetKeyword, "println" to PrintlnKeyword)))
                    .withLexer(TypeLexer(mapOf("string" to TypeString, "number" to TypeNumber)))
                    .withLexer(IdentifierLexer(listOf("let", "println", "number", "string")))
                    .withLexer(NumberLexer())
                    .withLexer(
                        OperatorLexer(
                            operators,
                        ),
                    )
                    .withLexer(StringLexer())
                    .build()
            }

            "1.1" -> {
                builder
                    .withLexer(
                        KeywordLexer(
                            mapOf(
                                "let" to LetKeyword,
                                "const" to ConstKeyword,
                                "println" to PrintlnKeyword,
                                "if" to IfKeyword,
                                "else" to ElseKeyword,
                                "readInput" to ReadInputKeyword,
                            ),
                        ),
                    )
                    .withLexer(
                        TypeLexer(
                            mapOf(
                                "string" to TypeString,
                                "number" to TypeNumber,
                                "boolean" to TypeBoolean,
                            ),
                        ),
                    )
                    .withLexer(
                        IdentifierLexer(
                            // constraints
                            listOf(
                                "const",
                                "let",
                                "println",
                                "number",
                                "string",
                                "boolean",
                                "if",
                                "else",
                                "readInput",
                                "true",
                                "false",
                            ),
                        ),
                    )
                    .withLexer(NumberLexer())
                    .withLexer(BooleanLexer())
                    .withLexer(
                        OperatorLexer(
                            operators + bracketsOperators,
                        ),
                    )
                    .withLexer(StringLexer())
                    .build()
            }

            else -> {
                throw Exception("The version $version is not implemented yet")
            }
        }
    }
}
