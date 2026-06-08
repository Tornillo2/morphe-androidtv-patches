package ajstrick81.morphe.extension.primevideo.ads;

import com.google.common.collect.ImmutableMap;
import java.util.Map;

/**
 * Prime Video ATV — ad group suppression and GMB diagnostic extension.
 *
 * Entry points called from patched bytecode:
 *
 *   skipAllMedia3AdGroups — transforms the media3 AdPlaybackState map
 *   skipAllExo2AdGroups   — same for the ExoPlayer2 / GMS Ads variant
 *   logGMBMessage         — DIAGNOSTIC: logs all GMB event types to logcat
 *
 * --- AdPlaybackState suppression ---
 *
 * Strategy: withRemovedAdGroupCount(adGroupCount) physically removes all ad
 * groups from the state in one operation rather than iterating and skipping
 * each group individually. This prevents the WASM playback::machine from
 * looping trying to find correlation IDs for skipped groups — reducing the
 * ad break stall from ~56 seconds to ~9 seconds.
 *
 * --- GMB Diagnostic ---
 *
 * logGMBMessage() is called at index 0 of GMBMessageProcessor.processMessage()
 * and logs every GMB event type string and payload to Android logcat under
 * the tag "GMB_DIAGNOSTIC" using standard android.util.Log.d().
 *
 * Amazon's internal logger (com.amazon.reporting.Log.d) already logs GMB
 * messages but uses an internal pipeline invisible to ADB logcat. This method
 * bridges that gap by duplicating the log to the standard Android logger.
 *
 * Filter in logcat:
 *   adb logcat --pid=<PID> -v time | findstr "GMB_DIAGNOSTIC"
 *
 * This is a TEMPORARY diagnostic — once the ad/billing event type strings
 * are identified it will be replaced with a targeted suppression patch.
 *
 * All paths are wrapped in try/catch — any failure is a silent no-op so
 * playback continues normally.
 */
@SuppressWarnings({"unused", "unchecked", "rawtypes"})
public class SkipAdsPatch {

    private static final String GMB_TAG = "GMB_DIAGNOSTIC";

    // ── AdPlaybackState suppression ───────────────────────────────────────────

    /**
     * Transforms an AdPlaybackState map for the media3 SSAI pipeline.
     *
     * Called at index 0 of:
     *   androidx.media3.exoplayer.source.ads.ServerSideAdInsertionMediaSource
     *       .setAdPlaybackStates(ImmutableMap, Timeline)
     */
    public static ImmutableMap skipAllMedia3AdGroups(ImmutableMap adPlaybackStates) {
        try {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            for (Object o : adPlaybackStates.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                Object key = entry.getKey();
                androidx.media3.common.AdPlaybackState state =
                        (androidx.media3.common.AdPlaybackState) entry.getValue();
                if (state.adGroupCount > state.removedAdGroupCount) {
                    state = state.withRemovedAdGroupCount(state.adGroupCount);
                }
                builder.put(key, state);
            }
            return builder.build();
        } catch (Exception e) {
            return adPlaybackStates;
        }
    }

    /**
     * Transforms an AdPlaybackState map for the ExoPlayer2 SSAI pipeline
     * bundled inside the Google Mobile Ads SDK (classes3.dex).
     *
     * Called at index 0 of:
     *   com.google.android.exoplayer2.source.ads.ServerSideAdInsertionMediaSource
     *       .setAdPlaybackStates(ImmutableMap)
     */
    public static ImmutableMap skipAllExo2AdGroups(ImmutableMap adPlaybackStates) {
        try {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            for (Object o : adPlaybackStates.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                Object key = entry.getKey();
                com.google.android.exoplayer2.source.ads.AdPlaybackState state =
                        (com.google.android.exoplayer2.source.ads.AdPlaybackState) entry.getValue();
                if (state.adGroupCount > state.removedAdGroupCount) {
                    state = state.withRemovedAdGroupCount(state.adGroupCount);
                }
                builder.put(key, state);
            }
            return builder.build();
        } catch (Exception e) {
            return adPlaybackStates;
        }
    }

    // ── GMB Diagnostic ────────────────────────────────────────────────────────

    /**
     * DIAGNOSTIC — called at index 0 of GMBMessageProcessor.processMessage().
     *
     * Logs every GMB event type and payload to Android logcat under the tag
     * "GMB_DIAGNOSTIC" using standard android.util.Log.d() which surfaces
     * in ADB logcat — unlike Amazon's internal com.amazon.reporting.Log.d().
     *
     * This method does NOT suppress any messages. Every GMB message passes
     * through to the original processMessage() body after this call.
     *
     * During ad breaks and overlay events you will see entries like:
     *   GMB_DIAGNOSTIC: [TYPE] billing.reportPurchaseLaunchState
     *   GMB_DIAGNOSTIC: [PAYLOAD] {"state":"LAUNCHED",...}
     *
     * Payload is truncated to 500 chars to keep logcat readable.
     *
     * @param eventType The GMB event type string
     * @param payload   The JSON payload for this event
     */
    public static void logGMBMessage(String eventType, String payload) {
        try {
            android.util.Log.d(GMB_TAG, "[TYPE] " + eventType);
            if (payload != null && payload.length() > 0) {
                String truncated = payload.length() > 500
                    ? payload.substring(0, 500) + "...[TRUNCATED]"
                    : payload;
                android.util.Log.d(GMB_TAG, "[PAYLOAD] " + truncated);
            }
        } catch (Exception e) {
            // Silent fail — never interfere with original processMessage flow
        }
    }
}
