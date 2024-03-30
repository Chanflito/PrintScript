package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.ast.AssignationNode
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.NumberNode
import edu.austral.ingsis.gradle.common.ast.OperatorNode
import edu.austral.ingsis.gradle.common.ast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.StringNode
import edu.austral.ingsis.gradle.interpreter.util.isIdentifier
import edu.austral.ingsis.gradle.interpreter.util.isKeyword

class Interpreter {
    private val valuesMap = hashMapOf<String, Any>()

    fun interpret(programNode: ASTNode): List<Any> {
//      in case there's another type of node in the future
        return when (programNode.nodeType) {
            ProgramNode -> evaluateChildren(programNode.children)
            else -> throw Exception("Invalid node type for program")
        }
    }

    private fun evaluateChildren(children: List<ASTNode>): MutableList<Any> {
        val result = mutableListOf<Any>()
        for (child in children) {
            when (child.nodeType) {
                PrintLnNode -> result.add(evaluate(child))
                else -> evaluate(child)
            }
        }
        return result
    }

    private fun evaluate(node: ASTNode): Any {
        return when (node.nodeType) {
            PrintLnNode -> evaluatePrintNode(node)
            AssignationNode -> evaluateAssignationNode(node)
            OperatorNode -> evaluateOperatorNode(node)
            IdentifierNode -> evaluateIdentifierNode(node)
            NumberNode -> node.value ?: 0
            StringNode -> node.value ?: ""
            else -> throw Exception("Invalid node type")
        }
    }

    private fun evaluateIdentifierNode(node: ASTNode): Any {
        return valuesMap[node.value.toString()] ?: throw Exception("Identifier not found")
    }

    private fun evaluateOperatorNode(node: ASTNode): Any {
        val evaluatedChildren = node.children.map { evaluate(it) }

        require(evaluatedChildren.isNotEmpty()) { "Operator node must have children to evaluate" }

        if (evaluatedChildren.all { it is String }) {
            return when (node.value) {
                "+" -> evaluatedChildren.reduce { acc, i -> (acc as String) + (i as String) }
                else -> throw Exception("Invalid operator for string operators")
            }
        }

        return when (node.value) { // TODO ADD HERE OPERATION BETWEEN STRINGS AND NUMBERS, CONCATENATION!!! Source_003 is not supported.
            "+" -> evaluatedChildren.reduce { acc, i -> (acc as Double) + (i as Double) }
            "-" -> evaluatedChildren.reduce { acc, i -> (acc as Double) - (i as Double) }
            "*" -> evaluatedChildren.reduce { acc, i -> (acc as Double) * (i as Double) }
            "/" -> evaluatedChildren.reduce { acc, i -> (acc as Double) / (i as Double) }
            else -> throw Exception("Invalid operator")
        }
    }

    private fun evaluateAssignationNode(node: ASTNode): Any {
//        the first child is the identifier, if not makes any sense
        val identifierNode = node.children.first { isIdentifier(it) }
        val identifier = identifierNode.value.toString()

//      check if the identifier is already declared or not declared at all
        checkVariableAssignation(identifierNode)

//       the rest of children list must be one only child and is the value to be assigned
        val filteredChildren = node.children.filter { it != identifierNode }
        val value = evaluate(filteredChildren.first())

        valuesMap[identifier] = value
        return value
    }

    private fun evaluatePrintNode(node: ASTNode): Any {
        val value = evaluate(node.children.first())
        println(value)
        return value
    }

    private fun checkVariableAssignation(node: ASTNode) {
        val identifier = node.value.toString()
        val keyword = node.children.firstOrNull { isKeyword(it) }
        if (isNotDeclaredNorInMap(keyword, identifier)) {
            throw Exception("Variable $identifier not found")
        }

        if (isRedeclared(identifier, keyword)) {
            throw Exception("Variable $identifier already declared")
        }
    }

    private fun isRedeclared(
        identifier: String,
        keyword: ASTNode?,
    ) = valuesMap.contains(identifier) && keyword != null

    private fun isNotDeclaredNorInMap(
        keyword: ASTNode?,
        identifier: String,
    ) = keyword == null && !valuesMap.contains(identifier)
}
