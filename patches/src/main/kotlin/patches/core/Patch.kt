package patches.core

abstract class Patch {

    abstract val id: String
    abstract val name: String
    abstract val description: String

    abstract fun execute(context: PatchContext)
}
