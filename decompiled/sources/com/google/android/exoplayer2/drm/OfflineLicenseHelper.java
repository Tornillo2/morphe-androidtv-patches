package com.google.android.exoplayer2.drm;

import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Pair;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.analytics.PlayerId;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.drm.ExoMediaDrm;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.common.util.concurrent.SettableFuture;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@RequiresApi(18)
public final class OfflineLicenseHelper {
    public static final Format FORMAT_WITH_EMPTY_DRM_INIT_DATA;
    public final ConditionVariable drmListenerConditionVariable;
    public final DefaultDrmSessionManager drmSessionManager;
    public final DrmSessionEventListener.EventDispatcher eventDispatcher;
    public final Handler handler;
    public final HandlerThread handlerThread;

    /* JADX INFO: renamed from: $r8$lambda$Wju-5Twa8nRFNeR-Ms3wBdGckW4, reason: not valid java name */
    public static void m452$r8$lambda$Wju5Twa8nRFNeRMs3wBdGckW4(OfflineLicenseHelper offlineLicenseHelper, SettableFuture settableFuture, DrmSession drmSession) {
        offlineLicenseHelper.getClass();
        try {
            Pair<Long, Long> licenseDurationRemainingSec = WidevineUtil.getLicenseDurationRemainingSec(drmSession);
            licenseDurationRemainingSec.getClass();
            settableFuture.set(licenseDurationRemainingSec);
        } finally {
            try {
            } finally {
            }
        }
    }

    public static /* synthetic */ void $r8$lambda$e9eYovKS_v2TSZ_iVrMRsVr3Pac(OfflineLicenseHelper offlineLicenseHelper, DrmSession drmSession, SettableFuture settableFuture) {
        offlineLicenseHelper.getClass();
        try {
            DrmSession.DrmSessionException error = drmSession.getError();
            if (drmSession.getState() == 1) {
                drmSession.release(offlineLicenseHelper.eventDispatcher);
                offlineLicenseHelper.drmSessionManager.release();
            }
            settableFuture.set(error);
        } catch (Throwable th) {
            settableFuture.setException(th);
            drmSession.release(offlineLicenseHelper.eventDispatcher);
            offlineLicenseHelper.drmSessionManager.release();
        }
    }

    public static /* synthetic */ void $r8$lambda$iwe5Xt3V7f8y8PE2Term5idZZTA(OfflineLicenseHelper offlineLicenseHelper, SettableFuture settableFuture, DrmSession drmSession) {
        offlineLicenseHelper.getClass();
        try {
            settableFuture.set(drmSession.getOfflineLicenseKeySetId());
        } finally {
            try {
            } finally {
            }
        }
    }

    public static /* synthetic */ void $r8$lambda$kXU0gEIdWELrfVZcPBQDB50j5Zo(OfflineLicenseHelper offlineLicenseHelper, SettableFuture settableFuture) {
        offlineLicenseHelper.getClass();
        try {
            offlineLicenseHelper.drmSessionManager.release();
            settableFuture.set(null);
        } catch (Throwable th) {
            settableFuture.setException(th);
        }
    }

    public static void $r8$lambda$qOheiByonsUVmNcVwqDHWfJHaVc(OfflineLicenseHelper offlineLicenseHelper, int i, byte[] bArr, SettableFuture settableFuture, Format format) {
        offlineLicenseHelper.getClass();
        try {
            DefaultDrmSessionManager defaultDrmSessionManager = offlineLicenseHelper.drmSessionManager;
            Looper looperMyLooper = Looper.myLooper();
            looperMyLooper.getClass();
            defaultDrmSessionManager.setPlayer(looperMyLooper, PlayerId.UNSET);
            offlineLicenseHelper.drmSessionManager.prepare();
            try {
                offlineLicenseHelper.drmSessionManager.setMode(i, bArr);
                DrmSession drmSessionAcquireSession = offlineLicenseHelper.drmSessionManager.acquireSession(offlineLicenseHelper.eventDispatcher, format);
                drmSessionAcquireSession.getClass();
                settableFuture.set(drmSessionAcquireSession);
            } catch (Throwable th) {
                offlineLicenseHelper.drmSessionManager.release();
                throw th;
            }
        } catch (Throwable th2) {
            settableFuture.setException(th2);
        }
    }

