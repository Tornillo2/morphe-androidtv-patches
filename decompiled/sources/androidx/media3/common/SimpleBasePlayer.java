package androidx.media3.common;

import android.graphics.Rect;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Pair;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.Player;
import androidx.media3.common.SimpleBasePlayer;
import androidx.media3.common.Timeline;
import androidx.media3.common.Tracks;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.ForOverride;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public abstract class SimpleBasePlayer extends BasePlayer {
    public static final long POSITION_DISCONTINUITY_THRESHOLD_MS = 1000;
    public final HandlerWrapper applicationHandler;
    public final Looper applicationLooper;
    public final ListenerSet<Player.Listener> listeners;
    public final HashSet<ListenableFuture<?>> pendingOperations;
    public final Timeline.Period period;
    public boolean released;
    public State state;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MediaItemData {
        public final MediaMetadata combinedMediaMetadata;
        public final long defaultPositionUs;
        public final long durationUs;
        public final long elapsedRealtimeEpochOffsetMs;
        public final boolean isDynamic;
        public final boolean isPlaceholder;
        public final boolean isSeekable;

        @Nullable
        public final MediaItem.LiveConfiguration liveConfiguration;

        @Nullable
        public final Object manifest;
        public final MediaItem mediaItem;

        @Nullable
        public final MediaMetadata mediaMetadata;
        public final long[] periodPositionInWindowUs;
        public final ImmutableList<PeriodData> periods;
        public final long positionInFirstPeriodUs;
        public final long presentationStartTimeMs;
        public final Tracks tracks;
        public final Object uid;
        public final long windowStartTimeMs;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Builder {
            public long defaultPositionUs;
            public long durationUs;
            public long elapsedRealtimeEpochOffsetMs;
            public boolean isDynamic;
            public boolean isPlaceholder;
            public boolean isSeekable;

            @Nullable
            public MediaItem.LiveConfiguration liveConfiguration;

            @Nullable
            public Object manifest;
            public MediaItem mediaItem;

            @Nullable
            public MediaMetadata mediaMetadata;
            public ImmutableList<PeriodData> periods;
            public long positionInFirstPeriodUs;
            public long presentationStartTimeMs;
            public Tracks tracks;
            public Object uid;
            public long windowStartTimeMs;

            public MediaItemData build() {
                return new MediaItemData(this);
            }

            @CanIgnoreReturnValue
            public Builder setDefaultPositionUs(long j) {
                Assertions.checkArgument(j >= 0);
                this.defaultPositionUs = j;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setDurationUs(long j) {
                Assertions.checkArgument(j == -9223372036854775807L || j >= 0);
                this.durationUs = j;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setElapsedRealtimeEpochOffsetMs(long j) {
                this.elapsedRealtimeEpochOffsetMs = j;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setIsDynamic(boolean z) {
                this.isDynamic = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setIsPlaceholder(boolean z) {
                this.isPlaceholder = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setIsSeekable(boolean z) {
                this.isSeekable = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setLiveConfiguration(@Nullable MediaItem.LiveConfiguration liveConfiguration) {
                this.liveConfiguration = liveConfiguration;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setManifest(@Nullable Object obj) {
                this.manifest = obj;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setMediaItem(MediaItem mediaItem) {
                this.mediaItem = mediaItem;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setMediaMetadata(@Nullable MediaMetadata mediaMetadata) {
                this.mediaMetadata = mediaMetadata;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setPeriods(List<PeriodData> list) {
                int size = list.size();
                int i = 0;
                while (i < size - 1) {
                    Assertions.checkArgument(list.get(i).durationUs != -9223372036854775807L, "Periods other than last need a duration");
                    int i2 = i + 1;
                    for (int i3 = i2; i3 < size; i3++) {
                        Assertions.checkArgument(!list.get(i).uid.equals(list.get(i3).uid), "Duplicate PeriodData UIDs in period list");
                    }
                    i = i2;
                }
                this.periods = ImmutableList.copyOf((Collection) list);
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setPositionInFirstPeriodUs(long j) {
                Assertions.checkArgument(j >= 0);
                this.positionInFirstPeriodUs = j;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setPresentationStartTimeMs(long j) {
                this.presentationStartTimeMs = j;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setTracks(Tracks tracks) {
                this.tracks = tracks;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setUid(Object obj) {
                this.uid = obj;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setWindowStartTimeMs(long j) {
                this.windowStartTimeMs = j;
                return this;
            }

            public Builder(Object obj) {
                this.uid = obj;
                this.tracks = Tracks.EMPTY;
                this.mediaItem = MediaItem.EMPTY;
                this.mediaMetadata = null;
                this.manifest = null;
                this.liveConfiguration = null;
                this.presentationStartTimeMs = -9223372036854775807L;
                this.windowStartTimeMs = -9223372036854775807L;
                this.elapsedRealtimeEpochOffsetMs = -9223372036854775807L;
                this.isSeekable = false;
                this.isDynamic = false;
                this.defaultPositionUs = 0L;
                this.durationUs = -9223372036854775807L;
                this.positionInFirstPeriodUs = 0L;
                this.isPlaceholder = false;
                this.periods = ImmutableList.of();
            }

            public Builder(MediaItemData mediaItemData) {
                this.uid = mediaItemData.uid;
                this.tracks = mediaItemData.tracks;
                this.mediaItem = mediaItemData.mediaItem;
                this.mediaMetadata = mediaItemData.mediaMetadata;
                this.manifest = mediaItemData.manifest;
                this.liveConfiguration = mediaItemData.liveConfiguration;
                this.presentationStartTimeMs = mediaItemData.presentationStartTimeMs;
                this.windowStartTimeMs = mediaItemData.windowStartTimeMs;
                this.elapsedRealtimeEpochOffsetMs = mediaItemData.elapsedRealtimeEpochOffsetMs;
                this.isSeekable = mediaItemData.isSeekable;
                this.isDynamic = mediaItemData.isDynamic;
                this.defaultPositionUs = mediaItemData.defaultPositionUs;
                this.durationUs = mediaItemData.durationUs;
                this.positionInFirstPeriodUs = mediaItemData.positionInFirstPeriodUs;
                this.isPlaceholder = mediaItemData.isPlaceholder;
                this.periods = mediaItemData.periods;
            }
        }

        public static /* synthetic */ Timeline.Window access$4400(MediaItemData mediaItemData, int i, Timeline.Window window) {
            mediaItemData.getWindow(i, window);
            return window;
        }

        public static /* synthetic */ Timeline.Period access$4500(MediaItemData mediaItemData, int i, int i2, Timeline.Period period) {
            mediaItemData.getPeriod(i, i2, period);
            return period;
        }

        public static MediaMetadata getCombinedMediaMetadata(MediaItem mediaItem, Tracks tracks) {
            MediaMetadata.Builder builder = new MediaMetadata.Builder();
            int size = tracks.groups.size();
            for (int i = 0; i < size; i++) {
                Tracks.Group group = tracks.groups.get(i);
                for (int i2 = 0; i2 < group.length; i2++) {
                    if (group.trackSelected[i2]) {
                        Format trackFormat = group.getTrackFormat(i2);
                        if (trackFormat.metadata != null) {
                            int i3 = 0;
                            while (true) {
                                Metadata.Entry[] entryArr = trackFormat.metadata.entries;
                                if (i3 < entryArr.length) {
                                    entryArr[i3].populateMediaMetadata(builder);
                                    i3++;
                                }
                            }
                        }
                    }
                }
            }
            builder.populate(mediaItem.mediaMetadata);
            return new MediaMetadata(builder);
        }

        public Builder buildUpon() {
            return new Builder(this);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MediaItemData)) {
                return false;
            }
            MediaItemData mediaItemData = (MediaItemData) obj;
            return this.uid.equals(mediaItemData.uid) && this.tracks.equals(mediaItemData.tracks) && this.mediaItem.equals(mediaItemData.mediaItem) && Util.areEqual(this.mediaMetadata, mediaItemData.mediaMetadata) && Util.areEqual(this.manifest, mediaItemData.manifest) && Util.areEqual(this.liveConfiguration, mediaItemData.liveConfiguration) && this.presentationStartTimeMs == mediaItemData.presentationStartTimeMs && this.windowStartTimeMs == mediaItemData.windowStartTimeMs && this.elapsedRealtimeEpochOffsetMs == mediaItemData.elapsedRealtimeEpochOffsetMs && this.isSeekable == mediaItemData.isSeekable && this.isDynamic == mediaItemData.isDynamic && this.defaultPositionUs == mediaItemData.defaultPositionUs && this.durationUs == mediaItemData.durationUs && this.positionInFirstPeriodUs == mediaItemData.positionInFirstPeriodUs && this.isPlaceholder == mediaItemData.isPlaceholder && this.periods.equals(mediaItemData.periods);
        }

        public final Timeline.Period getPeriod(int i, int i2, Timeline.Period period) {
            if (this.periods.isEmpty()) {
                Object obj = this.uid;
                period.set(obj, obj, i, this.positionInFirstPeriodUs + this.durationUs, 0L, AdPlaybackState.NONE, this.isPlaceholder);
                return period;
            }
            PeriodData periodData = this.periods.get(i2);
            Object obj2 = periodData.uid;
            period.set(obj2, Pair.create(this.uid, obj2), i, periodData.durationUs, this.periodPositionInWindowUs[i2], periodData.adPlaybackState, periodData.isPlaceholder);
            return period;
        }

        public final Object getPeriodUid(int i) {
            if (this.periods.isEmpty()) {
                return this.uid;
            }
            return Pair.create(this.uid, this.periods.get(i).uid);
        }

        public final Timeline.Window getWindow(int i, Timeline.Window window) {
            window.set(this.uid, this.mediaItem, this.manifest, this.presentationStartTimeMs, this.windowStartTimeMs, this.elapsedRealtimeEpochOffsetMs, this.isSeekable, this.isDynamic, this.liveConfiguration, this.defaultPositionUs, this.durationUs, i, (i + (this.periods.isEmpty() ? 1 : this.periods.size())) - 1, this.positionInFirstPeriodUs);
            window.isPlaceholder = this.isPlaceholder;
            return window;
        }

        public int hashCode() {
            int iHashCode = (this.mediaItem.hashCode() + ((this.tracks.groups.hashCode() + ((this.uid.hashCode() + DefaultImageHeaderParser.MARKER_EOI) * 31)) * 31)) * 31;
            MediaMetadata mediaMetadata = this.mediaMetadata;
            int iHashCode2 = (iHashCode + (mediaMetadata == null ? 0 : mediaMetadata.hashCode())) * 31;
            Object obj = this.manifest;
            int iHashCode3 = (iHashCode2 + (obj == null ? 0 : obj.hashCode())) * 31;
            MediaItem.LiveConfiguration liveConfiguration = this.liveConfiguration;
            int iHashCode4 = liveConfiguration != null ? liveConfiguration.hashCode() : 0;
            long j = this.presentationStartTimeMs;
            int i = (((iHashCode3 + iHashCode4) * 31) + ((int) (j ^ (j >>> 32)))) * 31;
            long j2 = this.windowStartTimeMs;
            int i2 = (i + ((int) (j2 ^ (j2 >>> 32)))) * 31;
            long j3 = this.elapsedRealtimeEpochOffsetMs;
            int i3 = (((((i2 + ((int) (j3 ^ (j3 >>> 32)))) * 31) + (this.isSeekable ? 1 : 0)) * 31) + (this.isDynamic ? 1 : 0)) * 31;
            long j4 = this.defaultPositionUs;
            int i4 = (i3 + ((int) (j4 ^ (j4 >>> 32)))) * 31;
            long j5 = this.durationUs;
            int i5 = (i4 + ((int) (j5 ^ (j5 >>> 32)))) * 31;
            long j6 = this.positionInFirstPeriodUs;
            return this.periods.hashCode() + ((((i5 + ((int) (j6 ^ (j6 >>> 32)))) * 31) + (this.isPlaceholder ? 1 : 0)) * 31);
        }

        public MediaItemData(Builder builder) {
            int i = 0;
            if (builder.liveConfiguration == null) {
                Assertions.checkArgument(builder.presentationStartTimeMs == -9223372036854775807L, "presentationStartTimeMs can only be set if liveConfiguration != null");
                Assertions.checkArgument(builder.windowStartTimeMs == -9223372036854775807L, "windowStartTimeMs can only be set if liveConfiguration != null");
                Assertions.checkArgument(builder.elapsedRealtimeEpochOffsetMs == -9223372036854775807L, "elapsedRealtimeEpochOffsetMs can only be set if liveConfiguration != null");
            } else {
                long j = builder.presentationStartTimeMs;
                if (j != -9223372036854775807L) {
                    long j2 = builder.windowStartTimeMs;
                    if (j2 != -9223372036854775807L) {
                        Assertions.checkArgument(j2 >= j, "windowStartTimeMs can't be less than presentationStartTimeMs");
                    }
                }
            }
            int size = builder.periods.size();
            long j3 = builder.durationUs;
            if (j3 != -9223372036854775807L) {
                Assertions.checkArgument(builder.defaultPositionUs <= j3, "defaultPositionUs can't be greater than durationUs");
            }
            this.uid = builder.uid;
            this.tracks = builder.tracks;
            this.mediaItem = builder.mediaItem;
            this.mediaMetadata = builder.mediaMetadata;
            this.manifest = builder.manifest;
            this.liveConfiguration = builder.liveConfiguration;
            this.presentationStartTimeMs = builder.presentationStartTimeMs;
            this.windowStartTimeMs = builder.windowStartTimeMs;
            this.elapsedRealtimeEpochOffsetMs = builder.elapsedRealtimeEpochOffsetMs;
            this.isSeekable = builder.isSeekable;
            this.isDynamic = builder.isDynamic;
            this.defaultPositionUs = builder.defaultPositionUs;
            this.durationUs = builder.durationUs;
            long j4 = builder.positionInFirstPeriodUs;
            this.positionInFirstPeriodUs = j4;
            this.isPlaceholder = builder.isPlaceholder;
            ImmutableList<PeriodData> immutableList = builder.periods;
            this.periods = immutableList;
            long[] jArr = new long[immutableList.size()];
            this.periodPositionInWindowUs = jArr;
            if (!immutableList.isEmpty()) {
                jArr[0] = -j4;
                while (i < size - 1) {
                    long[] jArr2 = this.periodPositionInWindowUs;
                    int i2 = i + 1;
                    jArr2[i2] = jArr2[i] + this.periods.get(i).durationUs;
                    i = i2;
                }
            }
            MediaMetadata mediaMetadata = this.mediaMetadata;
            this.combinedMediaMetadata = mediaMetadata == null ? getCombinedMediaMetadata(this.mediaItem, this.tracks) : mediaMetadata;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PeriodData {
        public final AdPlaybackState adPlaybackState;
        public final long durationUs;
        public final boolean isPlaceholder;
        public final Object uid;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Builder {
            public AdPlaybackState adPlaybackState;
            public long durationUs;
            public boolean isPlaceholder;
            public Object uid;

            public PeriodData build() {
                return new PeriodData(this);
            }

            @CanIgnoreReturnValue
            public Builder setAdPlaybackState(AdPlaybackState adPlaybackState) {
                this.adPlaybackState = adPlaybackState;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setDurationUs(long j) {
                Assertions.checkArgument(j == -9223372036854775807L || j >= 0);
                this.durationUs = j;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setIsPlaceholder(boolean z) {
                this.isPlaceholder = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setUid(Object obj) {
                this.uid = obj;
                return this;
            }

            public Builder(Object obj) {
                this.uid = obj;
                this.durationUs = 0L;
                this.adPlaybackState = AdPlaybackState.NONE;
                this.isPlaceholder = false;
            }

            public Builder(PeriodData periodData) {
                this.uid = periodData.uid;
                this.durationUs = periodData.durationUs;
                this.adPlaybackState = periodData.adPlaybackState;
                this.isPlaceholder = periodData.isPlaceholder;
            }
        }

        public Builder buildUpon() {
            return new Builder(this);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PeriodData)) {
                return false;
            }
            PeriodData periodData = (PeriodData) obj;
            return this.uid.equals(periodData.uid) && this.durationUs == periodData.durationUs && this.adPlaybackState.equals(periodData.adPlaybackState) && this.isPlaceholder == periodData.isPlaceholder;
        }

        public int hashCode() {
            int iHashCode = (this.uid.hashCode() + DefaultImageHeaderParser.MARKER_EOI) * 31;
            long j = this.durationUs;
            return ((this.adPlaybackState.hashCode() + ((iHashCode + ((int) (j ^ (j >>> 32)))) * 31)) * 31) + (this.isPlaceholder ? 1 : 0);
        }

        public PeriodData(Builder builder) {
            this.uid = builder.uid;
            this.durationUs = builder.durationUs;
            this.adPlaybackState = builder.adPlaybackState;
            this.isPlaceholder = builder.isPlaceholder;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PlaceholderUid {
        public PlaceholderUid() {
        }

        public PlaceholderUid(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PlaylistTimeline extends Timeline {
        public final int[] firstPeriodIndexByWindowIndex;
        public final HashMap<Object, Integer> periodIndexByUid;
        public final ImmutableList<MediaItemData> playlist;
        public final int[] windowIndexByPeriodIndex;

        public PlaylistTimeline(ImmutableList<MediaItemData> immutableList) {
            int size = immutableList.size();
            this.playlist = immutableList;
            this.firstPeriodIndexByWindowIndex = new int[size];
            int periodCountInMediaItem = 0;
            for (int i = 0; i < size; i++) {
                MediaItemData mediaItemData = immutableList.get(i);
                this.firstPeriodIndexByWindowIndex[i] = periodCountInMediaItem;
                periodCountInMediaItem += getPeriodCountInMediaItem(mediaItemData);
            }
            this.windowIndexByPeriodIndex = new int[periodCountInMediaItem];
            this.periodIndexByUid = new HashMap<>();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                MediaItemData mediaItemData2 = immutableList.get(i3);
                for (int i4 = 0; i4 < getPeriodCountInMediaItem(mediaItemData2); i4++) {
                    this.periodIndexByUid.put(mediaItemData2.getPeriodUid(i4), Integer.valueOf(i2));
                    this.windowIndexByPeriodIndex[i2] = i3;
                    i2++;
                }
            }
        }

        public static int getPeriodCountInMediaItem(MediaItemData mediaItemData) {
            if (mediaItemData.periods.isEmpty()) {
                return 1;
            }
            return mediaItemData.periods.size();
        }

        @Override // androidx.media3.common.Timeline
        public int getFirstWindowIndex(boolean z) {
            return super.getFirstWindowIndex(z);
        }

        @Override // androidx.media3.common.Timeline
        public int getIndexOfPeriod(Object obj) {
            Integer num = this.periodIndexByUid.get(obj);
            if (num == null) {
                return -1;
            }
            return num.intValue();
        }

        @Override // androidx.media3.common.Timeline
        public int getLastWindowIndex(boolean z) {
            return super.getLastWindowIndex(z);
        }

        @Override // androidx.media3.common.Timeline
        public int getNextWindowIndex(int i, int i2, boolean z) {
            return super.getNextWindowIndex(i, i2, z);
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            int i2 = this.windowIndexByPeriodIndex[i];
            this.playlist.get(i2).getPeriod(i2, i - this.firstPeriodIndexByWindowIndex[i2], period);
            return period;
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Period getPeriodByUid(Object obj, Timeline.Period period) {
            Integer num = this.periodIndexByUid.get(obj);
            num.getClass();
            getPeriod(num.intValue(), period, true);
            return period;
        }

        @Override // androidx.media3.common.Timeline
        public int getPeriodCount() {
            return this.windowIndexByPeriodIndex.length;
        }

        @Override // androidx.media3.common.Timeline
        public int getPreviousWindowIndex(int i, int i2, boolean z) {
            return super.getPreviousWindowIndex(i, i2, z);
        }

        @Override // androidx.media3.common.Timeline
        public Object getUidOfPeriod(int i) {
            int i2 = this.windowIndexByPeriodIndex[i];
            return this.playlist.get(i2).getPeriodUid(i - this.firstPeriodIndexByWindowIndex[i2]);
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Window getWindow(int i, Timeline.Window window, long j) {
            this.playlist.get(i).getWindow(this.firstPeriodIndexByWindowIndex[i], window);
            return window;
        }

        @Override // androidx.media3.common.Timeline
        public int getWindowCount() {
            return this.playlist.size();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class State {
        public final PositionSupplier adBufferedPositionMsSupplier;
        public final PositionSupplier adPositionMsSupplier;
        public final AudioAttributes audioAttributes;
        public final Player.Commands availableCommands;
        public final PositionSupplier contentBufferedPositionMsSupplier;
        public final PositionSupplier contentPositionMsSupplier;
        public final int currentAdGroupIndex;
        public final int currentAdIndexInAdGroup;
        public final CueGroup currentCues;
        public final int currentMediaItemIndex;
        public final DeviceInfo deviceInfo;

        @IntRange(from = 0)
        public final int deviceVolume;
        public final long discontinuityPositionMs;
        public final boolean hasPositionDiscontinuity;
        public final boolean isDeviceMuted;
        public final boolean isLoading;
        public final long maxSeekToPreviousPositionMs;
        public final boolean newlyRenderedFirstFrame;
        public final boolean playWhenReady;
        public final int playWhenReadyChangeReason;
        public final PlaybackParameters playbackParameters;
        public final int playbackState;
        public final int playbackSuppressionReason;

        @Nullable
        public final PlaybackException playerError;
        public final ImmutableList<MediaItemData> playlist;
        public final MediaMetadata playlistMetadata;
        public final int positionDiscontinuityReason;
        public final int repeatMode;
        public final long seekBackIncrementMs;
        public final long seekForwardIncrementMs;
        public final boolean shuffleModeEnabled;
        public final Size surfaceSize;
        public final Metadata timedMetadata;
        public final Timeline timeline;
        public final PositionSupplier totalBufferedDurationMsSupplier;
        public final TrackSelectionParameters trackSelectionParameters;
        public final VideoSize videoSize;

        @FloatRange(from = 0.0d, to = 1.0d)
        public final float volume;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Builder {
            public PositionSupplier adBufferedPositionMsSupplier;

            @Nullable
            public Long adPositionMs;
            public PositionSupplier adPositionMsSupplier;
            public AudioAttributes audioAttributes;
            public Player.Commands availableCommands;
            public PositionSupplier contentBufferedPositionMsSupplier;

            @Nullable
            public Long contentPositionMs;
            public PositionSupplier contentPositionMsSupplier;
            public int currentAdGroupIndex;
            public int currentAdIndexInAdGroup;
            public CueGroup currentCues;
            public int currentMediaItemIndex;
            public DeviceInfo deviceInfo;
            public int deviceVolume;
            public long discontinuityPositionMs;
            public boolean hasPositionDiscontinuity;
            public boolean isDeviceMuted;
            public boolean isLoading;
            public long maxSeekToPreviousPositionMs;
            public boolean newlyRenderedFirstFrame;
            public boolean playWhenReady;
            public int playWhenReadyChangeReason;
            public PlaybackParameters playbackParameters;
            public int playbackState;
            public int playbackSuppressionReason;

            @Nullable
            public PlaybackException playerError;
            public ImmutableList<MediaItemData> playlist;
            public MediaMetadata playlistMetadata;
            public int positionDiscontinuityReason;
            public int repeatMode;
            public long seekBackIncrementMs;
            public long seekForwardIncrementMs;
            public boolean shuffleModeEnabled;
            public Size surfaceSize;
            public Metadata timedMetadata;
            public Timeline timeline;
            public PositionSupplier totalBufferedDurationMsSupplier;
            public TrackSelectionParameters trackSelectionParameters;
            public VideoSize videoSize;
            public float volume;

            public State build() {
                return new State(this);
            }

            @CanIgnoreReturnValue
            public Builder clearPositionDiscontinuity() {
                this.hasPositionDiscontinuity = false;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setAdBufferedPositionMs(PositionSupplier positionSupplier) {
                this.adBufferedPositionMsSupplier = positionSupplier;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setAdPositionMs(long j) {
                this.adPositionMs = Long.valueOf(j);
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setAudioAttributes(AudioAttributes audioAttributes) {
                this.audioAttributes = audioAttributes;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setAvailableCommands(Player.Commands commands) {
                this.availableCommands = commands;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setContentBufferedPositionMs(PositionSupplier positionSupplier) {
                this.contentBufferedPositionMsSupplier = positionSupplier;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setContentPositionMs(long j) {
                this.contentPositionMs = Long.valueOf(j);
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setCurrentAd(int i, int i2) {
                Assertions.checkArgument((i == -1) == (i2 == -1));
                this.currentAdGroupIndex = i;
                this.currentAdIndexInAdGroup = i2;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setCurrentCues(CueGroup cueGroup) {
                this.currentCues = cueGroup;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setCurrentMediaItemIndex(int i) {
                this.currentMediaItemIndex = i;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setDeviceInfo(DeviceInfo deviceInfo) {
                this.deviceInfo = deviceInfo;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setDeviceVolume(@IntRange(from = 0) int i) {
                Assertions.checkArgument(i >= 0);
                this.deviceVolume = i;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setIsDeviceMuted(boolean z) {
                this.isDeviceMuted = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setIsLoading(boolean z) {
                this.isLoading = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setMaxSeekToPreviousPositionMs(long j) {
                this.maxSeekToPreviousPositionMs = j;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setNewlyRenderedFirstFrame(boolean z) {
                this.newlyRenderedFirstFrame = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setPlayWhenReady(boolean z, int i) {
                this.playWhenReady = z;
                this.playWhenReadyChangeReason = i;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setPlaybackParameters(PlaybackParameters playbackParameters) {
                this.playbackParameters = playbackParameters;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setPlaybackState(int i) {
                this.playbackState = i;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setPlaybackSuppressionReason(int i) {
                this.playbackSuppressionReason = i;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setPlayerError(@Nullable PlaybackException playbackException) {
                this.playerError = playbackException;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setPlaylist(List<MediaItemData> list) {
                HashSet hashSet = new HashSet();
                for (int i = 0; i < list.size(); i++) {
                    Assertions.checkArgument(hashSet.add(list.get(i).uid), "Duplicate MediaItemData UID in playlist");
                }
                this.playlist = ImmutableList.copyOf((Collection) list);
                this.timeline = new PlaylistTimeline(this.playlist);
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setPlaylistMetadata(MediaMetadata mediaMetadata) {
                this.playlistMetadata = mediaMetadata;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setPositionDiscontinuity(int i, long j) {
                this.hasPositionDiscontinuity = true;
                this.positionDiscontinuityReason = i;
                this.discontinuityPositionMs = j;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setRepeatMode(int i) {
                this.repeatMode = i;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setSeekBackIncrementMs(long j) {
                this.seekBackIncrementMs = j;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setSeekForwardIncrementMs(long j) {
                this.seekForwardIncrementMs = j;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setShuffleModeEnabled(boolean z) {
                this.shuffleModeEnabled = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setSurfaceSize(Size size) {
                this.surfaceSize = size;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setTimedMetadata(Metadata metadata) {
                this.timedMetadata = metadata;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setTotalBufferedDurationMs(PositionSupplier positionSupplier) {
                this.totalBufferedDurationMsSupplier = positionSupplier;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
                this.trackSelectionParameters = trackSelectionParameters;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setVideoSize(VideoSize videoSize) {
                this.videoSize = videoSize;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setVolume(@FloatRange(from = 0.0d, to = 1.0d) float f) {
                Assertions.checkArgument(f >= 0.0f && f <= 1.0f);
                this.volume = f;
                return this;
            }

            public Builder() {
                this.availableCommands = Player.Commands.EMPTY;
                this.playWhenReady = false;
                this.playWhenReadyChangeReason = 1;
                this.playbackState = 1;
                this.playbackSuppressionReason = 0;
                this.playerError = null;
                this.repeatMode = 0;
                this.shuffleModeEnabled = false;
                this.isLoading = false;
                this.seekBackIncrementMs = 5000L;
                this.seekForwardIncrementMs = 15000L;
                this.maxSeekToPreviousPositionMs = 3000L;
                this.playbackParameters = PlaybackParameters.DEFAULT;
                this.trackSelectionParameters = TrackSelectionParameters.DEFAULT_WITHOUT_CONTEXT;
                this.audioAttributes = AudioAttributes.DEFAULT;
                this.volume = 1.0f;
                this.videoSize = VideoSize.UNKNOWN;
                this.currentCues = CueGroup.EMPTY_TIME_ZERO;
                this.deviceInfo = DeviceInfo.UNKNOWN;
                this.deviceVolume = 0;
                this.isDeviceMuted = false;
                this.surfaceSize = Size.UNKNOWN;
                this.newlyRenderedFirstFrame = false;
                this.timedMetadata = new Metadata(-9223372036854775807L, new Metadata.Entry[0]);
                this.playlist = ImmutableList.of();
                this.timeline = Timeline.EMPTY;
                this.playlistMetadata = MediaMetadata.EMPTY;
                this.currentMediaItemIndex = -1;
                this.currentAdGroupIndex = -1;
                this.currentAdIndexInAdGroup = -1;
                this.contentPositionMs = null;
                this.contentPositionMsSupplier = PositionSupplier.CC.getConstant(-9223372036854775807L);
                this.adPositionMs = null;
                PositionSupplier positionSupplier = PositionSupplier.ZERO;
                this.adPositionMsSupplier = positionSupplier;
                this.contentBufferedPositionMsSupplier = new SimpleBasePlayer$PositionSupplier$$ExternalSyntheticLambda1(-9223372036854775807L);
                this.adBufferedPositionMsSupplier = positionSupplier;
                this.totalBufferedDurationMsSupplier = positionSupplier;
                this.hasPositionDiscontinuity = false;
                this.positionDiscontinuityReason = 5;
                this.discontinuityPositionMs = 0L;
            }

            @CanIgnoreReturnValue
            public Builder setAdPositionMs(PositionSupplier positionSupplier) {
                this.adPositionMs = null;
                this.adPositionMsSupplier = positionSupplier;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setContentPositionMs(PositionSupplier positionSupplier) {
                this.contentPositionMs = null;
                this.contentPositionMsSupplier = positionSupplier;
                return this;
            }

            public Builder(State state) {
                this.availableCommands = state.availableCommands;
                this.playWhenReady = state.playWhenReady;
                this.playWhenReadyChangeReason = state.playWhenReadyChangeReason;
                this.playbackState = state.playbackState;
                this.playbackSuppressionReason = state.playbackSuppressionReason;
                this.playerError = state.playerError;
                this.repeatMode = state.repeatMode;
                this.shuffleModeEnabled = state.shuffleModeEnabled;
                this.isLoading = state.isLoading;
                this.seekBackIncrementMs = state.seekBackIncrementMs;
                this.seekForwardIncrementMs = state.seekForwardIncrementMs;
                this.maxSeekToPreviousPositionMs = state.maxSeekToPreviousPositionMs;
                this.playbackParameters = state.playbackParameters;
                this.trackSelectionParameters = state.trackSelectionParameters;
                this.audioAttributes = state.audioAttributes;
                this.volume = state.volume;
                this.videoSize = state.videoSize;
                this.currentCues = state.currentCues;
                this.deviceInfo = state.deviceInfo;
                this.deviceVolume = state.deviceVolume;
                this.isDeviceMuted = state.isDeviceMuted;
                this.surfaceSize = state.surfaceSize;
                this.newlyRenderedFirstFrame = state.newlyRenderedFirstFrame;
                this.timedMetadata = state.timedMetadata;
                this.playlist = state.playlist;
                this.timeline = state.timeline;
                this.playlistMetadata = state.playlistMetadata;
                this.currentMediaItemIndex = state.currentMediaItemIndex;
                this.currentAdGroupIndex = state.currentAdGroupIndex;
                this.currentAdIndexInAdGroup = state.currentAdIndexInAdGroup;
                this.contentPositionMs = null;
                this.contentPositionMsSupplier = state.contentPositionMsSupplier;
                this.adPositionMs = null;
                this.adPositionMsSupplier = state.adPositionMsSupplier;
                this.contentBufferedPositionMsSupplier = state.contentBufferedPositionMsSupplier;
                this.adBufferedPositionMsSupplier = state.adBufferedPositionMsSupplier;
                this.totalBufferedDurationMsSupplier = state.totalBufferedDurationMsSupplier;
                this.hasPositionDiscontinuity = state.hasPositionDiscontinuity;
                this.positionDiscontinuityReason = state.positionDiscontinuityReason;
                this.discontinuityPositionMs = state.discontinuityPositionMs;
            }
        }

        public Builder buildUpon() {
            return new Builder(this);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof State)) {
                return false;
            }
            State state = (State) obj;
            return this.playWhenReady == state.playWhenReady && this.playWhenReadyChangeReason == state.playWhenReadyChangeReason && this.availableCommands.equals(state.availableCommands) && this.playbackState == state.playbackState && this.playbackSuppressionReason == state.playbackSuppressionReason && Util.areEqual(this.playerError, state.playerError) && this.repeatMode == state.repeatMode && this.shuffleModeEnabled == state.shuffleModeEnabled && this.isLoading == state.isLoading && this.seekBackIncrementMs == state.seekBackIncrementMs && this.seekForwardIncrementMs == state.seekForwardIncrementMs && this.maxSeekToPreviousPositionMs == state.maxSeekToPreviousPositionMs && this.playbackParameters.equals(state.playbackParameters) && this.trackSelectionParameters.equals(state.trackSelectionParameters) && this.audioAttributes.equals(state.audioAttributes) && this.volume == state.volume && this.videoSize.equals(state.videoSize) && this.currentCues.equals(state.currentCues) && this.deviceInfo.equals(state.deviceInfo) && this.deviceVolume == state.deviceVolume && this.isDeviceMuted == state.isDeviceMuted && this.surfaceSize.equals(state.surfaceSize) && this.newlyRenderedFirstFrame == state.newlyRenderedFirstFrame && this.timedMetadata.equals(state.timedMetadata) && this.playlist.equals(state.playlist) && this.playlistMetadata.equals(state.playlistMetadata) && this.currentMediaItemIndex == state.currentMediaItemIndex && this.currentAdGroupIndex == state.currentAdGroupIndex && this.currentAdIndexInAdGroup == state.currentAdIndexInAdGroup && this.contentPositionMsSupplier.equals(state.contentPositionMsSupplier) && this.adPositionMsSupplier.equals(state.adPositionMsSupplier) && this.contentBufferedPositionMsSupplier.equals(state.contentBufferedPositionMsSupplier) && this.adBufferedPositionMsSupplier.equals(state.adBufferedPositionMsSupplier) && this.totalBufferedDurationMsSupplier.equals(state.totalBufferedDurationMsSupplier) && this.hasPositionDiscontinuity == state.hasPositionDiscontinuity && this.positionDiscontinuityReason == state.positionDiscontinuityReason && this.discontinuityPositionMs == state.discontinuityPositionMs;
        }

        public int hashCode() {
            int iHashCode = (((((((((this.availableCommands.flags.hashCode() + DefaultImageHeaderParser.MARKER_EOI) * 31) + (this.playWhenReady ? 1 : 0)) * 31) + this.playWhenReadyChangeReason) * 31) + this.playbackState) * 31) + this.playbackSuppressionReason) * 31;
            PlaybackException playbackException = this.playerError;
            int iHashCode2 = (((((((iHashCode + (playbackException == null ? 0 : playbackException.hashCode())) * 31) + this.repeatMode) * 31) + (this.shuffleModeEnabled ? 1 : 0)) * 31) + (this.isLoading ? 1 : 0)) * 31;
            long j = this.seekBackIncrementMs;
            int i = (iHashCode2 + ((int) (j ^ (j >>> 32)))) * 31;
            long j2 = this.seekForwardIncrementMs;
            int i2 = (i + ((int) (j2 ^ (j2 >>> 32)))) * 31;
            long j3 = this.maxSeekToPreviousPositionMs;
            int iHashCode3 = (((((this.totalBufferedDurationMsSupplier.hashCode() + ((this.adBufferedPositionMsSupplier.hashCode() + ((this.contentBufferedPositionMsSupplier.hashCode() + ((this.adPositionMsSupplier.hashCode() + ((this.contentPositionMsSupplier.hashCode() + ((((((((this.playlistMetadata.hashCode() + ((this.playlist.hashCode() + ((this.timedMetadata.hashCode() + ((((this.surfaceSize.hashCode() + ((((((this.deviceInfo.hashCode() + ((this.currentCues.hashCode() + ((this.videoSize.hashCode() + ((Float.floatToRawIntBits(this.volume) + ((this.audioAttributes.hashCode() + ((this.trackSelectionParameters.hashCode() + ((this.playbackParameters.hashCode() + ((i2 + ((int) (j3 ^ (j3 >>> 32)))) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + this.deviceVolume) * 31) + (this.isDeviceMuted ? 1 : 0)) * 31)) * 31) + (this.newlyRenderedFirstFrame ? 1 : 0)) * 31)) * 31)) * 31)) * 31) + this.currentMediaItemIndex) * 31) + this.currentAdGroupIndex) * 31) + this.currentAdIndexInAdGroup) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + (this.hasPositionDiscontinuity ? 1 : 0)) * 31) + this.positionDiscontinuityReason) * 31;
            long j4 = this.discontinuityPositionMs;
            return iHashCode3 + ((int) (j4 ^ (j4 >>> 32)));
        }

        public State(Builder builder) {
            int i;
            if (builder.timeline.isEmpty()) {
                int i2 = builder.playbackState;
                Assertions.checkArgument(i2 == 1 || i2 == 4, "Empty playlist only allowed in STATE_IDLE or STATE_ENDED");
                Assertions.checkArgument(builder.currentAdGroupIndex == -1 && builder.currentAdIndexInAdGroup == -1, "Ads not allowed if playlist is empty");
            } else {
                int i3 = builder.currentMediaItemIndex;
                if (i3 == -1) {
                    i = 0;
                } else {
                    Assertions.checkArgument(i3 < builder.timeline.getWindowCount(), "currentMediaItemIndex must be less than playlist.size()");
                    i = i3;
                }
                if (builder.currentAdGroupIndex != -1) {
                    Timeline.Period period = new Timeline.Period();
                    Timeline.Window window = new Timeline.Window();
                    Long l = builder.contentPositionMs;
                    builder.timeline.getPeriod(SimpleBasePlayer.getPeriodIndexFromWindowPosition(builder.timeline, i, l != null ? l.longValue() : builder.contentPositionMsSupplier.get(), window, period), period, false);
                    Assertions.checkArgument(builder.currentAdGroupIndex < period.adPlaybackState.adGroupCount, "PeriodData has less ad groups than adGroupIndex");
                    int adCountInAdGroup = period.getAdCountInAdGroup(builder.currentAdGroupIndex);
                    if (adCountInAdGroup != -1) {
                        Assertions.checkArgument(builder.currentAdIndexInAdGroup < adCountInAdGroup, "Ad group has less ads than adIndexInGroupIndex");
                    }
                }
            }
            if (builder.playerError != null) {
                Assertions.checkArgument(builder.playbackState == 1, "Player error only allowed in STATE_IDLE");
            }
            int i4 = builder.playbackState;
            if (i4 == 1 || i4 == 4) {
                Assertions.checkArgument(!builder.isLoading, "isLoading only allowed when not in STATE_IDLE or STATE_ENDED");
            }
            PositionSupplier extrapolating = builder.contentPositionMsSupplier;
            Long l2 = builder.contentPositionMs;
            extrapolating = l2 != null ? (builder.currentAdGroupIndex == -1 && builder.playWhenReady && builder.playbackState == 3 && builder.playbackSuppressionReason == 0 && l2.longValue() != -9223372036854775807L) ? PositionSupplier.CC.getExtrapolating(builder.contentPositionMs.longValue(), builder.playbackParameters.speed) : PositionSupplier.CC.getConstant(builder.contentPositionMs.longValue()) : extrapolating;
            PositionSupplier extrapolating2 = builder.adPositionMsSupplier;
            Long l3 = builder.adPositionMs;
            extrapolating2 = l3 != null ? (builder.currentAdGroupIndex != -1 && builder.playWhenReady && builder.playbackState == 3 && builder.playbackSuppressionReason == 0) ? PositionSupplier.CC.getExtrapolating(l3.longValue(), 1.0f) : PositionSupplier.CC.getConstant(l3.longValue()) : extrapolating2;
            this.availableCommands = builder.availableCommands;
            this.playWhenReady = builder.playWhenReady;
            this.playWhenReadyChangeReason = builder.playWhenReadyChangeReason;
            this.playbackState = builder.playbackState;
            this.playbackSuppressionReason = builder.playbackSuppressionReason;
            this.playerError = builder.playerError;
            this.repeatMode = builder.repeatMode;
            this.shuffleModeEnabled = builder.shuffleModeEnabled;
            this.isLoading = builder.isLoading;
            this.seekBackIncrementMs = builder.seekBackIncrementMs;
            this.seekForwardIncrementMs = builder.seekForwardIncrementMs;
            this.maxSeekToPreviousPositionMs = builder.maxSeekToPreviousPositionMs;
            this.playbackParameters = builder.playbackParameters;
            this.trackSelectionParameters = builder.trackSelectionParameters;
            this.audioAttributes = builder.audioAttributes;
            this.volume = builder.volume;
            this.videoSize = builder.videoSize;
            this.currentCues = builder.currentCues;
            this.deviceInfo = builder.deviceInfo;
            this.deviceVolume = builder.deviceVolume;
            this.isDeviceMuted = builder.isDeviceMuted;
            this.surfaceSize = builder.surfaceSize;
            this.newlyRenderedFirstFrame = builder.newlyRenderedFirstFrame;
            this.timedMetadata = builder.timedMetadata;
            this.playlist = builder.playlist;
            this.timeline = builder.timeline;
            this.playlistMetadata = builder.playlistMetadata;
            this.currentMediaItemIndex = builder.currentMediaItemIndex;
            this.currentAdGroupIndex = builder.currentAdGroupIndex;
            this.currentAdIndexInAdGroup = builder.currentAdIndexInAdGroup;
            this.contentPositionMsSupplier = extrapolating;
            this.adPositionMsSupplier = extrapolating2;
            this.contentBufferedPositionMsSupplier = builder.contentBufferedPositionMsSupplier;
            this.adBufferedPositionMsSupplier = builder.adBufferedPositionMsSupplier;
            this.totalBufferedDurationMsSupplier = builder.totalBufferedDurationMsSupplier;
            this.hasPositionDiscontinuity = builder.hasPositionDiscontinuity;
            this.positionDiscontinuityReason = builder.positionDiscontinuityReason;
            this.discontinuityPositionMs = builder.discontinuityPositionMs;
        }
    }

    public static /* synthetic */ State $r8$lambda$01PmFwFxQnwjkGSZuekCQx0XfYg(SimpleBasePlayer simpleBasePlayer, List list, State state, int i, long j) {
        simpleBasePlayer.getClass();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            arrayList.add(simpleBasePlayer.getPlaceholderMediaItemData((MediaItem) list.get(i2)));
        }
        return getStateWithNewPlaylistAndPosition(state, arrayList, i, j);
    }

    /* JADX INFO: renamed from: $r8$lambda$2QoPtsjKv-8ysYtn7GFf9CDzQcA, reason: not valid java name */
    public static /* synthetic */ State m63$r8$lambda$2QoPtsjKv8ysYtn7GFf9CDzQcA(SimpleBasePlayer simpleBasePlayer, State state, int i, int i2) {
        simpleBasePlayer.getClass();
        ArrayList arrayList = new ArrayList(state.playlist);
        Util.removeRange(arrayList, i, i2);
        return getStateWithNewPlaylist(state, arrayList, simpleBasePlayer.period);
    }

    public static State $r8$lambda$80vx0IhcYf_sTqhUI2ps56IILwY(State state) {
        State.Builder builderM = SimpleBasePlayer$$ExternalSyntheticOutline0.m(state, state);
        builderM.setDeviceVolume(state.deviceVolume + 1);
        return new State(builderM);
    }

    public static State $r8$lambda$8sUgcqv8zchG4_t77rotGFbkNsI(State state) {
        State.Builder builderM = SimpleBasePlayer$$ExternalSyntheticOutline0.m(state, state);
        builderM.setDeviceVolume(Math.max(0, state.deviceVolume - 1));
        return new State(builderM);
    }

    public static State $r8$lambda$Edp6YjfCh4NeLDgXhO6MewUditw(State state, MediaMetadata mediaMetadata) {
        State.Builder builderM = SimpleBasePlayer$$ExternalSyntheticOutline0.m(state, state);
        builderM.playlistMetadata = mediaMetadata;
        return new State(builderM);
    }

    public static State $r8$lambda$GFV02exEO_0sAn7A4Tpeg_Hs0nc(State state) {
        State.Builder builderM = SimpleBasePlayer$$ExternalSyntheticOutline0.m(state, state);
        builderM.playerError = null;
        builderM.playbackState = state.timeline.isEmpty() ? 4 : 2;
        return new State(builderM);
    }

    public static /* synthetic */ State $r8$lambda$HTaPGiDj4nIWjipL95JmCLHIMHo(SimpleBasePlayer simpleBasePlayer, State state, List list, int i, int i2) {
        simpleBasePlayer.getClass();
        ArrayList arrayList = new ArrayList(state.playlist);
        for (int i3 = 0; i3 < list.size(); i3++) {
            arrayList.add(i3 + i, simpleBasePlayer.getPlaceholderMediaItemData((MediaItem) list.get(i3)));
        }
        State stateWithNewPlaylist = !state.playlist.isEmpty() ? getStateWithNewPlaylist(state, arrayList, simpleBasePlayer.period) : getStateWithNewPlaylistAndPosition(state, arrayList, state.currentMediaItemIndex, state.contentPositionMsSupplier.get());
        if (i2 >= i) {
            return stateWithNewPlaylist;
        }
        Util.removeRange(arrayList, i2, i);
        return getStateWithNewPlaylist(stateWithNewPlaylist, arrayList, simpleBasePlayer.period);
    }

    public static /* synthetic */ void $r8$lambda$HqE8U1mizpYmbh7H9rcQJnFJKFQ(SimpleBasePlayer simpleBasePlayer, ListenableFuture listenableFuture) {
        Util.castNonNull(simpleBasePlayer.state);
        simpleBasePlayer.pendingOperations.remove(listenableFuture);
        if (!simpleBasePlayer.pendingOperations.isEmpty() || simpleBasePlayer.released) {
            return;
        }
        simpleBasePlayer.updateStateAndInformListeners(simpleBasePlayer.getState(), false, false);
    }

    /* JADX INFO: renamed from: $r8$lambda$MwAxcMxcv-2oOJDGffCJtaqz46M, reason: not valid java name */
    public static /* synthetic */ void m64$r8$lambda$MwAxcMxcv2oOJDGffCJtaqz46M(SimpleBasePlayer simpleBasePlayer, Player.Listener listener, FlagSet flagSet) {
        simpleBasePlayer.getClass();
        listener.onEvents(simpleBasePlayer, new Player.Events(flagSet));
    }

    public static State $r8$lambda$N143NepUE2kUII04spvrQFUUoE0(State state, PlaybackParameters playbackParameters) {
        State.Builder builderM = SimpleBasePlayer$$ExternalSyntheticOutline0.m(state, state);
        builderM.playbackParameters = playbackParameters;
        return new State(builderM);
    }

    /* JADX INFO: renamed from: $r8$lambda$NT-wInvx3AqRen2Ap3Wig0P5jzE, reason: not valid java name */
    public static State m65$r8$lambda$NTwInvx3AqRen2Ap3Wig0P5jzE(State state, int i) {
        State.Builder builderM = SimpleBasePlayer$$ExternalSyntheticOutline0.m(state, state);
        builderM.repeatMode = i;
        return new State(builderM);
    }

    /* JADX INFO: renamed from: $r8$lambda$P-TcZE2M2cglwSSNXVp-8HtcL9k, reason: not valid java name */
    public static State m66$r8$lambda$PTcZE2M2cglwSSNXVp8HtcL9k(State state, int i) {
        state.getClass();
        State.Builder builder = new State.Builder(state);
        builder.setDeviceVolume(i);
        return new State(builder);
    }

    public static State $r8$lambda$Und2L0KqGHwU9_wx8LuBEVsvRYw(State state, boolean z) {
        State.Builder builderM = SimpleBasePlayer$$ExternalSyntheticOutline0.m(state, state);
        builderM.isDeviceMuted = z;
        return new State(builderM);
    }

    public static State $r8$lambda$WbtxR3nuRD8a0uCHmUedk96mubM(State state) {
        State.Builder builderM = SimpleBasePlayer$$ExternalSyntheticOutline0.m(state, state);
        builderM.playbackState = 1;
        builderM.totalBufferedDurationMsSupplier = PositionSupplier.ZERO;
        builderM.contentBufferedPositionMsSupplier = new SimpleBasePlayer$PositionSupplier$$ExternalSyntheticLambda1(getPositionOrDefaultInMediaItem(state.contentPositionMsSupplier.get(), state));
        builderM.adBufferedPositionMsSupplier = state.adPositionMsSupplier;
        builderM.isLoading = false;
        return new State(builderM);
    }

    public static State $r8$lambda$XnoC1vHrL1HFaZDCiQw2XTW_jlM(State state, AudioAttributes audioAttributes) {
        State.Builder builderM = SimpleBasePlayer$$ExternalSyntheticOutline0.m(state, state);
        builderM.audioAttributes = audioAttributes;
        return new State(builderM);
    }

    public static /* synthetic */ void $r8$lambda$YT_KocT6Bbx1L_8lykLTipcacck(State state, Player.Listener listener) {
        listener.onLoadingChanged(state.isLoading);
        listener.onIsLoadingChanged(state.isLoading);
    }

    /* JADX INFO: renamed from: $r8$lambda$Yx-XW4ZvUu2T4o5ykO2tFFGKszA, reason: not valid java name */
    public static /* synthetic */ State m69$r8$lambda$YxXW4ZvUu2T4o5ykO2tFFGKszA(SimpleBasePlayer simpleBasePlayer, State state, List list, int i) {
        simpleBasePlayer.getClass();
        ArrayList arrayList = new ArrayList(state.playlist);
        for (int i2 = 0; i2 < list.size(); i2++) {
            arrayList.add(i2 + i, simpleBasePlayer.getPlaceholderMediaItemData((MediaItem) list.get(i2)));
        }
        return !state.playlist.isEmpty() ? getStateWithNewPlaylist(state, arrayList, simpleBasePlayer.period) : getStateWithNewPlaylistAndPosition(state, arrayList, state.currentMediaItemIndex, state.contentPositionMsSupplier.get());
    }

    public static /* synthetic */ void $r8$lambda$b6UbJyEda3NWTH0KG3Bx8qJOhos(int i, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, Player.Listener listener) {
        listener.onPositionDiscontinuity(i);
        listener.onPositionDiscontinuity(positionInfo, positionInfo2, i);
    }

    public static State $r8$lambda$bsa6idRnf4bw2Oqh5zxPyP2DNOo(State state) {
        State.Builder builderM = SimpleBasePlayer$$ExternalSyntheticOutline0.m(state, state);
        builderM.surfaceSize = Size.ZERO;
        return new State(builderM);
    }

    /* JADX INFO: renamed from: $r8$lambda$cD2e-Jp21Fh6Maa2KxagjJYwePg, reason: not valid java name */
    public static State m71$r8$lambda$cD2eJp21Fh6Maa2KxagjJYwePg(State state) {
        State.Builder builderM = SimpleBasePlayer$$ExternalSyntheticOutline0.m(state, state);
        builderM.setDeviceVolume(Math.max(0, state.deviceVolume - 1));
        return new State(builderM);
    }

    public static /* synthetic */ State $r8$lambda$dNWc6xoZLktGAvAIanZxhQ4QND4(SimpleBasePlayer simpleBasePlayer, State state, int i, int i2, int i3) {
        simpleBasePlayer.getClass();
        ArrayList arrayList = new ArrayList(state.playlist);
        Util.moveItems(arrayList, i, i2, i3);
        return getStateWithNewPlaylist(state, arrayList, simpleBasePlayer.period);
    }

    /* JADX INFO: renamed from: $r8$lambda$e0U_mSusx48zst6CylazaO1v-6c, reason: not valid java name */
    public static State m72$r8$lambda$e0U_mSusx48zst6CylazaO1v6c(State state, TrackSelectionParameters trackSelectionParameters) {
        State.Builder builderM = SimpleBasePlayer$$ExternalSyntheticOutline0.m(state, state);
        builderM.trackSelectionParameters = trackSelectionParameters;
        return new State(builderM);
    }

    /* JADX INFO: renamed from: $r8$lambda$fPZkjbiu6b-5hguQ3SHCFJlQWKY, reason: not valid java name */
    public static State m73$r8$lambda$fPZkjbiu6b5hguQ3SHCFJlQWKY(State state) {
        State.Builder builderM = SimpleBasePlayer$$ExternalSyntheticOutline0.m(state, state);
        builderM.surfaceSize = Size.UNKNOWN;
        return new State(builderM);
    }

    /* JADX INFO: renamed from: $r8$lambda$gYa9ZLjUwJBMTGN17zgdfmm-X6c, reason: not valid java name */
    public static State m74$r8$lambda$gYa9ZLjUwJBMTGN17zgdfmmX6c(State state, int i) {
        state.getClass();
        State.Builder builder = new State.Builder(state);
        builder.setDeviceVolume(i);
        return new State(builder);
    }

    /* JADX INFO: renamed from: $r8$lambda$l-4kU7jETVij9EP62Mw5fkd2F4U, reason: not valid java name */
    public static /* synthetic */ void m76$r8$lambda$l4kU7jETVij9EP62Mw5fkd2F4U(State state, Player.Listener listener) {
        listener.onCues(state.currentCues.cues);
        listener.onCues(state.currentCues);
    }

    /* JADX INFO: renamed from: $r8$lambda$lCM_L53qGVg0CKv-jqvduK62N1Q, reason: not valid java name */
    public static State m77$r8$lambda$lCM_L53qGVg0CKvjqvduK62N1Q(State state) {
        State.Builder builderM = SimpleBasePlayer$$ExternalSyntheticOutline0.m(state, state);
        builderM.setDeviceVolume(state.deviceVolume + 1);
        return new State(builderM);
    }

    public static void $r8$lambda$ntfS0AAdTGSUikNTmXJU5vN6rXI(State state, Player.Listener listener) {
        Size size = state.surfaceSize;
        listener.onSurfaceSizeChanged(size.width, size.height);
    }

    public static State $r8$lambda$oFf2ezRzl3WKsQNn9U7BJOH5JX8(State state, SurfaceView surfaceView) {
        State.Builder builderM = SimpleBasePlayer$$ExternalSyntheticOutline0.m(state, state);
        builderM.surfaceSize = getSurfaceHolderSize(surfaceView.getHolder());
        return new State(builderM);
    }

    public static State $r8$lambda$oWReaT1FTOgsPNdwRWIaTcpe9K4(State state, SurfaceHolder surfaceHolder) {
        State.Builder builderM = SimpleBasePlayer$$ExternalSyntheticOutline0.m(state, state);
        builderM.surfaceSize = getSurfaceHolderSize(surfaceHolder);
        return new State(builderM);
    }

    /* JADX INFO: renamed from: $r8$lambda$q4V7vsJgcMGs2-qwMg3h5mwPdDE, reason: not valid java name */
    public static State m80$r8$lambda$q4V7vsJgcMGs2qwMg3h5mwPdDE(State state, float f) {
        state.getClass();
        State.Builder builder = new State.Builder(state);
        builder.setVolume(f);
        return new State(builder);
    }

    public static State $r8$lambda$qgZ3EHLOng7QhmPbrCsuhVnaw5g(State state, boolean z) {
        State.Builder builderM = SimpleBasePlayer$$ExternalSyntheticOutline0.m(state, state);
        builderM.shuffleModeEnabled = z;
        return new State(builderM);
    }

    /* JADX INFO: renamed from: $r8$lambda$qtop60og5GxSs6IGHxJUiKNbc-U, reason: not valid java name */
    public static State m81$r8$lambda$qtop60og5GxSs6IGHxJUiKNbcU(State state, boolean z) {
        State.Builder builderM = SimpleBasePlayer$$ExternalSyntheticOutline0.m(state, state);
        builderM.playWhenReady = z;
        builderM.playWhenReadyChangeReason = 1;
        return new State(builderM);
    }

    public static State $r8$lambda$vFSYNE4QUxbjSjaDyRFflXcqNRE(State state, Size size) {
        State.Builder builderM = SimpleBasePlayer$$ExternalSyntheticOutline0.m(state, state);
        builderM.surfaceSize = size;
        return new State(builderM);
    }

    public static State $r8$lambda$vttxAC27KJV5VjtH_95RMtuhSSI(State state, boolean z) {
        State.Builder builderM = SimpleBasePlayer$$ExternalSyntheticOutline0.m(state, state);
        builderM.isDeviceMuted = z;
        return new State(builderM);
    }

    public static /* synthetic */ void $r8$lambda$wLjJcXuBYIce7zDkAvTvA01EEE0(State state, Player.Listener listener) {
        PlaybackException playbackException = state.playerError;
        Util.castNonNull(playbackException);
        listener.onPlayerError(playbackException);
    }

    public SimpleBasePlayer(Looper looper) {
        this(looper, Clock.DEFAULT);
    }

    public static State buildStateForNewPosition(State.Builder builder, State state, long j, List<MediaItemData> list, int i, long j2, boolean z) {
        long positionOrDefaultInMediaItem = getPositionOrDefaultInMediaItem(j, state);
        boolean z2 = false;
        if (!list.isEmpty() && (i == -1 || i >= list.size())) {
            j2 = -9223372036854775807L;
            i = 0;
        }
        if (!list.isEmpty() && j2 == -9223372036854775807L) {
            j2 = Util.usToMs(list.get(i).defaultPositionUs);
        }
        boolean z3 = state.playlist.isEmpty() || list.isEmpty();
        if (!z3) {
            ImmutableList<MediaItemData> immutableList = state.playlist;
            int i2 = state.currentMediaItemIndex;
            if (i2 == -1) {
                i2 = 0;
            }
            if (!immutableList.get(i2).uid.equals(list.get(i).uid)) {
                z2 = true;
            }
        }
        if (z3 || z2 || j2 < positionOrDefaultInMediaItem) {
            builder.currentMediaItemIndex = i;
            builder.setCurrentAd(-1, -1);
            builder.contentPositionMs = Long.valueOf(j2);
            builder.contentBufferedPositionMsSupplier = PositionSupplier.CC.getConstant(j2);
            builder.totalBufferedDurationMsSupplier = PositionSupplier.ZERO;
        } else if (j2 == positionOrDefaultInMediaItem) {
            builder.currentMediaItemIndex = i;
            if (state.currentAdGroupIndex == -1 || !z) {
                builder.setCurrentAd(-1, -1);
                builder.totalBufferedDurationMsSupplier = PositionSupplier.CC.getConstant(getPositionOrDefaultInMediaItem(state.contentBufferedPositionMsSupplier.get(), state) - positionOrDefaultInMediaItem);
            } else {
                builder.totalBufferedDurationMsSupplier = PositionSupplier.CC.getConstant(state.adBufferedPositionMsSupplier.get() - state.adPositionMsSupplier.get());
            }
        } else {
            long jMax = Math.max(getPositionOrDefaultInMediaItem(state.contentBufferedPositionMsSupplier.get(), state), j2);
            long jMax2 = Math.max(0L, state.totalBufferedDurationMsSupplier.get() - (j2 - positionOrDefaultInMediaItem));
            builder.currentMediaItemIndex = i;
            builder.setCurrentAd(-1, -1);
            builder.contentPositionMs = Long.valueOf(j2);
            builder.contentBufferedPositionMsSupplier = PositionSupplier.CC.getConstant(jMax);
            builder.totalBufferedDurationMsSupplier = new SimpleBasePlayer$PositionSupplier$$ExternalSyntheticLambda1(jMax2);
        }
        builder.getClass();
        return new State(builder);
    }

    public static long getContentBufferedPositionMsInternal(State state) {
        return getPositionOrDefaultInMediaItem(state.contentBufferedPositionMsSupplier.get(), state);
    }

    public static long getContentPositionMsInternal(State state) {
        return getPositionOrDefaultInMediaItem(state.contentPositionMsSupplier.get(), state);
    }

    public static int getCurrentMediaItemIndexInternal(State state) {
        int i = state.currentMediaItemIndex;
        if (i != -1) {
            return i;
        }
        return 0;
    }

    public static int getCurrentPeriodIndexInternal(State state, Timeline.Window window, Timeline.Period period) {
        int currentMediaItemIndexInternal = getCurrentMediaItemIndexInternal(state);
        return state.timeline.isEmpty() ? currentMediaItemIndexInternal : getPeriodIndexFromWindowPosition(state.timeline, currentMediaItemIndexInternal, getPositionOrDefaultInMediaItem(state.contentPositionMsSupplier.get(), state), window, period);
    }

    public static long getCurrentPeriodOrAdPositionMs(State state, Object obj, Timeline.Period period) {
        return state.currentAdGroupIndex != -1 ? state.adPositionMsSupplier.get() : getPositionOrDefaultInMediaItem(state.contentPositionMsSupplier.get(), state) - Util.usToMs(state.timeline.getPeriodByUid(obj, period).positionInWindowUs);
    }

    public static Tracks getCurrentTracksInternal(State state) {
        if (state.playlist.isEmpty()) {
            return Tracks.EMPTY;
        }
        ImmutableList<MediaItemData> immutableList = state.playlist;
        int i = state.currentMediaItemIndex;
        if (i == -1) {
            i = 0;
        }
        return immutableList.get(i).tracks;
    }

    public static int getMediaItemIndexInNewPlaylist(List<MediaItemData> list, Timeline timeline, int i, Timeline.Period period) {
        if (list.isEmpty()) {
            if (i < timeline.getWindowCount()) {
                return i;
            }
            return -1;
        }
        Object periodUid = list.get(i).getPeriodUid(0);
        if (timeline.getIndexOfPeriod(periodUid) == -1) {
            return -1;
        }
        return timeline.getPeriodByUid(periodUid, period).windowIndex;
    }

    public static int getMediaItemTransitionReason(State state, State state2, int i, boolean z, Timeline.Window window) {
        Timeline timeline = state.timeline;
        Timeline timeline2 = state2.timeline;
        if (timeline2.isEmpty() && timeline.isEmpty()) {
            return -1;
        }
        if (timeline2.isEmpty() != timeline.isEmpty()) {
            return 3;
        }
        Timeline timeline3 = state.timeline;
        int i2 = state.currentMediaItemIndex;
        if (i2 == -1) {
            i2 = 0;
        }
        Object obj = timeline3.getWindow(i2, window, 0L).uid;
        Timeline timeline4 = state2.timeline;
        int i3 = state2.currentMediaItemIndex;
        if (i3 == -1) {
            i3 = 0;
        }
        Object obj2 = timeline4.getWindow(i3, window, 0L).uid;
        if ((obj instanceof PlaceholderUid) && !(obj2 instanceof PlaceholderUid)) {
            return -1;
        }
        if (!obj.equals(obj2)) {
            if (i == 0) {
                return 1;
            }
            return i == 1 ? 2 : 3;
        }
        if (i != 0 || getPositionOrDefaultInMediaItem(state.contentPositionMsSupplier.get(), state) <= getPositionOrDefaultInMediaItem(state2.contentPositionMsSupplier.get(), state2)) {
            return (i == 1 && z) ? 2 : -1;
        }
        return 0;
    }

    public static MediaMetadata getMediaMetadataInternal(State state) {
        if (state.playlist.isEmpty()) {
            return MediaMetadata.EMPTY;
        }
        ImmutableList<MediaItemData> immutableList = state.playlist;
        int i = state.currentMediaItemIndex;
        if (i == -1) {
            i = 0;
        }
        return immutableList.get(i).combinedMediaMetadata;
    }

    public static int getPeriodIndexFromWindowPosition(Timeline timeline, int i, long j, Timeline.Window window, Timeline.Period period) {
        return timeline.getIndexOfPeriod(timeline.getPeriodPositionUs(window, period, i, Util.msToUs(j)).first);
    }

    public static long getPeriodOrAdDurationMs(State state, Object obj, Timeline.Period period) {
        state.timeline.getPeriodByUid(obj, period);
        int i = state.currentAdGroupIndex;
        return Util.usToMs(i == -1 ? period.durationUs : period.getAdDurationUs(i, state.currentAdIndexInAdGroup));
    }

    public static int getPositionDiscontinuityReason(State state, State state2, boolean z, Timeline.Window window, Timeline.Period period) {
        if (state2.hasPositionDiscontinuity) {
            return state2.positionDiscontinuityReason;
        }
        if (z) {
            return 1;
        }
        if (state.playlist.isEmpty()) {
            return -1;
        }
        if (state2.playlist.isEmpty()) {
            return 4;
        }
        Object uidOfPeriod = state.timeline.getUidOfPeriod(getCurrentPeriodIndexInternal(state, window, period));
        Object uidOfPeriod2 = state2.timeline.getUidOfPeriod(getCurrentPeriodIndexInternal(state2, window, period));
        if ((uidOfPeriod instanceof PlaceholderUid) && !(uidOfPeriod2 instanceof PlaceholderUid)) {
            return -1;
        }
        if (uidOfPeriod2.equals(uidOfPeriod) && state.currentAdGroupIndex == state2.currentAdGroupIndex && state.currentAdIndexInAdGroup == state2.currentAdIndexInAdGroup) {
            long currentPeriodOrAdPositionMs = getCurrentPeriodOrAdPositionMs(state, uidOfPeriod, period);
            if (Math.abs(currentPeriodOrAdPositionMs - getCurrentPeriodOrAdPositionMs(state2, uidOfPeriod2, period)) < 1000) {
                return -1;
            }
            long periodOrAdDurationMs = getPeriodOrAdDurationMs(state, uidOfPeriod, period);
            return (periodOrAdDurationMs == -9223372036854775807L || currentPeriodOrAdPositionMs < periodOrAdDurationMs) ? 5 : 0;
        }
        if (state2.timeline.getIndexOfPeriod(uidOfPeriod) == -1) {
            return 4;
        }
        long currentPeriodOrAdPositionMs2 = getCurrentPeriodOrAdPositionMs(state, uidOfPeriod, period);
        long periodOrAdDurationMs2 = getPeriodOrAdDurationMs(state, uidOfPeriod, period);
        return (periodOrAdDurationMs2 == -9223372036854775807L || currentPeriodOrAdPositionMs2 < periodOrAdDurationMs2) ? 3 : 0;
    }

    public static Player.PositionInfo getPositionInfo(State state, boolean z, Timeline.Window window, Timeline.Period period) {
        Object obj;
        MediaItem mediaItem;
        Object obj2;
        int i;
        long positionOrDefaultInMediaItem;
        long j;
        int currentMediaItemIndexInternal = getCurrentMediaItemIndexInternal(state);
        if (state.timeline.isEmpty()) {
            obj = null;
            mediaItem = null;
            obj2 = null;
            i = -1;
        } else {
            int currentPeriodIndexInternal = getCurrentPeriodIndexInternal(state, window, period);
            obj2 = state.timeline.getPeriod(currentPeriodIndexInternal, period, true).uid;
            i = currentPeriodIndexInternal;
            obj = state.timeline.getWindow(currentMediaItemIndexInternal, window, 0L).uid;
            mediaItem = window.mediaItem;
        }
        if (z) {
            j = state.discontinuityPositionMs;
            positionOrDefaultInMediaItem = state.currentAdGroupIndex == -1 ? j : getPositionOrDefaultInMediaItem(state.contentPositionMsSupplier.get(), state);
        } else {
            long positionOrDefaultInMediaItem2 = getPositionOrDefaultInMediaItem(state.contentPositionMsSupplier.get(), state);
            positionOrDefaultInMediaItem = positionOrDefaultInMediaItem2;
            j = state.currentAdGroupIndex != -1 ? state.adPositionMsSupplier.get() : positionOrDefaultInMediaItem2;
        }
        return new Player.PositionInfo(obj, currentMediaItemIndexInternal, mediaItem, obj2, i, j, positionOrDefaultInMediaItem, state.currentAdGroupIndex, state.currentAdIndexInAdGroup);
    }

    public static long getPositionOrDefaultInMediaItem(long j, State state) {
        if (j != -9223372036854775807L) {
            return j;
        }
        if (state.playlist.isEmpty()) {
            return 0L;
        }
        ImmutableList<MediaItemData> immutableList = state.playlist;
        int i = state.currentMediaItemIndex;
        if (i == -1) {
            i = 0;
        }
        return Util.usToMs(immutableList.get(i).defaultPositionUs);
    }

    public static State getStateWithNewPlaylist(State state, List<MediaItemData> list, Timeline.Period period) {
        state.getClass();
        State.Builder builder = new State.Builder(state);
        builder.setPlaylist(list);
        Timeline timeline = builder.timeline;
        long j = state.contentPositionMsSupplier.get();
        int i = state.currentMediaItemIndex;
        if (i == -1) {
            i = 0;
        }
        int mediaItemIndexInNewPlaylist = getMediaItemIndexInNewPlaylist(state.playlist, timeline, i, period);
        long j2 = mediaItemIndexInNewPlaylist == -1 ? -9223372036854775807L : j;
        for (int i2 = i + 1; mediaItemIndexInNewPlaylist == -1 && i2 < state.playlist.size(); i2++) {
            mediaItemIndexInNewPlaylist = getMediaItemIndexInNewPlaylist(state.playlist, timeline, i2, period);
        }
        if (state.playbackState != 1 && mediaItemIndexInNewPlaylist == -1) {
            builder.playbackState = 4;
            builder.isLoading = false;
        }
        return buildStateForNewPosition(builder, state, j, list, mediaItemIndexInNewPlaylist, j2, true);
    }

    public static State getStateWithNewPlaylistAndPosition(State state, List<MediaItemData> list, int i, long j) {
        state.getClass();
        State.Builder builder = new State.Builder(state);
        builder.setPlaylist(list);
        if (state.playbackState != 1) {
            if (list.isEmpty() || (i != -1 && i >= list.size())) {
                builder.playbackState = 4;
                builder.isLoading = false;
            } else {
                builder.playbackState = 2;
            }
        }
        return buildStateForNewPosition(builder, state, state.contentPositionMsSupplier.get(), list, i, j, false);
    }

    public static Size getSurfaceHolderSize(SurfaceHolder surfaceHolder) {
        if (!surfaceHolder.getSurface().isValid()) {
            return Size.ZERO;
        }
        Rect surfaceFrame = surfaceHolder.getSurfaceFrame();
        return new Size(surfaceFrame.width(), surfaceFrame.height());
    }

    public static int getTimelineChangeReason(List<MediaItemData> list, List<MediaItemData> list2) {
        if (list.size() != list2.size()) {
            return 0;
        }
        int i = 0;
        while (true) {
            if (i >= list.size()) {
                return 1;
            }
            Object obj = list.get(i).uid;
            Object obj2 = list2.get(i).uid;
            boolean z = (obj instanceof PlaceholderUid) && !(obj2 instanceof PlaceholderUid);
            if (!obj.equals(obj2) && !z) {
                return 0;
            }
            i++;
        }
    }

    public static boolean isPlaying(State state) {
        return state.playWhenReady && state.playbackState == 3 && state.playbackSuppressionReason == 0;
    }

    @Override // androidx.media3.common.Player
    public final void addListener(Player.Listener listener) {
        ListenerSet<Player.Listener> listenerSet = this.listeners;
        listener.getClass();
        listenerSet.add(listener);
    }

    @Override // androidx.media3.common.Player
    public final void addMediaItems(int i, List<MediaItem> list) {
        verifyApplicationThreadAndInitState();
        Assertions.checkArgument(i >= 0);
        int size = this.state.playlist.size();
        if (!shouldHandleCommand(20) || list.isEmpty()) {
            return;
        }
        handleAddMediaItems(Math.min(i, size), list);
        throw null;
    }

    public final void clearVideoOutput(@Nullable Object obj) {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(27)) {
            handleClearVideoOutput(obj);
            throw null;
        }
    }

    @Override // androidx.media3.common.Player
    public final void clearVideoSurface() {
        clearVideoOutput(null);
    }

    @Override // androidx.media3.common.Player
    public final void clearVideoSurfaceHolder(@Nullable SurfaceHolder surfaceHolder) {
        clearVideoOutput(surfaceHolder);
    }

    @Override // androidx.media3.common.Player
    public final void clearVideoSurfaceView(@Nullable SurfaceView surfaceView) {
        clearVideoOutput(surfaceView);
    }

    @Override // androidx.media3.common.Player
    public final void clearVideoTextureView(@Nullable TextureView textureView) {
        clearVideoOutput(textureView);
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final void decreaseDeviceVolume() {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(26)) {
            handleDecreaseDeviceVolume(1);
            throw null;
        }
    }

    @Override // androidx.media3.common.Player
    public final Looper getApplicationLooper() {
        return this.applicationLooper;
    }

    @Override // androidx.media3.common.Player
    public final AudioAttributes getAudioAttributes() {
        verifyApplicationThreadAndInitState();
        return this.state.audioAttributes;
    }

    @Override // androidx.media3.common.Player
    public final Player.Commands getAvailableCommands() {
        verifyApplicationThreadAndInitState();
        return this.state.availableCommands;
    }

    @Override // androidx.media3.common.Player
    public final long getBufferedPosition() {
        verifyApplicationThreadAndInitState();
        return isPlayingAd() ? Math.max(this.state.adBufferedPositionMsSupplier.get(), this.state.adPositionMsSupplier.get()) : getContentBufferedPosition();
    }

    @Override // androidx.media3.common.Player
    public final long getContentBufferedPosition() {
        verifyApplicationThreadAndInitState();
        return Math.max(getContentBufferedPositionMsInternal(this.state), getContentPositionMsInternal(this.state));
    }

    @Override // androidx.media3.common.Player
    public final long getContentPosition() {
        verifyApplicationThreadAndInitState();
        return getContentPositionMsInternal(this.state);
    }

    @Override // androidx.media3.common.Player
    public final int getCurrentAdGroupIndex() {
        verifyApplicationThreadAndInitState();
        return this.state.currentAdGroupIndex;
    }

    @Override // androidx.media3.common.Player
    public final int getCurrentAdIndexInAdGroup() {
        verifyApplicationThreadAndInitState();
        return this.state.currentAdIndexInAdGroup;
    }

    @Override // androidx.media3.common.Player
    public final CueGroup getCurrentCues() {
        verifyApplicationThreadAndInitState();
        return this.state.currentCues;
    }

    @Override // androidx.media3.common.Player
    public final int getCurrentMediaItemIndex() {
        verifyApplicationThreadAndInitState();
        return getCurrentMediaItemIndexInternal(this.state);
    }

    @Override // androidx.media3.common.Player
    public final int getCurrentPeriodIndex() {
        verifyApplicationThreadAndInitState();
        return getCurrentPeriodIndexInternal(this.state, this.window, this.period);
    }

    @Override // androidx.media3.common.Player
    public final long getCurrentPosition() {
        verifyApplicationThreadAndInitState();
        return isPlayingAd() ? this.state.adPositionMsSupplier.get() : getContentPosition();
    }

    @Override // androidx.media3.common.Player
    public final Timeline getCurrentTimeline() {
        verifyApplicationThreadAndInitState();
        return this.state.timeline;
    }

    @Override // androidx.media3.common.Player
    public final Tracks getCurrentTracks() {
        verifyApplicationThreadAndInitState();
        return getCurrentTracksInternal(this.state);
    }

    @Override // androidx.media3.common.Player
    public final DeviceInfo getDeviceInfo() {
        verifyApplicationThreadAndInitState();
        return this.state.deviceInfo;
    }

    @Override // androidx.media3.common.Player
    public final int getDeviceVolume() {
        verifyApplicationThreadAndInitState();
        return this.state.deviceVolume;
    }

    @Override // androidx.media3.common.Player
    public final long getDuration() {
        verifyApplicationThreadAndInitState();
        if (!isPlayingAd()) {
            return getContentDuration();
        }
        this.state.timeline.getPeriod(getCurrentPeriodIndex(), this.period, false);
        Timeline.Period period = this.period;
        State state = this.state;
        return Util.usToMs(period.getAdDurationUs(state.currentAdGroupIndex, state.currentAdIndexInAdGroup));
    }

    @Override // androidx.media3.common.Player
    public final long getMaxSeekToPreviousPosition() {
        verifyApplicationThreadAndInitState();
        return this.state.maxSeekToPreviousPositionMs;
    }

    @Override // androidx.media3.common.Player
    public final MediaMetadata getMediaMetadata() {
        verifyApplicationThreadAndInitState();
        return getMediaMetadataInternal(this.state);
    }

    @ForOverride
    public MediaItemData getPlaceholderMediaItemData(MediaItem mediaItem) {
        MediaItemData.Builder builder = new MediaItemData.Builder(new PlaceholderUid());
        builder.mediaItem = mediaItem;
        builder.isDynamic = true;
        builder.isPlaceholder = true;
        return new MediaItemData(builder);
    }

    @Override // androidx.media3.common.Player
    public final boolean getPlayWhenReady() {
        verifyApplicationThreadAndInitState();
        return this.state.playWhenReady;
    }

    @Override // androidx.media3.common.Player
    public final PlaybackParameters getPlaybackParameters() {
        verifyApplicationThreadAndInitState();
        return this.state.playbackParameters;
    }

    @Override // androidx.media3.common.Player
    public final int getPlaybackState() {
        verifyApplicationThreadAndInitState();
        return this.state.playbackState;
    }

    @Override // androidx.media3.common.Player
    public final int getPlaybackSuppressionReason() {
        verifyApplicationThreadAndInitState();
        return this.state.playbackSuppressionReason;
    }

    @Override // androidx.media3.common.Player
    @Nullable
    public final PlaybackException getPlayerError() {
        verifyApplicationThreadAndInitState();
        return this.state.playerError;
    }

    @Override // androidx.media3.common.Player
    public final MediaMetadata getPlaylistMetadata() {
        verifyApplicationThreadAndInitState();
        return this.state.playlistMetadata;
    }

    @Override // androidx.media3.common.Player
    public final int getRepeatMode() {
        verifyApplicationThreadAndInitState();
        return this.state.repeatMode;
    }

    @Override // androidx.media3.common.Player
    public final long getSeekBackIncrement() {
        verifyApplicationThreadAndInitState();
        return this.state.seekBackIncrementMs;
    }

    @Override // androidx.media3.common.Player
    public final long getSeekForwardIncrement() {
        verifyApplicationThreadAndInitState();
        return this.state.seekForwardIncrementMs;
    }

    @Override // androidx.media3.common.Player
    public final boolean getShuffleModeEnabled() {
        verifyApplicationThreadAndInitState();
        return this.state.shuffleModeEnabled;
    }

    @ForOverride
    public abstract State getState();

    @Override // androidx.media3.common.Player
    public final Size getSurfaceSize() {
        verifyApplicationThreadAndInitState();
        return this.state.surfaceSize;
    }

    @Override // androidx.media3.common.Player
    public final long getTotalBufferedDuration() {
        verifyApplicationThreadAndInitState();
        return this.state.totalBufferedDurationMsSupplier.get();
    }

    @Override // androidx.media3.common.Player
    public final TrackSelectionParameters getTrackSelectionParameters() {
        verifyApplicationThreadAndInitState();
        return this.state.trackSelectionParameters;
    }

    @Override // androidx.media3.common.Player
    public final VideoSize getVideoSize() {
        verifyApplicationThreadAndInitState();
        return this.state.videoSize;
    }

    @Override // androidx.media3.common.Player
    public final float getVolume() {
        verifyApplicationThreadAndInitState();
        return this.state.volume;
    }

    @ForOverride
    public ListenableFuture<?> handleAddMediaItems(int i, List<MediaItem> list) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_CHANGE_MEDIA_ITEMS");
    }

    @ForOverride
    public ListenableFuture<?> handleClearVideoOutput(@Nullable Object obj) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_VIDEO_SURFACE");
    }

    @ForOverride
    public ListenableFuture<?> handleDecreaseDeviceVolume(int i) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_ADJUST_DEVICE_VOLUME or COMMAND_ADJUST_DEVICE_VOLUME_WITH_FLAGS");
    }

    @ForOverride
    public ListenableFuture<?> handleIncreaseDeviceVolume(int i) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_ADJUST_DEVICE_VOLUME or COMMAND_ADJUST_DEVICE_VOLUME_WITH_FLAGS");
    }

    @ForOverride
    public ListenableFuture<?> handleMoveMediaItems(int i, int i2, int i3) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_CHANGE_MEDIA_ITEMS");
    }

    @ForOverride
    public ListenableFuture<?> handlePrepare() {
        throw new IllegalStateException("Missing implementation to handle COMMAND_PREPARE");
    }

    @ForOverride
    public ListenableFuture<?> handleRelease() {
        throw new IllegalStateException("Missing implementation to handle COMMAND_RELEASE");
    }

    @ForOverride
    public ListenableFuture<?> handleRemoveMediaItems(int i, int i2) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_CHANGE_MEDIA_ITEMS");
    }

    @ForOverride
    public ListenableFuture<?> handleReplaceMediaItems(int i, int i2, List<MediaItem> list) {
        handleAddMediaItems(i2, list);
        throw null;
    }

    @ForOverride
    public ListenableFuture<?> handleSeek(int i, long j, int i2) {
        throw new IllegalStateException("Missing implementation to handle one of the COMMAND_SEEK_*");
    }

    @ForOverride
    public ListenableFuture<?> handleSetAudioAttributes(AudioAttributes audioAttributes, boolean z) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_AUDIO_ATTRIBUTES");
    }

    @ForOverride
    public ListenableFuture<?> handleSetDeviceMuted(boolean z, int i) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_ADJUST_DEVICE_VOLUME or COMMAND_ADJUST_DEVICE_VOLUME_WITH_FLAGS");
    }

    @ForOverride
    public ListenableFuture<?> handleSetDeviceVolume(@IntRange(from = 0) int i, int i2) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_DEVICE_VOLUME or COMMAND_SET_DEVICE_VOLUME_WITH_FLAGS");
    }

    @ForOverride
    public ListenableFuture<?> handleSetMediaItems(List<MediaItem> list, int i, long j) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_MEDIA_ITEM(S)");
    }

    @ForOverride
    public ListenableFuture<?> handleSetPlayWhenReady(boolean z) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_PLAY_PAUSE");
    }

    @ForOverride
    public ListenableFuture<?> handleSetPlaybackParameters(PlaybackParameters playbackParameters) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_SPEED_AND_PITCH");
    }

    @ForOverride
    public ListenableFuture<?> handleSetPlaylistMetadata(MediaMetadata mediaMetadata) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_PLAYLIST_METADATA");
    }

    @ForOverride
    public ListenableFuture<?> handleSetRepeatMode(int i) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_REPEAT_MODE");
    }

    @ForOverride
    public ListenableFuture<?> handleSetShuffleModeEnabled(boolean z) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_SHUFFLE_MODE");
    }

    @ForOverride
    public ListenableFuture<?> handleSetTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_TRACK_SELECTION_PARAMETERS");
    }

    @ForOverride
    public ListenableFuture<?> handleSetVideoOutput(Object obj) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_VIDEO_SURFACE");
    }

    @ForOverride
    public ListenableFuture<?> handleSetVolume(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_VOLUME");
    }

    @ForOverride
    public ListenableFuture<?> handleStop() {
        throw new IllegalStateException("Missing implementation to handle COMMAND_STOP");
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final void increaseDeviceVolume() {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(26)) {
            handleIncreaseDeviceVolume(1);
            throw null;
        }
    }

    public final void invalidateState() {
        verifyApplicationThreadAndInitState();
        if (!this.pendingOperations.isEmpty() || this.released) {
            return;
        }
        updateStateAndInformListeners(getState(), false, false);
    }

    @Override // androidx.media3.common.Player
    public final boolean isDeviceMuted() {
        verifyApplicationThreadAndInitState();
        return this.state.isDeviceMuted;
    }

    @Override // androidx.media3.common.Player
    public final boolean isLoading() {
        verifyApplicationThreadAndInitState();
        return this.state.isLoading;
    }

    @Override // androidx.media3.common.Player
    public final boolean isPlayingAd() {
        verifyApplicationThreadAndInitState();
        return this.state.currentAdGroupIndex != -1;
    }

    @Override // androidx.media3.common.Player
    public final void moveMediaItems(int i, int i2, int i3) {
        verifyApplicationThreadAndInitState();
        Assertions.checkArgument(i >= 0 && i2 >= i && i3 >= 0);
        State state = this.state;
        int size = state.playlist.size();
        if (!shouldHandleCommand(20) || size == 0 || i >= size) {
            return;
        }
        int iMin = Math.min(i2, size);
        int iMin2 = Math.min(i3, state.playlist.size() - (iMin - i));
        if (i == iMin || iMin2 == i) {
            return;
        }
        handleMoveMediaItems(i, iMin, iMin2);
        throw null;
    }

    public final void postOrRunOnApplicationHandler(Runnable runnable) {
        if (this.applicationHandler.getLooper() == Looper.myLooper()) {
            runnable.run();
        } else {
            this.applicationHandler.post(runnable);
        }
    }

    @Override // androidx.media3.common.Player
    public final void prepare() {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(2)) {
            handlePrepare();
            throw null;
        }
    }

    @Override // androidx.media3.common.Player
    public final void release() {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(32)) {
            handleRelease();
            throw null;
        }
    }

    @Override // androidx.media3.common.Player
    public final void removeListener(Player.Listener listener) {
        verifyApplicationThreadAndInitState();
        this.listeners.remove(listener);
    }

    @Override // androidx.media3.common.Player
    public final void removeMediaItems(int i, int i2) {
        int iMin;
        verifyApplicationThreadAndInitState();
        Assertions.checkArgument(i >= 0 && i2 >= i);
        int size = this.state.playlist.size();
        if (!shouldHandleCommand(20) || size == 0 || i >= size || i == (iMin = Math.min(i2, size))) {
            return;
        }
        handleRemoveMediaItems(i, iMin);
        throw null;
    }

    @Override // androidx.media3.common.Player
    public final void replaceMediaItems(final int i, int i2, final List<MediaItem> list) {
        verifyApplicationThreadAndInitState();
        Assertions.checkArgument(i >= 0 && i <= i2);
        final State state = this.state;
        int size = state.playlist.size();
        if (!shouldHandleCommand(20) || i > size) {
            return;
        }
        final int iMin = Math.min(i2, size);
        updateStateForPendingOperation(handleReplaceMediaItems(i, iMin, list), new Supplier() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda35
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return SimpleBasePlayer.$r8$lambda$HTaPGiDj4nIWjipL95JmCLHIMHo(this.f$0, state, list, iMin, i);
            }
        }, false, false);
    }

    @Override // androidx.media3.common.BasePlayer
    @VisibleForTesting(otherwise = 4)
    public final void seekTo(int i, long j, int i2, boolean z) {
        verifyApplicationThreadAndInitState();
        Assertions.checkArgument(i >= 0);
        State state = this.state;
        if (!shouldHandleCommand(i2) || isPlayingAd()) {
            return;
        }
        if (state.playlist.isEmpty() || i < state.playlist.size()) {
            handleSeek(i, j, i2);
            throw null;
        }
    }

    @Override // androidx.media3.common.Player
    public final void setAudioAttributes(AudioAttributes audioAttributes, boolean z) {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(35)) {
            handleSetAudioAttributes(audioAttributes, z);
            throw null;
        }
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final void setDeviceMuted(boolean z) {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(26)) {
            handleSetDeviceMuted(z, 1);
            throw null;
        }
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final void setDeviceVolume(int i) {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(25)) {
            handleSetDeviceVolume(i, 1);
            throw null;
        }
    }

    @Override // androidx.media3.common.Player
    public final void setMediaItems(List<MediaItem> list, boolean z) {
        verifyApplicationThreadAndInitState();
        setMediaItemsInternal(list, z ? -1 : this.state.currentMediaItemIndex, z ? -9223372036854775807L : this.state.contentPositionMsSupplier.get());
    }

    @RequiresNonNull({"state"})
    public final void setMediaItemsInternal(List<MediaItem> list, int i, long j) {
        Assertions.checkArgument(i == -1 || i >= 0);
        if (shouldHandleCommand(20) || (list.size() == 1 && shouldHandleCommand(31))) {
            handleSetMediaItems(list, i, j);
            throw null;
        }
    }

    @Override // androidx.media3.common.Player
    public final void setPlayWhenReady(boolean z) {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(1)) {
            handleSetPlayWhenReady(z);
            throw null;
        }
    }

    @Override // androidx.media3.common.Player
    public final void setPlaybackParameters(PlaybackParameters playbackParameters) {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(13)) {
            handleSetPlaybackParameters(playbackParameters);
            throw null;
        }
    }

    @Override // androidx.media3.common.Player
    public final void setPlaylistMetadata(MediaMetadata mediaMetadata) {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(19)) {
            handleSetPlaylistMetadata(mediaMetadata);
            throw null;
        }
    }

    @Override // androidx.media3.common.Player
    public final void setRepeatMode(int i) {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(15)) {
            handleSetRepeatMode(i);
            throw null;
        }
    }

    @Override // androidx.media3.common.Player
    public final void setShuffleModeEnabled(boolean z) {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(14)) {
            handleSetShuffleModeEnabled(z);
            throw null;
        }
    }

    @Override // androidx.media3.common.Player
    public final void setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(29)) {
            handleSetTrackSelectionParameters(trackSelectionParameters);
            throw null;
        }
    }

    @Override // androidx.media3.common.Player
    public final void setVideoSurface(@Nullable Surface surface) {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(27)) {
            if (surface == null) {
                clearVideoOutput(null);
            } else {
                handleSetVideoOutput(surface);
                throw null;
            }
        }
    }

    @Override // androidx.media3.common.Player
    public final void setVideoSurfaceHolder(@Nullable SurfaceHolder surfaceHolder) {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(27)) {
            if (surfaceHolder == null) {
                clearVideoOutput(null);
            } else {
                handleSetVideoOutput(surfaceHolder);
                throw null;
            }
        }
    }

    @Override // androidx.media3.common.Player
    public final void setVideoSurfaceView(@Nullable SurfaceView surfaceView) {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(27)) {
            if (surfaceView == null) {
                clearVideoOutput(null);
            } else {
                handleSetVideoOutput(surfaceView);
                throw null;
            }
        }
    }

    @Override // androidx.media3.common.Player
    public final void setVideoTextureView(@Nullable TextureView textureView) {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(27)) {
            if (textureView == null) {
                clearVideoOutput(null);
                return;
            }
            if (textureView.isAvailable()) {
                new Size(textureView.getWidth(), textureView.getHeight());
            } else {
                Size size = Size.ZERO;
            }
            handleSetVideoOutput(textureView);
            throw null;
        }
    }

    @Override // androidx.media3.common.Player
    public final void setVolume(float f) {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(24)) {
            handleSetVolume(f);
            throw null;
        }
    }

    @RequiresNonNull({"state"})
    public final boolean shouldHandleCommand(int i) {
        return !this.released && this.state.availableCommands.contains(i);
    }

    @Override // androidx.media3.common.Player
    public final void stop() {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(3)) {
            handleStop();
            throw null;
        }
    }

    @RequiresNonNull({"state"})
    public final void updateStateAndInformListeners(final State state, boolean z, boolean z2) {
        final MediaItem mediaItem;
        State state2 = this.state;
        this.state = state;
        if (state.hasPositionDiscontinuity || state.newlyRenderedFirstFrame) {
            State.Builder builderM = SimpleBasePlayer$$ExternalSyntheticOutline0.m(state, state);
            builderM.hasPositionDiscontinuity = false;
            builderM.newlyRenderedFirstFrame = false;
            this.state = new State(builderM);
        }
        boolean z3 = state2.playWhenReady != state.playWhenReady;
        boolean z4 = state2.playbackState != state.playbackState;
        Tracks currentTracksInternal = getCurrentTracksInternal(state2);
        final Tracks currentTracksInternal2 = getCurrentTracksInternal(state);
        MediaMetadata mediaMetadataInternal = getMediaMetadataInternal(state2);
        final MediaMetadata mediaMetadataInternal2 = getMediaMetadataInternal(state);
        final int positionDiscontinuityReason = getPositionDiscontinuityReason(state2, state, z, this.window, this.period);
        boolean zEquals = state2.timeline.equals(state.timeline);
        final int mediaItemTransitionReason = getMediaItemTransitionReason(state2, state, positionDiscontinuityReason, z2, this.window);
        if (!zEquals) {
            final int timelineChangeReason = getTimelineChangeReason(state2.playlist, state.playlist);
            this.listeners.queueEvent(0, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda4
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    Player.Listener listener = (Player.Listener) obj;
                    listener.onTimelineChanged(state.timeline, timelineChangeReason);
                }
            });
        }
        if (positionDiscontinuityReason != -1) {
            final Player.PositionInfo positionInfo = getPositionInfo(state2, false, this.window, this.period);
            final Player.PositionInfo positionInfo2 = getPositionInfo(state, state.hasPositionDiscontinuity, this.window, this.period);
            this.listeners.queueEvent(11, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda15
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    SimpleBasePlayer.$r8$lambda$b6UbJyEda3NWTH0KG3Bx8qJOhos(positionDiscontinuityReason, positionInfo, positionInfo2, (Player.Listener) obj);
                }
            });
        }
        if (mediaItemTransitionReason != -1) {
            if (state.timeline.isEmpty()) {
                mediaItem = null;
            } else {
                ImmutableList<MediaItemData> immutableList = state.playlist;
                int i = state.currentMediaItemIndex;
                if (i == -1) {
                    i = 0;
                }
                mediaItem = immutableList.get(i).mediaItem;
            }
            this.listeners.queueEvent(1, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda26
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onMediaItemTransition(mediaItem, mediaItemTransitionReason);
                }
            });
        }
        if (!Util.areEqual(state2.playerError, state.playerError)) {
            this.listeners.queueEvent(10, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda28
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlayerErrorChanged(state.playerError);
                }
            });
            if (state.playerError != null) {
                this.listeners.queueEvent(10, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda29
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        SimpleBasePlayer.$r8$lambda$wLjJcXuBYIce7zDkAvTvA01EEE0(state, (Player.Listener) obj);
                    }
                });
            }
        }
        if (!state2.trackSelectionParameters.equals(state.trackSelectionParameters)) {
            this.listeners.queueEvent(19, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda30
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onTrackSelectionParametersChanged(state.trackSelectionParameters);
                }
            });
        }
        if (!currentTracksInternal.equals(currentTracksInternal2)) {
            this.listeners.queueEvent(2, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda31
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onTracksChanged(currentTracksInternal2);
                }
            });
        }
        if (!mediaMetadataInternal.equals(mediaMetadataInternal2)) {
            this.listeners.queueEvent(14, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda32
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onMediaMetadataChanged(mediaMetadataInternal2);
                }
            });
        }
        if (state2.isLoading != state.isLoading) {
            this.listeners.queueEvent(3, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda33
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    SimpleBasePlayer.$r8$lambda$YT_KocT6Bbx1L_8lykLTipcacck(state, (Player.Listener) obj);
                }
            });
        }
        if (z3 || z4) {
            this.listeners.queueEvent(-1, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda34
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    SimpleBasePlayer.State state3 = state;
                    ((Player.Listener) obj).onPlayerStateChanged(state3.playWhenReady, state3.playbackState);
                }
            });
        }
        if (z4) {
            this.listeners.queueEvent(4, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda5
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlaybackStateChanged(state.playbackState);
                }
            });
        }
        if (z3 || state2.playWhenReadyChangeReason != state.playWhenReadyChangeReason) {
            this.listeners.queueEvent(5, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda6
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    SimpleBasePlayer.State state3 = state;
                    ((Player.Listener) obj).onPlayWhenReadyChanged(state3.playWhenReady, state3.playWhenReadyChangeReason);
                }
            });
        }
        if (state2.playbackSuppressionReason != state.playbackSuppressionReason) {
            this.listeners.queueEvent(6, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda7
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlaybackSuppressionReasonChanged(state.playbackSuppressionReason);
                }
            });
        }
        if (isPlaying(state2) != isPlaying(state)) {
            this.listeners.queueEvent(7, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda8
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onIsPlayingChanged(SimpleBasePlayer.isPlaying(state));
                }
            });
        }
        if (!state2.playbackParameters.equals(state.playbackParameters)) {
            this.listeners.queueEvent(12, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda9
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlaybackParametersChanged(state.playbackParameters);
                }
            });
        }
        if (state2.repeatMode != state.repeatMode) {
            this.listeners.queueEvent(8, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda10
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onRepeatModeChanged(state.repeatMode);
                }
            });
        }
        if (state2.shuffleModeEnabled != state.shuffleModeEnabled) {
            this.listeners.queueEvent(9, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda11
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onShuffleModeEnabledChanged(state.shuffleModeEnabled);
                }
            });
        }
        if (state2.seekBackIncrementMs != state.seekBackIncrementMs) {
            this.listeners.queueEvent(16, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda12
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onSeekBackIncrementChanged(state.seekBackIncrementMs);
                }
            });
        }
        if (state2.seekForwardIncrementMs != state.seekForwardIncrementMs) {
            this.listeners.queueEvent(17, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda13
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onSeekForwardIncrementChanged(state.seekForwardIncrementMs);
                }
            });
        }
        if (state2.maxSeekToPreviousPositionMs != state.maxSeekToPreviousPositionMs) {
            this.listeners.queueEvent(18, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda14
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onMaxSeekToPreviousPositionChanged(state.maxSeekToPreviousPositionMs);
                }
            });
        }
        if (!state2.audioAttributes.equals(state.audioAttributes)) {
            this.listeners.queueEvent(20, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda16
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onAudioAttributesChanged(state.audioAttributes);
                }
            });
        }
        if (!state2.videoSize.equals(state.videoSize)) {
            this.listeners.queueEvent(25, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda17
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onVideoSizeChanged(state.videoSize);
                }
            });
        }
        if (!state2.deviceInfo.equals(state.deviceInfo)) {
            this.listeners.queueEvent(29, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda18
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onDeviceInfoChanged(state.deviceInfo);
                }
            });
        }
        if (!state2.playlistMetadata.equals(state.playlistMetadata)) {
            this.listeners.queueEvent(15, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda19
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlaylistMetadataChanged(state.playlistMetadata);
                }
            });
        }
        if (state.newlyRenderedFirstFrame) {
            this.listeners.queueEvent(26, new SimpleBasePlayer$$ExternalSyntheticLambda20());
        }
        if (!state2.surfaceSize.equals(state.surfaceSize)) {
            this.listeners.queueEvent(24, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda21
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    SimpleBasePlayer.$r8$lambda$ntfS0AAdTGSUikNTmXJU5vN6rXI(state, (Player.Listener) obj);
                }
            });
        }
        if (state2.volume != state.volume) {
            this.listeners.queueEvent(22, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda22
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onVolumeChanged(state.volume);
                }
            });
        }
        if (state2.deviceVolume != state.deviceVolume || state2.isDeviceMuted != state.isDeviceMuted) {
            this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda23
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    SimpleBasePlayer.State state3 = state;
                    ((Player.Listener) obj).onDeviceVolumeChanged(state3.deviceVolume, state3.isDeviceMuted);
                }
            });
        }
        if (!state2.currentCues.equals(state.currentCues)) {
            this.listeners.queueEvent(27, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda24
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    SimpleBasePlayer.m76$r8$lambda$l4kU7jETVij9EP62Mw5fkd2F4U(state, (Player.Listener) obj);
                }
            });
        }
        if (!state2.timedMetadata.equals(state.timedMetadata) && state.timedMetadata.presentationTimeUs != -9223372036854775807L) {
            this.listeners.queueEvent(28, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda25
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onMetadata(state.timedMetadata);
                }
            });
        }
        if (!state2.availableCommands.equals(state.availableCommands)) {
            this.listeners.queueEvent(13, new ListenerSet.Event() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda27
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onAvailableCommandsChanged(state.availableCommands);
                }
            });
        }
        this.listeners.flushEvents();
    }

    @RequiresNonNull({"state"})
    public final void updateStateForPendingOperation(ListenableFuture<?> listenableFuture, Supplier<State> supplier) {
        updateStateForPendingOperation(listenableFuture, supplier, false, false);
    }

    public final void verifyApplicationThread() {
        if (Thread.currentThread() != this.applicationLooper.getThread()) {
            throw new IllegalStateException(Util.formatInvariant("Player is accessed on the wrong thread.\nCurrent thread: '%s'\nExpected thread: '%s'\n", Thread.currentThread().getName(), this.applicationLooper.getThread().getName()));
        }
    }

    @EnsuresNonNull({"state"})
    public final void verifyApplicationThreadAndInitState() {
        verifyApplicationThread();
        if (this.state == null) {
            this.state = getState();
        }
    }

    public SimpleBasePlayer(Looper looper, Clock clock) {
        this.applicationLooper = looper;
        this.applicationHandler = clock.createHandler(looper, null);
        this.pendingOperations = new HashSet<>();
        this.period = new Timeline.Period();
        this.listeners = new ListenerSet<>(looper, clock, new ListenerSet.IterationFinishedEvent() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda45
            @Override // androidx.media3.common.util.ListenerSet.IterationFinishedEvent
            public final void invoke(Object obj, FlagSet flagSet) {
                SimpleBasePlayer.m64$r8$lambda$MwAxcMxcv2oOJDGffCJtaqz46M(this.f$0, (Player.Listener) obj, flagSet);
            }
        });
    }

    @Override // androidx.media3.common.Player
    public final void clearVideoSurface(@Nullable Surface surface) {
        clearVideoOutput(surface);
    }

    @RequiresNonNull({"state"})
    public final void updateStateForPendingOperation(final ListenableFuture<?> listenableFuture, Supplier<State> supplier, boolean z, boolean z2) {
        if (listenableFuture.isDone() && this.pendingOperations.isEmpty()) {
            updateStateAndInformListeners(getState(), z, z2);
            return;
        }
        this.pendingOperations.add(listenableFuture);
        updateStateAndInformListeners(getPlaceholderState(supplier.get()), z, z2);
        listenableFuture.addListener(new Runnable() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda38
            @Override // java.lang.Runnable
            public final void run() {
                SimpleBasePlayer.$r8$lambda$HqE8U1mizpYmbh7H9rcQJnFJKFQ(this.f$0, listenableFuture);
            }
        }, new Executor() { // from class: androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda39
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                this.f$0.postOrRunOnApplicationHandler(runnable);
            }
        });
    }

    @Override // androidx.media3.common.Player
    public final void decreaseDeviceVolume(int i) {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(34)) {
            handleDecreaseDeviceVolume(i);
            throw null;
        }
    }

    @Override // androidx.media3.common.Player
    public final void increaseDeviceVolume(int i) {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(34)) {
            handleIncreaseDeviceVolume(i);
            throw null;
        }
    }

    @Override // androidx.media3.common.Player
    public final void setDeviceMuted(boolean z, int i) {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(34)) {
            handleSetDeviceMuted(z, i);
            throw null;
        }
    }

    @Override // androidx.media3.common.Player
    public final void setDeviceVolume(int i, int i2) {
        verifyApplicationThreadAndInitState();
        if (shouldHandleCommand(33)) {
            handleSetDeviceVolume(i, i2);
            throw null;
        }
    }

    @Override // androidx.media3.common.Player
    public final void setMediaItems(List<MediaItem> list, int i, long j) {
        verifyApplicationThreadAndInitState();
        if (i == -1) {
            State state = this.state;
            int i2 = state.currentMediaItemIndex;
            long j2 = state.contentPositionMsSupplier.get();
            i = i2;
            j = j2;
        }
        setMediaItemsInternal(list, i, j);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface PositionSupplier {
        public static final PositionSupplier ZERO = CC.getConstant(0);

        long get();

        /* JADX INFO: renamed from: androidx.media3.common.SimpleBasePlayer$PositionSupplier$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public final /* synthetic */ class CC {
            static {
                PositionSupplier positionSupplier = PositionSupplier.ZERO;
            }

            public static PositionSupplier getConstant(long j) {
                return new SimpleBasePlayer$PositionSupplier$$ExternalSyntheticLambda1(j);
            }

            public static PositionSupplier getExtrapolating(final long j, final float f) {
                final long jElapsedRealtime = SystemClock.elapsedRealtime();
                return new PositionSupplier() { // from class: androidx.media3.common.SimpleBasePlayer$PositionSupplier$$ExternalSyntheticLambda0
                    @Override // androidx.media3.common.SimpleBasePlayer.PositionSupplier
                    public final long get() {
                        return SimpleBasePlayer.PositionSupplier.CC.lambda$getExtrapolating$1(j, jElapsedRealtime, f);
                    }
                };
            }

            public static /* synthetic */ long lambda$getExtrapolating$1(long j, long j2, float f) {
                return j + ((long) ((SystemClock.elapsedRealtime() - j2) * f));
            }

            public static /* synthetic */ long lambda$getConstant$0(long j) {
                return j;
            }
        }
    }

    public static /* synthetic */ State $r8$lambda$zHfRCGGsqZ4s866XdR0PjuUvXuE(State state) {
        return state;
    }

    @ForOverride
    public State getPlaceholderState(State state) {
        return state;
    }

    public static /* synthetic */ ListenableFuture $r8$lambda$L7WDRhdT4VrAM8hs9p07VRosxyQ(ListenableFuture listenableFuture, Object obj) {
        return listenableFuture;
    }
}
