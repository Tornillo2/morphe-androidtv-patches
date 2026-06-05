package ajstrick81.morphe.extension.primevideo.ads;

import com.google.common.collect.ImmutableMap;
import java.util.Map;

/**
 * Prime Video ATV — ad group suppression extension.
 *
 * Two entry points called from patched bytecode:
 *
 *   skipAllMedia3AdGroups — transforms the media3 AdPlaybackState map
 *   skipAllExo2AdGroups   — same for the ExoPlayer2 / GMS Ads variant
 *
 * Strategy change from withSkippedAdGroup to withRemovedAdGroupCount:
 *
 *   withSkippedAdGroup(i) marks each ad as AD_STATE_SKIPPED but leaves the
 *   ad groups present in the AdPlaybackState. The WASM playback::machine
 *   sees groups in SKIPPED state and loops trying to find their correlation
 *   IDs to close the ad break envelope. When those IDs are absent (because
 *   we stripped the ad data) the machine stalls for ~56 seconds before
 *   timing out — causing the "longer commercials" effect observed in logcat.
 *
 *   withRemovedAdGroupCount(adGroupCount) physically removes all ad groups
 *   from the state object by setting removedAdGroupCount = adGroupCount.
 *   From the playback::machine's perspective the ad break never existed —
 *   no groups to iterate, no envelope to refresh, no correlation ID to find.
 *   The state machine advances cleanly to content without the stall loop.
 *
 * All paths are wrapped in try/catch — any failure returns the original
 * map unmodified so playback degrades gracefully rather than crashing.
 */
@SuppressWarnings({"unused", "unchecked", "rawtypes"})
public class SkipAdsPatch {

    /**
     * Transforms an AdPlaybackState map for the media3 SSAI pipeline.
     *
     * Called at index 0 of:
     *   androidx.media3.exoplayer.source.ads.ServerSideAdInsertionMediaSource
     *       .setAdPlaybackStates(ImmutableMap, Timeline)
     *
     * For each AdPlaybackState in the map, calls withRemovedAdGroupCount
     * with the full adGroupCount to remove all ad groups in one operation,
     * rather than iterating and skipping each group individually.
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
     *
     * Same withRemovedAdGroupCount strategy as skipAllMedia3AdGroups.
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
}
