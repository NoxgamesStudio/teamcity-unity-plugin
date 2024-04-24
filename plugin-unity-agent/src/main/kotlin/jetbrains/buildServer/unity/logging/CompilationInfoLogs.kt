package jetbrains.buildServer.unity.logging

class CompilationInfoLogs : LogBlock {
    override val name = "info: ..."

    override val logFirstLine = LogType.Inside

    override val logLastLine = LogType.Outside

    override fun isBlockStart(text: String) = blockItem.containsMatchIn(text)

    override fun isBlockEnd(text: String) = !(
            blockItem.containsMatchIn(text) ||
                    text.contains(blockItem2) ||
                    text.contains(blockItem3) ||
                    text.contains(blockItem4) ||
                    text.contains(blockItem5) ||
                    text.contains(blockItem6) ||
                    text.contains(blockItem7) ||
                    text.contains(blockItem8) ||
                    text.contains(blockItem9) ||
                    text.contains(blockItem10) ||
                    text.contains(blockItem11))

    override fun getText(text: String) = text

    companion object {
        private val blockItem = Regex("^info: .*")
        private const val blockItem2 = "      Request starting"
        private const val blockItem3 = "      Request finished "
        private const val blockItem4 = "      Executing endpoint "
        private const val blockItem5 = "      Executed endpoint "
        private const val blockItem6 = "      ALC ILPP context"
        private const val blockItem7 = "      Resolved "
        private const val blockItem8 = "      processors: "
        private const val blockItem9 = "      Processing assembly "
        private const val blockItem10 = "      running "
        private const val blockItem11 = "- EILPP : "
    }
}
