package impl

import InputContext
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
class PrintlnParser () : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<ASTNode,Int> {
        val indexCopy=input.index
        return when(currentToken(input.tokens,indexCopy)?.tokenType){
            PrintlnKeyword->parsePrintLn(input.tokens,indexCopy)
            else->throw Exception("Invalid token")//Todo change this.
        }
    }

    private fun parsePrintLn(tokens:List<Token>,index:Int): Pair<ASTNode,Int>{
        val consumeResult= consumeToken(tokens,index)
        val token=consumeResult.first
        val newIndex=consumeResult.second
        require(isLeftParenthesis(currentToken(tokens,newIndex))){"Missing ( after method call"}
        val childNode=ExpressionParser().parse(InputContext(tokens,index+1));
        return Pair(ASTNodeImpl(token?.value,token, PrintLnNode, listOf(childNode.first)),childNode.second)
    }
}