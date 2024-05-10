package edu.austral.ingsis.gradle.sca.factory

import edu.austral.ingsis.gradle.sca.analyzer.BlockNodeAnalyzer
import edu.austral.ingsis.gradle.sca.analyzer.ComposeAnalyzer
import edu.austral.ingsis.gradle.sca.analyzer.DeclarationAssignationNodeAnalyzer
import edu.austral.ingsis.gradle.sca.analyzer.DeclarationNodeAnalyzer
import edu.austral.ingsis.gradle.sca.analyzer.DivideNodeAnalyzer
import edu.austral.ingsis.gradle.sca.analyzer.IdentifierNodeAnalyzer
import edu.austral.ingsis.gradle.sca.analyzer.IfElseStatementAnalyzer
import edu.austral.ingsis.gradle.sca.analyzer.IfStatementAnalyzer
import edu.austral.ingsis.gradle.sca.analyzer.MultiplyNodeAnalyzer
import edu.austral.ingsis.gradle.sca.analyzer.PrintLnNodeAnalyzer
import edu.austral.ingsis.gradle.sca.analyzer.ProgramNodeAnalyzer
import edu.austral.ingsis.gradle.sca.analyzer.ReAssignationNodeAnalyzer
import edu.austral.ingsis.gradle.sca.analyzer.ReadInputNodeAnalyzer
import edu.austral.ingsis.gradle.sca.analyzer.SubtractNodeAnalyzer
import edu.austral.ingsis.gradle.sca.analyzer.SumNodeAnalyzer

fun createComposeAnalyzer(): ComposeAnalyzer {
    return ComposeAnalyzer(
        listOf(
            ProgramNodeAnalyzer(),
            IdentifierNodeAnalyzer(),
            PrintLnNodeAnalyzer(),
            ReadInputNodeAnalyzer(),
            BlockNodeAnalyzer(),
            IfElseStatementAnalyzer(),
            IfStatementAnalyzer(),
            SumNodeAnalyzer(),
            SubtractNodeAnalyzer(),
            DivideNodeAnalyzer(),
            MultiplyNodeAnalyzer(),
            ReAssignationNodeAnalyzer(),
            DeclarationAssignationNodeAnalyzer(),
            DeclarationNodeAnalyzer(),
        ),
    )
}
