package com.amazon.avod.mpb.media.drm;

import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import com.amazon.avod.mpb.api.core.MpbLog;
import java.util.Arrays;
import java.util.UUID;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nMediaCryptoSessionImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MediaCryptoSessionImpl.kt\ncom/amazon/avod/mpb/media/drm/MediaCryptoSessionImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,263:1\n1#2:264\n*E\n"})
public final class MediaCryptoSessionImpl implements MediaCryptoSession {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final int INIT_VECTOR_EXPECTED_SIZE = 16;
    public MediaCrypto audioMediaCrypto;
    public byte[] audioSessionId;

    @NotNull
    public AndroidDrmSystem drmSystem;
    public final boolean generateCryptoInfoForClearSamples;
    public MediaCrypto videoMediaCrypto;
    public byte[] videoSessionId;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public MediaCryptoSessionImpl(@NotNull AndroidDrmSystem drmSystem, boolean z) {
        Intrinsics.checkNotNullParameter(drmSystem, "drmSystem");
        this.drmSystem = drmSystem;
        this.generateCryptoInfoForClearSamples = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0032  */
    @Override // com.amazon.avod.mpb.media.drm.MediaCryptoSession
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.media.MediaCodec.CryptoInfo generateCryptoInfo(@org.jetbrains.annotations.NotNull com.amazon.avod.mpb.api.sample.BufferedMediaSample r19) throws com.amazon.avod.mpb.api.core.MediaPipelineBackendException {
        /*
            Method dump skipped, instruction units count: 375
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.mpb.media.drm.MediaCryptoSessionImpl.generateCryptoInfo(com.amazon.avod.mpb.api.sample.BufferedMediaSample):android.media.MediaCodec$CryptoInfo");
    }

    @Override // com.amazon.avod.mpb.media.drm.MediaCryptoSession
    @NotNull
    public MediaCrypto getMediaCrypto(boolean z) {
        if (z) {
            MediaCrypto mediaCrypto = this.audioMediaCrypto;
            if (mediaCrypto != null) {
                return mediaCrypto;
            }
            Intrinsics.throwUninitializedPropertyAccessException("audioMediaCrypto");
            throw null;
        }
        MediaCrypto mediaCrypto2 = this.videoMediaCrypto;
        if (mediaCrypto2 != null) {
            return mediaCrypto2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("videoMediaCrypto");
        throw null;
    }

    @Override // com.amazon.avod.mpb.media.drm.MediaCryptoSession
    public void initialize() throws MediaPipelineBackendException {
        AndroidDrmSystem androidDrmSystem = this.drmSystem;
        byte[] mediaDrmSessionId = androidDrmSystem.getMediaDrmSessionId(androidDrmSystem.lastOpenedIgniteSessionId);
        if (mediaDrmSessionId == null) {
            throw new MediaPipelineBackendException("No session id is present", MediaPipelineBackendResultCode.DRM_NULL_MEDIA_DRM_SESSION_ID);
        }
        if (!this.drmSystem.isSessionOpen(mediaDrmSessionId)) {
            MpbLog.errorf(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Session with ", DrmUtils.INSTANCE.toHexString(mediaDrmSessionId), " is not open, cannot initialize MediaCryptoSession"), new Object[0]);
            throw new MediaPipelineBackendException("Session is not open", MediaPipelineBackendResultCode.DRM_OPEN_SESSION_UNKNOWN_FAILURE);
        }
        this.audioSessionId = mediaDrmSessionId;
        this.videoSessionId = mediaDrmSessionId;
        MpbLog.logf("Initializing MediaCryptoSession with sessionId ".concat(DrmUtils.INSTANCE.toHexString(mediaDrmSessionId)), new Object[0]);
        try {
            UUID uuid = this.drmSystem.schemeId;
            byte[] bArr = this.audioSessionId;
            if (bArr == null) {
                Intrinsics.throwUninitializedPropertyAccessException("audioSessionId");
                throw null;
            }
            this.audioMediaCrypto = new MediaCrypto(uuid, bArr);
            UUID uuid2 = this.drmSystem.schemeId;
            byte[] bArr2 = this.videoSessionId;
            if (bArr2 != null) {
                this.videoMediaCrypto = new MediaCrypto(uuid2, bArr2);
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("videoSessionId");
                throw null;
            }
        } catch (MediaCryptoException e) {
            MpbLog.exceptionf(e, "Failed to instantiate MediaCrypto with " + this.drmSystem.schemeId + " and " + mediaDrmSessionId, new Object[0]);
            throw new MediaPipelineBackendException("Failed to instantiate MediaCrypto", MediaPipelineBackendResultCode.DRM_FAILED_TO_CREATE_MEDIA_CRYPTO, e);
        }
    }

    @NotNull
    public final Pair<int[], int[]> mergeRegions(@NotNull int[] clear, @NotNull int[] encrypted) {
        Intrinsics.checkNotNullParameter(clear, "clear");
        Intrinsics.checkNotNullParameter(encrypted, "encrypted");
        if (clear.length != encrypted.length) {
            throw new IllegalArgumentException("Arrays must be the same size");
        }
        int length = clear.length;
        if (length == 0) {
            return new Pair<>(clear, encrypted);
        }
        int i = 1;
        for (int i2 = 1; i2 < length; i2++) {
            int i3 = i - 1;
            int i4 = encrypted[i3];
            if (i4 == 0) {
                clear[i3] = clear[i3] + clear[i2];
                encrypted[i3] = encrypted[i2];
            } else {
                int i5 = clear[i2];
                if (i5 == 0) {
                    encrypted[i3] = i4 + encrypted[i2];
                } else {
                    clear[i] = i5;
                    encrypted[i] = encrypted[i2];
                    i++;
                }
            }
        }
        if (i == length) {
            return new Pair<>(clear, encrypted);
        }
        int[] iArrCopyOf = Arrays.copyOf(clear, i);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
        int[] iArrCopyOf2 = Arrays.copyOf(encrypted, i);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf2, "copyOf(...)");
        return new Pair<>(iArrCopyOf, iArrCopyOf2);
    }

    @Override // com.amazon.avod.mpb.media.drm.MediaCryptoSession
    public void release() {
        MediaCrypto mediaCrypto = this.audioMediaCrypto;
        if (mediaCrypto == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioMediaCrypto");
            throw null;
        }
        mediaCrypto.release();
        MediaCrypto mediaCrypto2 = this.videoMediaCrypto;
        if (mediaCrypto2 != null) {
            mediaCrypto2.release();
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("videoMediaCrypto");
            throw null;
        }
    }
}
