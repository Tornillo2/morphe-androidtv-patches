package com.amazon.livingroom.mediapipelinebackend;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.OptIn;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.util.UnstableApi;
import java.math.BigInteger;
import java.util.Formatter;
import java.util.Locale;
import java.util.UUID;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@OptIn(markerClass = {UnstableApi.class})
public class DrmUtils {
    public DrmUtils() {
        throw new UnsupportedOperationException("Abstract utility class; do not instantiate");
    }

    public static void appendDrmSchemeData(Formatter formatter, DrmInitData.SchemeData schemeData) {
        formatter.format("{ uuid=%s, mimeType=%s, sessionId=%s }, ", getDrmSchemeId(schemeData), schemeData.mimeType, toString(schemeData.data));
    }

    public static UUID getDrmSchemeId(DrmInitData.SchemeData schemeData) {
        UUID uuid = C.WIDEVINE_UUID;
        boolean zMatches = schemeData.matches(uuid);
        UUID uuid2 = C.PLAYREADY_UUID;
        boolean zMatches2 = schemeData.matches(uuid2);
        return (!zMatches || zMatches2) ? (!zMatches2 || zMatches) ? C.UUID_NIL : uuid2 : uuid;
    }

    @NonNull
    public static String toString(@Nullable byte[] bArr) {
        return bArr == null ? AbstractJsonLexerKt.NULL : new BigInteger(1, bArr).toString(16);
    }

    @NonNull
    public static String toString(@NonNull DrmInitData drmInitData) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        for (int i = 0; i < drmInitData.schemeDataCount; i++) {
            appendDrmSchemeData(formatter, drmInitData.schemeDatas[i]);
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }
}
