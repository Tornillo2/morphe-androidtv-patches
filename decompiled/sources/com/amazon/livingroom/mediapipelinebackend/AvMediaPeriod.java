package com.amazon.livingroom.mediapipelinebackend;

import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import androidx.annotation.OptIn;
import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.mediapipelinebackend.AvSampleStream;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@OptIn(markerClass = {UnstableApi.class})
public final class AvMediaPeriod implements MediaPeriod {
    public static final int AUDIO_STREAM_IDX = 0;
    public static final int VIDEO_STREAM_IDX = 1;
    public final Format[] formats;
    public final Listener listener;
    public final AvSampleStream[] sampleStreams;
    public TrackGroupArray tracks;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Listener {
        void onSeekCompleted(long j);

        void onSeekError(Throwable th);

        void onTrackSelectionError(Throwable th);

        void onTracksSelected();
    }

    public AvMediaPeriod(NativeMediaPipelineBackend nativeMediaPipelineBackend, Format[] formatArr, Listener listener, MinervaMetrics minervaMetrics, DeviceProperties deviceProperties, ExoDrmSessionManager exoDrmSessionManager, AvSampleStream.AspectRatioSetter aspectRatioSetter) {
        this.formats = formatArr;
        this.listener = listener;
        this.sampleStreams = new AvSampleStream[]{AvSampleStream.createForAudio(nativeMediaPipelineBackend, minervaMetrics, deviceProperties, exoDrmSessionManager), AvSampleStream.createForVideo(nativeMediaPipelineBackend, minervaMetrics, deviceProperties, exoDrmSessionManager, aspectRatioSetter)};
    }

    public final TrackGroupArray buildTracks() {
        int length = this.formats.length;
        TrackGroup[] trackGroupArr = new TrackGroup[length];
        for (int i = 0; i < length; i++) {
            trackGroupArr[i] = new TrackGroup("", this.formats[i]);
            StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Configuring Track ", i, ": ");
            sbM.append(trackGroupArr[i].formats[0]);
            MpbLog.t(sbM.toString());
        }
        return new TrackGroupArray(trackGroupArr);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo loadingInfo) {
        return true;
    }

    public String describeState() {
        return "[AudioSampleStreamState:" + this.sampleStreams[0].describeState() + "],[VideoSampleStreamState:" + this.sampleStreams[1].describeState() + "]";
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        long j = Long.MIN_VALUE;
        for (AvSampleStream avSampleStream : this.sampleStreams) {
            long bufferedPositionUs = avSampleStream.getBufferedPositionUs();
            if (j == Long.MIN_VALUE || (bufferedPositionUs != Long.MIN_VALUE && bufferedPositionUs < j)) {
                j = bufferedPositionUs;
            }
        }
        return j;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return getBufferedPositionUs();
    }

    public final AvSampleStream getStreamFor(Format format) {
        String str = format.sampleMimeType;
        if (MimeTypes.isAudio(str)) {
            return this.sampleStreams[0];
        }
        if (MimeTypes.isVideo(str)) {
            return this.sampleStreams[1];
        }
        throw new IllegalArgumentException(RemoteInput$$ExternalSyntheticOutline0.m("Unknown stream format: ", str));
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public List getStreamKeys(List list) {
        return Collections.EMPTY_LIST;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.tracks;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return false;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j) {
        MpbLog.t("AvMediaPeriod.prepare");
        this.tracks = buildTracks();
        callback.onPrepared(this);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long readDiscontinuity() {
        return -9223372036854775807L;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long seekToUs(long j) {
        try {
            for (AvSampleStream avSampleStream : this.sampleStreams) {
                avSampleStream.seekTo(j);
            }
            Listener listener = this.listener;
            if (listener != null) {
                listener.onSeekCompleted(j);
            }
            return j;
        } catch (Exception e) {
            this.listener.onSeekError(e);
            return j;
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        MpbLog.t("AvMediaPeriod.selectTracks");
        for (int i = 0; i < exoTrackSelectionArr.length; i++) {
            try {
                ExoTrackSelection exoTrackSelection = exoTrackSelectionArr[i];
                if (exoTrackSelection == null) {
                    throw new IllegalArgumentException("Missing selection for stream " + i);
                }
                int length = exoTrackSelection.length();
                if (length != 1) {
                    throw new IllegalArgumentException("Expected track selection to have exactly one format, but it had " + length);
                }
                Format format = exoTrackSelection.getFormat(0);
                if (format == null) {
                    throw new IllegalArgumentException("Missing format for stream " + i);
                }
                AvSampleStream streamFor = getStreamFor(format);
                streamFor.seekTo(j);
                sampleStreamArr[i] = streamFor;
                zArr2[i] = true;
            } catch (Exception e) {
                this.listener.onTrackSelectionError(e);
                return j;
            }
        }
        this.listener.onTracksSelected();
        return j;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void maybeThrowPrepareError() {
    }

    public void release() {
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long j) {
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long j, boolean z) {
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        return j;
    }
}
