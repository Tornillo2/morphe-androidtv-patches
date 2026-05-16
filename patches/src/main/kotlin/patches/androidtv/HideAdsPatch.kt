package patches.androidtv

import patches.core.Patch
import patches.core.PatchContext

object HideAdsPatch : Patch() {

    override val name = "Hide IMA Ads"
    override val description = "Neutralizes the IMA SDK configuration to skip ad loading."

    override fun execute(context: PatchContext) {

        context.forClass("com.google.ads.interactivemedia.v3.api.ImaSdkFactory") {

            forMethod("zzb") {
                val result = HideAdsFingerprint.find(this)

                if (result.found) {
                    val register = result.group(1)

                    addInstruction(
                        result.startOffset,
                        "const/4 $register, 0x0"
                    )

                    replaceInstruction(
                        result.startOffset + 2,
                        "const/4 $register, 0x0"
                    )
                }
            }
        }
    }
}