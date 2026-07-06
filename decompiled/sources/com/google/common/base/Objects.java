package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class Objects extends ExtraObjectsMethodsForWeb {
    public static boolean equal(Object a, Object b) {
        if (a != b) {
            return a != null && a.equals(b);
        }
        return true;
    }

    public static int hashCode(Object... objects) {
        return Arrays.hashCode(objects);
    }
}
