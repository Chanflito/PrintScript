package edu.austral.ingsis.gradle.sca

interface Rule<T> {
    /**
     *Should implement the rule for specific node type
     * @param node the node to verify
     * @return report result which should be either ReportSuccess or ReportFailure
     **/
    fun verify(node: T): ReportResult
}

sealed interface ReportResult

data object ReportSuccess : ReportResult

data class ReportFailure(val failureMessages: List<String>) : ReportResult {
    override fun toString(): String {
        val formattedMessages = failureMessages.joinToString(separator = "\n")
        return "$formattedMessages\n"
    }
}
