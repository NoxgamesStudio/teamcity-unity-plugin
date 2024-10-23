package jetbrains.buildServer.unity.logging

class StartImportingBlock : LogBlock {
    override val name = "Start importing ..."

    override val logFirstLine = LogType.Inside

    override val logLastLine = LogType.Outside

    override fun isBlockStart(text: String) = blockItem.containsMatchIn(text)

    override fun isBlockEnd(text: String) = !blockItem.containsMatchIn(text) && !blockItem2.containsMatchIn(text) && !blockItem3.containsMatchIn(text)

    override fun getText(text: String) = text

    companion object {
        private val blockItem = Regex("^\\s*Start importing .*")
        private val blockItem2 = Regex("^\\s*\\[PhysX] Initialized MultithreadedTaskDispatcher.*")
        private val blockItem3 = Regex("^\\s*-> \\(artifact id:.*")
    }
}

