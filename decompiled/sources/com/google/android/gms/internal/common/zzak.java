package com.google.android.gms.internal.common;

import com.google.errorprone.annotations.DoNotCall;
import java.util.ListIterator;
import org.jspecify.nullness.NullMarked;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@NullMarked
public abstract class zzak extends zzaj implements ListIterator {
    @Override // java.util.ListIterator
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
