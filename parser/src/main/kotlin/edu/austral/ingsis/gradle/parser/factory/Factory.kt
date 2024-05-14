package edu.austral.ingsis.gradle.parser.factory

import edu.austral.ingsis.gradle.common.token.BooleanValue
import edu.austral.ingsis.gradle.common.token.ConstKeyword
import edu.austral.ingsis.gradle.common.token.Identifier
import edu.austral.ingsis.gradle.common.token.IfKeyword
import edu.austral.ingsis.gradle.common.token.LeftParenthesis
import edu.austral.ingsis.gradle.common.token.LetKeyword
import edu.austral.ingsis.gradle.common.token.NumberValue
import edu.austral.ingsis.gradle.common.token.PrintlnKeyword
import edu.austral.ingsis.gradle.common.token.ReadEnvKeyword
import edu.austral.ingsis.gradle.common.token.ReadInputKeyword
import edu.austral.ingsis.gradle.common.token.StringValue
import edu.austral.ingsis.gradle.parser.impl.AssignationParser
import edu.austral.ingsis.gradle.parser.impl.BooleanValueParser
import edu.austral.ingsis.gradle.parser.impl.ComposeParser
import edu.austral.ingsis.gradle.parser.impl.DeclarationParser
import edu.austral.ingsis.gradle.parser.impl.ExpressionParser
import edu.austral.ingsis.gradle.parser.impl.IdentifierParser
import edu.austral.ingsis.gradle.parser.impl.IfParser
import edu.austral.ingsis.gradle.parser.impl.LeftParenthesisParser
import edu.austral.ingsis.gradle.parser.impl.PrintlnParser
import edu.austral.ingsis.gradle.parser.impl.ReadEnvParser
import edu.austral.ingsis.gradle.parser.impl.ReadInputParser
import edu.austral.ingsis.gradle.parser.impl.ValueNumberParser
import edu.austral.ingsis.gradle.parser.impl.ValueStringParser

val EXPRESSION_PARSER_MAP =
    mapOf(
        NumberValue to ValueNumberParser(),
        StringValue to ValueStringParser(),
        BooleanValue to BooleanValueParser(),
        LeftParenthesis to LeftParenthesisParser(),
        Identifier to IdentifierParser(),
        ReadEnvKeyword to ReadEnvParser(),
        ReadInputKeyword to ReadInputParser(),
    )

fun createComposeParser(): ComposeParser {
    return ComposeParser(
        mapOf(
            PrintlnKeyword to PrintlnParser(),
            ReadInputKeyword to ReadInputParser(),
            ReadEnvKeyword to ReadEnvParser(),
            IfKeyword to IfParser(),
            StringValue to ExpressionParser(EXPRESSION_PARSER_MAP),
            NumberValue to ExpressionParser(EXPRESSION_PARSER_MAP),
            LeftParenthesis to ExpressionParser(EXPRESSION_PARSER_MAP),
            LetKeyword to DeclarationParser(),
            ConstKeyword to DeclarationParser(),
            Identifier to AssignationParser(),
        ),
    )
}
