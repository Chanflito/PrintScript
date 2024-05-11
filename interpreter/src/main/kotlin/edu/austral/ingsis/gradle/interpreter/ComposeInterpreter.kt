package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.EnvReader
import edu.austral.ingsis.gradle.interpreter.util.InputReader
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterList
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager
import edu.austral.ingsis.gradle.interpreter.util.Printer

class ComposeInterpreter(
    interpreters: List<Interpreter> = InterpreterList().getInterpreters(),
    emitter: Printer,
    envReader: EnvReader,
    inputReader: InputReader,
    private val context: Context = Context(),
) {
    private val manager = InterpreterManager(interpreters, emitter, envReader, inputReader)

    fun interpret(ast: AST): InterpretResult {
        val interpreter = manager.getInterpreterDisregardingType(ast)
        return interpreter.interpret(ast, context, manager)
    }

    fun interpretAndUpdateContext(ast: AST): ComposeInterpreter {
        val interpretResult = interpret(ast)
        if (interpretResult is InterpretResult.ContextResult) {
            return updateContext(interpretResult.context)
        }
        return this
    }

    private fun updateContext(newContext: Context): ComposeInterpreter {
        return ComposeInterpreter(manager.interpreters, manager.printer, manager.envReader, manager.inputReader, context.update(newContext))
    }

    fun getContext(): Context {
        return context
    }

    fun getPrinter(): Printer {
        return manager.printer
    }
}
