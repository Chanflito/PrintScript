// package edu.austral.ingsis.gradle.interpreter.newinterpreter
// import java.util.NoSuchElementException
//
// class MainInterpreter( val iterator: ASTIterator, val interpreterFactory: InterpreterFactory) {
//
//    // Define a method to start interpreting
//    fun interpret(): InterpretResult {
//        val context = Context() // create a new context for interpretation
//        var result: InterpretResult? = null // initialize the result variable
//
//        try {
//            while (true) { // loop until NoSuchElementException is thrown
//                val astToInterpret = iterator.next() // get the next AST node to interpret from the iterator
//
//                // Create an interpreter for the AST node
//                val interpreter = interpreterFactory.createInterpreter(astToInterpret, context)
//
//                // Interpret the AST node and get the result
//                result = interpreter.interpret()
//
//                // Update context based on interpreter result if needed
//                context = context.update(result)
//            }
//        } catch (e: NoSuchElementException) {
//            // NoSuchElementException indicates that there are no more elements in the iterator
//            // Return the last interpretation result or an empty result if nothing was interpreted
//            return result ?: InterpretResult()
//        }
//    }
// }
