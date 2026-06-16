/*
 * MLB At Bat Android TV — Ad Break Fingerprints
 *
 * Validated against:
 *   v26.8.1  (versionCode 1750000022) — com.bamnetworks.mobile.android.gameday
 *
 * BETWEEN-INNINGS AD BREAK MECHANISM (confirmed via logcat analysis):
 *
 *   At Bat uses a TWO-LAYER system for between-innings ads:
 *
 *   Layer 1 — Publica (Programmatic Ad Auction):
 *     GetPublicaBidsUseCase fetches ad bids from Publica's ad server.
 *     PublicaBidListener.onAdBreakStarted() is called when a break begins,
 *     triggering the ad auction and slot fill.
 *
 *   Layer 2 — LinearGoogleDaiListener (Pod Metadata + Segment Delivery):
 *     Fetches DAI pod metadata from dai.google.com (ad break timing/segments).
 *     Ad video segments are then fetched from googlevideo.com as MPEG-TS
 *     with source=dclk_video_ads (DoubleClick Video Ads).
 *
 *   Confirmed via logcat:
 *     "[MlbMediaPlayer] onAdBreakStarted"
 *     "[LinearGoogleDaiListener] Starting pod metadata timer"
 *     googlevideo.com/.../source/dclk_video_ads (responseCode: 200)
 *     pubads.g.doubleclick.net/adsid/integrator.json (ad auction call)
 *
 *   The ad video segments come from rotating CDN subdomains:
 *     r2---sn-uhvcpax0n5-vgqz.googlevideo.com
 *     r3---sn-uhvcpax0n5-vgqz.googlevideo.com
 *     r4---sn-vgqsknez.googlevideo.com
 *   All with source=dclk_video_ads — same CDN as live game content,
 *   making domain-level blocking impossible without killing the stream.
 *
 * STRATEGY:
 *   Intercept at the HIGHEST level — PublicaBidListener.onAdBreakStarted().
 *   This fires when the player signals a commercial break is starting.
 *   return-void here prevents:
 *     - Publica ad auction from running
 *     - DAI pod metadata from being fetched
 *     - googlevideo.com ad segments from being requested
 *     - BetMGM, Bet365, and other gambling ads from rendering
 *
 *   The "Commercial Break - We'll be right back" placeholder is expected
 *   to display instead (as it did with older DNS blocking on previous versions).
 *
 * PATCH POINTS:
 *   Primary:   PublicaBidListener.onAdBreakStarted()  ← Highest-level intercept
 *   Secondary: GetPublicaBidsUseCase (execute/invoke)  ← Kills upstream bid request
 *
 * CLASSES (unobfuscated — confirmed in classes6.dex):
 *   mlb.atbat.media.player.listener.publica.PublicaBidListener
 *   mlb.atbat.data.usecase.GetPublicaBidsUseCase
 */

package app.morphe.patches.atbat

import app.morphe.patcher.Fingerprint

// ---------------------------------------------------------------------------
// Patch 3: Between-Innings Ad Break Suppression — PublicaBidListener
//
// PublicaBidListener.onAdBreakStarted() is the primary entry point for
// between-innings commercial breaks. When the DAI stream signals a break,
// this method fires and triggers the full Publica ad auction + segment fetch.
//
// return-void prevents:
//   - Publica bid request (GetPublicaBidsUseCase)
//   - DAI pod metadata fetch (LinearGoogleDaiListener.fetchPodMetadata)
//   - googlevideo.com MPEG-TS ad segment requests (source=dclk_video_ads)
//   - BetMGM, Bet365, FanDuel gambling ad rendering
//
// Anchored on unobfuscated log strings from classes6.dex.
// The companion lambda classes confirm the method structure:
//   PublicaBidListener$onAdBreakStarted$1
//   PublicaBidListener$onAdBreakFinished$1
//
// EXPECTED RESULT:
//   Between-innings break shows "Commercial Break - We'll be right back"
//   placeholder (same as old DNS blocking behavior on previous versions).
//   Game playback resumes normally after the break duration.
// ---------------------------------------------------------------------------

internal object PublicaAdBreakStartedFingerprint : Fingerprint(
    returnType = "V",
    strings = listOf("[MlbMediaPlayer] onAdBreakStarted"),
    custom = { method, _ ->
        method.definingClass.endsWith("PublicaBidListener;") &&
            method.name == "onAdBreakStarted"
    },
)

// ---------------------------------------------------------------------------
// Patch 4: Publica Bid Request Suppression — GetPublicaBidsUseCase
//
// GetPublicaBidsUseCase fetches ad bids from Publica's auction server.
// This is the upstream source of all between-innings ad decisions.
// Killing this use case prevents any ad bid from being placed, which
// means no ad creative is selected for playback.
//
// Anchored on error strings from classes6.dex:
//   "Failed to get Publica ads with response code: "
//   "Failed to get Publica ads due to an exception: "
//   "Publica bids count: "
//
// This is a SECONDARY patch — PublicaAdBreakStartedFingerprint is the
// primary intercept. This adds depth-of-defense: even if onAdBreakStarted
// somehow fires, there are no bids to fulfill.
//
// NOTE: If this fingerprint causes issues (e.g. the method is a suspend
// function with a Continuation parameter), disable it and rely solely on
// PublicaAdBreakStartedFingerprint.
// ---------------------------------------------------------------------------

internal object GetPublicaBidsFingerprint : Fingerprint(
    returnType = "V",
    strings = listOf("Publica bids count: ", "Failed to get Publica ads"),
    custom = { method, _ ->
        method.definingClass.endsWith("GetPublicaBidsUseCase;")
    },
)
