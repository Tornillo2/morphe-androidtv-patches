package ajstrick81.morphe.extension.primevideo.ads;

import com.google.common.collect.ImmutableMap;
import java.lang.ref.WeakReference;
import java.util.Map;

/**
 * Prime Video ATV — ad suppression and speed ramp extension.
 *
 * Three entry points called from patched bytecode:
 *
 *   capturePlayer()       — stores the ExoPlayer instance at build() time
 *   skipAllMedia3AdGroups — transforms the media3 AdPlaybackState map + ramps speed
 *   skipAllExo2AdGroups   — same for the ExoPlayer2 / GMS Ads variant
 *
 * Speed ramp strategy:
 *   When ad groups are present in the incoming map (i.e. the SSAI pipeline is
 *   trying to schedule ads) we set playback speed to 8x before ExoPlayer sees
 *   the transformed (skipped) state. This acts as a safety net for any ad
 *   content that is already stitched into the HLS manifest at the segment level
 *   (SGAI pre-roll / mid-roll) and would play regardless of AdPlaybackState.
 *
 *   When the incoming map has no active groups we reset speed to 1.0x, restoring
 *   normal playback after the ad break window passes.
 *
 * Reflection is used for setPlaybackParameters() and PlaybackParameters to avoid
 * needing additional stub classes in patches/stub/. The Player reference itself
 * is typed as Object for the same reason.
 *
 * All paths are wrapped in try/catch — any failure leaves playback unaffected.
 */
@SuppressWarnings({"unused", "unchecked", "rawtypes"})
public class SkipAdsPatch {

    // ── Player reference ──────────────────────────────────────────────────────

    /**
     * Holds the active ExoPlayer instance captured from ExoPlayer.Builder.build().
     * WeakReference prevents this from keeping the Player alive past its natural
     * lifecycle (e.g. when MediaPipelineBackendEngine is destroyed and rebuilt
     * between content items or on back-navigation).
     */
    private static WeakReference<Object> sPlayerRef = null;

    /**
     * Called from the patched ExoPlayer.Builder.build() return site.
     * Stores the freshly constructed ExoPlayer in sPlayerRef so that
     * the skipAll methods can call setPlaybackParameters() on it.
     *
     * @param player The ExoPlayer instance returned by build() (typed as Object
     *               to avoid requiring an ExoPlayer stub in the project).
     */
    public static void capturePlayer(Object player) {
        if (player != null) {
            sPlayerRef = new WeakReference<>(player);
        }
    }

    // ── Speed control ─────────────────────────────────────────────────────────

    /** Playback speed applied when ad groups are detected. 8x makes ~15s ads
     *  pass in under 2 seconds; fast enough to be nearly transparent, slow
     *  enough to avoid buffering stalls on typical CDN delivery rates.      */
    private static final float AD_SPEED = 8.0f;

    /** Normal content speed restored after the ad break window clears.      */
    private static final float NORMAL_SPEED = 1.0f;

    /**
     * Sets ExoPlayer playback speed via reflection on the captured Player
     * instance. Called with AD_SPEED when ad groups are detected and
     * NORMAL_SPEED when the ad break window clears.
     *
     * Constructs PlaybackParameters(float) via reflection to avoid needing
     * a PlaybackParameters stub class. Silent no-op on any failure.
     */
    private static void setSpeed(float speed) {
        if (sPlayerRef == null) return;
        Object player = sPlayerRef.get();
        if (player == null) return;
        try {
            Class<?> ppClass = Class.forName("androidx.media3.common.PlaybackParameters");
            Object params = ppClass.getConstructor(float.class).newInstance(speed);
            player.getClass()
                  .getMethod("setPlaybackParameters", ppClass)
                  .invoke(player, params);
        } catch (Exception e) {
            // Silent fail — playback continues at whatever speed it was at.
        }
    }

    // ── Ad group transformation ───────────────────────────────────────────────

    /**
     * Transforms an AdPlaybackState map for the media3 SSAI pipeline.
     *
     * Called at index 0 of:
     *   androidx.media3.exoplayer.source.ads.ServerSideAdInsertionMediaSource
     *       .setAdPlaybackStates(ImmutableMap, Timeline)
     *
     * For each AdPlaybackState in the map:
     *   - Iterates active groups [removedAdGroupCount, adGroupCount)
     *   - Calls withSkippedAdGroup(i) to mark every ad as AD_STATE_SKIPPED
     *
     * withSkippedAdGroup() calls AdGroup.withAllAdsSkipped() which sets
     * AD_STATE_SKIPPED without touching isServerSideInserted, so the SSAI
     * validation in the original method continues to pass.
     *
     * Speed ramp: if any active groups were found in the input map, ramps
     * ExoPlayer to AD_SPEED to fly through any segments already stitched
     * into the HLS manifest. Resets to NORMAL_SPEED when no groups remain.
     *
     * Fail-safe: any exception returns the original map unmodified so
     * playback degrades gracefully rather than crashing.
     */
    public static ImmutableMap skipAllMedia3AdGroups(ImmutableMap adPlaybackStates) {
        try {
            boolean hasAdGroups = false;
            ImmutableMap.Builder builder = ImmutableMap.builder();

            for (Object o : adPlaybackStates.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                Object key = entry.getKey();
                androidx.media3.common.AdPlaybackState state =
                        (androidx.media3.common.AdPlaybackState) entry.getValue();

                if (state.adGroupCount > state.removedAdGroupCount) {
                    hasAdGroups = true;
                }

                for (int i = state.removedAdGroupCount; i < state.adGroupCount; i++) {
                    state = state.withSkippedAdGroup(i);
                }
                builder.put(key, state);
            }

            setSpeed(hasAdGroups ? AD_SPEED : NORMAL_SPEED);
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
     * contract, same speed ramp behaviour — but operating on the ExoPlayer2
     * AdPlaybackState type from the parallel class hierarchy.
     */
    public static ImmutableMap skipAllExo2AdGroups(ImmutableMap adPlaybackStates) {
        try {
            boolean hasAdGroups = false;
            ImmutableMap.Builder builder = ImmutableMap.builder();

            for (Object o : adPlaybackStates.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                Object key = entry.getKey();
                com.google.android.exoplayer2.source.ads.AdPlaybackState state =
                        (com.google.android.exoplayer2.source.ads.AdPlaybackState) entry.getValue();

                if (state.adGroupCount > state.removedAdGroupCount) {
                    hasAdGroups = true;
                }

                for (int i = state.removedAdGroupCount; i < state.adGroupCount; i++) {
                    state = state.withSkippedAdGroup(i);
                }
                builder.put(key, state);
            }

            setSpeed(hasAdGroups ? AD_SPEED : NORMAL_SPEED);
            return builder.build();

        } catch (Exception e) {
            return adPlaybackStates;
        }
    }
}
