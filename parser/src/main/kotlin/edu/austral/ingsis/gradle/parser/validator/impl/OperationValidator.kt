package edu.austral.ingsis.gradle.parser.validator.impl

import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.util.isOperator
import edu.austral.ingsis.gradle.parser.validator.Validator

class OperationValidator : Validator {
    override fun validate(tokens: List<Token>): Boolean {
        val (operators, operands) = tokens.partition { isOperator(it) }
        return operators.size == operands.size - 1
    }
}