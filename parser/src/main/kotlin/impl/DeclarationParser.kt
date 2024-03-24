package impl

import Parser
import InputContext
import ast.*
import builder.AstBuilder
import builder.impl.AssignationNodeWithLetBuilder
import builder.impl.IdentifierNodeWithLetBuilder
import util.*
import validator.SyntaxValidator
import validator.impl.TypeAssignmentValidator
import validator.impl.VariableDeclarationValidator

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