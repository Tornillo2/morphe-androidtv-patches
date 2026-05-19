package app.morphe.patches.shared.compat

import app.morphe.patcher.patch.Compatibility

object AppCompatibilities {

    val PARAMOUNT_TV = Compatibility(
        name = "Paramount+ (Android TV)",
        packageName = "com.cbs.ott",
        appIconColor = 0x0064FF,
    )

    val DISNEY_PLUS_TV = Compatibility(
        name = "Disney+ (Android TV)",
        packageName = "com.disney.disneyplus",
        appIconColor = 0x113CCF,
    )
}
