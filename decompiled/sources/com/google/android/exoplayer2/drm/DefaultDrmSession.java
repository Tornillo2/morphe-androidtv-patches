package com.google.android.exoplayer2.drm;

import android.annotation.SuppressLint;
import android.media.NotProvisionedException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Pair;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.analytics.PlayerId;
import com.google.android.exoplayer2.decoder.CryptoConfig;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.drm.ExoMediaDrm;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaLoadData;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Consumer;
import com.google.android.exoplayer2.util.CopyOnWriteMultiset;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import j$.util.DesugarCollections;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@RequiresApi(18)
public class DefaultDrmSession implements DrmSession {
    public static final int MAX_LICENSE_DURATION_TO_RENEW_SECONDS = 60;
    public static final int MSG_KEYS = 1;
    public static final int MSG_PROVISION = 0;
    public static final String TAG = "DefaultDrmSession";
    public final MediaDrmCallback callback;

    @Nullable
    public CryptoConfig cryptoConfig;

    @Nullable
    public ExoMediaDrm.KeyRequest currentKeyRequest;

    @Nullable
    public ExoMediaDrm.ProvisionRequest currentProvisionRequest;
    public final CopyOnWriteMultiset<DrmSessionEventListener.EventDispatcher> eventDispatchers;
    public final boolean isPlaceholderSession;
    public final HashMap<String, String> keyRequestParameters;

    @Nullable
    public DrmSession.DrmSessionException lastException;
    public final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    public final ExoMediaDrm mediaDrm;
    public final int mode;
    public byte[] offlineLicenseKeySetId;
    public final boolean playClearSamplesWithoutKeys;
    public final Looper playbackLooper;
    public final PlayerId playerId;
    public final ProvisioningManager provisioningManager;
    public int referenceCount;
    public final ReferenceCountListener referenceCountListener;

    @Nullable
    public RequestHandler requestHandler;

    @Nullable
    public HandlerThread requestHandlerThread;
    public final ResponseHandler responseHandler;

    @Nullable
    public final List<DrmInitData.SchemeData> schemeDatas;

