# Prime Video — DNS Ad-Suppression Layer

This is the **DNS half of a dual-layer ad-suppression strategy** for Prime Video
on Android TV. It is the companion to the bytecode patch (`skipAds`), not a
replacement for it.

## Why two layers?

Prime Video chooses an ad-delivery architecture **server-side, per session**
(keyed on device type, title, account, region, and A/B bucket). There are two
"roads", and they require different tools:

| Road | How the ad arrives | Reached by |
|---|---|---|
| **Java road** | Ad break signalled to the client as a media3 `AdPlaybackState` ad group | **Bytecode patch** (`skipAllMedia3AdGroups`) |
| **Native road** | Ad stitched server-side (SGAI) and delivered through Amazon's **native** pipeline over **native libcurl** | **DNS blocking** (this list) |

The bytecode patch operates at the Java layer and **cannot see** native-road
traffic — on-device logcat confirmed Prime Video's ad/media/API plane runs
through native `libcurl` (`DOWNLOADER`) and a native media pipeline
(`NativeMediaPipelineBackend`), not Java `Volley`/`DefaultHttpDataSource`.

DNS blocking works **below HTTP**, so it reaches that native traffic just as
well as anything. Conversely, DNS is **host-level only** — it cannot block an ad
*path* on a dual-use host (e.g. `atv-ps.amazon.com/cdp/getVideoAds`, which also
serves the Widevine license). That path is exactly what the patch handles.

**Each layer covers the road the other cannot. Run both.**

## What this list is calibrated for

Every rule is tagged with the capture that confirmed it (`[ATV]`, `[PC]`,
`[PIH]`, `[PCAP]` = TLS SNI from packet captures, `[v3.6]`, `[UNV]` =
unverified). It is deliberately **more conservative
on CDNs than the older v3.6 list**, because our packet/HAR evidence proved v3.6
would block real movie content:

- v3.6 null-routed `*vod-dash-pv-ta-amazon.akamaized.net` and
  `*.vod-dash.main.amazon.pv-cdn.net`. Our PC capture caught those exact hosts
  **serving the movie**. CDN shard assignment is non-deterministic, so host/
  prefix blocking on the shared VOD CDNs will eventually break playback. They are
  now in **safe harbor**, and the only way to strip ad *segments* from a shared
  CDN reliably is at the path/manifest layer (the patch), not DNS.
- `s0s7.api.amazonvideo.com` returned HTTP 304 as a live playback API in our
  capture, so it is no longer blocked by default.

## Tiers

1. **Safe harbor** (`@@ ... $important`) — content/DRM/playback/subtitles. Always
   on, always wins.
2. **Tier 1** — high-confidence pure ad hosts. Recommended default.
3. **Tier 2** — aggressive/opt-in, **commented out**. Real content-risk or
   unverified. Enable one at a time and watch for breakage.
4. **Telemetry** — privacy only; does not affect ads.

## Usage

### AdGuard Home (the list's native format)
1. **Filters → DNS blocklists → Add blocklist → Add a custom list.**
2. Paste the raw URL of `dns/prime-video.txt` (or upload its contents).
3. Ensure your default blocking mode returns `0.0.0.0`/refused (the `$dnsrewrite`
   rules force this anyway, which gives a clean fast-fail instead of NXDOMAIN
   retry storms).

### Pi-hole
Pi-hole does not parse `$dnsrewrite`/`$important`. Two options:
- **Adlist (ABP mode):** enable Pi-hole's ABP-style adlist support, then the
  plain `||host^` rules are honored; strip the `$...` modifiers.
- **Regex/exact:** translate the key rules manually, e.g.
  - Block: `amazon-adsystem.com`, `aiv-delivery.net`,
    `zoar.triggers-v1.prod.mobile.weblab.a2z.com`
  - Allowlist (critical): `atv-ps.amazon.com`, `vod-dash.main.amazon.pv-cdn.net`,
    `vod-dash-pv-ta-amazon.akamaized.net`, `aux.pv-cdn.net` (subtitles +
    trickplay), `aiv-cdn.net`, `images-na.ssl-images-amazon.com`, `m.media-amazon.com`

> The `threeplr*`/`nit*` prefix rules need wildcard/regex support; in Pi-hole use
> a regex blacklist: `^(threeplr|nit)[a-z0-9.-]*\.api\.amazonvideo\.com$`.

## Verifying it works

- **DNS side:** watch your resolver log during playback. You want
  `aiv-delivery.net` / `amazon-adsystem.com` **blocked**, and `amazonvideo.com` /
  `*.pv-cdn.net` content hosts **allowed**.
- **If a title stalls / black-screens:** you are likely on a native-road SGAI
  title and the stitcher block is too aggressive — narrow `||aiv-delivery.net^`
  to `||api.us-east-1.aiv-delivery.net^` (see the inline note in the list).
- **Patch side:** with DNS blocking **off**, `logcat | grep -i skipads` during an
  ad title tells you whether the Java-road hooks are firing.
