package com.amazon.ion.util;

import com.amazon.ion.IonValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class IonValueUtils {
    public static final boolean anyNull(IonValue ionValue) {
        return ionValue == null || ionValue.isNullValue();
    }
}
