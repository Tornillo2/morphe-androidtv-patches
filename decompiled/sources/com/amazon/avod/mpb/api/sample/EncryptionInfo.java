package com.amazon.avod.mpb.api.sample;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.media3.common.TrackGroup$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.media.drm.DrmUtils;
import java.util.Arrays;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.builders.ListBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class EncryptionInfo {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final byte[] ZERO_INITIALIZATION_VECTOR = {0, 0, 0, 0, 0, 0, 0, 0};

    @NotNull
    public final int[] clearRegions;

    @NotNull
    public final int[] encryptedRegions;

    @NotNull
    public final byte[] initVector;

    @NotNull
    public final byte[] keyId;

    @NotNull
    public final String sessionId;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final EncryptionInfo generateClearTextEncryptionInfo(int i) {
            return new EncryptionInfo(EncryptionInfo.ZERO_INITIALIZATION_VECTOR, new byte[0], new int[]{i}, new int[]{0}, "");
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public EncryptionInfo(@NotNull byte[] initVector, @NotNull byte[] keyId, @NotNull int[] clearRegions, @NotNull int[] encryptedRegions, @NotNull String sessionId) {
        Intrinsics.checkNotNullParameter(initVector, "initVector");
        Intrinsics.checkNotNullParameter(keyId, "keyId");
        Intrinsics.checkNotNullParameter(clearRegions, "clearRegions");
        Intrinsics.checkNotNullParameter(encryptedRegions, "encryptedRegions");
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        this.initVector = initVector;
        this.keyId = keyId;
        this.clearRegions = clearRegions;
        this.encryptedRegions = encryptedRegions;
        this.sessionId = sessionId;
    }

    public static /* synthetic */ EncryptionInfo copy$default(EncryptionInfo encryptionInfo, byte[] bArr, byte[] bArr2, int[] iArr, int[] iArr2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            bArr = encryptionInfo.initVector;
        }
        if ((i & 2) != 0) {
            bArr2 = encryptionInfo.keyId;
        }
        if ((i & 4) != 0) {
            iArr = encryptionInfo.clearRegions;
        }
        if ((i & 8) != 0) {
            iArr2 = encryptionInfo.encryptedRegions;
        }
        if ((i & 16) != 0) {
            str = encryptionInfo.sessionId;
        }
        String str2 = str;
        int[] iArr3 = iArr;
        return encryptionInfo.copy(bArr, bArr2, iArr3, iArr2, str2);
    }

    public static final CharSequence toString$lambda$1(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE + it + ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE;
    }

    @NotNull
    public final byte[] component1() {
        return this.initVector;
    }

    @NotNull
    public final byte[] component2() {
        return this.keyId;
    }

    @NotNull
    public final int[] component3() {
        return this.clearRegions;
    }

    @NotNull
    public final int[] component4() {
        return this.encryptedRegions;
    }

    @NotNull
    public final String component5() {
        return this.sessionId;
    }

    @NotNull
    public final EncryptionInfo copy(@NotNull byte[] initVector, @NotNull byte[] keyId, @NotNull int[] clearRegions, @NotNull int[] encryptedRegions, @NotNull String sessionId) {
        Intrinsics.checkNotNullParameter(initVector, "initVector");
        Intrinsics.checkNotNullParameter(keyId, "keyId");
        Intrinsics.checkNotNullParameter(clearRegions, "clearRegions");
        Intrinsics.checkNotNullParameter(encryptedRegions, "encryptedRegions");
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        return new EncryptionInfo(initVector, keyId, clearRegions, encryptedRegions, sessionId);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!EncryptionInfo.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.amazon.avod.mpb.api.sample.EncryptionInfo");
        EncryptionInfo encryptionInfo = (EncryptionInfo) obj;
        return Arrays.equals(this.initVector, encryptionInfo.initVector) && Arrays.equals(this.keyId, encryptionInfo.keyId) && Arrays.equals(this.clearRegions, encryptionInfo.clearRegions) && Arrays.equals(this.encryptedRegions, encryptionInfo.encryptedRegions) && Intrinsics.areEqual(this.sessionId, encryptionInfo.sessionId);
    }

    @NotNull
    public final int[] getClearRegions() {
        return this.clearRegions;
    }

    @NotNull
    public final int[] getEncryptedRegions() {
        return this.encryptedRegions;
    }

    @NotNull
    public final byte[] getInitVector() {
        return this.initVector;
    }

    @NotNull
    public final byte[] getKeyId() {
        return this.keyId;
    }

    @NotNull
    public final String getSessionId() {
        return this.sessionId;
    }

    public int hashCode() {
        return this.sessionId.hashCode() + ((Arrays.hashCode(this.encryptedRegions) + ((Arrays.hashCode(this.clearRegions) + ((Arrays.hashCode(this.keyId) + (Arrays.hashCode(this.initVector) * 31)) * 31)) * 31)) * 31);
    }

    @NotNull
    public String toString() {
        List listCreateListBuilder = CollectionsKt__CollectionsJVMKt.createListBuilder();
        int length = this.clearRegions.length;
        for (int i = 0; i < length; i++) {
            ListBuilder listBuilder = (ListBuilder) listCreateListBuilder;
            listBuilder.add(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("clearBytes:", this.clearRegions[i]));
            listBuilder.add("encryptedBytes:" + this.encryptedRegions[i]);
        }
        List listBuild = CollectionsKt__CollectionsJVMKt.build(listCreateListBuilder);
        DrmUtils drmUtils = DrmUtils.INSTANCE;
        String hexString = drmUtils.toHexString(this.initVector);
        String hexString2 = drmUtils.toHexString(this.keyId);
        String strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(listBuild, null, null, null, 0, null, new EncryptionInfo$$ExternalSyntheticLambda0(), 31, null);
        String str = this.sessionId;
        StringBuilder sbM = TrackGroup$$ExternalSyntheticOutline0.m("{\"initVector\":\"", hexString, "\",\"keyId\":\"", hexString2, "\",\"encryptedRegions\":[");
        sbM.append(strJoinToString$default);
        sbM.append("],\"sessionId\":\"");
        sbM.append(str);
        sbM.append("\"}");
        return sbM.toString();
    }
}
