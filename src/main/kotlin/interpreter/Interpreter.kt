import common.ast.ASTNode
import common.ast.NodeType

class Interpreter(val nodeList: List<ASTNode>) {

    val valuesMap = hashMapOf<String, Any>()

    fun interpret(): List<Any> {
        val printNodes = nodeList.filter { it.nodeType == NodeType.PRINT_NODE }
        val assignationNodes = nodeList.filter { it.nodeType == NodeType.ASSIGNMENT_NODE }
        for (node in assignationNodes) {
            searchAssignation(node)
        }
        val results = mutableListOf<Any>()
        for (node in printNodes) {
            val result = searchPrint(node)
            println(result)
            results.add(result)
        }
        return results
    }

    fun searchAssignation(node: ASTNode) {
        val identifier = findIdentifier(node)
        val value = findValue(node)
        valuesMap[identifier] = value
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

    fun findValue(node: ASTNode): Any {
        return when (node.nodeType) {
            NodeType.STRING_NODE -> {
                node.token?.value ?: ""
            }
            NodeType.NUMBER_NODE -> {
                node.token?.value?.toIntOrNull() ?: 0
            }
            NodeType.OPERATOR_NODE -> {
                var result = 0
                for (child in node.children!!) {
                    val operand = findValue(child)
                    if (child.nodeType != NodeType.OPERATOR_NODE) {
                        result = performOperation(result, operand, node.token?.value ?: "")
                    }
                }
                result
            }
            else -> {
                var foundValue: Any = ""
                node.children?.forEach {
                    val value = findValue(it)
                    if (value != "") {
                        foundValue = value
                        return@forEach
                    }
                }
                foundValue
            }
        }
    }

    fun performOperation(leftOperand: Any, rightOperand: Any, operator: String): Any {
        return if (leftOperand is String || rightOperand is String) {
            performStringOperation(leftOperand.toString(), rightOperand.toString())
        } else {
            performNumberOperation(leftOperand as Int, rightOperand as Int, operator)
        }
    }

    fun performStringOperation(leftOperand: String, rightOperand: String): String {
        return leftOperand + rightOperand
    }

    fun performNumberOperation(leftOperand: Int, rightOperand: Int, operator: String): Int {
        return when (operator) {
            "+" -> leftOperand + rightOperand
            "-" -> leftOperand - rightOperand
            "*" -> leftOperand * rightOperand
            "/" -> leftOperand / rightOperand
            else -> 0 // Handle invalid operator case
        }
    }

    fun searchPrint(node: ASTNode): Any {
        return when (node.nodeType) {
            NodeType.IDENTIFIER_NODE -> {
                val identifier = node.token?.value ?: ""
                valuesMap[identifier] ?: ""
            }
            NodeType.STRING_NODE -> {
                node.token?.value ?: ""
            }
            NodeType.NUMBER_NODE -> {
                node.token?.value?.toIntOrNull() ?: 0
            }
            NodeType.OPERATOR_NODE -> {
                var result = 0
                for (child in node.children!!) {
                    val operand = searchPrint(child)
                    if (child.nodeType != NodeType.OPERATOR_NODE) {
                        result = performOperation(result, operand, node.token?.value ?: "")
                    }
                }
                result
            }
            else -> {
                var foundValue: Any = ""
                node.children?.forEach {
                    val value = searchPrint(it)
                    if (value != "") {
                        foundValue = value
                        return@forEach
                    }
                }
                foundValue
            }
        }
    }
}
