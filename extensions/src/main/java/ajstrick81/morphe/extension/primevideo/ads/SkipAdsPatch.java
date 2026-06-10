package ajstrick81.morphe.extension.primevideo.ads;

import com.google.common.collect.ImmutableMap;
import java.util.Map;

/**
 * Prime Video ATV — ad group suppression extension.
 *
 * Entry points called from patched bytecode:
 *
 *   skipAllMedia3AdGroups — transforms the media3 AdPlaybackState map
 *   skipAllExo2AdGroups   — same for the ExoPlayer2 / GMS Ads variant
 *
 * Strategy: withRemovedAdGroupCount(adGroupCount) physically removes all ad
 * groups from the AdPlaybackState in one operation. This is the stable,
 * verified approach that has been confirmed working across multiple test
 * sessions on Onn 4K TV.
 *
 * The withAdDurationsUs(1L) approach (Gemini suggestion) was reverted —
 * it added complexity without a measurable improvement given that the
 * isAdvertisingOptOut hook (SupplementaryAdsPatch) already dramatically
 * reduces ad load before setAdPlaybackStates is called.
 *
 * Note: logGMBMessage was removed. GMB diagnostic analysis confirmed the
 * message bus carries zero ad-related traffic — all ad delivery is handled
 * entirely within the WASM/Ignite native layer. The diagnostic method
 * served its purpose and is no longer needed.
 *
 * All paths wrapped in try/catch — any failure returns the original
 * unmodified map so playback degrades gracefully.
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
     * bundled inside the Google Mobile Ads SDK (classes4.dex).
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
}