    static {
        Format.Builder builder = new Format.Builder();
        builder.drmInitData = new DrmInitData(new DrmInitData.SchemeData[0]);
        FORMAT_WITH_EMPTY_DRM_INIT_DATA = new Format(builder);
    }

    @Deprecated
    public OfflineLicenseHelper(UUID uuid, ExoMediaDrm.Provider provider, MediaDrmCallback mediaDrmCallback, @Nullable Map<String, String> map, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        DefaultDrmSessionManager.Builder builder = new DefaultDrmSessionManager.Builder();
        builder.setUuidAndExoMediaDrmProvider(uuid, provider);
        builder.setKeyRequestParameters(map);
        this(builder.build(mediaDrmCallback), eventDispatcher);
    }

    public static OfflineLicenseHelper newWidevineInstance(String str, DataSource.Factory factory, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        return newWidevineInstance(str, false, factory, null, eventDispatcher);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final DrmSession acquireFirstSessionOnHandlerThread(final int i, @Nullable final byte[] bArr, final Format format) throws DrmSession.DrmSessionException {
        format.drmInitData.getClass();
        final SettableFuture settableFutureCreate = SettableFuture.create();
        this.drmListenerConditionVariable.close();
        this.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.drm.OfflineLicenseHelper$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                OfflineLicenseHelper.$r8$lambda$qOheiByonsUVmNcVwqDHWfJHaVc(this.f$0, i, bArr, settableFutureCreate, format);
            }
        });
        try {
            final DrmSession drmSession = (DrmSession) settableFutureCreate.blockingGet();
            this.drmListenerConditionVariable.block();
            final SettableFuture settableFuture = new SettableFuture();
            this.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.drm.OfflineLicenseHelper$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineLicenseHelper.$r8$lambda$e9eYovKS_v2TSZ_iVrMRsVr3Pac(this.f$0, drmSession, settableFuture);
                }
            });
            try {
                DrmSession.DrmSessionException drmSessionException = (DrmSession.DrmSessionException) settableFuture.blockingGet();
                if (drmSessionException == null) {
                    return drmSession;
                }
                throw drmSessionException;
            } catch (InterruptedException | ExecutionException e) {
                throw new IllegalStateException(e);
            }
        } catch (InterruptedException | ExecutionException e2) {
            throw new IllegalStateException(e2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final byte[] acquireSessionAndGetOfflineLicenseKeySetIdOnHandlerThread(int i, @Nullable byte[] bArr, Format format) throws DrmSession.DrmSessionException {
        final DrmSession drmSessionAcquireFirstSessionOnHandlerThread = acquireFirstSessionOnHandlerThread(i, bArr, format);
        final SettableFuture settableFuture = new SettableFuture();
        this.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.drm.OfflineLicenseHelper$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                OfflineLicenseHelper.$r8$lambda$iwe5Xt3V7f8y8PE2Term5idZZTA(this.f$0, settableFuture, drmSessionAcquireFirstSessionOnHandlerThread);
            }
        });
        try {
            try {
                byte[] bArr2 = (byte[]) settableFuture.blockingGet();
                bArr2.getClass();
                return bArr2;
            } finally {
                releaseManagerOnHandlerThread();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException(e);
        }
    }

    public synchronized byte[] downloadLicense(Format format) throws DrmSession.DrmSessionException {
        Assertions.checkArgument(format.drmInitData != null);
        return acquireSessionAndGetOfflineLicenseKeySetIdOnHandlerThread(2, null, format);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized Pair<Long, Long> getLicenseDurationRemainingSec(byte[] bArr) throws DrmSession.DrmSessionException {
        final SettableFuture settableFuture;
        bArr.getClass();
        try {
            final DrmSession drmSessionAcquireFirstSessionOnHandlerThread = acquireFirstSessionOnHandlerThread(1, bArr, FORMAT_WITH_EMPTY_DRM_INIT_DATA);
            settableFuture = new SettableFuture();
            this.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.drm.OfflineLicenseHelper$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineLicenseHelper.m452$r8$lambda$Wju5Twa8nRFNeRMs3wBdGckW4(this.f$0, settableFuture, drmSessionAcquireFirstSessionOnHandlerThread);
                }
            });
            try {
                try {
                } finally {
                    releaseManagerOnHandlerThread();
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new IllegalStateException(e);
            }
        } catch (DrmSession.DrmSessionException e2) {
            if (e2.getCause() instanceof KeysExpiredException) {
                return Pair.create(0L, 0L);
            }
            throw e2;
        }
        return (Pair) settableFuture.blockingGet();
    }

    public void release() {
        this.handlerThread.quit();
    }

    public synchronized void releaseLicense(byte[] bArr) throws DrmSession.DrmSessionException {
        bArr.getClass();
        acquireSessionAndGetOfflineLicenseKeySetIdOnHandlerThread(3, bArr, FORMAT_WITH_EMPTY_DRM_INIT_DATA);
    }

    public final void releaseManagerOnHandlerThread() {
        final SettableFuture settableFutureCreate = SettableFuture.create();
        this.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.drm.OfflineLicenseHelper$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                OfflineLicenseHelper.$r8$lambda$kXU0gEIdWELrfVZcPBQDB50j5Zo(this.f$0, settableFutureCreate);
            }
        });
        try {
            settableFutureCreate.blockingGet();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException(e);
        }
    }

    public synchronized byte[] renewLicense(byte[] bArr) throws DrmSession.DrmSessionException {
        bArr.getClass();
        return acquireSessionAndGetOfflineLicenseKeySetIdOnHandlerThread(2, bArr, FORMAT_WITH_EMPTY_DRM_INIT_DATA);
    }

    public static OfflineLicenseHelper newWidevineInstance(String str, boolean z, DataSource.Factory factory, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        return newWidevineInstance(str, z, factory, null, eventDispatcher);
    }

    public static OfflineLicenseHelper newWidevineInstance(String str, boolean z, DataSource.Factory factory, @Nullable Map<String, String> map, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        DefaultDrmSessionManager.Builder builder = new DefaultDrmSessionManager.Builder();
        builder.setKeyRequestParameters(map);
        return new OfflineLicenseHelper(builder.build(new HttpMediaDrmCallback(str, z, factory)), eventDispatcher);
    }

    public OfflineLicenseHelper(DefaultDrmSessionManager defaultDrmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        this.drmSessionManager = defaultDrmSessionManager;
        this.eventDispatcher = eventDispatcher;
        HandlerThread handlerThread = new HandlerThread("ExoPlayer:OfflineLicenseHelper");
        this.handlerThread = handlerThread;
        handlerThread.start();
        this.handler = new Handler(handlerThread.getLooper());
        this.drmListenerConditionVariable = new ConditionVariable();
        eventDispatcher.addEventListener(new Handler(handlerThread.getLooper()), new DrmSessionEventListener() { // from class: com.google.android.exoplayer2.drm.OfflineLicenseHelper.1
            @Override // com.google.android.exoplayer2.drm.DrmSessionEventListener
            public void onDrmKeysLoaded(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
                OfflineLicenseHelper.this.drmListenerConditionVariable.open();
            }

            @Override // com.google.android.exoplayer2.drm.DrmSessionEventListener
            public void onDrmKeysRemoved(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
                OfflineLicenseHelper.this.drmListenerConditionVariable.open();
            }

            @Override // com.google.android.exoplayer2.drm.DrmSessionEventListener
            public void onDrmKeysRestored(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
                OfflineLicenseHelper.this.drmListenerConditionVariable.open();
            }

            @Override // com.google.android.exoplayer2.drm.DrmSessionEventListener
            public /* synthetic */ void onDrmSessionAcquired(int i, MediaSource.MediaPeriodId mediaPeriodId) {
            }

            @Override // com.google.android.exoplayer2.drm.DrmSessionEventListener
            public void onDrmSessionManagerError(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, Exception exc) {
                OfflineLicenseHelper.this.drmListenerConditionVariable.open();
            }

            @Override // com.google.android.exoplayer2.drm.DrmSessionEventListener
            public /* synthetic */ void onDrmSessionAcquired(int i, MediaSource.MediaPeriodId mediaPeriodId, int i2) {
            }

            @Override // com.google.android.exoplayer2.drm.DrmSessionEventListener
            public /* synthetic */ void onDrmSessionReleased(int i, MediaSource.MediaPeriodId mediaPeriodId) {
            }
        });
    }
}
