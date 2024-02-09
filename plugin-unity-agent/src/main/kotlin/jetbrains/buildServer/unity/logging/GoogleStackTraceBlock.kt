package jetbrains.buildServer.unity.logging

class GoogleStackTraceBlock : LogBlock {

    override var name = "... Google Log"

    override val logFirstLine = LogType.Inside

    override val logLastLine = LogType.Inside

    override fun isBlockStart(text: String) = blockStart.containsMatchIn(text)

    override fun isBlockEnd(text: String) = blockEnd.containsMatchIn(text) ||
            blockEnd2.containsMatchIn(text) ||
            blockEnd3.containsMatchIn(text)

    /*
    override fun getText(text: String) =
            if (isBlockEnd(text)) {
                // if (blockEnd.containsMatchIn(text)) {
                //     name = "... $text"
                // }
                text + "\n"
            } else
                text
    */
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
        private val blockStart = Regex("Google.Logger:Log")

        private val blockEnd = Regex("(^\\s*\\(Filename:.*)|(\\s*\\[.* line \\d+\\])")
        private val blockEnd2 = Regex("UnityEditor.EditorAssemblies:ProcessInitializeOnLoadAttributes")
        private val blockEnd3 = Regex("UnityEditor.EditorApplication:Internal_CallUpdateFunctions")

        private val filterOut = Regex("^(UnityEngine.Debug|UnityEngine.Logger|UnityEngine.StackTraceUtility).*$")
    }
}