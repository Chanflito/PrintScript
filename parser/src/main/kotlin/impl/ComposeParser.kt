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
                parseWith(tokens,PrintlnParser(index))
            }

            is ValueString, ValueNumber, LeftParenthesis -> {
                parseWith(tokens,ExpressionParser(index))
            }

            is LetKeyword -> {
               parseWith(tokens,DeclarationParser(index))
            }

            is Identifier -> {
                parseWith(tokens,AssignationParser(index))
            }

            else -> {
                throw Exception("Invalid token")
            }
        }
    }

    private fun parseWith(tokens: List<Token>, parser: Parser): Pair<ASTNode, Int> {
        val result = parser.parse(tokens)
        val newIndex = result.second
        val newAstNode = astNode.addChild(result.first)
        return ComposeParser(newIndex, newAstNode).parse(tokens)
    }


}