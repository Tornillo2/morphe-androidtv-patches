package app.morphe.patches.shared.compat

import app.morphe.patcher.patch.Compatibility

object AppCompatibilities {

    // Peacock TV — Android TV (Leanback)
    // ⚠️ Recommended version: 6.11.212 ONLY
    // See README for APKMirror download instructions.
    // v7.x raised Min SDK to 32 and added anti-tamper library — do not use.
    val PEACOCK_TV = Compatibility(
        name = "Peacock (Android TV)",
        packageName = "com.peacocktv.peacockandroid",
        appIconColor = 0x000000,
    )

    val PEACOCK_TV_ANDROID_TV = PEACOCK_TV
}
