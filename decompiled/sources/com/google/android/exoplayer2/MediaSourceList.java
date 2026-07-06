package com.google.android.exoplayer2;

import android.util.Pair;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.MediaSourceList;
import com.google.android.exoplayer2.analytics.AnalyticsCollector;
import com.google.android.exoplayer2.analytics.PlayerId;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.source.MaskingMediaPeriod;
import com.google.android.exoplayer2.source.MaskingMediaSource;
import com.google.android.exoplayer2.source.MediaLoadData;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.ShuffleOrder;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.HandlerWrapper;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MediaSourceList {
    public static final String TAG = "MediaSourceList";
    public final HandlerWrapper eventHandler;
    public final AnalyticsCollector eventListener;
    public boolean isPrepared;
    public final MediaSourceListInfoRefreshListener mediaSourceListInfoListener;

    @Nullable
    public TransferListener mediaTransferListener;
    public final PlayerId playerId;
    public ShuffleOrder shuffleOrder = new ShuffleOrder.DefaultShuffleOrder(0);
    public final IdentityHashMap<MediaPeriod, MediaSourceHolder> mediaSourceByMediaPeriod = new IdentityHashMap<>();
    public final Map<Object, MediaSourceHolder> mediaSourceByUid = new HashMap();
    public final List<MediaSourceHolder> mediaSourceHolders = new ArrayList();
    public final HashMap<MediaSourceHolder, MediaSourceAndListener> childSources = new HashMap<>();
    public final Set<MediaSourceHolder> enabledMediaSourceHolders = new HashSet();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class ForwardingEventListener implements MediaSourceEventListener, DrmSessionEventListener {
        public final MediaSourceHolder id;

        public static void $r8$lambda$R73wGROipMBBRf4MGgAn4ECnkqc(ForwardingEventListener forwardingEventListener, Pair pair, MediaLoadData mediaLoadData) {
            AnalyticsCollector analyticsCollector = MediaSourceList.this.eventListener;
            int iIntValue = ((Integer) pair.first).intValue();
            MediaSource.MediaPeriodId mediaPeriodId = (MediaSource.MediaPeriodId) pair.second;
            mediaPeriodId.getClass();
            analyticsCollector.onUpstreamDiscarded(iIntValue, mediaPeriodId, mediaLoadData);
        }

        public ForwardingEventListener(MediaSourceHolder mediaSourceHolder) {
            this.id = mediaSourceHolder;
        }

        @Nullable
        public final Pair<Integer, MediaSource.MediaPeriodId> getEventParameters(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
            MediaSource.MediaPeriodId mediaPeriodId2 = null;
            if (mediaPeriodId != null) {
                MediaSource.MediaPeriodId mediaPeriodIdForChildMediaPeriodId = MediaSourceList.getMediaPeriodIdForChildMediaPeriodId(this.id, mediaPeriodId);
                if (mediaPeriodIdForChildMediaPeriodId == null) {
                    return null;
                }
                mediaPeriodId2 = mediaPeriodIdForChildMediaPeriodId;
            }
            return Pair.create(Integer.valueOf(i + this.id.firstWindowIndexInChild), mediaPeriodId2);
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
        public void onDownstreamFormatChanged(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, final MediaLoadData mediaLoadData) {
            final Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer2.MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSourceList.ForwardingEventListener forwardingEventListener = this.f$0;
                        Pair pair = eventParameters;
                        MediaSourceList.this.eventListener.onDownstreamFormatChanged(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second, mediaLoadData);
                    }
                });
            }
        }

        @Override // com.google.android.exoplayer2.drm.DrmSessionEventListener
        public void onDrmKeysLoaded(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
            final Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer2.MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda11
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSourceList.ForwardingEventListener forwardingEventListener = this.f$0;
                        Pair pair = eventParameters;
                        MediaSourceList.this.eventListener.onDrmKeysLoaded(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second);
                    }
                });
            }
        }

        @Override // com.google.android.exoplayer2.drm.DrmSessionEventListener
        public void onDrmKeysRemoved(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
            final Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer2.MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSourceList.ForwardingEventListener forwardingEventListener = this.f$0;
                        Pair pair = eventParameters;
                        MediaSourceList.this.eventListener.onDrmKeysRemoved(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second);
                    }
                });
            }
        }

        @Override // com.google.android.exoplayer2.drm.DrmSessionEventListener
        public void onDrmKeysRestored(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
            final Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer2.MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSourceList.ForwardingEventListener forwardingEventListener = this.f$0;
                        Pair pair = eventParameters;
                        MediaSourceList.this.eventListener.onDrmKeysRestored(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second);
                    }
                });
            }
        }

        @Override // com.google.android.exoplayer2.drm.DrmSessionEventListener
        public /* synthetic */ void onDrmSessionAcquired(int i, MediaSource.MediaPeriodId mediaPeriodId) {
        }

        @Override // com.google.android.exoplayer2.drm.DrmSessionEventListener
        public void onDrmSessionManagerError(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, final Exception exc) {
            final Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer2.MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSourceList.ForwardingEventListener forwardingEventListener = this.f$0;
                        Pair pair = eventParameters;
                        MediaSourceList.this.eventListener.onDrmSessionManagerError(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second, exc);
                    }
                });
            }
        }

        @Override // com.google.android.exoplayer2.drm.DrmSessionEventListener
        public void onDrmSessionReleased(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
            final Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer2.MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSourceList.ForwardingEventListener forwardingEventListener = this.f$0;
                        Pair pair = eventParameters;
                        MediaSourceList.this.eventListener.onDrmSessionReleased(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second);
                    }
                });
            }
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
        public void onLoadCanceled(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, final LoadEventInfo loadEventInfo, final MediaLoadData mediaLoadData) {
            final Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer2.MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda8
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSourceList.ForwardingEventListener forwardingEventListener = this.f$0;
                        Pair pair = eventParameters;
                        MediaSourceList.this.eventListener.onLoadCanceled(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second, loadEventInfo, mediaLoadData);
                    }
                });
            }
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
        public void onLoadCompleted(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, final LoadEventInfo loadEventInfo, final MediaLoadData mediaLoadData) {
            final Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer2.MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSourceList.ForwardingEventListener forwardingEventListener = this.f$0;
                        Pair pair = eventParameters;
                        MediaSourceList.this.eventListener.onLoadCompleted(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second, loadEventInfo, mediaLoadData);
                    }
                });
            }
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
        public void onLoadError(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, final LoadEventInfo loadEventInfo, final MediaLoadData mediaLoadData, final IOException iOException, final boolean z) {
            final Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer2.MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda9
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSourceList.ForwardingEventListener forwardingEventListener = this.f$0;
                        Pair pair = eventParameters;
                        MediaSourceList.this.eventListener.onLoadError(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second, loadEventInfo, mediaLoadData, iOException, z);
                    }
                });
            }
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
        public void onLoadStarted(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, final LoadEventInfo loadEventInfo, final MediaLoadData mediaLoadData) {
            final Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer2.MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSourceList.ForwardingEventListener forwardingEventListener = this.f$0;
                        Pair pair = eventParameters;
                        MediaSourceList.this.eventListener.onLoadStarted(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second, loadEventInfo, mediaLoadData);
                    }
                });
            }
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
        public void onUpstreamDiscarded(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, final MediaLoadData mediaLoadData) {
            final Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer2.MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda10
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSourceList.ForwardingEventListener.$r8$lambda$R73wGROipMBBRf4MGgAn4ECnkqc(this.f$0, eventParameters, mediaLoadData);
                    }
                });
            }
        }

        @Override // com.google.android.exoplayer2.drm.DrmSessionEventListener
        public void onDrmSessionAcquired(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, final int i2) {
            final Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer2.MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda7
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSourceList.ForwardingEventListener forwardingEventListener = this.f$0;
                        Pair pair = eventParameters;
                        MediaSourceList.this.eventListener.onDrmSessionAcquired(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second, i2);
                    }
                });
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MediaSourceAndListener {
        public final MediaSource.MediaSourceCaller caller;
        public final ForwardingEventListener eventListener;
        public final MediaSource mediaSource;

        public MediaSourceAndListener(MediaSource mediaSource, MediaSource.MediaSourceCaller mediaSourceCaller, ForwardingEventListener forwardingEventListener) {
            this.mediaSource = mediaSource;
            this.caller = mediaSourceCaller;
            this.eventListener = forwardingEventListener;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MediaSourceHolder implements MediaSourceInfoHolder {
        public int firstWindowIndexInChild;
        public boolean isRemoved;
        public final MaskingMediaSource mediaSource;
        public final List<MediaSource.MediaPeriodId> activeMediaPeriodIds = new ArrayList();
        public final Object uid = new Object();

        public MediaSourceHolder(MediaSource mediaSource, boolean z) {
            this.mediaSource = new MaskingMediaSource(mediaSource, z);
        }

        @Override // com.google.android.exoplayer2.MediaSourceInfoHolder
        public Timeline getTimeline() {
            return this.mediaSource.timeline;
        }

        @Override // com.google.android.exoplayer2.MediaSourceInfoHolder
        public Object getUid() {
            return this.uid;
        }

        public void reset(int i) {
            this.firstWindowIndexInChild = i;
            this.isRemoved = false;
            this.activeMediaPeriodIds.clear();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface MediaSourceListInfoRefreshListener {
        void onPlaylistUpdateRequested();
    }

    public MediaSourceList(MediaSourceListInfoRefreshListener mediaSourceListInfoRefreshListener, AnalyticsCollector analyticsCollector, HandlerWrapper handlerWrapper, PlayerId playerId) {
        this.playerId = playerId;
        this.mediaSourceListInfoListener = mediaSourceListInfoRefreshListener;
        this.eventListener = analyticsCollector;
        this.eventHandler = handlerWrapper;
    }

    public static int access$200(MediaSourceHolder mediaSourceHolder, int i) {
        return i + mediaSourceHolder.firstWindowIndexInChild;
    }

    public static Object getChildPeriodUid(Object obj) {
        return AbstractConcatenatedTimeline.getChildPeriodUidFromConcatenatedUid(obj);
    }

    @Nullable
    public static MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSourceHolder mediaSourceHolder, MediaSource.MediaPeriodId mediaPeriodId) {
        for (int i = 0; i < mediaSourceHolder.activeMediaPeriodIds.size(); i++) {
            if (mediaSourceHolder.activeMediaPeriodIds.get(i).windowSequenceNumber == mediaPeriodId.windowSequenceNumber) {
                return mediaPeriodId.copyWithPeriodUid(AbstractConcatenatedTimeline.getConcatenatedUid(mediaSourceHolder.uid, mediaPeriodId.periodUid));
            }
        }
        return null;
    }

    public static Object getMediaSourceHolderUid(Object obj) {
        return AbstractConcatenatedTimeline.getChildTimelineUidFromConcatenatedUid(obj);
    }

    public static Object getPeriodUid(MediaSourceHolder mediaSourceHolder, Object obj) {
        return AbstractConcatenatedTimeline.getConcatenatedUid(mediaSourceHolder.uid, obj);
    }

    public static int getWindowIndexForChildWindowIndex(MediaSourceHolder mediaSourceHolder, int i) {
        return i + mediaSourceHolder.firstWindowIndexInChild;
    }

    public Timeline addMediaSources(int i, List<MediaSourceHolder> list, ShuffleOrder shuffleOrder) {
        if (!list.isEmpty()) {
            this.shuffleOrder = shuffleOrder;
            for (int i2 = i; i2 < list.size() + i; i2++) {
                MediaSourceHolder mediaSourceHolder = list.get(i2 - i);
                if (i2 > 0) {
                    MediaSourceHolder mediaSourceHolder2 = this.mediaSourceHolders.get(i2 - 1);
                    mediaSourceHolder.reset(mediaSourceHolder2.mediaSource.timeline.timeline.getWindowCount() + mediaSourceHolder2.firstWindowIndexInChild);
                } else {
                    mediaSourceHolder.reset(0);
                }
                correctOffsets(i2, mediaSourceHolder.mediaSource.timeline.timeline.getWindowCount());
                this.mediaSourceHolders.add(i2, mediaSourceHolder);
                this.mediaSourceByUid.put(mediaSourceHolder.uid, mediaSourceHolder);
                if (this.isPrepared) {
                    prepareChildSource(mediaSourceHolder);
                    if (this.mediaSourceByMediaPeriod.isEmpty()) {
                        this.enabledMediaSourceHolders.add(mediaSourceHolder);
                    } else {
                        disableChildSource(mediaSourceHolder);
                    }
                }
            }
        }
        return createTimeline();
    }

    public Timeline clear(@Nullable ShuffleOrder shuffleOrder) {
        if (shuffleOrder == null) {
            shuffleOrder = this.shuffleOrder.cloneAndClear();
        }
        this.shuffleOrder = shuffleOrder;
        removeMediaSourcesInternal(0, this.mediaSourceHolders.size());
        return createTimeline();
    }

    public final void correctOffsets(int i, int i2) {
        while (i < this.mediaSourceHolders.size()) {
            this.mediaSourceHolders.get(i).firstWindowIndexInChild += i2;
            i++;
        }
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        Object childTimelineUidFromConcatenatedUid = AbstractConcatenatedTimeline.getChildTimelineUidFromConcatenatedUid(mediaPeriodId.periodUid);
        MediaSource.MediaPeriodId mediaPeriodIdCopyWithPeriodUid = mediaPeriodId.copyWithPeriodUid(AbstractConcatenatedTimeline.getChildPeriodUidFromConcatenatedUid(mediaPeriodId.periodUid));
        MediaSourceHolder mediaSourceHolder = this.mediaSourceByUid.get(childTimelineUidFromConcatenatedUid);
        mediaSourceHolder.getClass();
        enableMediaSource(mediaSourceHolder);
        mediaSourceHolder.activeMediaPeriodIds.add(mediaPeriodIdCopyWithPeriodUid);
        MaskingMediaPeriod maskingMediaPeriodCreatePeriod = mediaSourceHolder.mediaSource.createPeriod(mediaPeriodIdCopyWithPeriodUid, allocator, j);
        this.mediaSourceByMediaPeriod.put(maskingMediaPeriodCreatePeriod, mediaSourceHolder);
        disableUnusedMediaSources();
        return maskingMediaPeriodCreatePeriod;
    }

    public Timeline createTimeline() {
        if (this.mediaSourceHolders.isEmpty()) {
            return Timeline.EMPTY;
        }
        int windowCount = 0;
        for (int i = 0; i < this.mediaSourceHolders.size(); i++) {
            MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(i);
            mediaSourceHolder.firstWindowIndexInChild = windowCount;
            windowCount += mediaSourceHolder.mediaSource.timeline.timeline.getWindowCount();
        }
        return new PlaylistTimeline(this.mediaSourceHolders, this.shuffleOrder);
    }

    public final void disableChildSource(MediaSourceHolder mediaSourceHolder) {
        MediaSourceAndListener mediaSourceAndListener = this.childSources.get(mediaSourceHolder);
        if (mediaSourceAndListener != null) {
            mediaSourceAndListener.mediaSource.disable(mediaSourceAndListener.caller);
        }
    }

    public final void disableUnusedMediaSources() {
        Iterator<MediaSourceHolder> it = this.enabledMediaSourceHolders.iterator();
        while (it.hasNext()) {
            MediaSourceHolder next = it.next();
            if (next.activeMediaPeriodIds.isEmpty()) {
                disableChildSource(next);
                it.remove();
            }
        }
    }

    public final void enableMediaSource(MediaSourceHolder mediaSourceHolder) {
        this.enabledMediaSourceHolders.add(mediaSourceHolder);
        MediaSourceAndListener mediaSourceAndListener = this.childSources.get(mediaSourceHolder);
        if (mediaSourceAndListener != null) {
            mediaSourceAndListener.mediaSource.enable(mediaSourceAndListener.caller);
        }
    }

    public int getSize() {
        return this.mediaSourceHolders.size();
    }

    public boolean isPrepared() {
        return this.isPrepared;
    }

    public final void maybeReleaseChildSource(MediaSourceHolder mediaSourceHolder) {
        if (mediaSourceHolder.isRemoved && mediaSourceHolder.activeMediaPeriodIds.isEmpty()) {
            MediaSourceAndListener mediaSourceAndListenerRemove = this.childSources.remove(mediaSourceHolder);
            mediaSourceAndListenerRemove.getClass();
            mediaSourceAndListenerRemove.mediaSource.releaseSource(mediaSourceAndListenerRemove.caller);
            mediaSourceAndListenerRemove.mediaSource.removeEventListener(mediaSourceAndListenerRemove.eventListener);
            mediaSourceAndListenerRemove.mediaSource.removeDrmEventListener(mediaSourceAndListenerRemove.eventListener);
            this.enabledMediaSourceHolders.remove(mediaSourceHolder);
        }
    }

    public Timeline moveMediaSource(int i, int i2, ShuffleOrder shuffleOrder) {
        return moveMediaSourceRange(i, i + 1, i2, shuffleOrder);
    }

    public Timeline moveMediaSourceRange(int i, int i2, int i3, ShuffleOrder shuffleOrder) {
        Assertions.checkArgument(i >= 0 && i <= i2 && i2 <= this.mediaSourceHolders.size() && i3 >= 0);
        this.shuffleOrder = shuffleOrder;
        if (i == i2 || i == i3) {
            return createTimeline();
        }
        int iMin = Math.min(i, i3);
        int iMax = Math.max(((i2 - i) + i3) - 1, i2 - 1);
        int windowCount = this.mediaSourceHolders.get(iMin).firstWindowIndexInChild;
        Util.moveItems(this.mediaSourceHolders, i, i2, i3);
        while (iMin <= iMax) {
            MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(iMin);
            mediaSourceHolder.firstWindowIndexInChild = windowCount;
            windowCount += mediaSourceHolder.mediaSource.timeline.timeline.getWindowCount();
            iMin++;
        }
        return createTimeline();
    }

    public void prepare(@Nullable TransferListener transferListener) {
        Assertions.checkState(!this.isPrepared);
        this.mediaTransferListener = transferListener;
        for (int i = 0; i < this.mediaSourceHolders.size(); i++) {
            MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(i);
            prepareChildSource(mediaSourceHolder);
            this.enabledMediaSourceHolders.add(mediaSourceHolder);
        }
        this.isPrepared = true;
    }

    public final void prepareChildSource(MediaSourceHolder mediaSourceHolder) {
        MaskingMediaSource maskingMediaSource = mediaSourceHolder.mediaSource;
        MediaSource.MediaSourceCaller mediaSourceCaller = new MediaSource.MediaSourceCaller() { // from class: com.google.android.exoplayer2.MediaSourceList$$ExternalSyntheticLambda0
            @Override // com.google.android.exoplayer2.source.MediaSource.MediaSourceCaller
            public final void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline) {
                this.f$0.mediaSourceListInfoListener.onPlaylistUpdateRequested();
            }
        };
        ForwardingEventListener forwardingEventListener = new ForwardingEventListener(mediaSourceHolder);
        this.childSources.put(mediaSourceHolder, new MediaSourceAndListener(maskingMediaSource, mediaSourceCaller, forwardingEventListener));
        maskingMediaSource.addEventListener(Util.createHandlerForCurrentOrMainLooper(), forwardingEventListener);
        maskingMediaSource.addDrmEventListener(Util.createHandlerForCurrentOrMainLooper(null), forwardingEventListener);
        maskingMediaSource.prepareSource(mediaSourceCaller, this.mediaTransferListener, this.playerId);
    }

    public void release() {
        for (MediaSourceAndListener mediaSourceAndListener : this.childSources.values()) {
            try {
                mediaSourceAndListener.mediaSource.releaseSource(mediaSourceAndListener.caller);
            } catch (RuntimeException e) {
                Log.e("MediaSourceList", "Failed to release child source.", e);
            }
            mediaSourceAndListener.mediaSource.removeEventListener(mediaSourceAndListener.eventListener);
            mediaSourceAndListener.mediaSource.removeDrmEventListener(mediaSourceAndListener.eventListener);
        }
        this.childSources.clear();
        this.enabledMediaSourceHolders.clear();
        this.isPrepared = false;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        MediaSourceHolder mediaSourceHolderRemove = this.mediaSourceByMediaPeriod.remove(mediaPeriod);
        mediaSourceHolderRemove.getClass();
        mediaSourceHolderRemove.mediaSource.releasePeriod(mediaPeriod);
        mediaSourceHolderRemove.activeMediaPeriodIds.remove(((MaskingMediaPeriod) mediaPeriod).id);
        if (!this.mediaSourceByMediaPeriod.isEmpty()) {
            disableUnusedMediaSources();
        }
        maybeReleaseChildSource(mediaSourceHolderRemove);
    }

    public Timeline removeMediaSourceRange(int i, int i2, ShuffleOrder shuffleOrder) {
        Assertions.checkArgument(i >= 0 && i <= i2 && i2 <= this.mediaSourceHolders.size());
        this.shuffleOrder = shuffleOrder;
        removeMediaSourcesInternal(i, i2);
        return createTimeline();
    }

    public final void removeMediaSourcesInternal(int i, int i2) {
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            MediaSourceHolder mediaSourceHolderRemove = this.mediaSourceHolders.remove(i3);
            this.mediaSourceByUid.remove(mediaSourceHolderRemove.uid);
            correctOffsets(i3, -mediaSourceHolderRemove.mediaSource.timeline.timeline.getWindowCount());
            mediaSourceHolderRemove.isRemoved = true;
            if (this.isPrepared) {
                maybeReleaseChildSource(mediaSourceHolderRemove);
            }
        }
    }

    public Timeline setMediaSources(List<MediaSourceHolder> list, ShuffleOrder shuffleOrder) {
        removeMediaSourcesInternal(0, this.mediaSourceHolders.size());
        return addMediaSources(this.mediaSourceHolders.size(), list, shuffleOrder);
    }

    public Timeline setShuffleOrder(ShuffleOrder shuffleOrder) {
        int size = this.mediaSourceHolders.size();
        if (shuffleOrder.getLength() != size) {
            shuffleOrder = shuffleOrder.cloneAndClear().cloneAndInsert(0, size);
        }
        this.shuffleOrder = shuffleOrder;
        return createTimeline();
    }
}
