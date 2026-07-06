package com.google.android.exoplayer2.util;

import android.os.Trace;
import androidx.annotation.RequiresApi;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class TraceUtil {
    public static void beginSection(String str) {
        if (Util.SDK_INT >= 18) {
            Trace.beginSection(str);
        }
    }

    @RequiresApi(18)
    public static void beginSectionV18(String str) {
        Trace.beginSection(str);
    }

    public static void endSection() {
        if (Util.SDK_INT >= 18) {
            Trace.endSection();
        }
    }

    @RequiresApi(18)
    public static void endSectionV18() {
        Trace.endSection();
    }
}
