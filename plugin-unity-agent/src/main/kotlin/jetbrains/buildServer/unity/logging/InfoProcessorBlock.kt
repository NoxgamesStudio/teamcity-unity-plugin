package jetbrains.buildServer.unity.logging

class InfoProcessorBlock : LogBlock {

    override var name = "info (processors)"

    override val logFirstLine = LogType.Inside

    override val logLastLine = LogType.Inside

    override fun isBlockStart(text: String) = blockStart1.containsMatchIn(text) || blockStart2.containsMatchIn(text)

    override fun isBlockEnd(text: String) =
        !blockEnd1.containsMatchIn(text) && !blockEnd2.containsMatchIn(text) && !blockEnd3.containsMatchIn(text)

    override fun getText(text: String) = text

    companion object {
        private val blockStart1 = Regex("\\[40m.*(info |warn ).*: Unity.ILPP.Runner.*")
        private val blockStart2 = Regex("\\[40m.*(info |warn ).*: Microsoft.*")
        private val blockEnd1 = Regex("^\\s*Executed endpoint.*")
        private val blockEnd2 = Regex("^\\s*Request (finished|starting).*")
        private val blockEnd3 = Regex("^\\s*(running|processors:|Processing assembly) .*")
    }
}
