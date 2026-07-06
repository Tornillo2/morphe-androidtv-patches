package androidx.media3.exoplayer.drm;

import android.annotation.SuppressLint;
import android.media.DeniedByServerException;
import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.media.MediaDrm;
import android.media.MediaDrmException;
import android.media.NotProvisionedException;
import android.media.UnsupportedSchemeException;
import android.media.metrics.LogSessionId;
import android.os.Handler;
import android.os.PersistableBundle;
import android.text.TextUtils;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.ExoMediaDrm;
import androidx.media3.extractor.mp4.PsshAtomUtil;
import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.google.common.base.Charsets;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(18)
public final class FrameworkMediaDrm implements ExoMediaDrm {
    public static final String CENC_SCHEME_MIME_TYPE = "cenc";

    @UnstableApi
    public static final ExoMediaDrm.Provider DEFAULT_PROVIDER = new FrameworkMediaDrm$$ExternalSyntheticLambda0();
    public static final String MOCK_LA_URL = "<LA_URL>https://x</LA_URL>";
    public static final String MOCK_LA_URL_VALUE = "https://x";
    public static final String TAG = "FrameworkMediaDrm";
    public static final int UTF_16_BYTES_PER_CHARACTER = 2;
    public final MediaDrm mediaDrm;
    public int referenceCount;
    public final UUID uuid;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(31)
    public static class Api31 {
        @DoNotInline
        public static boolean requiresSecureDecoder(MediaDrm mediaDrm, String str) {
            return mediaDrm.requiresSecureDecoder(str);
        }

        @DoNotInline
        public static void setLogSessionIdOnMediaDrmSession(MediaDrm mediaDrm, byte[] bArr, PlayerId playerId) {
            LogSessionId logSessionId = playerId.getLogSessionId();
            if (logSessionId.equals(LogSessionId.LOG_SESSION_ID_NONE)) {
                return;
            }
            MediaDrm.PlaybackComponent playbackComponent = mediaDrm.getPlaybackComponent(bArr);
            playbackComponent.getClass();
            playbackComponent.setLogSessionId(logSessionId);
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$-ofscM7m9YZH4gea4zOZQfoikU8, reason: not valid java name */
    public static /* synthetic */ void m140$r8$lambda$ofscM7m9YZH4gea4zOZQfoikU8(FrameworkMediaDrm frameworkMediaDrm, ExoMediaDrm.OnExpirationUpdateListener onExpirationUpdateListener, MediaDrm mediaDrm, byte[] bArr, long j) {
        frameworkMediaDrm.getClass();
        onExpirationUpdateListener.onExpirationUpdate(frameworkMediaDrm, bArr, j);
    }

    public static ExoMediaDrm $r8$lambda$DzWbix_Ud4a3ekWyswo40Jo03Ks(UUID uuid) {
        try {
            return newInstance(uuid);
        } catch (UnsupportedDrmException unused) {
            Log.e("FrameworkMediaDrm", "Failed to instantiate a FrameworkMediaDrm for uuid: " + uuid + ExternalFourCCMapper.CODEC_NAME_SPLITTER);
            return new DummyExoMediaDrm();
        }
    }

    public static /* synthetic */ void $r8$lambda$FTMrYBm0_nl4l9MpEYMiZMFYRQE(FrameworkMediaDrm frameworkMediaDrm, ExoMediaDrm.OnEventListener onEventListener, MediaDrm mediaDrm, byte[] bArr, int i, int i2, byte[] bArr2) {
        frameworkMediaDrm.getClass();
        onEventListener.onEvent(frameworkMediaDrm, bArr, i, i2, bArr2);
    }

    /* JADX INFO: renamed from: $r8$lambda$n5JKhJipz-ndj_Tm6-P_Wdm1cRk, reason: not valid java name */
    public static /* synthetic */ void m141$r8$lambda$n5JKhJipzndj_Tm6P_Wdm1cRk(FrameworkMediaDrm frameworkMediaDrm, ExoMediaDrm.OnKeyStatusChangeListener onKeyStatusChangeListener, MediaDrm mediaDrm, byte[] bArr, List list, boolean z) {
        frameworkMediaDrm.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            MediaDrm.KeyStatus keyStatus = (MediaDrm.KeyStatus) it.next();
            arrayList.add(new ExoMediaDrm.KeyStatus(keyStatus.getStatusCode(), keyStatus.getKeyId()));
        }
        onKeyStatusChangeListener.onKeyStatusChange(frameworkMediaDrm, bArr, arrayList, z);
    }

