package ajstrick81.morphe.patches.peacock.ads

import app.morphe.patcher.Fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

// ── Layer 1 ──────────────────────────────────────────────────────────────────
// Target: SSAIConfiguration$MediaTailor$AutomaticMediaTailor.getProxyHost()
// Returns the MediaTailor SSAI proxy URL. Returning "" disables SSAI.
// Confirmed present in v7.5.102.
internal object MediaTailorProxyHostFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC),
    returnType = "Ljava/lang/String;",
    custom = { method, classDef ->
        method.name == "getProxyHost" &&
            classDef.type.contains("AutomaticMediaTailor")
    },
)

// ── Layer 2 ──────────────────────────────────────────────────────────────────
// Target: ObfuscatedProfileId.values()
// Returns all 9 registered ad/analytics SDK profiles. Returning an empty
// array prevents Adobe, Comscore, Conviva, Freewheel, MParticle,
// MParticleSessionId, MediaTailor, Nielsen, and OpenMeasurement from loading.
// Confirmed present in v7.5.102.
internal object ObfuscatedProfileIdValuesFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.STATIC),
    returnType = "[Lcom/sky/core/player/addon/common/data/ObfuscatedProfileId;",
    custom = { method, classDef ->
        method.name == "values" &&
            classDef.type == "Lcom/sky/core/player/addon/common/data/ObfuscatedProfileId;"
    },
)

// ── Layer 3 ──────────────────────────────────────────────────────────────────
// Target: MediaTailorAdvertServiceFactoryImpl — method containing unique
// error string "Could not build MT Advertising service".
// Returning null aborts service construction.
// String confirmed present in v7.5.102 DEX 2.
internal object MediaTailorAdServiceMethodFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC),
    returnType = "Ljava/lang/Object;",
    strings = listOf("Could not build MT Advertising service"),
)

// ── Layer 4 ──────────────────────────────────────────────────────────────────
// Target: Configuration.getSsaiConfigurationProvider()
// Returning null forces Configuration$getDefaultAdvertisingStrategyProvider$1
// .strategyForType() to return AdvertisingStrategy.None for all playback
// types via confirmed if-eqz branch. AutomaticSSAI becomes unreachable.
// Named method on stable Sky SDK class — version-resilient.
internal object SsaiConfigurationProviderFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "Lcom/sky/core/player/sdk/addon/SSAIConfigurationProvider;",
    custom = { method, classDef ->
        method.name == "getSsaiConfigurationProvider" &&
            classDef.type == "Lcom/sky/core/player/sdk/data/Configuration;"
    },
)

// ── Layer 5 ──────────────────────────────────────────────────────────────────
// Target: PlayerEngineItemImpl.handleAdBreakStarted(AdBreakStartedEvent)
// The Sky SDK player engine ad break entry point. Returning void immediately
// prevents the player from ever entering an ad break at the playback level.
// Confirmed present in v7.5.102 DEX 2 via androguard analysis.
internal object HandleAdBreakStartedFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC),
    returnType = "V",
    custom = { method, classDef ->
        method.name == "handleAdBreakStarted" &&
            classDef.type ==
                "Lcom/sky/core/player/sdk/playerEngine/playerBase/PlayerEngineItemImpl;"
    },
)
