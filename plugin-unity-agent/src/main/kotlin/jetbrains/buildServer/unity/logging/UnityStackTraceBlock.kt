package jetbrains.buildServer.unity.logging

class UnityStackTraceBlock : LogBlock {

    override var name = "..."

    override val logFirstLine = LogType.Inside

    override val logLastLine = LogType.Inside

    override fun isBlockStart(text: String) = blockStart.containsMatchIn(text) ||
            blockStart2.containsMatchIn(text)

    override fun isBlockEnd(text: String) = blockEnd.containsMatchIn(text) ||
            // blockEnd2.containsMatchIn(text) ||
            // blockEnd3.containsMatchIn(text) ||
            blockEnd4.containsMatchIn(text) ||
            blockEnd5.containsMatchIn(text) ||
            blockEnd6.containsMatchIn(text) ||
            blockEnd7.containsMatchIn(text)

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
                // if (blockEnd.containsMatchIn(text))
                //     name = "... $text"
                if (isBlockEnd(text))
                    text + "\n"
                else
                    text
            }

    companion object {
        private val blockStart = Regex("UnityEngine.StackTraceUtility:ExtractStackTrace.*$")
        private val blockStart2 = Regex("UnityEditor.BuildPipeline:BuildPlayerInternalNoCheck.*$")
        // private val blockStart3 = Regex("Google.Logger:Log.*$") moved to GoogleStackTraceBlock.kt

        private val blockEnd = Regex("(^\\s*\\(Filename:.*)|(\\s*\\[.* line \\d+\\])")

        // private val blockEnd2 = Regex("UnityEditor.EditorAssemblies:ProcessInitializeOnLoadAttributes.*") moved to GoogleStackTraceBlock.kt
        // private val blockEnd3 = Regex("UnityEditor.EditorApplication:Internal_CallUpdateFunctions.*")
        private val blockEnd4 =
                Regex("UnityEditor.Scripting.ScriptCompilation.EditorCompilationInterface:(TickCompilationPipeline|CompileScripts).*")
        private val blockEnd5 = Regex("UnityEditorInternal.APIUpdating.APIUpdaterManager:ProcessImportedAssemblies.*")
        private val blockEnd6 = Regex("UnityEditor.Modules.ModuleManager:InitializePlatformSupportModules.*")
        private val blockEnd7 = Regex("UnityEditor.EditorApplication:Internal_CallDelayFunctions.*")
        private val filterOut = Regex("^(UnityEngine.Debug|UnityEngine.Logger|UnityEngine.StackTraceUtility).*$")
    }
}