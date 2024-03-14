package parser

import common.ast.ASTNodeImpl
import common.ast.NodeType
import common.token.Position
import common.token.Token
import common.token.TokenType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import parser.impl.ParserImpl

class ParserTest {
    private fun buildParser(tokens: List<Token>): Parser {
        return ParserImpl(tokens)
    }

    @Test
    fun test001_parseSingleNumber() {
        val tokens = listOf(Token("5", TokenType.VALUE_NUMBER, Position(1, 1), Position(1, 1)))
        val parser = buildParser(tokens)
        val ast = parser.parse()

        val expected = ASTNodeImpl(
            Token("5", TokenType.VALUE_NUMBER, Position(1, 1), Position(1, 1)),
            NodeType.NUMBER_NODE,
            null
        )
        assert(ast.contains(expected))
    }

    @Test
    fun test002_parseSingleOperation() {
        val tokens = listOf(
            Token("5", TokenType.VALUE_NUMBER, Position(1, 1), Position(1, 1)),
            Token("+", TokenType.PLUS, Position(1, 1), Position(1, 1)),
            Token("5", TokenType.VALUE_NUMBER, Position(1, 1), Position(1, 1)),
        )
        val parser = buildParser(tokens)
        val ast = parser.parse()

        val expected = ASTNodeImpl(
            Token("+", TokenType.PLUS, Position(1, 1), Position(1, 1)),
            NodeType.OPERATOR_NODE,
            listOf(
                ASTNodeImpl(
                    Token("5", TokenType.VALUE_NUMBER, Position(1, 1), Position(1, 1)),
                    NodeType.NUMBER_NODE,
                    null
                ),
                ASTNodeImpl(
                    Token("5", TokenType.VALUE_NUMBER, Position(1, 1), Position(1, 1)),
                    NodeType.NUMBER_NODE,
                    null
                )
            )
        )
        assert(ast.contains(expected))
    }

    @Test
    fun test003_parseSingleStringOperator() {
        val tokens = listOf(
            Token("hola", TokenType.VALUE_STRING, Position(1, 1), Position(1, 1)),
            Token("+", TokenType.PLUS, Position(1, 1), Position(1, 1)),
            Token("loco", TokenType.VALUE_STRING, Position(1, 1), Position(1, 1))
        )
        val parser = buildParser(tokens)
        val ast = parser.parse()

        val expected = ASTNodeImpl(
            Token("+", TokenType.PLUS, Position(1, 1), Position(1, 1)),
            NodeType.OPERATOR_NODE,
            listOf(
                ASTNodeImpl(
                    Token("hola", TokenType.VALUE_STRING, Position(1, 1), Position(1, 1)),
                    NodeType.STRING_NODE,
                    null
                ),
                ASTNodeImpl(
                    Token("loco", TokenType.VALUE_STRING, Position(1, 1), Position(1, 1)),
                    NodeType.STRING_NODE,
                    null
                )
            )
        )
        assert(ast.contains(expected))
    }

    @Test
    fun test004_parseSingleMultiplicativeOperation() {
        val tokens = listOf(
            Token("5", TokenType.VALUE_NUMBER, Position(1, 1), Position(1, 1)),
            Token("*", TokenType.MULTIPLY, Position(1, 1), Position(1, 1)),
            Token("5", TokenType.VALUE_NUMBER, Position(1, 1), Position(1, 1))
        )
        val parser = buildParser(tokens)
        val ast = parser.parse()

        val expected = ASTNodeImpl(
            Token("*", TokenType.MULTIPLY, Position(1, 1), Position(1, 1)),
            NodeType.OPERATOR_NODE,
            listOf(
                ASTNodeImpl(
                    Token("5", TokenType.VALUE_NUMBER, Position(1, 1), Position(1, 1)),
                    NodeType.NUMBER_NODE,
                    null
                ),
                ASTNodeImpl(
                    Token("5", TokenType.VALUE_NUMBER, Position(1, 1), Position(1, 1)),
                    NodeType.NUMBER_NODE,
                    null
                )
            )
        )
        assert(ast.contains(expected))
    }

