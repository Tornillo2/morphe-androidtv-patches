/*
 * Credit:
 * Original work by RookieEnough
 *
 * Forked from:
 * https://gitlab.com/ReVanced/revanced-patches/-/blob/main/patches/src/main/kotlin/app/revanced/patches/disneyplus/ads/Fingerprints.kt
 *
 * Modified for use in morphe-androidtv-patches
 */

package patches.androidtv.disney

import patches.core.Patch
import patches.core.PatchContext

object DisneyPatch : Patch() {

    override val name = "Disney+ Disable Ads"
    override val description = "Disables ad insertion methods in Disney+"

    override fun execute(context: PatchContext) {

        context.forClass("com.dss.sdk.internal.media.Insertion") {

            forMethod("getPoints") {
                val result = InsertionGetPointsFingerprint.find(this)

                if (result.found) {
                    addInstruction(
                        result.startOffset,
                        "return-object v0"
                    )
                }
            }

            forMethod("getRanges") {
                val result = InsertionGetRangesFingerprint.find(this)

                if (result.found) {
                    addInstruction(
                        result.startOffset,
                        "return-object v0"
                    )
                }
            }
        }
    }
}
