package ajstrick81.morphe.patches.vix.misc.security

import app.morphe.patcher.patch.resourcePatch
import ajstrick81.morphe.patches.vix.shared.Constants

// ─────────────────────────────────────────────────────────────────────────────
// Certificate Pinning Override (ViX Android TV)
//
// Companion to the AdGuard/AGP SSAI ruleset. ViX live ads are Lura server-side
// stitched (SSAI) and decisioned through client-built Google GAM calls — they
// cannot be removed by a bytecode hook, only at the network layer. But apps
// targeting API 24+ only trust SYSTEM CAs by default, so an HTTPS-inspection
// proxy's user-installed CA is rejected and the proxy never sees ViX traffic.
// This patch makes ViX trust user CAs so AdGuard (or mitmproxy) can intercept
// and apply manifest/segment/GAM rules.
//
// What this creates / changes:
//   res/xml/network_security_config.xml:
//     - base-config trust-anchors = system + user (enables HTTPS inspection)
//     - no <pin-set> entries (any manifest-level pins are dropped)
//   AndroidManifest.xml:
//     - sets/replaces android:networkSecurityConfig on <application> to point
//       at the config above. setNamedItem replaces an existing attribute, so
//       this also overrides whatever NSC ViX already ships.
//
// ViX-specific notes:
//   - Unlike Prime Video, ViX DOES reference xml resources (a network security
//     config is likely already present). Overwriting the standard-named file
//     and repointing the manifest attribute makes our config authoritative in
//     both cases (shipped-or-not), and any orphaned original file is harmless.
//   - The only in-code TLS pin found in the dex belongs to PerimeterX
//     (com/perimeterx/mobile_sdk, sha256/V5L96...), an OkHttp CertificatePinner
//     that network_security_config does NOT override. That is intentional and
//     fine: PerimeterX is bot-defense for login, NOT the video/ad path, and the
//     SSAI ruleset deliberately avoids MITM-ing it (do not intercept PX, or you
//     risk lockouts). The video/ad hosts (*.mp.lura.live, GAM/IMA) are NOT
//     pinned in code, so user-CA trust is sufficient for them.
//   - cleartextTrafficPermitted stays false — AdGuard inspection is still TLS.
//
// This patch is optional and independent of the Skip ads patch. It only has an
// effect when an HTTPS-inspection proxy with its CA installed is in the path.
// ─────────────────────────────────────────────────────────────────────────────

@Suppress("unused")
val certificatePinningPatch = resourcePatch(
    name = "Override certificate pinning",
    description = "Allows HTTPS traffic inspection tools like AdGuard Premium to intercept app connections. Enables ViX live (Lura SSAI) ad suppression when used alongside AdGuard Premium.",
) {
    compatibleWith(Constants.COMPATIBILITY)

    execute {

        // ─────────────────────────────────────────────────────────────────────
        // Step 1 — Write a permissive network_security_config.xml
        //
        // Trusts user-installed CAs and declares no pin-sets. Overwrites the
        // file if ViX already ships one under this name.
        // ─────────────────────────────────────────────────────────────────────
        get("res/xml/network_security_config.xml").writeText(
            """<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <base-config cleartextTrafficPermitted="false">
        <trust-anchors>
            <!-- Trust system CA certificates (default) -->
            <certificates src="system" />
            <!-- Trust user-installed CA certificates (required for AGP HTTPS inspection) -->
            <certificates src="user" />
        </trust-anchors>
    </base-config>
</network-security-config>"""
        )

        // ─────────────────────────────────────────────────────────────────────
        // Step 2 — Point the <application> tag at our config
        //
        // setNamedItem replaces an existing android:networkSecurityConfig
        // attribute, so this overrides any NSC ViX already references (and adds
        // the attribute if none was present).
        // ─────────────────────────────────────────────────────────────────────
        document("AndroidManifest.xml").use { document ->
            val applicationNode = document
                .getElementsByTagName("application")
                .item(0)

            applicationNode
                .attributes
                .setNamedItem(
                    document.createAttribute("android:networkSecurityConfig").also {
                        it.value = "@xml/network_security_config"
                    }
                )
        }
    }
}
