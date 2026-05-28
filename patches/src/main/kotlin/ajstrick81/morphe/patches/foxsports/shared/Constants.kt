package ajstrick81.morphe.patches.foxsports.shared

import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility

object Constants {
    val COMPATIBILITY = Compatibility(
        name = "Fox Sports",
        packageName = "com.foxsports.android",
        appIconColor = 0x003767,
        targets = listOf(AppTarget("5.152.0"))
    )
}
