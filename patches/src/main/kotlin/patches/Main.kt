package patches

import patches.core.PatchContext
import patches.core.PatchLoader

fun main() {

    println("Starting patch execution...\n")

    val context = PatchContext()

    val patches = PatchLoader.loadPatches()

    println("Loaded ${patches.size} patches from JSON\n")

    patches.forEach { patch ->
        println("Running patch: ${patch.name} (${patch.id})")
        patch.execute(context)
        println()
    }

    println("Done.")
}
