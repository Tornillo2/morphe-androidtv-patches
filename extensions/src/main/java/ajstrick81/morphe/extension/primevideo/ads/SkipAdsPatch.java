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
 * --- Strategy: 1-microsecond fake ad duration ---
 *
 * Previous strategy: withRemovedAdGroupCount(adGroupCount)
 *   Physically removed all ad groups from the state. ExoPlayer skipped to
 *   content cleanly but never fired ad transition events back to the WASM
 *   layer. The WASM playback::machine waited ~9 seconds for transition
 *   events that never arrived before timing out.
 *
 * New strategy: withAdDurationsUs(long[][] durationsUs)
 *   Keeps all ad groups intact but sets every individual ad duration to
 *   1 microsecond. ExoPlayer technically "plays" each ad for 1 microsecond
 *   — fast enough to be invisible — and fires the proper onPositionDiscontinuity
 *   and MediaItemTransition events back to the WASM layer. The WASM machine
 *   receives these events, registers the ad break as completed, and
 *   immediately resumes normal playback UI without any stall.
 *
 * withAdDurationsUs takes a 2D long array [[J — one long[] per ad group,
 * each containing the duration for every individual ad in that group.
 * The array index aligns with ad group index in the AdPlaybackState.
 * The AdGroup.count field gives us the number of ads in each group.
 *
 * Both media3 and ExoPlayer2 share identical signatures and field names:
 *   withAdDurationsUs([[J) → AdPlaybackState
 *   AdGroup.count: I
 *   AdGroup.durationsUs: [J
 *
 * --- GMB Diagnostic ---
 *
 * logGMBMessage() confirmed via inline smali hook that the GMB message bus
 * carries zero ad-related traffic. All 4 event types observed are device
 * integration only (voice, ODE recommendations). Ad delivery is handled
 * entirely within the WASM/Ignite native layer. This method is kept for
 * completeness but the diagnostic patch is no longer needed.
 *
 * All paths wrapped in try/catch — any failure returns the original
 * unmodified map so playback degrades gracefully.
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
     *
     * For each AdPlaybackState, builds a 2D long array where every ad
     * duration across all groups is set to 1 microsecond, then applies
     * withAdDurationsUs to force immediate ad completion events.
     */
    public static ImmutableMap skipAllMedia3AdGroups(ImmutableMap adPlaybackStates) {
        try {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            for (Object o : adPlaybackStates.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                Object key = entry.getKey();
                androidx.media3.common.AdPlaybackState state =
                        (androidx.media3.common.AdPlaybackState) entry.getValue();

                int groupCount = state.adGroupCount;
                if (groupCount > state.removedAdGroupCount) {
                    // Build 2D duration array — one long[] per ad group
                    long[][] allDurations = new long[groupCount][];
                    for (int i = 0; i < groupCount; i++) {
                        androidx.media3.common.AdPlaybackState.AdGroup group =
                                state.getAdGroup(i);
                        int adCount = group.count;
                        // If count is unknown (UNDEFINED) default to 1
                        if (adCount <= 0) adCount = 1;
                        long[] groupDurations = new long[adCount];
                        java.util.Arrays.fill(groupDurations, 1L);
                        allDurations[i] = groupDurations;
                    }
                    state = state.withAdDurationsUs(allDurations);
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
     * bundled inside the Google Mobile Ads SDK (classes4.dex).
     *
     * Called at index 0 of:
     *   com.google.android.exoplayer2.source.ads.ServerSideAdInsertionMediaSource
     *       .setAdPlaybackStates(ImmutableMap)
     *
     * Identical strategy to skipAllMedia3AdGroups — same withAdDurationsUs
     * signature and identical AdGroup.count field.
     */
    public static ImmutableMap skipAllExo2AdGroups(ImmutableMap adPlaybackStates) {
        try {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            for (Object o : adPlaybackStates.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                Object key = entry.getKey();
                com.google.android.exoplayer2.source.ads.AdPlaybackState state =
                        (com.google.android.exoplayer2.source.ads.AdPlaybackState) entry.getValue();

                int groupCount = state.adGroupCount;
                if (groupCount > state.removedAdGroupCount) {
                    long[][] allDurations = new long[groupCount][];
                    for (int i = 0; i < groupCount; i++) {
                        com.google.android.exoplayer2.source.ads.AdPlaybackState.AdGroup group =
                                state.getAdGroup(i);
                        int adCount = group.count;
                        if (adCount <= 0) adCount = 1;
                        long[] groupDurations = new long[adCount];
                        java.util.Arrays.fill(groupDurations, 1L);
                        allDurations[i] = groupDurations;
                    }
                    state = state.withAdDurationsUs(allDurations);
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
     * DIAGNOSTIC — kept for reference, no longer actively needed.
     *
     * GMB analysis confirmed the message bus carries zero ad-related traffic.
     * All ad delivery is handled within the WASM/Ignite native layer.
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
            // Silent fail
        }
    }
}
