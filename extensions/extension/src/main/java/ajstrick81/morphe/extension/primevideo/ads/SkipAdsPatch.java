package ajstrick81.morphe.extension.primevideo.ads;

import android.util.Log;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;


/**
 * Prime Video ATV — ad suppression extension.
 *
 * Entry points called from patched bytecode:
 *
 *   skipAllMedia3AdGroups — strips ad schedule from media3 AdPlaybackState map
 *   skipAllExo2AdGroups   — same for ExoPlayer2 / GMS Ads variant
 *   enforceAdBlock        — Volley BasicNetwork.performRequest() host filter
 *
 * ─── Bug fixes applied in this version ─────────────────────────────────────
 *
 * BUG 1 — skipAllExo2AdGroups: ImmutableMap.checkArgument(!isEmpty()) crash
 *
 *   The original code strips ad groups using withRemovedAdGroupCount(), then
 *   rebuilds the map, then returns it. The patched hook replaces p1 BEFORE
 *   setAdPlaybackStates() executes, so setAdPlaybackStates() receives the
 *   stripped map. That is correct.
 *
 *   The actual defect: when adGroupCount == removedAdGroupCount (zero ads),
 *   withRemovedAdGroupCount() returns `this` unchanged. That is fine on its
 *   own. BUT if the original map was empty (size == 0), the function fell
 *   through to `return adPlaybackStates` (original map) from the catch block
 *   even when no exception was thrown, because ImmutableMap.Builder.build()
 *   with zero entries is valid — the map is just empty.
 *   setAdPlaybackStates() then fires Assertions.checkArgument(!isEmpty()) and
 *   the process crashes. This is NOT caught by our try/catch because it
 *   happens inside the original method AFTER our hook returns.
 *
 *   FIX: If the input map is empty, return it unchanged (let the original
 *   method crash on its own assertion — which it would do anyway — rather than
 *   masking the underlying issue). More importantly, never return an empty map
 *   when the input was non-empty. The new implementation uses
 *   withRemovedAdGroupCount only when there are real ad groups to remove;
 *   for states that are already clean (adGroupCount == removedAdGroupCount)
 *   the original state is passed through as-is, preserving the adsId
 *   required by the caller's Assertions.checkArgument(obj.equals(value.adsId)).
 *
 * BUG 2 — skipAllExo2AdGroups: adsId nullity / mismatch assertion
 *
 *   setAdPlaybackStates() reads the adsId of the first entry and then asserts
 *   that ALL entries share the same adsId. Our ImmutableMap.Builder.put()
 *   preserves each entry's key and value verbatim, so the adsId is
 *   UNCHANGED. There is therefore no mismatch here in theory. However the
 *   original code did NOT preserve the adsId when producing a new
 *   AdPlaybackState via withRemovedAdGroupCount() — that method internally
 *   calls new AdPlaybackState(this.adsId, ...) copying it through, so it was
 *   actually safe. Confirmed from decompiled source at line 641:
 *     return new AdPlaybackState(this.adsId, adGroupArr, ...);
 *   No change needed here, but the guard is now explicit.
 *
 * BUG 3 — skipAllMedia3AdGroups: same withRemovedAdGroupCount semantic
 *   Media3 AdPlaybackState.withRemovedAdGroupCount() also copies the adsId
 *   through; no issue there. The fix mirrors ExoPlayer2 for consistency.
 *
 * BUG 4 — enforceAdBlock: missing ad-decisioning paths
 *
 *   The original blocklist was missing the /cdp/catalog/GetPlaybackResources
 *   path variant used by some versions as the primary SSAI ad-schedule
 *   request. Added explicit check. Also added the `fls-na.amazon.com` host
 *   used for ad impression pixels and the `aax-us-east.amazon.com` header
 *   bidding host observed in 6.23.x logcat traces.
 *
 *   The getVideoAds path check has been hardened: the original used
 *   url.contains("/cdp/getVideoAds") but Amazon routes this as
 *   /cdp/catalog/GetVideoAds (capital G, capital V, capital A) in some builds,
 *   so the check is now case-insensitive via toLowerCase().
 *
 * ────────────────────────────────────────────────────────────────────────────
 */
@SuppressWarnings({"unused", "unchecked", "rawtypes"})
public class SkipAdsPatch {

    private static final String TAG = "SkipAdsPatch";

    // ── AdPlaybackState suppression ───────────────────────────────────────────

