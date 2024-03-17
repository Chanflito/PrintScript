package interpreter
import kotlin.test.Test


class InterpreterTest {
    @Test
    fun directSumAndPrint() {
        val input = input_001
        val interpreter = Interpreter(input)
        val results= interpreter.interpret()
        assert(results[0] == 12)
    }
    @Test
    fun assignationAndPrintWithSum(){
        val input = input_002
        val interpreter = Interpreter(input)
        val results= interpreter.interpret()
        assert(results[0] == 10)
    }
}