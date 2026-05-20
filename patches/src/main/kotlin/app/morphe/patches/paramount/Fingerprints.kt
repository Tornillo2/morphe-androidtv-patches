package app.morphe.patches.paramount

import app.morphe.patcher.Fingerprint

internal object ImaSdkFactoryFingerprint : Fingerprint(
    returnType = "Lcom/google/ads/interactivemedia/v3/api/AdsLoader",
    custom = { method, _ ->
        method.definingClass == "Lcom/google/ads/interactivemedia/v3/api/ImaSdkFactory;" &&
            method.name == "zzb"
    },
)
