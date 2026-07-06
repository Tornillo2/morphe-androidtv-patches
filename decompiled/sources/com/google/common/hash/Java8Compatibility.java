package com.google.common.hash;

import com.google.common.annotations.GwtIncompatible;
import java.nio.Buffer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public final class Java8Compatibility {
    public static void clear(Buffer b) {
        b.clear();
    }

    public static void flip(Buffer b) {
        b.flip();
    }

    public static void limit(Buffer b, int limit) {
        b.limit(limit);
    }

    public static void position(Buffer b, int position) {
        b.position(position);
    }
}
