package patches.androidtv.disney

/*
 * Credit:
 * Original work by RookieEnough
 *
 * Forked from:
 * https://gitlab.com/ReVanced/revanced-patches/-/blob/main/patches/src/main/kotlin/app/revanced/patches/disneyplus/ads/Fingerprints.kt
 *
 * Modified for use in morphe-androidtv-patches
 */

import patches.core.ParameterizedFingerprint

object InsertionGetPointsFingerprint : ParameterizedFingerprint(
    "getPoints"
)

object InsertionGetRangesFingerprint : ParameterizedFingerprint(
    "getRanges"
)