    @Nullable
    public byte[] sessionId;
    public int state;
    public final UUID uuid;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface ProvisioningManager {
        void onProvisionCompleted();

        void onProvisionError(Exception exc, boolean z);

        void provisionRequired(DefaultDrmSession defaultDrmSession);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface ReferenceCountListener {
        void onReferenceCountDecremented(DefaultDrmSession defaultDrmSession, int i);

        void onReferenceCountIncremented(DefaultDrmSession defaultDrmSession, int i);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SuppressLint({"HandlerLeak"})
    public class RequestHandler extends Handler {

        @GuardedBy("this")
        public boolean isReleased;

        public RequestHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Object objExecuteProvisionRequest;
            RequestTask requestTask = (RequestTask) message.obj;
            try {
                int i = message.what;
                if (i == 0) {
                    DefaultDrmSession defaultDrmSession = DefaultDrmSession.this;
                    objExecuteProvisionRequest = defaultDrmSession.callback.executeProvisionRequest(defaultDrmSession.uuid, (ExoMediaDrm.ProvisionRequest) requestTask.request);
                } else {
                    if (i != 1) {
                        throw new RuntimeException();
                    }
                    DefaultDrmSession defaultDrmSession2 = DefaultDrmSession.this;
                    objExecuteProvisionRequest = defaultDrmSession2.callback.executeKeyRequest(defaultDrmSession2.uuid, (ExoMediaDrm.KeyRequest) requestTask.request);
                }
            } catch (MediaDrmCallbackException e) {
                boolean zMaybeRetryRequest = maybeRetryRequest(message, e);
                objExecuteProvisionRequest = e;
                if (zMaybeRetryRequest) {
                    return;
                }
            } catch (Exception e2) {
                Log.w("DefaultDrmSession", "Key/provisioning request produced an unexpected exception. Not retrying.", e2);
                objExecuteProvisionRequest = e2;
            }
            LoadErrorHandlingPolicy loadErrorHandlingPolicy = DefaultDrmSession.this.loadErrorHandlingPolicy;
            long j = requestTask.taskId;
            loadErrorHandlingPolicy.getClass();
            synchronized (this) {
                try {
                    if (!this.isReleased) {
                        DefaultDrmSession.this.responseHandler.obtainMessage(message.what, Pair.create(requestTask.request, objExecuteProvisionRequest)).sendToTarget();
                    }
                } finally {
                }
            }
        }

        public final boolean maybeRetryRequest(Message message, MediaDrmCallbackException mediaDrmCallbackException) {
            RequestTask requestTask = (RequestTask) message.obj;
            if (requestTask.allowRetry) {
                int i = requestTask.errorCount + 1;
                requestTask.errorCount = i;
                if (i <= DefaultDrmSession.this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(3)) {
                    long retryDelayMsFor = DefaultDrmSession.this.loadErrorHandlingPolicy.getRetryDelayMsFor(new LoadErrorHandlingPolicy.LoadErrorInfo(new LoadEventInfo(requestTask.taskId, mediaDrmCallbackException.dataSpec, mediaDrmCallbackException.uriAfterRedirects, mediaDrmCallbackException.responseHeaders, SystemClock.elapsedRealtime(), SystemClock.elapsedRealtime() - requestTask.startTimeMs, mediaDrmCallbackException.bytesLoaded), new MediaLoadData(3), mediaDrmCallbackException.getCause() instanceof IOException ? (IOException) mediaDrmCallbackException.getCause() : new UnexpectedDrmSessionException(mediaDrmCallbackException.getCause()), requestTask.errorCount));
                    if (retryDelayMsFor == -9223372036854775807L) {
                        return false;
                    }
                    synchronized (this) {
                        try {
                            if (this.isReleased) {
                                return false;
                            }
                            sendMessageDelayed(Message.obtain(message), retryDelayMsFor);
                            return true;
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
            }
            return false;
        }

        public void post(int i, Object obj, boolean z) {
            obtainMessage(i, new RequestTask(LoadEventInfo.getNewId(), z, SystemClock.elapsedRealtime(), obj)).sendToTarget();
        }

        public synchronized void release() {
            removeCallbacksAndMessages(null);
            this.isReleased = true;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class RequestTask {
        public final boolean allowRetry;
        public int errorCount;
        public final Object request;
        public final long startTimeMs;
        public final long taskId;

        public RequestTask(long j, boolean z, long j2, Object obj) {
            this.taskId = j;
            this.allowRetry = z;
            this.startTimeMs = j2;
            this.request = obj;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SuppressLint({"HandlerLeak"})
    public class ResponseHandler extends Handler {
        public ResponseHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Pair pair = (Pair) message.obj;
            Object obj = pair.first;
            Object obj2 = pair.second;
            int i = message.what;
            if (i == 0) {
                DefaultDrmSession.this.onProvisionResponse(obj, obj2);
            } else {
                if (i != 1) {
                    return;
                }
                DefaultDrmSession.this.onKeyResponse(obj, obj2);
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class UnexpectedDrmSessionException extends IOException {
        public UnexpectedDrmSessionException(@Nullable Throwable th) {
            super(th);
        }
    }

    public DefaultDrmSession(UUID uuid, ExoMediaDrm exoMediaDrm, ProvisioningManager provisioningManager, ReferenceCountListener referenceCountListener, @Nullable List<DrmInitData.SchemeData> list, int i, boolean z, boolean z2, @Nullable byte[] bArr, HashMap<String, String> map, MediaDrmCallback mediaDrmCallback, Looper looper, LoadErrorHandlingPolicy loadErrorHandlingPolicy, PlayerId playerId) {
        if (i == 1 || i == 3) {
            bArr.getClass();
        }
        this.uuid = uuid;
        this.provisioningManager = provisioningManager;
        this.referenceCountListener = referenceCountListener;
        this.mediaDrm = exoMediaDrm;
        this.mode = i;
        this.playClearSamplesWithoutKeys = z;
        this.isPlaceholderSession = z2;
        if (bArr != null) {
            this.offlineLicenseKeySetId = bArr;
            this.schemeDatas = null;
        } else {
            list.getClass();
            this.schemeDatas = DesugarCollections.unmodifiableList(list);
        }
        this.keyRequestParameters = map;
        this.callback = mediaDrmCallback;
        this.eventDispatchers = new CopyOnWriteMultiset<>();
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
        this.playerId = playerId;
        this.state = 2;
        this.playbackLooper = looper;
        this.responseHandler = new ResponseHandler(looper);
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public void acquire(@Nullable DrmSessionEventListener.EventDispatcher eventDispatcher) {
        verifyPlaybackThread();
        if (this.referenceCount < 0) {
            Log.e("DefaultDrmSession", "Session reference count less than zero: " + this.referenceCount);
            this.referenceCount = 0;
        }
        if (eventDispatcher != null) {
            this.eventDispatchers.add(eventDispatcher);
        }
        int i = this.referenceCount + 1;
        this.referenceCount = i;
        if (i == 1) {
            Assertions.checkState(this.state == 2);
            HandlerThread handlerThread = new HandlerThread("ExoPlayer:DrmRequestHandler");
            this.requestHandlerThread = handlerThread;
            handlerThread.start();
            this.requestHandler = new RequestHandler(this.requestHandlerThread.getLooper());
            if (openInternal()) {
                doLicense(true);
            }
        } else if (eventDispatcher != null && isOpen() && this.eventDispatchers.count(eventDispatcher) == 1) {
            eventDispatcher.drmSessionAcquired(this.state);
        }
        this.referenceCountListener.onReferenceCountIncremented(this, this.referenceCount);
    }

    public final void dispatchEvent(Consumer<DrmSessionEventListener.EventDispatcher> consumer) {
        Iterator<DrmSessionEventListener.EventDispatcher> it = this.eventDispatchers.elementSet().iterator();
        while (it.hasNext()) {
            consumer.accept(it.next());
        }
    }

    @RequiresNonNull({"sessionId"})
    public final void doLicense(boolean z) {
        if (this.isPlaceholderSession) {
            return;
        }
        byte[] bArr = this.sessionId;
        Util.castNonNull(bArr);
        byte[] bArr2 = bArr;
        int i = this.mode;
        if (i != 0 && i != 1) {
            if (i == 2) {
                if (this.offlineLicenseKeySetId == null || restoreKeys()) {
                    postKeyRequest(bArr2, 2, z);
                    return;
                }
                return;
            }
            if (i != 3) {
                return;
            }
            this.offlineLicenseKeySetId.getClass();
            this.sessionId.getClass();
            postKeyRequest(this.offlineLicenseKeySetId, 3, z);
            return;
        }
        if (this.offlineLicenseKeySetId == null) {
            postKeyRequest(bArr2, 1, z);
            return;
        }
        if (this.state == 4 || restoreKeys()) {
            long licenseDurationRemainingSec = getLicenseDurationRemainingSec();
            if (this.mode == 0 && licenseDurationRemainingSec <= 60) {
                Log.d("DefaultDrmSession", "Offline license has expired or will expire soon. Remaining seconds: " + licenseDurationRemainingSec);
                postKeyRequest(bArr2, 2, z);
                return;
            }
            if (licenseDurationRemainingSec <= 0) {
                onError(new KeysExpiredException(), 2);
            } else {
                this.state = 4;
                dispatchEvent(new DefaultDrmSession$$ExternalSyntheticLambda1());
            }
        }
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    @Nullable
    public final CryptoConfig getCryptoConfig() {
        verifyPlaybackThread();
        return this.cryptoConfig;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    @Nullable
    public final DrmSession.DrmSessionException getError() {
        verifyPlaybackThread();
        if (this.state == 1) {
            return this.lastException;
        }
        return null;
    }

    public final long getLicenseDurationRemainingSec() {
        if (!C.WIDEVINE_UUID.equals(this.uuid)) {
            return Long.MAX_VALUE;
        }
        Pair<Long, Long> licenseDurationRemainingSec = WidevineUtil.getLicenseDurationRemainingSec(this);
        licenseDurationRemainingSec.getClass();
        return Math.min(((Long) licenseDurationRemainingSec.first).longValue(), ((Long) licenseDurationRemainingSec.second).longValue());
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    @Nullable
    public byte[] getOfflineLicenseKeySetId() {
        verifyPlaybackThread();
        return this.offlineLicenseKeySetId;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public final UUID getSchemeUuid() {
        verifyPlaybackThread();
        return this.uuid;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public final int getState() {
        verifyPlaybackThread();
        return this.state;
    }

    public boolean hasSessionId(byte[] bArr) {
        verifyPlaybackThread();
        return Arrays.equals(this.sessionId, bArr);
    }

    @EnsuresNonNullIf(expression = {"sessionId"}, result = true)
    public final boolean isOpen() {
        int i = this.state;
        return i == 3 || i == 4;
    }

    public final void onError(final Exception exc, int i) {
        this.lastException = new DrmSession.DrmSessionException(exc, DrmUtil.getErrorCodeForMediaDrmException(exc, i));
        Log.e("DefaultDrmSession", "DRM session error", exc);
        dispatchEvent(new Consumer() { // from class: com.google.android.exoplayer2.drm.DefaultDrmSession$$ExternalSyntheticLambda2
            @Override // com.google.android.exoplayer2.util.Consumer
            public final void accept(Object obj) {
                ((DrmSessionEventListener.EventDispatcher) obj).drmSessionManagerError(exc);
            }
        });
        if (this.state != 4) {
            this.state = 1;
        }
    }

    public final void onKeyResponse(Object obj, Object obj2) {
        if (obj == this.currentKeyRequest && isOpen()) {
            this.currentKeyRequest = null;
            if (obj2 instanceof Exception) {
                onKeysError((Exception) obj2, false);
                return;
            }
            try {
                byte[] bArr = (byte[]) obj2;
                if (this.mode == 3) {
                    ExoMediaDrm exoMediaDrm = this.mediaDrm;
                    byte[] bArr2 = this.offlineLicenseKeySetId;
                    Util.castNonNull(bArr2);
                    exoMediaDrm.provideKeyResponse(bArr2, bArr);
                    dispatchEvent(new DefaultDrmSession$$ExternalSyntheticLambda3());
                    return;
                }
                byte[] bArrProvideKeyResponse = this.mediaDrm.provideKeyResponse(this.sessionId, bArr);
                int i = this.mode;
                if ((i == 2 || (i == 0 && this.offlineLicenseKeySetId != null)) && bArrProvideKeyResponse != null && bArrProvideKeyResponse.length != 0) {
                    this.offlineLicenseKeySetId = bArrProvideKeyResponse;
                }
                this.state = 4;
                dispatchEvent(new DefaultDrmSession$$ExternalSyntheticLambda4());
            } catch (Exception e) {
                onKeysError(e, true);
            }
        }
    }

    public final void onKeysError(Exception exc, boolean z) {
        if (exc instanceof NotProvisionedException) {
            this.provisioningManager.provisionRequired(this);
        } else {
            onError(exc, z ? 1 : 2);
        }
    }

    public final void onKeysRequired() {
        if (this.mode == 0 && this.state == 4) {
            Util.castNonNull(this.sessionId);
            doLicense(false);
        }
    }

    public void onMediaDrmEvent(int i) {
        if (i != 2) {
            return;
        }
        onKeysRequired();
    }

    public void onProvisionCompleted() {
        if (openInternal()) {
            doLicense(true);
        }
    }

    public void onProvisionError(Exception exc, boolean z) {
        onError(exc, z ? 1 : 3);
    }

    public final void onProvisionResponse(Object obj, Object obj2) {
        if (obj == this.currentProvisionRequest) {
            if (this.state == 2 || isOpen()) {
                this.currentProvisionRequest = null;
                if (obj2 instanceof Exception) {
                    this.provisioningManager.onProvisionError((Exception) obj2, false);
                    return;
                }
                try {
                    this.mediaDrm.provideProvisionResponse((byte[]) obj2);
                    this.provisioningManager.onProvisionCompleted();
                } catch (Exception e) {
                    this.provisioningManager.onProvisionError(e, true);
                }
            }
        }
    }

    @EnsuresNonNullIf(expression = {"sessionId"}, result = true)
    public final boolean openInternal() {
        if (isOpen()) {
            return true;
        }
        try {
            byte[] bArrOpenSession = this.mediaDrm.openSession();
            this.sessionId = bArrOpenSession;
            this.mediaDrm.setPlayerIdForSession(bArrOpenSession, this.playerId);
            this.cryptoConfig = this.mediaDrm.createCryptoConfig(this.sessionId);
            final int i = 3;
            this.state = 3;
            dispatchEvent(new Consumer() { // from class: com.google.android.exoplayer2.drm.DefaultDrmSession$$ExternalSyntheticLambda0
                @Override // com.google.android.exoplayer2.util.Consumer
                public final void accept(Object obj) {
                    ((DrmSessionEventListener.EventDispatcher) obj).drmSessionAcquired(3);
                }
            });
            this.sessionId.getClass();
            return true;
        } catch (NotProvisionedException unused) {
            this.provisioningManager.provisionRequired(this);
            return false;
        } catch (Exception e) {
            onError(e, 1);
            return false;
        }
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public boolean playClearSamplesWithoutKeys() {
        verifyPlaybackThread();
        return this.playClearSamplesWithoutKeys;
    }

    public final void postKeyRequest(byte[] bArr, int i, boolean z) {
        try {
            this.currentKeyRequest = this.mediaDrm.getKeyRequest(bArr, this.schemeDatas, i, this.keyRequestParameters);
            RequestHandler requestHandler = this.requestHandler;
            Util.castNonNull(requestHandler);
            ExoMediaDrm.KeyRequest keyRequest = this.currentKeyRequest;
            keyRequest.getClass();
            requestHandler.post(1, keyRequest, z);
        } catch (Exception e) {
            onKeysError(e, true);
        }
    }

    public void provision() {
        this.currentProvisionRequest = this.mediaDrm.getProvisionRequest();
        RequestHandler requestHandler = this.requestHandler;
        Util.castNonNull(requestHandler);
        ExoMediaDrm.ProvisionRequest provisionRequest = this.currentProvisionRequest;
        provisionRequest.getClass();
        requestHandler.post(0, provisionRequest, true);
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    @Nullable
    public Map<String, String> queryKeyStatus() {
        verifyPlaybackThread();
        byte[] bArr = this.sessionId;
        if (bArr == null) {
            return null;
        }
        return this.mediaDrm.queryKeyStatus(bArr);
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public void release(@Nullable DrmSessionEventListener.EventDispatcher eventDispatcher) {
        verifyPlaybackThread();
        int i = this.referenceCount;
        if (i <= 0) {
            Log.e("DefaultDrmSession", "release() called on a session that's already fully released.");
            return;
        }
        int i2 = i - 1;
        this.referenceCount = i2;
        if (i2 == 0) {
            this.state = 0;
            ResponseHandler responseHandler = this.responseHandler;
            Util.castNonNull(responseHandler);
            responseHandler.removeCallbacksAndMessages(null);
            this.requestHandler.release();
            this.requestHandler = null;
            this.requestHandlerThread.quit();
            this.requestHandlerThread = null;
            this.cryptoConfig = null;
            this.lastException = null;
            this.currentKeyRequest = null;
            this.currentProvisionRequest = null;
            byte[] bArr = this.sessionId;
            if (bArr != null) {
                this.mediaDrm.closeSession(bArr);
                this.sessionId = null;
            }
        }
        if (eventDispatcher != null) {
            this.eventDispatchers.remove(eventDispatcher);
            if (this.eventDispatchers.count(eventDispatcher) == 0) {
                eventDispatcher.drmSessionReleased();
            }
        }
        this.referenceCountListener.onReferenceCountDecremented(this, this.referenceCount);
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public boolean requiresSecureDecoder(String str) {
        verifyPlaybackThread();
        ExoMediaDrm exoMediaDrm = this.mediaDrm;
        byte[] bArr = this.sessionId;
        Assertions.checkStateNotNull(bArr);
        return exoMediaDrm.requiresSecureDecoder(bArr, str);
    }

    @RequiresNonNull({"sessionId", "offlineLicenseKeySetId"})
    public final boolean restoreKeys() {
        try {
            this.mediaDrm.restoreKeys(this.sessionId, this.offlineLicenseKeySetId);
            return true;
        } catch (Exception e) {
            onError(e, 1);
            return false;
        }
    }

    public final void verifyPlaybackThread() {
        if (Thread.currentThread() != this.playbackLooper.getThread()) {
            Log.w("DefaultDrmSession", "DefaultDrmSession accessed on the wrong thread.\nCurrent thread: " + Thread.currentThread().getName() + "\nExpected thread: " + this.playbackLooper.getThread().getName(), new IllegalStateException());
        }
    }
}
