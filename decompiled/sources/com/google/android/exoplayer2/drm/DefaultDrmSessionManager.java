package com.google.android.exoplayer2.drm;

import android.annotation.SuppressLint;
import android.media.ResourceBusyException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.analytics.PlayerId;
import com.google.android.exoplayer2.drm.DefaultDrmSession;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.ExoMediaDrm;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@RequiresApi(18)
public class DefaultDrmSessionManager implements DrmSessionManager {
    public static final long DEFAULT_SESSION_KEEPALIVE_MS = 300000;
    public static final int INITIAL_DRM_REQUEST_RETRY_COUNT = 3;
    public static final int MODE_DOWNLOAD = 2;
    public static final int MODE_PLAYBACK = 0;
    public static final int MODE_QUERY = 1;
    public static final int MODE_RELEASE = 3;
    public static final String PLAYREADY_CUSTOM_DATA_KEY = "PRCustomData";
    public static final String TAG = "DefaultDrmSessionMgr";
    public final MediaDrmCallback callback;

    @Nullable
    public ExoMediaDrm exoMediaDrm;
    public final ExoMediaDrm.Provider exoMediaDrmProvider;
    public final Set<DefaultDrmSession> keepaliveSessions;
    public final HashMap<String, String> keyRequestParameters;
    public final LoadErrorHandlingPolicy loadErrorHandlingPolicy;

    @Nullable
    public volatile MediaDrmHandler mediaDrmHandler;
    public int mode;
    public final boolean multiSession;

    @Nullable
    public DefaultDrmSession noMultiSessionDrmSession;

    @Nullable
    public byte[] offlineLicenseKeySetId;

    @Nullable
    public DefaultDrmSession placeholderDrmSession;
    public final boolean playClearSamplesWithoutKeys;
    public Handler playbackHandler;
    public Looper playbackLooper;
    public PlayerId playerId;
    public final Set<PreacquiredSessionReference> preacquiredSessionReferences;
    public int prepareCallsCount;
    public final ProvisioningManagerImpl provisioningManagerImpl;
    public final ReferenceCountListenerImpl referenceCountListener;
    public final long sessionKeepaliveMs;
    public final List<DefaultDrmSession> sessions;
    public final int[] useDrmSessionsForClearContentTrackTypes;
    public final UUID uuid;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public boolean multiSession;
        public boolean playClearSamplesWithoutKeys;
        public final HashMap<String, String> keyRequestParameters = new HashMap<>();
        public UUID uuid = C.WIDEVINE_UUID;
        public ExoMediaDrm.Provider exoMediaDrmProvider = FrameworkMediaDrm.DEFAULT_PROVIDER;
        public LoadErrorHandlingPolicy loadErrorHandlingPolicy = new DefaultLoadErrorHandlingPolicy(-1);
        public int[] useDrmSessionsForClearContentTrackTypes = new int[0];
        public long sessionKeepaliveMs = 300000;

        public DefaultDrmSessionManager build(MediaDrmCallback mediaDrmCallback) {
            return new DefaultDrmSessionManager(this.uuid, this.exoMediaDrmProvider, mediaDrmCallback, this.keyRequestParameters, this.multiSession, this.useDrmSessionsForClearContentTrackTypes, this.playClearSamplesWithoutKeys, this.loadErrorHandlingPolicy, this.sessionKeepaliveMs);
        }

