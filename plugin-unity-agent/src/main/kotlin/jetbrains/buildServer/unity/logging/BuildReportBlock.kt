package jetbrains.buildServer.unity.logging

class BuildReportBlock : LogBlock {

    override val name = "Build Report"

    override val logFirstLine = LogType.None

    override val logLastLine = LogType.None

    override fun isBlockStart(text: String) = blockName.containsMatchIn(text) // text.contains(blockName)

    override fun isBlockEnd(text: String) = blockEnd.containsMatchIn(text)

    override fun getText(text: String) = text

    companion object {
        private val blockName = Regex("^\\s*Build Report") // "Build Report"
        private val blockEnd = Regex("-{79}")
    }
}
