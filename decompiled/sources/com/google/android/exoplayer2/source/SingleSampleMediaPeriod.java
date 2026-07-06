package com.google.android.exoplayer2.source;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSourceUtil;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.StatsDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class SingleSampleMediaPeriod implements MediaPeriod, Loader.Callback<SourceLoadable> {
    public static final int INITIAL_SAMPLE_SIZE = 1024;
    public static final String TAG = "SingleSampleMediaPeriod";
    public final DataSource.Factory dataSourceFactory;
    public final DataSpec dataSpec;
    public final long durationUs;
    public final MediaSourceEventListener.EventDispatcher eventDispatcher;
    public final Format format;
    public final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    public boolean loadingFinished;
    public byte[] sampleData;
    public int sampleSize;
    public final TrackGroupArray tracks;

    @Nullable
    public final TransferListener transferListener;
    public final boolean treatLoadErrorsAsEndOfStream;
    public final ArrayList<SampleStreamImpl> sampleStreams = new ArrayList<>();
    public final Loader loader = new Loader("SingleSampleMediaPeriod");

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class SampleStreamImpl implements SampleStream {
        public static final int STREAM_STATE_END_OF_STREAM = 2;
        public static final int STREAM_STATE_SEND_FORMAT = 0;
        public static final int STREAM_STATE_SEND_SAMPLE = 1;
        public boolean notifiedDownstreamFormat;
        public int streamState;

        public SampleStreamImpl() {
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public boolean isReady() {
            return SingleSampleMediaPeriod.this.loadingFinished;
        }

        public final void maybeNotifyDownstreamFormat() {
            if (this.notifiedDownstreamFormat) {
                return;
            }
            SingleSampleMediaPeriod singleSampleMediaPeriod = SingleSampleMediaPeriod.this;
            singleSampleMediaPeriod.eventDispatcher.downstreamFormatChanged(MimeTypes.getTrackType(singleSampleMediaPeriod.format.sampleMimeType), SingleSampleMediaPeriod.this.format, 0, null, 0L);
            this.notifiedDownstreamFormat = true;
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public void maybeThrowError() throws IOException {
            SingleSampleMediaPeriod singleSampleMediaPeriod = SingleSampleMediaPeriod.this;
            if (singleSampleMediaPeriod.treatLoadErrorsAsEndOfStream) {
                return;
            }
            singleSampleMediaPeriod.loader.maybeThrowError();
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i) {
            maybeNotifyDownstreamFormat();
            SingleSampleMediaPeriod singleSampleMediaPeriod = SingleSampleMediaPeriod.this;
            boolean z = singleSampleMediaPeriod.loadingFinished;
            if (z && singleSampleMediaPeriod.sampleData == null) {
                this.streamState = 2;
            }
            int i2 = this.streamState;
            if (i2 == 2) {
                decoderInputBuffer.addFlag(4);
                return -4;
            }
            if ((i & 2) != 0 || i2 == 0) {
                formatHolder.format = singleSampleMediaPeriod.format;
                this.streamState = 1;
                return -5;
            }
            if (!z) {
                return -3;
            }
            singleSampleMediaPeriod.sampleData.getClass();
            decoderInputBuffer.addFlag(1);
            decoderInputBuffer.timeUs = 0L;
            if ((i & 4) == 0) {
                decoderInputBuffer.ensureSpaceForWrite(SingleSampleMediaPeriod.this.sampleSize);
                ByteBuffer byteBuffer = decoderInputBuffer.data;
                SingleSampleMediaPeriod singleSampleMediaPeriod2 = SingleSampleMediaPeriod.this;
                byteBuffer.put(singleSampleMediaPeriod2.sampleData, 0, singleSampleMediaPeriod2.sampleSize);
            }
            if ((i & 1) == 0) {
                this.streamState = 2;
            }
            return -4;
        }

        public void reset() {
            if (this.streamState == 2) {
                this.streamState = 1;
            }
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public int skipData(long j) {
            maybeNotifyDownstreamFormat();
            if (j <= 0 || this.streamState == 2) {
                return 0;
            }
            this.streamState = 2;
            return 1;
        }
    }

    public SingleSampleMediaPeriod(DataSpec dataSpec, DataSource.Factory factory, @Nullable TransferListener transferListener, Format format, long j, LoadErrorHandlingPolicy loadErrorHandlingPolicy, MediaSourceEventListener.EventDispatcher eventDispatcher, boolean z) {
        this.dataSpec = dataSpec;
        this.dataSourceFactory = factory;
        this.transferListener = transferListener;
        this.format = format;
        this.durationUs = j;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
        this.eventDispatcher = eventDispatcher;
        this.treatLoadErrorsAsEndOfStream = z;
        this.tracks = new TrackGroupArray(new TrackGroup("", format));
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public boolean continueLoading(long j) {
        if (this.loadingFinished || this.loader.isLoading() || this.loader.hasFatalError()) {
            return false;
        }
        DataSource dataSourceCreateDataSource = this.dataSourceFactory.createDataSource();
        TransferListener transferListener = this.transferListener;
        if (transferListener != null) {
            dataSourceCreateDataSource.addTransferListener(transferListener);
        }
        SourceLoadable sourceLoadable = new SourceLoadable(this.dataSpec, dataSourceCreateDataSource);
        this.eventDispatcher.loadStarted(new LoadEventInfo(sourceLoadable.loadTaskId, this.dataSpec, this.loader.startLoading(sourceLoadable, this, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(1))), 1, -1, this.format, 0, null, 0L, this.durationUs);
        return true;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getBufferedPositionUs() {
        return this.loadingFinished ? Long.MIN_VALUE : 0L;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return (this.loadingFinished || this.loader.isLoading()) ? Long.MIN_VALUE : 0L;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public List getStreamKeys(List list) {
        return Collections.EMPTY_LIST;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.tracks;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public boolean isLoading() {
        return this.loader.isLoading();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j) {
        callback.onPrepared(this);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long readDiscontinuity() {
        return -9223372036854775807L;
    }

    public void release() {
        this.loader.release(null);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long seekToUs(long j) {
        for (int i = 0; i < this.sampleStreams.size(); i++) {
            this.sampleStreams.get(i).reset();
        }
        return j;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        for (int i = 0; i < exoTrackSelectionArr.length; i++) {
            SampleStream sampleStream = sampleStreamArr[i];
            if (sampleStream != null && (exoTrackSelectionArr[i] == null || !zArr[i])) {
                this.sampleStreams.remove(sampleStream);
                sampleStreamArr[i] = null;
            }
            if (sampleStreamArr[i] == null && exoTrackSelectionArr[i] != null) {
                SampleStreamImpl sampleStreamImpl = new SampleStreamImpl();
                this.sampleStreams.add(sampleStreamImpl);
                sampleStreamArr[i] = sampleStreamImpl;
                zArr2[i] = true;
            }
        }
        return j;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public void onLoadCanceled(SourceLoadable sourceLoadable, long j, long j2, boolean z) {
        StatsDataSource statsDataSource = sourceLoadable.dataSource;
        LoadEventInfo loadEventInfo = new LoadEventInfo(sourceLoadable.loadTaskId, sourceLoadable.dataSpec, statsDataSource.lastOpenedUri, statsDataSource.lastResponseHeaders, j, j2, statsDataSource.bytesRead);
        this.loadErrorHandlingPolicy.getClass();
        this.eventDispatcher.loadCanceled(loadEventInfo, 1, -1, null, 0, null, 0L, this.durationUs);
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public void onLoadCompleted(SourceLoadable sourceLoadable, long j, long j2) {
        this.sampleSize = (int) sourceLoadable.dataSource.bytesRead;
        byte[] bArr = sourceLoadable.sampleData;
        bArr.getClass();
        this.sampleData = bArr;
        this.loadingFinished = true;
        StatsDataSource statsDataSource = sourceLoadable.dataSource;
        LoadEventInfo loadEventInfo = new LoadEventInfo(sourceLoadable.loadTaskId, sourceLoadable.dataSpec, statsDataSource.lastOpenedUri, statsDataSource.lastResponseHeaders, j, j2, this.sampleSize);
        this.loadErrorHandlingPolicy.getClass();
        this.eventDispatcher.loadCompleted(loadEventInfo, 1, -1, this.format, 0, null, 0L, this.durationUs);
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public Loader.LoadErrorAction onLoadError(SourceLoadable sourceLoadable, long j, long j2, IOException iOException, int i) {
        Loader.LoadErrorAction loadErrorAction;
        StatsDataSource statsDataSource = sourceLoadable.dataSource;
        LoadEventInfo loadEventInfo = new LoadEventInfo(sourceLoadable.loadTaskId, sourceLoadable.dataSpec, statsDataSource.lastOpenedUri, statsDataSource.lastResponseHeaders, j, j2, statsDataSource.bytesRead);
        long retryDelayMsFor = this.loadErrorHandlingPolicy.getRetryDelayMsFor(new LoadErrorHandlingPolicy.LoadErrorInfo(loadEventInfo, new MediaLoadData(1, -1, this.format, 0, null, 0L, Util.usToMs(this.durationUs)), iOException, i));
        boolean z = retryDelayMsFor == -9223372036854775807L || i >= this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(1);
        if (this.treatLoadErrorsAsEndOfStream && z) {
            Log.w("SingleSampleMediaPeriod", "Loading failed, treating as end-of-stream.", iOException);
            this.loadingFinished = true;
            loadErrorAction = Loader.DONT_RETRY;
        } else {
            loadErrorAction = retryDelayMsFor != -9223372036854775807L ? new Loader.LoadErrorAction(0, retryDelayMsFor) : Loader.DONT_RETRY_FATAL;
        }
        Loader.LoadErrorAction loadErrorAction2 = loadErrorAction;
        boolean zIsRetry = loadErrorAction2.isRetry();
        this.eventDispatcher.loadError(loadEventInfo, 1, -1, this.format, 0, null, 0L, this.durationUs, iOException, !zIsRetry);
        if (!zIsRetry) {
            this.loadErrorHandlingPolicy.getClass();
        }
        return loadErrorAction2;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SourceLoadable implements Loader.Loadable {
        public final StatsDataSource dataSource;
        public final DataSpec dataSpec;
        public final long loadTaskId = LoadEventInfo.getNewId();

        @Nullable
        public byte[] sampleData;

        public SourceLoadable(DataSpec dataSpec, DataSource dataSource) {
            this.dataSpec = dataSpec;
            this.dataSource = new StatsDataSource(dataSource);
        }

        @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
        public void load() throws IOException {
            StatsDataSource statsDataSource = this.dataSource;
            statsDataSource.bytesRead = 0L;
            try {
                statsDataSource.open(this.dataSpec);
                int i = 0;
                while (i != -1) {
                    int i2 = (int) this.dataSource.bytesRead;
                    byte[] bArr = this.sampleData;
                    if (bArr == null) {
                        this.sampleData = new byte[1024];
                    } else if (i2 == bArr.length) {
                        this.sampleData = Arrays.copyOf(bArr, bArr.length * 2);
                    }
                    StatsDataSource statsDataSource2 = this.dataSource;
                    byte[] bArr2 = this.sampleData;
                    i = statsDataSource2.read(bArr2, i2, bArr2.length - i2);
                }
                DataSourceUtil.closeQuietly(this.dataSource);
            } catch (Throwable th) {
                DataSourceUtil.closeQuietly(this.dataSource);
                throw th;
            }
        }

        @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
        public void cancelLoad() {
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void maybeThrowPrepareError() {
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public void reevaluateBuffer(long j) {
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void discardBuffer(long j, boolean z) {
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        return j;
    }
}
