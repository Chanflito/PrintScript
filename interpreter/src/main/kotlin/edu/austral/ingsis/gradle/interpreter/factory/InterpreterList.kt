package edu.austral.ingsis.gradle.interpreter.factory

import edu.austral.ingsis.gradle.common.ast.newast.BooleanNodeType
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
import edu.austral.ingsis.gradle.interpreter.Interpreter
import edu.austral.ingsis.gradle.interpreter.MultiplyInterpreter
import edu.austral.ingsis.gradle.interpreter.NumberLiteralInterpreter
import edu.austral.ingsis.gradle.interpreter.PrintLnInterpreter
import edu.austral.ingsis.gradle.interpreter.ReadEnvInterpreter
import edu.austral.ingsis.gradle.interpreter.ReadInputInterpreter
import edu.austral.ingsis.gradle.interpreter.ReassignationInterpreter
import edu.austral.ingsis.gradle.interpreter.StringLiteralInterpreter
import edu.austral.ingsis.gradle.interpreter.SubtractInterpreter
import edu.austral.ingsis.gradle.interpreter.SumInterpreter

class InterpreterList {
    fun getInterpreters(): List<Interpreter> {
        return listOf(
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
    }
}