    public FrameworkMediaDrm(UUID uuid) throws UnsupportedSchemeException {
        uuid.getClass();
        Assertions.checkArgument(!C.COMMON_PSSH_UUID.equals(uuid), "Use C.CLEARKEY_UUID instead");
        this.uuid = uuid;
        MediaDrm mediaDrm = new MediaDrm(adjustUuid(uuid));
        this.mediaDrm = mediaDrm;
        this.referenceCount = 1;
        if (C.WIDEVINE_UUID.equals(uuid) && needsForceWidevineL3Workaround()) {
            forceWidevineL3(mediaDrm);
        }
    }

    public static byte[] addLaUrlAttributeIfMissing(byte[] bArr) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        int littleEndianInt = parsableByteArray.readLittleEndianInt();
        short littleEndianShort = parsableByteArray.readLittleEndianShort();
        short littleEndianShort2 = parsableByteArray.readLittleEndianShort();
        if (littleEndianShort != 1 || littleEndianShort2 != 1) {
            Log.i("FrameworkMediaDrm", "Unexpected record count or type. Skipping LA_URL workaround.");
            return bArr;
        }
        short littleEndianShort3 = parsableByteArray.readLittleEndianShort();
        Charset charset = Charsets.UTF_16LE;
        String string = parsableByteArray.readString(littleEndianShort3, charset);
        if (string.contains("<LA_URL>")) {
            return bArr;
        }
        int iIndexOf = string.indexOf("</DATA>");
        if (iIndexOf == -1) {
            Log.w("FrameworkMediaDrm", "Could not find the </DATA> tag. Skipping LA_URL workaround.");
        }
        String str = string.substring(0, iIndexOf) + "<LA_URL>https://x</LA_URL>" + string.substring(iIndexOf);
        int i = littleEndianInt + 52;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.putInt(i);
        byteBufferAllocate.putShort(littleEndianShort);
        byteBufferAllocate.putShort(littleEndianShort2);
        byteBufferAllocate.putShort((short) (str.length() * 2));
        byteBufferAllocate.put(str.getBytes(charset));
        return byteBufferAllocate.array();
    }

    public static String adjustLicenseServerUrl(String str) {
        return "<LA_URL>https://x</LA_URL>".equals(str) ? "" : (Util.SDK_INT < 33 || !"https://default.url".equals(str)) ? str : "";
    }

