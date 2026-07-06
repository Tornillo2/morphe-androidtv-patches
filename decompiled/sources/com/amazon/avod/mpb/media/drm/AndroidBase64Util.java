package com.amazon.avod.mpb.media.drm;

import android.util.Base64;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidBase64Util implements Base64Util {
    @Override // com.amazon.avod.mpb.media.drm.Base64Util
    @NotNull
    public byte[] decode(@NotNull String base64) {
        Intrinsics.checkNotNullParameter(base64, "base64");
        byte[] bArrDecode = Base64.decode(base64, 2);
        Intrinsics.checkNotNullExpressionValue(bArrDecode, "decode(...)");
        return bArrDecode;
    }

    @Override // com.amazon.avod.mpb.media.drm.Base64Util
    @NotNull
    public String encode(@NotNull byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        String strEncodeToString = Base64.encodeToString(bytes, 2);
        Intrinsics.checkNotNullExpressionValue(strEncodeToString, "encodeToString(...)");
        return strEncodeToString;
    }
}
