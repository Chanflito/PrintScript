package interpreter
import common.ast.ASTNodeImpl
import common.ast.ProgramNode
import kotlin.test.Test


class InterpreterTest {
    @Test
    fun directSumAndPrint() {
        val input = ASTNodeImpl("Program",null,ProgramNode,input_001)
        val interpreter = Interpreter()
        val results= interpreter.interpret(input)
        assert(results[0] == 12)
    }
    @Test
    fun assignationAndPrintWithSum(){
        val input = ASTNodeImpl("Program",null,ProgramNode,input_002)
        val interpreter = Interpreter()
        val results= interpreter.interpret(input)
        assert(results[0] == 10)
    }
}