    @Test
    fun test005_parseSingleDivisionOperation() {
        val tokens = listOf(
            Token("5", TokenType.VALUE_NUMBER, Position(1, 1), Position(1, 1)),
            Token("/", TokenType.DIVIDE, Position(1, 1), Position(1, 1)),
            Token("5", TokenType.VALUE_NUMBER, Position(1, 1), Position(1, 1))
        )
        val parser = buildParser(tokens)
        val ast = parser.parse()

        val expected = ASTNodeImpl(
            Token("/", TokenType.DIVIDE, Position(1, 1), Position(1, 1)),
            NodeType.OPERATOR_NODE,
            listOf(
                ASTNodeImpl(
                    Token("5", TokenType.VALUE_NUMBER, Position(1, 1), Position(1, 1)),
                    NodeType.NUMBER_NODE,
                    null
                ),
                ASTNodeImpl(
                    Token("5", TokenType.VALUE_NUMBER, Position(1, 1), Position(1, 1)),
                    NodeType.NUMBER_NODE,
                    null
                )
            )
        )
        assert(ast.contains(expected))
    }

    @Test
    fun test005_parseVariableDeclaration() {
        val tokens = listOf(
            Token("let", TokenType.LET_KEYWORD, Position(1, 1), Position(1, 4)),
            Token("a", TokenType.IDENTIFIER, Position(1, 5), Position(1, 6)),
            Token(":", TokenType.COLON, Position(1, 7), Position(1, 8)),
            Token("number", TokenType.TYPE_NUMBER, Position(1, 9), Position(1, 15)),
            Token(";", TokenType.SEMI_COLON, Position(1, 19), Position(1, 20)),
        )

        val parser = buildParser(tokens)
        val ast = parser.parse()

        val expected = ASTNodeImpl(
            Token("a", TokenType.IDENTIFIER, Position(1, 5), Position(1, 6)),
            NodeType.IDENTIFIER_NODE,
            listOf(
                ASTNodeImpl(
                    Token("let", TokenType.LET_KEYWORD, Position(1, 1), Position(1, 4)),
                    NodeType.KEYWORD_NODE,
                    null
                ),
                ASTNodeImpl(
                    Token("number", TokenType.TYPE_NUMBER, Position(1, 9), Position(1, 15)),
                    NodeType.TYPE_NODE,
                    null
                )
            )
        )

        assert(ast.contains(expected))
    }

    @Test
    fun test005_parseExpression() {
        val expected = listOf(
            Token("let", TokenType.LET_KEYWORD, Position(1, 1), Position(1, 4)),
            Token("a", TokenType.IDENTIFIER, Position(1, 5), Position(1, 6)),
            Token(":", TokenType.COLON, Position(1, 7), Position(1, 8)),
            Token("number", TokenType.TYPE_NUMBER, Position(1, 9), Position(1, 15)),
            Token("=", TokenType.ASSIGNATION, Position(1, 16), Position(1, 17)),
            Token("5", TokenType.VALUE_NUMBER, Position(1, 18), Position(1, 19)),
            Token(";", TokenType.SEMI_COLON, Position(1, 19), Position(1, 20))
        );

        val parser = buildParser(expected)
        val ast = parser.parse()

        val expectedAST = ASTNodeImpl(
            Token("=", TokenType.ASSIGNATION, Position(1, 16), Position(1, 17)),
            NodeType.ASSIGNMENT_NODE,
            listOf(
                ASTNodeImpl(
                    Token("a", TokenType.IDENTIFIER, Position(1, 5), Position(1, 6)),
                    NodeType.IDENTIFIER_NODE,
                    listOf(
                        ASTNodeImpl(
                            Token("let", TokenType.LET_KEYWORD, Position(1, 1), Position(1, 4)),
                            NodeType.KEYWORD_NODE,
                            null
                        ),
                        ASTNodeImpl(
                            Token("number", TokenType.TYPE_NUMBER, Position(1, 9), Position(1, 15)),
                            NodeType.TYPE_NODE,
                            null
                        )
                    )
                ),
                ASTNodeImpl(
                    Token("5", TokenType.VALUE_NUMBER, Position(1, 18), Position(1, 19)),
                    NodeType.NUMBER_NODE,
                    null
                )
            )
        )

        assert(ast.contains(expectedAST))
    }

