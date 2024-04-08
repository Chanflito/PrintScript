package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.ast.ASTNodeImpl
import edu.austral.ingsis.gradle.common.ast.AssignationNode
import edu.austral.ingsis.gradle.common.token.Assignation
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.ComposeParser
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.getSublists
import edu.austral.ingsis.gradle.parser.validator.Validator

class AssignationParser(override val validator: Validator, override val parsers: List<Parser>) : ComposeParser {
    override fun parse(tokens: List<Token>): ASTNode {
        val assignationToken = tokens.find { it.tokenType == Assignation }
        val tokenIndex = tokens.indexOf(assignationToken)
        val (leftSide, rightSide) = getSublists(tokens, tokenIndex);
        var ast = ASTNodeImpl(assignationToken?.value, assignationToken, AssignationNode, emptyList());
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