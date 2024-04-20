package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.formatter.rule.Rule

interface Formatter<AST> {
    /**
     * Convert an AST into a string.
     * @param node AST node to be converted.
     * @param defaultRule List of formatting rules to be applied.
     * @param blockRule List of formatting rules to be applied to if blocks.
     * @return String representation of the AST node.
     */
    fun format(
        node: AST,
        defaultRule: Rule,
        blockRule: Rule,
    ): String

    /**
     * Applies formatting rules to AST already converted into string.
     * @param result String representation of the AST node.
     * @param rule List of formatting rules to be applied.
     * @return String representation of the AST node with formatting rules applied.
     */
    fun applyFormat(
        result: String,
        rule: Rule,
    ): String {
        return rule.applyRule(result)
    }

    /**
     * Checks if the formatter can format a specific AST node.
     * @param node AST node to be checked.
     * @return True if the formatter can format the node, false otherwise.
     */
    fun canFormat(node: AST): Boolean
}
