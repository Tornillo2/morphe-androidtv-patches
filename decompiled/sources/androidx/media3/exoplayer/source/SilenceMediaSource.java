package androidx.media3.exoplayer.source;

import android.net.Uri;
import androidx.annotation.GuardedBy;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.TransferListener;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class SilenceMediaSource extends BaseMediaSource {
    public static final int CHANNEL_COUNT = 2;
    public static final Format FORMAT;
    public static final String MEDIA_ID = "SilenceMediaSource";
    public static final MediaItem MEDIA_ITEM;
    public static final int PCM_ENCODING = 2;
    public static final int SAMPLE_RATE_HZ = 44100;
    public static final byte[] SILENCE_SAMPLE;
    public final long durationUs;

    @GuardedBy("this")
    public MediaItem mediaItem;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Factory {
        public long durationUs;

        @Nullable
        public Object tag;

        public SilenceMediaSource createMediaSource() {
            Assertions.checkState(this.durationUs > 0);
            long j = this.durationUs;
            MediaItem mediaItem = SilenceMediaSource.MEDIA_ITEM;
            mediaItem.getClass();
            MediaItem.Builder builder = new MediaItem.Builder(mediaItem);
            builder.tag = this.tag;
            return new SilenceMediaSource(j, builder.build());
        }

        @CanIgnoreReturnValue
        public Factory setDurationUs(@IntRange(from = 1) long j) {
            this.durationUs = j;
            return this;
        }

        @CanIgnoreReturnValue
        public Factory setTag(@Nullable Object obj) {
            this.tag = obj;
            return this;
        }
    }

    static {
        Format.Builder builder = new Format.Builder();
        builder.sampleMimeType = MimeTypes.normalizeMimeType("audio/raw");
        builder.channelCount = 2;
        builder.sampleRate = 44100;
        builder.pcmEncoding = 2;
        Format format = new Format(builder);
        FORMAT = format;
        MediaItem.Builder builder2 = new MediaItem.Builder();
        builder2.mediaId = "SilenceMediaSource";
        builder2.uri = Uri.EMPTY;
        builder2.mimeType = format.sampleMimeType;
        MEDIA_ITEM = builder2.build();
        SILENCE_SAMPLE = new byte[4096];
    }

    public static long getAudioByteCount(long j) {
        return ((long) Util.getPcmFrameSize(2, 2)) * ((j * 44100) / 1000000);
    }

    public static long getAudioPositionUs(long j) {
        return ((j / ((long) Util.getPcmFrameSize(2, 2))) * 1000000) / 44100;
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public boolean canUpdateMediaItem(MediaItem mediaItem) {
        return true;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        return new SilenceMediaPeriod(this.durationUs);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public synchronized MediaItem getMediaItem() {
        return this.mediaItem;
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void prepareSourceInternal(@Nullable TransferListener transferListener) {
        refreshSourceInfo(new SinglePeriodTimeline(this.durationUs, true, false, false, (Object) null, getMediaItem()));
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public synchronized void updateMediaItem(MediaItem mediaItem) {
        this.mediaItem = mediaItem;
    }

    public SilenceMediaSource(long j) {
        this(j, MEDIA_ITEM);
    }

    public SilenceMediaSource(long j, MediaItem mediaItem) {
        Assertions.checkArgument(j >= 0);
        this.durationUs = j;
        this.mediaItem = mediaItem;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SilenceMediaPeriod implements MediaPeriod {
        public static final TrackGroupArray TRACKS = new TrackGroupArray(new TrackGroup("", SilenceMediaSource.FORMAT));
        public final long durationUs;
        public final ArrayList<SampleStream> sampleStreams = new ArrayList<>();

        public SilenceMediaPeriod(long j) {
            this.durationUs = j;
        }

        public final long constrainSeekPosition(long j) {
            return Util.constrainValue(j, 0L, this.durationUs);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public boolean continueLoading(LoadingInfo loadingInfo) {
            return false;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
            return constrainSeekPosition(j);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public long getBufferedPositionUs() {
            return Long.MIN_VALUE;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public long getNextLoadPositionUs() {
            return Long.MIN_VALUE;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public List getStreamKeys(List list) {
            return Collections.EMPTY_LIST;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public TrackGroupArray getTrackGroups() {
            return TRACKS;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public boolean isLoading() {
            return false;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public void prepare(MediaPeriod.Callback callback, long j) {
            callback.onPrepared(this);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public long readDiscontinuity() {
            return -9223372036854775807L;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public long seekToUs(long j) {
            long jConstrainSeekPosition = constrainSeekPosition(j);
            for (int i = 0; i < this.sampleStreams.size(); i++) {
                ((SilenceSampleStream) this.sampleStreams.get(i)).seekTo(jConstrainSeekPosition);
            }
            return jConstrainSeekPosition;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
            long jConstrainSeekPosition = constrainSeekPosition(j);
            for (int i = 0; i < exoTrackSelectionArr.length; i++) {
                SampleStream sampleStream = sampleStreamArr[i];
                if (sampleStream != null && (exoTrackSelectionArr[i] == null || !zArr[i])) {
                    this.sampleStreams.remove(sampleStream);
                    sampleStreamArr[i] = null;
                }
                if (sampleStreamArr[i] == null && exoTrackSelectionArr[i] != null) {
                    SilenceSampleStream silenceSampleStream = new SilenceSampleStream(this.durationUs);
                    silenceSampleStream.seekTo(jConstrainSeekPosition);
                    this.sampleStreams.add(silenceSampleStream);
                    sampleStreamArr[i] = silenceSampleStream;
                    zArr2[i] = true;
                }
            }
            return jConstrainSeekPosition;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public void maybeThrowPrepareError() {
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public void reevaluateBuffer(long j) {
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public void discardBuffer(long j, boolean z) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SilenceSampleStream implements SampleStream {
        public final long durationBytes;
        public long positionBytes;
        public boolean sentFormat;

        public SilenceSampleStream(long j) {
            this.durationBytes = SilenceMediaSource.getAudioByteCount(j);
            seekTo(0L);
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public boolean isReady() {
            return true;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i) {
            if (!this.sentFormat || (i & 2) != 0) {
                formatHolder.format = SilenceMediaSource.FORMAT;
                this.sentFormat = true;
                return -5;
            }
            long j = this.durationBytes;
            long j2 = this.positionBytes;
            long j3 = j - j2;
            if (j3 == 0) {
                decoderInputBuffer.addFlag(4);
                return -4;
            }
            decoderInputBuffer.timeUs = SilenceMediaSource.getAudioPositionUs(j2);
            decoderInputBuffer.addFlag(1);
            byte[] bArr = SilenceMediaSource.SILENCE_SAMPLE;
            int iMin = (int) Math.min(bArr.length, j3);
            if ((i & 4) == 0) {
                decoderInputBuffer.ensureSpaceForWrite(iMin);
                decoderInputBuffer.data.put(bArr, 0, iMin);
            }
            if ((i & 1) == 0) {
                this.positionBytes += (long) iMin;
            }
            return -4;
        }

        public void seekTo(long j) {
            this.positionBytes = Util.constrainValue(SilenceMediaSource.getAudioByteCount(j), 0L, this.durationBytes);
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int skipData(long j) {
            long j2 = this.positionBytes;
            seekTo(j);
            return (int) ((this.positionBytes - j2) / ((long) SilenceMediaSource.SILENCE_SAMPLE.length));
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public void maybeThrowError() {
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() {
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
    }
}
