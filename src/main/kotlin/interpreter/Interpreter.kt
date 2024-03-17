import common.ast.*

class Interpreter(val nodeList: List<ASTNode>) {

    private val valuesMap = hashMapOf<String, Any>()

    fun interpret(): List<Any> {
        val printNodes = nodeList.filter { it.nodeType is PrintLnNode }
        val assignationNodes = nodeList.filter { it.nodeType is AssignationNode }
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
            is IdentifierNode -> node.value.toString()?: ""
            else -> {
                var foundIdentifier = ""
                for (child in node.children!!) {
                    foundIdentifier = findIdentifier(child).toString()
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
            is StringNode -> {
                node.value ?: ""
            }
            is NumberNode -> {
                node.value ?: 0
            }
            is OperatorNode -> {
                val initialValue = when (node.value) {
                    "+" -> if (node.children?.any { it.nodeType is StringNode } == true) "" else 0
                    "-" -> 0  // Subtracting strings doesn't make sense, so initialize with 0
                    "*" -> 1  // Multiplication identity value
                    "/" -> 1  // Division identity value
                    else -> throw Exception("Invalid operator")
                }
                var result: Any = initialValue
                for (child in node.children!!) {
                    val operand = findValue(child)
                    if (child.nodeType !is OperatorNode) {
                        result = performOperation(result, operand, node.value.toString())
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


    private fun performOperation(leftOperand: Any, rightOperand: Any, operator: String):Any{
        return if (leftOperand is String && rightOperand is String) {
            performStringOperation(leftOperand, rightOperand, operator)
        } else if (leftOperand is Int && rightOperand is Int) {
            performNumberOperation(leftOperand, rightOperand, operator)
        } else {
            throw Exception("Invalid Operation")
        }
    }

    private fun performStringOperation(leftOperand: String, rightOperand: String, operator: String): String {
        return when (operator) {
            "+" -> leftOperand + rightOperand
            else -> throw Exception("Invalid Operation")
        }
    }

    private fun performNumberOperation(leftOperand: Int, rightOperand: Int, operator: String):Int {
        return when (operator) {
            "+" -> leftOperand + rightOperand
            "-" -> leftOperand - rightOperand
            "*" -> leftOperand * rightOperand
            "/" -> leftOperand / rightOperand
            else -> throw Exception("Invalid operator")
        }
    }

    private fun searchPrint(node: ASTNode): Any {
        return when (node.nodeType) {
            is IdentifierNode -> {
                val identifier = node.value.toString()
                valuesMap[identifier] ?: ""
            }
            is StringNode -> {
                node.value ?: ""
            }
            is NumberNode -> {
                node.value ?: 0
            }
            is OperatorNode -> {
                val initialValue = when (node.value) {
                    "+" -> if (node.children?.any { it.nodeType is StringNode } == true) "" else 0
                    "-" -> 0  // Subtracting strings doesn't make sense, so initialize with 0
                    "*" -> 1  // Multiplication identity value
                    "/" -> 1  // Division identity value
                    else -> throw Exception("Invalid operator")
                }
                var result: Any = initialValue
                for (child in node.children!!) {
                    val operand = searchPrint(child)
                    if (child.nodeType !is OperatorNode) {
                        result = performOperation(result, operand, node.value.toString())
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