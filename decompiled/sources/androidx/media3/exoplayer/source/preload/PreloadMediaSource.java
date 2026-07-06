package androidx.media3.exoplayer.source.preload;

import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import androidx.annotation.Nullable;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.source.WrappingMediaSource;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class PreloadMediaSource extends WrappingMediaSource {
    public static final String TAG = "PreloadMediaSource";
    public final Allocator allocator;
    public final BandwidthMeter bandwidthMeter;

    @Nullable
    public Pair<PreloadMediaPeriod, MediaSource.MediaPeriodId> playingPreloadedMediaPeriodAndId;
    public boolean preloadCalled;
    public final PreloadControl preloadControl;
    public final Handler preloadHandler;

    @Nullable
    public Pair<PreloadMediaPeriod, MediaPeriodKey> preloadingMediaPeriodAndKey;
    public boolean prepareChildSourceCalled;
    public final RendererCapabilities[] rendererCapabilities;
    public long startPositionUs;

    @Nullable
    public Timeline timeline;
    public final TrackSelector trackSelector;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Factory implements MediaSource.Factory {
        public final Allocator allocator;
        public final BandwidthMeter bandwidthMeter;
        public final MediaSource.Factory mediaSourceFactory;
        public final PreloadControl preloadControl;
        public final Looper preloadLooper;
        public final RendererCapabilities[] rendererCapabilities;
        public final TrackSelector trackSelector;

        public Factory(MediaSource.Factory factory, PreloadControl preloadControl, TrackSelector trackSelector, BandwidthMeter bandwidthMeter, RendererCapabilities[] rendererCapabilitiesArr, Allocator allocator, Looper looper) {
            this.mediaSourceFactory = factory;
            this.preloadControl = preloadControl;
            this.trackSelector = trackSelector;
            this.bandwidthMeter = bandwidthMeter;
            this.rendererCapabilities = (RendererCapabilities[]) Arrays.copyOf(rendererCapabilitiesArr, rendererCapabilitiesArr.length);
            this.allocator = allocator;
            this.preloadLooper = looper;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public int[] getSupportedTypes() {
            return this.mediaSourceFactory.getSupportedTypes();
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public MediaSource.Factory setCmcdConfigurationFactory(CmcdConfiguration.Factory factory) {
            this.mediaSourceFactory.setCmcdConfigurationFactory(factory);
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public MediaSource.Factory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider) {
            this.mediaSourceFactory.setDrmSessionManagerProvider(drmSessionManagerProvider);
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public MediaSource.Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
            this.mediaSourceFactory.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy);
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public PreloadMediaSource createMediaSource(MediaItem mediaItem) {
            return new PreloadMediaSource(this.mediaSourceFactory.createMediaSource(mediaItem), this.preloadControl, this.trackSelector, this.bandwidthMeter, this.rendererCapabilities, this.allocator, this.preloadLooper);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public Factory setCmcdConfigurationFactory(CmcdConfiguration.Factory factory) {
            this.mediaSourceFactory.setCmcdConfigurationFactory(factory);
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public Factory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider) {
            this.mediaSourceFactory.setDrmSessionManagerProvider(drmSessionManagerProvider);
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
            this.mediaSourceFactory.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy);
            return this;
        }

        public PreloadMediaSource createMediaSource(MediaSource mediaSource) {
            return new PreloadMediaSource(mediaSource, this.preloadControl, this.trackSelector, this.bandwidthMeter, this.rendererCapabilities, this.allocator, this.preloadLooper);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public MediaSource.Factory experimentalParseSubtitlesDuringExtraction(boolean z) {
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public MediaSource.Factory setSubtitleParserFactory(SubtitleParser.Factory factory) {
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MediaPeriodKey {
        public final MediaSource.MediaPeriodId mediaPeriodId;
        public final Long startPositionUs;

        public MediaPeriodKey(MediaSource.MediaPeriodId mediaPeriodId, long j) {
            this.mediaPeriodId = mediaPeriodId;
            this.startPositionUs = Long.valueOf(j);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MediaPeriodKey)) {
                return false;
            }
            MediaPeriodKey mediaPeriodKey = (MediaPeriodKey) obj;
            return PreloadMediaSource.mediaPeriodIdEqualsWithoutWindowSequenceNumber(this.mediaPeriodId, mediaPeriodKey.mediaPeriodId) && this.startPositionUs.equals(mediaPeriodKey.startPositionUs);
        }

        public int hashCode() {
            int iHashCode = (this.mediaPeriodId.periodUid.hashCode() + 527) * 31;
            MediaSource.MediaPeriodId mediaPeriodId = this.mediaPeriodId;
            return this.startPositionUs.intValue() + ((((((iHashCode + mediaPeriodId.adGroupIndex) * 31) + mediaPeriodId.adIndexInAdGroup) * 31) + mediaPeriodId.nextAdGroupIndex) * 31);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface PreloadControl {
        boolean onContinueLoadingRequested(PreloadMediaSource preloadMediaSource, long j);

        boolean onPrepared(PreloadMediaSource preloadMediaSource);

        boolean onTimelineRefreshed(PreloadMediaSource preloadMediaSource);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class PreloadMediaPeriodCallback implements MediaPeriod.Callback {
        public final long periodStartPositionUs;
        public boolean prepared;

        public PreloadMediaPeriodCallback(long j) {
            this.periodStartPositionUs = j;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
        public void onPrepared(MediaPeriod mediaPeriod) {
            TrackSelectorResult trackSelectorResultSelectTracks;
            this.prepared = true;
            PreloadMediaPeriod preloadMediaPeriod = (PreloadMediaPeriod) mediaPeriod;
            TrackGroupArray trackGroups = preloadMediaPeriod.mediaPeriod.getTrackGroups();
            Pair<PreloadMediaPeriod, MediaPeriodKey> pair = PreloadMediaSource.this.preloadingMediaPeriodAndKey;
            pair.getClass();
            MediaPeriodKey mediaPeriodKey = (MediaPeriodKey) pair.second;
            try {
                PreloadMediaSource preloadMediaSource = PreloadMediaSource.this;
                TrackSelector trackSelector = preloadMediaSource.trackSelector;
                RendererCapabilities[] rendererCapabilitiesArr = preloadMediaSource.rendererCapabilities;
                MediaSource.MediaPeriodId mediaPeriodId = mediaPeriodKey.mediaPeriodId;
                Timeline timeline = preloadMediaSource.timeline;
                timeline.getClass();
                trackSelectorResultSelectTracks = trackSelector.selectTracks(rendererCapabilitiesArr, trackGroups, mediaPeriodId, timeline);
            } catch (ExoPlaybackException e) {
                Log.e(PreloadMediaSource.TAG, "Failed to select tracks", e);
                trackSelectorResultSelectTracks = null;
            }
            if (trackSelectorResultSelectTracks != null) {
                preloadMediaPeriod.selectTracksForPreloading(trackSelectorResultSelectTracks.selections, this.periodStartPositionUs);
                PreloadMediaSource preloadMediaSource2 = PreloadMediaSource.this;
                if (preloadMediaSource2.preloadControl.onPrepared(preloadMediaSource2)) {
                    LoadingInfo.Builder builder = new LoadingInfo.Builder();
                    builder.playbackPositionUs = this.periodStartPositionUs;
                    preloadMediaPeriod.continueLoading(new LoadingInfo(builder));
                }
            }
        }

        @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
        public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
            PreloadMediaPeriod preloadMediaPeriod = (PreloadMediaPeriod) mediaPeriod;
            if (this.prepared) {
                PreloadMediaSource preloadMediaSource = PreloadMediaSource.this;
                if (!preloadMediaSource.preloadControl.onContinueLoadingRequested(preloadMediaSource, preloadMediaPeriod.mediaPeriod.getBufferedPositionUs())) {
                    return;
                }
            }
            LoadingInfo.Builder builder = new LoadingInfo.Builder();
            builder.playbackPositionUs = this.periodStartPositionUs;
            preloadMediaPeriod.continueLoading(new LoadingInfo(builder));
        }
    }

    public static /* synthetic */ void $r8$lambda$BOAlICx_OFjx9ksF9uFqB2gCkHE(PreloadMediaSource preloadMediaSource) {
        preloadMediaSource.preloadCalled = false;
        preloadMediaSource.startPositionUs = -9223372036854775807L;
        Pair<PreloadMediaPeriod, MediaPeriodKey> pair = preloadMediaSource.preloadingMediaPeriodAndKey;
        if (pair != null) {
            preloadMediaSource.mediaSource.releasePeriod(((PreloadMediaPeriod) pair.first).mediaPeriod);
            preloadMediaSource.preloadingMediaPeriodAndKey = null;
        }
        preloadMediaSource.releaseSourceInternal();
        preloadMediaSource.preloadHandler.removeCallbacksAndMessages(null);
    }

    public static void $r8$lambda$igH0qgy3yFy9Xnb_rwK1ngpHhWI(PreloadMediaSource preloadMediaSource, long j) {
        preloadMediaSource.preloadCalled = true;
        preloadMediaSource.startPositionUs = j;
        if (preloadMediaSource.prepareSourceCalled()) {
            return;
        }
        preloadMediaSource.playerId = PlayerId.UNSET;
        preloadMediaSource.prepareSourceInternal(preloadMediaSource.bandwidthMeter.getTransferListener());
    }

    public static boolean mediaPeriodIdEqualsWithoutWindowSequenceNumber(MediaSource.MediaPeriodId mediaPeriodId, MediaSource.MediaPeriodId mediaPeriodId2) {
        return mediaPeriodId.periodUid.equals(mediaPeriodId2.periodUid) && mediaPeriodId.adGroupIndex == mediaPeriodId2.adGroupIndex && mediaPeriodId.adIndexInAdGroup == mediaPeriodId2.adIndexInAdGroup && mediaPeriodId.nextAdGroupIndex == mediaPeriodId2.nextAdGroupIndex;
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSource.MediaPeriodId mediaPeriodId) {
        Pair<PreloadMediaPeriod, MediaSource.MediaPeriodId> pair = this.playingPreloadedMediaPeriodAndId;
        if (pair == null) {
            return mediaPeriodId;
        }
        pair.getClass();
        if (!mediaPeriodIdEqualsWithoutWindowSequenceNumber(mediaPeriodId, (MediaSource.MediaPeriodId) pair.second)) {
            return mediaPeriodId;
        }
        Pair<PreloadMediaPeriod, MediaSource.MediaPeriodId> pair2 = this.playingPreloadedMediaPeriodAndId;
        pair2.getClass();
        return (MediaSource.MediaPeriodId) pair2.second;
    }

    public boolean isUsedByPlayer() {
        return prepareSourceCalled();
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource
    public void onChildSourceInfoRefreshed(Timeline timeline) {
        this.timeline = timeline;
        refreshSourceInfo(timeline);
        if (prepareSourceCalled() || !this.preloadControl.onTimelineRefreshed(this)) {
            return;
        }
        Pair<Object, Long> periodPositionUs = timeline.getPeriodPositionUs(new Timeline.Window(), new Timeline.Period(), 0, this.startPositionUs);
        createPeriod(new MediaSource.MediaPeriodId(periodPositionUs.first), this.allocator, ((Long) periodPositionUs.second).longValue()).preload(new PreloadMediaPeriodCallback(((Long) periodPositionUs.second).longValue()), ((Long) periodPositionUs.second).longValue());
    }

    public void preload(final long j) {
        this.preloadHandler.post(new Runnable() { // from class: androidx.media3.exoplayer.source.preload.PreloadMediaSource$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                PreloadMediaSource.$r8$lambda$igH0qgy3yFy9Xnb_rwK1ngpHhWI(this.f$0, j);
            }
        });
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource
    public void prepareSourceInternal() {
        Timeline timeline = this.timeline;
        if (timeline != null) {
            onChildSourceInfoRefreshed(timeline);
        } else {
            if (this.prepareChildSourceCalled) {
                return;
            }
            this.prepareChildSourceCalled = true;
            prepareChildSource();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0011  */
    @Override // androidx.media3.exoplayer.source.WrappingMediaSource, androidx.media3.exoplayer.source.MediaSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void releasePeriod(androidx.media3.exoplayer.source.MediaPeriod r3) {
        /*
            r2 = this;
            androidx.media3.exoplayer.source.preload.PreloadMediaPeriod r3 = (androidx.media3.exoplayer.source.preload.PreloadMediaPeriod) r3
            android.util.Pair<androidx.media3.exoplayer.source.preload.PreloadMediaPeriod, androidx.media3.exoplayer.source.preload.PreloadMediaSource$MediaPeriodKey> r0 = r2.preloadingMediaPeriodAndKey
            r1 = 0
            if (r0 == 0) goto L11
            r0.getClass()
            java.lang.Object r0 = r0.first
            if (r3 != r0) goto L11
            r2.preloadingMediaPeriodAndKey = r1
            goto L1e
        L11:
            android.util.Pair<androidx.media3.exoplayer.source.preload.PreloadMediaPeriod, androidx.media3.exoplayer.source.MediaSource$MediaPeriodId> r0 = r2.playingPreloadedMediaPeriodAndId
            if (r0 == 0) goto L1e
            r0.getClass()
            java.lang.Object r0 = r0.first
            if (r3 != r0) goto L1e
            r2.playingPreloadedMediaPeriodAndId = r1
        L1e:
            androidx.media3.exoplayer.source.MediaPeriod r3 = r3.mediaPeriod
            androidx.media3.exoplayer.source.MediaSource r0 = r2.mediaSource
            r0.releasePeriod(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.preload.PreloadMediaSource.releasePeriod(androidx.media3.exoplayer.source.MediaPeriod):void");
    }

    public void releasePreloadMediaSource() {
        this.preloadHandler.post(new Runnable() { // from class: androidx.media3.exoplayer.source.preload.PreloadMediaSource$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                PreloadMediaSource.$r8$lambda$BOAlICx_OFjx9ksF9uFqB2gCkHE(this.f$0);
            }
        });
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
        if (this.preloadCalled || prepareSourceCalled()) {
            return;
        }
        this.timeline = null;
        this.prepareChildSourceCalled = false;
        super.releaseSourceInternal();
    }

    public PreloadMediaSource(MediaSource mediaSource, PreloadControl preloadControl, TrackSelector trackSelector, BandwidthMeter bandwidthMeter, RendererCapabilities[] rendererCapabilitiesArr, Allocator allocator, Looper looper) {
        super(mediaSource);
        this.preloadControl = preloadControl;
        this.trackSelector = trackSelector;
        this.bandwidthMeter = bandwidthMeter;
        this.rendererCapabilities = rendererCapabilitiesArr;
        this.allocator = allocator;
        this.preloadHandler = Util.createHandler(looper, null);
        this.startPositionUs = -9223372036854775807L;
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource, androidx.media3.exoplayer.source.MediaSource
    public PreloadMediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        MediaPeriodKey mediaPeriodKey = new MediaPeriodKey(mediaPeriodId, j);
        Pair<PreloadMediaPeriod, MediaPeriodKey> pair = this.preloadingMediaPeriodAndKey;
        if (pair != null && mediaPeriodKey.equals(pair.second)) {
            Pair<PreloadMediaPeriod, MediaPeriodKey> pair2 = this.preloadingMediaPeriodAndKey;
            pair2.getClass();
            PreloadMediaPeriod preloadMediaPeriod = (PreloadMediaPeriod) pair2.first;
            if (prepareSourceCalled()) {
                this.preloadingMediaPeriodAndKey = null;
                this.playingPreloadedMediaPeriodAndId = new Pair<>(preloadMediaPeriod, mediaPeriodId);
            }
            return preloadMediaPeriod;
        }
        Pair<PreloadMediaPeriod, MediaPeriodKey> pair3 = this.preloadingMediaPeriodAndKey;
        if (pair3 != null) {
            MediaSource mediaSource = this.mediaSource;
            pair3.getClass();
            mediaSource.releasePeriod(((PreloadMediaPeriod) pair3.first).mediaPeriod);
            this.preloadingMediaPeriodAndKey = null;
        }
        PreloadMediaPeriod preloadMediaPeriod2 = new PreloadMediaPeriod(this.mediaSource.createPeriod(mediaPeriodId, allocator, j));
        if (!prepareSourceCalled()) {
            this.preloadingMediaPeriodAndKey = new Pair<>(preloadMediaPeriod2, mediaPeriodKey);
        }
        return preloadMediaPeriod2;
    }
}
