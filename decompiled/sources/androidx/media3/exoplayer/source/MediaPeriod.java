package androidx.media3.exoplayer.source;

import androidx.media3.common.StreamKey;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.SequenceableLoader;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public interface MediaPeriod extends SequenceableLoader {

    /* JADX INFO: renamed from: androidx.media3.exoplayer.source.MediaPeriod$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Callback extends SequenceableLoader.Callback<MediaPeriod> {
        void onPrepared(MediaPeriod mediaPeriod);
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    boolean continueLoading(LoadingInfo loadingInfo);

    void discardBuffer(long j, boolean z);

    long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters);

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    long getBufferedPositionUs();

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    long getNextLoadPositionUs();

    List<StreamKey> getStreamKeys(List<ExoTrackSelection> list);

    TrackGroupArray getTrackGroups();

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    boolean isLoading();

    void maybeThrowPrepareError() throws IOException;

    void prepare(Callback callback, long j);

    long readDiscontinuity();

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    void reevaluateBuffer(long j);

    long seekToUs(long j);

    long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j);
}
