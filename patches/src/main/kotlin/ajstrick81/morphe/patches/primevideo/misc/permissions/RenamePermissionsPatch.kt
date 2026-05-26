package ajstrick81.morphe.patches.primevideo.misc.permissions

import app.morphe.patcher.patch.PatchException
import app.morphe.patcher.patch.resourcePatch
import ajstrick81.morphe.patches.primevideo.shared.Constants
import org.w3c.dom.Element

// Note: app.morphe.util.asSequence and app.morphe.util.getNode are not
// available in Morphe 1.3.0 (they are part of morphe-patches-library which
// is not a dependency in this project). This patch uses standard Java DOM
// APIs instead, which are always available in the patcher environment.

@Suppress("unused")
val renamePermissionsPatch = resourcePatch(
    name = "Rename shared permissions",
    description = "Rename certain permissions shared across Amazon apps. " +
            "Applying this patch can fix installation errors, but can also break features in certain apps."
) {
    compatibleWith(Constants.COMPATIBILITY)

    // Amazon-wide identity and SSO permissions declared in the ATV manifest.
    // Renaming them prevents install conflicts when multiple Amazon apps
    // define the same signature-level permission on the same device.
    val permissionNames = setOf(
        "com.amazon.identity.permission.CAN_CALL_MAP_INFORMATION_PROVIDER",
        "com.amazon.identity.auth.device.perm.AUTH_SDK",
        "com.amazon.dcp.sso.permission.account.changed",
        "com.amazon.dcp.sso.permission.AmazonAccountPropertyService.property.changed",
        "com.amazon.identity.permission.CALL_AMAZON_DEVICE_INFORMATION_PROVIDER",
        "com.amazon.appmanager.preload.permission.READ_PRELOAD_DEVICE_INFO_PROVIDER"
    )

    execute {
        document("AndroidManifest.xml").use { document ->
            // documentElement is always the root <manifest> element.
            val manifest = document.documentElement as Element
            val permissionNodes = manifest.getElementsByTagName("permission")

            var found = false
            for (i in 0 until permissionNodes.length) {
                val element = permissionNodes.item(i) as Element
                val name = element.getAttribute("android:name")
                if (name in permissionNames) {
                    element.setAttribute("android:name", "revanced.$name")
                    found = true
                }
            }

            if (!found) throw PatchException("Could not find any permissions to rename")
        }
    }
}
