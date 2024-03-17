import common.ast.ASTNode
import common.ast.NodeType

class Interpreter(val nodeList: List<ASTNode>) {

    private val valuesMap = hashMapOf<String, Any>()

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

    private fun searchAssignation(node: ASTNode) {
        val identifier = findIdentifier(node)
        val value = findValue(node)
        valuesMap[identifier] = value
    }

    private fun findIdentifier(node: ASTNode): String {
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

    private fun findValue(node: ASTNode): Any {
        return when (node.nodeType) {
            NodeType.STRING_NODE -> {
                node.token?.value ?: ""
            }
            NodeType.NUMBER_NODE -> {
                node.token?.value ?: 0
            }
            NodeType.OPERATOR_NODE -> {
                var result = ""
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

    private fun performOperation(leftOperand: Any, rightOperand: Any, operator: String):String{
        return if (leftOperand is String || rightOperand is String) {
            performStringOperation(leftOperand.toString(), rightOperand.toString(), operator)
        } else {
            performNumberOperation(leftOperand as Int, rightOperand as Int, operator)
        }
    }

    private fun performStringOperation(leftOperand: String, rightOperand: String, operator: String): String {
        return when (operator) {
            "+" -> leftOperand + rightOperand
            else -> throw Exception("Invalid Operation")
        }
    }

    private fun performNumberOperation(leftOperand: Int, rightOperand: Int, operator: String):String {
        return when (operator) {
            "+" -> (leftOperand + rightOperand).toString()
            "-" -> (leftOperand - rightOperand).toString()
            "*" -> (leftOperand * rightOperand).toString()
            "/" -> (leftOperand / rightOperand).toString()
            else -> throw Exception("Invalid operator")
        }
    }

    private fun searchPrint(node: ASTNode): Any {
        return when (node.nodeType) {
            NodeType.IDENTIFIER_NODE -> {
                val identifier = node.token?.value ?: ""
                valuesMap[identifier] ?: ""
            }
            NodeType.STRING_NODE -> {
                node.token?.value ?: ""
            }
            NodeType.NUMBER_NODE -> {
                node.token?.value ?: 0
            }
            NodeType.OPERATOR_NODE -> {
                var result = ""
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
