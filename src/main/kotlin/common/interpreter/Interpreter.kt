package common.interpreter
import common.ast

class Interpreter(val astList: List<ASTNode>) {
    fun interpret() {
        val printNodes = astList.filter { it.nodeType == NodeType.PRINT_NODE }
        val assignmentNodes = astList.filter { it.nodeType == NodeType.ASSIGNMENT }
        val variables = buildMap(emptyMap(), assignmentNodes)
        printNodes.forEach {printLine(it, variables)}
    }

    private fun buildMap(variables: Map<String, String>, nodes: List<ASTNode>): Map<String, String> {
        if(nodes.isEmpty()) return variables
        val node = nodes.removeAt(0)
        val name = findName(node)
        val value = findValue(node)
        return buildMap(variables.plus(Pair(name, value)), nodes)
    }

    private fun findValue(node: ASTNode): String {
        return when (node.token?.type) {
            TokenType.VALUE_STRING -> node.token.value
            TokenType.VALUE_NUMBER -> node.token.value.toString()
            else -> {
                node.children.fold("") {acc, child -> acc + findValue(child)}
            }
        }
    }

    private fun findName(node: ASTNode):String {
        return when (node.token?.type) {
            TokenType.IDENTIFIER -> node.token.value
            else -> {
                node.children.fold("") {acc, child -> acc + findName(child)}
            }
        }
    }

    private fun printLine(node: ASTNode, variables: Map<String, String>) {

    }
}