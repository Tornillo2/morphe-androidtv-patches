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
 * For each AdPlaybackState in the incoming ImmutableMap:
 *   - Iterates active groups in range [removedAdGroupCount, adGroupCount)
 *   - Calls withSkippedAdGroup(i) to mark every ad as AD_STATE_SKIPPED
 *
 * withSkippedAdGroup() calls AdGroup.withAllAdsSkipped() which sets
 * AD_STATE_SKIPPED on each ad without touching isServerSideInserted,
 * so the SSAI validation in setAdPlaybackStates() continues to pass.
 *
 * Loop range rationale:
 *   removedAdGroupCount — groups already passed in live streams; skipping
 *   below this index produces a negative array index inside withSkippedAdGroup.
 *   adGroupCount — total groups including removed; active groups are at
 *   array indices [0, adGroupCount - removedAdGroupCount).
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
     */
    public static ImmutableMap skipAllMedia3AdGroups(ImmutableMap adPlaybackStates) {
        try {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            for (Object o : adPlaybackStates.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                Object key = entry.getKey();
                androidx.media3.common.AdPlaybackState state =
                        (androidx.media3.common.AdPlaybackState) entry.getValue();
                for (int i = state.removedAdGroupCount; i < state.adGroupCount; i++) {
                    state = state.withSkippedAdGroup(i);
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
     * Identical logic to skipAllMedia3AdGroups — same withSkippedAdGroup
     * contract — but operating on the ExoPlayer2 AdPlaybackState type.
     */
    public static ImmutableMap skipAllExo2AdGroups(ImmutableMap adPlaybackStates) {
        try {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            for (Object o : adPlaybackStates.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                Object key = entry.getKey();
                com.google.android.exoplayer2.source.ads.AdPlaybackState state =
                        (com.google.android.exoplayer2.source.ads.AdPlaybackState) entry.getValue();
                for (int i = state.removedAdGroupCount; i < state.adGroupCount; i++) {
                    state = state.withSkippedAdGroup(i);
                }
                builder.put(key, state);
            }
            return builder.build();
        } catch (Exception e) {
            return adPlaybackStates;
        }
    }
}
