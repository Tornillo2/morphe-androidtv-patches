package com.google.common.hash;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.Throwable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class SneakyThrows<T extends Throwable> {
    @CanIgnoreReturnValue
    public static Error sneakyThrow(Throwable t) throws Throwable {
        throw t;
    }

    public final Error throwIt(Throwable t) throws Throwable {
        throw t;
    }
}
