package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.ast.ASTNodeImpl
import edu.austral.ingsis.gradle.common.ast.OperatorNode
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.common.token.TokenType
import edu.austral.ingsis.gradle.parser.ComposeParser
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.getSublists
import edu.austral.ingsis.gradle.parser.validator.Validator

class OperationParser(
    private val operator: TokenType,
    override val validator: Validator,
    override val parsers: List<Parser>
) : ComposeParser {
    override fun parse(tokens: List<Token>): ASTNode {
        val operatorToken = tokens.findLast { it.tokenType == operator }
        val tokenIndex = tokens.lastIndexOf(operatorToken)
        val (leftSide, rightSide) = getSublists(tokens, tokenIndex);
        var ast = ASTNodeImpl(operatorToken?.value, operatorToken, OperatorNode, emptyList());
        parsers.forEach {
            if (it.canParse(leftSide)) {
                ast = ast.addChild(it.parse(leftSide))
            }
            if (it.canParse(rightSide)) {
                ast = ast.addChild(it.parse(rightSide))
            }
        }
        return ast;
    }

    override fun canParse(tokens: List<Token>): Boolean {
        return validator.validate(tokens)
    }
}