package jetbrains.buildServer.unity.logging

class UnityAnotherStackTraceFromCoroutineBlock : LogBlock {

    override val name = "..."

    override val logFirstLine = LogType.Inside

    override val logLastLine = LogType.Inside

    override fun isBlockStart(text: String) = blockStart.containsMatchIn(text)

    override fun isBlockEnd(text: String) = !blockEnd.containsMatchIn(text)

    override fun getText(text: String) = text

    companion object {
        private val blockStart = Regex(".*#0 GetStacktrace\\(int\\).*")
        private val blockEnd = Regex(".*#\\d+.*")
    }
}