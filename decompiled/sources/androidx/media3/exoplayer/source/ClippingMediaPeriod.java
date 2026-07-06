package androidx.media3.exoplayer.source;

import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.StreamKey;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.ClippingMediaSource;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class ClippingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {

    @Nullable
    public MediaPeriod.Callback callback;

    @Nullable
    public ClippingMediaSource.IllegalClippingException clippingError;
    public long endUs;
    public final MediaPeriod mediaPeriod;
    public long pendingInitialDiscontinuityPositionUs;
    public ClippingSampleStream[] sampleStreams = new ClippingSampleStream[0];
    public long startUs;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class ClippingSampleStream implements SampleStream {
        public final SampleStream childStream;
        public boolean sentEos;

        public ClippingSampleStream(SampleStream sampleStream) {
            this.childStream = sampleStream;
        }

        public void clearSentEos() {
            this.sentEos = false;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public boolean isReady() {
            return !ClippingMediaPeriod.this.isPendingInitialDiscontinuity() && this.childStream.isReady();
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public void maybeThrowError() throws IOException {
            this.childStream.maybeThrowError();
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i) {
            if (ClippingMediaPeriod.this.isPendingInitialDiscontinuity()) {
                return -3;
            }
            if (this.sentEos) {
                decoderInputBuffer.flags = 4;
                return -4;
            }
            long bufferedPositionUs = ClippingMediaPeriod.this.getBufferedPositionUs();
            int data = this.childStream.readData(formatHolder, decoderInputBuffer, i);
            if (data != -5) {
                long j = ClippingMediaPeriod.this.endUs;
                if (j == Long.MIN_VALUE || ((data != -4 || decoderInputBuffer.timeUs < j) && !(data == -3 && bufferedPositionUs == Long.MIN_VALUE && !decoderInputBuffer.waitingForKeys))) {
                    return data;
                }
                decoderInputBuffer.clear();
                decoderInputBuffer.flags = 4;
                this.sentEos = true;
                return -4;
            }
            Format format = formatHolder.format;
            format.getClass();
            int i2 = format.encoderDelay;
            if (i2 != 0 || format.encoderPadding != 0) {
                ClippingMediaPeriod clippingMediaPeriod = ClippingMediaPeriod.this;
                if (clippingMediaPeriod.startUs != 0) {
                    i2 = 0;
                }
                int i3 = clippingMediaPeriod.endUs == Long.MIN_VALUE ? format.encoderPadding : 0;
                Format.Builder builder = new Format.Builder(format);
                builder.encoderDelay = i2;
                builder.encoderPadding = i3;
                formatHolder.format = new Format(builder);
            }
            return -5;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int skipData(long j) {
            if (ClippingMediaPeriod.this.isPendingInitialDiscontinuity()) {
                return -3;
            }
            return this.childStream.skipData(j);
        }
    }

    public ClippingMediaPeriod(MediaPeriod mediaPeriod, boolean z, long j, long j2) {
        this.mediaPeriod = mediaPeriod;
        this.pendingInitialDiscontinuityPositionUs = z ? j : -9223372036854775807L;
        this.startUs = j;
        this.endUs = j2;
    }

    public static boolean shouldKeepInitialDiscontinuity(long j, ExoTrackSelection[] exoTrackSelectionArr) {
        if (j != 0) {
            for (ExoTrackSelection exoTrackSelection : exoTrackSelectionArr) {
                if (exoTrackSelection != null) {
                    Format selectedFormat = exoTrackSelection.getSelectedFormat();
                    if (!MimeTypes.allSamplesAreSyncSamples(selectedFormat.sampleMimeType, selectedFormat.codecs)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final SeekParameters clipSeekParameters(long j, SeekParameters seekParameters) {
        long jConstrainValue = Util.constrainValue(seekParameters.toleranceBeforeUs, 0L, j - this.startUs);
        long j2 = seekParameters.toleranceAfterUs;
        long j3 = this.endUs;
        long jConstrainValue2 = Util.constrainValue(j2, 0L, j3 == Long.MIN_VALUE ? Long.MAX_VALUE : j3 - j);
        return (jConstrainValue == seekParameters.toleranceBeforeUs && jConstrainValue2 == seekParameters.toleranceAfterUs) ? seekParameters : new SeekParameters(jConstrainValue, jConstrainValue2);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo loadingInfo) {
        return this.mediaPeriod.continueLoading(loadingInfo);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long j, boolean z) {
        this.mediaPeriod.discardBuffer(j, z);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        long j2 = this.startUs;
        if (j == j2) {
            return j2;
        }
        return this.mediaPeriod.getAdjustedSeekPositionUs(j, clipSeekParameters(j, seekParameters));
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        long bufferedPositionUs = this.mediaPeriod.getBufferedPositionUs();
        if (bufferedPositionUs != Long.MIN_VALUE) {
            long j = this.endUs;
            if (j == Long.MIN_VALUE || bufferedPositionUs < j) {
                return bufferedPositionUs;
            }
        }
        return Long.MIN_VALUE;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        long nextLoadPositionUs = this.mediaPeriod.getNextLoadPositionUs();
        if (nextLoadPositionUs != Long.MIN_VALUE) {
            long j = this.endUs;
            if (j == Long.MIN_VALUE || nextLoadPositionUs < j) {
                return nextLoadPositionUs;
            }
        }
        return Long.MIN_VALUE;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public List<StreamKey> getStreamKeys(List<ExoTrackSelection> list) {
        return this.mediaPeriod.getStreamKeys(list);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.mediaPeriod.getTrackGroups();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return this.mediaPeriod.isLoading();
    }

    public boolean isPendingInitialDiscontinuity() {
        return this.pendingInitialDiscontinuityPositionUs != -9223372036854775807L;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        ClippingMediaSource.IllegalClippingException illegalClippingException = this.clippingError;
        if (illegalClippingException != null) {
            throw illegalClippingException;
        }
        this.mediaPeriod.maybeThrowPrepareError();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod mediaPeriod) {
        if (this.clippingError != null) {
            return;
        }
        MediaPeriod.Callback callback = this.callback;
        callback.getClass();
        callback.onPrepared(this);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j) {
        this.callback = callback;
        this.mediaPeriod.prepare(this, j);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long readDiscontinuity() {
        if (isPendingInitialDiscontinuity()) {
            long j = this.pendingInitialDiscontinuityPositionUs;
            this.pendingInitialDiscontinuityPositionUs = -9223372036854775807L;
            long discontinuity = readDiscontinuity();
            return discontinuity != -9223372036854775807L ? discontinuity : j;
        }
        long discontinuity2 = this.mediaPeriod.readDiscontinuity();
        if (discontinuity2 == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        Assertions.checkState(discontinuity2 >= this.startUs);
        long j2 = this.endUs;
        Assertions.checkState(j2 == Long.MIN_VALUE || discontinuity2 <= j2);
        return discontinuity2;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long j) {
        this.mediaPeriod.reevaluateBuffer(j);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0033  */
    @Override // androidx.media3.exoplayer.source.MediaPeriod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long seekToUs(long r7) {
        /*
            r6 = this;
            r0 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r6.pendingInitialDiscontinuityPositionUs = r0
            androidx.media3.exoplayer.source.ClippingMediaPeriod$ClippingSampleStream[] r0 = r6.sampleStreams
            int r1 = r0.length
            r2 = 0
            r3 = 0
        Lc:
            if (r3 >= r1) goto L17
            r4 = r0[r3]
            if (r4 == 0) goto L14
            r4.sentEos = r2
        L14:
            int r3 = r3 + 1
            goto Lc
        L17:
            androidx.media3.exoplayer.source.MediaPeriod r0 = r6.mediaPeriod
            long r0 = r0.seekToUs(r7)
            int r3 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r3 == 0) goto L33
            long r7 = r6.startUs
            int r3 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r3 < 0) goto L34
            long r7 = r6.endUs
            r3 = -9223372036854775808
            int r5 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r5 == 0) goto L33
            int r3 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r3 > 0) goto L34
        L33:
            r2 = 1
        L34:
            androidx.media3.common.util.Assertions.checkState(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.ClippingMediaPeriod.seekToUs(long):long");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005d  */
    @Override // androidx.media3.exoplayer.source.MediaPeriod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long selectTracks(androidx.media3.exoplayer.trackselection.ExoTrackSelection[] r10, boolean[] r11, androidx.media3.exoplayer.source.SampleStream[] r12, boolean[] r13, long r14) {
        /*
            r9 = this;
            int r0 = r12.length
            androidx.media3.exoplayer.source.ClippingMediaPeriod$ClippingSampleStream[] r0 = new androidx.media3.exoplayer.source.ClippingMediaPeriod.ClippingSampleStream[r0]
            r9.sampleStreams = r0
            int r0 = r12.length
            androidx.media3.exoplayer.source.SampleStream[] r4 = new androidx.media3.exoplayer.source.SampleStream[r0]
            r0 = 0
            r1 = 0
        La:
            int r2 = r12.length
            r8 = 0
            if (r1 >= r2) goto L1f
            androidx.media3.exoplayer.source.ClippingMediaPeriod$ClippingSampleStream[] r2 = r9.sampleStreams
            r3 = r12[r1]
            androidx.media3.exoplayer.source.ClippingMediaPeriod$ClippingSampleStream r3 = (androidx.media3.exoplayer.source.ClippingMediaPeriod.ClippingSampleStream) r3
            r2[r1] = r3
            if (r3 == 0) goto L1a
            androidx.media3.exoplayer.source.SampleStream r8 = r3.childStream
        L1a:
            r4[r1] = r8
            int r1 = r1 + 1
            goto La
        L1f:
            androidx.media3.exoplayer.source.MediaPeriod r1 = r9.mediaPeriod
            r2 = r10
            r3 = r11
            r5 = r13
            r6 = r14
            long r10 = r1.selectTracks(r2, r3, r4, r5, r6)
            boolean r13 = r9.isPendingInitialDiscontinuity()
            if (r13 == 0) goto L3d
            long r13 = r9.startUs
            int r15 = (r6 > r13 ? 1 : (r6 == r13 ? 0 : -1))
            if (r15 != 0) goto L3d
            boolean r13 = shouldKeepInitialDiscontinuity(r13, r2)
            if (r13 == 0) goto L3d
            r13 = r10
            goto L42
        L3d:
            r13 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L42:
            r9.pendingInitialDiscontinuityPositionUs = r13
            int r13 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r13 == 0) goto L5d
            long r13 = r9.startUs
            int r15 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r15 < 0) goto L5b
            long r13 = r9.endUs
            r1 = -9223372036854775808
            int r15 = (r13 > r1 ? 1 : (r13 == r1 ? 0 : -1))
            if (r15 == 0) goto L5d
            int r15 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r15 > 0) goto L5b
            goto L5d
        L5b:
            r13 = 0
            goto L5e
        L5d:
            r13 = 1
        L5e:
            androidx.media3.common.util.Assertions.checkState(r13)
        L61:
            int r13 = r12.length
            if (r0 >= r13) goto L87
            r13 = r4[r0]
            if (r13 != 0) goto L6d
            androidx.media3.exoplayer.source.ClippingMediaPeriod$ClippingSampleStream[] r13 = r9.sampleStreams
            r13[r0] = r8
            goto L7e
        L6d:
            androidx.media3.exoplayer.source.ClippingMediaPeriod$ClippingSampleStream[] r14 = r9.sampleStreams
            r15 = r14[r0]
            if (r15 == 0) goto L77
            androidx.media3.exoplayer.source.SampleStream r15 = r15.childStream
            if (r15 == r13) goto L7e
        L77:
            androidx.media3.exoplayer.source.ClippingMediaPeriod$ClippingSampleStream r15 = new androidx.media3.exoplayer.source.ClippingMediaPeriod$ClippingSampleStream
            r15.<init>(r13)
            r14[r0] = r15
        L7e:
            androidx.media3.exoplayer.source.ClippingMediaPeriod$ClippingSampleStream[] r13 = r9.sampleStreams
            r13 = r13[r0]
            r12[r0] = r13
            int r0 = r0 + 1
            goto L61
        L87:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.ClippingMediaPeriod.selectTracks(androidx.media3.exoplayer.trackselection.ExoTrackSelection[], boolean[], androidx.media3.exoplayer.source.SampleStream[], boolean[], long):long");
    }

    public void setClippingError(ClippingMediaSource.IllegalClippingException illegalClippingException) {
        this.clippingError = illegalClippingException;
    }

    public void updateClipping(long j, long j2) {
        this.startUs = j;
        this.endUs = j2;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        MediaPeriod.Callback callback = this.callback;
        callback.getClass();
        callback.onContinueLoadingRequested(this);
    }
}
