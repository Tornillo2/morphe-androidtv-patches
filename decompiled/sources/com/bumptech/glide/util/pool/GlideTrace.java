package com.bumptech.glide.util.pool;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class GlideTrace {
    public static final int MAX_LENGTH = 127;
    public static final boolean TRACING_ENABLED = false;

    public static void beginSectionFormat(String str, Object obj) {
    }

    public static String truncateTag(String str) {
        return str.length() > 127 ? str.substring(0, 126) : str;
    }

    public static void beginSectionFormat(String str, Object obj, Object obj2) {
    }

    public static void beginSectionFormat(String str, Object obj, Object obj2, Object obj3) {
    }

    public static void endSection() {
    }

    public static void beginSection(String str) {
    }
}
