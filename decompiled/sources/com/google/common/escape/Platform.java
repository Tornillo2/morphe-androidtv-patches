package com.google.common.escape;

import com.google.common.annotations.GwtCompatible;
import j$.util.Objects;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class Platform {
    public static final ThreadLocal<char[]> DEST_TL = new AnonymousClass1();

    /* JADX INFO: renamed from: com.google.common.escape.Platform$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends ThreadLocal<char[]> {
        @Override // java.lang.ThreadLocal
        public char[] initialValue() {
            return new char[1024];
        }
    }

    public static char[] charBufferFromThreadLocal() {
        char[] cArr = DEST_TL.get();
        Objects.requireNonNull(cArr);
        return cArr;
    }
}
