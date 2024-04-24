package jetbrains.buildServer.unity.logging

class IL2CppProgressBlock : LogBlock {
    override val name = "IL2Cpp progress logs ..."

    override val logFirstLine = LogType.Inside

    override val logLastLine = LogType.Outside

    override fun isBlockStart(text: String) = blockItem.containsMatchIn(text)

    override fun isBlockEnd(text: String) = !(
            blockItem.containsMatchIn(text) ||
                    text == "\n" ||
                    text.contains(blockItem2) ||
                    text.contains(blockItem3) ||
                    text.contains(blockItem4) ||
                    text.contains(blockItem5) ||
                    text.contains(blockItem6) ||
                    text.contains(blockItem7) ||
                    text.contains(blockItem8))

    override fun getText(text: String) = text

    companion object {
        private val blockItem = Regex("\\[\\s*(\\d+/\\d+)*\\s*\\d+s\\]")
        private const val blockItem2 = "- Starting ILPostProcessor"
        private const val blockItem3 = "- Finished ILPostProcessor"
        private const val blockItem4 = "Processing assembly Library/Bee/artifacts"
        private const val blockItem5 = "processors: Unity.Entities.CodeGen.EntitiesILPostProcessors"
        private const val blockItem6 = "running Unity.Entities.CodeGen.EntitiesILPostProcessors"
        private const val blockItem7 = "running Unity.Jobs.CodeGen.JobsILPostProcessor"
        private const val blockItem8 = "running zzzUnity.Burst.CodeGen.BurstILPostProcessor"
    }
}
