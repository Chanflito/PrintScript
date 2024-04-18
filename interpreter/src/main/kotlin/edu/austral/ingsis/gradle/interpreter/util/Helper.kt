package edu.austral.ingsis.gradle.interpreter.util

import edu.austral.ingsis.gradle.common.ast.newast.BooleanNodeType
import edu.austral.ingsis.gradle.common.ast.newast.NodeType
import edu.austral.ingsis.gradle.common.ast.newast.NumberNodeType
import edu.austral.ingsis.gradle.common.ast.newast.StringNodeType
import edu.austral.ingsis.gradle.interpreter.BlockNodeInterpreter
import edu.austral.ingsis.gradle.interpreter.BooleanLiteralInterpreter
import edu.austral.ingsis.gradle.interpreter.DeclarationAssignationInterpreter
import edu.austral.ingsis.gradle.interpreter.DeclarationInterpreter
import edu.austral.ingsis.gradle.interpreter.DivideInterpreter
import edu.austral.ingsis.gradle.interpreter.IdentifierInterpreter
import edu.austral.ingsis.gradle.interpreter.IfElseStatementInterpreter
import edu.austral.ingsis.gradle.interpreter.IfStatementInterpreter
import edu.austral.ingsis.gradle.interpreter.MultiplyInterpreter
import edu.austral.ingsis.gradle.interpreter.NumberLiteralInterpreter
import edu.austral.ingsis.gradle.interpreter.PrintLnInterpreter
import edu.austral.ingsis.gradle.interpreter.ReadEnvInterpreter
import edu.austral.ingsis.gradle.interpreter.ReadInputInterpreter
import edu.austral.ingsis.gradle.interpreter.ReassignationInterpreter
import edu.austral.ingsis.gradle.interpreter.StringLiteralInterpreter
import edu.austral.ingsis.gradle.interpreter.SubtractInterpreter
import edu.austral.ingsis.gradle.interpreter.SumInterpreter

fun doesTypeMatch(
    result: Any,
    type: NodeType,
): Boolean {
    return when (type) {
        StringNodeType -> result is String
        NumberNodeType -> result is Number
        BooleanNodeType -> result is Boolean
        else -> false
    }
}

fun castToDesiredType(num: Number): Number {
    if (num is Double) {
        return if (num % 1 == 0.0) {
            num.toInt()
        } else {
            num
        }
    }
    return num
}

fun createInterpreterManager(): InterpreterManager {
    val interpreters =
        listOf(
            BlockNodeInterpreter(),
            DeclarationInterpreter(),
            StringLiteralInterpreter(),
            NumberLiteralInterpreter(),
            BooleanLiteralInterpreter(),
            SumInterpreter(),
            SubtractInterpreter(),
            MultiplyInterpreter(),
            DivideInterpreter(),
            DeclarationInterpreter(),
            DeclarationAssignationInterpreter(),
            IdentifierInterpreter(),
            IfElseStatementInterpreter(),
            IfStatementInterpreter(),
            PrintLnInterpreter(),
            ReadEnvInterpreter(StringNodeType),
            ReadEnvInterpreter(NumberNodeType),
            ReadEnvInterpreter(BooleanNodeType),
            ReadInputInterpreter(StringNodeType),
            ReadInputInterpreter(NumberNodeType),
            ReadInputInterpreter(BooleanNodeType),
            ReassignationInterpreter(),
        )

    val printer = KotlinPrinter()
    val reader = KotlinEnvReader()
    val inputReader = KotlinInputReader()

    return InterpreterManager(interpreters, printer, reader, inputReader)
}

fun createInterpreterManagerTest(): InterpreterManager {
    val interpreters =
        listOf(
            BlockNodeInterpreter(),
            DeclarationInterpreter(),
            StringLiteralInterpreter(),
            NumberLiteralInterpreter(),
            BooleanLiteralInterpreter(),
            SumInterpreter(),
            SubtractInterpreter(),
            MultiplyInterpreter(),
            DivideInterpreter(),
            DeclarationInterpreter(),
            DeclarationAssignationInterpreter(),
            IdentifierInterpreter(),
            IfElseStatementInterpreter(),
            IfStatementInterpreter(),
            PrintLnInterpreter(),
            ReadEnvInterpreter(StringNodeType),
            ReadEnvInterpreter(NumberNodeType),
            ReadEnvInterpreter(BooleanNodeType),
            ReadInputInterpreter(StringNodeType),
            ReadInputInterpreter(NumberNodeType),
            ReadInputInterpreter(BooleanNodeType),
            ReassignationInterpreter(),
        )

    val printer = KotlinPrinter()
    val reader = KotlinEnvReader()
    val inputReader = MockInputReader()

    return InterpreterManager(interpreters, printer, reader, inputReader)
}
