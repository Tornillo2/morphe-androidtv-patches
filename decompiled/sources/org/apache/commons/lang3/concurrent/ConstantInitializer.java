package org.apache.commons.lang3.concurrent;

import j$.util.Objects;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ConstantInitializer<T> implements ConcurrentInitializer<T> {
    public static final String FMT_TO_STRING = "ConstantInitializer@%d [ object = %s ]";
    public final T object;

    public ConstantInitializer(T t) {
        this.object = t;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ConstantInitializer) {
            return Objects.equals(this.object, ((ConstantInitializer) obj).object);
        }
        return false;
    }

    @Override // org.apache.commons.lang3.concurrent.ConcurrentInitializer
    public T get() throws ConcurrentException {
        return this.object;
    }

    public final T getObject() {
        return this.object;
    }

    public int hashCode() {
        T t = this.object;
        if (t != null) {
            return t.hashCode();
        }
        return 0;
    }

    public String toString() {
        return String.format(FMT_TO_STRING, Integer.valueOf(System.identityHashCode(this)), String.valueOf(this.object));
    }
}
