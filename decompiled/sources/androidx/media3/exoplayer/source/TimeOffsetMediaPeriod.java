package androidx.media3.exoplayer.source;

import androidx.media3.common.StreamKey;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class TimeOffsetMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    public MediaPeriod.Callback callback;
    public final MediaPeriod mediaPeriod;
    public final long timeOffsetUs;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class TimeOffsetSampleStream implements SampleStream {
        public final SampleStream sampleStream;
        public final long timeOffsetUs;

        public TimeOffsetSampleStream(SampleStream sampleStream, long j) {
            this.sampleStream = sampleStream;
            this.timeOffsetUs = j;
        }

        public SampleStream getChildStream() {
            return this.sampleStream;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public boolean isReady() {
            return this.sampleStream.isReady();
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public void maybeThrowError() throws IOException {
            this.sampleStream.maybeThrowError();
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i) {
            int data = this.sampleStream.readData(formatHolder, decoderInputBuffer, i);
            if (data == -4) {
                decoderInputBuffer.timeUs += this.timeOffsetUs;
            }
            return data;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int skipData(long j) {
            return this.sampleStream.skipData(j - this.timeOffsetUs);
        }
    }

    public TimeOffsetMediaPeriod(MediaPeriod mediaPeriod, long j) {
        this.mediaPeriod = mediaPeriod;
        this.timeOffsetUs = j;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo loadingInfo) {
        MediaPeriod mediaPeriod = this.mediaPeriod;
        loadingInfo.getClass();
        LoadingInfo.Builder builder = new LoadingInfo.Builder(loadingInfo);
        builder.playbackPositionUs = loadingInfo.playbackPositionUs - this.timeOffsetUs;
        return mediaPeriod.continueLoading(new LoadingInfo(builder));
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long j, boolean z) {
        this.mediaPeriod.discardBuffer(j - this.timeOffsetUs, z);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        return this.mediaPeriod.getAdjustedSeekPositionUs(j - this.timeOffsetUs, seekParameters) + this.timeOffsetUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        long bufferedPositionUs = this.mediaPeriod.getBufferedPositionUs();
        if (bufferedPositionUs == Long.MIN_VALUE) {
            return Long.MIN_VALUE;
        }
        return bufferedPositionUs + this.timeOffsetUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        long nextLoadPositionUs = this.mediaPeriod.getNextLoadPositionUs();
        if (nextLoadPositionUs == Long.MIN_VALUE) {
            return Long.MIN_VALUE;
        }
        return nextLoadPositionUs + this.timeOffsetUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public List<StreamKey> getStreamKeys(List<ExoTrackSelection> list) {
        return this.mediaPeriod.getStreamKeys(list);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.mediaPeriod.getTrackGroups();
    }

    public MediaPeriod getWrappedMediaPeriod() {
        return this.mediaPeriod;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return this.mediaPeriod.isLoading();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        this.mediaPeriod.maybeThrowPrepareError();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod mediaPeriod) {
        MediaPeriod.Callback callback = this.callback;
        callback.getClass();
        callback.onPrepared(this);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j) {
        this.callback = callback;
        this.mediaPeriod.prepare(this, j - this.timeOffsetUs);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long readDiscontinuity() {
        long discontinuity = this.mediaPeriod.readDiscontinuity();
        if (discontinuity == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        return discontinuity + this.timeOffsetUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long j) {
        this.mediaPeriod.reevaluateBuffer(j - this.timeOffsetUs);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long seekToUs(long j) {
        return this.mediaPeriod.seekToUs(j - this.timeOffsetUs) + this.timeOffsetUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        SampleStream[] sampleStreamArr2 = new SampleStream[sampleStreamArr.length];
        int i = 0;
        while (true) {
            SampleStream sampleStream = null;
            if (i >= sampleStreamArr.length) {
                break;
            }
            TimeOffsetSampleStream timeOffsetSampleStream = (TimeOffsetSampleStream) sampleStreamArr[i];
            if (timeOffsetSampleStream != null) {
                sampleStream = timeOffsetSampleStream.sampleStream;
            }
            sampleStreamArr2[i] = sampleStream;
            i++;
        }
        long jSelectTracks = this.mediaPeriod.selectTracks(exoTrackSelectionArr, zArr, sampleStreamArr2, zArr2, j - this.timeOffsetUs);
        for (int i2 = 0; i2 < sampleStreamArr.length; i2++) {
            SampleStream sampleStream2 = sampleStreamArr2[i2];
            if (sampleStream2 == null) {
                sampleStreamArr[i2] = null;
            } else {
                SampleStream sampleStream3 = sampleStreamArr[i2];
                if (sampleStream3 == null || ((TimeOffsetSampleStream) sampleStream3).sampleStream != sampleStream2) {
                    sampleStreamArr[i2] = new TimeOffsetSampleStream(sampleStream2, this.timeOffsetUs);
                }
            }
        }
        return jSelectTracks + this.timeOffsetUs;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        MediaPeriod.Callback callback = this.callback;
        callback.getClass();
        callback.onContinueLoadingRequested(this);
    }
}
