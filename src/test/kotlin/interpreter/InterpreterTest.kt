import common.ast.ASTNodeImpl
import common.ast.NodeType
import common.token.Position
import common.token.Token
import common.token.TokenType
import kotlin.test.Test

class InterpreterTest {
    @Test
    fun simpleAssignationAndPrint() {
        val exampleTree1 = ASTNodeImpl(
            Token("=", TokenType.ASSIGNATION, Position(1, 1), Position(1, 1)),
            NodeType.ASSIGNMENT_NODE,
            listOf(
                ASTNodeImpl(
                    Token("x", TokenType.IDENTIFIER, Position(1, 1), Position(1, 1)),
                    NodeType.IDENTIFIER_NODE,
                    null
                ),
                ASTNodeImpl(
                    Token("5", TokenType.VALUE_NUMBER, Position(1, 3), Position(1, 3)),
                    NodeType.NUMBER_NODE,
                    null
                )
            )
)
        val examplePrintNode = ASTNodeImpl(
            Token("print", TokenType.PRINTLN_KEYWORD, Position(1, 1), Position(1, 1)),
            NodeType.PRINT_NODE,
            listOf(
                ASTNodeImpl(
                    Token("x", TokenType.IDENTIFIER, Position(1, 1), Position(1, 1)),
                    NodeType.IDENTIFIER_NODE,
                    null
                )
            )
        )
        val interpreter = Interpreter(listOf(exampleTree1, examplePrintNode))
        val results = interpreter.interpret()
        assert(results[0] == "5")
    }

    @Test
    fun multipleAssignationsAndPrint() {
        val exampleTree1 = ASTNodeImpl(
            Token("=", TokenType.ASSIGNATION, Position(1, 1), Position(1, 1)),
            NodeType.ASSIGNMENT_NODE,
            listOf(
                ASTNodeImpl(
                    Token("x", TokenType.IDENTIFIER, Position(1, 1), Position(1, 1)),
                    NodeType.IDENTIFIER_NODE,
                    null
                ),
                ASTNodeImpl(
                    Token("hello", TokenType.VALUE_NUMBER, Position(1, 3), Position(1, 3)),
                    NodeType.NUMBER_NODE,
                    null
                )
            )
        )
        val exampleTree2 = ASTNodeImpl(
            Token("=", TokenType.ASSIGNATION, Position(2, 1), Position(2, 1)),
            NodeType.ASSIGNMENT_NODE,
            listOf(
                ASTNodeImpl(
                    Token("y", TokenType.IDENTIFIER, Position(2, 1), Position(2, 1)),
                    NodeType.IDENTIFIER_NODE,
                    null
                ),
                ASTNodeImpl(
                    Token("world", TokenType.VALUE_NUMBER, Position(2, 3), Position(2, 3)),
                    NodeType.NUMBER_NODE,
                    null
                )
            )
        )
        val examplePrintNode = ASTNodeImpl(
            Token("print", TokenType.PRINTLN_KEYWORD, Position(3, 1), Position(3, 1)),
            NodeType.PRINT_NODE,
            listOf(
                ASTNodeImpl(
                    Token("x", TokenType.IDENTIFIER, Position(3, 1), Position(3, 1)),
                    NodeType.IDENTIFIER_NODE,
                    null
                ), ASTNodeImpl(
                    Token("+", TokenType.PLUS, Position(3, 3), Position(3, 3)),
                    NodeType.OPERATOR_NODE,
                    listOf(
                        ASTNodeImpl(
                            Token("y", TokenType.IDENTIFIER, Position(3, 5), Position(3, 5)),
                            NodeType.IDENTIFIER_NODE,
                            null
                        )
                    )
                )
            )
            )
        val interpreter = Interpreter(listOf(exampleTree1, exampleTree2, examplePrintNode))
        val results = interpreter.interpret()
        assert(results[0] == "helloworld")
    }
}