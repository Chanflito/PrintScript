package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.parser.util.NoTokenFoundErrorMessage

import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.builder.AstBuilder
import edu.austral.ingsis.gradle.parser.builder.impl.AssignationNodeWithLetBuilder
import edu.austral.ingsis.gradle.parser.builder.impl.IdentifierNodeWithLetBuilder
import edu.austral.ingsis.gradle.parser.validator.SyntaxValidator
import edu.austral.ingsis.gradle.parser.validator.impl.TypeAssignmentValidator
import edu.austral.ingsis.gradle.parser.validator.impl.VariableDeclarationValidator
import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.ast.ASTNodeImpl


//Here should go assignations like let a : number= 7; or let a : number;
class DeclarationParser(
    private val validators: List<Pair<SyntaxValidator, AstBuilder<ASTNodeImpl>>> = listOf(
        Pair(TypeAssignmentValidator(), IdentifierNodeWithLetBuilder()),
        Pair(VariableDeclarationValidator(), AssignationNodeWithLetBuilder())
    )
) :
    Parser<InputContext> {
    override fun parse(input: InputContext): Pair<ASTNode, Int> {
        val copyIndex = input.index
        validators.forEach { (validator, builder) ->
            if (validator.validate(input.tokens, copyIndex)) {
                return builder.build(input.tokens, copyIndex)
            }
        }
        throw Exception(NoTokenFoundErrorMessage(copyIndex).toString())
    }

}