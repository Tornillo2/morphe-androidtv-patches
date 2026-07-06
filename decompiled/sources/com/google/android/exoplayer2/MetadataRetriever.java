package com.google.android.exoplayer2;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.annotation.VisibleForTesting;
import com.google.android.exoplayer2.analytics.PlayerId;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.HandlerWrapper;
import com.google.android.exoplayer2.util.SystemHandlerWrapper;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MetadataRetriever {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MetadataRetrieverInternal {
        public static final int MESSAGE_CHECK_FOR_FAILURE = 1;
        public static final int MESSAGE_CONTINUE_LOADING = 2;
        public static final int MESSAGE_PREPARE_SOURCE = 0;
        public static final int MESSAGE_RELEASE = 3;
        public final MediaSource.Factory mediaSourceFactory;
        public final HandlerWrapper mediaSourceHandler;
        public final HandlerThread mediaSourceThread;
        public final SettableFuture<TrackGroupArray> trackGroupsFuture;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public final class MediaSourceHandlerCallback implements Handler.Callback {
            public static final int ERROR_POLL_INTERVAL_MS = 100;
            public MediaPeriod mediaPeriod;
            public MediaSource mediaSource;
            public final MediaSourceCaller mediaSourceCaller = new MediaSourceCaller();

            /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
            public final class MediaSourceCaller implements MediaSource.MediaSourceCaller {
                public boolean mediaPeriodCreated;
                public final MediaPeriodCallback mediaPeriodCallback = new MediaPeriodCallback();
                public final Allocator allocator = new DefaultAllocator(true, 65536, 0);

                /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
                public final class MediaPeriodCallback implements MediaPeriod.Callback {
                    public MediaPeriodCallback() {
                    }

                    @Override // com.google.android.exoplayer2.source.MediaPeriod.Callback
                    public void onPrepared(MediaPeriod mediaPeriod) {
                        MetadataRetrieverInternal.this.trackGroupsFuture.set(mediaPeriod.getTrackGroups());
                        ((SystemHandlerWrapper.SystemMessage) MetadataRetrieverInternal.this.mediaSourceHandler.obtainMessage(3)).sendToTarget();
                    }

                    @Override // com.google.android.exoplayer2.source.SequenceableLoader.Callback
                    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
                        ((SystemHandlerWrapper.SystemMessage) MetadataRetrieverInternal.this.mediaSourceHandler.obtainMessage(2)).sendToTarget();
                    }
                }

                public MediaSourceCaller() {
                }

                @Override // com.google.android.exoplayer2.source.MediaSource.MediaSourceCaller
                public void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline) {
                    if (this.mediaPeriodCreated) {
                        return;
                    }
                    this.mediaPeriodCreated = true;
                    MediaSourceHandlerCallback.this.mediaPeriod = mediaSource.createPeriod(new MediaSource.MediaPeriodId(timeline.getUidOfPeriod(0)), this.allocator, 0L);
                    MediaSourceHandlerCallback.this.mediaPeriod.prepare(this.mediaPeriodCallback, 0L);
                }
            }

            public MediaSourceHandlerCallback() {
            }

            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    MediaSource mediaSourceCreateMediaSource = MetadataRetrieverInternal.this.mediaSourceFactory.createMediaSource((MediaItem) message.obj);
                    this.mediaSource = mediaSourceCreateMediaSource;
                    mediaSourceCreateMediaSource.prepareSource(this.mediaSourceCaller, null, PlayerId.UNSET);
                    MetadataRetrieverInternal.this.mediaSourceHandler.sendEmptyMessage(1);
                    return true;
                }
                if (i == 1) {
                    try {
                        MediaPeriod mediaPeriod = this.mediaPeriod;
                        if (mediaPeriod == null) {
                            MediaSource mediaSource = this.mediaSource;
                            mediaSource.getClass();
                            mediaSource.maybeThrowSourceInfoRefreshError();
                        } else {
                            mediaPeriod.maybeThrowPrepareError();
                        }
                        MetadataRetrieverInternal.this.mediaSourceHandler.sendEmptyMessageDelayed(1, 100);
                        return true;
                    } catch (Exception e) {
                        MetadataRetrieverInternal.this.trackGroupsFuture.setException(e);
                        ((SystemHandlerWrapper.SystemMessage) MetadataRetrieverInternal.this.mediaSourceHandler.obtainMessage(3)).sendToTarget();
                        return true;
                    }
                }
                if (i == 2) {
                    MediaPeriod mediaPeriod2 = this.mediaPeriod;
                    mediaPeriod2.getClass();
                    mediaPeriod2.continueLoading(0L);
                    return true;
                }
                if (i != 3) {
                    return false;
                }
                if (this.mediaPeriod != null) {
                    MediaSource mediaSource2 = this.mediaSource;
                    mediaSource2.getClass();
                    mediaSource2.releasePeriod(this.mediaPeriod);
                }
                MediaSource mediaSource3 = this.mediaSource;
                mediaSource3.getClass();
                mediaSource3.releaseSource(this.mediaSourceCaller);
                MetadataRetrieverInternal.this.mediaSourceHandler.removeCallbacksAndMessages(null);
                MetadataRetrieverInternal.this.mediaSourceThread.quit();
                return true;
            }
        }

        public MetadataRetrieverInternal(MediaSource.Factory factory, Clock clock) {
            this.mediaSourceFactory = factory;
            HandlerThread handlerThread = new HandlerThread("ExoPlayer:MetadataRetriever");
            this.mediaSourceThread = handlerThread;
            handlerThread.start();
            this.mediaSourceHandler = clock.createHandler(handlerThread.getLooper(), new MediaSourceHandlerCallback());
            this.trackGroupsFuture = SettableFuture.create();
        }

        public ListenableFuture<TrackGroupArray> retrieveMetadata(MediaItem mediaItem) {
            ((SystemHandlerWrapper.SystemMessage) this.mediaSourceHandler.obtainMessage(0, mediaItem)).sendToTarget();
            return this.trackGroupsFuture;
        }
    }

    public static ListenableFuture<TrackGroupArray> retrieveMetadata(Context context, MediaItem mediaItem) {
        return retrieveMetadata(context, mediaItem, Clock.DEFAULT);
    }

    public static ListenableFuture<TrackGroupArray> retrieveMetadata(MediaSource.Factory factory, MediaItem mediaItem) {
        return retrieveMetadata(factory, mediaItem, Clock.DEFAULT);
    }

    @VisibleForTesting
    public static ListenableFuture<TrackGroupArray> retrieveMetadata(Context context, MediaItem mediaItem, Clock clock) {
        DefaultExtractorsFactory defaultExtractorsFactory = new DefaultExtractorsFactory();
        defaultExtractorsFactory.setMp4ExtractorFlags(6);
        return retrieveMetadata(new DefaultMediaSourceFactory(context, defaultExtractorsFactory), mediaItem, clock);
    }

    public static ListenableFuture<TrackGroupArray> retrieveMetadata(MediaSource.Factory factory, MediaItem mediaItem, Clock clock) {
        return new MetadataRetrieverInternal(factory, clock).retrieveMetadata(mediaItem);
    }
}
