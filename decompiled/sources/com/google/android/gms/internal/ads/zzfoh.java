package com.google.android.gms.internal.ads;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzfoh extends zzfog {
    public final char zza;

    public zzfoh(char c) {
        this.zza = c;
    }

    public final String toString() {
        int i = this.zza;
        char[] cArr = new char[6];
        cArr[0] = '\\';
        cArr[1] = AbstractJsonLexerKt.UNICODE_ESC;
        cArr[2] = 0;
        cArr[3] = 0;
        cArr[4] = 0;
        cArr[5] = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            cArr[5 - i2] = "0123456789ABCDEF".charAt(i & 15);
            i >>= 4;
        }
        return MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("CharMatcher.is('", String.copyValueOf(cArr), "')");
    }

    @Override // com.google.android.gms.internal.ads.zzfok
    public final boolean zzb(char c) {
        return c == this.zza;
    }
}
