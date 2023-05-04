package jetbrains.buildServer.unity.logging

class UnityStackTraceBlock : LogBlock {

    override val name = "..."

    override val logFirstLine = LogType.Inside

    override val logLastLine = LogType.Inside

    override fun isBlockStart(text: String) = blockStart.containsMatchIn(text)

    override fun isBlockEnd(text: String) = blockEnd.containsMatchIn(text) || blockEnd2.containsMatchIn(text)

    override fun getText(text: String) =
        if (filterOut.containsMatchIn(text)) {
            ""
        } else {
            if (isBlockEnd(text))
                text + "\n"
            else
                text
        }

    companion object {
        private val blockStart = Regex("UnityEngine.StackTraceUtility:ExtractStackTrace.*$")
        private val blockEnd = Regex("(^\\(Filename:.*)|(\\[.* line \\d+\\])")
        private val blockEnd2 = Regex("UnityEditor.EditorAssemblies:ProcessInitializeOnLoadAttributes.*")
        private val filterOut = Regex("^(UnityEngine.Debug|UnityEngine.Logger|UnityEngine.StackTraceUtility).*$")
    }
}