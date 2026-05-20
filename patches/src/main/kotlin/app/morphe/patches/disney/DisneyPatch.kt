/*
 * Credit:
 * Original work by RookieEnough aka The G.O.A.T :)
 *
 * Forked from:
 * https://gitlab.com/ReVanced/revanced-patches/-/blob/main/patches/src/main/kotlin/app/revanced/patches/disneyplus/ads/Fingerprints.kt
 *
 * Modified for use in morphe-androidtv-patches
 */

package app.morphe.patches.disney

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patches.shared.compat.AppCompatibilities

@Suppress("unused")
val disneyPatch = bytecodePatch(
    name = "Disney+ Android TV",
    description = "Automatically skips ads.",
) {
    compatibleWith(AppCompatibilities.DISNEY_PLUS_TV)

    execute {
        arrayOf(InsertionGetPointsFingerprint, InsertionGetRangesFingerprint).forEach {
            it.method.addInstructions(
                0,
                """
                    new-instance v0, Ljava/util/ArrayList;
                    invoke-direct { v0 }, Ljava/util/ArrayList;-><init>()V
                    return-object v0
                """.trimIndent(),
            )
        }
    }
}
