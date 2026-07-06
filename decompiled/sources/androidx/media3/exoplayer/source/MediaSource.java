package androidx.media3.exoplayer.source;

import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.extractor.text.SubtitleParser;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface MediaSource {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @UnstableApi
    public static final class MediaPeriodId {
        public final int adGroupIndex;
        public final int adIndexInAdGroup;
        public final int nextAdGroupIndex;
        public final Object periodUid;
        public final long windowSequenceNumber;

        public MediaPeriodId(Object obj) {
            this(obj, -1L);
        }

        public MediaPeriodId copyWithPeriodUid(Object obj) {
            return this.periodUid.equals(obj) ? this : new MediaPeriodId(obj, this.adGroupIndex, this.adIndexInAdGroup, this.windowSequenceNumber, this.nextAdGroupIndex);
        }

        public MediaPeriodId copyWithWindowSequenceNumber(long j) {
            return this.windowSequenceNumber == j ? this : new MediaPeriodId(this.periodUid, this.adGroupIndex, this.adIndexInAdGroup, j, this.nextAdGroupIndex);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MediaPeriodId)) {
                return false;
            }
            MediaPeriodId mediaPeriodId = (MediaPeriodId) obj;
            return this.periodUid.equals(mediaPeriodId.periodUid) && this.adGroupIndex == mediaPeriodId.adGroupIndex && this.adIndexInAdGroup == mediaPeriodId.adIndexInAdGroup && this.windowSequenceNumber == mediaPeriodId.windowSequenceNumber && this.nextAdGroupIndex == mediaPeriodId.nextAdGroupIndex;
        }

        public int hashCode() {
            return ((((((((this.periodUid.hashCode() + 527) * 31) + this.adGroupIndex) * 31) + this.adIndexInAdGroup) * 31) + ((int) this.windowSequenceNumber)) * 31) + this.nextAdGroupIndex;
        }

        public boolean isAd() {
            return this.adGroupIndex != -1;
        }

        public MediaPeriodId(Object obj, long j) {
            this(obj, -1, -1, j, -1);
        }

        public MediaPeriodId(Object obj, long j, int i) {
            this(obj, -1, -1, j, i);
        }

        public MediaPeriodId(Object obj, int i, int i2, long j) {
            this(obj, i, i2, j, -1);
        }

        public MediaPeriodId(Object obj, int i, int i2, long j, int i3) {
            this.periodUid = obj;
            this.adGroupIndex = i;
            this.adIndexInAdGroup = i2;
            this.windowSequenceNumber = j;
            this.nextAdGroupIndex = i3;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @UnstableApi
    public interface MediaSourceCaller {
        void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline);
    }

    @UnstableApi
    void addDrmEventListener(Handler handler, DrmSessionEventListener drmSessionEventListener);

    @UnstableApi
    void addEventListener(Handler handler, MediaSourceEventListener mediaSourceEventListener);

    @UnstableApi
    boolean canUpdateMediaItem(MediaItem mediaItem);

    @UnstableApi
    MediaPeriod createPeriod(MediaPeriodId mediaPeriodId, Allocator allocator, long j);

    @UnstableApi
    void disable(MediaSourceCaller mediaSourceCaller);

    @UnstableApi
    void enable(MediaSourceCaller mediaSourceCaller);

    @Nullable
    @UnstableApi
    Timeline getInitialTimeline();

    @UnstableApi
    MediaItem getMediaItem();

    @UnstableApi
    boolean isSingleWindow();

    @UnstableApi
    void maybeThrowSourceInfoRefreshError() throws IOException;

    @UnstableApi
    @Deprecated
    void prepareSource(MediaSourceCaller mediaSourceCaller, @Nullable TransferListener transferListener);

    @UnstableApi
    void prepareSource(MediaSourceCaller mediaSourceCaller, @Nullable TransferListener transferListener, PlayerId playerId);

    @UnstableApi
    void releasePeriod(MediaPeriod mediaPeriod);

    @UnstableApi
    void releaseSource(MediaSourceCaller mediaSourceCaller);

    @UnstableApi
    void removeDrmEventListener(DrmSessionEventListener drmSessionEventListener);

    @UnstableApi
    void removeEventListener(MediaSourceEventListener mediaSourceEventListener);

    @UnstableApi
    void updateMediaItem(MediaItem mediaItem);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Factory {

        @UnstableApi
        public static final Factory UNSUPPORTED = MediaSourceFactory.UNSUPPORTED;

        @UnstableApi
        MediaSource createMediaSource(MediaItem mediaItem);

        @UnstableApi
        Factory experimentalParseSubtitlesDuringExtraction(boolean z);

        @UnstableApi
        int[] getSupportedTypes();

        @UnstableApi
        Factory setCmcdConfigurationFactory(CmcdConfiguration.Factory factory);

        @UnstableApi
        Factory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider);

        @UnstableApi
        Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy);

        @UnstableApi
        Factory setSubtitleParserFactory(SubtitleParser.Factory factory);

        /* JADX INFO: renamed from: androidx.media3.exoplayer.source.MediaSource$Factory$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public final /* synthetic */ class CC {
            @UnstableApi
            public static Factory $default$experimentalParseSubtitlesDuringExtraction(Factory factory, boolean z) {
                return factory;
            }

            @UnstableApi
            public static Factory $default$setCmcdConfigurationFactory(Factory factory, CmcdConfiguration.Factory factory2) {
                return factory;
            }

            @UnstableApi
            public static Factory $default$setSubtitleParserFactory(Factory factory, SubtitleParser.Factory factory2) {
                return factory;
            }
        }
    }

    /* JADX INFO: renamed from: androidx.media3.exoplayer.source.MediaSource$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        @UnstableApi
        public static boolean $default$canUpdateMediaItem(MediaSource mediaSource, MediaItem mediaItem) {
            return false;
        }

        @Nullable
        @UnstableApi
        public static Timeline $default$getInitialTimeline(MediaSource mediaSource) {
            return null;
        }

        @UnstableApi
        public static boolean $default$isSingleWindow(MediaSource mediaSource) {
            return true;
        }

        @UnstableApi
        public static void $default$updateMediaItem(MediaSource mediaSource, MediaItem mediaItem) {
        }
    }
}
