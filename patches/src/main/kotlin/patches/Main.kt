package patches

import patches.core.PatchContext
import patches.androidtv.paramount.ParamountPatch
import patches.androidtv.disney.DisneyPatch

fun main() {

    println("Starting patch execution...\n")

    val context = PatchContext()

    // Run Paramount+ patch
    println("Running patch: ${ParamountPatch.name}")
    ParamountPatch.execute(context)
    println()

    // Run Disney+ patch
    println("Running patch: ${DisneyPatch.name}")
    DisneyPatch.execute(context)
    println()

    println("Done.")
}