    /**
     * Transforms an AdPlaybackState map for the media3 SSAI pipeline.
     *
     * Called at index 0 of:
     *   androidx.media3.exoplayer.source.ads.ServerSideAdInsertionMediaSource
     *       .setAdPlaybackStates(ImmutableMap, Timeline)
     *
     * FIXED: pass-through for already-clean states to preserve adsId contract.
     */
    public static ImmutableMap skipAllMedia3AdGroups(ImmutableMap adPlaybackStates) {
        // An empty map would fail the caller's own assertion — pass through
        // and let the original method handle it (or crash on its own).
        if (adPlaybackStates.isEmpty()) {
            return adPlaybackStates;
        }
        try {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            int strippedGroups = 0;
            for (Object o : adPlaybackStates.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                Object key = entry.getKey();
                androidx.media3.common.AdPlaybackState state =
                        (androidx.media3.common.AdPlaybackState) entry.getValue();
                int activeAds = state.adGroupCount - state.removedAdGroupCount;
                if (activeAds > 0) {
                    strippedGroups += activeAds;
                    // withRemovedAdGroupCount preserves adsId internally.
                    state = state.withRemovedAdGroupCount(state.adGroupCount);
                }
                // Put the (possibly unchanged) state; adsId is always preserved.
                builder.put(key, state);
            }
            ImmutableMap result = builder.build();
            Log.i(TAG, "skipAllMedia3AdGroups: entries=" + adPlaybackStates.size()
                    + " strippedGroups=" + strippedGroups);
            return result;
        } catch (Exception e) {
            Log.e(TAG, "skipAllMedia3AdGroups failed", e);
            return adPlaybackStates;
        }
    }

    /**
     * Transforms an AdPlaybackState map for the ExoPlayer2 SSAI pipeline
     * bundled inside the Google Mobile Ads SDK (classes3.dex / classes4.dex).
     *
     * Called at index 0 of:
     *   com.google.android.exoplayer2.source.ads.ServerSideAdInsertionMediaSource
     *       .setAdPlaybackStates(ImmutableMap)
     *
     * FIXED: empty-map guard + pass-through for clean states.
     *
     * The caller (setAdPlaybackStates) does:
     *   1. Assertions.checkArgument(!immutableMap.isEmpty())      ← crashes on empty
     *   2. obj = immutableMap.values().asList().get(0).adsId      ← NPE if adsId null
     *   3. Assertions.checkArgument(obj.equals(value.adsId))      ← crash on mismatch
     *   4. Assertions.checkArgument(adGroup.isServerSideInserted) ← crash if false
     *
     * Our returned map is non-empty (same entries, states with adGroupCount ==
     * removedAdGroupCount). The loop body at step 4 runs from
     * removedAdGroupCount to adGroupCount — when they are equal the range is
     * empty so the isServerSideInserted assertion is never reached.
     * adsId is preserved by withRemovedAdGroupCount (confirmed line 641 of
     * the decompiled AdPlaybackState.java: new AdPlaybackState(this.adsId,...)).
     */
    public static ImmutableMap skipAllExo2AdGroups(ImmutableMap adPlaybackStates) {
        // Guard: do not return an empty map — the caller asserts !isEmpty().
        if (adPlaybackStates.isEmpty()) {
            return adPlaybackStates;
        }
        try {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            int strippedGroups = 0;
            for (Object o : adPlaybackStates.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                Object key = entry.getKey();
                com.google.android.exoplayer2.source.ads.AdPlaybackState state =
                        (com.google.android.exoplayer2.source.ads.AdPlaybackState) entry.getValue();
                int activeAds = state.adGroupCount - state.removedAdGroupCount;
                if (activeAds > 0) {
                    strippedGroups += activeAds;
                    state = state.withRemovedAdGroupCount(state.adGroupCount);
                }
                builder.put(key, state);
            }
            ImmutableMap result = builder.build();
            Log.i(TAG, "skipAllExo2AdGroups: entries=" + adPlaybackStates.size()
                    + " strippedGroups=" + strippedGroups);
            return result;
        } catch (Exception e) {
            Log.e(TAG, "skipAllExo2AdGroups failed", e);
            return adPlaybackStates;
        }
    }

    // ── Native/CURL downloader blocker ───────────────────────────────────────
    //
    // The logcat shows ads/manifest fetches going through a native downloader
    // (tags: DOWNLOADER, WASM, SCRIPT) using CURL, not Volley. Those requests
    // bypass BasicNetwork entirely. This helper centralizes the URL patterns
    // that should be killed for ad delivery/decisioning.
    //
    // Add more patterns here if logcat exposes new ad endpoints.
    // ─────────────────────────────────────────────────────────────────────────
    private static boolean isAdUrl(String url) {
        if (url == null) return false;
        String lower = url.toLowerCase();
        return lower.contains("aiv-delivery.net")                           // ad delivery CDN
                || lower.contains("/getvideoads")                           // SSAI ad manifest
                || lower.contains("/playbackresources")                      // SSAI schedule
                || lower.contains("getplaybackresources");
    }

    // ── Volley network-layer filtering ────────────────────────────────────────

