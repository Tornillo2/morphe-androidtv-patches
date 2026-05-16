package patches

import patches.core.PatchContext
import patches.androidtv.HideAdsPatch

fun main() {

    println("Starting patch execution...\n")

    val context = PatchContext()

    println("Running patch: ${HideAdsPatch.name}")
    HideAdsPatch.execute(context)

    println("\nDone.")
}
