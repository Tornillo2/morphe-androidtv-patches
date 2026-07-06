package com.google.android.exoplayer2.source;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.analytics.PlayerId;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayList;
import java.util.HashSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseMediaSource implements MediaSource {

    @Nullable
    public Looper looper;

    @Nullable
    public PlayerId playerId;

    @Nullable
    public Timeline timeline;
    public final ArrayList<MediaSource.MediaSourceCaller> mediaSourceCallers = new ArrayList<>(1);
    public final HashSet<MediaSource.MediaSourceCaller> enabledMediaSourceCallers = new HashSet<>(1);
    public final MediaSourceEventListener.EventDispatcher eventDispatcher = new MediaSourceEventListener.EventDispatcher();
    public final DrmSessionEventListener.EventDispatcher drmEventDispatcher = new DrmSessionEventListener.EventDispatcher();

    @Override // com.google.android.exoplayer2.source.MediaSource
    public final void addDrmEventListener(Handler handler, DrmSessionEventListener drmSessionEventListener) {
        handler.getClass();
        drmSessionEventListener.getClass();
        this.drmEventDispatcher.addEventListener(handler, drmSessionEventListener);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public final void addEventListener(Handler handler, MediaSourceEventListener mediaSourceEventListener) {
        handler.getClass();
        mediaSourceEventListener.getClass();
        this.eventDispatcher.addEventListener(handler, mediaSourceEventListener);
    }

    public final DrmSessionEventListener.EventDispatcher createDrmEventDispatcher(@Nullable MediaSource.MediaPeriodId mediaPeriodId) {
        return this.drmEventDispatcher.withParameters(0, mediaPeriodId);
    }

    public final MediaSourceEventListener.EventDispatcher createEventDispatcher(@Nullable MediaSource.MediaPeriodId mediaPeriodId) {
        return this.eventDispatcher.withParameters(0, mediaPeriodId, 0L);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public final void disable(MediaSource.MediaSourceCaller mediaSourceCaller) {
        boolean zIsEmpty = this.enabledMediaSourceCallers.isEmpty();
        this.enabledMediaSourceCallers.remove(mediaSourceCaller);
        if (zIsEmpty || !this.enabledMediaSourceCallers.isEmpty()) {
            return;
        }
        disableInternal();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public final void enable(MediaSource.MediaSourceCaller mediaSourceCaller) {
        this.looper.getClass();
        boolean zIsEmpty = this.enabledMediaSourceCallers.isEmpty();
        this.enabledMediaSourceCallers.add(mediaSourceCaller);
        if (zIsEmpty) {
            enableInternal();
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public /* synthetic */ Timeline getInitialTimeline() {
        return null;
    }

    public final PlayerId getPlayerId() {
        PlayerId playerId = this.playerId;
        Assertions.checkStateNotNull(playerId);
        return playerId;
    }

    public final boolean isEnabled() {
        return !this.enabledMediaSourceCallers.isEmpty();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public /* synthetic */ boolean isSingleWindow() {
        return true;
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public final void prepareSource(MediaSource.MediaSourceCaller mediaSourceCaller, @Nullable TransferListener transferListener) {
        prepareSource(mediaSourceCaller, transferListener, PlayerId.UNSET);
    }

    public abstract void prepareSourceInternal(@Nullable TransferListener transferListener);

    public final void refreshSourceInfo(Timeline timeline) {
        this.timeline = timeline;
        ArrayList<MediaSource.MediaSourceCaller> arrayList = this.mediaSourceCallers;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            MediaSource.MediaSourceCaller mediaSourceCaller = arrayList.get(i);
            i++;
            mediaSourceCaller.onSourceInfoRefreshed(this, timeline);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public final void releaseSource(MediaSource.MediaSourceCaller mediaSourceCaller) {
        this.mediaSourceCallers.remove(mediaSourceCaller);
        if (!this.mediaSourceCallers.isEmpty()) {
            disable(mediaSourceCaller);
            return;
        }
        this.looper = null;
        this.timeline = null;
        this.playerId = null;
        this.enabledMediaSourceCallers.clear();
        releaseSourceInternal();
    }

    public abstract void releaseSourceInternal();

    @Override // com.google.android.exoplayer2.source.MediaSource
    public final void removeDrmEventListener(DrmSessionEventListener drmSessionEventListener) {
        this.drmEventDispatcher.removeEventListener(drmSessionEventListener);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public final void removeEventListener(MediaSourceEventListener mediaSourceEventListener) {
        this.eventDispatcher.removeEventListener(mediaSourceEventListener);
    }

    public final DrmSessionEventListener.EventDispatcher createDrmEventDispatcher(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
        return this.drmEventDispatcher.withParameters(i, mediaPeriodId);
    }

    public final MediaSourceEventListener.EventDispatcher createEventDispatcher(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, long j) {
        return this.eventDispatcher.withParameters(i, mediaPeriodId, j);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public final void prepareSource(MediaSource.MediaSourceCaller mediaSourceCaller, @Nullable TransferListener transferListener, PlayerId playerId) {
        Looper looperMyLooper = Looper.myLooper();
        Looper looper = this.looper;
        Assertions.checkArgument(looper == null || looper == looperMyLooper);
        this.playerId = playerId;
        Timeline timeline = this.timeline;
        this.mediaSourceCallers.add(mediaSourceCaller);
        if (this.looper == null) {
            this.looper = looperMyLooper;
            this.enabledMediaSourceCallers.add(mediaSourceCaller);
            prepareSourceInternal(transferListener);
        } else if (timeline != null) {
            enable(mediaSourceCaller);
            mediaSourceCaller.onSourceInfoRefreshed(this, timeline);
        }
    }

    public final MediaSourceEventListener.EventDispatcher createEventDispatcher(MediaSource.MediaPeriodId mediaPeriodId, long j) {
        mediaPeriodId.getClass();
        return this.eventDispatcher.withParameters(0, mediaPeriodId, j);
    }

    public void disableInternal() {
    }

    public void enableInternal() {
    }
}
