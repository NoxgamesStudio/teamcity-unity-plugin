package jetbrains.buildServer.unity.logging

class IL2CppProgressBlock : LogBlock {
    override val name = "IL2Cpp progress logs ..."

    override val logFirstLine = LogType.Inside

    override val logLastLine = LogType.Outside

    override fun isBlockStart(text: String) = blockItem.containsMatchIn(text)

    override fun isBlockEnd(text: String) = !(blockItem.containsMatchIn(text) ||
            text.contains(blockItem2) ||
            text.contains(blockItem3))

    override fun getText(text: String) = text

    companion object {
        private val blockItem = Regex("\\[\\s*\\d+\\/\\d+\\s*\\d+s\\]")
        private const val blockItem2 = "- Starting ILPostProcessor"
        private const val blockItem3 = "- Finished ILPostProcessor"
    }
}
