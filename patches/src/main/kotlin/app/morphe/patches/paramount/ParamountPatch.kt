package app.morphe.patches.paramount

import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.fingerprint

@Suppress("unused")
val paramountPatch = bytecodePatch(
    name = "Paramount+ Android TV",
    description = "Disables IMA ad loading in Paramount+",
) {
    compatibleWith(AppCompatibilities.PARAMOUNT_TV)

    val imaSdkFactoryFingerprint = fingerprint {
        custom { method, _ ->
            method.definingClass == "Lcom/google/ads/interactivemedia/v3/api/ImaSdkFactory;" &&
                method.name == "zzb"
        }
    }

    execute {
        imaSdkFactoryFingerprint.method.addInstructions(
            0,
            """
                const/4 p2, 0x0
                const/4 p2, 0x0
            """.trimIndent(),
        )
    }
}
