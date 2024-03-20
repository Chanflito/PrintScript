package impl

import common.ast.ASTNode
import common.token.Token
import Parser
import ast.ASTNodeImpl
import ast.PrintLnNode
import common.token.PrintlnKeyword
import util.consumeToken
import util.currentToken
import util.isLeftParenthesis


/*Inside of parenthesis should go only expressions, so i call the Expression Parser.
* println(1+1) is valid
* println(a + b) where a and b are variables is valid
 */
class PrintlnParser (private val startIndex:Int) : Parser {
    override fun parse(tokens: List<Token>): ASTNode {
        val indexCopy=startIndex
        return when(currentToken(tokens,indexCopy)?.tokenType){
            PrintlnKeyword->parsePrintLn(tokens,indexCopy)
            else->throw Exception("Invalid token")//Todo change this.
        }
    }

    private fun parsePrintLn(tokens:List<Token>,index:Int): ASTNode{
        val consumeResult= consumeToken(tokens,index)
        val token=consumeResult.first
        val newIndex=consumeResult.second
        require(isLeftParenthesis(currentToken(tokens,newIndex))){"Missing ( after method call"}
        val childNode=ExpressionParser(index+1).parse(tokens)
        return ASTNodeImpl("println",token, PrintLnNode,listOf(childNode))
    }
}