package androidx.media3.exoplayer.source;

import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.TrackGroup;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.ExternalLoader;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import com.google.common.base.Charsets;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ExternallyLoadedMediaPeriod implements MediaPeriod {
    public final ExternalLoader externalLoader;
    public final AtomicBoolean loadingFinished;
    public ListenableFuture<?> loadingFuture;
    public final AtomicReference<Throwable> loadingThrowable;
    public final byte[] sampleData;
    public final TrackGroupArray tracks;
    public final Uri uri;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class SampleStreamImpl implements SampleStream {
        public static final int STREAM_STATE_END_OF_STREAM = 2;
        public static final int STREAM_STATE_SEND_FORMAT = 0;
        public static final int STREAM_STATE_SEND_SAMPLE = 1;
        public int streamState = 0;

        public SampleStreamImpl() {
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public boolean isReady() {
            return ExternallyLoadedMediaPeriod.this.loadingFinished.get();
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public void maybeThrowError() throws IOException {
            Throwable th = ExternallyLoadedMediaPeriod.this.loadingThrowable.get();
            if (th != null) {
                throw new IOException(th);
            }
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i) {
            int i2 = this.streamState;
            if (i2 == 2) {
                decoderInputBuffer.addFlag(4);
                return -4;
            }
            if ((i & 2) != 0 || i2 == 0) {
                formatHolder.format = ExternallyLoadedMediaPeriod.this.tracks.get(0).formats[0];
                this.streamState = 1;
                return -5;
            }
            if (!ExternallyLoadedMediaPeriod.this.loadingFinished.get()) {
                return -3;
            }
            int length = ExternallyLoadedMediaPeriod.this.sampleData.length;
            decoderInputBuffer.addFlag(1);
            decoderInputBuffer.timeUs = 0L;
            if ((i & 4) == 0) {
                decoderInputBuffer.ensureSpaceForWrite(length);
                decoderInputBuffer.data.put(ExternallyLoadedMediaPeriod.this.sampleData, 0, length);
            }
            if ((i & 1) == 0) {
                this.streamState = 2;
            }
            return -4;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int skipData(long j) {
            return 0;
        }
    }

    public ExternallyLoadedMediaPeriod(Uri uri, String str, ExternalLoader externalLoader) {
        this.uri = uri;
        Format.Builder builder = new Format.Builder();
        builder.sampleMimeType = MimeTypes.normalizeMimeType(str);
        Format format = new Format(builder);
        this.externalLoader = externalLoader;
        this.tracks = new TrackGroupArray(new TrackGroup("", format));
        this.sampleData = uri.toString().getBytes(Charsets.UTF_8);
        this.loadingFinished = new AtomicBoolean();
        this.loadingThrowable = new AtomicReference<>();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo loadingInfo) {
        return !this.loadingFinished.get();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        return this.loadingFinished.get() ? Long.MIN_VALUE : 0L;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return this.loadingFinished.get() ? Long.MIN_VALUE : 0L;
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
        return !this.loadingFinished.get();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j) {
        callback.onPrepared(this);
        ListenableFuture<?> listenableFutureLoad = this.externalLoader.load(new ExternalLoader.LoadRequest(this.uri));
        this.loadingFuture = listenableFutureLoad;
        Futures.addCallback(listenableFutureLoad, new FutureCallback<Object>() { // from class: androidx.media3.exoplayer.source.ExternallyLoadedMediaPeriod.1
            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(Throwable th) {
                ExternallyLoadedMediaPeriod.this.loadingThrowable.set(th);
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onSuccess(@Nullable Object obj) {
                ExternallyLoadedMediaPeriod.this.loadingFinished.set(true);
            }
        }, DirectExecutor.INSTANCE);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long readDiscontinuity() {
        return -9223372036854775807L;
    }

    public void releasePeriod() {
        ListenableFuture<?> listenableFuture = this.loadingFuture;
        if (listenableFuture != null) {
            listenableFuture.cancel(false);
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        for (int i = 0; i < exoTrackSelectionArr.length; i++) {
            if (sampleStreamArr[i] != null && (exoTrackSelectionArr[i] == null || !zArr[i])) {
                sampleStreamArr[i] = null;
            }
            if (sampleStreamArr[i] == null && exoTrackSelectionArr[i] != null) {
                sampleStreamArr[i] = new SampleStreamImpl();
                zArr2[i] = true;
            }
        }
        return j;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void maybeThrowPrepareError() {
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long j) {
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long seekToUs(long j) {
        return j;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long j, boolean z) {
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        return j;
    }
}