    public static byte[] adjustRequestData(UUID uuid, byte[] bArr) {
        return C.CLEARKEY_UUID.equals(uuid) ? ClearKeyUtil.adjustRequestData(bArr) : bArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] adjustRequestInitData(java.util.UUID r3, byte[] r4) {
        /*
            java.util.UUID r0 = androidx.media3.common.C.PLAYREADY_UUID
            boolean r1 = r0.equals(r3)
            if (r1 == 0) goto L19
            byte[] r1 = androidx.media3.extractor.mp4.PsshAtomUtil.parseSchemeSpecificData(r4, r3)
            if (r1 != 0) goto Lf
            goto L10
        Lf:
            r4 = r1
        L10:
            byte[] r4 = addLaUrlAttributeIfMissing(r4)
            r1 = 0
            byte[] r4 = androidx.media3.extractor.mp4.PsshAtomUtil.buildPsshAtom(r0, r1, r4)
        L19:
            int r1 = androidx.media3.common.util.Util.SDK_INT
            r2 = 23
            if (r1 >= r2) goto L27
            java.util.UUID r1 = androidx.media3.common.C.WIDEVINE_UUID
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L59
        L27:
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L60
            java.lang.String r0 = "Amazon"
            java.lang.String r1 = androidx.media3.common.util.Util.MANUFACTURER
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L60
            java.lang.String r0 = androidx.media3.common.util.Util.MODEL
            java.lang.String r1 = "AFTB"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L59
            java.lang.String r1 = "AFTS"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L59
            java.lang.String r1 = "AFTM"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L59
            java.lang.String r1 = "AFTT"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L60
        L59:
            byte[] r3 = androidx.media3.extractor.mp4.PsshAtomUtil.parseSchemeSpecificData(r4, r3)
            if (r3 == 0) goto L60
            return r3
        L60:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.drm.FrameworkMediaDrm.adjustRequestInitData(java.util.UUID, byte[]):byte[]");
    }

    public static String adjustRequestMimeType(UUID uuid, String str) {
        return (Util.SDK_INT < 26 && C.CLEARKEY_UUID.equals(uuid) && ("video/mp4".equals(str) || "audio/mp4".equals(str))) ? "cenc" : str;
    }

    public static UUID adjustUuid(UUID uuid) {
        return (Util.SDK_INT >= 27 || !C.CLEARKEY_UUID.equals(uuid)) ? uuid : C.COMMON_PSSH_UUID;
    }

    public static void forceWidevineL3(MediaDrm mediaDrm) {
        mediaDrm.setPropertyString("securityLevel", "L3");
    }

    public static DrmInitData.SchemeData getSchemeData(UUID uuid, List<DrmInitData.SchemeData> list) {
        if (!C.WIDEVINE_UUID.equals(uuid)) {
            return list.get(0);
        }
        if (Util.SDK_INT >= 28 && list.size() > 1) {
            DrmInitData.SchemeData schemeData = list.get(0);
            int length = 0;
            for (int i = 0; i < list.size(); i++) {
                DrmInitData.SchemeData schemeData2 = list.get(i);
                byte[] bArr = schemeData2.data;
                bArr.getClass();
                if (Util.areEqual(schemeData2.mimeType, schemeData.mimeType) && Util.areEqual(schemeData2.licenseServerUrl, schemeData.licenseServerUrl) && PsshAtomUtil.isPsshAtom(bArr)) {
                    length += bArr.length;
                }
            }
            byte[] bArr2 = new byte[length];
            int i2 = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                byte[] bArr3 = list.get(i3).data;
                bArr3.getClass();
                int length2 = bArr3.length;
                System.arraycopy(bArr3, 0, bArr2, i2, length2);
                i2 += length2;
            }
            return schemeData.copyWithData(bArr2);
        }
        for (int i4 = 0; i4 < list.size(); i4++) {
            DrmInitData.SchemeData schemeData3 = list.get(i4);
            byte[] bArr4 = schemeData3.data;
            bArr4.getClass();
            int version = PsshAtomUtil.parseVersion(bArr4);
            int i5 = Util.SDK_INT;
            if ((i5 < 23 && version == 0) || (i5 >= 23 && version == 1)) {
                return schemeData3;
            }
        }
        return list.get(0);
    }

    public static boolean isCryptoSchemeSupported(UUID uuid) {
        return MediaDrm.isCryptoSchemeSupported(adjustUuid(uuid));
    }

    public static boolean needsForceWidevineL3Workaround() {
        return "ASUS_Z00AD".equals(Util.MODEL);
    }

    @UnstableApi
    public static FrameworkMediaDrm newInstance(UUID uuid) throws UnsupportedDrmException {
        try {
            return new FrameworkMediaDrm(uuid);
        } catch (UnsupportedSchemeException e) {
            throw new UnsupportedDrmException(1, e);
        } catch (Exception e2) {
            throw new UnsupportedDrmException(2, e2);
        }
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @UnstableApi
    public synchronized void acquire() {
        Assertions.checkState(this.referenceCount > 0);
        this.referenceCount++;
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @UnstableApi
    public void closeSession(byte[] bArr) {
        this.mediaDrm.closeSession(bArr);
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @UnstableApi
    public int getCryptoType() {
        return 2;
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @SuppressLint({"WrongConstant"})
    @UnstableApi
    public ExoMediaDrm.KeyRequest getKeyRequest(byte[] bArr, @Nullable List<DrmInitData.SchemeData> list, int i, @Nullable HashMap<String, String> map) throws NotProvisionedException {
        DrmInitData.SchemeData schemeData;
        byte[] bArrAdjustRequestInitData;
        String strAdjustRequestMimeType;
        if (list != null) {
            schemeData = getSchemeData(this.uuid, list);
            UUID uuid = this.uuid;
            byte[] bArr2 = schemeData.data;
            bArr2.getClass();
            bArrAdjustRequestInitData = adjustRequestInitData(uuid, bArr2);
            strAdjustRequestMimeType = adjustRequestMimeType(this.uuid, schemeData.mimeType);
        } else {
            schemeData = null;
            bArrAdjustRequestInitData = null;
            strAdjustRequestMimeType = null;
        }
        MediaDrm.KeyRequest keyRequest = this.mediaDrm.getKeyRequest(bArr, bArrAdjustRequestInitData, strAdjustRequestMimeType, i, map);
        byte[] bArrAdjustRequestData = adjustRequestData(this.uuid, keyRequest.getData());
        String strAdjustLicenseServerUrl = adjustLicenseServerUrl(keyRequest.getDefaultUrl());
        if (TextUtils.isEmpty(strAdjustLicenseServerUrl) && schemeData != null && !TextUtils.isEmpty(schemeData.licenseServerUrl)) {
            strAdjustLicenseServerUrl = schemeData.licenseServerUrl;
        }
        return new ExoMediaDrm.KeyRequest(bArrAdjustRequestData, strAdjustLicenseServerUrl, Util.SDK_INT >= 23 ? keyRequest.getRequestType() : Integer.MIN_VALUE);
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @Nullable
    @UnstableApi
    public PersistableBundle getMetrics() {
        if (Util.SDK_INT < 28) {
            return null;
        }
        return this.mediaDrm.getMetrics();
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @RequiresApi(29)
    @UnstableApi
    public List<byte[]> getOfflineLicenseKeySetIds() {
        if (Util.SDK_INT >= 29) {
            return this.mediaDrm.getOfflineLicenseKeySetIds();
        }
        throw new UnsupportedOperationException();
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @UnstableApi
    public byte[] getPropertyByteArray(String str) {
        return this.mediaDrm.getPropertyByteArray(str);
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @UnstableApi
    public String getPropertyString(String str) {
        return this.mediaDrm.getPropertyString(str);
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @UnstableApi
    public ExoMediaDrm.ProvisionRequest getProvisionRequest() {
        MediaDrm.ProvisionRequest provisionRequest = this.mediaDrm.getProvisionRequest();
        return new ExoMediaDrm.ProvisionRequest(provisionRequest.getData(), provisionRequest.getDefaultUrl());
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @UnstableApi
    public byte[] openSession() throws MediaDrmException {
        return this.mediaDrm.openSession();
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @Nullable
    @UnstableApi
    public byte[] provideKeyResponse(byte[] bArr, byte[] bArr2) throws DeniedByServerException, NotProvisionedException {
        if (C.CLEARKEY_UUID.equals(this.uuid)) {
            bArr2 = ClearKeyUtil.adjustResponseData(bArr2);
        }
        return this.mediaDrm.provideKeyResponse(bArr, bArr2);
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @UnstableApi
    public void provideProvisionResponse(byte[] bArr) throws DeniedByServerException {
        this.mediaDrm.provideProvisionResponse(bArr);
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @UnstableApi
    public Map<String, String> queryKeyStatus(byte[] bArr) {
        return this.mediaDrm.queryKeyStatus(bArr);
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @UnstableApi
    public synchronized void release() {
        int i = this.referenceCount - 1;
        this.referenceCount = i;
        if (i == 0) {
            this.mediaDrm.release();
        }
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @RequiresApi(29)
    @UnstableApi
    public void removeOfflineLicense(byte[] bArr) {
        if (Util.SDK_INT < 29) {
            throw new UnsupportedOperationException();
        }
        this.mediaDrm.removeOfflineLicense(bArr);
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @UnstableApi
    public boolean requiresSecureDecoder(byte[] bArr, String str) {
        if (Util.SDK_INT >= 31) {
            return Api31.requiresSecureDecoder(this.mediaDrm, str);
        }
        try {
            MediaCrypto mediaCrypto = new MediaCrypto(this.uuid, bArr);
            try {
                return mediaCrypto.requiresSecureDecoderComponent(str);
            } finally {
                mediaCrypto.release();
            }
        } catch (MediaCryptoException unused) {
            return true;
        }
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @UnstableApi
    public void restoreKeys(byte[] bArr, byte[] bArr2) {
        this.mediaDrm.restoreKeys(bArr, bArr2);
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @UnstableApi
    public void setOnEventListener(@Nullable final ExoMediaDrm.OnEventListener onEventListener) {
        this.mediaDrm.setOnEventListener(onEventListener == null ? null : new MediaDrm.OnEventListener() { // from class: androidx.media3.exoplayer.drm.FrameworkMediaDrm$$ExternalSyntheticLambda2
            @Override // android.media.MediaDrm.OnEventListener
            public final void onEvent(MediaDrm mediaDrm, byte[] bArr, int i, int i2, byte[] bArr2) {
                FrameworkMediaDrm.$r8$lambda$FTMrYBm0_nl4l9MpEYMiZMFYRQE(this.f$0, onEventListener, mediaDrm, bArr, i, i2, bArr2);
            }
        });
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @RequiresApi(23)
    @UnstableApi
    public void setOnExpirationUpdateListener(@Nullable final ExoMediaDrm.OnExpirationUpdateListener onExpirationUpdateListener) {
        if (Util.SDK_INT < 23) {
            throw new UnsupportedOperationException();
        }
        this.mediaDrm.setOnExpirationUpdateListener(onExpirationUpdateListener == null ? null : new MediaDrm.OnExpirationUpdateListener() { // from class: androidx.media3.exoplayer.drm.FrameworkMediaDrm$$ExternalSyntheticLambda1
            @Override // android.media.MediaDrm.OnExpirationUpdateListener
            public final void onExpirationUpdate(MediaDrm mediaDrm, byte[] bArr, long j) {
                FrameworkMediaDrm.m140$r8$lambda$ofscM7m9YZH4gea4zOZQfoikU8(this.f$0, onExpirationUpdateListener, mediaDrm, bArr, j);
            }
        }, (Handler) null);
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @RequiresApi(23)
    @UnstableApi
    public void setOnKeyStatusChangeListener(@Nullable final ExoMediaDrm.OnKeyStatusChangeListener onKeyStatusChangeListener) {
        if (Util.SDK_INT < 23) {
            throw new UnsupportedOperationException();
        }
        this.mediaDrm.setOnKeyStatusChangeListener(onKeyStatusChangeListener == null ? null : new MediaDrm.OnKeyStatusChangeListener() { // from class: androidx.media3.exoplayer.drm.FrameworkMediaDrm$$ExternalSyntheticLambda3
            @Override // android.media.MediaDrm.OnKeyStatusChangeListener
            public final void onKeyStatusChange(MediaDrm mediaDrm, byte[] bArr, List list, boolean z) {
                FrameworkMediaDrm.m141$r8$lambda$n5JKhJipzndj_Tm6P_Wdm1cRk(this.f$0, onKeyStatusChangeListener, mediaDrm, bArr, list, z);
            }
        }, (Handler) null);
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @UnstableApi
    public void setPlayerIdForSession(byte[] bArr, PlayerId playerId) {
        if (Util.SDK_INT >= 31) {
            try {
                Api31.setLogSessionIdOnMediaDrmSession(this.mediaDrm, bArr, playerId);
            } catch (UnsupportedOperationException unused) {
                Log.w("FrameworkMediaDrm", "setLogSessionId failed.");
            }
        }
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @UnstableApi
    public void setPropertyByteArray(String str, byte[] bArr) {
        this.mediaDrm.setPropertyByteArray(str, bArr);
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @UnstableApi
    public void setPropertyString(String str, String str2) {
        this.mediaDrm.setPropertyString(str, str2);
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @UnstableApi
    public FrameworkCryptoConfig createCryptoConfig(byte[] bArr) throws MediaCryptoException {
        return new FrameworkCryptoConfig(adjustUuid(this.uuid), bArr, Util.SDK_INT < 21 && C.WIDEVINE_UUID.equals(this.uuid) && "L3".equals(this.mediaDrm.getPropertyString("securityLevel")));
    }
}
