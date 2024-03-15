import common.ast.ASTNode
import common.ast.NodeType

class Interpreter(val nodeList: List<ASTNode>) {

    val valuesLMap = hashMapOf<String, String>()

    fun interpret(): String {
        val printNodes = nodeList.filter { it.nodeType == NodeType.PRINT_NODE }
        val assignationNodes = nodeList.filter { it.nodeType == NodeType.ASSIGNMENT_NODE }
        for (node in assignationNodes) {
            searchAssignation(node)
        }

        val result = StringBuilder()
        for (node in printNodes) {
            val identifier = findIdentifier(node)
            val value = valuesLMap[identifier] ?: ""
            result.append(value)
        }
        return result.toString()
    }

    fun searchAssignation(node: ASTNode) {
        val identifier = findIdentifier(node)
        val value = findValue(node)
        valuesLMap[identifier] = value
    }

    fun findIdentifier(node: ASTNode): String {
        return when (node.nodeType) {
            NodeType.IDENTIFIER_NODE -> node.token?.value ?: ""
            else -> {
                var foundIdentifier = ""
                for (child in node.children!!) {
                    foundIdentifier = findIdentifier(child)
                    if (foundIdentifier.isNotEmpty()) {
                        break
                    }
                }
                foundIdentifier
            }
        }
    }

    fun findValue(node: ASTNode): String {
        return when (node.nodeType) {
            NodeType.STRING_NODE -> {
                node.token?.value ?: ""
            }
            NodeType.NUMBER_NODE -> {
                node.token?.value ?: "0"
            }
            NodeType.OPERATOR_NODE -> {
                var result = "0"
                for (child in node.children!!) {
                    val operand = findValue(child)
                    if (child.nodeType != NodeType.OPERATOR_NODE) {
                        result = performNumberOperation(result, operand, node.token?.value ?: "")
                    }
                }
                result
            }
            else -> {
                var foundValue = ""
                node.children?.forEach {
                    val value = findValue(it)
                    if (value.isNotEmpty()) {
                        foundValue = value
                        return@forEach
                    }
                }
                foundValue
            }
        }
    }

    fun performStringOperation(leftOperand: String, rightOperand: String): String {
        return leftOperand + rightOperand
    }

    fun performNumberOperation(leftOperand: String, rightOperand: String, operator: String): String {
        val left = leftOperand.toIntOrNull() ?: 0
        val right = rightOperand.toIntOrNull() ?: 0
        val result = when (operator) {
            "+" -> left + right
            "-" -> left - right
            "*" -> left * right
            "/" -> left / right
            else -> 0
        }
        return result.toString()
    }
}