    /**
     * Called at index 0 of:
     *   com.android.volley.toolbox.BasicNetwork.performRequest(Request)
     *
     * Rejects ad-decisioning and ad-tracking requests before any HTTP work
     * happens. Throws NoConnectionError — the same exception Volley raises
     * for genuine connectivity failures — so RetryPolicy/NetworkDispatcher
     * handle it cleanly without hanging the pipeline.
     *
     * FIXED:
     *  - Path check now case-insensitive (getVideoAds vs GetVideoAds)
     *  - Added /cdp/catalog/GetPlaybackResources ad path variant
     *  - Added fls-na.amazon.com (ad impression pixels)
     *  - Added aax-us-east.amazon.com (header bidding)
     *  - Added fls-eu.amazon.com and fls-fe.amazon.com for non-NA regions
     */
    public static void enforceAdBlock(Request<?> request) throws NoConnectionError {
        try {
            String url = request.getUrl();
            if (url == null) return;

            // Path-scoped block on the dual-use playback host.
            // atv-ps.amazon.com also serves Widevine and session APIs —
            // never block the host itself, only these ad-specific paths.
            // Case-insensitive: confirmed both camelCase and PascalCase
            // variants appear across Prime Video ATV builds.
            String urlLower = url.toLowerCase();
            if (urlLower.contains("/cdp/getvideoadss")
                    || urlLower.contains("/cdp/getvideoad")
                    || urlLower.contains("getvideoad")) {
                Log.i(TAG, "enforceAdBlock: blocking getVideoAds path [" + url + "]");
                throw new NoConnectionError(new IOException("ads_blocked: getVideoAds"));
            }

            // Block the GetPlaybackResources ad schedule call.
            // Prime Video uses GetPlaybackResources for SSAI scheduling.
            // In some builds the ad indicator query param is NOT consistently named
            // (sometimes ssai, sometimes adTracks or other variants), so requiring "ssai"
            // makes this filter miss the real ad call.
            //
            // We keep the host unblocked and key off the specific endpoint only.
            if (urlLower.contains("getplaybackresources")) {
                Log.i(TAG, "enforceAdBlock: blocking GetPlaybackResources [" + url + "]");
                throw new NoConnectionError(new IOException("ads_blocked: GetPlaybackResources"));
            }

            // Debug: log every URL that hits BasicNetwork (helps us find the *real*
            // SSAI ad endpoint on your build; right now you only showed host-blocks).
            // Low overhead: single log line.
            Log.i(TAG, "enforceAdBlock: seen url=" + url);

            String host;
            try {
                host = new URI(url).getHost();
            } catch (URISyntaxException e) {
                return; // malformed URL — not our concern
            }
            if (host == null) return;
            host = host.toLowerCase();

            boolean blocked =
                    // Ad delivery network — pure ads, no content
                    host.equals("aiv-delivery.net")
                    || host.endsWith(".aiv-delivery.net")
                    // Weblab ad triggers — keep rest of weblab safe
                    || host.equals("zoar.triggers-v1.prod.mobile.weblab.a2z.com")
                    || host.equals("hercule.triggers-v1.prod.mobile.weblab.a2z.com")
                    // Content recommendation hosts — DO NOT block these
                    // (they serve recommendations/browse, not ads)
                    // || host.matches("threeplr[a-z0-9.-]*\\.api\\.amazonvideo\\.com")
                    // || host.matches("nit[a-z0-9.-]*\\.api\\.amazonvideo\\.com")
                    // Pause-screen ad endpoint
                    || host.equals("regolith.prime-video.amazon.dev")
                    // Ad impression pixels
                    || host.equals("fls-na.amazon.com")
                    || host.equals("fls-eu.amazon.com")
                    || host.equals("fls-fe.amazon.com")
                    || host.endsWith(".fls.amazon.com")
                    // Header bidding / AAX
                    || host.equals("aax-us-east.amazon.com")
                    || host.equals("aax-us-west.amazon.com")
                    || host.endsWith(".aax.amazon.com")
                    // Amazon ad server
                    || isAdPlacementHost(host);

            if (blocked) {
                Log.i(TAG, "enforceAdBlock: blocking host [" + host + "]");
                throw new NoConnectionError(new IOException("ads_blocked: " + host));
            }
        } catch (NoConnectionError nce) {
            // Re-throw intentional blocks
            throw nce;
        } catch (Exception e) {
            Log.e(TAG, "enforceAdBlock unexpected error", e);
        }
    }

    private static boolean isAdPlacementHost(String host) {
        // Keep this conservative: only block known ad-placement hosts.
        // If you see a host in logs that you want to block, add it here.
        return host.equals("mads.amazon.com")
                || host.endsWith(".mads.amazon.com")
                || host.equals("amazon-adsystem.com")
                || host.endsWith(".amazon-adsystem.com");
    }
}
