package interpreter
import common.ast.ASTNode
import common.ast.NodeType
import common.token.TokenType

class Interpreter(val astList: List<ASTNode>) {
    fun interpret() {
        val printNodes = astList.filter { it.nodeType == NodeType.PRINT_NODE }
        val assignmentNodes = astList.filter { it.nodeType == NodeType.ASSIGNMENT_NODE }
        val variables = buildMap(emptyMap(), assignmentNodes.toMutableList())
        printNodes.forEach {printLine(it, variables)}
    }

    private fun buildMap(variables: Map<String?, String?>, nodes: MutableList<ASTNode>): Map<String?, String?> {
        if(nodes.isEmpty()) return variables
        val node = nodes.removeAt(0);
        val name = findName(node)
        val value = findValue(node)
        return buildMap(variables.plus(Pair(name, value)), nodes)
    }

    private fun findValue(node: ASTNode): String? {
        return when (node.token?.tokenType) {
            TokenType.VALUE_STRING -> node.token!!.value
            TokenType.VALUE_NUMBER -> node.token!!.value
            else -> {
                node.children?.fold("") { acc, child -> acc + findValue(child)}
            }
        }
    }

    private fun findName(node: ASTNode): String? {
        return when (node.token?.tokenType) {
            TokenType.IDENTIFIER -> node.token!!.value
            else -> {
                node.children?.fold("") { acc, child -> acc + findName(child)}
            }
        }
    }
    //TODO: Refactor this to use a visitor pattern
    private fun printLine(node: ASTNode, variables: Map<String?, String?>) {
        val value = findValue(node)
        val valueToPrint = if(value?.startsWith("\"") == true) value.substring(1, value.length - 1) else variables[value]
        println(valueToPrint)
    }
}