package com.google.common.base;

import com.google.common.annotations.GwtCompatible;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class CommonPattern {
    public static CommonPattern compile(String pattern) {
        return Platform.compilePattern(pattern);
    }

    public static boolean isPcreLike() {
        Platform.patternCompiler.getClass();
        return true;
    }

    public abstract int flags();

    public abstract CommonMatcher matcher(CharSequence t);

    public abstract String pattern();

    public abstract String toString();
}
