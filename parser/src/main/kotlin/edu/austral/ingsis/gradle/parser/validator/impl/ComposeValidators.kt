package edu.austral.ingsis.gradle.parser.validator.impl

val declarationValidator = OrValidator(
    listOf(
        AndValidator(
            listOf(
                KeywordValidator(),
                IdentifierValidator(),
                ColonValidator(),
                TypeValidator()
            )
        ),
        IdentifierValidator()
    )
)

val expressionValidator = OrValidator(
    listOf(
        IdentifierValidator(),
        AndValidator(
            listOf(
                OrValidator(
                    listOf(
                        LiteralValidator(),
                        IdentifierValidator()
                    )
                ),
                OperatorValidator()
            )
        ),
    )
)

val statementValidator = AndValidator(
    listOf(
        declarationValidator,
        EqualsValidator(),
        expressionValidator
    )
)