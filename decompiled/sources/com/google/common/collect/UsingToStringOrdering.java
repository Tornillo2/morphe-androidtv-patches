package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(serializable = true)
public final class UsingToStringOrdering extends Ordering<Object> implements Serializable {
    public static final UsingToStringOrdering INSTANCE = new UsingToStringOrdering();

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 0;

    private Object readResolve() {
        return INSTANCE;
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(Object left, Object right) {
        return left.toString().compareTo(right.toString());
    }

    public String toString() {
        return "Ordering.usingToString()";
    }
}
