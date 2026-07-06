package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class Absent<T> extends Optional<T> {
    public static final Absent<Object> INSTANCE = new Absent<>();

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 0;

    public static <T> Optional<T> withType() {
        return INSTANCE;
    }

    @Override // com.google.common.base.Optional
    public Set<T> asSet() {
        return Collections.EMPTY_SET;
    }

    @Override // com.google.common.base.Optional
    public boolean equals(Object object) {
        return object == this;
    }

    @Override // com.google.common.base.Optional
    public T get() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    @Override // com.google.common.base.Optional
    public int hashCode() {
        return 2040732332;
    }

    @Override // com.google.common.base.Optional
    public boolean isPresent() {
        return false;
    }

    @Override // com.google.common.base.Optional
    public T or(T defaultValue) {
        Preconditions.checkNotNull(defaultValue, "use Optional.orNull() instead of Optional.or(null)");
        return defaultValue;
    }

    @Override // com.google.common.base.Optional
    public T orNull() {
        return null;
    }

    public final Object readResolve() {
        return INSTANCE;
    }

    @Override // com.google.common.base.Optional
    public String toString() {
        return "Optional.absent()";
    }

    @Override // com.google.common.base.Optional
    public <V> Optional<V> transform(Function<? super T, V> function) {
        function.getClass();
        return INSTANCE;
    }

    @Override // com.google.common.base.Optional
    public T or(Supplier<? extends T> supplier) {
        T t = supplier.get();
        Preconditions.checkNotNull(t, "use Optional.orNull() instead of a Supplier that returns null");
        return t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.base.Optional
    public Optional<T> or(Optional<? extends T> secondChoice) {
        secondChoice.getClass();
        return secondChoice;
    }
}