        @CanIgnoreReturnValue
        public Builder setKeyRequestParameters(@Nullable Map<String, String> map) {
            this.keyRequestParameters.clear();
            if (map != null) {
                this.keyRequestParameters.putAll(map);
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
            loadErrorHandlingPolicy.getClass();
            this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMultiSession(boolean z) {
            this.multiSession = z;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setPlayClearSamplesWithoutKeys(boolean z) {
            this.playClearSamplesWithoutKeys = z;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setSessionKeepaliveMs(long j) {
            Assertions.checkArgument(j > 0 || j == -9223372036854775807L);
            this.sessionKeepaliveMs = j;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setUseDrmSessionsForClearContent(int... iArr) {
            for (int i : iArr) {
                boolean z = true;
                if (i != 2 && i != 1) {
                    z = false;
                }
                Assertions.checkArgument(z);
            }
            this.useDrmSessionsForClearContentTrackTypes = (int[]) iArr.clone();
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setUuidAndExoMediaDrmProvider(UUID uuid, ExoMediaDrm.Provider provider) {
            uuid.getClass();
            this.uuid = uuid;
            provider.getClass();
            this.exoMediaDrmProvider = provider;
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class MediaDrmEventListener implements ExoMediaDrm.OnEventListener {
        public MediaDrmEventListener() {
        }

        @Override // com.google.android.exoplayer2.drm.ExoMediaDrm.OnEventListener
        public void onEvent(ExoMediaDrm exoMediaDrm, @Nullable byte[] bArr, int i, int i2, @Nullable byte[] bArr2) {
            MediaDrmHandler mediaDrmHandler = DefaultDrmSessionManager.this.mediaDrmHandler;
            mediaDrmHandler.getClass();
            mediaDrmHandler.obtainMessage(i, bArr).sendToTarget();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SuppressLint({"HandlerLeak"})
    public class MediaDrmHandler extends Handler {
        public MediaDrmHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            byte[] bArr = (byte[]) message.obj;
            if (bArr == null) {
                return;
            }
            for (DefaultDrmSession defaultDrmSession : DefaultDrmSessionManager.this.sessions) {
                if (defaultDrmSession.hasSessionId(bArr)) {
                    defaultDrmSession.onMediaDrmEvent(message.what);
                    return;
                }
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MissingSchemeDataException extends Exception {
        public MissingSchemeDataException(UUID uuid) {
            super("Media does not support uuid: " + uuid);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class PreacquiredSessionReference implements DrmSessionManager.DrmSessionReference {

        @Nullable
        public final DrmSessionEventListener.EventDispatcher eventDispatcher;
        public boolean isReleased;

        @Nullable
        public DrmSession session;

        public static void $r8$lambda$YpXXpl9LmBj2CPJf8j0JHYUxFMw(PreacquiredSessionReference preacquiredSessionReference, Format format) {
            DefaultDrmSessionManager defaultDrmSessionManager = DefaultDrmSessionManager.this;
            if (defaultDrmSessionManager.prepareCallsCount == 0 || preacquiredSessionReference.isReleased) {
                return;
            }
            Looper looper = defaultDrmSessionManager.playbackLooper;
            looper.getClass();
            preacquiredSessionReference.session = defaultDrmSessionManager.acquireSession(looper, preacquiredSessionReference.eventDispatcher, format, false);
            DefaultDrmSessionManager.this.preacquiredSessionReferences.add(preacquiredSessionReference);
        }

        public static /* synthetic */ void $r8$lambda$tU__DaGLzLY9x7h4BLJsd4gjVTk(PreacquiredSessionReference preacquiredSessionReference) {
            if (preacquiredSessionReference.isReleased) {
                return;
            }
            DrmSession drmSession = preacquiredSessionReference.session;
            if (drmSession != null) {
                drmSession.release(preacquiredSessionReference.eventDispatcher);
            }
            DefaultDrmSessionManager.this.preacquiredSessionReferences.remove(preacquiredSessionReference);
            preacquiredSessionReference.isReleased = true;
        }

        public PreacquiredSessionReference(@Nullable DrmSessionEventListener.EventDispatcher eventDispatcher) {
            this.eventDispatcher = eventDispatcher;
        }

        public void acquire(final Format format) {
            Handler handler = DefaultDrmSessionManager.this.playbackHandler;
            handler.getClass();
            handler.post(new Runnable() { // from class: com.google.android.exoplayer2.drm.DefaultDrmSessionManager$PreacquiredSessionReference$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultDrmSessionManager.PreacquiredSessionReference.$r8$lambda$YpXXpl9LmBj2CPJf8j0JHYUxFMw(this.f$0, format);
                }
            });
        }

        @Override // com.google.android.exoplayer2.drm.DrmSessionManager.DrmSessionReference
        public void release() {
            Handler handler = DefaultDrmSessionManager.this.playbackHandler;
            handler.getClass();
            Util.postOrRun(handler, new Runnable() { // from class: com.google.android.exoplayer2.drm.DefaultDrmSessionManager$PreacquiredSessionReference$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultDrmSessionManager.PreacquiredSessionReference.$r8$lambda$tU__DaGLzLY9x7h4BLJsd4gjVTk(this.f$0);
                }
            });
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class ProvisioningManagerImpl implements DefaultDrmSession.ProvisioningManager {

        @Nullable
        public DefaultDrmSession provisioningSession;
        public final Set<DefaultDrmSession> sessionsAwaitingProvisioning = new HashSet();

        public ProvisioningManagerImpl() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.exoplayer2.drm.DefaultDrmSession.ProvisioningManager
        public void onProvisionCompleted() {
            this.provisioningSession = null;
            ImmutableList immutableListCopyOf = ImmutableList.copyOf((Collection) this.sessionsAwaitingProvisioning);
            this.sessionsAwaitingProvisioning.clear();
            UnmodifiableIterator it = immutableListCopyOf.iterator();
            while (it.hasNext()) {
                ((DefaultDrmSession) it.next()).onProvisionCompleted();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.exoplayer2.drm.DefaultDrmSession.ProvisioningManager
        public void onProvisionError(Exception exc, boolean z) {
            this.provisioningSession = null;
            ImmutableList immutableListCopyOf = ImmutableList.copyOf((Collection) this.sessionsAwaitingProvisioning);
            this.sessionsAwaitingProvisioning.clear();
            UnmodifiableIterator it = immutableListCopyOf.iterator();
            while (it.hasNext()) {
                ((DefaultDrmSession) it.next()).onProvisionError(exc, z);
            }
        }

        public void onSessionFullyReleased(DefaultDrmSession defaultDrmSession) {
            this.sessionsAwaitingProvisioning.remove(defaultDrmSession);
            if (this.provisioningSession == defaultDrmSession) {
                this.provisioningSession = null;
                if (this.sessionsAwaitingProvisioning.isEmpty()) {
                    return;
                }
                DefaultDrmSession next = this.sessionsAwaitingProvisioning.iterator().next();
                this.provisioningSession = next;
                next.provision();
            }
        }

        @Override // com.google.android.exoplayer2.drm.DefaultDrmSession.ProvisioningManager
        public void provisionRequired(DefaultDrmSession defaultDrmSession) {
            this.sessionsAwaitingProvisioning.add(defaultDrmSession);
            if (this.provisioningSession != null) {
                return;
            }
            this.provisioningSession = defaultDrmSession;
            defaultDrmSession.provision();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class ReferenceCountListenerImpl implements DefaultDrmSession.ReferenceCountListener {
        public ReferenceCountListenerImpl() {
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x0032  */
        @Override // com.google.android.exoplayer2.drm.DefaultDrmSession.ReferenceCountListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onReferenceCountDecremented(final com.google.android.exoplayer2.drm.DefaultDrmSession r7, int r8) {
            /*
                r6 = this;
                r0 = 1
                r1 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
                if (r8 != r0) goto L32
                com.google.android.exoplayer2.drm.DefaultDrmSessionManager r0 = com.google.android.exoplayer2.drm.DefaultDrmSessionManager.this
                int r3 = r0.prepareCallsCount
                if (r3 <= 0) goto L32
                long r3 = r0.sessionKeepaliveMs
                int r5 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
                if (r5 == 0) goto L32
                java.util.Set<com.google.android.exoplayer2.drm.DefaultDrmSession> r8 = r0.keepaliveSessions
                r8.add(r7)
                com.google.android.exoplayer2.drm.DefaultDrmSessionManager r8 = com.google.android.exoplayer2.drm.DefaultDrmSessionManager.this
                android.os.Handler r8 = r8.playbackHandler
                r8.getClass()
                com.google.android.exoplayer2.drm.DefaultDrmSessionManager$ReferenceCountListenerImpl$$ExternalSyntheticLambda0 r0 = new com.google.android.exoplayer2.drm.DefaultDrmSessionManager$ReferenceCountListenerImpl$$ExternalSyntheticLambda0
                r0.<init>()
                long r1 = android.os.SystemClock.uptimeMillis()
                com.google.android.exoplayer2.drm.DefaultDrmSessionManager r3 = com.google.android.exoplayer2.drm.DefaultDrmSessionManager.this
                long r3 = r3.sessionKeepaliveMs
                long r1 = r1 + r3
                r8.postAtTime(r0, r7, r1)
                goto L66
            L32:
                if (r8 != 0) goto L66
                com.google.android.exoplayer2.drm.DefaultDrmSessionManager r8 = com.google.android.exoplayer2.drm.DefaultDrmSessionManager.this
                java.util.List<com.google.android.exoplayer2.drm.DefaultDrmSession> r8 = r8.sessions
                r8.remove(r7)
                com.google.android.exoplayer2.drm.DefaultDrmSessionManager r8 = com.google.android.exoplayer2.drm.DefaultDrmSessionManager.this
                com.google.android.exoplayer2.drm.DefaultDrmSession r0 = r8.placeholderDrmSession
                r3 = 0
                if (r0 != r7) goto L44
                r8.placeholderDrmSession = r3
            L44:
                com.google.android.exoplayer2.drm.DefaultDrmSession r0 = r8.noMultiSessionDrmSession
                if (r0 != r7) goto L4a
                r8.noMultiSessionDrmSession = r3
            L4a:
                com.google.android.exoplayer2.drm.DefaultDrmSessionManager$ProvisioningManagerImpl r8 = r8.provisioningManagerImpl
                r8.onSessionFullyReleased(r7)
                com.google.android.exoplayer2.drm.DefaultDrmSessionManager r8 = com.google.android.exoplayer2.drm.DefaultDrmSessionManager.this
                long r3 = r8.sessionKeepaliveMs
                int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
                if (r0 == 0) goto L66
                android.os.Handler r8 = r8.playbackHandler
                r8.getClass()
                r8.removeCallbacksAndMessages(r7)
                com.google.android.exoplayer2.drm.DefaultDrmSessionManager r8 = com.google.android.exoplayer2.drm.DefaultDrmSessionManager.this
                java.util.Set<com.google.android.exoplayer2.drm.DefaultDrmSession> r8 = r8.keepaliveSessions
                r8.remove(r7)
            L66:
                com.google.android.exoplayer2.drm.DefaultDrmSessionManager r7 = com.google.android.exoplayer2.drm.DefaultDrmSessionManager.this
                r7.maybeReleaseMediaDrm()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.drm.DefaultDrmSessionManager.ReferenceCountListenerImpl.onReferenceCountDecremented(com.google.android.exoplayer2.drm.DefaultDrmSession, int):void");
        }

        @Override // com.google.android.exoplayer2.drm.DefaultDrmSession.ReferenceCountListener
        public void onReferenceCountIncremented(DefaultDrmSession defaultDrmSession, int i) {
            DefaultDrmSessionManager defaultDrmSessionManager = DefaultDrmSessionManager.this;
            if (defaultDrmSessionManager.sessionKeepaliveMs != -9223372036854775807L) {
                defaultDrmSessionManager.keepaliveSessions.remove(defaultDrmSession);
                Handler handler = DefaultDrmSessionManager.this.playbackHandler;
                handler.getClass();
                handler.removeCallbacksAndMessages(defaultDrmSession);
            }
        }
    }

    public static boolean acquisitionFailedIndicatingResourceShortage(DrmSession drmSession) {
        if (drmSession.getState() != 1) {
            return false;
        }
        if (Util.SDK_INT >= 19) {
            DrmSession.DrmSessionException error = drmSession.getError();
            error.getClass();
            if (!(error.getCause() instanceof ResourceBusyException)) {
                return false;
            }
        }
        return true;
    }

    public static List<DrmInitData.SchemeData> getSchemeDatas(DrmInitData drmInitData, UUID uuid, boolean z) {
        ArrayList arrayList = new ArrayList(drmInitData.schemeDataCount);
        for (int i = 0; i < drmInitData.schemeDataCount; i++) {
            DrmInitData.SchemeData schemeData = drmInitData.schemeDatas[i];
            if ((schemeData.matches(uuid) || (C.CLEARKEY_UUID.equals(uuid) && schemeData.matches(C.COMMON_PSSH_UUID))) && (schemeData.data != null || z)) {
                arrayList.add(schemeData);
            }
        }
        return arrayList;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSessionManager
    @Nullable
    public DrmSession acquireSession(@Nullable DrmSessionEventListener.EventDispatcher eventDispatcher, Format format) {
        verifyPlaybackThread(false);
        Assertions.checkState(this.prepareCallsCount > 0);
        Assertions.checkStateNotNull(this.playbackLooper);
        return acquireSession(this.playbackLooper, eventDispatcher, format, true);
    }

    public final boolean canAcquireSession(DrmInitData drmInitData) {
        if (this.offlineLicenseKeySetId != null) {
            return true;
        }
        if (((ArrayList) getSchemeDatas(drmInitData, this.uuid, true)).isEmpty()) {
            if (drmInitData.schemeDataCount != 1 || !drmInitData.schemeDatas[0].matches(C.COMMON_PSSH_UUID)) {
                return false;
            }
            Log.w("DefaultDrmSessionMgr", "DrmInitData only contains common PSSH SchemeData. Assuming support for: " + this.uuid);
        }
        String str = drmInitData.schemeType;
        if (str == null || "cenc".equals(str)) {
            return true;
        }
        return "cbcs".equals(str) ? Util.SDK_INT >= 25 : ("cbc1".equals(str) || "cens".equals(str)) ? false : true;
    }

    public final DefaultDrmSession createAndAcquireSession(@Nullable List<DrmInitData.SchemeData> list, boolean z, @Nullable DrmSessionEventListener.EventDispatcher eventDispatcher) {
        this.exoMediaDrm.getClass();
        boolean z2 = this.playClearSamplesWithoutKeys | z;
        UUID uuid = this.uuid;
        ExoMediaDrm exoMediaDrm = this.exoMediaDrm;
        ProvisioningManagerImpl provisioningManagerImpl = this.provisioningManagerImpl;
        ReferenceCountListenerImpl referenceCountListenerImpl = this.referenceCountListener;
        int i = this.mode;
        byte[] bArr = this.offlineLicenseKeySetId;
        HashMap<String, String> map = this.keyRequestParameters;
        MediaDrmCallback mediaDrmCallback = this.callback;
        Looper looper = this.playbackLooper;
        looper.getClass();
        LoadErrorHandlingPolicy loadErrorHandlingPolicy = this.loadErrorHandlingPolicy;
        PlayerId playerId = this.playerId;
        playerId.getClass();
        DefaultDrmSession defaultDrmSession = new DefaultDrmSession(uuid, exoMediaDrm, provisioningManagerImpl, referenceCountListenerImpl, list, i, z2, z, bArr, map, mediaDrmCallback, looper, loadErrorHandlingPolicy, playerId);
        defaultDrmSession.acquire(eventDispatcher);
        if (this.sessionKeepaliveMs != -9223372036854775807L) {
            defaultDrmSession.acquire(null);
        }
        return defaultDrmSession;
    }

    public final DefaultDrmSession createAndAcquireSessionWithRetry(@Nullable List<DrmInitData.SchemeData> list, boolean z, @Nullable DrmSessionEventListener.EventDispatcher eventDispatcher, boolean z2) {
        DefaultDrmSession defaultDrmSessionCreateAndAcquireSession = createAndAcquireSession(list, z, eventDispatcher);
        if (acquisitionFailedIndicatingResourceShortage(defaultDrmSessionCreateAndAcquireSession) && !this.keepaliveSessions.isEmpty()) {
            releaseAllKeepaliveSessions();
            undoAcquisition(defaultDrmSessionCreateAndAcquireSession, eventDispatcher);
            defaultDrmSessionCreateAndAcquireSession = createAndAcquireSession(list, z, eventDispatcher);
        }
        if (!acquisitionFailedIndicatingResourceShortage(defaultDrmSessionCreateAndAcquireSession) || !z2 || this.preacquiredSessionReferences.isEmpty()) {
            return defaultDrmSessionCreateAndAcquireSession;
        }
        releaseAllPreacquiredSessions();
        if (!this.keepaliveSessions.isEmpty()) {
            releaseAllKeepaliveSessions();
        }
        undoAcquisition(defaultDrmSessionCreateAndAcquireSession, eventDispatcher);
        return createAndAcquireSession(list, z, eventDispatcher);
    }

    @Override // com.google.android.exoplayer2.drm.DrmSessionManager
    public int getCryptoType(Format format) {
        verifyPlaybackThread(false);
        ExoMediaDrm exoMediaDrm = this.exoMediaDrm;
        exoMediaDrm.getClass();
        int cryptoType = exoMediaDrm.getCryptoType();
        DrmInitData drmInitData = format.drmInitData;
        if (drmInitData == null) {
            if (Util.linearSearch(this.useDrmSessionsForClearContentTrackTypes, MimeTypes.getTrackType(format.sampleMimeType)) == -1) {
                return 0;
            }
        } else if (!canAcquireSession(drmInitData)) {
            return 1;
        }
        return cryptoType;
    }

    @EnsuresNonNull({"this.playbackLooper", "this.playbackHandler"})
    public final synchronized void initPlaybackLooper(Looper looper) {
        try {
            Looper looper2 = this.playbackLooper;
            if (looper2 == null) {
                this.playbackLooper = looper;
                this.playbackHandler = new Handler(looper);
            } else {
                Assertions.checkState(looper2 == looper);
                this.playbackHandler.getClass();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Nullable
    public final DrmSession maybeAcquirePlaceholderSession(int i, boolean z) {
        ExoMediaDrm exoMediaDrm = this.exoMediaDrm;
        exoMediaDrm.getClass();
        if ((exoMediaDrm.getCryptoType() == 2 && FrameworkCryptoConfig.WORKAROUND_DEVICE_NEEDS_KEYS_TO_CONFIGURE_CODEC) || Util.linearSearch(this.useDrmSessionsForClearContentTrackTypes, i) == -1 || exoMediaDrm.getCryptoType() == 1) {
            return null;
        }
        DefaultDrmSession defaultDrmSession = this.placeholderDrmSession;
        if (defaultDrmSession == null) {
            DefaultDrmSession defaultDrmSessionCreateAndAcquireSessionWithRetry = createAndAcquireSessionWithRetry(ImmutableList.of(), true, null, z);
            this.sessions.add(defaultDrmSessionCreateAndAcquireSessionWithRetry);
            this.placeholderDrmSession = defaultDrmSessionCreateAndAcquireSessionWithRetry;
        } else {
            defaultDrmSession.acquire(null);
        }
        return this.placeholderDrmSession;
    }

    public final void maybeCreateMediaDrmHandler(Looper looper) {
        if (this.mediaDrmHandler == null) {
            this.mediaDrmHandler = new MediaDrmHandler(looper);
        }
    }

    public final void maybeReleaseMediaDrm() {
        if (this.exoMediaDrm != null && this.prepareCallsCount == 0 && this.sessions.isEmpty() && this.preacquiredSessionReferences.isEmpty()) {
            ExoMediaDrm exoMediaDrm = this.exoMediaDrm;
            exoMediaDrm.getClass();
            exoMediaDrm.release();
            this.exoMediaDrm = null;
        }
    }

    @Override // com.google.android.exoplayer2.drm.DrmSessionManager
    public DrmSessionManager.DrmSessionReference preacquireSession(@Nullable DrmSessionEventListener.EventDispatcher eventDispatcher, Format format) {
        Assertions.checkState(this.prepareCallsCount > 0);
        Assertions.checkStateNotNull(this.playbackLooper);
        PreacquiredSessionReference preacquiredSessionReference = new PreacquiredSessionReference(eventDispatcher);
        preacquiredSessionReference.acquire(format);
        return preacquiredSessionReference;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSessionManager
    public final void prepare() {
        verifyPlaybackThread(true);
        int i = this.prepareCallsCount;
        this.prepareCallsCount = i + 1;
        if (i != 0) {
            return;
        }
        if (this.exoMediaDrm == null) {
            ExoMediaDrm exoMediaDrmAcquireExoMediaDrm = this.exoMediaDrmProvider.acquireExoMediaDrm(this.uuid);
            this.exoMediaDrm = exoMediaDrmAcquireExoMediaDrm;
            exoMediaDrmAcquireExoMediaDrm.setOnEventListener(new MediaDrmEventListener());
        } else if (this.sessionKeepaliveMs != -9223372036854775807L) {
            for (int i2 = 0; i2 < this.sessions.size(); i2++) {
                this.sessions.get(i2).acquire(null);
            }
        }
    }

    @Override // com.google.android.exoplayer2.drm.DrmSessionManager
    public final void release() {
        verifyPlaybackThread(true);
        int i = this.prepareCallsCount - 1;
        this.prepareCallsCount = i;
        if (i != 0) {
            return;
        }
        if (this.sessionKeepaliveMs != -9223372036854775807L) {
            ArrayList arrayList = new ArrayList(this.sessions);
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                ((DefaultDrmSession) arrayList.get(i2)).release(null);
            }
        }
        releaseAllPreacquiredSessions();
        maybeReleaseMediaDrm();
    }

    public final void releaseAllKeepaliveSessions() {
        UnmodifiableIterator it = ImmutableSet.copyOf((Collection) this.keepaliveSessions).iterator();
        while (it.hasNext()) {
            ((DrmSession) it.next()).release(null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void releaseAllPreacquiredSessions() {
        UnmodifiableIterator it = ImmutableSet.copyOf((Collection) this.preacquiredSessionReferences).iterator();
        while (it.hasNext()) {
            ((PreacquiredSessionReference) it.next()).release();
        }
    }

    public void setMode(int i, @Nullable byte[] bArr) {
        Assertions.checkState(this.sessions.isEmpty());
        if (i == 1 || i == 3) {
            bArr.getClass();
        }
        this.mode = i;
        this.offlineLicenseKeySetId = bArr;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSessionManager
    public void setPlayer(Looper looper, PlayerId playerId) {
        initPlaybackLooper(looper);
        this.playerId = playerId;
    }

    public final void undoAcquisition(DrmSession drmSession, @Nullable DrmSessionEventListener.EventDispatcher eventDispatcher) {
        drmSession.release(eventDispatcher);
        if (this.sessionKeepaliveMs != -9223372036854775807L) {
            drmSession.release(null);
        }
    }

    public final void verifyPlaybackThread(boolean z) {
        if (z && this.playbackLooper == null) {
            Log.w("DefaultDrmSessionMgr", "DefaultDrmSessionManager accessed before setPlayer(), possibly on the wrong thread.", new IllegalStateException());
            return;
        }
        Thread threadCurrentThread = Thread.currentThread();
        Looper looper = this.playbackLooper;
        looper.getClass();
        if (threadCurrentThread != looper.getThread()) {
            Log.w("DefaultDrmSessionMgr", "DefaultDrmSessionManager accessed on the wrong thread.\nCurrent thread: " + Thread.currentThread().getName() + "\nExpected thread: " + this.playbackLooper.getThread().getName(), new IllegalStateException());
        }
    }

    @Deprecated
    public DefaultDrmSessionManager(UUID uuid, ExoMediaDrm exoMediaDrm, MediaDrmCallback mediaDrmCallback, @Nullable HashMap<String, String> map) {
        this(uuid, exoMediaDrm, mediaDrmCallback, map == null ? new HashMap<>() : map, false, 3);
    }

    @Deprecated
    public DefaultDrmSessionManager(UUID uuid, ExoMediaDrm exoMediaDrm, MediaDrmCallback mediaDrmCallback, @Nullable HashMap<String, String> map, boolean z) {
        this(uuid, exoMediaDrm, mediaDrmCallback, map == null ? new HashMap<>() : map, z, 3);
    }

    @Nullable
    public final DrmSession acquireSession(Looper looper, @Nullable DrmSessionEventListener.EventDispatcher eventDispatcher, Format format, boolean z) {
        List<DrmInitData.SchemeData> schemeDatas;
        maybeCreateMediaDrmHandler(looper);
        DrmInitData drmInitData = format.drmInitData;
        if (drmInitData == null) {
            return maybeAcquirePlaceholderSession(MimeTypes.getTrackType(format.sampleMimeType), z);
        }
        DefaultDrmSession defaultDrmSession = null;
        if (this.offlineLicenseKeySetId == null) {
            drmInitData.getClass();
            schemeDatas = getSchemeDatas(drmInitData, this.uuid, false);
            if (((ArrayList) schemeDatas).isEmpty()) {
                MissingSchemeDataException missingSchemeDataException = new MissingSchemeDataException(this.uuid);
                Log.e("DefaultDrmSessionMgr", "DRM error", missingSchemeDataException);
                if (eventDispatcher != null) {
                    eventDispatcher.drmSessionManagerError(missingSchemeDataException);
                }
                return new ErrorStateDrmSession(new DrmSession.DrmSessionException(missingSchemeDataException, 6003));
            }
        } else {
            schemeDatas = null;
        }
        if (!this.multiSession) {
            defaultDrmSession = this.noMultiSessionDrmSession;
        } else {
            Iterator<DefaultDrmSession> it = this.sessions.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DefaultDrmSession next = it.next();
                if (Util.areEqual(next.schemeDatas, schemeDatas)) {
                    defaultDrmSession = next;
                    break;
                }
            }
        }
        if (defaultDrmSession == null) {
            DefaultDrmSession defaultDrmSessionCreateAndAcquireSessionWithRetry = createAndAcquireSessionWithRetry(schemeDatas, false, eventDispatcher, z);
            if (!this.multiSession) {
                this.noMultiSessionDrmSession = defaultDrmSessionCreateAndAcquireSessionWithRetry;
            }
            this.sessions.add(defaultDrmSessionCreateAndAcquireSessionWithRetry);
            return defaultDrmSessionCreateAndAcquireSessionWithRetry;
        }
        defaultDrmSession.acquire(eventDispatcher);
        return defaultDrmSession;
    }

    @Deprecated
    public DefaultDrmSessionManager(UUID uuid, ExoMediaDrm exoMediaDrm, MediaDrmCallback mediaDrmCallback, @Nullable HashMap<String, String> map, boolean z, int i) {
        this(uuid, new ExoMediaDrm.AppManagedProvider(exoMediaDrm), mediaDrmCallback, map == null ? new HashMap<>() : map, z, new int[0], false, new DefaultLoadErrorHandlingPolicy(i), 300000L);
    }

    public DefaultDrmSessionManager(UUID uuid, ExoMediaDrm.Provider provider, MediaDrmCallback mediaDrmCallback, HashMap<String, String> map, boolean z, int[] iArr, boolean z2, LoadErrorHandlingPolicy loadErrorHandlingPolicy, long j) {
        uuid.getClass();
        Assertions.checkArgument(!C.COMMON_PSSH_UUID.equals(uuid), "Use C.CLEARKEY_UUID instead");
        this.uuid = uuid;
        this.exoMediaDrmProvider = provider;
        this.callback = mediaDrmCallback;
        this.keyRequestParameters = map;
        this.multiSession = z;
        this.useDrmSessionsForClearContentTrackTypes = iArr;
        this.playClearSamplesWithoutKeys = z2;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
        this.provisioningManagerImpl = new ProvisioningManagerImpl();
        this.referenceCountListener = new ReferenceCountListenerImpl();
        this.mode = 0;
        this.sessions = new ArrayList();
        this.preacquiredSessionReferences = Sets.newIdentityHashSet();
        this.keepaliveSessions = Sets.newIdentityHashSet();
        this.sessionKeepaliveMs = j;
    }
}
