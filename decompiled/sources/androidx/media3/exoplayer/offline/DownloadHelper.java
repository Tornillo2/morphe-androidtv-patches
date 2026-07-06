package androidx.media3.exoplayer.offline;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.SparseIntArray;
import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.RenderersFactory;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.offline.DownloadRequest;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunkIterator;
import androidx.media3.exoplayer.trackselection.BaseTrackSelection;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.MappingTrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelectionUtil;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import androidx.media3.exoplayer.upstream.DefaultAllocator;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import androidx.media3.extractor.ExtractorsFactory;
import com.google.common.collect.UnmodifiableIterator;
import j$.util.DesugarCollections;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class DownloadHelper {
    public static final DefaultTrackSelector.Parameters DEFAULT_TRACK_SELECTOR_PARAMETERS_WITHOUT_CONTEXT;
    public Callback callback;
    public final Handler callbackHandler;
    public List<ExoTrackSelection>[][] immutableTrackSelectionsByPeriodAndRenderer;
    public boolean isPreparedWithMedia;
    public final MediaItem.LocalConfiguration localConfiguration;
    public MappingTrackSelector.MappedTrackInfo[] mappedTrackInfos;
    public MediaPreparer mediaPreparer;

    @Nullable
    public final MediaSource mediaSource;
    public final RendererCapabilities[] rendererCapabilities;
    public final SparseIntArray scratchSet;
    public TrackGroupArray[] trackGroupArrays;
    public List<ExoTrackSelection>[][] trackSelectionsByPeriodAndRenderer;
    public final DefaultTrackSelector trackSelector;
    public final Timeline.Window window;

    /* JADX INFO: renamed from: androidx.media3.exoplayer.offline.DownloadHelper$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements VideoRendererEventListener {
        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public /* synthetic */ void onVideoInputFormatChanged(Format format) {
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public /* synthetic */ void onVideoInputFormatChanged(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public /* synthetic */ void onVideoCodecError(Exception exc) {
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public /* synthetic */ void onVideoDecoderReleased(String str) {
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public /* synthetic */ void onVideoDisabled(DecoderCounters decoderCounters) {
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public /* synthetic */ void onVideoEnabled(DecoderCounters decoderCounters) {
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public /* synthetic */ void onVideoSizeChanged(VideoSize videoSize) {
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public /* synthetic */ void onDroppedFrames(int i, long j) {
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public /* synthetic */ void onRenderedFirstFrame(Object obj, long j) {
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public /* synthetic */ void onVideoFrameProcessingOffset(long j, int i) {
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public /* synthetic */ void onVideoDecoderInitialized(String str, long j, long j2) {
        }
    }

    /* JADX INFO: renamed from: androidx.media3.exoplayer.offline.DownloadHelper$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass2 implements AudioRendererEventListener {
        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public /* synthetic */ void onAudioInputFormatChanged(Format format) {
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public /* synthetic */ void onAudioInputFormatChanged(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public /* synthetic */ void onAudioCodecError(Exception exc) {
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public /* synthetic */ void onAudioDecoderReleased(String str) {
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public /* synthetic */ void onAudioDisabled(DecoderCounters decoderCounters) {
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public /* synthetic */ void onAudioEnabled(DecoderCounters decoderCounters) {
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public /* synthetic */ void onAudioPositionAdvancing(long j) {
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public /* synthetic */ void onAudioSinkError(Exception exc) {
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public /* synthetic */ void onAudioTrackInitialized(AudioSink.AudioTrackConfig audioTrackConfig) {
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public /* synthetic */ void onAudioTrackReleased(AudioSink.AudioTrackConfig audioTrackConfig) {
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public /* synthetic */ void onSkipSilenceEnabledChanged(boolean z) {
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public /* synthetic */ void onAudioDecoderInitialized(String str, long j, long j2) {
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public /* synthetic */ void onAudioUnderrun(int i, long j, long j2) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Callback {
        void onPrepareError(DownloadHelper downloadHelper, IOException iOException);

        void onPrepared(DownloadHelper downloadHelper);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class FakeBandwidthMeter implements BandwidthMeter {
        public FakeBandwidthMeter() {
        }

        @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
        public long getBitrateEstimate() {
            return 0L;
        }

        @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
        public /* synthetic */ long getTimeToFirstByteEstimateUs() {
            return -9223372036854775807L;
        }

        @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
        @Nullable
        public TransferListener getTransferListener() {
            return null;
        }

        public FakeBandwidthMeter(AnonymousClass1 anonymousClass1) {
        }

        @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
        public void removeEventListener(BandwidthMeter.EventListener eventListener) {
        }

        @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
        public void addEventListener(Handler handler, BandwidthMeter.EventListener eventListener) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class LiveContentUnsupportedException extends IOException {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MediaPreparer implements MediaSource.MediaSourceCaller, MediaPeriod.Callback, Handler.Callback {
        public static final int DOWNLOAD_HELPER_CALLBACK_MESSAGE_FAILED = 1;
        public static final int DOWNLOAD_HELPER_CALLBACK_MESSAGE_PREPARED = 0;
        public static final int MESSAGE_CHECK_FOR_FAILURE = 1;
        public static final int MESSAGE_CONTINUE_LOADING = 2;
        public static final int MESSAGE_PREPARE_SOURCE = 0;
        public static final int MESSAGE_RELEASE = 3;
        public final DownloadHelper downloadHelper;
        public MediaPeriod[] mediaPeriods;
        public final MediaSource mediaSource;
        public final Handler mediaSourceHandler;
        public final HandlerThread mediaSourceThread;
        public boolean released;
        public Timeline timeline;
        public final Allocator allocator = new DefaultAllocator(true, 65536, 0);
        public final ArrayList<MediaPeriod> pendingMediaPeriods = new ArrayList<>();
        public final Handler downloadHelperHandler = Util.createHandlerForCurrentOrMainLooper(new Handler.Callback() { // from class: androidx.media3.exoplayer.offline.DownloadHelper$MediaPreparer$$ExternalSyntheticLambda0
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.f$0.handleDownloadHelperCallbackMessage(message);
            }
        });

        public MediaPreparer(MediaSource mediaSource, DownloadHelper downloadHelper) {
            this.mediaSource = mediaSource;
            this.downloadHelper = downloadHelper;
            HandlerThread handlerThread = new HandlerThread("ExoPlayer:DownloadHelper");
            this.mediaSourceThread = handlerThread;
            handlerThread.start();
            Handler handler = new Handler(handlerThread.getLooper(), this);
            this.mediaSourceHandler = handler;
            handler.sendEmptyMessage(0);
        }

        public final boolean handleDownloadHelperCallbackMessage(Message message) {
            if (this.released) {
                return false;
            }
            int i = message.what;
            if (i == 0) {
                try {
                    this.downloadHelper.onMediaPrepared();
                } catch (ExoPlaybackException e) {
                    this.downloadHelperHandler.obtainMessage(1, new IOException(e)).sendToTarget();
                }
                return true;
            }
            if (i != 1) {
                return false;
            }
            release();
            DownloadHelper downloadHelper = this.downloadHelper;
            Object obj = message.obj;
            Util.castNonNull(obj);
            downloadHelper.onMediaPreparationFailed((IOException) obj);
            return true;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                this.mediaSource.prepareSource(this, null, PlayerId.UNSET);
                this.mediaSourceHandler.sendEmptyMessage(1);
                return true;
            }
            int i2 = 0;
            if (i == 1) {
                try {
                    if (this.mediaPeriods == null) {
                        this.mediaSource.maybeThrowSourceInfoRefreshError();
                    } else {
                        while (i2 < this.pendingMediaPeriods.size()) {
                            this.pendingMediaPeriods.get(i2).maybeThrowPrepareError();
                            i2++;
                        }
                    }
                    this.mediaSourceHandler.sendEmptyMessageDelayed(1, 100L);
                } catch (IOException e) {
                    this.downloadHelperHandler.obtainMessage(1, e).sendToTarget();
                }
                return true;
            }
            if (i == 2) {
                MediaPeriod mediaPeriod = (MediaPeriod) message.obj;
                if (this.pendingMediaPeriods.contains(mediaPeriod)) {
                    LoadingInfo.Builder builder = new LoadingInfo.Builder();
                    builder.playbackPositionUs = 0L;
                    mediaPeriod.continueLoading(new LoadingInfo(builder));
                }
                return true;
            }
            if (i != 3) {
                return false;
            }
            MediaPeriod[] mediaPeriodArr = this.mediaPeriods;
            if (mediaPeriodArr != null) {
                int length = mediaPeriodArr.length;
                while (i2 < length) {
                    this.mediaSource.releasePeriod(mediaPeriodArr[i2]);
                    i2++;
                }
            }
            this.mediaSource.releaseSource(this);
            this.mediaSourceHandler.removeCallbacksAndMessages(null);
            this.mediaSourceThread.quit();
            return true;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
        public void onPrepared(MediaPeriod mediaPeriod) {
            this.pendingMediaPeriods.remove(mediaPeriod);
            if (this.pendingMediaPeriods.isEmpty()) {
                this.mediaSourceHandler.removeMessages(1);
                this.downloadHelperHandler.sendEmptyMessage(0);
            }
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.MediaSourceCaller
        public void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline) {
            MediaPeriod[] mediaPeriodArr;
            if (this.timeline != null) {
                return;
            }
            if (timeline.getWindow(0, new Timeline.Window(), 0L).isLive()) {
                this.downloadHelperHandler.obtainMessage(1, new LiveContentUnsupportedException()).sendToTarget();
                return;
            }
            this.timeline = timeline;
            this.mediaPeriods = new MediaPeriod[timeline.getPeriodCount()];
            int i = 0;
            while (true) {
                mediaPeriodArr = this.mediaPeriods;
                if (i >= mediaPeriodArr.length) {
                    break;
                }
                MediaPeriod mediaPeriodCreatePeriod = this.mediaSource.createPeriod(new MediaSource.MediaPeriodId(timeline.getUidOfPeriod(i)), this.allocator, 0L);
                this.mediaPeriods[i] = mediaPeriodCreatePeriod;
                this.pendingMediaPeriods.add(mediaPeriodCreatePeriod);
                i++;
            }
            for (MediaPeriod mediaPeriod : mediaPeriodArr) {
                mediaPeriod.prepare(this, 0L);
            }
        }

        public void release() {
            if (this.released) {
                return;
            }
            this.released = true;
            this.mediaSourceHandler.sendEmptyMessage(3);
        }

        @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
        public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
            if (this.pendingMediaPeriods.contains(mediaPeriod)) {
                this.mediaSourceHandler.obtainMessage(2, mediaPeriod).sendToTarget();
            }
        }
    }

    public static void $r8$lambda$O6ITP06WF2y_qgTmOc0xmCMZg0c(DownloadHelper downloadHelper, IOException iOException) {
        Callback callback = downloadHelper.callback;
        callback.getClass();
        callback.onPrepareError(downloadHelper, iOException);
    }

    public static /* synthetic */ void $r8$lambda$WRCge6RrufzclVmxlrqq8iYqyCI(DownloadHelper downloadHelper, Callback callback) {
        downloadHelper.getClass();
        callback.onPrepared(downloadHelper);
    }

    public static void $r8$lambda$mal4ZQBeG6ek3_B0q_iPfVUUGoI(DownloadHelper downloadHelper) {
        Callback callback = downloadHelper.callback;
        callback.getClass();
        callback.onPrepared(downloadHelper);
    }

    static {
        DefaultTrackSelector.Parameters parameters = DefaultTrackSelector.Parameters.DEFAULT_WITHOUT_CONTEXT;
        parameters.getClass();
        DefaultTrackSelector.Parameters.Builder builder = new DefaultTrackSelector.Parameters.Builder(parameters);
        builder.forceHighestSupportedBitrate = true;
        builder.constrainAudioChannelCountToDeviceCapabilities = false;
        DEFAULT_TRACK_SELECTOR_PARAMETERS_WITHOUT_CONTEXT = new DefaultTrackSelector.Parameters(builder);
    }

    public DownloadHelper(MediaItem mediaItem, @Nullable MediaSource mediaSource, TrackSelectionParameters trackSelectionParameters, RendererCapabilities[] rendererCapabilitiesArr) {
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        localConfiguration.getClass();
        this.localConfiguration = localConfiguration;
        this.mediaSource = mediaSource;
        DefaultTrackSelector defaultTrackSelector = new DefaultTrackSelector(trackSelectionParameters, new DownloadTrackSelection.Factory(), (Context) null);
        this.trackSelector = defaultTrackSelector;
        this.rendererCapabilities = rendererCapabilitiesArr;
        this.scratchSet = new SparseIntArray();
        DownloadHelper$$ExternalSyntheticLambda0 downloadHelper$$ExternalSyntheticLambda0 = new DownloadHelper$$ExternalSyntheticLambda0();
        FakeBandwidthMeter fakeBandwidthMeter = new FakeBandwidthMeter();
        defaultTrackSelector.listener = downloadHelper$$ExternalSyntheticLambda0;
        defaultTrackSelector.bandwidthMeter = fakeBandwidthMeter;
        this.callbackHandler = Util.createHandlerForCurrentOrMainLooper(null);
        this.window = new Timeline.Window();
    }

    public static MediaSource createMediaSource(DownloadRequest downloadRequest, DataSource.Factory factory) {
        return createMediaSource(downloadRequest, factory, null);
    }

    public static MediaSource createMediaSourceInternal(MediaItem mediaItem, DataSource.Factory factory, @Nullable final DrmSessionManager drmSessionManager) {
        DefaultMediaSourceFactory defaultMediaSourceFactory = new DefaultMediaSourceFactory(factory, ExtractorsFactory.EMPTY);
        if (drmSessionManager != null) {
            defaultMediaSourceFactory.setDrmSessionManagerProvider(new DrmSessionManagerProvider() { // from class: androidx.media3.exoplayer.offline.DownloadHelper$$ExternalSyntheticLambda1
                @Override // androidx.media3.exoplayer.drm.DrmSessionManagerProvider
                public final DrmSessionManager get(MediaItem mediaItem2) {
                    DrmSessionManager drmSessionManager2 = drmSessionManager;
                    DownloadHelper.$r8$lambda$VAQWZ83QPLJG0xo3934latvqWFE(drmSessionManager2, mediaItem2);
                    return drmSessionManager2;
                }
            });
        }
        return defaultMediaSourceFactory.createMediaSource(mediaItem);
    }

    public static DownloadHelper forMediaItem(Context context, MediaItem mediaItem) {
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        localConfiguration.getClass();
        Assertions.checkArgument(isProgressive(localConfiguration));
        return forMediaItem(mediaItem, getDefaultTrackSelectorParameters(context), null, null, null);
    }

    public static DefaultTrackSelector.Parameters getDefaultTrackSelectorParameters(Context context) {
        DefaultTrackSelector.Parameters.Builder builder = new DefaultTrackSelector.Parameters.Builder(DefaultTrackSelector.Parameters.getDefaults(context));
        builder.forceHighestSupportedBitrate = true;
        builder.constrainAudioChannelCountToDeviceCapabilities = false;
        return new DefaultTrackSelector.Parameters(builder);
    }

    public static RendererCapabilities[] getRendererCapabilities(RenderersFactory renderersFactory) {
        Renderer[] rendererArrCreateRenderers = renderersFactory.createRenderers(Util.createHandlerForCurrentOrMainLooper(), new AnonymousClass1(), new AnonymousClass2(), new DownloadHelper$$ExternalSyntheticLambda2(), new DownloadHelper$$ExternalSyntheticLambda3());
        RendererCapabilities[] rendererCapabilitiesArr = new RendererCapabilities[rendererArrCreateRenderers.length];
        for (int i = 0; i < rendererArrCreateRenderers.length; i++) {
            rendererCapabilitiesArr[i] = rendererArrCreateRenderers[i].getCapabilities();
        }
        return rendererCapabilitiesArr;
    }

    public static boolean isProgressive(MediaItem.LocalConfiguration localConfiguration) {
        return Util.inferContentTypeForUriAndMimeType(localConfiguration.uri, localConfiguration.mimeType) == 4;
    }

    public void addAudioLanguagesToSelection(String... strArr) {
        try {
            assertPreparedWithMedia();
            DefaultTrackSelector.Parameters parameters = DEFAULT_TRACK_SELECTOR_PARAMETERS_WITHOUT_CONTEXT;
            parameters.getClass();
            DefaultTrackSelector.Parameters.Builder builder = new DefaultTrackSelector.Parameters.Builder(parameters);
            builder.forceHighestSupportedBitrate = true;
            for (RendererCapabilities rendererCapabilities : this.rendererCapabilities) {
                int trackType = rendererCapabilities.getTrackType();
                builder.setTrackTypeDisabled(trackType, trackType != 1);
            }
            int periodCount = getPeriodCount();
            for (String str : strArr) {
                builder.setPreferredAudioLanguage(str);
                DefaultTrackSelector.Parameters parameters2 = new DefaultTrackSelector.Parameters(builder);
                for (int i = 0; i < periodCount; i++) {
                    addTrackSelectionInternal(i, parameters2);
                }
            }
        } catch (ExoPlaybackException e) {
            throw new IllegalStateException(e);
        }
    }

    public void addTextLanguagesToSelection(boolean z, String... strArr) {
        try {
            assertPreparedWithMedia();
            DefaultTrackSelector.Parameters parameters = DEFAULT_TRACK_SELECTOR_PARAMETERS_WITHOUT_CONTEXT;
            parameters.getClass();
            DefaultTrackSelector.Parameters.Builder builder = new DefaultTrackSelector.Parameters.Builder(parameters);
            builder.selectUndeterminedTextLanguage = z;
            builder.forceHighestSupportedBitrate = true;
            for (RendererCapabilities rendererCapabilities : this.rendererCapabilities) {
                int trackType = rendererCapabilities.getTrackType();
                builder.setTrackTypeDisabled(trackType, trackType != 3);
            }
            int periodCount = getPeriodCount();
            for (String str : strArr) {
                builder.setPreferredTextLanguage(str);
                DefaultTrackSelector.Parameters parameters2 = new DefaultTrackSelector.Parameters(builder);
                for (int i = 0; i < periodCount; i++) {
                    addTrackSelectionInternal(i, parameters2);
                }
            }
        } catch (ExoPlaybackException e) {
            throw new IllegalStateException(e);
        }
    }

    public void addTrackSelection(int i, TrackSelectionParameters trackSelectionParameters) {
        try {
            assertPreparedWithMedia();
            addTrackSelectionInternal(i, trackSelectionParameters);
        } catch (ExoPlaybackException e) {
            throw new IllegalStateException(e);
        }
    }

    public void addTrackSelectionForSingleRenderer(int i, int i2, DefaultTrackSelector.Parameters parameters, List<DefaultTrackSelector.SelectionOverride> list) {
        try {
            assertPreparedWithMedia();
            parameters.getClass();
            DefaultTrackSelector.Parameters.Builder builder = new DefaultTrackSelector.Parameters.Builder(parameters);
            int i3 = 0;
            while (i3 < this.mappedTrackInfos[i].rendererCount) {
                builder.setRendererDisabled(i3, i3 != i2);
                i3++;
            }
            if (list.isEmpty()) {
                addTrackSelectionInternal(i, new DefaultTrackSelector.Parameters(builder));
                return;
            }
            TrackGroupArray trackGroupArray = this.mappedTrackInfos[i].rendererTrackGroups[i2];
            for (int i4 = 0; i4 < list.size(); i4++) {
                builder.setSelectionOverride(i2, trackGroupArray, list.get(i4));
                addTrackSelectionInternal(i, new DefaultTrackSelector.Parameters(builder));
            }
        } catch (ExoPlaybackException e) {
            throw new IllegalStateException(e);
        }
    }

    @RequiresNonNull({"trackGroupArrays", "trackSelectionsByPeriodAndRenderer", "mediaPreparer", "mediaPreparer.timeline"})
    public final void addTrackSelectionInternal(int i, TrackSelectionParameters trackSelectionParameters) throws ExoPlaybackException {
        this.trackSelector.setParameters(trackSelectionParameters);
        runTrackSelection(i);
        UnmodifiableIterator<TrackSelectionOverride> it = trackSelectionParameters.overrides.values().iterator();
        while (it.hasNext()) {
            this.trackSelector.setParameters(trackSelectionParameters.buildUpon().setOverrideForType(it.next()).build());
            runTrackSelection(i);
        }
    }

    @EnsuresNonNull({"trackGroupArrays", "mappedTrackInfos", "trackSelectionsByPeriodAndRenderer", "immutableTrackSelectionsByPeriodAndRenderer", "mediaPreparer", "mediaPreparer.timeline", "mediaPreparer.mediaPeriods"})
    public final void assertPreparedWithMedia() {
        Assertions.checkState(this.isPreparedWithMedia);
    }

    public void clearTrackSelections(int i) {
        assertPreparedWithMedia();
        for (int i2 = 0; i2 < this.rendererCapabilities.length; i2++) {
            this.trackSelectionsByPeriodAndRenderer[i][i2].clear();
        }
    }

    public DownloadRequest getDownloadRequest(@Nullable byte[] bArr) {
        return getDownloadRequest(this.localConfiguration.uri.toString(), bArr);
    }

    @Nullable
    public Object getManifest() {
        if (this.mediaSource == null) {
            return null;
        }
        assertPreparedWithMedia();
        if (this.mediaPreparer.timeline.getWindowCount() > 0) {
            return this.mediaPreparer.timeline.getWindow(0, this.window, 0L).manifest;
        }
        return null;
    }

    public MappingTrackSelector.MappedTrackInfo getMappedTrackInfo(int i) {
        assertPreparedWithMedia();
        return this.mappedTrackInfos[i];
    }

    public int getPeriodCount() {
        if (this.mediaSource == null) {
            return 0;
        }
        assertPreparedWithMedia();
        return this.trackGroupArrays.length;
    }

    public TrackGroupArray getTrackGroups(int i) {
        assertPreparedWithMedia();
        return this.trackGroupArrays[i];
    }

    public List<ExoTrackSelection> getTrackSelections(int i, int i2) {
        assertPreparedWithMedia();
        return this.immutableTrackSelectionsByPeriodAndRenderer[i][i2];
    }

    public Tracks getTracks(int i) {
        assertPreparedWithMedia();
        return TrackSelectionUtil.buildTracks(this.mappedTrackInfos[i], this.immutableTrackSelectionsByPeriodAndRenderer[i]);
    }

    public final void onMediaPreparationFailed(final IOException iOException) {
        Handler handler = this.callbackHandler;
        handler.getClass();
        handler.post(new Runnable() { // from class: androidx.media3.exoplayer.offline.DownloadHelper$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                DownloadHelper.$r8$lambda$O6ITP06WF2y_qgTmOc0xmCMZg0c(this.f$0, iOException);
            }
        });
    }

    public final void onMediaPrepared() throws ExoPlaybackException {
        this.mediaPreparer.getClass();
        this.mediaPreparer.mediaPeriods.getClass();
        this.mediaPreparer.timeline.getClass();
        int length = this.mediaPreparer.mediaPeriods.length;
        int length2 = this.rendererCapabilities.length;
        this.trackSelectionsByPeriodAndRenderer = (List[][]) Array.newInstance((Class<?>) List.class, length, length2);
        this.immutableTrackSelectionsByPeriodAndRenderer = (List[][]) Array.newInstance((Class<?>) List.class, length, length2);
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < length2; i2++) {
                this.trackSelectionsByPeriodAndRenderer[i][i2] = new ArrayList();
                this.immutableTrackSelectionsByPeriodAndRenderer[i][i2] = DesugarCollections.unmodifiableList(this.trackSelectionsByPeriodAndRenderer[i][i2]);
            }
        }
        this.trackGroupArrays = new TrackGroupArray[length];
        this.mappedTrackInfos = new MappingTrackSelector.MappedTrackInfo[length];
        for (int i3 = 0; i3 < length; i3++) {
            this.trackGroupArrays[i3] = this.mediaPreparer.mediaPeriods[i3].getTrackGroups();
            TrackSelectorResult trackSelectorResultRunTrackSelection = runTrackSelection(i3);
            DefaultTrackSelector defaultTrackSelector = this.trackSelector;
            Object obj = trackSelectorResultRunTrackSelection.info;
            defaultTrackSelector.getClass();
            defaultTrackSelector.currentMappedTrackInfo = (MappingTrackSelector.MappedTrackInfo) obj;
            MappingTrackSelector.MappedTrackInfo[] mappedTrackInfoArr = this.mappedTrackInfos;
            MappingTrackSelector.MappedTrackInfo mappedTrackInfo = this.trackSelector.currentMappedTrackInfo;
            mappedTrackInfo.getClass();
            mappedTrackInfoArr[i3] = mappedTrackInfo;
        }
        this.isPreparedWithMedia = true;
        Handler handler = this.callbackHandler;
        handler.getClass();
        handler.post(new Runnable() { // from class: androidx.media3.exoplayer.offline.DownloadHelper$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                DownloadHelper.$r8$lambda$mal4ZQBeG6ek3_B0q_iPfVUUGoI(this.f$0);
            }
        });
    }

    public void prepare(final Callback callback) {
        Assertions.checkState(this.callback == null);
        this.callback = callback;
        MediaSource mediaSource = this.mediaSource;
        if (mediaSource != null) {
            this.mediaPreparer = new MediaPreparer(mediaSource, this);
        } else {
            this.callbackHandler.post(new Runnable() { // from class: androidx.media3.exoplayer.offline.DownloadHelper$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    DownloadHelper.$r8$lambda$WRCge6RrufzclVmxlrqq8iYqyCI(this.f$0, callback);
                }
            });
        }
    }

    public void release() {
        MediaPreparer mediaPreparer = this.mediaPreparer;
        if (mediaPreparer != null) {
            mediaPreparer.release();
        }
        this.trackSelector.release();
    }

    public void replaceTrackSelections(int i, TrackSelectionParameters trackSelectionParameters) {
        try {
            assertPreparedWithMedia();
            clearTrackSelections(i);
            addTrackSelectionInternal(i, trackSelectionParameters);
        } catch (ExoPlaybackException e) {
            throw new IllegalStateException(e);
        }
    }

    @RequiresNonNull({"trackGroupArrays", "trackSelectionsByPeriodAndRenderer", "mediaPreparer", "mediaPreparer.timeline"})
    public final TrackSelectorResult runTrackSelection(int i) throws ExoPlaybackException {
        TrackSelectorResult trackSelectorResultSelectTracks = this.trackSelector.selectTracks(this.rendererCapabilities, this.trackGroupArrays[i], new MediaSource.MediaPeriodId(this.mediaPreparer.timeline.getUidOfPeriod(i)), this.mediaPreparer.timeline);
        for (int i2 = 0; i2 < trackSelectorResultSelectTracks.length; i2++) {
            ExoTrackSelection exoTrackSelection = trackSelectorResultSelectTracks.selections[i2];
            if (exoTrackSelection != null) {
                List<ExoTrackSelection> list = this.trackSelectionsByPeriodAndRenderer[i][i2];
                int i3 = 0;
                while (true) {
                    if (i3 >= list.size()) {
                        list.add(exoTrackSelection);
                        break;
                    }
                    ExoTrackSelection exoTrackSelection2 = list.get(i3);
                    if (exoTrackSelection2.getTrackGroup().equals(exoTrackSelection.getTrackGroup())) {
                        this.scratchSet.clear();
                        for (int i4 = 0; i4 < exoTrackSelection2.length(); i4++) {
                            this.scratchSet.put(exoTrackSelection2.getIndexInTrackGroup(i4), 0);
                        }
                        for (int i5 = 0; i5 < exoTrackSelection.length(); i5++) {
                            this.scratchSet.put(exoTrackSelection.getIndexInTrackGroup(i5), 0);
                        }
                        int[] iArr = new int[this.scratchSet.size()];
                        for (int i6 = 0; i6 < this.scratchSet.size(); i6++) {
                            iArr[i6] = this.scratchSet.keyAt(i6);
                        }
                        list.set(i3, new DownloadTrackSelection(exoTrackSelection2.getTrackGroup(), iArr, 0));
                    } else {
                        i3++;
                    }
                }
            }
        }
        return trackSelectorResultSelectTracks;
    }

    @RequiresNonNull({"trackGroupArrays", "mappedTrackInfos", "trackSelectionsByPeriodAndRenderer", "immutableTrackSelectionsByPeriodAndRenderer", "mediaPreparer", "mediaPreparer.timeline", "mediaPreparer.mediaPeriods"})
    public final void setPreparedWithMedia() {
        this.isPreparedWithMedia = true;
    }

    public static MediaSource createMediaSource(DownloadRequest downloadRequest, DataSource.Factory factory, @Nullable DrmSessionManager drmSessionManager) {
        return createMediaSourceInternal(downloadRequest.toMediaItem(), factory, drmSessionManager);
    }

    public DownloadRequest getDownloadRequest(String str, @Nullable byte[] bArr) {
        MediaItem.LocalConfiguration localConfiguration = this.localConfiguration;
        DownloadRequest.Builder builder = new DownloadRequest.Builder(str, localConfiguration.uri);
        builder.mimeType = MimeTypes.normalizeMimeType(localConfiguration.mimeType);
        MediaItem.DrmConfiguration drmConfiguration = this.localConfiguration.drmConfiguration;
        builder.keySetId = drmConfiguration != null ? drmConfiguration.getKeySetId() : null;
        builder.customCacheKey = this.localConfiguration.customCacheKey;
        builder.data = bArr;
        if (this.mediaSource == null) {
            return builder.build();
        }
        assertPreparedWithMedia();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int length = this.trackSelectionsByPeriodAndRenderer.length;
        for (int i = 0; i < length; i++) {
            arrayList2.clear();
            int length2 = this.trackSelectionsByPeriodAndRenderer[i].length;
            for (int i2 = 0; i2 < length2; i2++) {
                arrayList2.addAll(this.trackSelectionsByPeriodAndRenderer[i][i2]);
            }
            arrayList.addAll(this.mediaPreparer.mediaPeriods[i].getStreamKeys(arrayList2));
        }
        builder.streamKeys = arrayList;
        return builder.build();
    }

    public static DownloadHelper forMediaItem(Context context, MediaItem mediaItem, @Nullable RenderersFactory renderersFactory, @Nullable DataSource.Factory factory) {
        return forMediaItem(mediaItem, getDefaultTrackSelectorParameters(context), renderersFactory, factory, null);
    }

    public static DownloadHelper forMediaItem(MediaItem mediaItem, TrackSelectionParameters trackSelectionParameters, @Nullable RenderersFactory renderersFactory, @Nullable DataSource.Factory factory) {
        return forMediaItem(mediaItem, trackSelectionParameters, renderersFactory, factory, null);
    }

    public static DownloadHelper forMediaItem(MediaItem mediaItem, TrackSelectionParameters trackSelectionParameters, @Nullable RenderersFactory renderersFactory, @Nullable DataSource.Factory factory, @Nullable DrmSessionManager drmSessionManager) {
        RendererCapabilities[] rendererCapabilities;
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        localConfiguration.getClass();
        boolean zIsProgressive = isProgressive(localConfiguration);
        Assertions.checkArgument(zIsProgressive || factory != null);
        MediaSource mediaSourceCreateMediaSourceInternal = zIsProgressive ? null : createMediaSourceInternal(mediaItem, factory, drmSessionManager);
        if (renderersFactory != null) {
            rendererCapabilities = getRendererCapabilities(renderersFactory);
        } else {
            rendererCapabilities = new RendererCapabilities[0];
        }
        return new DownloadHelper(mediaItem, mediaSourceCreateMediaSourceInternal, trackSelectionParameters, rendererCapabilities);
    }

    public static /* synthetic */ void $r8$lambda$6E1hFmjggl5xmNwOOeM2K23lf9w() {
    }

    public static /* synthetic */ void lambda$new$2() {
    }

    public static /* synthetic */ void $r8$lambda$4IErB0JnT5Fj4tRl2Hg1gCErCyA(CueGroup cueGroup) {
    }

    public static /* synthetic */ void $r8$lambda$haP4WROuoY6YG4XTC3somUOaJHA(Metadata metadata) {
    }

    public static /* synthetic */ void lambda$getRendererCapabilities$0(CueGroup cueGroup) {
    }

    public static /* synthetic */ void lambda$getRendererCapabilities$1(Metadata metadata) {
    }

    public static /* synthetic */ DrmSessionManager $r8$lambda$VAQWZ83QPLJG0xo3934latvqWFE(DrmSessionManager drmSessionManager, MediaItem mediaItem) {
        return drmSessionManager;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DownloadTrackSelection extends BaseTrackSelection {

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Factory implements ExoTrackSelection.Factory {
            public Factory() {
            }

            @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection.Factory
            public ExoTrackSelection[] createTrackSelections(ExoTrackSelection.Definition[] definitionArr, BandwidthMeter bandwidthMeter, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) {
                ExoTrackSelection[] exoTrackSelectionArr = new ExoTrackSelection[definitionArr.length];
                for (int i = 0; i < definitionArr.length; i++) {
                    ExoTrackSelection.Definition definition = definitionArr[i];
                    exoTrackSelectionArr[i] = definition == null ? null : new DownloadTrackSelection(definition.group, definition.tracks, 0);
                }
                return exoTrackSelectionArr;
            }

            public Factory(AnonymousClass1 anonymousClass1) {
            }
        }

        public DownloadTrackSelection(TrackGroup trackGroup, int[] iArr) {
            super(trackGroup, iArr, 0);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int getSelectedIndex() {
            return 0;
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        @Nullable
        public Object getSelectionData() {
            return null;
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int getSelectionReason() {
            return 0;
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void updateSelectedTrack(long j, long j2, long j3, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr) {
        }
    }
}
