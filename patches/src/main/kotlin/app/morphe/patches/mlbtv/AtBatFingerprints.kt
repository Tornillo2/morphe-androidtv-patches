/*
 * MLB At Bat Android TV — Ad Patch Fingerprints
 *
 * Validated against:
 *   v26.8.1  (versionCode 1750000022) — com.bamnetworks.mobile.android.gameday
 *
 * PATCH COVERAGE:
 *
 *   Patch 1a/1b — VOD SSAI & Gambling Ads (createVodStreamRequest)
 *     Identical IMA SDK v3 as Paramount+ v16.8.0.
 *     Empty zzcx → IMA SDK throws → fallback to pre-cached CDN URL.
 *
 *   Patch 3 — Between-Innings Ad Break (PublicaBidListener.onAdBreakStarted)
 *     NOTE: onAdBreakStarted is a Kotlin SUSPEND FUNCTION.
 *     returnType = "Ljava/lang/Object;" (not "V").
 *     Confirmed via lambda class: PublicaBidListener$onAdBreakStarted$1
 *     return-void is NOT valid for suspend functions — see patch notes.
 *
 *   Patch 4 — CreateMediaItemWithAdsUseCase (DAI/IMA stream initialization)
 *     This class sits between MlbPlayerComponent.startPlayback and the
 *     IMA SDK. It decides whether to use "DAI API" or "IMA SDK" path.
 *     Log strings confirmed in classes6.dex:
 *       "[CreateMediaItemWithAdsUseCase] Playing stream with DAI API"
 *       "[CreateMediaItemWithAdsUseCase] Playing stream with IMA SDK"
 *     Targeting the invoke/execute method here blocks ALL ad stream
 *     initialization upstream of both VOD and between-innings ads.
 *     This is also a suspend function — returnType = "Ljava/lang/Object;"
 *
 * SUSPEND FUNCTION NOTE:
 *   Kotlin suspend functions compile to methods with signature:
 *     fun methodName(params..., continuation: Continuation<T>): Object
 *   You CANNOT return-void. Instead inject:
 *     const/4 v0, 0x0
 *     return-object v0
 *   Or better: return the COROUTINES_SUSPENDED sentinel to signal suspension
 *   without executing the method body. But the safest approach for ad
 *   suppression is to target the non-suspend wrapper or use a different hook.
 */

package app.morphe.patches.mlbtv

import app.morphe.patcher.Fingerprint

// ---------------------------------------------------------------------------
// Patch 1a: VOD SSAI & Gambling Ads — createVodStreamRequest (3-arg)
// ---------------------------------------------------------------------------

internal object VodStreamRequest3ArgFingerprint : Fingerprint(
    returnType = "Lcom/google/ads/interactivemedia/v3/api/StreamRequest;",
    custom = { method, _ ->
        method.name == "createVodStreamRequest" &&
            method.definingClass ==
                "Lcom/google/ads/interactivemedia/v3/api/ImaSdkFactory;" &&
            method.parameterTypes.size == 3 &&
            method.parameterTypes.all { it == "Ljava/lang/String;" }
    },
)

// ---------------------------------------------------------------------------
// Patch 1b: VOD SSAI & Gambling Ads — createVodStreamRequest (4-arg)
// ---------------------------------------------------------------------------

internal object VodStreamRequest4ArgFingerprint : Fingerprint(
    returnType = "Lcom/google/ads/interactivemedia/v3/api/StreamRequest;",
    custom = { method, _ ->
        method.name == "createVodStreamRequest" &&
            method.definingClass ==
                "Lcom/google/ads/interactivemedia/v3/api/ImaSdkFactory;" &&
            method.parameterTypes.size == 4 &&
            method.parameterTypes.all { it == "Ljava/lang/String;" }
    },
)

// ---------------------------------------------------------------------------
// Patch 3: Between-Innings Ad Break — PublicaBidListener.onAdBreakStarted
//
// onAdBreakStarted is a Kotlin suspend function — returnType is Object.
// The lambda class PublicaBidListener$onAdBreakStarted$1 confirms this.
// We cannot use return-void. Instead we return null (Object) which causes
// the coroutine to complete without executing its body.
//
// Class confirmed unobfuscated in classes6.dex:
//   mlb.atbat.media.player.listener.publica.PublicaBidListener
// ---------------------------------------------------------------------------

internal object PublicaAdBreakStartedFingerprint : Fingerprint(
    returnType = "Ljava/lang/Object;",
    strings = listOf("[MlbMediaPlayer] onAdBreakStarted"),
    custom = { method, _ ->
        method.definingClass.endsWith("PublicaBidListener;") &&
            method.name == "onAdBreakStarted"
    },
)

// ---------------------------------------------------------------------------
// Patch 4: DAI/IMA Stream Initialization — CreateMediaItemWithAdsUseCase
//
// Sits between MlbPlayerComponent.startPlayback and the IMA SDK.
// Controls both "Playing stream with DAI API" and "Playing stream with IMA SDK"
// paths for between-innings ad breaks.
//
// Class confirmed unobfuscated in classes6.dex:
//   mlb.atbat.media.player.ads.CreateMediaItemWithAdsUseCase
//
// Also a suspend function — return null (Object) to suppress.
// ---------------------------------------------------------------------------

internal object CreateMediaItemWithAdsFingerprint : Fingerprint(
    returnType = "Ljava/lang/Object;",
    strings = listOf(
        "[CreateMediaItemWithAdsUseCase] Playing stream with DAI API",
        "[CreateMediaItemWithAdsUseCase] Playing stream with IMA SDK",
    ),
    custom = { method, _ ->
        method.definingClass.endsWith("CreateMediaItemWithAdsUseCase;")
    },
)
