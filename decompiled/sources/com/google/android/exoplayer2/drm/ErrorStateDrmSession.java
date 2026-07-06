package com.google.android.exoplayer2.drm;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.decoder.CryptoConfig;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ErrorStateDrmSession implements DrmSession {
    public final DrmSession.DrmSessionException error;

    public ErrorStateDrmSession(DrmSession.DrmSessionException drmSessionException) {
        drmSessionException.getClass();
        this.error = drmSessionException;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    @Nullable
    public CryptoConfig getCryptoConfig() {
        return null;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    @Nullable
    public DrmSession.DrmSessionException getError() {
        return this.error;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    @Nullable
    public byte[] getOfflineLicenseKeySetId() {
        return null;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public final UUID getSchemeUuid() {
        return C.UUID_NIL;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public int getState() {
        return 1;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public boolean playClearSamplesWithoutKeys() {
        return false;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    @Nullable
    public Map<String, String> queryKeyStatus() {
        return null;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public boolean requiresSecureDecoder(String str) {
        return false;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public void acquire(@Nullable DrmSessionEventListener.EventDispatcher eventDispatcher) {
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public void release(@Nullable DrmSessionEventListener.EventDispatcher eventDispatcher) {
    }
}
