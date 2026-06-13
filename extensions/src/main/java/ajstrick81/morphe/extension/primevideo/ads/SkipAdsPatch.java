package ajstrick81.morphe.extension.primevideo.ads;

import com.google.common.collect.ImmutableMap;
import java.util.Map;

/**
 * Prime Video ATV — ad suppression extension.
 *
 * Entry points called from patched bytecode:
 *
 *   skipAllMedia3AdGroups — strips ad schedule from media3 AdPlaybackState map
 *   skipAllExo2AdGroups   — same for ExoPlayer2 / GMS Ads variant
 *   seekToAdBreakEnd      — seeks player past current ad break (hoodles-inspired)
 *
 * --- seekToAdBreakEnd: The Hoodles-Inspired Seek Technique ---
 *
 * Inspired by hoodles' Prime Video Mobile patch which hooks
 * ServerInsertedAdBreakState.enter(state, trigger, player) to seek past
 * ad breaks using direct player control.
 *
 * The ATV equivalent hook point is
 * ServerSideAdInsertionUtil.getStreamPositionUs(Player, AdPlaybackState)
 * which is called during active ad playback with live references to both
 * the Player and the AdPlaybackState.
 *
 * The parallel:
 *   Hoodles mobile: trigger.getBreak().getDurationExcludingAux()
 *   Our ATV:        adPlaybackState.getAdGroup(i).durationsUs[j]
 *
 *   Hoodles mobile: player.seekTo(adBreakEndMs)
 *   Our ATV:        player.seekTo(currentPositionMs + remainingAdDurationMs)
 *
 * Algorithm:
 *   1. Check isPlayingAd() — return immediately if not in an ad
 *   2. Get currentAdGroupIndex from player
 *   3. Get currentAdIndexInAdGroup from player
 *   4. Sum remaining ad durations in the group starting from current ad
 *   5. Convert microseconds to milliseconds (durationsUs → durationMs)
 *   6. seekTo(currentPosition + remainingDuration)
 *
 * This operates DURING ad playback — after the WASM pre-buffers segments
 * but while they're playing. Combined with setAdPlaybackStates (prevents
 * new ad groups from being scheduled) this covers both pre-roll timing
 * gap and active mid-roll playback.
 *
 * All methods wrapped in try/catch — any failure is a silent no-op.
 */
@SuppressWarnings({"unused", "unchecked", "rawtypes"})
public class SkipAdsPatch {

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
     * Transforms an AdPlaybackState map for the ExoPlayer2 SSAI pipeline.
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

    // ── Hoodles-inspired seek technique ───────────────────────────────────────

    /**
     * Seeks the player past the current ad break.
     *
     * Called at index 0 of:
     *   ServerSideAdInsertionUtil.getStreamPositionUs(Player, AdPlaybackState)
     *
     * This method is only called when media3 needs to calculate the stream
     * position during ad playback — meaning isPlayingAd() is true and we
     * have a live player reference to seek with.
     *
     * The seek target is:
     *   currentPosition + sum of remaining ad durations in the current group
     *   (in milliseconds, converted from microseconds)
     *
     * After seeking, the WASM playback::machine's setAdPlaybackStates hook
     * (Hook 1) will fire with the new position's AdPlaybackState, where
     * withRemovedAdGroupCount() will clean up any remaining ad groups.
     *
     * @param player          Live Player interface reference from p0
     * @param adPlaybackState Current AdPlaybackState from p1
     */
    public static void seekToAdBreakEnd(
            androidx.media3.common.Player player,
            androidx.media3.common.AdPlaybackState adPlaybackState) {
        try {
            // Only act when actually playing an ad
            if (!player.isPlayingAd()) return;

            int adGroupIndex = player.getCurrentAdGroupIndex();
            int adIndexInGroup = player.getCurrentAdIndexInAdGroup();

            // Validate indices
            if (adGroupIndex < 0 || adGroupIndex >= adPlaybackState.adGroupCount) return;

            androidx.media3.common.AdPlaybackState.AdGroup adGroup =
                    adPlaybackState.getAdGroup(adGroupIndex);

            if (adGroup == null || adGroup.durationsUs == null) return;

            // Sum remaining ad durations from current ad to end of group
            // durationsUs is in microseconds — convert to milliseconds for seekTo
            long remainingDurationUs = 0L;
            int startIndex = Math.max(0, adIndexInGroup);
            for (int i = startIndex; i < adGroup.count && i < adGroup.durationsUs.length; i++) {
                long dur = adGroup.durationsUs[i];
                // Skip unknown/unset durations (C.TIME_UNSET = Long.MIN_VALUE + 1)
                if (dur > 0) remainingDurationUs += dur;
            }

            if (remainingDurationUs <= 0) return;

            // Convert microseconds to milliseconds
            long remainingDurationMs = remainingDurationUs / 1000L;

            // Seek past the ad break
            long currentPositionMs = player.getCurrentPosition();
            player.seekTo(currentPositionMs + remainingDurationMs);

        } catch (Exception e) {
            // Silent fail — never interfere with original getStreamPositionUs
        }
    }
}