    @Test
    fun test006_parseExpressionWithOperationOnOtherLine() {
        val expected = listOf(
            Token("let", TokenType.LET_KEYWORD, Position(1, 1), Position(1, 4)),
            Token("a", TokenType.IDENTIFIER, Position(1, 5), Position(1, 6)),
            Token(":", TokenType.COLON, Position(1, 7), Position(1, 8)),
            Token("number", TokenType.TYPE_NUMBER, Position(1, 9), Position(1, 15)),
            Token("=", TokenType.ASSIGNATION, Position(1, 16), Position(1, 17)),
            Token("5", TokenType.VALUE_NUMBER, Position(1, 18), Position(1, 19)),
            Token(";", TokenType.SEMI_COLON, Position(1, 19), Position(1, 20)),
            Token("5", TokenType.VALUE_NUMBER, Position(2, 1), Position(2, 2)),
            Token("+", TokenType.PLUS, Position(2, 3), Position(2, 4)),
            Token("5", TokenType.VALUE_NUMBER, Position(2, 5), Position(2, 6)),
            Token(";", TokenType.SEMI_COLON, Position(2, 7), Position(2, 8))
        );

        val parser = buildParser(expected)
        val ast = parser.parse()

        val expectedAST = listOf(ASTNodeImpl(
            Token("=", TokenType.ASSIGNATION, Position(1, 16), Position(1, 17)),
            NodeType.ASSIGNMENT_NODE,
            listOf(
                ASTNodeImpl(
                    Token("a", TokenType.IDENTIFIER, Position(1, 5), Position(1, 6)),
                    NodeType.IDENTIFIER_NODE,
                    listOf(
                        ASTNodeImpl(
                            Token("let", TokenType.LET_KEYWORD, Position(1, 1), Position(1, 4)),
                            NodeType.KEYWORD_NODE,
                            null
                        ),
                        ASTNodeImpl(
                            Token("number", TokenType.TYPE_NUMBER, Position(1, 9), Position(1, 15)),
                            NodeType.TYPE_NODE,
                            null
                        )
                    )
                ),
                ASTNodeImpl(
                    Token("5", TokenType.VALUE_NUMBER, Position(1, 18), Position(1, 19)),
                    NodeType.NUMBER_NODE,
                    null
                )
            )
        ), ASTNodeImpl(
            Token("+", TokenType.PLUS, Position(2, 3), Position(2, 4)),
            NodeType.OPERATOR_NODE,
            listOf(
                ASTNodeImpl(
                    Token("5", TokenType.VALUE_NUMBER, Position(2, 1), Position(2, 2)),
                    NodeType.NUMBER_NODE,
                    null
                ),
                ASTNodeImpl(
                    Token("5", TokenType.VALUE_NUMBER, Position(2, 5), Position(2, 6)),
                    NodeType.NUMBER_NODE,
                    null
                )
            )
        ))

        assertEquals(expectedAST, ast)
    }

//    @Test
//    fun test007_parseLuchitoExpression(){
//        val tokens = listOf(Token("let", TokenType.LET_KEYWORD, Position(1, 1), Position(1, 4)),
//            Token("variable", TokenType.IDENTIFIER, Position(1, 5), Position(1, 13)),
//            Token(":", TokenType.COLON, Position(1, 14), Position(1, 15)),
//            Token("string", TokenType.TYPE_STRING, Position(1, 16), Position(1, 22)),
//            Token("=", TokenType.ASSIGNATION, Position(1, 23), Position(1, 24)),
//            Token("5", TokenType.VALUE_NUMBER, Position(1, 27), Position(1, 28)),
//            Token("+", TokenType.PLUS, Position(1, 25), Position(1, 26)),
//            Token("\"let\"", TokenType.VALUE_STRING, Position(1, 29), Position(1, 34)),
//            Token("+", TokenType.PLUS, Position(1, 35), Position(1, 36)),
//            Token("1", TokenType.VALUE_NUMBER, Position(1, 37), Position(1, 38)),
//            Token("+", TokenType.PLUS, Position(1, 39), Position(1, 40)),
//            Token("\"println\"", TokenType.VALUE_STRING, Position(1, 41), Position(1, 50)),
//            Token("+", TokenType.PLUS, Position(1, 51), Position(1, 52)),
//            Token("\"aaalet1\"", TokenType.VALUE_STRING, Position(1, 53), Position(1, 62)),
//            Token(";", TokenType.SEMI_COLON, Position(1, 62), Position(1, 63)))
//
//        val parser = buildParser(tokens)
//        val ast = parser.parse()
//
//        val expected = ASTNodeImpl(
//            Token("=", TokenType.ASSIGNATION, Position(1, 23), Position(1, 24)),
//            NodeType.ASSIGNMENT_NODE,
//            listOf(
//                ASTNodeImpl(
//                    Token("variable", TokenType.IDENTIFIER, Position(1, 5), Position(1, 13)),
//                    NodeType.IDENTIFIER_NODE,
//                    listOf(
//                        ASTNodeImpl(
//                            Token("let", TokenType.LET_KEYWORD, Position(1, 1), Position(1, 4)),
//                            NodeType.KEYWORD_NODE,
//                            null
//                        ),
//                        ASTNodeImpl(
//                            Token("string", TokenType.TYPE_STRING, Position(1, 16), Position(1, 22)),
//                            NodeType.TYPE_NODE,
//                            null
//                        )
//                    )
//                ),
//                ASTNodeImpl(
//                    Token("+", TokenType.PLUS, Position(1, 25), Position(1, 26)),
//                    NodeType.OPERATOR_NODE,
//                    listOf(
//                        ASTNodeImpl(
//                            Token("+", TokenType.PLUS, Position(1, 35), Position(1, 36)),
//                            NodeType.OPERATOR_NODE,
//                            listOf(
//                                ASTNodeImpl(
//                                    Token("+", TokenType.PLUS, Position(1, 39), Position(1, 40)),
//                                    NodeType.OPERATOR_NODE,
//                                    listOf(
//                                        ASTNodeImpl(
//                                            Token("+", TokenType.PLUS, Position(1, 51), Position(1, 52)),
//                                            NodeType.OPERATOR_NODE,
//                                            listOf(
//                                                ASTNodeImpl(
//                                                    Token("5", TokenType.VALUE_NUMBER, Position(1, 27), Position(1, 28)),
//                                                    NodeType.NUMBER_NODE,
//                                                    null
//                                                ),
//                                                ASTNodeImpl(
//                                                    Token("\"let\"", TokenType.VALUE_STRING, Position(1, 29), Position(1, 34)),
//                                                    NodeType.STRING_NODE,
//                                                    null
//                                                )
//                                            )
//                                        ),
//                                        ASTNodeImpl(
//                                            Token("1", TokenType.VALUE_NUMBER, Position(1, 37), Position(1, 38)),
//                                            NodeType.NUMBER_NODE,
//                                            null
//                                        )
//                                    )
//                                ),
//                                ASTNodeImpl(
//                                    Token("\"println\"", TokenType.VALUE_STRING, Position(1, 41), Position(1, 50)),
//                                    NodeType.STRING_NODE,
//                                    null
//                                )
//                            )
//                        ),
//                        ASTNodeImpl(
//                            Token("\"aaalet1\"", TokenType.VALUE_STRING, Position(1,
//                            53), Position(1, 62)),
//                            NodeType.STRING_NODE,
//                            null
//                        )
//                    )
//                )
//            )
//        )
//
//        assert(ast.contains(expected))
//    }

}


