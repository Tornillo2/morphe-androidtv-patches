package com.google.android.exoplayer2.source;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MaskingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    public final Allocator allocator;

    @Nullable
    public MediaPeriod.Callback callback;
    public final MediaSource.MediaPeriodId id;

    @Nullable
    public PrepareListener listener;
    public MediaPeriod mediaPeriod;
    public MediaSource mediaSource;
    public boolean notifiedPrepareError;
    public long preparePositionOverrideUs = -9223372036854775807L;
    public final long preparePositionUs;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface PrepareListener {
        void onPrepareComplete(MediaSource.MediaPeriodId mediaPeriodId);

        void onPrepareError(MediaSource.MediaPeriodId mediaPeriodId, IOException iOException);
    }

    public MaskingMediaPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        this.id = mediaPeriodId;
        this.allocator = allocator;
        this.preparePositionUs = j;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public boolean continueLoading(long j) {
        MediaPeriod mediaPeriod = this.mediaPeriod;
        return mediaPeriod != null && mediaPeriod.continueLoading(j);
    }

    public void createPeriod(MediaSource.MediaPeriodId mediaPeriodId) {
        long preparePositionWithOverride = getPreparePositionWithOverride(this.preparePositionUs);
        MediaSource mediaSource = this.mediaSource;
        mediaSource.getClass();
        MediaPeriod mediaPeriodCreatePeriod = mediaSource.createPeriod(mediaPeriodId, this.allocator, preparePositionWithOverride);
        this.mediaPeriod = mediaPeriodCreatePeriod;
        if (this.callback != null) {
            mediaPeriodCreatePeriod.prepare(this, preparePositionWithOverride);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void discardBuffer(long j, boolean z) {
        MediaPeriod mediaPeriod = this.mediaPeriod;
        Util.castNonNull(mediaPeriod);
        mediaPeriod.discardBuffer(j, z);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        MediaPeriod mediaPeriod = this.mediaPeriod;
        Util.castNonNull(mediaPeriod);
        return mediaPeriod.getAdjustedSeekPositionUs(j, seekParameters);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getBufferedPositionUs() {
        MediaPeriod mediaPeriod = this.mediaPeriod;
        Util.castNonNull(mediaPeriod);
        return mediaPeriod.getBufferedPositionUs();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        MediaPeriod mediaPeriod = this.mediaPeriod;
        Util.castNonNull(mediaPeriod);
        return mediaPeriod.getNextLoadPositionUs();
    }

    public long getPreparePositionOverrideUs() {
        return this.preparePositionOverrideUs;
    }

    public long getPreparePositionUs() {
        return this.preparePositionUs;
    }

    public final long getPreparePositionWithOverride(long j) {
        long j2 = this.preparePositionOverrideUs;
        return j2 != -9223372036854775807L ? j2 : j;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public List getStreamKeys(List list) {
        return Collections.EMPTY_LIST;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        MediaPeriod mediaPeriod = this.mediaPeriod;
        Util.castNonNull(mediaPeriod);
        return mediaPeriod.getTrackGroups();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public boolean isLoading() {
        MediaPeriod mediaPeriod = this.mediaPeriod;
        return mediaPeriod != null && mediaPeriod.isLoading();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        try {
            MediaPeriod mediaPeriod = this.mediaPeriod;
            if (mediaPeriod != null) {
                mediaPeriod.maybeThrowPrepareError();
                return;
            }
            MediaSource mediaSource = this.mediaSource;
            if (mediaSource != null) {
                mediaSource.maybeThrowSourceInfoRefreshError();
            }
        } catch (IOException e) {
            PrepareListener prepareListener = this.listener;
            if (prepareListener == null) {
                throw e;
            }
            if (this.notifiedPrepareError) {
                return;
            }
            this.notifiedPrepareError = true;
            prepareListener.onPrepareError(this.id, e);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod mediaPeriod) {
        MediaPeriod.Callback callback = this.callback;
        Util.castNonNull(callback);
        callback.onPrepared(this);
        PrepareListener prepareListener = this.listener;
        if (prepareListener != null) {
            prepareListener.onPrepareComplete(this.id);
        }
    }

    public void overridePreparePositionUs(long j) {
        this.preparePositionOverrideUs = j;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j) {
        this.callback = callback;
        MediaPeriod mediaPeriod = this.mediaPeriod;
        if (mediaPeriod != null) {
            mediaPeriod.prepare(this, getPreparePositionWithOverride(this.preparePositionUs));
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long readDiscontinuity() {
        MediaPeriod mediaPeriod = this.mediaPeriod;
        Util.castNonNull(mediaPeriod);
        return mediaPeriod.readDiscontinuity();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public void reevaluateBuffer(long j) {
        MediaPeriod mediaPeriod = this.mediaPeriod;
        Util.castNonNull(mediaPeriod);
        mediaPeriod.reevaluateBuffer(j);
    }

    public void releasePeriod() {
        if (this.mediaPeriod != null) {
            MediaSource mediaSource = this.mediaSource;
            mediaSource.getClass();
            mediaSource.releasePeriod(this.mediaPeriod);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long seekToUs(long j) {
        MediaPeriod mediaPeriod = this.mediaPeriod;
        Util.castNonNull(mediaPeriod);
        return mediaPeriod.seekToUs(j);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        long j2;
        long j3 = this.preparePositionOverrideUs;
        if (j3 == -9223372036854775807L || j != this.preparePositionUs) {
            j2 = j;
        } else {
            this.preparePositionOverrideUs = -9223372036854775807L;
            j2 = j3;
        }
        MediaPeriod mediaPeriod = this.mediaPeriod;
        Util.castNonNull(mediaPeriod);
        return mediaPeriod.selectTracks(exoTrackSelectionArr, zArr, sampleStreamArr, zArr2, j2);
    }

    public void setMediaSource(MediaSource mediaSource) {
        Assertions.checkState(this.mediaSource == null);
        this.mediaSource = mediaSource;
    }

    public void setPrepareListener(PrepareListener prepareListener) {
        this.listener = prepareListener;
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        MediaPeriod.Callback callback = this.callback;
        Util.castNonNull(callback);
        callback.onContinueLoadingRequested(this);
    }
}
