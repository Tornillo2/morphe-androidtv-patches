package com.google.android.exoplayer2;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.offline.StreamKey;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableList;
import com.google.common.collect.RegularImmutableMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.InlineMe;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MediaItem implements Bundleable {
    public static final String DEFAULT_MEDIA_ID = "";
    public final ClippingConfiguration clippingConfiguration;

    @Deprecated
    public final ClippingProperties clippingProperties;
    public final LiveConfiguration liveConfiguration;

    @Nullable
    public final LocalConfiguration localConfiguration;
    public final String mediaId;
    public final MediaMetadata mediaMetadata;

    @Nullable
    @Deprecated
    public final PlaybackProperties playbackProperties;
    public final RequestMetadata requestMetadata;
    public static final MediaItem EMPTY = new Builder().build();
    public static final String FIELD_MEDIA_ID = Integer.toString(0, 36);
    public static final String FIELD_LIVE_CONFIGURATION = Integer.toString(1, 36);
    public static final String FIELD_MEDIA_METADATA = Integer.toString(2, 36);
    public static final String FIELD_CLIPPING_PROPERTIES = Integer.toString(3, 36);
    public static final String FIELD_REQUEST_METADATA = Integer.toString(4, 36);
    public static final Bundleable.Creator<MediaItem> CREATOR = new MediaItem$$ExternalSyntheticLambda0();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AdsConfiguration {
        public final Uri adTagUri;

        @Nullable
        public final Object adsId;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Builder {
            public Uri adTagUri;

            @Nullable
            public Object adsId;

            public Builder(Uri uri) {
                this.adTagUri = uri;
            }

            public AdsConfiguration build() {
                return new AdsConfiguration(this);
            }

            @CanIgnoreReturnValue
            public Builder setAdTagUri(Uri uri) {
                this.adTagUri = uri;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setAdsId(@Nullable Object obj) {
                this.adsId = obj;
                return this;
            }
        }

        public Builder buildUpon() {
            Builder builder = new Builder(this.adTagUri);
            builder.adsId = this.adsId;
            return builder;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AdsConfiguration)) {
                return false;
            }
            AdsConfiguration adsConfiguration = (AdsConfiguration) obj;
            return this.adTagUri.equals(adsConfiguration.adTagUri) && Util.areEqual(this.adsId, adsConfiguration.adsId);
        }

        public int hashCode() {
            int iHashCode = this.adTagUri.hashCode() * 31;
            Object obj = this.adsId;
            return iHashCode + (obj != null ? obj.hashCode() : 0);
        }

        public AdsConfiguration(Builder builder) {
            this.adTagUri = builder.adTagUri;
            this.adsId = builder.adsId;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {

        @Nullable
        public AdsConfiguration adsConfiguration;
        public ClippingConfiguration.Builder clippingConfiguration;

        @Nullable
        public String customCacheKey;
        public DrmConfiguration.Builder drmConfiguration;
        public LiveConfiguration.Builder liveConfiguration;

        @Nullable
        public String mediaId;

        @Nullable
        public MediaMetadata mediaMetadata;

        @Nullable
        public String mimeType;
        public RequestMetadata requestMetadata;
        public List<StreamKey> streamKeys;
        public ImmutableList<SubtitleConfiguration> subtitleConfigurations;

        @Nullable
        public Object tag;

        @Nullable
        public Uri uri;

        public MediaItem build() {
            PlaybackProperties playbackProperties;
            DrmConfiguration.Builder builder = this.drmConfiguration;
            Assertions.checkState(builder.licenseUri == null || builder.scheme != null);
            Uri uri = this.uri;
            DrmConfiguration drmConfiguration = null;
            if (uri != null) {
                String str = this.mimeType;
                DrmConfiguration.Builder builder2 = this.drmConfiguration;
                if (builder2.scheme != null) {
                    builder2.getClass();
                    drmConfiguration = new DrmConfiguration(builder2);
                }
                playbackProperties = new PlaybackProperties(uri, str, drmConfiguration, this.adsConfiguration, this.streamKeys, this.customCacheKey, this.subtitleConfigurations, this.tag);
            } else {
                playbackProperties = null;
            }
            String str2 = this.mediaId;
            if (str2 == null) {
                str2 = "";
            }
            String str3 = str2;
            ClippingConfiguration.Builder builder3 = this.clippingConfiguration;
            builder3.getClass();
            ClippingProperties clippingProperties = new ClippingProperties(builder3);
            LiveConfiguration.Builder builder4 = this.liveConfiguration;
            builder4.getClass();
            LiveConfiguration liveConfiguration = new LiveConfiguration(builder4);
            MediaMetadata mediaMetadata = this.mediaMetadata;
            if (mediaMetadata == null) {
                mediaMetadata = MediaMetadata.EMPTY;
            }
            return new MediaItem(str3, clippingProperties, playbackProperties, liveConfiguration, mediaMetadata, this.requestMetadata);
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setAdTagUri(@Nullable String str) {
            setAdTagUri(str != null ? Uri.parse(str) : null, null);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setAdsConfiguration(@Nullable AdsConfiguration adsConfiguration) {
            this.adsConfiguration = adsConfiguration;
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setClipEndPositionMs(long j) {
            this.clippingConfiguration.setEndPositionMs(j);
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setClipRelativeToDefaultPosition(boolean z) {
            this.clippingConfiguration.relativeToDefaultPosition = z;
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setClipRelativeToLiveWindow(boolean z) {
            this.clippingConfiguration.relativeToLiveWindow = z;
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setClipStartPositionMs(@IntRange(from = 0) long j) {
            this.clippingConfiguration.setStartPositionMs(j);
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setClipStartsAtKeyFrame(boolean z) {
            this.clippingConfiguration.startsAtKeyFrame = z;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setClippingConfiguration(ClippingConfiguration clippingConfiguration) {
            this.clippingConfiguration = clippingConfiguration.buildUpon();
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setCustomCacheKey(@Nullable String str) {
            this.customCacheKey = str;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setDrmConfiguration(@Nullable DrmConfiguration drmConfiguration) {
            this.drmConfiguration = drmConfiguration != null ? new DrmConfiguration.Builder(drmConfiguration) : new DrmConfiguration.Builder();
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setDrmForceDefaultLicenseUri(boolean z) {
            this.drmConfiguration.forceDefaultLicenseUri = z;
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setDrmKeySetId(@Nullable byte[] bArr) {
            this.drmConfiguration.setKeySetId(bArr);
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setDrmLicenseRequestHeaders(@Nullable Map<String, String> map) {
            DrmConfiguration.Builder builder = this.drmConfiguration;
            if (map == null) {
                map = RegularImmutableMap.EMPTY;
            }
            builder.setLicenseRequestHeaders(map);
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setDrmLicenseUri(@Nullable Uri uri) {
            this.drmConfiguration.licenseUri = uri;
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setDrmMultiSession(boolean z) {
            this.drmConfiguration.multiSession = z;
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setDrmPlayClearContentWithoutKey(boolean z) {
            this.drmConfiguration.playClearContentWithoutKey = z;
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setDrmSessionForClearPeriods(boolean z) {
            this.drmConfiguration.setForceSessionsForAudioAndVideoTracks(z);
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setDrmSessionForClearTypes(@Nullable List<Integer> list) {
            DrmConfiguration.Builder builder = this.drmConfiguration;
            if (list == null) {
                list = ImmutableList.of();
            }
            builder.setForcedSessionTrackTypes(list);
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setDrmUuid(@Nullable UUID uuid) {
            this.drmConfiguration.scheme = uuid;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setLiveConfiguration(LiveConfiguration liveConfiguration) {
            liveConfiguration.getClass();
            this.liveConfiguration = new LiveConfiguration.Builder(liveConfiguration);
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setLiveMaxOffsetMs(long j) {
            this.liveConfiguration.maxOffsetMs = j;
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setLiveMaxPlaybackSpeed(float f) {
            this.liveConfiguration.maxPlaybackSpeed = f;
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setLiveMinOffsetMs(long j) {
            this.liveConfiguration.minOffsetMs = j;
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setLiveMinPlaybackSpeed(float f) {
            this.liveConfiguration.minPlaybackSpeed = f;
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setLiveTargetOffsetMs(long j) {
            this.liveConfiguration.targetOffsetMs = j;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMediaId(String str) {
            str.getClass();
            this.mediaId = str;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMediaMetadata(MediaMetadata mediaMetadata) {
            this.mediaMetadata = mediaMetadata;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMimeType(@Nullable String str) {
            this.mimeType = str;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setRequestMetadata(RequestMetadata requestMetadata) {
            this.requestMetadata = requestMetadata;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setStreamKeys(@Nullable List<StreamKey> list) {
            this.streamKeys = (list == null || list.isEmpty()) ? Collections.EMPTY_LIST : DesugarCollections.unmodifiableList(new ArrayList(list));
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setSubtitleConfigurations(List<SubtitleConfiguration> list) {
            this.subtitleConfigurations = ImmutableList.copyOf((Collection) list);
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setSubtitles(@Nullable List<Subtitle> list) {
            this.subtitleConfigurations = list != null ? ImmutableList.copyOf((Collection) list) : ImmutableList.of();
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setTag(@Nullable Object obj) {
            this.tag = obj;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setUri(@Nullable String str) {
            this.uri = str == null ? null : Uri.parse(str);
            return this;
        }

        public Builder() {
            this.clippingConfiguration = new ClippingConfiguration.Builder();
            this.drmConfiguration = new DrmConfiguration.Builder();
            this.streamKeys = Collections.EMPTY_LIST;
            this.subtitleConfigurations = RegularImmutableList.EMPTY;
            this.liveConfiguration = new LiveConfiguration.Builder();
            this.requestMetadata = RequestMetadata.EMPTY;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setAdTagUri(@Nullable Uri uri) {
            setAdTagUri(uri, null);
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setDrmLicenseUri(@Nullable String str) {
            this.drmConfiguration.setLicenseUri(str);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setUri(@Nullable Uri uri) {
            this.uri = uri;
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setAdTagUri(@Nullable Uri uri, @Nullable Object obj) {
            AdsConfiguration adsConfiguration;
            if (uri != null) {
                AdsConfiguration.Builder builder = new AdsConfiguration.Builder(uri);
                builder.adsId = obj;
                adsConfiguration = new AdsConfiguration(builder);
            } else {
                adsConfiguration = null;
            }
            this.adsConfiguration = adsConfiguration;
            return this;
        }

        public Builder(MediaItem mediaItem) {
            DrmConfiguration.Builder builder;
            this();
            this.clippingConfiguration = mediaItem.clippingConfiguration.buildUpon();
            this.mediaId = mediaItem.mediaId;
            this.mediaMetadata = mediaItem.mediaMetadata;
            LiveConfiguration liveConfiguration = mediaItem.liveConfiguration;
            liveConfiguration.getClass();
            this.liveConfiguration = new LiveConfiguration.Builder(liveConfiguration);
            this.requestMetadata = mediaItem.requestMetadata;
            LocalConfiguration localConfiguration = mediaItem.localConfiguration;
            if (localConfiguration != null) {
                this.customCacheKey = localConfiguration.customCacheKey;
                this.mimeType = localConfiguration.mimeType;
                this.uri = localConfiguration.uri;
                this.streamKeys = localConfiguration.streamKeys;
                this.subtitleConfigurations = localConfiguration.subtitleConfigurations;
                this.tag = localConfiguration.tag;
                DrmConfiguration drmConfiguration = localConfiguration.drmConfiguration;
                if (drmConfiguration != null) {
                    builder = new DrmConfiguration.Builder(drmConfiguration);
                } else {
                    builder = new DrmConfiguration.Builder();
                }
                this.drmConfiguration = builder;
                this.adsConfiguration = localConfiguration.adsConfiguration;
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ClippingConfiguration implements Bundleable {
        public final long endPositionMs;
        public final boolean relativeToDefaultPosition;
        public final boolean relativeToLiveWindow;

        @IntRange(from = 0)
        public final long startPositionMs;
        public final boolean startsAtKeyFrame;
        public static final ClippingConfiguration UNSET = new ClippingProperties(new Builder());
        public static final String FIELD_START_POSITION_MS = Util.intToStringMaxRadix(0);
        public static final String FIELD_END_POSITION_MS = Integer.toString(1, 36);
        public static final String FIELD_RELATIVE_TO_LIVE_WINDOW = Integer.toString(2, 36);
        public static final String FIELD_RELATIVE_TO_DEFAULT_POSITION = Integer.toString(3, 36);
        public static final String FIELD_STARTS_AT_KEY_FRAME = Integer.toString(4, 36);
        public static final Bundleable.Creator<ClippingProperties> CREATOR = new MediaItem$ClippingConfiguration$$ExternalSyntheticLambda0();

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Builder {
            public long endPositionMs;
            public boolean relativeToDefaultPosition;
            public boolean relativeToLiveWindow;
            public long startPositionMs;
            public boolean startsAtKeyFrame;

            public ClippingConfiguration build() {
                return new ClippingProperties(this);
            }

            @Deprecated
            public ClippingProperties buildClippingProperties() {
                return new ClippingProperties(this);
            }

            @CanIgnoreReturnValue
            public Builder setEndPositionMs(long j) {
                Assertions.checkArgument(j == Long.MIN_VALUE || j >= 0);
                this.endPositionMs = j;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setRelativeToDefaultPosition(boolean z) {
                this.relativeToDefaultPosition = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setRelativeToLiveWindow(boolean z) {
                this.relativeToLiveWindow = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setStartPositionMs(@IntRange(from = 0) long j) {
                Assertions.checkArgument(j >= 0);
                this.startPositionMs = j;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setStartsAtKeyFrame(boolean z) {
                this.startsAtKeyFrame = z;
                return this;
            }

            public Builder() {
                this.endPositionMs = Long.MIN_VALUE;
            }

            public Builder(ClippingConfiguration clippingConfiguration) {
                this.startPositionMs = clippingConfiguration.startPositionMs;
                this.endPositionMs = clippingConfiguration.endPositionMs;
                this.relativeToLiveWindow = clippingConfiguration.relativeToLiveWindow;
                this.relativeToDefaultPosition = clippingConfiguration.relativeToDefaultPosition;
                this.startsAtKeyFrame = clippingConfiguration.startsAtKeyFrame;
            }
        }

        /* JADX INFO: renamed from: $r8$lambda$aJWhmzO8-cRmnRmTwTOr0EZQejY, reason: not valid java name */
        public static ClippingProperties m388$r8$lambda$aJWhmzO8cRmnRmTwTOr0EZQejY(Bundle bundle) {
            Builder builder = new Builder();
            String str = FIELD_START_POSITION_MS;
            ClippingConfiguration clippingConfiguration = UNSET;
            builder.setStartPositionMs(bundle.getLong(str, clippingConfiguration.startPositionMs));
            builder.setEndPositionMs(bundle.getLong(FIELD_END_POSITION_MS, clippingConfiguration.endPositionMs));
            builder.relativeToLiveWindow = bundle.getBoolean(FIELD_RELATIVE_TO_LIVE_WINDOW, clippingConfiguration.relativeToLiveWindow);
            builder.relativeToDefaultPosition = bundle.getBoolean(FIELD_RELATIVE_TO_DEFAULT_POSITION, clippingConfiguration.relativeToDefaultPosition);
            builder.startsAtKeyFrame = bundle.getBoolean(FIELD_STARTS_AT_KEY_FRAME, clippingConfiguration.startsAtKeyFrame);
            return new ClippingProperties(builder);
        }

        public Builder buildUpon() {
            return new Builder(this);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ClippingConfiguration)) {
                return false;
            }
            ClippingConfiguration clippingConfiguration = (ClippingConfiguration) obj;
            return this.startPositionMs == clippingConfiguration.startPositionMs && this.endPositionMs == clippingConfiguration.endPositionMs && this.relativeToLiveWindow == clippingConfiguration.relativeToLiveWindow && this.relativeToDefaultPosition == clippingConfiguration.relativeToDefaultPosition && this.startsAtKeyFrame == clippingConfiguration.startsAtKeyFrame;
        }

        public int hashCode() {
            long j = this.startPositionMs;
            int i = ((int) (j ^ (j >>> 32))) * 31;
            long j2 = this.endPositionMs;
            return ((((((i + ((int) (j2 ^ (j2 >>> 32)))) * 31) + (this.relativeToLiveWindow ? 1 : 0)) * 31) + (this.relativeToDefaultPosition ? 1 : 0)) * 31) + (this.startsAtKeyFrame ? 1 : 0);
        }

        @Override // com.google.android.exoplayer2.Bundleable
        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            long j = this.startPositionMs;
            ClippingConfiguration clippingConfiguration = UNSET;
            if (j != clippingConfiguration.startPositionMs) {
                bundle.putLong(FIELD_START_POSITION_MS, j);
            }
            long j2 = this.endPositionMs;
            if (j2 != clippingConfiguration.endPositionMs) {
                bundle.putLong(FIELD_END_POSITION_MS, j2);
            }
            boolean z = this.relativeToLiveWindow;
            if (z != clippingConfiguration.relativeToLiveWindow) {
                bundle.putBoolean(FIELD_RELATIVE_TO_LIVE_WINDOW, z);
            }
            boolean z2 = this.relativeToDefaultPosition;
            if (z2 != clippingConfiguration.relativeToDefaultPosition) {
                bundle.putBoolean(FIELD_RELATIVE_TO_DEFAULT_POSITION, z2);
            }
            boolean z3 = this.startsAtKeyFrame;
            if (z3 != clippingConfiguration.startsAtKeyFrame) {
                bundle.putBoolean(FIELD_STARTS_AT_KEY_FRAME, z3);
            }
            return bundle;
        }

        public ClippingConfiguration(Builder builder) {
            this.startPositionMs = builder.startPositionMs;
            this.endPositionMs = builder.endPositionMs;
            this.relativeToLiveWindow = builder.relativeToLiveWindow;
            this.relativeToDefaultPosition = builder.relativeToDefaultPosition;
            this.startsAtKeyFrame = builder.startsAtKeyFrame;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Deprecated
    public static final class ClippingProperties extends ClippingConfiguration {
        public static final ClippingProperties UNSET = new ClippingProperties(new ClippingConfiguration.Builder());

        public ClippingProperties(ClippingConfiguration.Builder builder) {
            super(builder);
        }

        public ClippingProperties(ClippingConfiguration.Builder builder, AnonymousClass1 anonymousClass1) {
            super(builder);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DrmConfiguration {
        public final boolean forceDefaultLicenseUri;
        public final ImmutableList<Integer> forcedSessionTrackTypes;

        @Nullable
        public final byte[] keySetId;
        public final ImmutableMap<String, String> licenseRequestHeaders;

        @Nullable
        public final Uri licenseUri;
        public final boolean multiSession;
        public final boolean playClearContentWithoutKey;

        @Deprecated
        public final ImmutableMap<String, String> requestHeaders;
        public final UUID scheme;

        @Deprecated
        public final ImmutableList<Integer> sessionForClearTypes;

        @Deprecated
        public final UUID uuid;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Builder {
            public boolean forceDefaultLicenseUri;
            public ImmutableList<Integer> forcedSessionTrackTypes;

            @Nullable
            public byte[] keySetId;
            public ImmutableMap<String, String> licenseRequestHeaders;

            @Nullable
            public Uri licenseUri;
            public boolean multiSession;
            public boolean playClearContentWithoutKey;

            @Nullable
            public UUID scheme;

            public static Builder access$100(Builder builder, UUID uuid) {
                builder.scheme = uuid;
                return builder;
            }

            public DrmConfiguration build() {
                return new DrmConfiguration(this);
            }

            @CanIgnoreReturnValue
            @InlineMe(replacement = "this.setForceSessionsForAudioAndVideoTracks(forceSessionsForAudioAndVideoTracks)")
            @Deprecated
            public Builder forceSessionsForAudioAndVideoTracks(boolean z) {
                setForceSessionsForAudioAndVideoTracks(z);
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setForceDefaultLicenseUri(boolean z) {
                this.forceDefaultLicenseUri = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setForceSessionsForAudioAndVideoTracks(boolean z) {
                setForcedSessionTrackTypes(z ? ImmutableList.of(2, 1) : ImmutableList.of());
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setForcedSessionTrackTypes(List<Integer> list) {
                this.forcedSessionTrackTypes = ImmutableList.copyOf((Collection) list);
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setKeySetId(@Nullable byte[] bArr) {
                this.keySetId = bArr != null ? Arrays.copyOf(bArr, bArr.length) : null;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setLicenseRequestHeaders(Map<String, String> map) {
                this.licenseRequestHeaders = ImmutableMap.copyOf((Map) map);
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setLicenseUri(@Nullable Uri uri) {
                this.licenseUri = uri;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setMultiSession(boolean z) {
                this.multiSession = z;
                return this;
            }

            @CanIgnoreReturnValue
            @Deprecated
            public final Builder setNullableScheme(@Nullable UUID uuid) {
                this.scheme = uuid;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setPlayClearContentWithoutKey(boolean z) {
                this.playClearContentWithoutKey = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setScheme(UUID uuid) {
                this.scheme = uuid;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setLicenseUri(@Nullable String str) {
                this.licenseUri = str == null ? null : Uri.parse(str);
                return this;
            }

            public Builder(UUID uuid) {
                this.scheme = uuid;
                this.licenseRequestHeaders = RegularImmutableMap.EMPTY;
                this.forcedSessionTrackTypes = ImmutableList.of();
            }

            @Deprecated
            public Builder() {
                this.licenseRequestHeaders = RegularImmutableMap.EMPTY;
                this.forcedSessionTrackTypes = ImmutableList.of();
            }

            public Builder(DrmConfiguration drmConfiguration) {
                this.scheme = drmConfiguration.scheme;
                this.licenseUri = drmConfiguration.licenseUri;
                this.licenseRequestHeaders = drmConfiguration.licenseRequestHeaders;
                this.multiSession = drmConfiguration.multiSession;
                this.playClearContentWithoutKey = drmConfiguration.playClearContentWithoutKey;
                this.forceDefaultLicenseUri = drmConfiguration.forceDefaultLicenseUri;
                this.forcedSessionTrackTypes = drmConfiguration.forcedSessionTrackTypes;
                this.keySetId = drmConfiguration.keySetId;
            }
        }

        public Builder buildUpon() {
            return new Builder(this);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DrmConfiguration)) {
                return false;
            }
            DrmConfiguration drmConfiguration = (DrmConfiguration) obj;
            return this.scheme.equals(drmConfiguration.scheme) && Util.areEqual(this.licenseUri, drmConfiguration.licenseUri) && Util.areEqual(this.licenseRequestHeaders, drmConfiguration.licenseRequestHeaders) && this.multiSession == drmConfiguration.multiSession && this.forceDefaultLicenseUri == drmConfiguration.forceDefaultLicenseUri && this.playClearContentWithoutKey == drmConfiguration.playClearContentWithoutKey && this.forcedSessionTrackTypes.equals(drmConfiguration.forcedSessionTrackTypes) && Arrays.equals(this.keySetId, drmConfiguration.keySetId);
        }

        @Nullable
        public byte[] getKeySetId() {
            byte[] bArr = this.keySetId;
            if (bArr != null) {
                return Arrays.copyOf(bArr, bArr.length);
            }
            return null;
        }

        public int hashCode() {
            int iHashCode = this.scheme.hashCode() * 31;
            Uri uri = this.licenseUri;
            return Arrays.hashCode(this.keySetId) + ((this.forcedSessionTrackTypes.hashCode() + ((((((((this.licenseRequestHeaders.hashCode() + ((iHashCode + (uri != null ? uri.hashCode() : 0)) * 31)) * 31) + (this.multiSession ? 1 : 0)) * 31) + (this.forceDefaultLicenseUri ? 1 : 0)) * 31) + (this.playClearContentWithoutKey ? 1 : 0)) * 31)) * 31);
        }

        public DrmConfiguration(Builder builder) {
            Assertions.checkState((builder.forceDefaultLicenseUri && builder.licenseUri == null) ? false : true);
            UUID uuid = builder.scheme;
            uuid.getClass();
            this.scheme = uuid;
            this.uuid = uuid;
            this.licenseUri = builder.licenseUri;
            ImmutableMap<String, String> immutableMap = builder.licenseRequestHeaders;
            this.requestHeaders = immutableMap;
            this.licenseRequestHeaders = immutableMap;
            this.multiSession = builder.multiSession;
            this.forceDefaultLicenseUri = builder.forceDefaultLicenseUri;
            this.playClearContentWithoutKey = builder.playClearContentWithoutKey;
            ImmutableList<Integer> immutableList = builder.forcedSessionTrackTypes;
            this.sessionForClearTypes = immutableList;
            this.forcedSessionTrackTypes = immutableList;
            byte[] bArr = builder.keySetId;
            this.keySetId = bArr != null ? Arrays.copyOf(bArr, bArr.length) : null;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class LiveConfiguration implements Bundleable {
        public final long maxOffsetMs;
        public final float maxPlaybackSpeed;
        public final long minOffsetMs;
        public final float minPlaybackSpeed;
        public final long targetOffsetMs;
        public static final LiveConfiguration UNSET = new LiveConfiguration(new Builder());
        public static final String FIELD_TARGET_OFFSET_MS = Util.intToStringMaxRadix(0);
        public static final String FIELD_MIN_OFFSET_MS = Integer.toString(1, 36);
        public static final String FIELD_MAX_OFFSET_MS = Integer.toString(2, 36);
        public static final String FIELD_MIN_PLAYBACK_SPEED = Integer.toString(3, 36);
        public static final String FIELD_MAX_PLAYBACK_SPEED = Integer.toString(4, 36);
        public static final Bundleable.Creator<LiveConfiguration> CREATOR = new MediaItem$LiveConfiguration$$ExternalSyntheticLambda0();

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Builder {
            public long maxOffsetMs;
            public float maxPlaybackSpeed;
            public long minOffsetMs;
            public float minPlaybackSpeed;
            public long targetOffsetMs;

            public LiveConfiguration build() {
                return new LiveConfiguration(this);
            }

            @CanIgnoreReturnValue
            public Builder setMaxOffsetMs(long j) {
                this.maxOffsetMs = j;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setMaxPlaybackSpeed(float f) {
                this.maxPlaybackSpeed = f;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setMinOffsetMs(long j) {
                this.minOffsetMs = j;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setMinPlaybackSpeed(float f) {
                this.minPlaybackSpeed = f;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setTargetOffsetMs(long j) {
                this.targetOffsetMs = j;
                return this;
            }

            public Builder() {
                this.targetOffsetMs = -9223372036854775807L;
                this.minOffsetMs = -9223372036854775807L;
                this.maxOffsetMs = -9223372036854775807L;
                this.minPlaybackSpeed = -3.4028235E38f;
                this.maxPlaybackSpeed = -3.4028235E38f;
            }

            public Builder(LiveConfiguration liveConfiguration) {
                this.targetOffsetMs = liveConfiguration.targetOffsetMs;
                this.minOffsetMs = liveConfiguration.minOffsetMs;
                this.maxOffsetMs = liveConfiguration.maxOffsetMs;
                this.minPlaybackSpeed = liveConfiguration.minPlaybackSpeed;
                this.maxPlaybackSpeed = liveConfiguration.maxPlaybackSpeed;
            }
        }

        public static /* synthetic */ LiveConfiguration $r8$lambda$SLcz1FXubVQ03pQY35SwlBIwYYM(Bundle bundle) {
            String str = FIELD_TARGET_OFFSET_MS;
            LiveConfiguration liveConfiguration = UNSET;
            return new LiveConfiguration(bundle.getLong(str, liveConfiguration.targetOffsetMs), bundle.getLong(FIELD_MIN_OFFSET_MS, liveConfiguration.minOffsetMs), bundle.getLong(FIELD_MAX_OFFSET_MS, liveConfiguration.maxOffsetMs), bundle.getFloat(FIELD_MIN_PLAYBACK_SPEED, liveConfiguration.minPlaybackSpeed), bundle.getFloat(FIELD_MAX_PLAYBACK_SPEED, liveConfiguration.maxPlaybackSpeed));
        }

        public Builder buildUpon() {
            return new Builder(this);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LiveConfiguration)) {
                return false;
            }
            LiveConfiguration liveConfiguration = (LiveConfiguration) obj;
            return this.targetOffsetMs == liveConfiguration.targetOffsetMs && this.minOffsetMs == liveConfiguration.minOffsetMs && this.maxOffsetMs == liveConfiguration.maxOffsetMs && this.minPlaybackSpeed == liveConfiguration.minPlaybackSpeed && this.maxPlaybackSpeed == liveConfiguration.maxPlaybackSpeed;
        }

        public int hashCode() {
            long j = this.targetOffsetMs;
            long j2 = this.minOffsetMs;
            int i = ((((int) (j ^ (j >>> 32))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31;
            long j3 = this.maxOffsetMs;
            int i2 = (i + ((int) (j3 ^ (j3 >>> 32)))) * 31;
            float f = this.minPlaybackSpeed;
            int iFloatToIntBits = (i2 + (f != 0.0f ? Float.floatToIntBits(f) : 0)) * 31;
            float f2 = this.maxPlaybackSpeed;
            return iFloatToIntBits + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0);
        }

        @Override // com.google.android.exoplayer2.Bundleable
        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            long j = this.targetOffsetMs;
            LiveConfiguration liveConfiguration = UNSET;
            if (j != liveConfiguration.targetOffsetMs) {
                bundle.putLong(FIELD_TARGET_OFFSET_MS, j);
            }
            long j2 = this.minOffsetMs;
            if (j2 != liveConfiguration.minOffsetMs) {
                bundle.putLong(FIELD_MIN_OFFSET_MS, j2);
            }
            long j3 = this.maxOffsetMs;
            if (j3 != liveConfiguration.maxOffsetMs) {
                bundle.putLong(FIELD_MAX_OFFSET_MS, j3);
            }
            float f = this.minPlaybackSpeed;
            if (f != liveConfiguration.minPlaybackSpeed) {
                bundle.putFloat(FIELD_MIN_PLAYBACK_SPEED, f);
            }
            float f2 = this.maxPlaybackSpeed;
            if (f2 != liveConfiguration.maxPlaybackSpeed) {
                bundle.putFloat(FIELD_MAX_PLAYBACK_SPEED, f2);
            }
            return bundle;
        }

        public LiveConfiguration(Builder builder) {
            this(builder.targetOffsetMs, builder.minOffsetMs, builder.maxOffsetMs, builder.minPlaybackSpeed, builder.maxPlaybackSpeed);
        }

        @Deprecated
        public LiveConfiguration(long j, long j2, long j3, float f, float f2) {
            this.targetOffsetMs = j;
            this.minOffsetMs = j2;
            this.maxOffsetMs = j3;
            this.minPlaybackSpeed = f;
            this.maxPlaybackSpeed = f2;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class LocalConfiguration {

        @Nullable
        public final AdsConfiguration adsConfiguration;

        @Nullable
        public final String customCacheKey;

        @Nullable
        public final DrmConfiguration drmConfiguration;

        @Nullable
        public final String mimeType;
        public final List<StreamKey> streamKeys;
        public final ImmutableList<SubtitleConfiguration> subtitleConfigurations;

        @Deprecated
        public final List<Subtitle> subtitles;

        @Nullable
        public final Object tag;
        public final Uri uri;

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LocalConfiguration)) {
                return false;
            }
            LocalConfiguration localConfiguration = (LocalConfiguration) obj;
            return this.uri.equals(localConfiguration.uri) && Util.areEqual(this.mimeType, localConfiguration.mimeType) && Util.areEqual(this.drmConfiguration, localConfiguration.drmConfiguration) && Util.areEqual(this.adsConfiguration, localConfiguration.adsConfiguration) && this.streamKeys.equals(localConfiguration.streamKeys) && Util.areEqual(this.customCacheKey, localConfiguration.customCacheKey) && this.subtitleConfigurations.equals(localConfiguration.subtitleConfigurations) && Util.areEqual(this.tag, localConfiguration.tag);
        }

        public int hashCode() {
            int iHashCode = this.uri.hashCode() * 31;
            String str = this.mimeType;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            DrmConfiguration drmConfiguration = this.drmConfiguration;
            int iHashCode3 = (iHashCode2 + (drmConfiguration == null ? 0 : drmConfiguration.hashCode())) * 31;
            AdsConfiguration adsConfiguration = this.adsConfiguration;
            int iHashCode4 = (this.streamKeys.hashCode() + ((iHashCode3 + (adsConfiguration == null ? 0 : adsConfiguration.hashCode())) * 31)) * 31;
            String str2 = this.customCacheKey;
            int iHashCode5 = (this.subtitleConfigurations.hashCode() + ((iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31)) * 31;
            Object obj = this.tag;
            return iHashCode5 + (obj != null ? obj.hashCode() : 0);
        }

        public LocalConfiguration(Uri uri, @Nullable String str, @Nullable DrmConfiguration drmConfiguration, @Nullable AdsConfiguration adsConfiguration, List<StreamKey> list, @Nullable String str2, ImmutableList<SubtitleConfiguration> immutableList, @Nullable Object obj) {
            this.uri = uri;
            this.mimeType = str;
            this.drmConfiguration = drmConfiguration;
            this.adsConfiguration = adsConfiguration;
            this.streamKeys = list;
            this.customCacheKey = str2;
            this.subtitleConfigurations = immutableList;
            ImmutableList.Builder builder = ImmutableList.builder();
            for (int i = 0; i < immutableList.size(); i++) {
                builder.add(new Subtitle(immutableList.get(i).buildUpon()));
            }
            this.subtitles = builder.build();
            this.tag = obj;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Deprecated
    public static final class PlaybackProperties extends LocalConfiguration {
        public PlaybackProperties(Uri uri, @Nullable String str, @Nullable DrmConfiguration drmConfiguration, @Nullable AdsConfiguration adsConfiguration, List<StreamKey> list, @Nullable String str2, ImmutableList<SubtitleConfiguration> immutableList, @Nullable Object obj) {
            super(uri, str, drmConfiguration, adsConfiguration, list, str2, immutableList, obj);
        }

        public PlaybackProperties(Uri uri, String str, DrmConfiguration drmConfiguration, AdsConfiguration adsConfiguration, List list, String str2, ImmutableList immutableList, Object obj, AnonymousClass1 anonymousClass1) {
            super(uri, str, drmConfiguration, adsConfiguration, list, str2, immutableList, obj);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class RequestMetadata implements Bundleable {

        @Nullable
        public final Bundle extras;

        @Nullable
        public final Uri mediaUri;

        @Nullable
        public final String searchQuery;
        public static final RequestMetadata EMPTY = new RequestMetadata(new Builder());
        public static final String FIELD_MEDIA_URI = Util.intToStringMaxRadix(0);
        public static final String FIELD_SEARCH_QUERY = Integer.toString(1, 36);
        public static final String FIELD_EXTRAS = Integer.toString(2, 36);
        public static final Bundleable.Creator<RequestMetadata> CREATOR = new MediaItem$RequestMetadata$$ExternalSyntheticLambda0();

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Builder {

            @Nullable
            public Bundle extras;

            @Nullable
            public Uri mediaUri;

            @Nullable
            public String searchQuery;

            public RequestMetadata build() {
                return new RequestMetadata(this);
            }

            @CanIgnoreReturnValue
            public Builder setExtras(@Nullable Bundle bundle) {
                this.extras = bundle;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setMediaUri(@Nullable Uri uri) {
                this.mediaUri = uri;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setSearchQuery(@Nullable String str) {
                this.searchQuery = str;
                return this;
            }

            public Builder() {
            }

            public Builder(RequestMetadata requestMetadata) {
                this.mediaUri = requestMetadata.mediaUri;
                this.searchQuery = requestMetadata.searchQuery;
                this.extras = requestMetadata.extras;
            }
        }

        /* JADX INFO: renamed from: $r8$lambda$_-kiwWpfj1qO-PokPkATPl1ogjo, reason: not valid java name */
        public static RequestMetadata m389$r8$lambda$_kiwWpfj1qOPokPkATPl1ogjo(Bundle bundle) {
            Builder builder = new Builder();
            builder.mediaUri = (Uri) bundle.getParcelable(FIELD_MEDIA_URI);
            builder.searchQuery = bundle.getString(FIELD_SEARCH_QUERY);
            builder.extras = bundle.getBundle(FIELD_EXTRAS);
            return new RequestMetadata(builder);
        }

        public Builder buildUpon() {
            return new Builder(this);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof RequestMetadata)) {
                return false;
            }
            RequestMetadata requestMetadata = (RequestMetadata) obj;
            return Util.areEqual(this.mediaUri, requestMetadata.mediaUri) && Util.areEqual(this.searchQuery, requestMetadata.searchQuery);
        }

        public int hashCode() {
            Uri uri = this.mediaUri;
            int iHashCode = (uri == null ? 0 : uri.hashCode()) * 31;
            String str = this.searchQuery;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }

        @Override // com.google.android.exoplayer2.Bundleable
        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            Uri uri = this.mediaUri;
            if (uri != null) {
                bundle.putParcelable(FIELD_MEDIA_URI, uri);
            }
            String str = this.searchQuery;
            if (str != null) {
                bundle.putString(FIELD_SEARCH_QUERY, str);
            }
            Bundle bundle2 = this.extras;
            if (bundle2 != null) {
                bundle.putBundle(FIELD_EXTRAS, bundle2);
            }
            return bundle;
        }

        public RequestMetadata(Builder builder) {
            this.mediaUri = builder.mediaUri;
            this.searchQuery = builder.searchQuery;
            this.extras = builder.extras;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Deprecated
    public static final class Subtitle extends SubtitleConfiguration {
        @Deprecated
        public Subtitle(Uri uri, String str, @Nullable String str2, int i) {
            super(uri, str, str2, i, 0, null, null);
        }

        @Deprecated
        public Subtitle(Uri uri, String str, @Nullable String str2, int i, int i2, @Nullable String str3) {
            super(uri, str, str2, i, i2, str3, null);
        }

        public Subtitle(SubtitleConfiguration.Builder builder) {
            super(builder);
        }

        public Subtitle(SubtitleConfiguration.Builder builder, AnonymousClass1 anonymousClass1) {
            super(builder);
        }

        @Deprecated
        public Subtitle(Uri uri, String str, @Nullable String str2) {
            this(uri, str, str2, 0);
        }
    }

    public static MediaItem fromBundle(Bundle bundle) {
        String string = bundle.getString(FIELD_MEDIA_ID, "");
        string.getClass();
        Bundle bundle2 = bundle.getBundle(FIELD_LIVE_CONFIGURATION);
        LiveConfiguration liveConfiguration = bundle2 == null ? LiveConfiguration.UNSET : (LiveConfiguration) LiveConfiguration.CREATOR.fromBundle(bundle2);
        Bundle bundle3 = bundle.getBundle(FIELD_MEDIA_METADATA);
        MediaMetadata mediaMetadata = bundle3 == null ? MediaMetadata.EMPTY : (MediaMetadata) MediaMetadata.CREATOR.fromBundle(bundle3);
        Bundle bundle4 = bundle.getBundle(FIELD_CLIPPING_PROPERTIES);
        ClippingProperties clippingProperties = bundle4 == null ? ClippingProperties.UNSET : (ClippingProperties) ClippingConfiguration.CREATOR.fromBundle(bundle4);
        Bundle bundle5 = bundle.getBundle(FIELD_REQUEST_METADATA);
        return new MediaItem(string, clippingProperties, null, liveConfiguration, mediaMetadata, bundle5 == null ? RequestMetadata.EMPTY : (RequestMetadata) RequestMetadata.CREATOR.fromBundle(bundle5));
    }

    public static MediaItem fromUri(String str) {
        Builder builder = new Builder();
        builder.setUri(str);
        return builder.build();
    }

    public Builder buildUpon() {
        return new Builder(this);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaItem)) {
            return false;
        }
        MediaItem mediaItem = (MediaItem) obj;
        return Util.areEqual(this.mediaId, mediaItem.mediaId) && this.clippingConfiguration.equals(mediaItem.clippingConfiguration) && Util.areEqual(this.localConfiguration, mediaItem.localConfiguration) && Util.areEqual(this.liveConfiguration, mediaItem.liveConfiguration) && Util.areEqual(this.mediaMetadata, mediaItem.mediaMetadata) && Util.areEqual(this.requestMetadata, mediaItem.requestMetadata);
    }

    public int hashCode() {
        int iHashCode = this.mediaId.hashCode() * 31;
        LocalConfiguration localConfiguration = this.localConfiguration;
        return this.requestMetadata.hashCode() + ((this.mediaMetadata.hashCode() + ((this.clippingConfiguration.hashCode() + ((this.liveConfiguration.hashCode() + ((iHashCode + (localConfiguration != null ? localConfiguration.hashCode() : 0)) * 31)) * 31)) * 31)) * 31);
    }

    @Override // com.google.android.exoplayer2.Bundleable
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        if (!this.mediaId.equals("")) {
            bundle.putString(FIELD_MEDIA_ID, this.mediaId);
        }
        if (!this.liveConfiguration.equals(LiveConfiguration.UNSET)) {
            bundle.putBundle(FIELD_LIVE_CONFIGURATION, this.liveConfiguration.toBundle());
        }
        if (!this.mediaMetadata.equals(MediaMetadata.EMPTY)) {
            bundle.putBundle(FIELD_MEDIA_METADATA, this.mediaMetadata.toBundle());
        }
        if (!this.clippingConfiguration.equals(ClippingConfiguration.UNSET)) {
            bundle.putBundle(FIELD_CLIPPING_PROPERTIES, this.clippingConfiguration.toBundle());
        }
        if (!this.requestMetadata.equals(RequestMetadata.EMPTY)) {
            bundle.putBundle(FIELD_REQUEST_METADATA, this.requestMetadata.toBundle());
        }
        return bundle;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SubtitleConfiguration {

        @Nullable
        public final String id;

        @Nullable
        public final String label;

        @Nullable
        public final String language;

        @Nullable
        public final String mimeType;
        public final int roleFlags;
        public final int selectionFlags;
        public final Uri uri;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Builder {

            @Nullable
            public String id;

            @Nullable
            public String label;

            @Nullable
            public String language;

            @Nullable
            public String mimeType;
            public int roleFlags;
            public int selectionFlags;
            public Uri uri;

            public static Subtitle access$1800(Builder builder) {
                builder.getClass();
                return new Subtitle(builder);
            }

            public SubtitleConfiguration build() {
                return new SubtitleConfiguration(this);
            }

            public final Subtitle buildSubtitle() {
                return new Subtitle(this);
            }

            @CanIgnoreReturnValue
            public Builder setId(@Nullable String str) {
                this.id = str;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setLabel(@Nullable String str) {
                this.label = str;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setLanguage(@Nullable String str) {
                this.language = str;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setMimeType(@Nullable String str) {
                this.mimeType = str;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setRoleFlags(int i) {
                this.roleFlags = i;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setSelectionFlags(int i) {
                this.selectionFlags = i;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setUri(Uri uri) {
                this.uri = uri;
                return this;
            }

            public Builder(Uri uri) {
                this.uri = uri;
            }

            public Builder(SubtitleConfiguration subtitleConfiguration) {
                this.uri = subtitleConfiguration.uri;
                this.mimeType = subtitleConfiguration.mimeType;
                this.language = subtitleConfiguration.language;
                this.selectionFlags = subtitleConfiguration.selectionFlags;
                this.roleFlags = subtitleConfiguration.roleFlags;
                this.label = subtitleConfiguration.label;
                this.id = subtitleConfiguration.id;
            }
        }

        public Builder buildUpon() {
            return new Builder(this);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SubtitleConfiguration)) {
                return false;
            }
            SubtitleConfiguration subtitleConfiguration = (SubtitleConfiguration) obj;
            return this.uri.equals(subtitleConfiguration.uri) && Util.areEqual(this.mimeType, subtitleConfiguration.mimeType) && Util.areEqual(this.language, subtitleConfiguration.language) && this.selectionFlags == subtitleConfiguration.selectionFlags && this.roleFlags == subtitleConfiguration.roleFlags && Util.areEqual(this.label, subtitleConfiguration.label) && Util.areEqual(this.id, subtitleConfiguration.id);
        }

        public int hashCode() {
            int iHashCode = this.uri.hashCode() * 31;
            String str = this.mimeType;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.language;
            int iHashCode3 = (((((iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.selectionFlags) * 31) + this.roleFlags) * 31;
            String str3 = this.label;
            int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.id;
            return iHashCode4 + (str4 != null ? str4.hashCode() : 0);
        }

        public SubtitleConfiguration(Uri uri, String str, @Nullable String str2, int i, int i2, @Nullable String str3, @Nullable String str4) {
            this.uri = uri;
            this.mimeType = str;
            this.language = str2;
            this.selectionFlags = i;
            this.roleFlags = i2;
            this.label = str3;
            this.id = str4;
        }

        public SubtitleConfiguration(Builder builder) {
            this.uri = builder.uri;
            this.mimeType = builder.mimeType;
            this.language = builder.language;
            this.selectionFlags = builder.selectionFlags;
            this.roleFlags = builder.roleFlags;
            this.label = builder.label;
            this.id = builder.id;
        }
    }

    public MediaItem(String str, ClippingProperties clippingProperties, @Nullable PlaybackProperties playbackProperties, LiveConfiguration liveConfiguration, MediaMetadata mediaMetadata, RequestMetadata requestMetadata) {
        this.mediaId = str;
        this.localConfiguration = playbackProperties;
        this.playbackProperties = playbackProperties;
        this.liveConfiguration = liveConfiguration;
        this.mediaMetadata = mediaMetadata;
        this.clippingConfiguration = clippingProperties;
        this.clippingProperties = clippingProperties;
        this.requestMetadata = requestMetadata;
    }

    public static MediaItem fromUri(Uri uri) {
        Builder builder = new Builder();
        builder.uri = uri;
        return builder.build();
    }
}
