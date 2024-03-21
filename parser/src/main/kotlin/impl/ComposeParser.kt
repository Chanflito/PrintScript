package impl

import Parser
import ast.ASTNodeImpl
import ast.ProgramNode
import common.ast.ASTNode
import common.token.*
import util.currentToken
import util.endOfFile

class ComposeParser(private val index: Int=0, private val astNode: ASTNodeImpl = ASTNodeImpl("Program", null, ProgramNode, listOf())) :
    Parser {

    override fun parse(tokens: List<Token>): Pair<ASTNode, Int> {
        val indexCopy=index+1
        if (tokens.size==1 && !endOfFile(tokens,index)) return handleResult(tokens)
        if (endOfFile(tokens, indexCopy) || currentToken(tokens,indexCopy)==null){
            return Pair(astNode, indexCopy)
        }
        return if (tokens[indexCopy].tokenType is SemiColon){
            ComposeParser(indexCopy +1 ,astNode).parse(tokens)
        } else{
            handleResult(tokens)
        }
    }

    private fun handleResult(tokens: List<Token>): Pair<ASTNode, Int> {
        return when (currentToken(tokens, index)?.tokenType) {//TODO CHANGE THIS HARDCODED VALUES
            is PrintlnKeyword -> {
                val printLnParser = PrintlnParser(index)
                val result = printLnParser.parse(tokens)
                val newIndex = result.second
                val newAstNode = astNode.addChild(result.first)
                ComposeParser(newIndex, newAstNode).parse(tokens)
            }

            is ValueString, ValueNumber, LeftParenthesis -> {
                val expressionParser = ExpressionParser(index)
                val result = expressionParser.parse(tokens)
                val newIndex = result.second
                val newAstNode = astNode.addChild(result.first)
                ComposeParser(newIndex, newAstNode).parse(tokens)

            }

            is LetKeyword -> {
                val declarativeParser = DeclarationParser(index)
                val result = declarativeParser.parse(tokens)
                val newIndex = result.second
                val newAstNode = astNode.addChild(result.first)
                ComposeParser(newIndex, newAstNode).parse(tokens)
            }

            is Identifier -> {
                val assignationParser = AssignationParser(index)
                val result = assignationParser.parse(tokens)
                val newIndex = result.second
                val newAstNode = astNode.addChild(result.first)
                ComposeParser(newIndex, newAstNode).parse(tokens)
            }

            else -> {
                throw Exception("Invalid token")
            }
        }
    }


}