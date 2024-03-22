package impl

import InputContext
import Parser
import ast.ASTNodeImpl
import ast.ProgramNode
import common.ast.ASTNode
import common.token.*
import util.currentToken
import util.endOfFile

class ComposeParser(
    private val parsers: Map<TokenType, Parser<InputContext>>,
    private val astNode: ASTNodeImpl = ASTNodeImpl("Program", null, ProgramNode, listOf())
) :
    Parser<InputContext> {

    override fun parse(input: InputContext): Pair<ASTNode, Int> {
        val indexCopy=input.index+1
        if (input.tokens.size==1 && !endOfFile(input.tokens,input.index)) return handleResult(input)
        if (endOfFile(input.tokens, indexCopy) || currentToken(input.tokens,indexCopy)==null){
            return Pair(astNode, indexCopy)
        }
        return if (input.tokens[indexCopy].tokenType is SemiColon){
            ComposeParser(parsers,astNode).parse(InputContext(input.tokens,indexCopy +1))
        } else{
            handleResult(input)
        }
    }



    private fun handleResult (input: InputContext) :Pair<ASTNode, Int>{
        val result=currentToken(input.tokens,input.index)?.tokenType
        val parserFound= parsers[result];
        if (parserFound!=null){
            return parseWith(input, parserFound)
        }
        throw Exception("Invalid token")

    }
    private fun parseWith(input: InputContext, parser: Parser<InputContext>): Pair<ASTNode, Int> {
        val result = parser.parse(InputContext(input.tokens, input.index))
        val newIndex = result.second
        val newAstNode = astNode.addChild(result.first)
        return ComposeParser(parsers, newAstNode).parse(InputContext(input.tokens, newIndex))
    }

}