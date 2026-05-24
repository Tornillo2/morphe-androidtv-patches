package app.morphe.patches.paramount

import app.morphe.patcher.Fingerprint

internal object ImaSdkFactoryFingerprint : Fingerprint(
    custom = { method, _ ->
        method.definingClass == "Lcom/google/ads/interactivemedia/v3/api/ImaSdkFactory;" &&
            method.name == "zzb"
    },
)
