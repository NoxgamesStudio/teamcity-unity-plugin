package jetbrains.buildServer.unity.logging

class CompilingShaderBlock : LogBlock {

    override var name = "Compiling shader"

    override val logFirstLine = LogType.None

    override val logLastLine = LogType.None

    override fun isBlockStart(text: String) = if (blockStart.containsMatchIn(text)) {
        name = text; true
    } else {
        false
    }

    override fun isBlockEnd(text: String) = blockEnd.containsMatchIn(text)

    override fun getText(text: String) = text

    companion object {
        private val blockStart = Regex("Compiling shader .*")
        private val blockEnd = Regex("finished in.*seconds. Local cache hits.*")
    }
}