package app.morphe.patches.shared.compat

import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility

object AppCompatibilities {

    val PARAMOUNT_TV = Compatibility(
        name = "Paramount+",
        packageName = "com.cbs.ott",
        appIconColor = 0x0064FF,
        targets = listOf(AppTarget("16.8.0")),
    )

    val DISNEY_PLUS_TV = Compatibility(
        name = "Disney+",
        packageName = "com.disney.disneyplus",
        appIconColor = 0x113CCF,
    )
}
